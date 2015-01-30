package mortvana.fluxgearcore.util.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class TweakHelper {

	private static ArrayList<ItemStack> recipesToRemove = new ArrayList<ItemStack>();
	private static HashMap<ItemStack, HashMap<Integer, String[]>> removalReasons = new HashMap<ItemStack, HashMap<Integer, String[]>>();

	public enum TweakReason {
		REMOVED("Removed:"), CHANGED("Recipe Changed:"), ADDED("Added:"), NOTE("Note: ");
		private String name;
		TweakReason(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}

	/**
	 * The main way of removing recipes
	 *
	 * @param itemstack - ItemStack to remove all recipes for
	 */
	public static void markItemForRecipeRemoval(ItemStack itemstack) {
		recipesToRemove.add(itemstack);
	}

	/**
	 * Adds an item to the list of items to remove recipes for, with a reason.
	 *
	 * @param itemstack - ItemStack to remove all recipes for
	 * @param action - {@link TweakReason} for the type of change being made
	 * @param details - String... of details to add to the tooltip
	 */
	public static void markItemForRecipeRemoval(ItemStack itemstack, TweakReason action, String... details) {
		recipesToRemove.add(itemstack);
	}

	public static void removeRecipes() {
		ListIterator<IRecipe> iterator = CraftingManager.getInstance().getRecipeList().listIterator();
		while (iterator.hasNext()) {
			IRecipe r = iterator.next();
			if (canRemoveRecipe(r)) {
				iterator.remove();
			}
		}
		recipesToRemove.clear();
	}

	static boolean canRemoveRecipe(IRecipe r) {
		try {
			ItemStack output = r.getRecipeOutput();
			if (output == null || output.getItem() == null)
				return false;

			for (ItemStack recipe : recipesToRemove) {
				if (recipe.isItemEqual(output)) {
					return true;
				}
			}
			return false;

		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

}