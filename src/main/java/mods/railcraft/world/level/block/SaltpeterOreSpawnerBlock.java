package mods.railcraft.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.ticks.TickPriority;

public class SaltpeterOreSpawnerBlock extends Block {

    public SaltpeterOreSpawnerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean pmovedbypiston) {
        level.scheduleTick(pos, this, 20, TickPriority.NORMAL);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.scheduleTick(pos, this, 20, TickPriority.NORMAL);

        if (random.nextInt(32) != 0)
            return;

        int belowSurfaceY = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, pos).getY() - 2;
        if ((belowSurfaceY < 50) || (belowSurfaceY > 100))
            return;

        var belowSurface = new BlockPos(pos.getX(), belowSurfaceY, pos.getZ());

        var block = level.getBlockState(belowSurface);
        if (!block.is(Blocks.SAND))
            return;

        var above = level.getBlockState(pos.above());
        if (!above.is(Blocks.SAND))
            return;

        boolean genSurface = random.nextInt(25) == 0;
        if (!genSurface) {
            var below = level.getBlockState(belowSurface.below());
            if (!below.is(Blocks.SAND) && !below.is(Blocks.SANDSTONE))
                return;

            int airCount = 0;
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                boolean isAir = level.getBlockState(belowSurface.relative(dir)).is(Blocks.AIR);
                if (isAir)
                    ++airCount;

                if (airCount > 1)
                    return;

                if (isAir)
                    continue;

                block = level.getBlockState(belowSurface.relative(dir));
                if (!block.is(Blocks.SAND)
                        && !block.is(Blocks.SANDSTONE)
                        && !block.is(RailcraftBlocks.SALTPETER_ORE.get())) {
                    return;
                }
            }
        }
        level.setBlock(genSurface ? belowSurface.above() : belowSurface,
            RailcraftBlocks.SALTPETER_ORE.get().defaultBlockState(), Block.UPDATE_CLIENTS);
    }
}
