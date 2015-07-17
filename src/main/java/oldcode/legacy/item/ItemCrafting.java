package oldcode.legacy.item;

import mantle.items.abstracts.CraftingItem;

import oldcode.legacy.common.MTContent;

public class ItemCrafting extends CraftingItem {
    public ItemCrafting() {
        super(materialNames, getTextures(), "crafting/", "morttech", MTContent.componentsTab);
    }

    private static String[] getTextures () {
        String[] names = new String[craftingTextures.length];
        for (int i = 0; i < craftingTextures.length; i++) {
            if (craftingTextures[i].equals(""))
                names[i] = "";
            else
                names[i] = craftingTextures[i];
        }
        return names;
    }

    static String[] materialNames = new String[] {"gear", "shaft", "bevel_Mechanism", "small_Grinder", "ceramic_Plate", "ceramic_Slab", "invar_Chain", "ingot_Mould", "rope", "cutting_Blade"};

    static String[] craftingTextures = new String[] {"gear", "shaft", "bevel_Mechanism", "small_Grinder", "ceramic_Plate", "ceramic_Slab", "invar_Chain", "ingot_Mould", "rope", "cutting_Blade"};
}
