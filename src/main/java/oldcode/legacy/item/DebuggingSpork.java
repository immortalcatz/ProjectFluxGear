package oldcode.legacy.item;

import mantle.items.abstracts.CraftingItem;
import mantle.items.iface.IDebugItem;

import oldcode.legacy.common.MTContent;

public class DebuggingSpork extends CraftingItem implements IDebugItem {

    public DebuggingSpork() {
        super(name, texture(), "tool/", "morttech", MTContent.toolsTab);
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