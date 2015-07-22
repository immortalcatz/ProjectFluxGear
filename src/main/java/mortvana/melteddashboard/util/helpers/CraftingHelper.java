package mortvana.melteddashboard.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.event.FMLInterModComms;

import net.minecraftforge.oredict.OreDictionary;

/**
 *  A helper for REGISTERING Crafting Recipes from Minecraft or that use IMC
 *
 *  @author Mortvana
 */
public class CraftingHelper {

	// TODO: Move
	public static void registerOreDict(ItemStack itemstack, String... entries) {
		for (String entry : entries) {
			OreDictionary.registerOre(entry, itemstack);
		}
	}

	// Thermal Expansion
	public static void registerInductionSmelting(ItemStack input1, ItemStack input2, ItemStack output1, ItemStack output2, int chance, int flux) {
		NBTTagCompound nbt = new NBTTagCompound();

		if (input1 != null && output1 != null && flux > 0) {
			nbt.setTag("primaryInput", new NBTTagCompound());
			nbt.setTag("secondaryInput", new NBTTagCompound());
			nbt.setTag("primaryOutput", new NBTTagCompound());
			nbt.setTag("secondaryOutput", new NBTTagCompound());
			nbt.setInteger("secondayChance", chance);
			nbt.setInteger("energy", flux);
			input1.writeToNBT(nbt.getCompoundTag("primaryInput"));
			input2.writeToNBT(nbt.getCompoundTag("secondaryInput"));
			output1.writeToNBT(nbt.getCompoundTag("primaryOutput"));
			output2.writeToNBT(nbt.getCompoundTag("secondaryOutput"));
			FMLInterModComms.sendMessage("ThermalExpansion", "SmelterRecipe", nbt);
		}
	}

}
