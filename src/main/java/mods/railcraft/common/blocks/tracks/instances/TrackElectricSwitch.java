/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2016
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.blocks.tracks.instances;

import mods.railcraft.common.blocks.tracks.EnumTrack;

//TODO: migrate to new charge API
public class TrackElectricSwitch extends TrackSwitch {

//    private final ChargeHandler chargeHandler = new ChargeHandler(this, IChargeBlock.ConnectType.TRACK);

    @Override
    public EnumTrack getTrackType() {
        return EnumTrack.ELECTRIC_SWITCH;
    }

//    @Override
//    public ChargeHandler getChargeHandler() {
//        return chargeHandler;
//    }

//    @Override
//    public boolean canUpdate() {
//        return true;
//    }
//
//    @Override
//    public void update() {
//        super.update();
//        chargeHandler.tick();
//    }
//
//    @Override
//    public void writeToNBT(NBTTagCompound data) {
//        super.writeToNBT(data);
//        chargeHandler.writeToNBT(data);
//    }
//
//    @Override
//    public void readFromNBT(NBTTagCompound data) {
//        super.readFromNBT(data);
//        chargeHandler.readFromNBT(data);
//    }

}
