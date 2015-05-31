package oldcode.projectfluxgear.to_refactor.util.gui;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * A slot that is S-M-R-T, used for naming stuff in Mechanic's Utilities
 */
public class SmrtSlot extends Slot {

    /** The player that is using the GUI where this slot resides. */
    public EntityPlayer player;
    Random random = new Random();

    public SmrtSlot(EntityPlayer entPlayer, IInventory inv, int par3, int par4, int par5) {
        super(inv, par3, par4, par5);
        player = entPlayer;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid (ItemStack stack) {
        return false;
    }

    public void onPickupFromSlot (EntityPlayer entPlayer, ItemStack stack) {
        onCrafting(stack);
        super.onPickupFromSlot(entPlayer, stack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting (ItemStack stack, int par2) {
        onCrafting(stack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    protected void onCrafting (ItemStack stack) {
        //Simply naming items
        int amount = inventory.getStackInSlot(1).stackSize;
        inventory.decrStackSize(1, amount);
    }
}
