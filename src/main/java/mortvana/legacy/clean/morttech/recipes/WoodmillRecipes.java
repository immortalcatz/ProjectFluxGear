package mortvana.legacy.clean.morttech.recipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class WoodmillRecipes {
    private static final WoodmillRecipes cuttingBase = new WoodmillRecipes();

    /** The list of smelting results. */
    private HashMap<ItemStack, ItemStack> cuttingList = new HashMap<ItemStack, ItemStack>();
	private HashMap<ItemStack, Float> metaExperience = new HashMap<ItemStack, Float>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final WoodmillRecipes cutting() {
        return cuttingBase;
    }

    public WoodmillRecipes() {
        addAllLogs();
        addCutting(new ItemStack(Blocks.log, 1, 0), new ItemStack(Blocks.planks, 4, 0));
        addCutting(new ItemStack(Blocks.log, 1, 1), new ItemStack(Blocks.planks, 4, 1));
        addCutting(new ItemStack(Blocks.log, 1, 2), new ItemStack(Blocks.planks, 4, 2));
        addCutting(new ItemStack(Blocks.log, 1, 3), new ItemStack(Blocks.planks, 4, 3));
        addCutting(new ItemStack(Blocks.log2, 1, 0), new ItemStack(Blocks.planks, 4, 4));
        addCutting(new ItemStack(Blocks.log2, 1, 1), new ItemStack(Blocks.planks, 4, 5));
    }

    public void addAllLogs() {
        Container tempContainer = new Container() {
            public boolean canInteractWith(EntityPlayer player) {
                return false;
            }
        };
        InventoryCrafting tempCrafting = new InventoryCrafting(tempContainer, 3, 3);
        ArrayList recipeList = (ArrayList) CraftingManager.getInstance().getRecipeList();
        for (int i = 1; i < 9; i++) {
            tempCrafting.setInventorySlotContents(i, null);
        }
        ArrayList<ItemStack> registeredOres = OreDictionary.getOres("logWood");
	    for (ItemStack logEntry : registeredOres) {
		    if (logEntry.getMetadata() == OreDictionary.WILDCARD_VALUE) {
			    for (int j = 0; j < 256; j++) {
				    ItemStack log = new ItemStack(logEntry.getItem(), 1, j);
				    tempCrafting.setInventorySlotContents(0, log);
				    ItemStack resultEntry = CraftingManager.getInstance().findMatchingRecipe(tempCrafting, null);
				    if (resultEntry != null) {
					    ItemStack result = resultEntry.copy();
					    result.stackSize = ((int) (result.stackSize * 1.5F));
					    addCutting(log, result/*, 0.15f*/);
				    }
			    }
		    } else {
			    ItemStack log = ItemStack.copyItemStack(logEntry);
			    tempCrafting.setInventorySlotContents(0, log);
			    ItemStack resultEntry = CraftingManager.getInstance().findMatchingRecipe(tempCrafting, null);
			    if (resultEntry != null) {
				    ItemStack result = resultEntry.copy();
				    result.stackSize = ((int) (result.stackSize * 1.5F));
				    addCutting(log, result/*, 0.15f*/);
			    }
		    }
	    }
    }

    /**
     * A metadata sensitive version of adding a furnace recipe.
     */
    public void addCutting(ItemStack item, ItemStack itemstack) {
	    cuttingList.put(item, itemstack);
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getCuttingResult(ItemStack item) {
        if (item == null) {
            return null;
        }
        ItemStack ret = cuttingList.get(item);
        if (ret != null) {
            return ret;
        }
        return null;
    }

    public Map<ItemStack, ItemStack> getCuttingList(){
        return cuttingList;
    }
	/**
	 * Used to get the resulting ItemStack form a source ItemStack
	 * @param item The Source ItemStack
	 * @return The result ItemStack
	 */
	public ItemStack getSmeltingResult(ItemStack item) {
		if (item == null) {
			return null;
		}
		ItemStack ret = cuttingList.get(item);
		if (ret != null) {
			return ret;
		}
		return cuttingList.get(item);
	}

}
