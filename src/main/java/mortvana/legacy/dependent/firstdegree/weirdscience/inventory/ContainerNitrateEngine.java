package mortvana.legacy.dependent.firstdegree.weirdscience.inventory;

import mortvana.legacy.errored.weirdscience.TileEntityNitrateDynamo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@Deprecated
public class ContainerNitrateEngine extends Container {

	protected TileEntityNitrateDynamo tileEntity;

	public ContainerNitrateEngine(InventoryPlayer inventoryPlayer, TileEntityNitrateDynamo te) {
		tileEntity = te;

		addSlotToContainer(new Slot(te, 0, 80, 17)); // Add the fuel slot.
										// (slot 0)
		addSlotToContainer(new Slot(te, 1, 80, 53)); // Slot 1 is output.

		// Display the player's inventory.
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventoryPlayer,
						j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18,
					142));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
		Slot slot = (Slot) this.inventorySlots.get(slotID);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			ItemStack itemstack = itemstack1.copy();

			if (slotID == 0) {
				// mergeItemStack takes the item stack to attempt to merge, and
				// the slots on this object to attempt to merge it into. Bool at
				// the end is "reverse order or no?"
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (slotID == 1) {
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
			return itemstack;
		} else {
			return null;
		}

	}
}
