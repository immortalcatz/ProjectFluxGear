package mortvana.legacy.errored.thaumicrevelations.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import thaumcraft.common.tiles.TileOwned;

public class TileWardChest extends TileOwned implements ISidedInventory {
	private static final String TAG_CUSTOM_NAME = "customName";
	private ItemStack[] inventorySlots = new ItemStack[54];
	public String customName;
	public double ticksExisted;

	public boolean canUpdate() {
		return true;
	}

	public void updateEntity() {
		super.updateEntity();
		ticksExisted += 1.0D;
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		customName = nbt.getString("customName");
		NBTTagList items = nbt.getTagList("Items");
		inventorySlots = new ItemStack[getSizeInventory()];
		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound nbtTag = items.getCompoundTagAt(i);
			byte slot = nbtTag.getByte("Slot");
			if (slot >= 0 && slot < inventorySlots.length) {
				inventorySlots[slot] = ItemStack.loadItemStackFromNBT(nbtTag);
			}
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("customName", customName == null ? "" : customName);
		NBTTagList nbtList = new NBTTagList();
		for (int i = 0; i < inventorySlots.length; i++) {
			if (inventorySlots[i] != null) {
				NBTTagCompound nbtTag = new NBTTagCompound();
				nbtTag.setByte("Slot", (byte) i);
				inventorySlots[i].writeToNBT(nbtTag);
				nbtList.appendTag(nbtTag);
			}
		}
		nbt.setTag("Items", nbtList);
	}

	public int getSizeInventory() {
		return inventorySlots.length;
	}

	public ItemStack getStackInSlot(int i) {
		if (i >= inventorySlots.length) {
			return null;
		}
		return inventorySlots[i];
	}

	public ItemStack decrStackSize(int par1, int par2) {
		if (inventorySlots[par1] != null) {
			if (inventorySlots[par1].stackSize <= par2) {
				ItemStack stackAt = inventorySlots[par1];
				inventorySlots[par1] = null;
				return stackAt;
			}
			ItemStack stackAt = inventorySlots[par1].splitStack(par2);
			if (inventorySlots[par1].stackSize == 0) {
				inventorySlots[par1] = null;
			}
			return stackAt;
		}
		return null;
	}

	public ItemStack getStackInSlotOnClosing(int i) {
		return getStackInSlot(i);
	}

	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inventorySlots[i] = itemstack;
	}

	public String getInventoryName() {
		return ModBlocks.wardChest.func_71917_a() + ".name";
	}

	public boolean hasCustomInventoryName() {
		return customName != null && customName.length() > 0;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this;
	}

	public void openChest() {}

	public void closeChest() {}

	public boolean receiveClientEvent(int par1, int par2) {
		return tileEntityInvalid;
	}

	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	public int[] getAccessibleSlotsFromSide(int var1) {
		return new int[0];
	}

	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		return false;
	}

	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return false;
	}
}