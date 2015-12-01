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

	@Override
	public boolean canUpdate() {
		return true;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		ticksExisted += 1.0D;
	}

	@Override
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

	@Override
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

	@Override
	public int getSizeInventory() {
		return inventorySlots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i >= inventorySlots.length) {
			return null;
		}
		return inventorySlots[i];
	}

	@Override
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

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return getStackInSlot(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inventorySlots[i] = itemstack;
	}

	@Override
	public String getInventoryName() {
		return ModBlocks.wardChest.getUnlocalizedName() + ".name";
	}

	@Override
	public boolean isCustomInventoryName() {
		return customName != null && customName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean receiveClientEvent(int par1, int par2) {
		return tileEntityInvalid;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public int[] getSlotsForFace(int var1) {
		return new int[0];
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return false;
	}
}