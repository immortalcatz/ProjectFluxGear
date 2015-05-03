package mortvana.projectfluxgear.util.block.ores;

import net.minecraft.item.ItemStack;

public class BasicOreEntry {

	public static ItemStack itemstack;
	public static int harvestLevel;
	public static String oreDict;
	public static String oreDict2;

	public BasicOreEntry(ItemStack itemstack, int harvestLevel, String oreDict, String oreDict2) {
		this.itemstack = itemstack;
		this.harvestLevel = harvestLevel;
		this.oreDict = oreDict;
		this.oreDict2 = oreDict2;
	}

	public BasicOreEntry(ItemStack itemstack, int harvestLevel, String oreDict) {
		this(itemstack, harvestLevel, oreDict, null);
	}
}
