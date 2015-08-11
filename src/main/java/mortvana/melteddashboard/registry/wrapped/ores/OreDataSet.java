package mortvana.melteddashboard.registry.wrapped.ores;

import net.minecraft.block.Block;

import mortvana.melteddashboard.block.FluxGearItemBlock;

public class OreDataSet {

	public Block block;
	public Class<? extends FluxGearItemBlock> itemblock;
	public String name;
	public String unlocalizedName;
	public String[] names;
	public int[] rarities;
	public OreDataEntry[] entries;
	public String toolType;

	public OreDataSet(Block block, Class<? extends FluxGearItemBlock> itemblock, String name, String unlocalizedName, String[] names, int[] rarities, OreDataEntry[] entries, String toolType) {

	}

	public Block getBlock() {
		return block;
	}

	public Class<? extends FluxGearItemBlock> getItemblock() {
		return itemblock;
	}

	public String getName() {
		return name;
	}

	public String getUnlocalizedName() {
		return unlocalizedName;
	}

	public String[] getNames() {
		return names;
	}

	public int[] getRarities() {
		return rarities;
	}

	public OreDataEntry[] getEntries() {
		return entries;
	}

	public String getToolType() {
		return toolType;
	}
}
