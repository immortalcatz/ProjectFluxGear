package mortvana.legacy.clean.thaumicrevelations.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.intermod.thaumcraft.inventory.SlotEssentia;

import mortvana.melteddashboard.api.item.IWardenicEquipment;

import mortvana.legacy.clean.thaumicrevelations.util.WardenicChargeHelper;
import thaumcraft.api.aspects.IEssentiaContainerItem;

public class ContainerHammer extends Container {

	public InventoryPlayer playerInv;
	public InventoryCrafting hammerInv;
	public IInventory resultInv;

	public ContainerHammer(EntityPlayer player) {
		playerInv = player.inventory;
		hammerInv = new InventoryCrafting(this, 2, 1);
		resultInv = new InventoryCraftResult();

		for (int hotbar = 0; hotbar < 9; hotbar++) {
			addSlotToContainer(new Slot(playerInv, hotbar, 8 + 18 * hotbar, 142));
		}
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlotToContainer(new Slot(playerInv, 9 + row * 9 + column, 8 + 18 * column, 84 + row * 18));
			}
		}

		addSlotToContainer(new SlotEssentia(hammerInv, 0, 80, 54));
		addSlotToContainer(new Slot(hammerInv, 1, 80, 33));
		addSlotToContainer(new SlotCrafting(player, hammerInv, resultInv, 0, 80, 12));
		onCraftMatrixChanged(hammerInv);
	}

	@Override
	public void onCraftMatrixChanged(IInventory craftingMatrix) {
		ItemStack essentia = craftingMatrix.getStackInSlot(0);
		ItemStack item = craftingMatrix.getStackInSlot(1);

		if (item != null) {
			if (!(item.getItem() instanceof IWardenicEquipment)) {
				ItemStack repairedItem = new ItemStack(item.getItem());
				if (item.getMetadata() != 0 && item.getItem().isRepairable()) {
					repairedItem.setMetadata(0);
					resultInv.setInventorySlotContents(0, repairedItem);
				}
			} else if (essentia != null) {
				ItemStack infusedArmor = new ItemStack(item.getItem());
				String aspectKey = ((IEssentiaContainerItem) essentia.getItem()).getAspects(essentia).getAspects()[0].getName();
				if (WardenicChargeHelper.upgrades.containsKey(aspectKey)) {
					WardenicChargeHelper.setUpgradeOnStack(infusedArmor, aspectKey);
				}
				resultInv.setInventorySlotContents(0, infusedArmor);
			} else {
				resultInv.setInventorySlotContents(0, null);
			}
		} else {
			resultInv.setInventorySlotContents(0, null);
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		ItemStack essentia = hammerInv.getStackInSlotOnClosing(0);
		if (essentia != null) {
			player.dropPlayerItemWithRandomChoice(essentia, false);
		}
		ItemStack item = hammerInv.getStackInSlotOnClosing(1);
		if (item != null) {
			player.dropPlayerItemWithRandomChoice(item, false);
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}

	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
			return null;
		}
		return super.slotClick(slot, button, flag, player);
	}

}