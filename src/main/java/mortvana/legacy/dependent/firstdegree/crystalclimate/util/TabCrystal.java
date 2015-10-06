package mortvana.legacy.dependent.firstdegree.crystalclimate.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import mortvana.legacy.errored.crystalclimate.common.CrystalClimate;

public class TabCrystal extends CreativeTabs {

	public TabCrystal(String label) {
		super(label);
	}

	@Override
	public ItemStack getIconItemStack() {
		return CrystalClimate.essenceStack;
	}

	@Override
	public Item getTabIconItem() {
		return CrystalClimate.essenceCrystal;
	}
}