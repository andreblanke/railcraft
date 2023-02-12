package mods.railcraft.world.entity.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import mods.railcraft.api.carts.Link;
import mods.railcraft.api.core.RailcraftConstantsAPI;
import mods.railcraft.api.core.RailcraftFakePlayer;
import mods.railcraft.api.track.TrackUtil;
import mods.railcraft.util.EntitySearcher;
import mods.railcraft.world.entity.vehicle.locomotive.Locomotive;
import mods.railcraft.world.item.CartItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import com.mojang.authlib.GameProfile;

public final class CartTools {

  public static void explodeCart(AbstractMinecart cart) {
    if (!cart.isAlive()) {
      return;
    }
    MinecartExtension.getOrThrow(cart).setHighSpeed(false);
    cart.setDeltaMovement(0, cart.getDeltaMovement().y(), 0);

    if (cart.level.isClientSide()) {
      return;
    }
    removePassengers(cart, cart.getX(), cart.getY() + 1.5D, cart.getZ());
    cart.level.explode(cart, cart.getX(), cart.getY(), cart.getZ(), 3F,
        Level.ExplosionInteraction.TNT);
    if (cart.level.getRandom().nextInt(2) == 0) {
      cart.kill();
    }
  }

  public static boolean cartVelocityIsLessThan(AbstractMinecart cart, float vel) {
    return Math.abs(cart.getDeltaMovement().x()) < vel
        && Math.abs(cart.getDeltaMovement().z()) < vel;
  }

  public static List<UUID> getMinecartUUIDsAt(Level level, BlockPos pos, float sensitivity) {
    return getMinecartUUIDsAt(level, pos.getX(), pos.getY(), pos.getZ(), sensitivity);
  }

  public static List<UUID> getMinecartUUIDsAt(Level level, int x, int y, int z, float sensitivity) {
    sensitivity = Math.min(sensitivity, 0.49f);
    return level
        .getEntitiesOfClass(AbstractMinecart.class,
            new AABB(x + sensitivity, y + sensitivity, z + sensitivity,
                x + 1 - sensitivity,
                y + 1 - sensitivity, z + 1 - sensitivity),
            EntitySelector.ENTITY_STILL_ALIVE)
        .stream()
        .map(Entity::getUUID)
        .collect(Collectors.toList());
  }

  public static void addPassenger(AbstractMinecart cart, Entity passenger) {
    passenger.startRiding(cart);
  }

  public static void removePassengers(AbstractMinecart cart) {
    removePassengers(cart, cart.getX(), cart.getY(), cart.getZ());
  }

  public static void removePassengers(AbstractMinecart cart, double x, double y, double z) {
    var passengers = cart.getPassengers();
    cart.ejectPassengers();
    for (var entity : passengers) {
      if (entity instanceof ServerPlayer player) {
        player.setPos(x, y, z);
      } else
        entity.moveTo(x, y, z, entity.getYRot(), entity.getXRot());
    }
  }

  public static ServerPlayer getFakePlayer(AbstractMinecart cart) {
    return RailcraftFakePlayer.get((ServerLevel) cart.level, cart.getX(), cart.getY(), cart.getZ());
  }

  public static ServerPlayer getFakePlayerWith(AbstractMinecart cart, ItemStack stack) {
    ServerPlayer player = getFakePlayer(cart);
    player.setItemInHand(InteractionHand.MAIN_HAND, stack);
    return player;
  }

  /**
   * Checks if the entity is in range to render.
   */
  public static boolean isInRangeToRenderDist(AbstractMinecart entity, double distance) {
    double range = entity.getBoundingBox().getSize();

    if (Double.isNaN(range)) {
      range = 1.0D;
    }

    range = range * 64.0D * CartConstants.RENDER_DIST_MULTIPLIER;
    return distance < range * range;
  }

  public static List<String> getDebugOutput(AbstractMinecart cart) {
    List<String> debug = new ArrayList<>();
    debug.add("Railcraft Minecart Data Dump");
    String cartInfo;
    if (cart.level.getGameRules().getBoolean(GameRules.RULE_REDUCEDDEBUGINFO)) {
      cartInfo = String.format("%s[\'%s\'/%d, l=\'%s\']", cart.getClass().getSimpleName(),
          cart.getName(), cart.getId(), cart.level.dimension().toString());
    } else {
      cartInfo = cart.toString();
    }
    debug.add("Object: " + cartInfo);
    debug.add("UUID: " + cart.getUUID());
    debug.add("LinkA: " + MinecartExtension.getOrThrow(cart).getLinkedMinecart(Link.FRONT)
        .map(AbstractMinecart::getUUID)
        .orElse(null));
    debug.add("LinkB: " + MinecartExtension.getOrThrow(cart).getLinkedMinecart(Link.BACK)
        .map(AbstractMinecart::getUUID)
        .orElse(null));
    debug.add(
        "Train: " + Train.get(cart).map(Train::getId).map(UUID::toString).orElse("NA on Client"));
    Train.get(cart).ifPresent(train -> {
      debug.add("Train Carts:");
      for (UUID uuid : train.getCarts()) {
        debug.add("  " + uuid);
      }
    });
    return debug;
  }

