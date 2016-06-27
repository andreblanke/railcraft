/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.tracks.speedcontroller;

import mods.railcraft.api.tracks.ITrackInstance;
import mods.railcraft.common.blocks.tracks.TrackTools;
import mods.railcraft.common.blocks.tracks.instances.TrackSpeed;
import mods.railcraft.common.core.RailcraftConfig;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class SpeedControllerHighSpeed extends SpeedController {

    private static final int LOOK_AHEAD_DIST = 2;
    private static final float SPEED_SLOPE = 0.45f;
    private static SpeedController instance;

    public static SpeedController instance() {
        if (instance == null)
            instance = new SpeedControllerHighSpeed();
        return instance;
    }

    @Override
    public float getMaxSpeed(ITrackInstance track, EntityMinecart cart) {
        Float speed = null;
        if (track instanceof TrackSpeed)
            speed = ((TrackSpeed) track).maxSpeed;
        if (speed == null)
            speed = speedForCurrentTrack(track, cart);
        if (track instanceof TrackSpeed)
            ((TrackSpeed) track).maxSpeed = speed;
        return speed;
    }

    public static float speedForCurrentTrack(ITrackInstance track, EntityMinecart cart) {
        World world = track.theWorld();
        assert world != null;
        BlockRailBase.EnumRailDirection dir = TrackTools.getTrackDirection(world, track.getPos(), cart);
        if (dir != null && dir.isAscending())
            return SPEED_SLOPE;
        return speedForNextTrack(world, track.getPos(), 0, cart);
    }

    private static float speedForNextTrack(World world, BlockPos pos, int dist, EntityMinecart cart) {
        float maxSpeed = RailcraftConfig.getMaxHighSpeed();
        if (dist < LOOK_AHEAD_DIST)
            for (EnumFacing side : EnumFacing.HORIZONTALS) {
                BlockPos nextPos = pos.offset(side);
                boolean foundTrack = TrackTools.isRailBlockAt(world, nextPos);
                if (!foundTrack) {
                    if (TrackTools.isRailBlockAt(world, nextPos.up())) {
                        foundTrack = true;
                        nextPos = nextPos.up();
                    } else if (TrackTools.isRailBlockAt(world, nextPos.down())) {
                        foundTrack = true;
                        nextPos = nextPos.down();
                    }
                }
                if (foundTrack) {
                    BlockRailBase.EnumRailDirection dir = TrackTools.getTrackDirection(world, nextPos, cart);
                    if (dir != null && dir.isAscending())
                        return SPEED_SLOPE;
                    maxSpeed = speedForNextTrack(world, nextPos, dist + 1, cart);
                    if (maxSpeed == SPEED_SLOPE)
                        return SPEED_SLOPE;
                }
            }

        return maxSpeed;
    }
}
