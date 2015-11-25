package mortvana.melteddashboard.registry.wrapped;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.oredict.OreDictionary;

import mortvana.melteddashboard.block.FluxGearItemBlock;
import mortvana.melteddashboard.registry.wrapped.ores.OreDataEntry;
import mortvana.melteddashboard.registry.wrapped.ores.OreDataSet;

public class RegistationWrapper {
	public static Block registerBlock(Block block, Class<? extends FluxGearItemBlock> itemblock, String name, String[] names, int[] rarities, String unlocalizedName) {
		return GameRegistry.registerBlock(block, itemblock, name, names, rarities, unlocalizedName);
	}

	public static void registerOres(OreDataSet... oreSets) {
		for (OreDataSet data : oreSets) {
			registerBlock(data.block, data.itemblock, data.name, data.names, data.rarities, data.unlocalizedName);
			for (int i = 0; i < data.entries.length; i++) {
				OreDataEntry entry = data.entries[i];
				data.block.setHarvestLevel(data.toolType, entry.miningLevel, i);
				registerWithHandlers(entry.oreStack, data.names[i], entry.oreDict);
			}
		}
	}

	public static void registerWithHandlers(ItemStack itemstack, String name, String... oreDict) {
		GameRegistry.registerCustomItemStack(name, itemstack);
		registerFMP(itemstack);
		registerOreDict(itemstack, oreDict);
	}

	public static void registerFMP(Block block, int amount) {
		for (int i = 0; i < amount; i++) {
			registerFMP(new ItemStack(block, 1, i));
		}
	}

	public static void registerFMP(ItemStack itemstack) {
		FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", itemstack);
	}

	public static void registerOreDict(ItemStack itemstack, String... oreDict) {
		for (String name : oreDict) {
			OreDictionary.registerOre(name, itemstack);
		}
	}

	public static void addSmelting(ItemStack input, ItemStack output, float experience) {
		FurnaceRecipes.instance().addSmeltingRecipe(input, output, experience);
	}

	public static void registerSquareRecipe(ItemStack input, ItemStack output) {
		GameRegistry.addRecipe(output, "##", "##", '#', input);
	}
}
