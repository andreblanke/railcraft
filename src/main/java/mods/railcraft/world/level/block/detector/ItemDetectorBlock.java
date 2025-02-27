package mods.railcraft.world.level.block.detector;

import org.jetbrains.annotations.Nullable;
import mods.railcraft.world.level.block.entity.RailcraftBlockEntityTypes;
import mods.railcraft.world.level.block.entity.detector.DetectorBlockEntity;
import mods.railcraft.world.level.block.entity.detector.ItemDetectorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ItemDetectorBlock extends DetectorBlock {

  public ItemDetectorBlock(Properties properties) {
    super(properties);
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new ItemDetectorBlockEntity(pos, state);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
      BlockEntityType<T> type) {
    return level.isClientSide() ? null
        : createTickerHelper(type, RailcraftBlockEntityTypes.ITEM_DETECTOR.get(),
            DetectorBlockEntity::serverTick);
  }

  @Override
  protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos pos,
      Player player, BlockHitResult rayTraceResult) {
    if (player instanceof ServerPlayer serverPlayer) {
      level.getBlockEntity(pos, RailcraftBlockEntityTypes.ITEM_DETECTOR.get())
          .ifPresent(blockEntity -> serverPlayer.openMenu(blockEntity, pos));
    }
    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState state) {
    return state.getValue(POWERED);
  }

  @Override
  public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
    return level.getBlockEntity(pos, RailcraftBlockEntityTypes.ITEM_DETECTOR.get())
        .map(DetectorBlockEntity::getPowerState)
        .orElse(0);
  }
}
