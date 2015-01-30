package mortvana.fluxgearcore.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ActiveContainer extends Container{

    public List<ActiveSlot> activeSlots = new ArrayList<ActiveSlot>();


    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return false;
    }

    protected ActiveSlot addDualSlotToContainer (ActiveSlot slot) {
        slot.activeSlotNumber = activeSlots.size();
        activeSlots.add(slot);
        addSlotToContainer(slot);
        return slot;
    }
}
