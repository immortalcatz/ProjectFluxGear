package mortvana.projectfluxgear.trevelations.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import mortvana.projectfluxgear.trevelations.common.ModContent;

public class TabTRevelations extends CreativeTabs {

	public TabTRevelations(String label) {

		super(label);

	}

	@Override
	public Item getTabIconItem() {

		return ModContent.itemWardenAmulet;

	}

}
