package mortvana.legacy.crystaltweaks.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import mortvana.legacy.crystaltweaks.CrystalContent;

public class TabCrystal extends CreativeTabs {

	public TabCrystal(String label) {
		super(label);
	}

	@Override
	public ItemStack getIconItemStack() {
		return CrystalContent.essenceStack;
	}

	@Override
	public Item getTabIconItem() {
		return CrystalContent.essenceCrystal;
	}
}