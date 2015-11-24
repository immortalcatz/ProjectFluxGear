package mortvana.legacy.clean.core.util.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mortvana.melteddashboard.util.helpers.EnergyHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cofh.api.item.IEmpowerableItem;
import cofh.api.item.IInventoryContainerItem;

import mortvana.legacy.errored.core.util.helpers.StringHelper;

public final class ItemHelper {
	public static final String BLOCK = "block";
	public static final String ORE = "ore";
	public static final String DUST = "dust";
	public static final String INGOT = "ingot";
	public static final String NUGGET = "nugget";
	public static final String LOG = "log";
	public static OreDictHelper oreHelper = new OreDictHelper();

	private ItemHelper() {}

	public static ItemStack cloneStack(Item item, int amount) {
		if(item == null) {
			return null;
		} else {
			ItemStack newStack = new ItemStack(item, amount);
			return newStack;
		}
	}

	public static ItemStack cloneStack(Block block, int amount) {
		if(block == null) {
			return null;
		} else {
			ItemStack newStack = new ItemStack(block, amount);
			return newStack;
		}
	}

	public static ItemStack cloneStack(ItemStack itemstack, int amount) {
		if(itemstack == null) {
			return null;
		} else {
			ItemStack newStack = itemstack.copy();
			newStack.stackSize = amount;
			return newStack;
		}
	}

	public static ItemStack cloneStack(ItemStack itemstack) {
		if(itemstack == null) {
			return null;
		} else {
			ItemStack newStack = itemstack.copy();
			return newStack;
		}
	}

	public static ItemStack copyTag(ItemStack var0, ItemStack var1) {
		if(var1 != null && var1.stackTagCompound != null) {
			var0.stackTagCompound = (NBTTagCompound)var1.stackTagCompound.copy();
		}

		return var0;
	}

	public static NBTTagCompound setItemStackTagName(NBTTagCompound nbtTag, String stackName) {
		if(stackName == "") {
			return null;
		} else {
			if(nbtTag == null) {
				nbtTag = new NBTTagCompound();
			}

			if(!nbtTag.hasKey("display")) {
				nbtTag.setTag("display", new NBTTagCompound());
			}

			nbtTag.getCompoundTag("display").setString("Name", stackName);
			return nbtTag;
		}
	}

	public static ItemStack readItemStackFromNBT(NBTTagCompound nbtTag) {
		ItemStack var1 = new ItemStack(Item.getItemById(nbtTag.getShort("id")));
		var1.stackSize = nbtTag.getInteger("Count");
		var1.setMetadata(Math.max(0, nbtTag.getShort("Damage")));
		if(nbtTag.hasKey("tag", 10)) {
			var1.stackTagCompound = nbtTag.getCompoundTag("tag");
		}

		return var1;
	}

	public static NBTTagCompound writeItemStackToNBT(ItemStack itemstack, NBTTagCompound nbtTag) {
		nbtTag.setShort("id", (short)Item.getIdFromItem(itemstack.getItem()));
		nbtTag.setInteger("Count", itemstack.stackSize);
		nbtTag.setShort("Damage", (short)getMetadata(itemstack));
		if(itemstack.stackTagCompound != null) {
			nbtTag.setTag("tag", itemstack.stackTagCompound);
		}
		return nbtTag;
	}

	public static NBTTagCompound writeItemStackToNBT(ItemStack itemstack, int amount, NBTTagCompound nbtTag) {
		nbtTag.setShort("id", (short)Item.getIdFromItem(itemstack.getItem()));
		nbtTag.setInteger("Count", amount);
		nbtTag.setShort("Damage", (short)getMetadata(itemstack));
		if(itemstack.stackTagCompound != null) {
			nbtTag.setTag("tag", itemstack.stackTagCompound);
		}
		return nbtTag;
	}

	public static String getNameFromItemStack(ItemStack itemstack) {
		return itemstack != null && itemstack.stackTagCompound != null && itemstack.stackTagCompound.hasKey("display") ? itemstack.stackTagCompound.getCompoundTag("display").getString("Name") : "";
	}

