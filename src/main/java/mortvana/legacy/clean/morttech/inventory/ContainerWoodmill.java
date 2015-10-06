package mortvana.legacy.clean.morttech.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.clean.morttech.block.tileentity.WoodmillLogic;
import mortvana.legacy.errored.morttech.recipe.WoodmillRecipes;

public class ContainerWoodmill extends Container {
    private WoodmillLogic woodmill;
    private int progressTime;
    private int powerTime;
    private int powerGaugeTime;

    public ContainerWoodmill(InventoryPlayer inventoryPlayer, WoodmillLogic TileEntityWoodmill) {
        woodmill = TileEntityWoodmill;
        addSlotToContainer(new Slot(TileEntityWoodmill, 0, 56, 17));
        addSlotToContainer(new SlotFurnace(inventoryPlayer.player, TileEntityWoodmill, 1, 56, 53));
        addSlotToContainer(new SlotFurnace(inventoryPlayer.player, TileEntityWoodmill, 2, 116, 53));
        addSlotToContainer(new SlotFurnace(inventoryPlayer.player, TileEntityWoodmill, 3, 116, 35));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters (ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, woodmill.progress);
        par1ICrafting.sendProgressBarUpdate(this, 1, woodmill.power);
        par1ICrafting.sendProgressBarUpdate(this, 2, woodmill.powerGauge);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges () {
        super.detectAndSendChanges();

        for (int i = 0; i < crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) crafters.get(i);

            if (progressTime != woodmill.progress) {
                icrafting.sendProgressBarUpdate(this, 0, woodmill.progress);
            }

            if (powerTime != woodmill.power) {
                icrafting.sendProgressBarUpdate(this, 1, woodmill.power);
            }

            if (powerGaugeTime != woodmill.powerGauge) {
                icrafting.sendProgressBarUpdate(this, 2, woodmill.powerGauge);
            }
        }

        progressTime = woodmill.progress;
        powerTime = woodmill.power;
        powerGaugeTime = woodmill.powerGauge;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar (int par1, int par2) {
        if (par1 == 0) {
            woodmill.progress = par2;
        }

        if (par1 == 1) {
            woodmill.power = par2;
        }

        if (par1 == 2) {
            woodmill.powerGauge = par2;
        }
    }

    @Override
    public boolean canInteractWith (EntityPlayer par1EntityPlayer) {
        return woodmill.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or
     * you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot (EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (par2 != 1 && par2 != 0) {
                if (WoodmillRecipes.cutting().getCuttingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityFurnace.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (par2 >= 3 && par2 < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}

	
