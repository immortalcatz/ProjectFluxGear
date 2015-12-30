package mortvana.legacy.dependent.seconddegree.morttech.item;

import mantle.items.abstracts.CraftingItem;
import mantle.items.iface.IDebugItem;

import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;

public class DebuggingSpork extends CraftingItem implements IDebugItem {

    public DebuggingSpork() {
        super(new String[] {"debugging_spork"}, new String[] {"debugging_spork"}, "tool/", "morttech", FluxGearContent.toolsTab);
    }
}