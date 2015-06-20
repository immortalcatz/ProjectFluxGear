package oldcode.projectfluxgear;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

@Deprecated
public class ActiveSlot extends Slot {
    public boolean active;
    public int activeSlotNumber;

    public ActiveSlot(IInventory inv, int par2, int par3, int par4, boolean flag) {
        super(inv, par2, par3, par4);
        active = flag;
    }

    public void setActive (boolean flag) {
        active = flag;
    }

}
