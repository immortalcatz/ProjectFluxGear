package mortvana.projectfluxgear.modules.mechutil;

import mortvana.projectfluxgear.modules.mechutil.util.ActiveContainer;
import mortvana.projectfluxgear.modules.mechutil.util.SmrtSlot;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class NamingTableContainer extends ActiveContainer {

    public InventoryPlayer invPlayer;
    public NamingTableLogic logic;
    public Slot[] slots;
    public SmrtSlot smrtSlot;
    public Random random = new Random();

    public NamingTableContainer(InventoryPlayer inventoryPlayer, NamingTableLogic tableLogic) {
        initContainer(inventoryPlayer, tableLogic);
    }

    public void initContainer(InventoryPlayer inventoryPlayer, NamingTableLogic tableLogic) {
        invPlayer = inventoryPlayer;
        logic = tableLogic;
        smrtSlot = new SmrtSlot (invPlayer.player, logic, 0, 225, 38);
        this.addSlotToContainer(smrtSlot);
        slots = new Slot[] { new Slot(logic, 1, 167, 29), new Slot(logic, 2, 149, 38), new Slot(logic, 3, 167, 47) };
        for (int iter = 0; iter < 3; iter++)
            this.addSlotToContainer(slots[iter]);
        /* Player inventory */
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 9; row++) {
                this.addSlotToContainer(new Slot(invPlayer, row + column * 9 + 9, 118 + row * 18, 84 + column * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            this.addSlotToContainer(new Slot(invPlayer, column, 118 + column * 18, 142));
        }
    }

    // posX and posY must be the same length
    public void resetSlots (int[] posX, int[] posY) {
        /* Station inventory */
        inventorySlots.clear();
        inventoryItemStacks.clear();
        this.addSlotToContainer(smrtSlot);
        for (int iter = 0; iter < 3; iter++) {
            slots[iter].xDisplayPosition = posX[iter] + 111;
            slots[iter].yDisplayPosition = posY[iter] + 1;
            addSlotToContainer(slots[iter]);
        }
        /* Player inventory */
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 9; row++) {
                this.addSlotToContainer(new Slot(invPlayer, row + column * 9 + 9, 118 + row * 18, 84 + column * 18));
            }
        }
        for (int column = 0; column < 9; column++) {
            this.addSlotToContainer(new Slot(invPlayer, column, 118 + column * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith (EntityPlayer entityplayer) {
        Block block = logic.getWorldObj().getBlock(logic.xCoord, logic.yCoord, logic.zCoord);
        if (block != MechanicsUtils.woodTileEntity)
            return false;
        return logic.isUseableByPlayer(entityplayer);
    }

    @Override
    public ItemStack transferStackInSlot (EntityPlayer player, int slotID) {
        ItemStack stack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotID);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();
            if (slotID < logic.getSizeInventory() && ((slotID == 0 && !mergeCraftedStack(slotStack, logic.getSizeInventory(), this.inventorySlots.size(), true, player)) || (!this.mergeItemStack(slotStack, logic.getSizeInventory(), this.inventorySlots.size(), true)) || (!this.mergeItemStack(slotStack, 1, logic.getSizeInventory(), false)))) {
                return null;
            }
            if (slotStack.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }
        }
        return stack;
    }

    protected void craftTool (ItemStack stack) {
        int amount = logic.getStackInSlot(1).stackSize;
        logic.decrStackSize(1, amount);
    }


    protected boolean mergeCraftedStack (ItemStack stack, int slotsStart, int slotsTotal, boolean playerInventory, EntityPlayer player) {
        boolean failedToMerge = false;
        int slotIndex = slotsStart;
        if (playerInventory) {
            slotIndex = slotsTotal - 1;
        }
        Slot otherInventorySlot;
        ItemStack copyStack = null;
        if (stack.stackSize > 0) {
            if (playerInventory) {
                slotIndex = slotsTotal - 1;
            } else {
                slotIndex = slotsStart;
            }
            while (!playerInventory && slotIndex < slotsTotal || playerInventory && slotIndex >= slotsStart) {
                otherInventorySlot = (Slot) this.inventorySlots.get(slotIndex);
                copyStack = otherInventorySlot.getStack();
                if (copyStack == null) {
                    craftTool(stack);
                    otherInventorySlot.putStack(stack.copy());
                    otherInventorySlot.onSlotChanged();
                    stack.stackSize = 0;
                    failedToMerge = true;
                    break;
                }
                if (playerInventory) {
                    --slotIndex;
                } else {
                    ++slotIndex;
                }
            }
        }
        return failedToMerge;
    }
}
