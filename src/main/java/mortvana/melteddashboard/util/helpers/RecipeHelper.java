package mortvana.melteddashboard.util.helpers;

import cpw.mods.fml.common.registry.GameRegistry;
import mortvana.melteddashboard.util.helpers.imc.CoFHIMCHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

/**
 *  A class for delegating registration of crafting recipes to their respective handlers.
 *
 *  @author Mortvana
 */

public class RecipeHelper {

    public static void addRecipe(IRecipe recipe) {
        GameRegistry.addRecipe(recipe);
    }

    public static void addInductionSmelterRecipe(ItemStack input1, ItemStack input2, ItemStack output1, int flux) {
        CoFHIMCHelper.registerInductionSmelting(input1, input2, output1, null, 0, flux);
    }

    public static void addInductionSmelterRecipe(ItemStack input1, ItemStack input2, ItemStack output1, ItemStack output2, int chance, int flux) {
        CoFHIMCHelper.registerInductionSmelting(input1, input2, output1, output2, chance, flux);
    }
}
