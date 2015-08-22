package mortvana.legacy.crystaltweaks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CrystalRecipes {
	public static void CreateRecipes() {
		GameRegistry.addRecipe(new ItemStack(CrystalContent.essenceExtractor, 1, 0), " b ", "eme", "mmm", 'b', Items.book, 'e', Items.emerald, 'm', Blocks.end_stone);
		GameRegistry.addRecipe(new ItemStack(CrystalContent.sugarBlock, 1, 7), "###", "###", "###", '#', Items.sugar);
		GameRegistry.addRecipe(new ItemStack(CrystalContent.aggregator, 1, 0), "#d#", "#r#", "#e#", '#', Items.quartz, 'd', Items.diamond, 'e', Items.ender_pearl, 'r', Blocks.redstone_block);
		GameRegistry.addRecipe(new ItemStack(CrystalContent.aggregator, 1, 0), "#e#", "#r#", "#d#", '#', Items.quartz, 'd', Items.diamond, 'e', Items.ender_pearl, 'r', Blocks.redstone_block);
	}
}
