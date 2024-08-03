package mods.railcraft.world.level.levelgen.feature.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class NearLavaFilter extends PlacementFilter {

    private static final NearLavaFilter INSTANCE = new NearLavaFilter();

    public static final Codec<NearLavaFilter> CODEC = Codec.unit(INSTANCE);

    @Override
    protected boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos origin) {
        var level = context.getLevel();
        for (var dir : Direction.Plane.HORIZONTAL) {
            var side = origin.relative(dir);

            var block = level.getBlockState(side);
            if (block.isAir())
                continue;

            if (block.getFluidState().is(FluidTags.LAVA))
                return true;
        }

        var pos = new BlockPos.MutableBlockPos(origin.getX(), origin.getY(), origin.getZ());
        for (int i = 0; i < 4; ++i) {
            pos.setY(origin.getY() - i);

            var block = level.getBlockState(pos);
            if (block.getFluidState().is(FluidTags.LAVA))
                return true;
            if (!block.isAir())
                return false;
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> type() {
        return RailcraftPlacementModifiers.NEAR_LAVA_FILTER.get();
    }
}
