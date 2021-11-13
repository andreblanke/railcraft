package mods.railcraft.world.inventory;

import mods.railcraft.world.level.block.entity.ItemManipulatorBlockEntity;
import net.minecraft.entity.player.PlayerInventory;

public class ItemManipulatorMenu extends ManipulatorMenu<ItemManipulatorBlockEntity> {

  public ItemManipulatorMenu(int id, PlayerInventory inventory,
      ItemManipulatorBlockEntity manipulator) {
    super(RailcraftMenuTypes.ITEM_MANIPULATOR.get(), id, inventory, manipulator);
  }

  @Override
  protected void addSlots(ItemManipulatorBlockEntity manipulator) {
    for (int i = 0; i < 3; i++) {
      for (int k = 0; k < 3; k++) {
        this.addSlot(new SlotRailcraft(
            manipulator.getItemFilters(), k + i * 3, 8 + k * 18, 26 + i * 18).setPhantom());
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int k = 0; k < 3; k++) {
        this.addSlot(manipulator.getBufferSlot(k + i * 3, 116 + k * 18, 26 + i * 18));
      }
    }
  }
}
