/*------------------------------------------------------------------------------
 Copyright (c) Railcraft Reborn, 2023+

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/
package mods.railcraft.api.signal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;
import mods.railcraft.api.core.BlockEntityLike;
import mods.railcraft.api.core.CompoundTagKeys;
import mods.railcraft.api.core.NetworkSerializable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.INBTSerializable;

/**
 *
 * @author Sm0keySa1m0n
 *
 * @param <T>
 */
public abstract class AbstractSignalNetwork<T extends BlockEntityLike>
    implements SignalNetwork<T>, INBTSerializable<CompoundTag>, NetworkSerializable {

  protected static final RandomSource RANDOM = RandomSource.create();

  private final Class<T> peerType;
  private final int maxPeers;
  private final Runnable syncListener;

  protected final Deque<BlockPos> peers = new ArrayDeque<>();
  private final Collection<BlockPos> unmodifiablePeers =
      Collections.unmodifiableCollection(this.peers);

  private boolean linking;

  public AbstractSignalNetwork(Class<T> peerType, Runnable syncListener) {
    this(peerType, -1, syncListener);
  }

  public AbstractSignalNetwork(Class<T> peerType, int maxPeers, Runnable syncListener) {
    this.peerType = peerType;
    this.maxPeers = maxPeers;
    this.syncListener = syncListener;
  }

  public abstract Level getLevel();

  @Override
  public Optional<T> peerAt(BlockPos blockPos) {
    return this.peers.contains(blockPos)
        ? Optional.ofNullable(this.getBlockEntity(blockPos))
        : Optional.empty();
  }

  @Nullable
  protected T getBlockEntity(BlockPos blockPos) {
    var blockEntity = this.getLevel().getBlockEntity(blockPos);
    return this.peerType.isInstance(blockEntity) && !blockEntity.isRemoved()
        ? this.peerType.cast(blockEntity)
        : null;
  }

  @Override
  public Collection<BlockPos> peers() {
    return this.unmodifiablePeers;
  }

  @Override
  public boolean addPeer(T peer) {
    if (this.peers.contains(peer.asBlockEntity().getBlockPos())) {
      return false;
    }
    var peerPos = peer.asBlockEntity().getBlockPos();
    this.peers.add(peerPos);
    if (this.maxPeers > -1 && this.peers.size() > this.maxPeers) {
      this.removePeer(this.peers.peek());
    }
    this.syncToClient();
    return true;
  }

  @Override
  public void destroy() {
    List<BlockPos> peers = new ArrayList<>(this.peers);
    for (var peerPos : peers) {
      this.removePeer(peerPos);
    }
  }

  @Override
  public void refresh() {
    this.peers.removeIf(peerPos -> this.peerAt(peerPos).filter(this::refreshPeer).isEmpty());
  }

  protected boolean refreshPeer(T peer) {
    return true;
  }

  @Override
  public boolean removePeer(BlockPos peerPos) {
    if (this.peers.remove(peerPos)) {
      this.syncToClient();
      return true;
    }
    return false;
  }

  @Override
  public boolean isLinking() {
    return this.linking;
  }

  @Override
  public void startLinking() {
    this.linking = true;
    this.syncToClient();
  }

  @Override
  public void stopLinking() {
    this.linking = false;
    this.syncToClient();
  }

  @Override
  public CompoundTag serializeNBT(HolderLookup.Provider provider) {
    var peersTag = new ListTag();
    for (var peer : this.peers) {
      var posTag = new CompoundTag();
      posTag.put(CompoundTagKeys.POS, NbtUtils.writeBlockPos(peer));
      peersTag.add(posTag);
    }
    var tag = new CompoundTag();
    tag.put(CompoundTagKeys.PEER_POS, peersTag);
    return tag;
  }

  @Override
  public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
    var peersTag = tag.getList(CompoundTagKeys.PEER_POS, Tag.TAG_COMPOUND);
    peersTag.stream()
        .map(CompoundTag.class::cast)
        .map(posTag -> NbtUtils.readBlockPos(posTag, CompoundTagKeys.POS).orElseThrow())
        .forEach(this.peers::add);
  }

  @Override
  public void writeToBuf(RegistryFriendlyByteBuf data) {
    data.writeBoolean(this.linking);
    data.writeCollection(this.peers, RegistryFriendlyByteBuf::writeBlockPos);
  }

  @Override
  public void readFromBuf(RegistryFriendlyByteBuf data) {
    this.linking = data.readBoolean();
    this.peers.clear();
    this.peers.addAll(data.readList(RegistryFriendlyByteBuf::readBlockPos));
  }

  public void syncToClient() {
    this.syncListener.run();
  }
}