  /**
   * Returns a minecart from a persistent UUID. Only returns carts from the same world.
   *
   * @param id Cart's persistent UUID
   * @return AbstractMinecartEntity
   */
  @Nullable
  public static AbstractMinecart getCartFromUUID(@Nullable Level level,
      @Nullable UUID id) {
    if (level == null || id == null) {
      return null;
    }

    if (level instanceof ServerLevel serverLevel) {
      var entity = serverLevel.getEntity(id);
      return entity instanceof AbstractMinecart cart && entity.isAlive() ? cart : null;
    }

    return getClientCartFromUUID(level, id);
  }

  private static AbstractMinecart getClientCartFromUUID(Level level, UUID id) {
    if (!(level instanceof ClientLevel clientLevel))
      return null;
    // for performance reasons
    // noinspection Convert2streamapi
    for (Entity entity : clientLevel.entitiesForRendering()) {
      if (entity instanceof AbstractMinecart abstractMinecart && entity.isAlive()
          && entity.getUUID().equals(id))
        return abstractMinecart;
    }
    return null;
  }

  public static boolean startBoost(AbstractMinecart cart, BlockPos pos,
      RailShape dir, double startBoost) {
    Level level = cart.level;
    if (dir == RailShape.EAST_WEST) {
      if (Block.canSupportCenter(level, pos.west(), Direction.EAST)) {
        Vec3 motion = cart.getDeltaMovement();
        cart.setDeltaMovement(startBoost, motion.y(), motion.z());
        return true;

      } else if (Block.canSupportCenter(level, pos.east(), Direction.WEST)) {
        Vec3 motion = cart.getDeltaMovement();
        cart.setDeltaMovement(-startBoost, motion.y(), motion.z());
        return true;
      }
    } else if (dir == RailShape.NORTH_SOUTH) {
      if (Block.canSupportCenter(level, pos.north(), Direction.SOUTH)) {
        Vec3 motion = cart.getDeltaMovement();
        cart.setDeltaMovement(motion.x(), motion.y(), startBoost);
        return true;
      } else if (Block.canSupportCenter(level, pos.south(), Direction.NORTH)) {
        Vec3 motion = cart.getDeltaMovement();
        cart.setDeltaMovement(motion.x(), motion.y(), -startBoost);
        return true;
      }
    }
    return false;
  }

  public static void smackCart(AbstractMinecart cart, Player smacker,
      float smackVelocity) {
    smackCart(cart, cart, smacker, smackVelocity);
  }

  public static void smackCart(AbstractMinecart respect, AbstractMinecart cart,
      Player smacker,
      float smackVelocity) {
    cart.setDeltaMovement(
        cart.getDeltaMovement().add(Math.copySign(smackVelocity, respect.getX() - smacker.getX()),
            0, Math.copySign(smackVelocity, respect.getZ() - smacker.getZ())));
  }

  public static void initCartPos(AbstractMinecart entity, double x, double y, double z) {
    entity.setPos(x, y, z);
    entity.setOldPosAndRot();
    entity.setDeltaMovement(Vec3.ZERO);
  }

  @Nullable
  public static AbstractMinecart placeCart(ItemStack cartItem, ServerLevel level, BlockPos pos) {
    if (cartItem.isEmpty()) {
      return null;
    }
    var blockState = level.getBlockState(pos);
    if (!TrackUtil.isStraightTrackAt(level, pos)) {
      return null;
    }

    if (EntitySearcher.findMinecarts().at(pos).list(level).isEmpty()) {
      var trackShape = TrackUtil.getTrackDirection(level, pos, blockState);
      double h = trackShape.isAscending() ? 0.5 : 0.0;

      var cartStack = cartItem.copy();
      AbstractMinecart cart = null;

      if (cartItem.getItem() instanceof CartItem railcraftCartItem) {
        cart = railcraftCartItem.getMinecartFactory().createMinecart(cartStack, pos.getX() + 0.5,
            pos.getY() + 0.0625D + h, pos.getZ() + 0.5, level);
      } else if (cartItem.getItem() instanceof MinecartItem minecartItem) {
        cart = AbstractMinecart.createMinecart(level, pos.getX() + 0.5,
            pos.getY() + 0.0625D + h, pos.getZ() + 0.5, minecartItem.type);
      }

      if (cart == null) {
        return null;
      }

      if (cartStack.hasCustomHoverName())
        cart.setCustomName(cartStack.getDisplayName());
      level.addFreshEntity(cart);
      return cart;
    }
    return null;
  }

  @Nullable
  public static GameProfile getCartOwner(AbstractMinecart cart) {
    if (cart instanceof Locomotive loco) {
      var owner = loco.getOwner();
      if (owner.isPresent()) {
        if (owner.get().isComplete())
          return owner.get();
        String ownerName = RailcraftConstantsAPI.UNKNOWN_PLAYER;
        if (!StringUtils.isBlank(owner.get().getName()))
          ownerName = owner.get().getName();

        UUID ownerId = null;
        if (owner.get().getId() != null)
          ownerId = owner.get().getId();
        return new GameProfile(ownerId, ownerName);
      }
    }
    return null;
  }
}
