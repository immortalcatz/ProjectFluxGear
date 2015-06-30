package mortvana.projectfluxgear.util.helpers;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.ActiveToolMod;
import tconstruct.library.client.TConstructClientRegistry;
import tconstruct.library.crafting.*;
import tconstruct.library.util.IPattern;
import tconstruct.smeltery.TinkerSmeltery;
import tconstruct.tools.TinkerTools;
import tconstruct.weaponry.TinkerWeaponry;

public class TinkersHelper {

	public static int ingotFluid = TConstruct.ingotLiquidValue;

	public static Block glueBlock = TinkerSmeltery.glueBlock;

	public static Fluid moltenSteel = TinkerSmeltery.moltenSteelFluid;
	public static Fluid moltenEnderium = TinkerSmeltery.moltenEnderiumFluid;
	public static Fluid moltenLumium = TinkerSmeltery.moltenLumiumFluid;
	public static Fluid moltenSignalum = TinkerSmeltery.moltenSignalumFluid;
	public static Fluid moltenManaMithral = TinkerSmeltery.moltenMithrilFluid;

	public static Item toolShard = TinkerTools.toolShard;
	public static Item toolRod = TinkerTools.toolRod;

	public static ItemStack blankCast = new ItemStack(TinkerSmeltery.metalPattern, 1, 0);

	public static PatternBuilder patternBuilder = PatternBuilder.instance;
	public static LiquidCasting tableCasting = TConstructRegistry.getTableCasting();
	public static LiquidCasting basinCasting = TConstructRegistry.getBasinCasting();

	public static void registerMaterial(int id, String name, int harvestLevel, int durability, int speed, int attack, float handle, int reinforced, int color, int mass, float breakChance, int drawSpeed, float arrowSpeed) {
		registerMaterial(id, name, harvestLevel, durability, speed, attack, handle, reinforced, color, mass, breakChance, drawSpeed, arrowSpeed, EnumChatFormatting.DARK_AQUA.toString());
	}

	public static void registerMaterial(int id, String name, int harvestLevel, int durability, int speed, int attack, float handle, int reinforced, int color, int mass, float breakChance, int drawSpeed, float arrowSpeed, String textColor) {
		if (id != -1) {
			TConstructRegistry.addToolMaterial(id, name, harvestLevel, durability, speed, attack, handle, reinforced, 0, textColor, color);
			TConstructRegistry.addArrowMaterial(id, mass, breakChance);
			TConstructRegistry.addBowMaterial(id, drawSpeed, arrowSpeed);
			TConstructRegistry.addDefaultShardMaterial(id);
			TConstructRegistry.addDefaultToolPartMaterial(id);
		}
	}

	public static int getIngotFluidValue() {
		return ingotFluid;
	}

	public static void registerPartBuilding(int materialID) {
		for (int meta = 0; meta < TinkerTools.patternOutputs.length; meta++) {
			if (TinkerTools.patternOutputs[meta] != null) {
				TConstructRegistry.addPartMapping(TinkerTools.woodPattern, meta + 1, materialID, new ItemStack(TinkerTools.patternOutputs[meta], 1, materialID));
			}
		}

		for (int meta = 0; meta < TinkerWeaponry.patternOutputs.length; meta++) {
			if (TinkerWeaponry.patternOutputs[meta] != null) {
				TConstructRegistry.addPartMapping(TinkerWeaponry.woodPattern, meta + 1, materialID, new ItemStack(TinkerWeaponry.patternOutputs[meta], 1, materialID));
			}
		}

		TConstructRegistry.addPartMapping(TinkerTools.woodPattern, 25, materialID, new ItemStack(TinkerWeaponry.arrowhead, 1, materialID));
	}

	public static void registerCasting(Fluid fluid, int materialID, String oreDict, String materialKey) {
		ArrayList<ItemStack> itemstacks = OreDictionary.getOres(oreDict);
		if (materialID != -1) {
			if (itemstacks.size() == 1) {
				ItemStack entry = itemstacks.get(0);
				registerCasting(fluid, materialID, entry, materialKey);
			} else if (itemstacks.size() > 1) {
				for (ItemStack material : itemstacks) {
					if (material != null) {
						registerCasting(fluid, materialID, material, materialKey);
					}
				}
			}
		}
	}

	public static void registerCasting(Fluid fluid, int materialID, ItemStack material, String materialKey) {
		tableCasting.addCastingRecipe(blankCast, new FluidStack(TinkerSmeltery.moltenAlubrassFluid, ingotFluid), material, false, 50);
		tableCasting.addCastingRecipe(blankCast, new FluidStack(TinkerSmeltery.moltenGoldFluid, ingotFluid * 2), material, false, 50);
		tableCasting.addCastingRecipe(material, new FluidStack(fluid, ingotFluid), blankCast, 80);
		Smeltery.addMelting(FluidType.getFluidType(materialKey), material, 0, ingotFluid);

		registerCasting(fluid, materialID);
	}

	public static boolean registerCasting(Fluid fluid, int materialID, String oreDict) {
		if (OreDictionary.getOres(oreDict).size() > 0) {
			registerCasting(fluid, materialID);
			return true;
		} else {
			return false;
		}
	}


	public static void registerCasting(Fluid fluid, int materialID) {
		if (materialID != -1 && fluid != null) {
			int fluidAmount;

			for (int i = 0; i < TinkerTools.patternOutputs.length; i++) {
				if (TinkerTools.patternOutputs[i] != null) {
					ItemStack cast = new ItemStack(TinkerSmeltery.metalPattern, 1, i + 1);

					tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenAlubrassFluid, ingotFluid), new ItemStack(TinkerTools.patternOutputs[i], 1, Short.MAX_VALUE), false, 50);
					tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenGoldFluid, ingotFluid * 2), new ItemStack(TinkerTools.patternOutputs[i], 1, Short.MAX_VALUE), false, 50);

