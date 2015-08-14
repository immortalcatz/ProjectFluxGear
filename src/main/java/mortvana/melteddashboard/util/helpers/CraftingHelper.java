package mortvana.melteddashboard.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.event.FMLInterModComms;

import net.minecraftforge.oredict.OreDictionary;

/**
 *  A helper for REGISTERING Crafting Recipes from Minecraft or Forge
 *
 *  @author Mortvana
 */
@Deprecated
public class CraftingHelper {

	// TODO: Move
	public static void registerOreDict(ItemStack itemstack, String... entries) {
		for (String entry : entries) {
			OreDictionary.registerOre(entry, itemstack);
		}
	}
}
