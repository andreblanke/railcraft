package mods.railcraft.world.level.levelgen.feature;

import mods.railcraft.api.core.RailcraftConstants;
import mods.railcraft.world.level.levelgen.feature.configuration.QuarriedConfiguration;
import mods.railcraft.world.level.levelgen.feature.configuration.RespawningOreConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RailcraftFeatures {

  private static final DeferredRegister<Feature<?>> deferredRegister =
      DeferredRegister.create(Registries.FEATURE, RailcraftConstants.ID);

  public static final RegistryObject<QuarriedFeature> QUARRIED_STONE =
      deferredRegister.register("quarried", () -> new QuarriedFeature(QuarriedConfiguration.CODEC));

  public static final RegistryObject<RespawningOreFeature> RESPAWNING_ORE =
        deferredRegister.register("respawning_ore", () -> new RespawningOreFeature(RespawningOreConfiguration.CODEC));

  public static void register(IEventBus eventBus) {
    deferredRegister.register(eventBus);
  }
}
