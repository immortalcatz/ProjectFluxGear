package mortvana.melteddashboard.api.block.tile;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public interface IHasInventory extends ISidedInventory {

	/**
	 *  Returns if the inventory was modified during this tick.
	 */
	public boolean hasInventoryBeenModified();

	/**
	 *  Returns if the slot is a real slot, or a fake (holograpic) slot.
	 */
	public boolean isRealSlot(int slot);

	/**
	 *  Tries to add a stack to the Slot. Ignores whether it is a real slot or not.
	 *
	 *  Generally you return true if stack == null or the stack has been added, false if the slot is out of bounds,
	 *  the slot is fake, or stack can't be added
	 */
	public boolean addStackToSlot(int slot, ItemStack stack);
}