	public static ItemStack consumeItem(ItemStack itemstack) {
		Item item = itemstack.getItem();
		boolean var2 = itemstack.stackSize > 1;
		if(var2) {
			--itemstack.stackSize;
		}

		if(item.hasContainerItem(itemstack)) {
			ItemStack containerStack = item.getContainerItem(itemstack);
			if(containerStack == null) {
				return null;
			} else {
				if(containerStack.isItemStackDamageable() && containerStack.getMetadata() > containerStack.getMaxDurability()) {
					containerStack = null;
				}
				return containerStack;
			}
		} else {
			return var2 ? itemstack : null;
		}
	}

	public static int getMetadata(ItemStack itemstack) {
		return Items.diamond.getDamage(itemstack);
	}

	public static ItemStack findMatchingRecipe(InventoryCrafting var0, World var1) {
		ItemStack[] var2 = new ItemStack[2];

		for(int var3 = 0; var3 < var0.getSizeInventory(); ++var3) {
			if(var0.getStackInSlot(var3) != null) {
				if(var2[0] != null) {
					var2[1] = var0.getStackInSlot(var3);
					break;
				}
				var2[0] = var0.getStackInSlot(var3);
			}
		}

		if(var2[0] != null && var2[0].getItem() != null) {
			int var4;
			if(var2[1] != null && var2[0].getItem() == var2[1].getItem() && var2[0].stackSize == 1 && var2[1].stackSize == 1 && var2[0].getItem().isRepairable()) {
				Item var9 = var2[0].getItem();
				var4 = var9.getMaxDurability() - var2[0].getCurrentDurability();
				int var5 = var9.getMaxDurability() - var2[1].getCurrentDurability();
				int var6 = var4 + var5 + var9.getMaxDurability() * 5 / 100;
				int var7 = Math.max(0, var9.getMaxDurability() - var6);
				return new ItemStack(var2[0].getItem(), 1, var7);
			} else {
				for(var4 = 0; var4 < CraftingManager.getInstance().getRecipeList().size(); ++var4) {
					IRecipe var8 = (IRecipe)CraftingManager.getInstance().getRecipeList().get(var4);
					if(var8.matches(var0, var1)) {
						return var8.getCraftingResult(var0);
					}
				}
				return null;
			}
		} else {
			return null;
		}
	}

	public static ItemStack getOre(String name) {
		return oreHelper.getOre(name);
	}

	public static String getOreName(ItemStack itemstack) {
		return oreHelper.getOreName(itemstack);
	}

	public static boolean isOreIDEqual(ItemStack itemstack, int var1) {
		return oreHelper.isOreIDEqual(itemstack, var1);
	}

	public static boolean isOreNameEqual(ItemStack itemstack, String var1) {
		return oreHelper.isOreNameEqual(itemstack, var1);
	}

	public static boolean oreNameExists(String name) {
		return oreHelper.oreNameExists(name);
	}

	public static boolean hasOreName(ItemStack itemstack) {
		return !getOreName(itemstack).equals("Unknown");
	}

	public static boolean isBlock(ItemStack itemstack) {
		return getOreName(itemstack).startsWith("block");
	}

	public static boolean isOre(ItemStack itemstack) {
		return getOreName(itemstack).startsWith("ore");
	}

	public static boolean isDust(ItemStack itemstack) {
		return getOreName(itemstack).startsWith("dust");
	}

	public static boolean isIngot(ItemStack itemstack) {
		return getOreName(itemstack).startsWith("ingot");
	}

	public static boolean isNugget(ItemStack itemstack) {
		return getOreName(itemstack).startsWith("nugget");
	}

	public static boolean isLog(ItemStack itemstack) {
		return getOreName(itemstack).startsWith("log");
	}

	public static final ItemStack stack(Item item) {
		return new ItemStack(item);
	}

	public static final ItemStack stack(Item item, int amount) {
		return new ItemStack(item, amount);
	}

	public static final ItemStack stack(Item item, int amount, int meta) {
		return new ItemStack(item, amount, meta);
	}

	public static final ItemStack stack(Block block) {
		return new ItemStack(block);
	}

	public static final ItemStack stack(Block block, int amount) {
		return new ItemStack(block, amount);
	}

	public static final ItemStack stack(Block block, int amount, int meta) {
		return new ItemStack(block, amount, meta);
	}

	public static final ItemStack stack2(Item item) {
		return new ItemStack(item, 1, 32767);
	}

