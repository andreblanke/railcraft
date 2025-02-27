package mods.railcraft.data;

import java.util.concurrent.CompletableFuture;
import mods.railcraft.api.core.RailcraftConstants;
import mods.railcraft.tags.RailcraftTags;
import mods.railcraft.world.level.block.RailcraftBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class RailcraftBlockTagsProvider extends BlockTagsProvider {

  public RailcraftBlockTagsProvider(PackOutput packOutput,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      ExistingFileHelper fileHelper) {
    super(packOutput, lookupProvider, RailcraftConstants.ID, fileHelper);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void addTags(HolderLookup.Provider provider) {
    this.tag(RailcraftTags.Blocks.BALLAST)
        .addTag(Tags.Blocks.GRAVELS);
    this.tag(RailcraftTags.Blocks.TRACK_UNDERCUTTER_INVALID_BALLAST)
        .addTags(Tags.Blocks.SANDS, BlockTags.SAND);
    this.tag(RailcraftTags.Blocks.SWITCH_TRACK_ACTUATOR)
        .add(RailcraftBlocks.SWITCH_TRACK_LEVER.get())
        .add(RailcraftBlocks.SWITCH_TRACK_MOTOR.get())
        .add(RailcraftBlocks.SWITCH_TRACK_ROUTER.get());
    this.tag(BlockTags.RAILS)
        .addTag(RailcraftTags.Blocks.ABANDONED_TRACK)
        .addTag(RailcraftTags.Blocks.ELECTRIC_TRACK)
        .addTag(RailcraftTags.Blocks.HIGH_SPEED_TRACK)
        .addTag(RailcraftTags.Blocks.HIGH_SPEED_ELECTRIC_TRACK)
        .addTag(RailcraftTags.Blocks.IRON_TRACK)
        .addTag(RailcraftTags.Blocks.REINFORCED_TRACK)
        .addTag(RailcraftTags.Blocks.STRAP_IRON_TRACK)
        .add(RailcraftBlocks.FORCE_TRACK.get());
    this.tag(BlockTags.CLIMBABLE)
        .add(RailcraftBlocks.ELEVATOR_TRACK.get());
    this.tag(RailcraftTags.Blocks.ASPECT_EMITTER)
        .add(RailcraftBlocks.SIGNAL_CAPACITOR_BOX.get(),
            RailcraftBlocks.SIGNAL_RECEIVER_BOX.get(),
            RailcraftBlocks.SIGNAL_BLOCK_RELAY_BOX.get(),
            RailcraftBlocks.SIGNAL_SEQUENCER_BOX.get(),
            RailcraftBlocks.TOKEN_SIGNAL_BOX.get());
    this.tag(RailcraftTags.Blocks.ASPECT_RECEIVER)
        .add(RailcraftBlocks.SIGNAL_CAPACITOR_BOX.get(),
            RailcraftBlocks.SIGNAL_CONTROLLER_BOX.get(),
            RailcraftBlocks.SIGNAL_INTERLOCK_BOX.get(),
            RailcraftBlocks.SIGNAL_SEQUENCER_BOX.get());
    this.tag(RailcraftTags.Blocks.ABANDONED_TRACK)
        .add(RailcraftBlocks.ABANDONED_TRACK.get(),
            RailcraftBlocks.ABANDONED_LOCKING_TRACK.get(),
            RailcraftBlocks.ABANDONED_ACTIVATOR_TRACK.get(),
            RailcraftBlocks.ABANDONED_BOOSTER_TRACK.get(),
            RailcraftBlocks.ABANDONED_CONTROL_TRACK.get(),
            RailcraftBlocks.ABANDONED_GATED_TRACK.get(),
            RailcraftBlocks.ABANDONED_DETECTOR_TRACK.get(),
            RailcraftBlocks.ABANDONED_COUPLER_TRACK.get(),
            RailcraftBlocks.ABANDONED_EMBARKING_TRACK.get(),
            RailcraftBlocks.ABANDONED_DISEMBARKING_TRACK.get(),
            RailcraftBlocks.ABANDONED_DUMPING_TRACK.get(),
            RailcraftBlocks.ABANDONED_TURNOUT_TRACK.get(),
            RailcraftBlocks.ABANDONED_WYE_TRACK.get(),
            RailcraftBlocks.ABANDONED_JUNCTION_TRACK.get(),
            RailcraftBlocks.ABANDONED_LAUNCHER_TRACK.get(),
            RailcraftBlocks.ABANDONED_ONE_WAY_TRACK.get(),
            RailcraftBlocks.ABANDONED_WHISTLE_TRACK.get(),
            RailcraftBlocks.ABANDONED_LOCOMOTIVE_TRACK.get(),
            RailcraftBlocks.ABANDONED_THROTTLE_TRACK.get(),
            RailcraftBlocks.ABANDONED_ROUTING_TRACK.get());
    this.tag(RailcraftTags.Blocks.ELECTRIC_TRACK)
        .add(RailcraftBlocks.ELECTRIC_TRACK.get(),
            RailcraftBlocks.ELECTRIC_LOCKING_TRACK.get(),
            RailcraftBlocks.ELECTRIC_ACTIVATOR_TRACK.get(),
            RailcraftBlocks.ELECTRIC_BOOSTER_TRACK.get(),
            RailcraftBlocks.ELECTRIC_CONTROL_TRACK.get(),
            RailcraftBlocks.ELECTRIC_GATED_TRACK.get(),
            RailcraftBlocks.ELECTRIC_DETECTOR_TRACK.get(),
            RailcraftBlocks.ELECTRIC_COUPLER_TRACK.get(),
            RailcraftBlocks.ELECTRIC_EMBARKING_TRACK.get(),
            RailcraftBlocks.ELECTRIC_DISEMBARKING_TRACK.get(),
            RailcraftBlocks.ELECTRIC_DUMPING_TRACK.get(),
            RailcraftBlocks.ELECTRIC_TURNOUT_TRACK.get(),
            RailcraftBlocks.ELECTRIC_WYE_TRACK.get(),
            RailcraftBlocks.ELECTRIC_JUNCTION_TRACK.get(),
            RailcraftBlocks.ELECTRIC_LAUNCHER_TRACK.get(),
            RailcraftBlocks.ELECTRIC_ONE_WAY_TRACK.get(),
            RailcraftBlocks.ELECTRIC_WHISTLE_TRACK.get(),
            RailcraftBlocks.ELECTRIC_LOCOMOTIVE_TRACK.get(),
            RailcraftBlocks.ELECTRIC_THROTTLE_TRACK.get(),
            RailcraftBlocks.ELECTRIC_ROUTING_TRACK.get());
    this.tag(RailcraftTags.Blocks.HIGH_SPEED_TRACK)
        .add(RailcraftBlocks.HIGH_SPEED_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_TRANSITION_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_LOCKING_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ACTIVATOR_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_BOOSTER_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_DETECTOR_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_TURNOUT_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_WYE_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_JUNCTION_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_WHISTLE_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_LOCOMOTIVE_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_THROTTLE_TRACK.get());
    this.tag(RailcraftTags.Blocks.HIGH_SPEED_ELECTRIC_TRACK)
        .add(RailcraftBlocks.HIGH_SPEED_ELECTRIC_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_TRANSITION_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_LOCKING_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_ACTIVATOR_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_BOOSTER_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_DETECTOR_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_TURNOUT_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_WYE_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_JUNCTION_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_WHISTLE_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_LOCOMOTIVE_TRACK.get(),
            RailcraftBlocks.HIGH_SPEED_ELECTRIC_THROTTLE_TRACK.get());
    this.tag(RailcraftTags.Blocks.IRON_TRACK)
        .add(RailcraftBlocks.IRON_LOCKING_TRACK.get(),
            RailcraftBlocks.IRON_ACTIVATOR_TRACK.get(),
            RailcraftBlocks.IRON_BOOSTER_TRACK.get(),
            RailcraftBlocks.IRON_CONTROL_TRACK.get(),
            RailcraftBlocks.IRON_GATED_TRACK.get(),
            RailcraftBlocks.IRON_DETECTOR_TRACK.get(),
            RailcraftBlocks.IRON_COUPLER_TRACK.get(),
            RailcraftBlocks.IRON_EMBARKING_TRACK.get(),
            RailcraftBlocks.IRON_DISEMBARKING_TRACK.get(),
            RailcraftBlocks.IRON_DUMPING_TRACK.get(),
            RailcraftBlocks.IRON_TURNOUT_TRACK.get(),
            RailcraftBlocks.IRON_WYE_TRACK.get(),
            RailcraftBlocks.IRON_JUNCTION_TRACK.get(),
            RailcraftBlocks.IRON_LAUNCHER_TRACK.get(),
            RailcraftBlocks.IRON_ONE_WAY_TRACK.get(),
            RailcraftBlocks.IRON_WHISTLE_TRACK.get(),
            RailcraftBlocks.IRON_LOCOMOTIVE_TRACK.get(),
            RailcraftBlocks.IRON_THROTTLE_TRACK.get(),
            RailcraftBlocks.IRON_ROUTING_TRACK.get());
    this.tag(RailcraftTags.Blocks.REINFORCED_TRACK)
        .add(RailcraftBlocks.REINFORCED_TRACK.get(),
            RailcraftBlocks.REINFORCED_LOCKING_TRACK.get(),
            RailcraftBlocks.REINFORCED_ACTIVATOR_TRACK.get(),
            RailcraftBlocks.REINFORCED_BOOSTER_TRACK.get(),
            RailcraftBlocks.REINFORCED_CONTROL_TRACK.get(),
            RailcraftBlocks.REINFORCED_GATED_TRACK.get(),
            RailcraftBlocks.REINFORCED_DETECTOR_TRACK.get(),
            RailcraftBlocks.REINFORCED_COUPLER_TRACK.get(),
            RailcraftBlocks.REINFORCED_EMBARKING_TRACK.get(),
            RailcraftBlocks.REINFORCED_DISEMBARKING_TRACK.get(),
            RailcraftBlocks.REINFORCED_DUMPING_TRACK.get(),
            RailcraftBlocks.REINFORCED_TURNOUT_TRACK.get(),
            RailcraftBlocks.REINFORCED_WYE_TRACK.get(),
            RailcraftBlocks.REINFORCED_JUNCTION_TRACK.get(),
            RailcraftBlocks.REINFORCED_LAUNCHER_TRACK.get(),
            RailcraftBlocks.REINFORCED_ONE_WAY_TRACK.get(),
            RailcraftBlocks.REINFORCED_WHISTLE_TRACK.get(),
            RailcraftBlocks.REINFORCED_LOCOMOTIVE_TRACK.get(),
            RailcraftBlocks.REINFORCED_THROTTLE_TRACK.get(),
            RailcraftBlocks.REINFORCED_ROUTING_TRACK.get());
    this.tag(RailcraftTags.Blocks.STRAP_IRON_TRACK)
        .add(RailcraftBlocks.STRAP_IRON_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_LOCKING_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_ACTIVATOR_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_BOOSTER_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_CONTROL_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_GATED_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_DETECTOR_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_COUPLER_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_EMBARKING_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_DISEMBARKING_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_DUMPING_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_TURNOUT_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_WYE_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_JUNCTION_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_LAUNCHER_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_ONE_WAY_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_WHISTLE_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_LOCOMOTIVE_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_THROTTLE_TRACK.get(),
            RailcraftBlocks.STRAP_IRON_ROUTING_TRACK.get());

    this.tag(RailcraftTags.Blocks.SIGNAL)
        .add(RailcraftBlocks.BLOCK_SIGNAL.get(), RailcraftBlocks.DISTANT_SIGNAL.get(),
            RailcraftBlocks.TOKEN_SIGNAL.get(), RailcraftBlocks.DUAL_BLOCK_SIGNAL.get(),
            RailcraftBlocks.DUAL_DISTANT_SIGNAL.get(), RailcraftBlocks.DUAL_TOKEN_SIGNAL.get());
    this.tag(RailcraftTags.Blocks.MINEABLE_WITH_CROWBAR)
        .add(RailcraftBlocks.ANALOG_SIGNAL_CONTROLLER_BOX.get(),
            RailcraftBlocks.ELEVATOR_TRACK.get())
        .addTag(RailcraftTags.Blocks.ASPECT_EMITTER)
        .addTag(RailcraftTags.Blocks.ASPECT_RECEIVER)
        .addTag(RailcraftTags.Blocks.SIGNAL)
        .addTag(RailcraftTags.Blocks.SWITCH_TRACK_ACTUATOR)
        .addTag(BlockTags.RAILS)
        .addTag(RailcraftTags.Blocks.DETECTOR);

    this.tag(BlockTags.ANVIL)
        .add(RailcraftBlocks.STEEL_ANVIL.get(),
            RailcraftBlocks.CHIPPED_STEEL_ANVIL.get(),
            RailcraftBlocks.DAMAGED_STEEL_ANVIL.get());

    this.tag(BlockTags.MINEABLE_WITH_AXE)
        .add(RailcraftBlocks.MANUAL_ROLLING_MACHINE.get())
        .add(RailcraftBlocks.WATER_TANK_SIDING.get());

    this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .add(RailcraftBlocks.FIRESTONE_ORE.get())
        .add(RailcraftBlocks.LOW_PRESSURE_STEAM_BOILER_TANK.get())
        .add(RailcraftBlocks.HIGH_PRESSURE_STEAM_BOILER_TANK.get())
        .add(RailcraftBlocks.SOLID_FUELED_FIREBOX.get())
        .add(RailcraftBlocks.FLUID_FUELED_FIREBOX.get())
        .add(RailcraftBlocks.STEAM_TURBINE.get())
        .add(RailcraftBlocks.COKE_OVEN_BRICKS.get())
        .add(RailcraftBlocks.BLAST_FURNACE_BRICKS.get())
        .add(RailcraftBlocks.POWERED_ROLLING_MACHINE.get())
        .add(RailcraftBlocks.CRUSHER.get())
        .add(RailcraftBlocks.STEAM_OVEN.get())
        .add(RailcraftBlocks.FEED_STATION.get())
        .add(RailcraftBlocks.LOGBOOK.get())
        .add(RailcraftBlocks.COAL_COKE_BLOCK.get())
        .add(RailcraftBlocks.CRUSHED_OBSIDIAN.get())
        .add(RailcraftBlocks.ADVANCED_ITEM_LOADER.get())
        .add(RailcraftBlocks.ADVANCED_ITEM_UNLOADER.get())
        .add(RailcraftBlocks.ITEM_LOADER.get())
        .add(RailcraftBlocks.ITEM_UNLOADER.get())
        .add(RailcraftBlocks.FLUID_LOADER.get())
        .add(RailcraftBlocks.FLUID_UNLOADER.get())
        .add(RailcraftBlocks.CART_DISPENSER.get())
        .add(RailcraftBlocks.TRAIN_DISPENSER.get())
        .add(RailcraftBlocks.STEEL_BLOCK.get())
        .add(RailcraftBlocks.BRASS_BLOCK.get())
        .add(RailcraftBlocks.BRONZE_BLOCK.get())
        .add(RailcraftBlocks.INVAR_BLOCK.get())
        .add(RailcraftBlocks.LEAD_BLOCK.get())
        .add(RailcraftBlocks.NICKEL_BLOCK.get())
        .add(RailcraftBlocks.SILVER_BLOCK.get())
        .add(RailcraftBlocks.TIN_BLOCK.get())
        .add(RailcraftBlocks.ZINC_BLOCK.get())
        .add(RailcraftBlocks.FORCE_TRACK_EMITTER.get())
        .add(RailcraftBlocks.NICKEL_IRON_BATTERY.get())
        .add(RailcraftBlocks.NICKEL_ZINC_BATTERY.get())
        .add(RailcraftBlocks.ZINC_SILVER_BATTERY.get())
        .add(RailcraftBlocks.ZINC_SILVER_BATTERY_EMPTY.get())
        .add(RailcraftBlocks.ZINC_CARBON_BATTERY.get())
        .add(RailcraftBlocks.ZINC_CARBON_BATTERY_EMPTY.get())
        .add(RailcraftBlocks.FRAME.get())
        .add(RailcraftBlocks.PERSONAL_WORLD_SPIKE.get())
        .add(RailcraftBlocks.WORLD_SPIKE.get())
        .addTags(RailcraftTags.Blocks.LEAD_ORE,
            RailcraftTags.Blocks.NICKEL_ORE,
            RailcraftTags.Blocks.SILVER_ORE,
            RailcraftTags.Blocks.SULFUR_ORE,
            RailcraftTags.Blocks.TIN_ORE,
            RailcraftTags.Blocks.ZINC_ORE,
            RailcraftTags.Blocks.SALTPETER_ORE,
            RailcraftTags.Blocks.POST,
            RailcraftTags.Blocks.STRENGTHENED_GLASS,
            RailcraftTags.Blocks.IRON_TANK_GAUGE,
            RailcraftTags.Blocks.IRON_TANK_VALVE,
            RailcraftTags.Blocks.IRON_TANK_WALL,
            RailcraftTags.Blocks.STEEL_TANK_GAUGE,
            RailcraftTags.Blocks.STEEL_TANK_VALVE,
            RailcraftTags.Blocks.STEEL_TANK_WALL)
        .addTag(RailcraftTags.Blocks.ABYSSAL)
        .add(RailcraftBlocks.ABYSSAL_BRICK_STAIRS.get())
        .add(RailcraftBlocks.ABYSSAL_PAVER_STAIRS.get())
        .add(RailcraftBlocks.ABYSSAL_BRICK_SLAB.get())
        .add(RailcraftBlocks.ABYSSAL_PAVER_SLAB.get())
        .addTag(RailcraftTags.Blocks.QUARRIED)
        .add(RailcraftBlocks.QUARRIED_BRICK_STAIRS.get())
        .add(RailcraftBlocks.QUARRIED_PAVER_STAIRS.get())
        .add(RailcraftBlocks.QUARRIED_BRICK_SLAB.get())
        .add(RailcraftBlocks.QUARRIED_PAVER_SLAB.get());

    this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
        .add(RailcraftBlocks.FIRESTONE_ORE.get())
        .add(RailcraftBlocks.CRUSHED_OBSIDIAN.get())
        .add(RailcraftBlocks.WORLD_SPIKE.get())
        .add(RailcraftBlocks.PERSONAL_WORLD_SPIKE.get());

    this.tag(BlockTags.NEEDS_IRON_TOOL)
        .add(RailcraftBlocks.STEEL_BLOCK.get())
        .add(RailcraftBlocks.BRASS_BLOCK.get())
        .add(RailcraftBlocks.BRONZE_BLOCK.get())
        .add(RailcraftBlocks.INVAR_BLOCK.get())
        .add(RailcraftBlocks.LEAD_BLOCK.get())
        .add(RailcraftBlocks.NICKEL_BLOCK.get())
        .add(RailcraftBlocks.SILVER_BLOCK.get())
        .add(RailcraftBlocks.TIN_BLOCK.get())
        .add(RailcraftBlocks.ZINC_BLOCK.get())
        .add(RailcraftBlocks.FORCE_TRACK_EMITTER.get());

    this.tag(BlockTags.NEEDS_STONE_TOOL)
        .add(RailcraftBlocks.LOW_PRESSURE_STEAM_BOILER_TANK.get())
        .add(RailcraftBlocks.HIGH_PRESSURE_STEAM_BOILER_TANK.get())
        .add(RailcraftBlocks.SOLID_FUELED_FIREBOX.get())
        .add(RailcraftBlocks.FLUID_FUELED_FIREBOX.get())
        .add(RailcraftBlocks.STEAM_TURBINE.get())
        .add(RailcraftBlocks.COAL_COKE_BLOCK.get())
        .add(RailcraftBlocks.CRUSHER.get())
        .add(RailcraftBlocks.STEAM_OVEN.get())
        .add(RailcraftBlocks.BLAST_FURNACE_BRICKS.get())
        .add(RailcraftBlocks.FEED_STATION.get())
        .add(RailcraftBlocks.LOGBOOK.get())
        .addTags(RailcraftTags.Blocks.LEAD_ORE,
            RailcraftTags.Blocks.NICKEL_ORE,
            RailcraftTags.Blocks.SILVER_ORE,
            RailcraftTags.Blocks.SULFUR_ORE,
            RailcraftTags.Blocks.TIN_ORE,
            RailcraftTags.Blocks.ZINC_ORE,
            RailcraftTags.Blocks.SALTPETER_ORE,
            RailcraftTags.Blocks.POST,
            RailcraftTags.Blocks.STRENGTHENED_GLASS,
            RailcraftTags.Blocks.IRON_TANK_GAUGE,
            RailcraftTags.Blocks.IRON_TANK_VALVE,
            RailcraftTags.Blocks.IRON_TANK_WALL,
            RailcraftTags.Blocks.STEEL_TANK_GAUGE,
            RailcraftTags.Blocks.STEEL_TANK_VALVE,
            RailcraftTags.Blocks.STEEL_TANK_WALL);

    RailcraftBlocks.STRENGTHENED_GLASS.boundVariants()
        .forEach(x -> this.tag(RailcraftTags.Blocks.STRENGTHENED_GLASS).add(x));
    RailcraftBlocks.IRON_TANK_GAUGE.boundVariants()
        .forEach(x -> this.tag(RailcraftTags.Blocks.IRON_TANK_GAUGE).add(x));
    RailcraftBlocks.IRON_TANK_VALVE.boundVariants()
        .forEach(x -> this.tag(RailcraftTags.Blocks.IRON_TANK_VALVE).add(x));
    RailcraftBlocks.IRON_TANK_WALL.boundVariants()
        .forEach(x -> this.tag(RailcraftTags.Blocks.IRON_TANK_WALL).add(x));
    RailcraftBlocks.STEEL_TANK_GAUGE.boundVariants()
        .forEach(x -> this.tag(RailcraftTags.Blocks.STEEL_TANK_GAUGE).add(x));
    RailcraftBlocks.STEEL_TANK_VALVE.boundVariants()
        .forEach(x -> this.tag(RailcraftTags.Blocks.STEEL_TANK_VALVE).add(x));
    RailcraftBlocks.STEEL_TANK_WALL.boundVariants()
        .forEach(x -> this.tag(RailcraftTags.Blocks.STEEL_TANK_WALL).add(x));
    RailcraftBlocks.POST.boundVariants()
        .forEach(x -> this.tag(RailcraftTags.Blocks.POST).add(x));

    this.tag(RailcraftTags.Blocks.QUARRIED)
        .add(RailcraftBlocks.QUARRIED_STONE.get(),
            RailcraftBlocks.QUARRIED_COBBLESTONE.get(),
            RailcraftBlocks.POLISHED_QUARRIED_STONE.get(),
            RailcraftBlocks.CHISELED_QUARRIED_STONE.get(),
            RailcraftBlocks.ETCHED_QUARRIED_STONE.get(),
            RailcraftBlocks.QUARRIED_BRICKS.get(),
            RailcraftBlocks.QUARRIED_PAVER.get());
    this.tag(RailcraftTags.Blocks.QUARRIED_REPLACEABLE_BLOCKS)
        .addTags(BlockTags.DIRT)
        .addTags(BlockTags.BASE_STONE_OVERWORLD)
        .addTags(Tags.Blocks.ORES)
        .add(Blocks.CLAY)
        .add(Blocks.GRAVEL);

    this.tag(RailcraftTags.Blocks.ABYSSAL)
        .add(RailcraftBlocks.ABYSSAL_STONE.get(),
            RailcraftBlocks.ABYSSAL_COBBLESTONE.get(),
            RailcraftBlocks.POLISHED_ABYSSAL_STONE.get(),
            RailcraftBlocks.CHISELED_ABYSSAL_STONE.get(),
            RailcraftBlocks.ETCHED_ABYSSAL_STONE.get(),
            RailcraftBlocks.ABYSSAL_BRICKS.get(),
            RailcraftBlocks.ABYSSAL_PAVER.get());

    this.tag(RailcraftTags.Blocks.DETECTOR)
        .add(RailcraftBlocks.ADVANCED_DETECTOR.get(),
            RailcraftBlocks.AGE_DETECTOR.get(),
            RailcraftBlocks.ANIMAL_DETECTOR.get(),
            RailcraftBlocks.ANY_DETECTOR.get(),
            RailcraftBlocks.EMPTY_DETECTOR.get(),
            RailcraftBlocks.ITEM_DETECTOR.get(),
            RailcraftBlocks.LOCOMOTIVE_DETECTOR.get(),
            RailcraftBlocks.MOB_DETECTOR.get(),
            RailcraftBlocks.PLAYER_DETECTOR.get(),
            RailcraftBlocks.ROUTING_DETECTOR.get(),
            RailcraftBlocks.SHEEP_DETECTOR.get(),
            RailcraftBlocks.TANK_DETECTOR.get(),
            RailcraftBlocks.TRAIN_DETECTOR.get(),
            RailcraftBlocks.VILLAGER_DETECTOR.get());

    this.tag(Tags.Blocks.ORE_RATES_SINGULAR)
        .add(RailcraftBlocks.LEAD_ORE.get())
        .add(RailcraftBlocks.NICKEL_ORE.get())
        .add(RailcraftBlocks.SILVER_ORE.get())
        .add(RailcraftBlocks.TIN_ORE.get())
        .add(RailcraftBlocks.ZINC_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_LEAD_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_NICKEL_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_SILVER_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_TIN_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_ZINC_ORE.get())
        .add(RailcraftBlocks.FIRESTONE_ORE.get());

    this.tag(Tags.Blocks.ORE_RATES_DENSE)
        .add(RailcraftBlocks.SULFUR_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_SULFUR_ORE.get())
        .add(RailcraftBlocks.SALTPETER_ORE.get());

    this.tag(RailcraftTags.Blocks.LEAD_ORE)
        .add(RailcraftBlocks.LEAD_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_LEAD_ORE.get());

    this.tag(RailcraftTags.Blocks.NICKEL_ORE)
        .add(RailcraftBlocks.NICKEL_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_NICKEL_ORE.get());

    this.tag(RailcraftTags.Blocks.SILVER_ORE)
        .add(RailcraftBlocks.SILVER_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_SILVER_ORE.get());

    this.tag(RailcraftTags.Blocks.SULFUR_ORE)
        .add(RailcraftBlocks.SULFUR_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_SULFUR_ORE.get());

    this.tag(RailcraftTags.Blocks.TIN_ORE)
        .add(RailcraftBlocks.TIN_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_TIN_ORE.get());

    this.tag(RailcraftTags.Blocks.ZINC_ORE)
        .add(RailcraftBlocks.ZINC_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_ZINC_ORE.get());

    this.tag(RailcraftTags.Blocks.SALTPETER_ORE)
        .add(RailcraftBlocks.SALTPETER_ORE.get());

    this.tag(Tags.Blocks.ORES)
        .add(RailcraftBlocks.FIRESTONE_ORE.get())
        .addTags(RailcraftTags.Blocks.LEAD_ORE,
            RailcraftTags.Blocks.NICKEL_ORE,
            RailcraftTags.Blocks.SILVER_ORE,
            RailcraftTags.Blocks.SULFUR_ORE,
            RailcraftTags.Blocks.TIN_ORE,
            RailcraftTags.Blocks.ZINC_ORE,
            RailcraftTags.Blocks.SALTPETER_ORE);

    this.tag(Tags.Blocks.ORES_IN_GROUND_STONE)
        .add(RailcraftBlocks.LEAD_ORE.get())
        .add(RailcraftBlocks.NICKEL_ORE.get())
        .add(RailcraftBlocks.SILVER_ORE.get())
        .add(RailcraftBlocks.TIN_ORE.get())
        .add(RailcraftBlocks.ZINC_ORE.get())
        .add(RailcraftBlocks.SULFUR_ORE.get())
        .add(RailcraftBlocks.SALTPETER_ORE.get());
    this.tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE)
        .add(RailcraftBlocks.DEEPSLATE_LEAD_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_NICKEL_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_SILVER_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_TIN_ORE.get())
        .add(RailcraftBlocks.DEEPSLATE_ZINC_ORE.get());
    this.tag(Tags.Blocks.ORES_IN_GROUND_NETHERRACK)
        .add(RailcraftBlocks.FIRESTONE_ORE.get());

    this.tag(Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST)
        .addTag(RailcraftTags.Blocks.STRENGTHENED_GLASS)
        .addTag(RailcraftTags.Blocks.IRON_TANK_GAUGE)
        .addTag(RailcraftTags.Blocks.IRON_TANK_VALVE)
        .addTag(RailcraftTags.Blocks.IRON_TANK_WALL)
        .addTag(RailcraftTags.Blocks.STEEL_TANK_GAUGE)
        .addTag(RailcraftTags.Blocks.STEEL_TANK_VALVE)
        .addTag(RailcraftTags.Blocks.STEEL_TANK_WALL);
    this.tag(RailcraftTags.Blocks.TUNNEL_BORE_MINEABLE_BLOCKS)
        .add(Blocks.CLAY,
            Blocks.SNOW,
            Blocks.CACTUS,
            Blocks.CARROTS,
            Blocks.MOSSY_COBBLESTONE,
            Blocks.COCOA,
            Blocks.WHEAT,
            Blocks.DEAD_BUSH,
            Blocks.FIRE,
            Blocks.GLOWSTONE,
            Blocks.ICE,
            Blocks.MELON,
            Blocks.MELON_STEM,
            Blocks.BROWN_MUSHROOM,
            Blocks.BROWN_MUSHROOM_BLOCK,
            Blocks.RED_MUSHROOM,
            Blocks.RED_MUSHROOM_BLOCK,
            Blocks.MYCELIUM,
            Blocks.NETHER_WART,
            Blocks.POTATOES,
            Blocks.PUMPKIN,
            Blocks.PUMPKIN_STEM,
            Blocks.SAND,
            Blocks.SANDSTONE,
            Blocks.SOUL_SAND,
            Blocks.STONE,
            Blocks.FARMLAND,
            Blocks.TORCH,
            Blocks.VINE,
            Blocks.COBWEB,
            Blocks.END_STONE)
        .addTags(Tags.Blocks.ORES,
            Tags.Blocks.NETHERRACKS,
            Tags.Blocks.COBBLESTONES,
            Tags.Blocks.OBSIDIANS,
            Tags.Blocks.GRAVELS,
            BlockTags.DIRT,
            BlockTags.LEAVES,
            BlockTags.SAPLINGS,
            BlockTags.LOGS,
            BlockTags.FLOWERS,
            BlockTags.REPLACEABLE);
    this.tag(RailcraftTags.Blocks.TUNNEL_BORE_REPLACEABLE_BLOCKS)
        .add(Blocks.TORCH)
        .addTags(BlockTags.FLOWERS,
            BlockTags.REPLACEABLE);
  }
}