					fluidAmount = ((IPattern) TinkerSmeltery.metalPattern).getPatternCost(cast) * ingotFluid / 2;
					ItemStack metalCast = new ItemStack(TinkerTools.patternOutputs[i], 1, materialID);
					tableCasting.addCastingRecipe(metalCast, new FluidStack(fluid, fluidAmount), cast, 50);
					Smeltery.addMelting(FluidType.getFluidType(fluid), metalCast, 0, fluidAmount);
				}
			}

			for (int i = 0; i < TinkerWeaponry.patternOutputs.length; i++) {
				if (TinkerWeaponry.patternOutputs[i] != null) {
					ItemStack cast = new ItemStack(TinkerWeaponry.metalPattern, 1, i);

					tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenAlubrassFluid, ingotFluid), new ItemStack(TinkerWeaponry.patternOutputs[i], 1, Short.MAX_VALUE), false, 50);
					tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenGoldFluid, ingotFluid * 2), new ItemStack(TinkerWeaponry.patternOutputs[i], 1, Short.MAX_VALUE), false, 50);

					fluidAmount = TinkerWeaponry.metalPattern.getPatternCost(cast) * ingotFluid / 2;
					ItemStack metalCast = new ItemStack(TinkerWeaponry.patternOutputs[i], 1, materialID);
					tableCasting.addCastingRecipe(metalCast, new FluidStack(fluid, fluidAmount), cast, 50);
					Smeltery.addMelting(FluidType.getFluidType(fluid), metalCast, 0, fluidAmount);
				}
			}

			ItemStack cast = new ItemStack(TinkerSmeltery.metalPattern, 1, 25);
			tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenAlubrassFluid, ingotFluid), new ItemStack(TinkerWeaponry.arrowhead, 1, Short.MAX_VALUE), false, 50);
			tableCasting.addCastingRecipe(cast, new FluidStack(TinkerSmeltery.moltenGoldFluid, ingotFluid * 2), new ItemStack(TinkerWeaponry.arrowhead, 1, Short.MAX_VALUE), false, 50);

			fluidAmount = ((IPattern) TinkerSmeltery.metalPattern).getPatternCost(cast) * ingotFluid / 2;
			ItemStack metalCast = new ItemStack(TinkerWeaponry.arrowhead, 1, materialID);
			tableCasting.addCastingRecipe(metalCast, new FluidStack(fluid, fluidAmount), cast, 50);
			Smeltery.addMelting(FluidType.getFluidType(fluid), metalCast, 0, fluidAmount);
		}
	}

	public static int getPartCount(NBTTagCompound toolNBT, int materialID) {
		int parts = 0;
		String[] areas = new String[] { "Head", "Handle", "Accessory", "Extra" };
		for (int i = 0; i < 4; i++) {
			if (toolNBT.getInteger(areas[i]) == materialID) {
				parts++;
			}
		}
		return parts;
	}

	public static void registerFluidType(String name, Block block, int a, int b, Fluid fluid, boolean bool) {
		FluidType.registerFluidType(name, block, a, b, fluid, bool);
	}

	public static void addMaterialRenderMappings(FMLPostInitializationEvent event, int... materials) {
		if (event.getSide().isClient()) {
			for (int material : materials) {
				TConstructClientRegistry.addMaterialRenderMapping(material, "tinker", TConstructRegistry.getMaterial(material).name(), true);
			}
		}
	}

	public static void addOreDictSmelting(String oreDict, String fluidType, int mB) {
		for (ItemStack itemstack : OreDictionary.getOres(oreDict)) {
			Smeltery.addMelting(FluidType.getFluidType(fluidType), itemstack, 0, mB);
		}
	}

	public static void registerActiveToolMod(ActiveToolMod modifier) {
		TConstructRegistry.registerActiveToolMod(modifier);
	}

	public static void addAlloying(FluidStack result, FluidStack... ingredients) {
		Smeltery.addAlloyMixing(result, ingredients);
	}

	public static void registerFullOreDictMaterial(String oreDict, String materialName, int materialID) {
		registerFullOreDictMaterial(oreDict, 2, materialName, toolShard, 1, toolRod, 1, materialID);
	}

	public static void registerFullOreDictMaterial(String oreDict, int value, String materialName, Item toolShard, int shards, Item toolRod, int rods, int materialID) {
		ArrayList<ItemStack> itemstacks = OreDictionary.getOres(oreDict);
		if (materialID != -1) {
			if (itemstacks.size() == 1) {
				ItemStack entry = itemstacks.get(0);
				registerFullMaterial(entry, value, materialName, toolShard, shards, toolRod, rods, materialID);
			} else if (itemstacks.size() > 1) {
				ItemStack entry = itemstacks.get(0);
				registerFullMaterial(entry, value, materialName, toolShard, shards, toolRod, rods, materialID);
				itemstacks.remove(0);
				for (ItemStack material : OreDictionary.getOres(oreDict)) {
					if (material != null) {
						patternBuilder.registerMaterial(material, value, materialName);
					}
				}
			}
		}
	}

	public static void registerFullMaterial(ItemStack material, int value, String materialName, Item toolShard, int shards, Item toolRod, int rods, int materialID) {
		patternBuilder.registerFullMaterial(material, value, materialName, new ItemStack(toolShard, shards, materialID), new ItemStack(toolRod, rods, materialID), materialID);
	}


}