	public static final ItemStack stack2(Item item, int amount) {
		return new ItemStack(item, amount, 32767);
	}

	public static final ItemStack stack2(Block block) {
		return new ItemStack(block, 1, 32767);
	}

	public static final ItemStack stack2(Block block, int amount) {
		return new ItemStack(block, amount, 32767);
	}

	public static final IRecipe ShapedRecipe(Block output, Object... var1) {
		return new ShapedOreRecipe(output, var1);
	}

	public static final IRecipe ShapedRecipe(Item output, Object... var1) {
		return new ShapedOreRecipe(output, var1);
	}

	public static final IRecipe ShapedRecipe(ItemStack output, Object... var1) {
		return new ShapedOreRecipe(output, var1);
	}

	public static final IRecipe ShapelessRecipe(Block var0, Object... var1) {
		return new ShapelessOreRecipe(var0, var1);
	}

	public static final IRecipe ShapelessRecipe(Item var0, Object... var1) {
		return new ShapelessOreRecipe(var0, var1);
	}

	public static final IRecipe ShapelessRecipe(ItemStack var0, Object... var1) {
		return new ShapelessOreRecipe(var0, var1);
	}

	public static boolean addGearRecipe(ItemStack var0, String var1) {
		if(var0 != null && oreNameExists(var1)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, " X ", "XIX", " X ", 'X', var1, 'I', "ingotIron"));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addGearRecipe(ItemStack var0, String var1, String var2) {
		if(var0 != null && oreNameExists(var1) && oreNameExists(var2)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, " X ", "XIX", " X ", 'X', var1, 'I', var2));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addGearRecipe(ItemStack var0, String var1, ItemStack var2) {
		if(!(var0 == null | var2 == null) && oreNameExists(var1)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, " X ", "XIX", " X ", 'X', var1, 'I', var2));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addGearRecipe(ItemStack var0, ItemStack var1, String var2) {
		if(!(var0 == null | var1 == null) && oreNameExists(var2)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, " X ", "XIX", " X ", 'X', var1, 'I', var2));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addGearRecipe(ItemStack var0, ItemStack var1, ItemStack var2) {
		if(var0 == null | var1 == null | var2 == null) {
			return false;
		} else {
			GameRegistry.addRecipe(cloneStack(var0), " X ", "XIX", " X ", 'X', cloneStack((ItemStack)var1, 1), 'I', cloneStack((ItemStack)var2, 1));
			return true;
		}
	}

	public static boolean addRotatedGearRecipe(ItemStack var0, String var1, String var2) {
		if(var0 != null && oreNameExists(var1) && oreNameExists(var2)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, "X X", " I ", "X X", 'X', var1, 'I', var2));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addRotatedGearRecipe(ItemStack var0, String var1, ItemStack var2) {
		if(!(var0 == null | var2 == null) && oreNameExists(var1)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, "X X", " I ", "X X", 'X', var1, 'I', var2));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addRotatedGearRecipe(ItemStack outputGear, ItemStack gearMaterial, String centerMaterial) {
		if(!(outputGear == null | gearMaterial == null) && oreNameExists(centerMaterial)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(outputGear, "X X", " I ", "X X", 'X', gearMaterial, 'I', centerMaterial));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addRotatedGearRecipe(ItemStack var0, ItemStack var1, ItemStack var2) {
		if(var0 == null | var1 == null | var2 == null) {
			return false;
		} else {
			GameRegistry.addRecipe(cloneStack(var0), "X X", " I ", "X X", 'X', cloneStack((ItemStack)var1, 1), 'I', cloneStack((ItemStack)var2, 1));
			return true;
		}
	}

	public static boolean addSurroundRecipe(ItemStack var0, ItemStack var1, ItemStack var2) {
		if(var0 == null | var1 == null | var2 == null) {
			return false;
		} else {
			GameRegistry.addRecipe(cloneStack(var0), "XXX", "XIX", "XXX", 'X', cloneStack((ItemStack)var2, 1), 'I', cloneStack((ItemStack)var1, 1));
			return true;
		}
	}

	public static boolean addSurroundRecipe(ItemStack var0, String var1, ItemStack var2) {
		if(!(var0 == null | var2 == null) && oreNameExists(var1)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, "XXX", "XIX", "XXX", 'X', var2, 'I', var1));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addSurroundRecipe(ItemStack var0, ItemStack var1, String var2) {
		if(!(var0 == null | var1 == null) && oreNameExists(var2)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, "XXX", "XIX", "XXX", 'X', var2, 'I', var1));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addSurroundRecipe(ItemStack var0, String var1, String var2) {
		if(var0 != null && oreNameExists(var1) && oreNameExists(var2)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, "XXX", "XIX", "XXX", 'X', var2, 'I', var1));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addFenceRecipe(ItemStack var0, ItemStack var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			GameRegistry.addRecipe(cloneStack(var0), "XXX", "XXX", 'X', cloneStack((ItemStack)var1, 1));
			return true;
		}
	}

