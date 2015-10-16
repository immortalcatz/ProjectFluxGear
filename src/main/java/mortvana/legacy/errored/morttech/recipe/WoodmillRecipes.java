package mortvana.legacy.errored.morttech.recipe;

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
    private Map smeltingList = new HashMap();
	private Map experienceList = new HashMap();
    private HashMap<List<Object>, ItemStack> cuttingList = new HashMap<List<Object>, ItemStack>();
	private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final WoodmillRecipes cutting() {
        return cuttingBase;
    }

    public WoodmillRecipes() {
        addAllLogs();
        this.addCutting(new ItemStack(Blocks.log, 1, 0), new ItemStack(Blocks.planks, 4, 0));
        this.addCutting(new ItemStack(Blocks.log, 1, 1), new ItemStack(Blocks.planks, 4, 1));
        this.addCutting(new ItemStack(Blocks.log, 1, 2), new ItemStack(Blocks.planks, 4, 2));
        this.addCutting(new ItemStack(Blocks.log, 1, 3), new ItemStack(Blocks.planks, 4, 3));
        this.addCutting(new ItemStack(Blocks.log2, 1, 0), new ItemStack(Blocks.planks, 4, 4));
        this.addCutting(new ItemStack(Blocks.log2, 1, 1), new ItemStack(Blocks.planks, 4, 5));
    }

    public void addAllLogs() {
        Container tempContainer = new Container()
        {
            public boolean canInteractWith(EntityPlayer player)
            {
                return false;
            }
        };
        InventoryCrafting tempCrafting = new InventoryCrafting(tempContainer, 3, 3);
        ArrayList recipeList = (ArrayList) CraftingManager.getInstance().getRecipeList();
        for (int i = 1; i < 9; i++) {
            tempCrafting.setInventorySlotContents(i, null);
        }
        ArrayList<ItemStack> registeredOres = OreDictionary.getOres("logWood");
        for (int i = 0; i < registeredOres.size(); i++)
        {
            ItemStack logEntry = (ItemStack)registeredOres.get(i);
            if (logEntry.getItemDamage() == OreDictionary.WILDCARD_VALUE)
            {
                for (int j = 0; j < 256; j++)
                {
                    ItemStack log = new ItemStack(logEntry.getItem(), 1, j);
                    tempCrafting.setInventorySlotContents(0, log);
                    ItemStack resultEntry = CraftingManager.getInstance().findMatchingRecipe(tempCrafting, null);
                    if (resultEntry != null)
                    {
                        ItemStack result = resultEntry.copy();
                        ItemStack tmp144_142 = result;
                        tmp144_142.stackSize = ((int)(tmp144_142.stackSize * 1.5F));
                        addCutting(log, result, 0.15f);
                    }
                }
            }
            else
            {
                ItemStack log = ItemStack.copyItemStack(logEntry);
                tempCrafting.setInventorySlotContents(0, log);
                ItemStack resultEntry = CraftingManager.getInstance().findMatchingRecipe(tempCrafting, null);
                if (resultEntry != null)
                {
                    ItemStack result = resultEntry.copy();
                    ItemStack tmp216_214 = result;
                    tmp216_214.stackSize = ((int)(tmp216_214.stackSize * 1.5F));
                    addCutting(log, result, 0.15f);
                }
            }
        }
    }

    /**
     * A metadata sensitive version of adding a furnace recipe.
     */
    public void addCutting(ItemStack item, ItemStack itemstack, float par3)
    {
        cuttingList.put(Arrays.asList(item, item.getItemDamage()),itemstack);
	    this.smeltingList.put(par1, itemstack);
	    this.experienceList.put(itemstack, par3);
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getCuttingResult(ItemStack item)
    {
        if (item == null)
        {
            return null;
        }
        ItemStack ret = cuttingList.get(Arrays.asList(item, item.getItemDamage()));
        if (ret != null)
        {
            return ret;
        }
        return null;
    }

    public Map<List<Object>, ItemStack> getCuttingList()
    {
        return cuttingList;
    }

	/**
     * Returns the smelting result of an item.
     * Deprecated in favor of a metadata sensitive version
     */
    @Deprecated
    public ItemStack getSmeltingResult(int par1)
{
	return (ItemStack)this.smeltingList.get(par1);
}

	public Map getSmeltingList()
	{
		return this.smeltingList;
	}

	@Deprecated //In favor of ItemStack sensitive version
	public float getExperience(int par1)
	{
		return this.experienceList.containsKey(par1) ? ((Float)this.experienceList.get(Integer.valueOf(par1))).floatValue() : 0.0F;
	}

	/**
	 * A metadata sensitive version of adding a furnace recipe.
	 */
	public void addSmelting(int itemID, int metadata, ItemStack itemstack, float experience) {
		metaSmeltingList.put(Arrays.asList(itemID, metadata), itemstack);
		metaExperience.put(Arrays.asList(itemstack.itemID, itemstack.getItemDamage()), experience);
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
		ItemStack ret = (ItemStack)metaSmeltingList.get(Arrays.asList(item.itemID, item.getItemDamage()));
		if (ret != null) {
			return ret;
		}
		return (ItemStack)smeltingList.get(Integer.valueOf(item.itemID));
	}

	/**
	 * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
	 */
	public float getExperience(ItemStack item) {
		if (item == null || item.getItem() == null) {
			return 0;
		}
		float ret = item.getItem().getSmeltingExperience(item);
		if (ret < 0 && metaExperience.containsKey(Arrays.asList(item.itemID, item.getItemDamage()))) {
			ret = metaExperience.get(Arrays.asList(item.itemID, item.getItemDamage()));
		}
		if (ret < 0 && experienceList.containsKey(item.itemID)) {
			ret = (Float) experienceList.get(item.itemID);
		}
		return (ret < 0 ? 0 : ret);
	}

	public Map<List<Integer>, ItemStack> getMetaSmeltingList()
	{
		return metaSmeltingList;
	}
}
