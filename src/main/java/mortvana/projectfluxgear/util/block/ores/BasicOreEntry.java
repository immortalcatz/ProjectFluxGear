package mortvana.projectfluxgear.util.block.ores;

import net.minecraft.item.ItemStack;

public class BasicOreEntry {

	public static ItemStack itemstack;
	public static int harvestLevel;
	public static String oreDict;

	public BasicOreEntry(ItemStack itemstack, int harvestLevel, String oreDict) {
		this.itemstack = itemstack;
		this.harvestLevel = harvestLevel;
		this.oreDict = oreDict;
	}
}
