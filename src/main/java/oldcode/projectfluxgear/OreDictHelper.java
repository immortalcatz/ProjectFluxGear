package oldcode.projectfluxgear;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHelper {
	public OreDictHelper() {
	}

	public ItemStack getOre(String var1) {
		return OreDictionary.getOres(var1).isEmpty()?null:ItemHelper.cloneStack((ItemStack)OreDictionary.getOres(var1).get(0), 1);
	}

	public int getOreID(ItemStack var1) {
		return OreDictionary.getOreID(var1);
	}

	public int getOreID(String var1) {
		return OreDictionary.getOreID(var1);
	}

	public String getOreName(ItemStack var1) {
		return OreDictionary.getOreName(OreDictionary.getOreID(var1));
	}

	public String getOreName(int var1) {
		return OreDictionary.getOreName(var1);
	}

	public boolean isOreIDEqual(ItemStack var1, int var2) {
		return OreDictionary.getOreID(var1) == var2;
	}

	public boolean isOreNameEqual(ItemStack var1, String var2) {
		return OreDictionary.getOreName(OreDictionary.getOreID(var1)).equals(var2);
	}

	public boolean oreNameExists(String var1) {
		return !OreDictionary.getOres(var1).isEmpty();
	}
}

