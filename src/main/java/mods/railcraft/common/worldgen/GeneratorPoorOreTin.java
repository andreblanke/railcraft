/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2016
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.worldgen;

import mods.railcraft.common.blocks.ore.EnumOre;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class GeneratorPoorOreTin extends GeneratorPoorOre {

    //    public static final EventType EVENT_TYPE = EnumHelper.addEnum(EventType.class, "RC_POOR_TIN", new Class[0], new Object[0]);
    private static final int Y_LEVEL = 50;
    private static final int Y_RANGE = 2;
    private static final int DENSITY = 2;
    private static final int SEED = 50;

    public GeneratorPoorOreTin() {
        super(EventType.CUSTOM, EnumOre.POOR_TIN, DENSITY, Y_LEVEL, Y_RANGE, SEED);
    }

}
