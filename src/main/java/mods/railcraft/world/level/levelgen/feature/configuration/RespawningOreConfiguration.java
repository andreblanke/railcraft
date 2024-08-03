package mods.railcraft.world.level.levelgen.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record RespawningOreConfiguration(BlockStateProvider oreProvider, BlockStateProvider oreSpawnerProvider) implements FeatureConfiguration {

    public static final Codec<RespawningOreConfiguration> CODEC =
        RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("ore_provider")
                .forGetter(RespawningOreConfiguration::oreProvider),
            BlockStateProvider.CODEC.fieldOf("ore_spawner_provider")
                .forGetter(RespawningOreConfiguration::oreSpawnerProvider)
        ).apply(instance, RespawningOreConfiguration::new));
}
