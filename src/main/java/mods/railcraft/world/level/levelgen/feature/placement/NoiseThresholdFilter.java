package mods.railcraft.world.level.levelgen.feature.placement;

import org.joml.SimplexNoise;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class NoiseThresholdFilter extends PlacementFilter {

    private final float noiseFactor;

    private final float noiseThreshold;

    public static final Codec<NoiseThresholdFilter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.FLOAT.fieldOf("noise_factor")
            .forGetter(filter -> filter.noiseFactor),
        Codec.FLOAT.fieldOf("noise_threshold")
            .forGetter(filter -> filter.noiseThreshold)
    ).apply(instance, NoiseThresholdFilter::new));

    private NoiseThresholdFilter(float noiseFactor, float noiseThreshold) {
        this.noiseFactor = noiseFactor;
        this.noiseThreshold = noiseThreshold;
    }

    public static NoiseThresholdFilter of(float noiseFactor, float noiseThreshold) {
        return new NoiseThresholdFilter(noiseFactor, noiseThreshold);
    }

    @Override
    protected boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos pos) {
        return (SimplexNoise.noise(pos.getX() / noiseFactor, pos.getZ() / noiseFactor) >= noiseThreshold);
    }

    @Override
    public PlacementModifierType<NoiseThresholdFilter> type() {
        return RailcraftPlacementModifiers.NOISE_THRESHOLD_FILTER.get();
    }
}
