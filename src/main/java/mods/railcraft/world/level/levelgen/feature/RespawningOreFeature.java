package mods.railcraft.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import mods.railcraft.world.level.levelgen.feature.configuration.RespawningOreConfiguration;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class RespawningOreFeature extends Feature<RespawningOreConfiguration> {

    public RespawningOreFeature(Codec<RespawningOreConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RespawningOreConfiguration> context) {
        var level  = context.level();
        var origin = context.origin();
        level.setBlock(origin,
            context.config().oreProvider().getState(context.random(), origin),
            Block.UPDATE_CLIENTS);
        level.setBlock(origin.atY(level.getMinBuildHeight()),
            context.config().oreSpawnerProvider().getState(context.random(), origin),
            Block.UPDATE_CLIENTS);
        return true;
    }
}
