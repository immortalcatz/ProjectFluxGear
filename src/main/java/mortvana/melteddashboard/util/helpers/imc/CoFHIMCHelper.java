package mortvana.melteddashboard.util.helpers.imc;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.event.FMLInterModComms;

public class CoFHIMCHelper {
	// Thermal Expansion IMC
	public static void registerInductionSmelting(ItemStack input1, ItemStack input2, ItemStack output1, int flux) {
		registerInductionSmelting(input1, input2, output1, null, 0, flux);
	}

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
