package mortvana.projectfluxgear.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import mortvana.mechstoneworks.common.MechStoneworksContent;
import mortvana.projectfluxgear.item.tool.ItemPaintbrush;

public class RecipePaintbrush implements IRecipe {
	private ItemStack output = null;
	private ArrayList<Object> input = new ArrayList<Object>();
	private int type;

	public RecipePaintbrush(Block result, int type, Object... recipe) {
		this((new ItemStack(result)), type, recipe);
	}

	public RecipePaintbrush(Item result, int type, Object... recipe) {
		this((new ItemStack(result)), type, recipe);
	}

	public RecipePaintbrush(ItemStack result, int type, Object... recipe) {
		input = new ArrayList();
		output = result.copy();
		this.type = type;
		Object[] arr$ = recipe;
		int len$ = recipe.length;

		for(int i$ = 0; i$ < len$; ++i$) {
			Object in = arr$[i$];
			if(in instanceof ItemStack) {
				this.input.add(((ItemStack)in).copy());
			} else if(in instanceof Item) {
				this.input.add(new ItemStack((Item)in));
			} else if(in instanceof Block) {
				this.input.add(new ItemStack((Block)in));
			} else {
				if(!(in instanceof String)) {
					String ret = "Invalid shapeless ore recipe: ";
					Object[] arr$1 = recipe;
					int len$1 = recipe.length;

					for(int i$1 = 0; i$1 < len$1; ++i$1) {
						Object tmp = arr$1[i$1];
						ret = ret + tmp + ", ";
					}

					ret = ret + this.output;
					throw new RuntimeException(ret);
				}

				this.input.add(OreDictionary.getOres((String) in));
			}
		}

	}

	public RecipePaintbrush(ShapelessRecipes recipe, Map<ItemStack, String> replacements) {
		input = new ArrayList();
		output = recipe.getRecipeOutput();
		Iterator i$ = recipe.recipeItems.iterator();

		while(i$.hasNext()) {
			ItemStack ingred = (ItemStack)i$.next();
			Object finalObj = ingred;
			Iterator i$1 = replacements.entrySet().iterator();

			while(true) {
				if(i$1.hasNext()) {
					Map.Entry replace = (Map.Entry)i$1.next();
					if(!OreDictionary.itemMatches((ItemStack)replace.getKey(), ingred, false)) {
						continue;
					}

					finalObj = OreDictionary.getOres((String)replace.getValue());
				}

				this.input.add(finalObj);
				break;
			}
		}

	}

	public int getRecipeSize() {
		return input.size();
	}

	public ItemStack getRecipeOutput() {
		return output;
	}



	ItemStack paintbrush;
	ItemStack dye;

	public boolean matches(InventoryCrafting inventoryCrafting, World world) {

		/*int countDye = 0;
		ItemStack tempStack;
		boolean foundBrush = false;

		for (int i = 0; i < inventoryCrafting.getSizeInventory(); ++i) {
			tempStack = inventoryCrafting.getStackInSlot(i);
			if (tempStack instanceof ItemStack) {
				if (tempStack.getItem() == paintbrush.getItem()) {
					if (foundBrush) {
						return false;
					}
					foundBrush = true;
				} else if (tempStack.getItem() == dye.getItem() && tempStack.getItemDamage() == dye.getItemDamage() /*&& countDye < 1702* /) {
					++countDye;
				} else {
					return false;
				}
			}
			if (countDye > 0 && foundBrush) {
				return true;
			}
		}
		return false;*/


		ArrayList required = new ArrayList(this.input);

		for(int x = 0; x < inventoryCrafting.getSizeInventory(); ++x) {
			ItemStack slot = inventoryCrafting.getStackInSlot(x);
			if(slot != null) {
				boolean inRecipe = false;
				Iterator req = required.iterator();

				while(req.hasNext()) {
					boolean match = false;
					Object next = req.next();
					if(next instanceof ItemStack) {
						match = this.checkItemEquals((ItemStack)next, slot);
					} else {
						ItemStack item;
						if(next instanceof ArrayList) {
							for(Iterator i$ = ((ArrayList)next).iterator(); i$.hasNext(); match = match || this.checkItemEquals(item, slot)) {
								item = (ItemStack)i$.next();
							}
						}
					}

					if(match) {
						inRecipe = true;
						required.remove(next);
						break;
					}
				}

				if(!inRecipe) {
					return false;
				}
			}
		}

		return required.isEmpty();
	}

	public ItemStack getCraftingResult(InventoryCrafting crafting) {
		ItemStack paintbrush = null;
		int amount = 0;

		int paint;
		for(paint = 0; paint < crafting.getSizeInventory(); ++paint) {
			ItemStack stack = crafting.getStackInSlot(paint);
			if(stack != null) {
				if(stack.getItem() instanceof ItemPaintbrush) {
					paintbrush = stack.copy();
				} else {
					++amount;
				}
			}
		}

		if(paintbrush != null && amount != 0) {
			if(!paintbrush.hasTagCompound()) {
				paintbrush.setTagCompound(new NBTTagCompound());
			}

			paintbrush.getTagCompound().setInteger("PaintType", type);
			paint = paintbrush.getItemDamage();
			if(paint == 0) {
				paint = 1728;
			}

			paint -= amount * 27;
			if(paint < 0) {
				paint = 0;
			}

			paintbrush.setItemDamage(paint);
			return paintbrush;
		} else {
			return null;
		}
	}

	private boolean checkItemEquals(ItemStack target, ItemStack input) {
			return input.getItem() == MechStoneworksContent.paintbrush ? !input.hasTagCompound() || input.getTagCompound().getInteger("PaintType") == 0 || input.getTagCompound().getInteger("PaintType") == type : target.copy() == input.copy() && (target.getItemDamage() == 32767 || target.getItemDamage() == input.getItemDamage());
	}

	public ArrayList getInput() {
		return input;
	}
}

