package mortvana.legacy.dependent.seconddegree.morttech.item;

import mantle.items.abstracts.CraftingItem;
import mantle.items.iface.IDebugItem;

import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;

public class DebuggingSpork extends CraftingItem implements IDebugItem {

    public DebuggingSpork() {
        super(name, texture(), "tool/", "morttech", FluxGearContent.toolsTab);
    }

    static String[] name = new String [] {"debugging_spork"};

    private static String[] texture() {
        String[] names = new String[1];
        for (int i = 0; i < 1; i++) {
            names[i] = "debugging_spork";
        }
        return names;
    }
}