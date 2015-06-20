package oldcode.projectfluxgear;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import mantle.debug.DebugData;
import mantle.debug.IDebuggable;

public abstract class InventoryLogic extends TileEntity implements IInventory, IDebuggable {
	protected ItemStack[] inventory;
	protected String invName;
	protected int stackSizeLimit;

	public InventoryLogic(int invSize) {
		this(invSize, 64);
	}

	public InventoryLogic(int invSize, int maxStackSize) {
		inventory = new ItemStack[invSize];
		stackSizeLimit = maxStackSize;
	}

    /* Inventory management */

	@Override
	public ItemStack getStackInSlot (int slot) {
		return inventory[slot];
	}

	public boolean isStackInSlot (int slot) {
		return inventory[slot] != null;
	}

	@Override
	public int getSizeInventory () {
		return inventory.length;
	}

	@Override
	public int getInventoryStackLimit () {
		return stackSizeLimit;
	}

	public boolean canDropInventorySlot (int slot) {
		return true;
	}

	@Override
	public void setInventorySlotContents (int slot, ItemStack itemstack) {
		inventory[slot] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public ItemStack decrStackSize (int slot, int quantity) {
		if (inventory[slot] != null) {
			if (inventory[slot].stackSize <= quantity) {
				ItemStack stack = inventory[slot];
				inventory[slot] = null;
				return stack;
			}
			ItemStack split = inventory[slot].splitStack(quantity);
			if (inventory[slot].stackSize == 0) {
				inventory[slot] = null;
			}
			return split;
		} else {
			return null;
		}
	}

	/* Supporting methods */
	@Override
	public boolean isUseableByPlayer (EntityPlayer entityplayer) {
		if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this)
			return false;

		else
			return entityplayer.getDistance((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;

	}

	public abstract Container getGuiContainer (InventoryPlayer inventoryplayer, World world, int x, int y, int z);

	/* NBT */
	@Override
	public void readFromNBT (NBTTagCompound tags) {
		super.readFromNBT(tags);
		readInventoryFromNBT(tags);
	}

	public void readInventoryFromNBT (NBTTagCompound tags) {
		super.readFromNBT(tags);
		NBTTagList nbttaglist = tags.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];

		if (tags.hasKey("CustomName", 8)) {
			this.invName = tags.getString("CustomName");
		}

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.inventory.length) {
				this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT (NBTTagCompound tags) {
		super.writeToNBT(tags);
		writeInventoryToNBT(tags);
	}

	public void writeInventoryToNBT (NBTTagCompound tags) {
		super.writeToNBT(tags);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tags.setTag("Items", nbttaglist);

		if (this.isInvNameLocalized()) {
			tags.setString("CustomName", this.invName);
		}

	}

	/* Default implementations of hardly used methods */
	public ItemStack getStackInSlotOnClosing (int slot) {
		return null;
	}

	public void openChest () {}

	public void closeChest () {}

	protected abstract String getDefaultName ();

	public void setInvName (String name) {
		this.invName = name;
	}

	public String getInvName () {
		return this.isInvNameLocalized() ? this.invName : getDefaultName();
	}

	public boolean hasCustomInventoryName() {
		return isInvNameLocalized();
	}

	public boolean isInvNameLocalized () {
		return this.invName != null && this.invName.length() > 0;
	}

	@Override
	public boolean isItemValidForSlot (int slot, ItemStack itemstack) {
		if ((slot < getSizeInventory()) || (inventory[slot] == null || itemstack.stackSize + inventory[slot].stackSize <= getInventoryStackLimit())) {
				return true;
		}
		return false;
	}

	public void placeBlock (EntityLivingBase entity, ItemStack stack) {}

	public void removeBlock () {}

	/* IDebuggable */
	@Override
	public DebugData getDebugInfo (EntityPlayer player) {
		String[] strs = new String[1];
		strs[0] = "invName: " + invName + ", inventory.length: " + inventory.length + ", stackSizeLimit: " + stackSizeLimit;
		return new DebugData(player, getClass(), strs);
	}
}
