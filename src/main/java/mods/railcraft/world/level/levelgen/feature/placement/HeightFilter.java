package mods.railcraft.world.level.levelgen.feature.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class HeightFilter extends PlacementFilter {

    private final int minInclusive;

    private final int maxInclusive;

    public static final Codec<HeightFilter> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.INT.fieldOf("min_inclusive").forGetter(filter -> filter.minInclusive),
        Codec.INT.fieldOf("max_inclusive").forGetter(filter -> filter.maxInclusive)
    ).apply(instance, HeightFilter::new));

    private HeightFilter(int minInclusive, int maxInclusive) {
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    public static HeightFilter between(int minInclusive, int maxInclusive) {
        return new HeightFilter(minInclusive, maxInclusive);
    }

    @Override
    protected boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos pos) {
        return ((minInclusive <= pos.getY()) && (pos.getY() <= maxInclusive));
    }

    @Override
    public PlacementModifierType<HeightFilter> type() {
        return RailcraftPlacementModifiers.HEIGHT_FILTER.get();
    }
}
