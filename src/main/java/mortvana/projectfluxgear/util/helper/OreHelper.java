package mortvana.projectfluxgear.util.helper;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class OreHelper {
	public static class BasicOreEntry {

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

	public static class OreBlockEntry {
		public static Block block;
		public static Class<? extends ItemBlock> itemblock;
		public static String name;
		public static String toolType;

		public OreBlockEntry(Block block, Class<? extends ItemBlock> itemblock, String name, String toolType) {
			this.block = block;
			this.itemblock = itemblock;
			this.name = name;
			this.toolType = toolType;
		}
	}

	public static class OrePair {
		public BasicOreEntry[] ores;
		public OreBlockEntry block;

		public OrePair (OreBlockEntry block, BasicOreEntry[] ores) {
			this.block = block;
			this.ores = ores;
		}
	}
}
