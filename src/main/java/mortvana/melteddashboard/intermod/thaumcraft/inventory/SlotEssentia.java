package mortvana.melteddashboard.intermod.thaumcraft.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.IEssentiaContainerItem;

public class SlotEssentia extends Slot {

	public SlotEssentia(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IEssentiaContainerItem;
	}
}

