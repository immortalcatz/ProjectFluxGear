package mortvana.projectfluxgear.block.logic;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import mantle.blocks.abstracts.InventoryLogic;

import mortvana.projectfluxgear.block.tile.NamingTableContainer;

public class NamingTableLogic extends InventoryLogic implements ISidedInventory {
    public ItemStack previousTool;
    public String toolName;

    public NamingTableLogic() {
        super(2);
        toolName = "";
    }

    public NamingTableLogic(int slots) {
        super(slots);
        toolName = "";
    }

    @Override
    public boolean canDropInventorySlot (int slot) {
        if (slot == 0)
            return false;
        return true;
    }

    @Override
    public ItemStack getStackInSlotOnClosing (int var1) {
        return null;
    }

    @Override
    public String getDefaultName () {
        return "mechutil.namingTable";
    }

    @Override
    public Container getGuiContainer (InventoryPlayer inventoryplayer, World world, int x, int y, int z) {
        return new NamingTableContainer(inventoryplayer, this);
    }

    @Override
    public void markDirty () {
        nameItem(toolName);
        if (this.worldObj != null) {
            this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.markTileEntityChunkModified(this.xCoord, this.yCoord, this.zCoord, this);
        }
    }

    public void nameItem (String name) {
        ItemStack output = null;
        if (inventory[1] != null && !name.equals("")) {
            output = tryRenameTool(output, name);
        }
        inventory[0] = output;
    }

    public void setToolname (String name) {
        toolName = name;
        nameItem(name);
    }

    protected ItemStack tryRenameTool (ItemStack output, String name) {
        ItemStack temp;
        if (output != null)
            temp = output;
        else
            temp = inventory[1].copy();
        if (temp == null)
            return null; // output as well as inventory is null :(
        NBTTagCompound tags = temp.getTagCompound();
        if (tags == null)
        {
            tags = new NBTTagCompound();
            temp.setTagCompound(tags);
        }
        NBTTagCompound display = null;
        if (!(tags.hasKey("display")))
            display = new NBTTagCompound();
        else if (tags.getCompoundTag("display").hasKey("Name"))
            display = tags.getCompoundTag("display");
        if (display == null)
            return output;
        if (display.hasKey("Name"))
    // no default name anymore
            return output;
        String dName = name;
        display.setString("Name", dName);
        tags.setTag("display", display);
        temp.setRepairCost(2);
        output = temp;
        return output;
    }

    @Override
    public boolean canUpdate () {
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide (int side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem (int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public boolean canExtractItem (int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public String getInventoryName () {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName () {
        return false;
    }

    @Override
    public void openInventory () {}

    @Override
    public void closeInventory () {}
}
