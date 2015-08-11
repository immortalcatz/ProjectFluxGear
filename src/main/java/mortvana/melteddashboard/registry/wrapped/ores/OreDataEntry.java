package mortvana.melteddashboard.registry.wrapped.ores;

import net.minecraft.item.ItemStack;

public class OreDataEntry {

	public ItemStack oreStack;
	public int miningLevel;
	public String[] oreDict;

	public OreDataEntry(ItemStack oreStack, int miningLevel, String... oreDict) {
		this.oreStack = oreStack;
		this.miningLevel = miningLevel;
		this.oreDict = oreDict;
	}

	public ItemStack getOreStack() {
		return oreStack;
	}

	public int getMiningLevel() {
		return miningLevel;
	}

	public String[] getOreDict() {
		return oreDict;
	}
}