	public static boolean addFenceRecipe(ItemStack var0, String var1) {
		if(var0 != null && oreNameExists(var1)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, "XXX", "XXX", 'X', var1));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addReverseStorageRecipe(ItemStack var0, String var1) {
		if(var0 != null && oreNameExists(var1)) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(cloneStack((ItemStack)var0, 9), var1));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addReverseStorageRecipe(ItemStack var0, ItemStack var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			GameRegistry.addShapelessRecipe(cloneStack((ItemStack)var0, 9), cloneStack((ItemStack)var1, 1));
			return true;
		}
	}

	public static boolean addSmallReverseStorageRecipe(ItemStack var0, String var1) {
		if(var0 != null && oreNameExists(var1)) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(cloneStack((ItemStack)var0, 4), var1));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addSmallReverseStorageRecipe(ItemStack var0, ItemStack var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			GameRegistry.addShapelessRecipe(cloneStack((ItemStack)var0, 4), cloneStack((ItemStack)var1, 1));
			return true;
		}
	}

	public static boolean addStorageRecipe(ItemStack var0, String var1) {
		if(var0 != null && oreNameExists(var1)) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(var0, var1, var1, var1, var1, var1, var1, var1, var1, var1));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addStorageRecipe(ItemStack var0, ItemStack var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			var1 = cloneStack((ItemStack)var1, 1);
			GameRegistry.addShapelessRecipe(var0, var1, var1, var1, var1, var1, var1, var1, var1, var1);
			return true;
		}
	}

	public static boolean addSmallStorageRecipe(ItemStack var0, String var1) {
		if(var0 != null && oreNameExists(var1)) {
			GameRegistry.addRecipe(new ShapedOreRecipe(var0, "XX", "XX", 'X', var1));
			return true;
		} else {
			return false;
		}
	}

	public static boolean addSmallStorageRecipe(ItemStack var0, ItemStack var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			GameRegistry.addRecipe(cloneStack(var0), "XX", "XX", 'X', cloneStack((ItemStack)var1, 1));
			return true;
		}
	}

	public static boolean addSmelting(ItemStack var0, Item var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			FurnaceRecipes.instance().addSmeltingRecipe(cloneStack(var1, 1), cloneStack(var0), 0.0F);
			return true;
		}
	}

	public static boolean addSmelting(ItemStack var0, Block var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			FurnaceRecipes.instance().addSmeltingRecipe(cloneStack(var1, 1), cloneStack(var0), 0.0F);
			return true;
		}
	}

	public static boolean addSmelting(ItemStack var0, ItemStack var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			FurnaceRecipes.instance().addSmeltingRecipe(cloneStack(var1, 1), cloneStack(var0), 0.0F);
			return true;
		}
	}

	public static boolean addSmelting(ItemStack var0, Item var1, float var2) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			FurnaceRecipes.instance().addSmeltingRecipe(cloneStack(var1, 1), cloneStack(var0), var2);
			return true;
		}
	}

	public static boolean addSmelting(ItemStack var0, Block var1, float var2) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			FurnaceRecipes.instance().addSmeltingRecipe(cloneStack(var1, 1), cloneStack(var0), var2);
			return true;
		}
	}

	public static boolean addSmelting(ItemStack var0, ItemStack var1, float var2) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			FurnaceRecipes.instance().addSmeltingRecipe(cloneStack(var1, 1), cloneStack(var0), var2);
			return true;
		}
	}

	public static boolean addWeakSmelting(ItemStack var0, Item var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			FurnaceRecipes.instance().addSmeltingRecipe(cloneStack(var1, 1), cloneStack(var0), 0.1F);
			return true;
		}
	}

	public static boolean addWeakSmelting(ItemStack var0, Block var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			FurnaceRecipes.instance().addSmeltingRecipe(cloneStack((Block)var1, 1), cloneStack(var0), 0.1F);
			return true;
		}
	}

	public static boolean addWeakSmelting(ItemStack var0, ItemStack var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			FurnaceRecipes.instance().addSmeltingRecipe(cloneStack((ItemStack)var1, 1), cloneStack(var0), 0.1F);
			return true;
		}
	}

	public static boolean addTwoWayConversionRecipe(ItemStack var0, ItemStack var1) {
		if(var0 == null | var1 == null) {
			return false;
		} else {
			GameRegistry.addShapelessRecipe(cloneStack(var0, 1), cloneStack(var1, 1));
			GameRegistry.addShapelessRecipe(cloneStack(var1, 1), cloneStack(var0, 1));
			return true;
		}
	}

	public static void registerWithHandlers(String var0, ItemStack var1) {
		OreDictionary.registerOre(var0, var1);
		GameRegistry.registerCustomItemStack(var0, var1);
		FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", var1);
	}

	public static void addRecipe(IRecipe var0) {
		GameRegistry.addRecipe(var0);
	}

	public static void addRecipe(ItemStack output, Object... input) {
		GameRegistry.addRecipe(output, input);
	}

	public static void addShapedRecipe(ItemStack var0, Object... var1) {
		GameRegistry.addRecipe(var0, var1);
	}

	public static void addShapelessRecipe(ItemStack var0, Object... var1) {
		GameRegistry.addShapelessRecipe(var0, var1);
	}

	public static boolean isPlayerHoldingEmpowerableItem(EntityPlayer var0) {
		Item var1 = var0.getCurrentEquippedItem() != null?var0.getCurrentEquippedItem().getItem():null;
		return var1 instanceof IEmpowerableItem;
	}

	public static boolean isPlayerHoldingEmpoweredItem(EntityPlayer var0) {
		Item var1 = var0.getCurrentEquippedItem() != null?var0.getCurrentEquippedItem().getItem():null;
		return var1 instanceof IEmpowerableItem && ((IEmpowerableItem)var1).isEmpowered(var0.getCurrentEquippedItem());
	}

	public static boolean toggleHeldEmpowerableItemState(EntityPlayer var0) {
		ItemStack var1 = var0.getCurrentEquippedItem();
		IEmpowerableItem var2 = (IEmpowerableItem)var1.getItem();
		return var2.setEmpoweredState(var1, !var2.isEmpowered(var1));
	}

	public static final boolean isPlayerHoldingFluidContainer(EntityPlayer player) {
		return FluidContainerRegistry.isContainer(player.getCurrentEquippedItem());
	}

	public static final boolean isPlayerHoldingFluidContainerItem(EntityPlayer player) {
		return FluidHelper.isPlayerHoldingFluidContainerItem(player);
	}

	public static final boolean isPlayerHoldingEnergyContainerItem(EntityPlayer player) {
		return EnergyHelper.isPlayerHoldingContainerItem(player);
	}

	public static final boolean isPlayerHoldingNothing(EntityPlayer player) {
		return player.getCurrentEquippedItem() == null;
	}

	public static Item getItemFromStack(ItemStack itemstack) {
		return itemstack == null ? null : itemstack.getItem();
	}

	public static boolean areItemsEqual(Item var0, Item var1) {
		return var0 == var1?true:(var0 == null | var1 == null?false:var0.equals(var1));
	}

	public static final boolean isPlayerHoldingItem(Class<?> var0, EntityPlayer var1) {
		return var0.isInstance(getItemFromStack(var1.getCurrentEquippedItem()));
	}

	public static final boolean isPlayerHoldingItem(Item var0, EntityPlayer var1) {
		return areItemsEqual(var0, getItemFromStack(var1.getCurrentEquippedItem()));
	}

	public static final boolean isPlayerHoldingItemStack(ItemStack var0, EntityPlayer var1) {
		return itemsEqualWithMetadata(var0, var1.getCurrentEquippedItem());
	}

	public static boolean itemsEqualWithoutMetadata(ItemStack var0, ItemStack var1) {
		return var0 == var1?true:(var0 == null | var1 == null?false:var0.getItem().equals(var1.getItem()));
	}

	public static boolean itemsEqualWithoutMetadata(ItemStack var0, ItemStack var1, boolean var2) {
		return var0 == var1?true:itemsEqualWithoutMetadata(var0, var1) && (!var2 || doNBTsMatch(var0.stackTagCompound, var1.stackTagCompound));
	}

	public static boolean itemsEqualWithMetadata(ItemStack var0, ItemStack var1) {
		return var0 == var1 ? true : itemsEqualWithoutMetadata(var0, var1) && (!var0.getHasSubtypes() || var0.getMetadata() == var1.getMetadata());
	}

	public static boolean itemsEqualWithMetadata(ItemStack var0, ItemStack var1, boolean var2) {
		return var0 == var1 ? true:itemsEqualWithMetadata(var0, var1) && (!var2 || doNBTsMatch(var0.stackTagCompound, var1.stackTagCompound));
	}

	public static boolean itemsIdentical(ItemStack var0, ItemStack var1) {
		return var0 == var1 ? true : itemsEqualWithoutMetadata(var0, var1) && var0.getMetadata() == var1.getMetadata() && doNBTsMatch(var0.stackTagCompound, var1.stackTagCompound);
	}

	public static boolean doNBTsMatch(NBTTagCompound var0, NBTTagCompound var1) {
		return var0 == var1 ? true : (var0 != null & var1 != null ? var0.equals(var1) : false);
	}

	public static boolean itemsEqualForCrafting(ItemStack var0, ItemStack var1) {
		return itemsEqualWithoutMetadata(var0, var1) && (!var0.getHasSubtypes() || var0.getMetadata() == 32767 || var1.getMetadata() == 32767 || var1.getMetadata() == var0.getMetadata());
	}

	public static boolean craftingEquivalent(ItemStack var0, ItemStack var1, String var2, ItemStack var3) {
		return itemsEqualForCrafting(var0, var1) ? true:(var3 != null && isBlacklist(var3) ? false:(var2 != null && !var2.equals("Unknown") ? getOreName(var0).equalsIgnoreCase(var2) : false));
	}

	public static boolean doOreIDsMatch(ItemStack var0, ItemStack var1) {
		int var2 = oreHelper.getOreID(var0);
		return var2 >= 0 && var2 == oreHelper.getOreID(var1);
	}

	public static boolean isBlacklist(ItemStack var0) {
		Item var1 = var0.getItem();
		return Item.getItemFromBlock(Blocks.birch_stairs) == var1 || Item.getItemFromBlock(Blocks.jungle_stairs) == var1 || Item.getItemFromBlock(Blocks.oak_stairs) == var1 || Item.getItemFromBlock(Blocks.spruce_stairs) == var1 || Item.getItemFromBlock(Blocks.planks) == var1 || Item.getItemFromBlock(Blocks.wooden_slab) == var1;
	}

	public static String getItemNBTString(ItemStack var0, String var1, String var2) {
		return var0.stackTagCompound != null && var0.stackTagCompound.hasKey(var1)?var0.stackTagCompound.getString(var1):var2;
	}

	public static void addInventoryInformation(ItemStack itemstack, List<String> var1) {
		addInventoryInformation(itemstack, var1, 0, 2147483647);
	}

	public static void addInventoryInformation(ItemStack itemstack, List<String> var1, int var2, int var3) {
		if(itemstack.stackTagCompound == null) {
			var1.add(StringHelper.localize("info.fluxgear.empty"));
		} else if(itemstack.getItem() instanceof IInventoryContainerItem && itemstack.stackTagCompound.hasKey("Accessible")) {
			addAccessibleInventoryInformation(itemstack, var1, var2, var3);
		} else if(itemstack.stackTagCompound.hasKey("Inventory") && itemstack.stackTagCompound.getTagList("Inventory", itemstack.stackTagCompound.getId()).tagCount() > 0) {
			NBTTagList var4 = itemstack.stackTagCompound.getTagList("Inventory", itemstack.stackTagCompound.getId());
			ArrayList var7 = new ArrayList();
			boolean[] var8 = new boolean[var4.tagCount()];

			int var11;
			for(int var9 = 0; var9 < var4.tagCount(); ++var9) {
				NBTTagCompound var10 = var4.getCompoundTagAt(var9);
				var11 = var10.getInteger("Slot");
				if(!var8[var9] && var11 >= var2 && var11 <= var3) {
					var8[var9] = true;
					ItemStack var5 = ItemStack.loadItemStackFromNBT(var10);
					if(var5 != null) {
						var7.add(var5);

						for(int var12 = 0; var12 < var4.tagCount(); ++var12) {
							NBTTagCompound var13 = var4.getCompoundTagAt(var12);
							int var14 = var10.getInteger("Slot");
							if(!var8[var12] && var14 >= var2 && var14 <= var3) {
								ItemStack var6 = ItemStack.loadItemStackFromNBT(var13);
								if(var6 != null && itemsEqualWithMetadata(var5, var6)) {
									var5.stackSize += var6.stackSize;
									var8[var12] = true;
								}
							}
						}
					}
				}
			}

			if(var7.size() > 0) {
				var1.add(StringHelper.localize("info.fluxgear.contents") + ":");
			}

			Iterator var15 = var7.iterator();

			while(var15.hasNext()) {
				ItemStack var16 = (ItemStack)var15.next();
				var11 = var16.getMaxStackSize();
				if(StringHelper.displayStackCount && var16.stackSize >= var11 && var11 != 1) {
					if(var16.stackSize % var11 != 0) {
						var1.add("    \u00a7a" + var11 + "x" + var16.stackSize / var11 + "+" + var16.stackSize % var11 + " " + StringHelper.getItemName(var16));
					} else {
						var1.add("    \u00a7a" + var11 + "x" + var16.stackSize / var11 + " " + StringHelper.getItemName(var16));
					}
				} else {
					var1.add("    \u00a7a" + var16.stackSize + " " + StringHelper.getItemName(var16));
				}
			}

		} else {
			var1.add(StringHelper.localize("info.fluxgear.empty"));
		}
	}

	public static void addAccessibleInventoryInformation(ItemStack itemstack, List<String> var1, int var2, int var3) {
		int var4 = ((IInventoryContainerItem)itemstack.getItem()).getSizeInventory(itemstack);
		ArrayList var7 = new ArrayList();
		boolean[] var8 = new boolean[var4];

		for(int var9 = var2; var9 < Math.min(var4, var3); ++var9) {
			if(!var8[var9] && itemstack.stackTagCompound.hasKey("Slot" + var9)) {
				ItemStack var5 = ItemStack.loadItemStackFromNBT(itemstack.stackTagCompound.getCompoundTag("Slot" + var9));
				var8[var9] = true;
				if(var5 != null) {
					var7.add(var5);

					for(int var10 = var2; var10 < Math.min(var4, var3); ++var10) {
						if(!var8[var10] && itemstack.stackTagCompound.hasKey("Slot" + var10)) {
							ItemStack var6 = ItemStack.loadItemStackFromNBT(itemstack.stackTagCompound.getCompoundTag("Slot" + var10));
							if(var6 != null && itemsEqualWithMetadata(var5, var6)) {
								var5.stackSize += var6.stackSize;
								var8[var10] = true;
							}
						}
					}
				}
			}
		}

		if(var7.size() > 0) {
			var1.add(StringHelper.localize("info.fluxgear.contents") + ":");
		} else {
			var1.add(StringHelper.localize("info.fluxgear.empty"));
		}

		Iterator var12 = var7.iterator();

		while(var12.hasNext()) {
			ItemStack var13 = (ItemStack)var12.next();
			int var11 = var13.getMaxStackSize();
			if(StringHelper.displayStackCount && var13.stackSize >= var11 && var11 != 1) {
				if(var13.stackSize % var11 != 0) {
					var1.add("    \u00a7a" + var11 + "x" + var13.stackSize / var11 + "+" + var13.stackSize % var11 + " " + StringHelper.getItemName(var13));
				} else {
					var1.add("    \u00a7a" + var11 + "x" + var13.stackSize / var11 + " " + StringHelper.getItemName(var13));
				}
			} else {
				var1.add("    \u00a7a" + var13.stackSize + " " + StringHelper.getItemName(var13));
			}
		}

	}
}
