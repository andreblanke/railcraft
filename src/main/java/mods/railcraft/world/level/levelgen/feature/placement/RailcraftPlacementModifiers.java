package mods.railcraft.world.level.levelgen.feature.placement;

import mods.railcraft.api.core.RailcraftConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RailcraftPlacementModifiers {

    private static final DeferredRegister<PlacementModifierType<?>> deferredRegister =
        DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, RailcraftConstants.ID);

    public static RegistryObject<PlacementModifierType<HeightFilter>> HEIGHT_FILTER =
        deferredRegister.register("height_filter", () -> () -> HeightFilter.CODEC);

    public static RegistryObject<PlacementModifierType<NoiseThresholdFilter>> NOISE_THRESHOLD_FILTER =
        deferredRegister.register("noise_threshold_filter", () -> () -> NoiseThresholdFilter.CODEC);

    private RailcraftPlacementModifiers() {
    }

    public static void register(IEventBus eventBus) {
        deferredRegister.register(eventBus);
    }
}
