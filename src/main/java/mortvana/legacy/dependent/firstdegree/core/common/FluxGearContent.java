package mortvana.legacy.dependent.firstdegree.core.common;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.*;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.*;
import net.minecraftforge.oredict.*;

import cofh.core.fluid.BlockFluidCoFHBase;
import mantle.lib.TabTools;

import mortvana.melteddashboard.block.FluxGearItemBlock;
import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.intermod.tinkers.TinkersHelper;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.item.FluxGearItem;
import mortvana.melteddashboard.util.enums.EnumArmorType;
import mortvana.melteddashboard.util.helpers.*;

import mortvana.projectfluxgear.api.data.TweakItemRegistry;
import mortvana.projectfluxgear.immersion.item.ItemPaintbrush;
import mortvana.projectfluxgear.thaumic.augments.*;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import mortvana.projectfluxgear.thaumic.item.ItemThaumicBauble;
import mortvana.projectfluxgear.tinkers.modifiers.ActiveToolModFeedback;
import mortvana.projectfluxgear.util.helpers.TweakHelper;

import mekanism.api.recipe.RecipeHelper;
import mortvana.legacy.clean.core.common.FluxGearConfig;
import mortvana.legacy.clean.core.util.block.BlockFluxGear;
import mortvana.legacy.clean.core.util.handlers.DispenserEmptyBucketHandler;
import mortvana.legacy.clean.core.util.handlers.DispenserFilledBucketHandler;
import mortvana.legacy.clean.core.util.helpers.ItemHelper;
import mortvana.legacy.clean.core.util.helpers.MiscHelper;
import mortvana.legacy.clean.core.util.item.BucketFluxGear;
import mortvana.legacy.clean.core.util.item.ItemArmorRF;
import mortvana.legacy.clean.fluxgeartweaks.block.BlockTimeyWimey;
import mortvana.legacy.clean.fluxgeartweaks.block.tileentity.TileTimeyWimey;
import mortvana.legacy.clean.morttech.block.BlockCrank;
import mortvana.legacy.dependent.firstdegree.mortech.block.BlockWoodmill;
import mortvana.legacy.clean.morttech.block.itemblock.*;
import mortvana.legacy.clean.morttech.block.tile.WoodmillLogic;
import mortvana.legacy.dependent.firstdegree.mortech.item.WrenchSonic;
import mortvana.legacy.dependent.seconddegree.paintedstone.recipe.RecipePaintbrush;
import mortvana.legacy.clean.projectfluxgear.item.ItemPrototypeSonicWrench;
import mortvana.legacy.clean.thaumicrevelations.block.*;
import mortvana.legacy.clean.thaumicrevelations.block.tile.TileWitor;
import mortvana.legacy.clean.thaumicrevelations.entity.EntityPurity;
import mortvana.legacy.clean.thaumicrevelations.item.*;
import mortvana.legacy.clean.thaumicrevelations.util.WardenicChargeHelper;
import mortvana.legacy.clean.weirdscience.block.BlockBloodDonation;
import mortvana.legacy.clean.weirdscience.block.BlockFuelBurner;
import mortvana.legacy.clean.weirdscience.block.fluid.BlockFluidAcid;
import mortvana.legacy.clean.weirdscience.block.tile.TileEntityGunpowderDynamo;
import mortvana.legacy.clean.weirdscience.util.ContentRegistry;
import mortvana.legacy.clean.weirdscience.util.block.fluid.BlockFluidClassicWS;
import mortvana.legacy.clean.weirdscience.util.block.fluid.BlockFluidReactive;
import mortvana.legacy.clean.weirdscience.util.chemistry.ReactionSpec;
import mortvana.legacy.dependent.firstdegree.projectfluxgear.block.BlockGravelOreAux;
import mortvana.legacy.dependent.firstdegree.projectfluxgear.block.BlockGravelOreMain;
import mortvana.legacy.dependent.firstdegree.projectfluxgear.block.fluid.BlockFluidGhastTears;
import mortvana.legacy.dependent.firstdegree.thaumicrevelations.entity.EntityFleshProjectile;
import mortvana.legacy.dependent.firstdegree.weirdscience.block.*;
import mortvana.legacy.dependent.seconddegree.morttech.block.BlockMachine;
import mortvana.legacy.dependent.seconddegree.morttech.block.BlockMortTechOre;
import mortvana.legacy.dependent.seconddegree.morttech.item.DebuggingSpork;
import mortvana.legacy.dependent.seconddegree.projectfluxgear.block.BlockPlant;
import mortvana.legacy.dependent.seconddegree.thaumicrevelations.block.BlockWardenicQuartzSlab;
import mortvana.legacy.dependent.seconddegree.thaumicrevelations.block.BlockWardenicQuartzStairs;
import mortvana.legacy.dependent.seconddegree.thaumicrevelations.entity.EntityFleshGolem;
import mortvana.legacy.dependent.seconddegree.thaumicrevelations.item.ItemWardenicArmor;
import mortvana.legacy.errored.projectfluxgear.*;
import mortvana.legacy.errored.thaumicrevelations.ItemFocusIllumination;
import mortvana.legacy.errored.weirdscience.BlockBloodDyanmo;
import mortvana.legacy.errored.weirdscience.BlockFluidSmog;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.*;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

import static mortvana.projectfluxgear.library.FluxGearLibrary.WARDEN;

public class FluxGearContent implements IFuelHandler {

	public void preInit() {
        loadBlocks();
		loadStones();
        loadFluids();
        loadItems();
		preInitMortTech();
    }

    public void init() {
        registerBlocks();
        //initWhatever();
        //addOreDict();
	    metalCraftingRecipes();
        machineCraftingRecipes();
        otherCraftingRecipes();
	    furnaceRecipes();
	    initTimeyWimey();
    }

    public void postInit() {
        aluminiumArc();
        //modIntegration;
        //postIntermodCommunication();
	    if (FluxGearConfig.doTweaks) {
		    removal();
		    addition();
	    }
	    if (LoadedHelper.isThaumcraftLoaded && LoadedHelper.isEnderIOLoaded && LoadedHelper.isRotaryCraftLoaded && LoadedHelper.isExtraUtilitiesLoaded && LoadedHelper.isChromatiCraftLoaded && LoadedHelper.isMagicBeesLoaded && LoadedHelper.isProjectRedIllumination && FluxGearConfig.thaumicTorch) {
		    loadTimeyWimey();
	    }
    }

	//@FormerClass(TinkersTweaks.removal)
	public static void smelteryGears() {
		//if (FluxGearConfig.tinkersGears) {
		//}
	}

	//@FormerClass(OreDictTweaks.addition)
	public static void aluminiumArc_Tweaks() {
		MeltedDashboardCore.logger.info("*Dalek Voice* Activating the Aluminium Arc!!!!");
		if (FluxGearConfig.aluminiumArc) {
			if (LoadedHelper.isProjectRedExploration) {
				CraftingHelper.registerOreDict(TweakItemRegistry.prMarbleSmooth, "marble", "blockMarble");
				CraftingHelper.registerOreDict(TweakItemRegistry.prBasaltSmooth, "basalt", "blockBasalt");
			}
		}
	}

	//@FormerClass(CoFHTweaks.addition)
	public static void ptMithral() {
		/*if (FluxGearConfig.ptMithralSmelter) {
			CraftingHelper.registerInductionSmelting(silverOre, platinumDust, platinumIngot, mithralOre, 10, 2000);
		}
		if (FluxGearConfig.ptMithralTransposer) {

		}*/
	}

	public void removal() {
		if (FluxGearConfig.tweakJABBA && LoadedHelper.isJABBALoaded) {
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("JABBA", "barrel"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to be cheaper");
		}
		if (LoadedHelper.isThermalExpansionLoaded) {
			if (FluxGearConfig.harderActivatorRecipe) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ThermalExpansion", "Device"), WILD, 2), TweakHelper.TweakReason.CHANGED, "Recipe requires steel", "to make this a later game item");
			}
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), WILD, 513), TweakHelper.TweakReason.NOTE, "Recipe edited to be", "ore dictionary.");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), WILD, 0), TweakHelper.TweakReason.NOTE, "Recipe edited to be", "ore dictionary");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), WILD, 1), TweakHelper.TweakReason.NOTE, "Recipe edited to be", "ore dictionary");

		}
		if (LoadedHelper.isMagicalCropsLoaded) {
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_ModMagicSeedsPlatinum"), WILD, 0));
		}
		if (LoadedHelper.isBigReactorsLoaded) {
			if (FluxGearConfig.steelReactorCasings && LoadedHelper.isSteelRegistered) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe requires steel to", "make the mod later game");
			}
			if (FluxGearConfig.glassFuelRods) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "YelloriumFuelRod"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe requires hardened glass", "to make the mod later game");
			}
			if (FluxGearConfig.fourReactorGlass) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe output quadrupled", "to offset the expensive glass");
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), WILD, 1), TweakHelper.TweakReason.CHANGED, "Recipe output quadrupled", "to offset the expensive glass");
			}
			if (FluxGearConfig.mortvanaReactors&& !OreDictionary.getOres("ingotHSLA").isEmpty() && !OreDictionary.getOres("dustLead").isEmpty() && !OreDictionary.getOres("ingotTungsten").isEmpty() && !OreDictionary.getOres("element_Be").isEmpty() && LoadedHelper.isRedstoneArsenalLoaded && LoadedHelper.isLaserCraftLoaded) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe requires many resources", "to make the mod later game");
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "YelloriumFuelRod"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe requires hardened glass", "to make the mod later game");
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe output quadrupled", "to offset the expensive glass");
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), WILD, 1), TweakHelper.TweakReason.CHANGED, "Recipe output quadrupled", "to offset the expensive glass");
			}
		}
		if (LoadedHelper.isOpenBlocksLoaded && FluxGearConfig.redPowerBreakersAndDeployers) {
			if (GameRegistry.findItem("OpenBlocks", "blockbreaker") != null) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("OpenBlocks", "blockbreaker"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to bring back", "RP2-like recipes");
			}
			if (GameRegistry.findItem("OpenBlocks", "blockPlacer") != null) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("OpenBlocks", "blockPlacer"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to bring back", "RP2-like recipes");
			}
		}
		if (FluxGearConfig.nerfEnderQuarry && LoadedHelper.isExtraUtilitiesLoaded) {
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ExtraUtilities", "enderQuarry"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to", "balance its power");
		}
		if (LoadedHelper.isMekanismLoaded) {
			if (FluxGearConfig.harderDisassemblerRecipe) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("Mekanism", "AtomicDisassembler"), WILD, 100), TweakHelper.TweakReason.CHANGED, "Changed to ensure", "balance with all other tools");
			}
			if (FluxGearConfig.nerfMiner) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("Mekanism", "MachineBlock"), WILD, 4), TweakHelper.TweakReason.CHANGED, "Changed to balance better", "with other quarry-like blocks");
			}
		}
		if (LoadedHelper.isStevesFactoryLoaded && LoadedHelper.isAppliedEnergisticsLoaded && FluxGearConfig.tweakSFM) {
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockMachineManagerName"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableRelayName"), WILD, 8), TweakHelper.TweakReason.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableInputName"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableOutputName"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableIntakeName"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableIntakeName"), WILD, 8), TweakHelper.TweakReason.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableBreakerName"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to be AE-like", "to balance the vast capabilities", "of this normally cheap mod");
		}
		TweakHelper.removeRecipes();
	}

	public void addition() {
		if (LoadedHelper.isJABBALoaded) {
			if (FluxGearConfig.tweakJABBA) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("JABBA", "barrel"), 1, 0), "wsw", "wpw", "www", 'w', "logWood", 's', "slabWood", 'p', Items.paper));
			}
			if (FluxGearConfig.enderChestJABBA) {
				OreDictionary.registerOre("transdimBlock", new ItemStack(GameRegistry.findItem("EnderStorage", "enderChest"), 1, Short.MAX_VALUE));
			}
		}
		if (FluxGearConfig.aluminiumArc) {
			if (LoadedHelper.isProjectRedExploration) {
				OreDictionary.registerOre("blockMarble", new ItemStack(GameRegistry.findItem("ProjRed|Exploration", "projectred.exploration.stone"), 1, 0));
				OreDictionary.registerOre("marble", new ItemStack(GameRegistry.findItem("ProjRed|Exploration", "projectred.exploration.stone"), 1, 0));
				OreDictionary.registerOre("blockBasalt", new ItemStack(GameRegistry.findItem("ProjRed|Exploration", "projectred.exploration.stone"), 1, 3));
				OreDictionary.registerOre("basalt", new ItemStack(GameRegistry.findItem("ProjRed|Exploration", "projectred.exploration.stone"), 1, 3));
			}
			/*if (LoadedHelper.isArtificeLoaded) {
				//TODO: Add Stuff
			}*/
			if (LoadedHelper.isThermalExpansionLoaded) {
				OreDictionary.registerOre("dustWood", new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 512));
				OreDictionary.registerOre("itemSlag", new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 514));
			}
		}
		if (LoadedHelper.isThermalExpansionLoaded) {
			if (FluxGearConfig.harderActivatorRecipe) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "Device"), 1, 2), "scs", "tpt", "sns", 's', OreDictionary.getOres("ingotSteel").isEmpty() ? "ingotInvar" : "ingotSteel", 'p', Blocks.piston, 't', "gearTin", 'c', Blocks.chest, 'n', new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"))));
			}
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 513), "sss", "s s", "sss", 's', "dustWood"));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), 1, 0), "dustWood", "itemSlag", "slimeball"));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), 1, 1), "dustWood", "itemSlag", "slimeball", "dustBlaze"));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), 1, 1), "dustWood", "itemSlag", "magmaCream"));
		}
		if (LoadedHelper.isMagicalCropsLoaded) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_ModMagicSeedsPlatinum"), 1, 0), "nep", "ese", "pen", 'n', new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_ModMagicSeedsNickel"), 1, 0), 'e', new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_MagicEssence"), 1, 4), 'p', "ingotPlatinum", 's', Items.wheat_seeds));
			//if (LoadedHelper.isThermalExpansionLoaded){
			//ItemStack pulv = thermalexpansion.block.machine.BlockMachine.pulverizer;
			//TweakHelper.addTweakedTooltip(pulv.itemID, pulv.getItemDamage(), TweakHelper.TweakReason.ADDED, "Recipes for Essence Ore and", "Nether Essence Ore");
			//thermalexpansion.util.crafting.PulverizerManager.addRecipe(2400, OreDictionary.getOres("oreMCropsEssence").get(0), new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_MagicEssence"), 8, 0), new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_CropEssence"), 1, 0), 5);
			//thermalexpansion.util.crafting.PulverizerManager.addRecipe(2400, OreDictionary.getOres("oreMCropsNetherEssence").get(0), new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_MagicEssence"), 12, 0), new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_CropEssence"), 1, 0), 10);
			//}
		}
		if (LoadedHelper.isBigReactorsLoaded) {
			if (FluxGearConfig.steelReactorCasings && LoadedHelper.isSteelRegistered) {
				// Reactor Casing
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 4, 0), "scs", "cuc", "scs", 's', "ingotSteel", 'c', "ingotGraphite", 'u', "ingotYellorium"));
			}
			if (FluxGearConfig.glassFuelRods && FluxGearConfig.steelReactorCasings && LoadedHelper.isSteelRegistered) {
				// Yellorium Fuel Rod
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "YelloriumFuelRod"), 1, 0), "csc", "gug", "csc", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 1, 0), 's', "ingotSteel", 'u', "ingotYellorium", 'g', "ingotGraphite"));
			}
			if (FluxGearConfig.glassFuelRods && (!FluxGearConfig.steelReactorCasings || (FluxGearConfig.steelReactorCasings && !LoadedHelper.isSteelRegistered))) {
				// Yellorium Fuel Rod
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "YelloriumFuelRod"), 1, 0), "cic", "gug", "cic", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 1, 0), 's', "ingotIron", 'u', "ingotYellorium", 'g', "ingotGraphite"));
			}
			if (FluxGearConfig.fourReactorGlass) {
				// Reactor Glass
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 4), " g ", "gcg", " g ", 'g', "glassHardened", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 4), " g ", "gcg", " g ", 'g', "glassReinforced", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 4), " g ", "gcg", " g ", 'g', "blockGlassHardened", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 8), " g ", "gcg", " g ", 'g', "blockGlassReinforced", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 16), " g ", "gcg", " g ", 'g', "blockGlassResonant", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0)));
				// Turbine Glass
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 4, 1), " g ", "gcg", " g ", 'g', "glassHardened", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRTurbinePart"), 1, 0)));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 4, 1), " g ", "gcg", " g ", 'g', "glassReinforced", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRTurbinePart"), 1, 0)));
			}
			if (FluxGearConfig.mortvanaReactors && !OreDictionary.getOres("ingotHSLA").isEmpty() && !OreDictionary.getOres("dustLead").isEmpty() && !OreDictionary.getOres("ingotTungsten").isEmpty() && !OreDictionary.getOres("element_Be").isEmpty() /*&& Loader.isModLoaded("RedstoneArsenal")*/) {
				// Yellorium Fuel Rod
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "YelloriumFuelRod"), 1, 0), "csc", "gwg", "csc", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 1, 0), 's', "ingotHSLA", 'w', "ingotTungsten", 'g', "ingotGraphite"));
				/// Reactor Glass
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 4), "bgb", "gcg", "bgb", 'g', "glassHardened", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0), 'b', "element_Be"));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 4), "bgb", "gcg", "bgb", 'g', "glassReinforced", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0), 'b', "element_Be"));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 4), "bgb", "gcg", "bgb", 'g', "blockGlassHardened", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0), 'b', "element_Be"));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 8), "bgb", "gcg", "bgb", 'g', "blockGlassReinforced", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0), 'b', "element_Be"));
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 16), "bgb", "gcg", "bgb", 'g', "blockGlassResonant", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 1, 0), 'b', "element_Be"));
				// Turbine Glass
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 4, 1), " g ", "gcg", " g ", 'g', "glassHardened", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRTurbinePart"), 1, 0)));
				// Reactor Casing
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 4, 0), "sws", "cbc", "sws", 's', "ingotHSLA", 'c', "ingotGraphite", 'b', "element_Be", 'w', "ingotTungsten"));
			}
		}
		if (LoadedHelper.isOpenBlocksLoaded && FluxGearConfig.redPowerBreakersAndDeployers) {
			if (GameRegistry.findItem("OpenBlocks", "blockbreaker") != null) {
				// Block Breaker
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("OpenBlocks", "blockbreaker"), 1, 0), "cac", "cpc", "crc", 'c', "cobblestone", 'a', Items.iron_pickaxe, 'p', Blocks.piston, 'r', Items.redstone));
			}
			if (GameRegistry.findItem("OpenBlocks", "blockPlacer") != null) {
				// Block Placer
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("OpenBlocks", "blockPlacer"), 1, 0), "csc", "cpc", "crc", 'c', "cobblestone", 's', Blocks.chest, 'p', Blocks.piston, 'r', Items.redstone));
			}
		}
		if (LoadedHelper.isExtraUtilitiesLoaded) {
			if (FluxGearConfig.nerfEnderQuarry) {
				// Ender Mining Mechanism
				GameRegistry.addRecipe(new ShapedOreRecipe(enderMiningCore, "pws", "dbd", "cec", 'p', new ItemStack(GameRegistry.findItem("ExtraUtilities", "destructionpickaxe"), 1, 0), 'w', new ItemStack(GameRegistry.findItem("ExtraUtilities", "builderswand"), 1, 0), 's', new ItemStack(GameRegistry.findItem("ExtraUtilities", "erosionShovel"), 1, 0), 'd', new ItemStack(GameRegistry.findItem("ExtraUtilities", "dark_portal")) , 'b', Blocks.iron_bars, 'c', enderCompCore, 'e', LoadedHelper.isThermalExpansionLoaded ? new ItemStack(GameRegistry.findItem("ThermalExpansion", "Cell"), 1, 4) : enderCompCore));
				// Ender Quarry
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("ExtraUtilities", "enderQuarry"), 1, 0), "oqo", "cdc", "plp", 'o', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 1), 'q', new ItemStack(GameRegistry.findItem("ExtraUtilities", "cobblestone_compressed"), 1, 11), 'c', enderCompCore, 'd', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 12), 'l', enderMiningCore, 'p', GameRegistry.findItem("ExtraUtilities", "enderThermicPump") == null ? new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 12) : GameRegistry.findItem("ExtraUtilities", "enderThermicPump")));
				// Ender Computation Matrix
				GameRegistry.addRecipe(new ShapedOreRecipe(enderCompCore, "wcw", "nen", "wcw", 'w', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 8), 'c', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 12), 'e', Items.ender_eye, 'n', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 11)));
			}
		}
		if (LoadedHelper.isMekanismLoaded) {
			if (FluxGearConfig.nerfMiner) {
				// Digital Miner
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("Mekanism", "MachineBlock"), 1, 4), "aca", "lrl", "mdm", 'a', "alloyUltimate", 'c', "circuitUltimate", 'l', new ItemStack(GameRegistry.findItem("Mekanism", "MachineBlock"), 1, 15), 'r', GameRegistry.findItem("Mekanism", "Robit"), 'm', new ItemStack(GameRegistry.findItem("Mekanism", "BasicBlock"), 1, 8), 'd', GameRegistry.findItem("Mekanism", "AtomicDisassembler")));
			}
			if (FluxGearConfig.harderDisassemblerRecipe) {
				// Atomic Disassembler
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("Mekanism", "AtomicDisassembler"), 1, 100), "ata", "ada", " o ", 'd', disassemblyCore, 't', GameRegistry.findItem("Mekanism", "EnergyTablet"), 'o', "ingotRefinedObsidian", 'a', "alloyUltimate"));

				// Disassembly Core
				GameRegistry.addRecipe(new ShapedOreRecipe(disassemblyCore, "tst", "eae", "tst", 't', GameRegistry.findItem("Mekanism", "TeleportationCore"), 's', GameRegistry.findItem("Mekanism", "SpeedUpgrade"), 'e', GameRegistry.findItem("Mekanism", "EnergyUpgrade"), 'a', "alloyUltimate"));
			}

			if (FluxGearConfig.mekCrushingHelper) {
				// Mekanism Crusher Recipes
				ArrayList<ItemStack> oreIn, dustOut;
				if (!(oreIn = OreDictionary.getOres("oreAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					out.stackSize++;
					for (ItemStack i : oreIn)
						RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("oreVinteum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustVinteum")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					out.stackSize++;
					for (ItemStack i : oreIn)
						RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("oreYellorite")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					out.stackSize++;
					for (ItemStack i : oreIn)
						RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("oreRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					out.stackSize++;
					for (ItemStack i : oreIn)
						RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("oreSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					out.stackSize++;
					for (ItemStack i : oreIn)
						RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("oreOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					out.stackSize++;
					for (ItemStack i : oreIn)
						RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("ingotYellorium")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("gemRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("gemSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("gemGreenSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustGreenSapphire")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("gemOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("ingotPlatinum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustPlatinum")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("ingotElectrum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustElectrum")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
				if (!(oreIn = OreDictionary.getOres("ingotInvar")).isEmpty() && !(dustOut = OreDictionary.getOres("dustInvar")).isEmpty()) {
					ItemStack out = dustOut.get(0).copy();
					for (ItemStack i : oreIn)
						RecipeHelper.addCrusherRecipe(i.copy(), out);
				}
			}
			RecipeHelper.addCrusherRecipe(new ItemStack(Items.bone), new ItemStack(Items.dye, 5, 15));
			RecipeHelper.addCrusherRecipe(new ItemStack(Blocks.red_flower), new ItemStack(Items.dye, 2, 1));
			RecipeHelper.addCrusherRecipe(new ItemStack(Blocks.yellow_flower), new ItemStack(Items.dye, 2, 11));
		}
		if (LoadedHelper.isStevesFactoryLoaded && LoadedHelper.isAppliedEnergisticsLoaded && FluxGearConfig.tweakSFM) {
			// Machine Inventory Manager
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockMachineManagerName"), 1, 0), "iii", "apf", "sis", 'p', multicoreProcessor, 'a', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 44), 'f', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 43), 'i', LoadedHelper.isSteelRegistered ? "ingotSteel" : "ingotIron", 's', "stone"));
			// Inventory Cable
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 8, 0), "ipi", "gfg", "ipi", 'f', "dustFluix", 'g', "glass", 'i', LoadedHelper.isSteelRegistered ? "ingotSteel" : "ingotIron", 'p', Blocks.light_weighted_pressure_plate));
			// Advanced Inventory Relay
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableRelayName"), 1, 8), "fbf", "brb", "fbf", 'b', "blockLapis", 'r', new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableRelayName"), 1, 0), 'f', "dustFluix"));
			// Redston Receiver
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableInputName"), 1, 0), " r ", "rcr", " r ", 'r', "dustRedstone", 'c', new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 1, 0)));
			// Redstone Emitter
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableOutputName"), 1, 0), "rer", "rcr", "rrr", 'r', "dustRedstone", 'e', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiPart"), 1, 280), 'c', new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 1, 0)));
			// Multicore Processor
			GameRegistry.addRecipe(new ShapedOreRecipe(multicoreProcessor, "cfc", "fsf", "cfc", 'c', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 23), 's', "itemSilicon", 'f', "dustFluix"));
			// Item Valve
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableIntakeName"), 1, 0), new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 1, 0), Blocks.hopper, Blocks.dropper, new ItemStack(GameRegistry.findItem("appliedenergistics2", "BlockInterface"), 1, 0)));
			// Rapid Item Valve
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableIntakeName"), 1, 8), new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableIntakeName"), 1, 0), new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 23)));
			// Block Gate
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableBreakerName"), 1, 0), new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 1, 0), Items.iron_pickaxe, Blocks.dispenser, new ItemStack(GameRegistry.findItem("appliedenergistics2", "BlockInterface"), 1, 0)));
		}
		if (FluxGearConfig.wheatToSeeds) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.wheat_seeds, FluxGearConfig.wheatToSeedsAmount, 0), Items.wheat));
		}
		if (LoadedHelper.isRotaryCraftLoaded && LoadedHelper.isCopperRegistered && LoadedHelper.isNickelRegistered && LoadedHelper.isDamascusSteelRegistered && LoadedHelper.isInvarRegistered && FluxGearConfig.blastMechanism) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(dustCupronickel, "dustCopper", "dustCopper", "dustCopper", "dustNickel"));
			GameRegistry.addSmelting(dustCupronickel, ingotCupronickel, 0.0F);
			GameRegistry.addRecipe(new ShapedOreRecipe(heatingCoil, "ccc", "rsr", "ccc", 'c', "ingotCupronickel", 'r', "dustRedstone", 's', LoadedHelper.isSiliconRegistered ? "itemSilicon" : Items.quartz));
			GameRegistry.addRecipe(new ShapedOreRecipe(damascusSteelFrame, "did", "pcp", "did", 'd', "ingotDamascusSteel", 'i', "ingotInvar", 'p', ceramicPanel, 'c', heatingCoil));
			GameRegistry.addSmelting(ceramicPanelRaw, ceramicPanel, 1.0F);
			GameRegistry.addRecipe(new ShapedOreRecipe(ceramicPanelRaw, "ccc", "cnc", "ccc", 'c', ceramicMix, 'n', "nuggetCupronickel"));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(itemMaterial, 8, 13), Blocks.sand, Blocks.gravel, Items.clay_ball, Items.clay_ball, Items.clay_ball, Items.clay_ball, Items.clay_ball, Items.clay_ball, Items.clay_ball));
			ItemHelper.addReverseStorageRecipe(nuggetCupronickel, "ingotCupronickel");
			ItemHelper.addStorageRecipe(ingotCupronickel, "nuggetCupronickel");
			GameRegistry.addRecipe(new ShapedOreRecipe(dummyRotaryTitanium, "pcp", "dcd", "pcp", 'p', ceramicPanel, 'd', damascusSteelFrame, 'c', heatingCoil));
		}
	}

	/* Start MortTech */
	public void preInitMortTech() {
		componentsTab = new TabTools("MTComponents");
		toolsTab = new TabTools("MTTools");
		machineTab = new TabTools("MTMachines");

		basicOre = new BlockMortTechOre("BasicOre", "chalcopyrite_ore", "cassiterite_ore", "argentite_ore", "galena_ore", "sphalerite_ore", "bismuthinite_ore", "garnierite_ore", "chromite_ore", "cobaltite_ore", "wolframite_ore");
		gemOre = new BlockMortTechOre("GemOre", "dioptase_ore", "ruby_ore", "sapphire_ore", "green_sapphire_ore", "pink_sapphire_ore", "purple_sapphire_ore", "topaz_ore", "tanzanite_ore", "pyrope_ore", "malachite_ore", "olivine_ore", "super_sekrit_ore");
		complexOre = new BlockMortTechOre("ComplexOre", "bauxite_ore", "monazite_ore", "chalcocite_ore", "millerite_ore", "bornite_ore", "limonite_ore", "magnetite_ore", "hematite_ore", "pyrolusite_ore", "molybdenite_ore", "cooprite_ore", "ilmenite_ore", "tetrahedrite_ore", "tennatite_ore", "pentalandite_ore", "nierdermayrite_ore");

		GameRegistry.registerBlock(basicOre, ItemBlockBasicOre.class, "MortTech" + basicOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(gemOre, ItemBlockGemOre.class, "MortTech" + gemOre.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(complexOre, ItemBlockComplexOre.class, "MortTech" + complexOre.getUnlocalizedName().substring(5));

		// GameRegistry.addRecipe(new ItemStack(snakeBag, 1, 0), "sss", "sss", "sss", 's', snake);

		itemDust = new Item().setCreativeTab(componentsTab).setUnlocalizedName("dust");
		GameRegistry.registerItem(itemDust, "dust");

		//itemCrafting = new ItemCrafting().setCreativeTab(componentsTab).setUnlocalizedName("crafting");
		//GameRegistry.registerItem(itemCrafting, "crafting");

		wrenchSonic = new WrenchSonic().setCreativeTab(componentsTab).setUnlocalizedName("wrenchSonic");
		GameRegistry.registerItem(wrenchSonic, "sonicWrench");

		debugSpork = new DebuggingSpork().setCreativeTab(toolsTab).setUnlocalizedName("debuggingSpork");
		GameRegistry.registerItem(debugSpork, "debuggingSpork");

		GameRegistry.registerBlock(woodmill, "woodmill");
		GameRegistry.registerBlock(crank, "crank");
		LanguageRegistry.addName(woodmill, "Woodmill");
		LanguageRegistry.addName(crank, "Crank");

		GameRegistry.registerTileEntity(WoodmillLogic.class, tileWoodmillID);

		tileMachine = new BlockMachine(Material.iron).setResistance(5.0f).setCreativeTab(machineTab).setHardness(2.5f);
		GameRegistry.registerBlock(tileMachine, "machine");

		tileCrank = new BlockCrank(Material.wood).setResistance(5.0f).setCreativeTab(machineTab).setHardness(2.3f).setUnlocalizedName("Crank");
		GameRegistry.registerBlock(tileCrank, "tempCrank");

		tileMachineTier0 = new BlockWoodmill().setResistance(5.0f).setCreativeTab(machineTab).setHardness(2.3f).setUnlocalizedName("Woodmill");
		GameRegistry.registerBlock(tileMachineTier0, "Tier0Machine");

		LanguageRegistry.addName(tileMachineTier0, "Woodmill");
		LanguageRegistry.addName(itemDust, "Sawdust");
		LanguageRegistry.addName(tileCrank, "Crank");
		LanguageRegistry.addName(wrenchSonic, "Sonic Wrench");

		OreDictionary.registerOre("dustSawdust", itemDust);

		GameRegistry.registerCustomItemStack("dustSawdust", new ItemStack(itemDust, 1, 0));
		GameRegistry.registerCustomItemStack("dustCoal", new ItemStack(itemDust, 1, 1));
		GameRegistry.registerCustomItemStack("dustCharcoal", new ItemStack(itemDust, 1, 2));
		GameRegistry.registerCustomItemStack("dustCarbide", new ItemStack(itemDust, 1, 3));
		GameRegistry.registerCustomItemStack("dustFlint", new ItemStack(itemDust, 1, 4));
		GameRegistry.registerCustomItemStack("dustClay", new ItemStack(itemDust, 1, 5));
		GameRegistry.registerCustomItemStack("dustCeramic", new ItemStack(itemDust, 1, 6));
		GameRegistry.registerCustomItemStack("dustIron", new ItemStack(itemDust, 1, 7));
		GameRegistry.registerCustomItemStack("dustGold", new ItemStack(itemDust, 1, 8));
		GameRegistry.registerCustomItemStack("dustBismuth", new ItemStack(itemDust, 1, 9));
		GameRegistry.registerCustomItemStack("dustNigelite", new ItemStack(itemDust, 1, 10));
		GameRegistry.registerCustomItemStack("dustCopper", new ItemStack(itemDust, 1, 11));
		GameRegistry.registerCustomItemStack("dustTin", new ItemStack(itemDust, 1, 12));
		GameRegistry.registerCustomItemStack("dustZinc", new ItemStack(itemDust, 1, 13));
		GameRegistry.registerCustomItemStack("dustAluminium", new ItemStack(itemDust, 1, 14));
		GameRegistry.registerCustomItemStack("dustLead", new ItemStack(itemDust, 1, 15));
		GameRegistry.registerCustomItemStack("dustSilver", new ItemStack(itemDust, 1, 16));
		GameRegistry.registerCustomItemStack("dustChromium", new ItemStack(itemDust, 1, 17));
		GameRegistry.registerCustomItemStack("dustTitanium", new ItemStack(itemDust, 1, 18));
		GameRegistry.registerCustomItemStack("dustTungsten", new ItemStack(itemDust, 1, 19));
		GameRegistry.registerCustomItemStack("dustPalladium", new ItemStack(itemDust, 1, 20));
		GameRegistry.registerCustomItemStack("dustPlatinum", new ItemStack(itemDust, 1, 21));
		GameRegistry.registerCustomItemStack("dustNickel", new ItemStack(itemDust, 1, 22));
		GameRegistry.registerCustomItemStack("dustManganese", new ItemStack(itemDust, 1, 23));
		GameRegistry.registerCustomItemStack("dustCobalt", new ItemStack(itemDust, 1, 24));
		GameRegistry.registerCustomItemStack("dustGallium", new ItemStack(itemDust, 1, 25));
		GameRegistry.registerCustomItemStack("dustIndium", new ItemStack(itemDust, 1, 26));
		GameRegistry.registerCustomItemStack("dustCadmium", new ItemStack(itemDust, 1, 27));
		GameRegistry.registerCustomItemStack("dustTellerium", new ItemStack(itemDust, 1, 28));
		GameRegistry.registerCustomItemStack("dustVandium", new ItemStack(itemDust, 1, 29));
		GameRegistry.registerCustomItemStack("dustEmerald", new ItemStack(itemDust, 1, 30));
		GameRegistry.registerCustomItemStack("dustLapis", new ItemStack(itemDust, 1, 31));
		GameRegistry.registerCustomItemStack("dustDiamond", new ItemStack(itemDust, 1, 32));
		GameRegistry.registerCustomItemStack("dustObsidian", new ItemStack(itemDust, 1, 33));
		GameRegistry.registerCustomItemStack("dustStone", new ItemStack(itemDust, 1, 34));
		GameRegistry.registerCustomItemStack("dustCorundum", new ItemStack(itemDust, 1, 35));
		GameRegistry.registerCustomItemStack("dustRuby", new ItemStack(itemDust, 1, 36));
		GameRegistry.registerCustomItemStack("dustSapphire", new ItemStack(itemDust, 1, 37));
		GameRegistry.registerCustomItemStack("dustGreenSapphire", new ItemStack(itemDust, 1, 38));
		GameRegistry.registerCustomItemStack("dustPinkSapphire", new ItemStack(itemDust, 1, 39));
		GameRegistry.registerCustomItemStack("dustPurpleSapphire", new ItemStack(itemDust, 1, 40));
		GameRegistry.registerCustomItemStack("dustEmery", new ItemStack(itemDust, 1, 41));
		GameRegistry.registerCustomItemStack("dustDioptase", new ItemStack(itemDust, 1, 42));
		GameRegistry.registerCustomItemStack("dustPyrope", new ItemStack(itemDust, 1, 43));
		GameRegistry.registerCustomItemStack("dustApatite", new ItemStack(itemDust, 1, 44));
		GameRegistry.registerCustomItemStack("dustAmethyst", new ItemStack(itemDust, 1, 45));
		GameRegistry.registerCustomItemStack("dustTopaz", new ItemStack(itemDust, 1, 46));
		GameRegistry.registerCustomItemStack("dustTanzanite", new ItemStack(itemDust, 1, 47));
		GameRegistry.registerCustomItemStack("dustMalachite", new ItemStack(itemDust, 1, 48));
		GameRegistry.registerCustomItemStack("dustNetherQuartz", new ItemStack(itemDust, 1, 49));
		GameRegistry.registerCustomItemStack("dustCertusQuartz", new ItemStack(itemDust, 1, 50));
		GameRegistry.registerCustomItemStack("dustPeridot", new ItemStack(itemDust, 1, 51));
		GameRegistry.registerCustomItemStack("dustMystic", new ItemStack(itemDust, 1, 52));
		GameRegistry.registerCustomItemStack("dustBoron", new ItemStack(itemDust, 1, 53));
		GameRegistry.registerCustomItemStack("dustPhosphorus", new ItemStack(itemDust, 1, 54));
		GameRegistry.registerCustomItemStack("dustSulfur", new ItemStack(itemDust, 1, 55));
		GameRegistry.registerCustomItemStack("dustMilk", new ItemStack(itemDust, 1, 56));
		GameRegistry.registerCustomItemStack("dustUranium", new ItemStack(itemDust, 1, 57));
		GameRegistry.registerCustomItemStack("dustThorium", new ItemStack(itemDust, 1, 58));
		GameRegistry.registerCustomItemStack("dustPlutonium", new ItemStack(itemDust, 1, 59));
		GameRegistry.registerCustomItemStack("dustNeptunium", new ItemStack(itemDust, 1, 60));
		GameRegistry.registerCustomItemStack("dustProactanium", new ItemStack(itemDust, 1, 61));
		GameRegistry.registerCustomItemStack("dustActanium", new ItemStack(itemDust, 1, 62));
		GameRegistry.registerCustomItemStack("dustFerrousMetal", new ItemStack(itemDust, 1, 63));
		GameRegistry.registerCustomItemStack("dustRadioactive", new ItemStack(itemDust, 1, 64));
		GameRegistry.registerCustomItemStack("dustEnderPearl", new ItemStack(itemDust, 1, 65));
		GameRegistry.registerCustomItemStack("dustEndstone", new ItemStack(itemDust, 1, 66));
		GameRegistry.registerCustomItemStack("dustSaltpeter", new ItemStack(itemDust, 1, 67));
		GameRegistry.registerCustomItemStack("dustPlastic", new ItemStack(itemDust, 1, 68));

        //Tier 1
        GameRegistry.registerCustomItemStack("machineAlloyFurnace", new ItemStack(tileMachine, 1, 0));
        GameRegistry.registerCustomItemStack("machineGrinder", new ItemStack(tileMachine, 1, 1));

        //Tier 2
        GameRegistry.registerCustomItemStack("machineCrucible", new ItemStack(tileMachine, 1, 2));
        GameRegistry.registerCustomItemStack("machineCasting", new ItemStack(tileMachine, 1, 3));
		GameRegistry.registerCustomItemStack("machineWoodmill", new ItemStack(tileMachine, 1, 4));
        GameRegistry.registerCustomItemStack("machineStoneAnvil", new ItemStack(tileMachine, 1, 5));
        GameRegistry.registerCustomItemStack("machineBellows", new ItemStack(tileMachine, 1, 6));
        GameRegistry.registerCustomItemStack("machineCoolingBasin", new ItemStack(tileMachine, 1, 7));
        GameRegistry.registerCustomItemStack("machineMetalBarrel", new ItemStack(tileMachine, 1, 8));
        GameRegistry.registerCustomItemStack("machineSolarBrick", new ItemStack(tileMachine, 1, 9));
        GameRegistry.registerCustomItemStack("machineSolarMirror", new ItemStack(tileMachine, 1, 10));
        GameRegistry.registerCustomItemStack("machineSolarLens", new ItemStack(tileMachine, 1, 11));
        GameRegistry.registerCustomItemStack("machineChipperShaft", new ItemStack(tileMachine, 1, 12));
        GameRegistry.registerCustomItemStack("machineChipperCase", new ItemStack(tileMachine, 1, 13));
        GameRegistry.registerCustomItemStack("machineChipperHead", new ItemStack(tileMachine, 1, 14));
        GameRegistry.registerCustomItemStack("machineWaterWheel", new ItemStack(tileMachine, 1, 15));
        GameRegistry.registerCustomItemStack("machineSimpleWindmill", new ItemStack(tileMachine, 1, 16));
        GameRegistry.registerCustomItemStack("machineRollingBase", new ItemStack(tileMachine, 1, 17));
        GameRegistry.registerCustomItemStack("machineRolling", new ItemStack(tileMachine, 1, 18));

        //Tier 3
        GameRegistry.registerCustomItemStack("machineServoControl", new ItemStack(tileMachine, 1, 20));
        GameRegistry.registerCustomItemStack("machineServoSwitch", new ItemStack(tileMachine, 1, 21));
        GameRegistry.registerCustomItemStack("machineRotaryInterface", new ItemStack(tileMachine, 1, 22));
        GameRegistry.registerCustomItemStack("machineThermalFurnace", new ItemStack(tileMachine, 1, 23));


        //PRE-MULTIPART GameRegistry.registerCustomItemStack("machineCrank", new ItemStack(tileMachine, 1, 2));
        //PRE-MULTIPART GameRegistry.registerCustomItemStack("machineHinge", new ItemStack(tileMachine, 1, 12));
        //PRE-MULTIPART GameRegistry.registerCustomItemStack("machineServoRail", new ItemStack(tileMachine, 1, 28));
        //PRE-MULTIPART GameRegistry.registerCustomItemStack("machineCeramicPipe", new ItemStack(tileMachine, 1, 29));
        //GameRegistry.registerCustomItemStack("machineDCMotor", new ItemStack(tileMachine, 1, 19));
        //GameRegistry.registerCustomItemStack("machineRopeBridge", new ItemStack(tileMachine, 1, 22));
        //GameRegistry.registerCustomItemStack("machineLadderControl", new ItemStack(tileMachine, 1, 23));
        //GameRegistry.registerCustomItemStack("machineRopeLadder", new ItemStack(tileMachine, 1, 24));

        //TODO: Refactor commented areas for Tier changes
        GameRegistry.registerCustomItemStack("machineWireCut", new ItemStack(tileMachine, 1, 8));
        GameRegistry.registerCustomItemStack("machineAssemblyBase", new ItemStack(tileMachine, 1, 10));
        GameRegistry.registerCustomItemStack("machineAssembler", new ItemStack(tileMachine, 1, 11));
        GameRegistry.registerCustomItemStack("machineFurnace", new ItemStack(tileMachine, 1, 17));
        GameRegistry.registerCustomItemStack("machineRGrinder", new ItemStack(tileMachine, 1, 18));
        

		OreDictionary.registerOre("dustSawdust", new ItemStack(itemDust, 1, 0));
		OreDictionary.registerOre("dustWood", new ItemStack(itemDust, 1, 0));
		OreDictionary.registerOre("pulpWood", new ItemStack(itemDust, 1, 0));
		OreDictionary.registerOre("dustCoal", new ItemStack(itemDust, 1, 1));
		OreDictionary.registerOre("dustCharcoal", new ItemStack(itemDust, 1, 2));
		OreDictionary.registerOre("dustCarbide", new ItemStack(itemDust, 1, 3));
		OreDictionary.registerOre("dustCarbon", new ItemStack(itemDust, 1, 3));
		OreDictionary.registerOre("dustFlint", new ItemStack(itemDust, 1, 4));
		OreDictionary.registerOre("dustClay", new ItemStack(itemDust, 1, 5));
		OreDictionary.registerOre("dustCeramic", new ItemStack(itemDust, 1, 6));
		OreDictionary.registerOre("dustIron", new ItemStack(itemDust, 1, 7));
		OreDictionary.registerOre("dustGold", new ItemStack(itemDust, 1, 8));
		OreDictionary.registerOre("dustBismuth", new ItemStack(itemDust, 1, 9));
		OreDictionary.registerOre("dustNigelite", new ItemStack(itemDust, 1, 10));
		OreDictionary.registerOre("dustCopper", new ItemStack(itemDust, 1, 11));
		OreDictionary.registerOre("dustTin", new ItemStack(itemDust, 1, 12));
		OreDictionary.registerOre("dustZinc", new ItemStack(itemDust, 1, 13));
		OreDictionary.registerOre("dustAluminium", new ItemStack(itemDust, 1, 14));
		OreDictionary.registerOre("dustLead", new ItemStack(itemDust, 1, 15));
		OreDictionary.registerOre("dustSilver", new ItemStack(itemDust, 1, 16));
		OreDictionary.registerOre("dustChromium", new ItemStack(itemDust, 1, 17));
		OreDictionary.registerOre("dustTitanium", new ItemStack(itemDust, 1, 18));
		OreDictionary.registerOre("dustTungsten", new ItemStack(itemDust, 1, 19));
		OreDictionary.registerOre("dustPalladium", new ItemStack(itemDust, 1, 20));
		OreDictionary.registerOre("dustPlatinum", new ItemStack(itemDust, 1, 21));
		OreDictionary.registerOre("dustNickel", new ItemStack(itemDust, 1, 22));
		OreDictionary.registerOre("dustManganese", new ItemStack(itemDust, 1, 23));
		OreDictionary.registerOre("dustCobalt", new ItemStack(itemDust, 1, 24));
		OreDictionary.registerOre("dustGallium", new ItemStack(itemDust, 1, 25));
		OreDictionary.registerOre("dustIndium", new ItemStack(itemDust, 1, 26));
		OreDictionary.registerOre("dustCadmium", new ItemStack(itemDust, 1, 27));
		OreDictionary.registerOre("dustTellerium", new ItemStack(itemDust, 1, 28));
		OreDictionary.registerOre("dustVandium", new ItemStack(itemDust, 1, 29));
		OreDictionary.registerOre("dustEmerald", new ItemStack(itemDust, 1, 30));
		OreDictionary.registerOre("dustLapis", new ItemStack(itemDust, 1, 31));
		OreDictionary.registerOre("dustDiamond", new ItemStack(itemDust, 1, 32));
		OreDictionary.registerOre("dustObsidian", new ItemStack(itemDust, 1, 33));
		OreDictionary.registerOre("dustStone", new ItemStack(itemDust, 1, 34));
		OreDictionary.registerOre("dustCorundum", new ItemStack(itemDust, 1, 35));
		OreDictionary.registerOre("dustRuby", new ItemStack(itemDust, 1, 36));
		OreDictionary.registerOre("dustSapphire", new ItemStack(itemDust, 1, 37));
		OreDictionary.registerOre("dustGreenSapphire", new ItemStack(itemDust, 1, 38));
		OreDictionary.registerOre("dustPinkSapphire", new ItemStack(itemDust, 1, 39));
		OreDictionary.registerOre("dustPurpleSapphire", new ItemStack(itemDust, 1, 40));
		OreDictionary.registerOre("dustEmery", new ItemStack(itemDust, 1, 41));
		OreDictionary.registerOre("dustDioptase", new ItemStack(itemDust, 1, 42));
		OreDictionary.registerOre("dustPyrope", new ItemStack(itemDust, 1, 43));
		OreDictionary.registerOre("dustApatite", new ItemStack(itemDust, 1, 44));
		OreDictionary.registerOre("dustAmethyst", new ItemStack(itemDust, 1, 45));
		OreDictionary.registerOre("dustTopaz", new ItemStack(itemDust, 1, 46));
		OreDictionary.registerOre("dustTanzanite", new ItemStack(itemDust, 1, 47));
		OreDictionary.registerOre("dustMalachite", new ItemStack(itemDust, 1, 48));
		OreDictionary.registerOre("dustNetherQuartz", new ItemStack(itemDust, 1, 49));
		OreDictionary.registerOre("dustCertusQuartz", new ItemStack(itemDust, 1, 50));
		OreDictionary.registerOre("dustPeridot", new ItemStack(itemDust, 1, 51));
		OreDictionary.registerOre("dustMystic", new ItemStack(itemDust, 1, 52));
		OreDictionary.registerOre("dustBoron", new ItemStack(itemDust, 1, 53));
		OreDictionary.registerOre("dustPhosphorus", new ItemStack(itemDust, 1, 54));
		OreDictionary.registerOre("dustSulfur", new ItemStack(itemDust, 1, 55));
		OreDictionary.registerOre("dustMilk", new ItemStack(itemDust, 1, 56));
		OreDictionary.registerOre("dustUranium", new ItemStack(itemDust, 1, 57));
		OreDictionary.registerOre("dustThorium", new ItemStack(itemDust, 1, 58));
		OreDictionary.registerOre("dustPlutonium", new ItemStack(itemDust, 1, 59));
		OreDictionary.registerOre("dustNeptunium", new ItemStack(itemDust, 1, 60));
		OreDictionary.registerOre("dustProactanium", new ItemStack(itemDust, 1, 61));
		OreDictionary.registerOre("dustActanium", new ItemStack(itemDust, 1, 62));
		OreDictionary.registerOre("dustFerrousMetal", new ItemStack(itemDust, 1, 63));
		OreDictionary.registerOre("dustRadioactive", new ItemStack(itemDust, 1, 64));
		OreDictionary.registerOre("dustEnderPearl", new ItemStack(itemDust, 1, 65));
		OreDictionary.registerOre("dustEndstone", new ItemStack(itemDust, 1, 66));
		OreDictionary.registerOre("dustSaltpeter", new ItemStack(itemDust, 1, 67));
		OreDictionary.registerOre("dustPlastic", new ItemStack(itemDust, 1, 68));

		basicOre.setHarvestLevel("pickaxe", 1, 0);
		basicOre.setHarvestLevel("pickaxe", 1, 1);
		basicOre.setHarvestLevel("pickaxe", 2, 2);
		basicOre.setHarvestLevel("pickaxe", 2, 3);
		basicOre.setHarvestLevel("pickaxe", 1, 4);
		basicOre.setHarvestLevel("pickaxe", 1, 5);
		basicOre.setHarvestLevel("pickaxe", 1, 6);
		basicOre.setHarvestLevel("pickaxe", 2, 7);
		basicOre.setHarvestLevel("pickaxe", 2, 8);
		basicOre.setHarvestLevel("pickaxe", 2, 9);

		gemOre.setHarvestLevel("pickaxe", 2, 0);
		gemOre.setHarvestLevel("pickaxe", 2, 1);
		gemOre.setHarvestLevel("pickaxe", 2, 2);
		gemOre.setHarvestLevel("pickaxe", 2, 3);
		gemOre.setHarvestLevel("pickaxe", 2, 4);
		gemOre.setHarvestLevel("pickaxe", 2, 5);
		gemOre.setHarvestLevel("pickaxe", 2, 6);
		gemOre.setHarvestLevel("pickaxe", 2, 7);
		gemOre.setHarvestLevel("pickaxe", 1, 8);
		gemOre.setHarvestLevel("pickaxe", 2, 9);
		gemOre.setHarvestLevel("pickaxe", 2, 10);

		complexOre.setHarvestLevel("pickaxe", 1, 0);
		complexOre.setHarvestLevel("pickaxe", 1, 1);
		complexOre.setHarvestLevel("pickaxe", 1, 2);
		complexOre.setHarvestLevel("pickaxe", 2, 3);
		complexOre.setHarvestLevel("pickaxe", 2, 4);
		complexOre.setHarvestLevel("pickaxe", 2, 5);
		complexOre.setHarvestLevel("pickaxe", 2, 6);
		complexOre.setHarvestLevel("pickaxe", 2, 7);
		complexOre.setHarvestLevel("pickaxe", 2, 8);
		complexOre.setHarvestLevel("pickaxe", 2, 9);
		complexOre.setHarvestLevel("pickaxe", 2, 10);
		complexOre.setHarvestLevel("pickaxe", 3, 11);
		complexOre.setHarvestLevel("pickaxe", 2, 12);
		complexOre.setHarvestLevel("pickaxe", 2, 13);
		complexOre.setHarvestLevel("pickaxe", 2, 14);
		complexOre.setHarvestLevel("pickaxe", 2, 15);

		LanguageRegistry.addName(new ItemStack(basicOre, 1, 0), "Chalcopyrite Ore"); //TODO: ADD CHALCOCITE
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 1), "Cassiterite Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 2), "Acanthite Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 3), "Galena Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 4), "Sphalerite Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 5), "Bismuthinite Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 6), "Garnierite Ore"); //TODO: ADD MILLERITE
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 7), "Chromite Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 8), "Cobaltite Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 9), "Wolframite Ore");

		LanguageRegistry.addName(new ItemStack(gemOre, 1, 0), "Dioptase Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 1), "Ruby Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 2), "Sapphire Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 3), "Green Sapphire Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 4), "Pink Sapphire Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 5), "Purple Sapphire Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 6), "Topaz Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 7), "Tanzanite Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 8), "Pyrope Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 9), "Malachite Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 10), "Uranite Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 11), "Olivine Ore");
		LanguageRegistry.addName(new ItemStack(gemOre, 1, 12), "Soarynium Ore");

		LanguageRegistry.addName(new ItemStack(complexOre, 1, 0), "Bauxite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 1), "Monazite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 2), "Chalcocite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 3), "Millerite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 4), "Bornite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 5), "Limonite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 6), "Magnetite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 7), "Hematite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 8), "Pyrolusite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 9), "Molybdenite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 10), "Cooprite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 11), "Ilmenite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 12), "Tetrahedrite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 13), "Tennatite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 14), "Pentalandite Ore");
		LanguageRegistry.addName(new ItemStack(complexOre, 1, 15), "Nierdermayrite Ore");

		honeyComb = new Item().setUnlocalizedName("mortvanaBeesHoneyComb");
		GameRegistry.registerItem(honeyComb, "mortvanaBeesHoneyComb");
	}

	public static Block basicOre;
	public static Block gemOre;
	public static Block complexOre;

	public static Block tileMachineTier0;

	public static String tileWoodmillID = "tileWoodmill";

	/* Creative Tabs */
	public static TabTools componentsTab;
	public static TabTools machineTab;
	public static TabTools toolsTab;

	/* Blocks */
	public static Block tileMachine;
	public static Block tileCrank;

	/* Items */
	public static Item itemDust;
	public static Item wrenchSonic;
	public static Item debugSpork;
	public static Item honeyComb;

	public static Block woodmill;
	public static Block crank;


	/* End MortTech */

	public static BlockFluxGear invisibleMultiblock;
	public static BlockFluxGear particleGenerator;
	public static BlockFluxGear energyPylon;
	public static BlockFluxGear energyStorageCore;

	public static final CreativeTabs tabMaterials = new FluxGearCreativeTab("PFG-Materials", "fluxgear.materialsTab", FluxGearContent.gemDioptase);
	public static final CreativeTabs tabWorld = new FluxGearCreativeTab("PFG-World", "fluxgear.worldTab", FluxGearContent.oreBauxite);
	public static final CreativeTabs techTab = new FluxGearCreativeTab("PFG-Tech", "fluxgear.techTab", FluxGearContent.toolProtoSonicWrench);
	public static final CreativeTabs generalTab = new FluxGearCreativeTab("PFG-General", "fluxgear.generalTab", new ItemStack(Items.potato));
	public static final CreativeTabs stonesTab = new FluxGearCreativeTab("PFG-Stone", "fluxgear.stoneTab", new ItemStack(Blocks.obsidian));
	//MOAR Tabs?


	/* Thaumic Revelations */
	public static void loadTRStuff() {

		stackExubitura = new ItemStack(FluxGearContent.blockPlant, 1, 9);
		GameRegistry.registerBlock(blockInfusedQuartzNormal, "blockInfusedQuartzNormal");
		GameRegistry.registerBlock(blockInfusedQuartzChiseled, "blockInfusedQuartzChiseled");
		GameRegistry.registerBlock(blockInfusedQuartzPillar, "blockInfusedQuartzPillar");
		GameRegistry.registerBlock(blockInfusedQuartzSlab, "blockInfusedQuartzSlab");
		GameRegistry.registerBlock(blockInfusedQuartzStair, "blockInfusedQuartzStair");
		GameRegistry.registerBlock(blockWitor, "blockWitor");

		GameRegistry.registerTileEntity(TileWitor.class, "tileWitor");

		GameRegistry.registerItem(itemFocusPurity, "itemFocusPurity");
		GameRegistry.registerItem(itemWardenSword, "itemWardenWeapon");
		GameRegistry.registerItem(itemWardenAmulet, "thaumicBauble");
		GameRegistry.registerItem(itemWardenHelm, "itemWardenHelm");
		GameRegistry.registerItem(itemWardenChest, "itemWardenChest");
		GameRegistry.registerItem(itemWardenLegs, "itemWardenLegs");
		GameRegistry.registerItem(itemWardenBoots, "itemWardenBoots");
		//GameRegistry.registerItem(itemLoveRing, "itemLoveRing"); //TODO
		GameRegistry.registerItem(itemWaslieHammer, "itemWaslieHammer");
		GameRegistry.registerItem(itemFocusIllumination, "itemFocusIllumination");

		exubituraPetal = FluxGearContent.itemMaterial.addItem(15000, "exubituraPetal");
		wardenicCrystal = FluxGearContent.itemMaterial.addItem(15001, "wardenicCrystal");
		wardenicQuartz = FluxGearContent.itemMaterial.addItem(15002, "wardenicQuartz");

		EntityRegistry.registerModEntity(EntityPurity.class, "PurityOrb", 0, mortvana.projectfluxgear.core.common.ProjectFluxGear.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityFleshProjectile.class, "ThrownFlesh", 1, mortvana.projectfluxgear.core.common.ProjectFluxGear.instance, 64, 3, true); //TODO
		EntityRegistry.registerGlobalEntityID(EntityFleshGolem.class, "FleshGolem", EntityRegistry.findGlobalUniqueEntityId(), 0xE4A2A9, 0x96452E);

		ThaumcraftApi.registerObjectTag(exubituraPetal, new AspectList().add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(wardenicCrystal, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(wardenicQuartz, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenHelm), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenChest), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenLegs), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenBoots), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenSword), new AspectList().add(Aspect.WEAPON, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));

	}

	public static void loadThaumicRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzNormal), "XX", "XX", 'X', wardenicQuartz);
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzChiseled), "X", "X", 'X', new ItemStack(blockInfusedQuartzSlab));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzPillar, 2), "X", "X", 'X', new ItemStack(blockInfusedQuartzNormal));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzSlab, 6), "XXX", 'X', new ItemStack(blockInfusedQuartzNormal));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzStair, 4), "X  ", "XX ", "XXX", 'X', new ItemStack(blockInfusedQuartzNormal));

		recipeQuartz = ThaumcraftApi.addCrucibleRecipe("QUARTZ", wardenicQuartz, new ItemStack(Items.quartz), new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ELDRITCH, 4));
		recipeCrystal = ThaumcraftApi.addCrucibleRecipe("CRYSTAL", wardenicCrystal, exubituraPetal, new AspectList().add(Aspect.MAGIC, 32).add(Aspect.CRYSTAL, 32));

		recipeWardenHelm = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenHelm, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XXX", "XOX", "   ", 'X', wardenicQuartz, 'O', wardenicCrystal);
		recipeWardenChest = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenChest, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"X X", "XOX", "XXX", 'X', wardenicQuartz, 'O', wardenicCrystal);
		recipeWardenLegs = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenLegs, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XXX", "XOX", "X X", 'X', wardenicQuartz, 'O', wardenicCrystal);
		recipeWardenBoots = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenBoots, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"   ", "XOX", "X X", 'X', wardenicQuartz, 'O', wardenicCrystal);
		recipeWardenSword = ThaumcraftApi.addArcaneCraftingRecipe("WARDENWEAPON", new ItemStack(itemWardenSword, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				" X ", " X ", " O ", 'X', wardenicQuartz, 'O', wardenicCrystal);

		recipeWaslieHammer = ThaumcraftApi.addArcaneCraftingRecipe("WASLIEHAMMER", new ItemStack(itemWaslieHammer, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XXX", "XOX", " I ", 'X', wardenicCrystal, 'O', wardenicQuartz, 'I', new ItemStack(ConfigBlocks.blockMagicalLog));

		recipeFocusIllumination = ThaumcraftApi.addArcaneCraftingRecipe("ILLUMINATION", new ItemStack(itemFocusIllumination, 1), new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50),
				" X ", "XOX", " X ", 'X', new ItemStack(ConfigItems.itemResource, 1, 1), 'O', new ItemStack(ConfigItems.itemFocusFire));

		ResearchCategories.registerCategory("FLUXGEAR", new ResourceLocation("projectfluxgear", "textures/items/wardenamulet.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

		researchTWarden = new FluxGearResearchItem("TREVELATIONS", category, new AspectList(), 0, 0, 0, new ItemStack(itemWardenAmulet)).setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchTWarden.setPages(new ResearchPage("0"));
		researchExubitura = new FluxGearResearchItem("EXUBITURA", category, new AspectList(), 0, -2, 0, new ItemStack(blockExubitura)).setParents("TREVELATIONS").setAutoUnlock().registerResearchItem();
		researchExubitura.setPages(new ResearchPage("0"));
		researchQuartz = new FluxGearResearchItem("QUARTZ", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4), 2, 0, 2, wardenicQuartz).setParents("TREVELATIONS").setRound().registerResearchItem();
		researchQuartz.setPages(new ResearchPage("0"), new ResearchPage(recipeQuartz));
		researchCrystal = new FluxGearResearchItem("CRYSTAL", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4), -2, 0, 2, wardenicCrystal).setParents("TREVELATIONS").setSecondary().registerResearchItem();
		researchCrystal.setPages(new ResearchPage("0"), new ResearchPage(recipeCrystal));
		researchWardenArmor = new FluxGearResearchItem("WARDENARMOR", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4).add(Aspect.ARMOR, 4), 6, 2, 3, new ItemStack(itemWardenChest)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenArmor.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenHelm), new ResearchPage(recipeWardenChest), new ResearchPage(recipeWardenLegs), new ResearchPage(recipeWardenBoots));
		researchWardenWeapon = new FluxGearResearchItem("WARDENWEAPON", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4).add(Aspect.WEAPON, 4), 6, 6, 3, new ItemStack(itemWardenSword)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenWeapon.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenSword));
		researchWaslieHammer = new FluxGearResearchItem("WASLIEHAMMER", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4), 4, 4, 3, new ItemStack(itemWaslieHammer)).setParentsHidden("CRYSTAL", "QUARTZ").setParents("LORE4").setRound().setSpecial().registerResearchItem();
		researchWaslieHammer.setPages(new ResearchPage("0"), new ResearchPage(recipeWaslieHammer));

		researchIllumination = new FluxGearResearchItem("ILLUMINATION", category, new AspectList().add(Aspect.AIR, 2).add(Aspect.FIRE, 2), 0, 4, 1, new ItemStack(itemFocusIllumination)).setRound().setParentsHidden("FOCUSFIRE").registerResearchItem();
		researchIllumination.setPages(new ResearchPage("0"), new ResearchPage(recipeFocusIllumination));

		researchLore1 = new FluxGearResearchItem("LORE1", category, new AspectList().add(WARDEN, 4).add(Aspect.MIND, 4), 0, 2, 3, new ItemStack(itemWardenAmulet)).setParents("TREVELATIONS").setRound().setSpecial().registerResearchItem();
		researchLore1.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"));
		researchLore2 = new FluxGearResearchItem("LORE2", category, new AspectList().add(WARDEN, 4).add(Aspect.MIND, 4), -2, 4, 3, new ItemStack(itemWardenAmulet)).setParents("LORE1").setRound().setSpecial().registerResearchItem();
		researchLore2.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"));
		researchLore3 = new FluxGearResearchItem("LORE3", category, new AspectList().add(WARDEN, 4).add(Aspect.MIND, 4), 0, 6, 3, new ItemStack(itemWardenAmulet)).setParents("LORE2").setRound().setSpecial().registerResearchItem();
		researchLore3.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4"), new ResearchPage("5"));
		researchLore4 = new FluxGearResearchItem("LORE4", category, new AspectList().add(WARDEN, 4).add(Aspect.MIND, 4), 2, 4, 3, new ItemStack(itemWardenAmulet)).setParents("LORE3").setRound().setSpecial().registerResearchItem();
		researchLore4.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"));
	}

	/* Items */
	public static Item itemWardenAmulet = new ItemThaumicBauble();
	public static Item itemWardenSword = new ItemWardenicBlade();
	public static Item itemFocusPurity = new ItemFocusPurity();
	public static Item itemWardenHelm = new ItemWardenicArmor(EnumArmorType.HELMET, "itemWardenHelm", "warden", "fluxgear:wardenhelm");
	public static Item itemWardenChest = new ItemWardenicArmor(EnumArmorType.CHESTPLATE, "itemWardenChest", "warden", "fluxgear:wardenchest");
	public static Item itemWardenLegs = new ItemWardenicArmor(EnumArmorType.PANTS, "itemWardenLegs", "warden", "fluxgear:wardenlegs");
	public static Item itemWardenBoots = new ItemWardenicArmor(EnumArmorType.BOOTS, "itemWardenBoots", "warden", "fluxgear:wardenboots");
	public static Item itemWaslieHammer = new ItemWaslieHammer();
	public static Item itemFocusIllumination = new ItemFocusIllumination();

	/* ItemStacks (Block) */
	public static ItemStack stackExubitura;

	/* ItemStacks (Items) */
	public static ItemStack exubituraPetal;
	public static ItemStack wardenicCrystal;
	public static ItemStack wardenicQuartz;

	public static Block blockExubitura = new BlockPlant(); //TODO: Proper cast
	public static Block blockInfusedQuartzNormal = new BlockWardenicQuartzNormal();
	public static Block blockInfusedQuartzChiseled = new BlockWardenicQuartzChiseled();
	public static Block blockInfusedQuartzPillar = new BlockWardenicQuartzPillar();
	public static Block blockInfusedQuartzSlab = new BlockWardenicQuartzSlab();
	public static Block blockInfusedQuartzStair = new BlockWardenicQuartzStairs();
	public static Block blockWitor = new BlockWitor();

	/* Research */
	public static ResearchItem researchTWarden;
	public static ResearchItem researchExubitura;
	public static ResearchItem researchQuartz;
	public static ResearchItem researchCrystal;
	public static ResearchItem researchWardenArmor;
	public static ResearchItem researchWardenWeapon;
	public static ResearchItem researchWaslieHammer;
	public static ResearchItem researchLore1;
	public static ResearchItem researchLore2;
	public static ResearchItem researchLore3;
	public static ResearchItem researchLore4;
	public static ResearchItem researchIllumination;

	/* Recipes */
	public static CrucibleRecipe recipeQuartz;
	public static CrucibleRecipe recipeCrystal;
	public static ShapedArcaneRecipe recipeWardenHelm;
	public static ShapedArcaneRecipe recipeWardenChest;
	public static ShapedArcaneRecipe recipeWardenLegs;
	public static ShapedArcaneRecipe recipeWardenBoots;
	public static ShapedArcaneRecipe recipeWardenSword;
	public static ShapedArcaneRecipe recipeWaslieHammer;
	public static ShapedArcaneRecipe recipeFocusIllumination;

	public static ResearchItem researchTorch;
	public static AspectList torchInfusionAspects;
	public static ItemStack[] torchInfusionComponents;
	public static ItemArmor.ArmorMaterial materialWarden = EnumHelper.addArmorMaterial("WARDEN", 50, new int[] {3, 8, 6, 3}, 0);
	public static String category;

	//TODO: Generalize this for future uses *cough* Alchemic Tools *cough*
	public static final ThaumicAugmentBase UPGRADE_WARDEN = new ThaumicAugmentExubitor(WARDEN);
	public static final ThaumicAugmentBase UPGRADE_FIRE = new ThaumicAugmentIgnis(Aspect.FIRE);
	public static final ThaumicAugmentBase UPGRADE_ARMOR = new ThaumicAugmentTutamen(Aspect.ARMOR);
	public static final ThaumicAugmentBase UPGRADE_WATER = new ThaumicAugmentAqua(Aspect.WATER);
	public static final ThaumicAugmentBase UPGRADE_AIR = new ThaumicAugmentAer(Aspect.AIR);
	public static final ThaumicAugmentBase UPGRADE_EARTH = new ThaumicAugmentTerra(Aspect.EARTH);
	public static final ThaumicAugmentBase UPGRADE_HEAL = new ThaumicAugmentSano(Aspect.HEAL);

	public void initWardenUpgrades() {
		WardenicChargeHelper.addUpgrade(UPGRADE_WARDEN);
		WardenicChargeHelper.addUpgrade(UPGRADE_FIRE);
		WardenicChargeHelper.addUpgrade(UPGRADE_ARMOR);
		WardenicChargeHelper.addUpgrade(UPGRADE_WATER);
		WardenicChargeHelper.addUpgrade(UPGRADE_AIR);
		WardenicChargeHelper.addUpgrade(UPGRADE_EARTH);
		WardenicChargeHelper.addUpgrade(UPGRADE_HEAL);
	}

	public void loadTimeyWimey() {
		torchInfusionComponents =  new ItemStack[] { new ItemStack(GameRegistry.findItem("MagicBees", "jellyBabies")), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_gearunits"), 1, 15), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_borecraft"), 1, 15), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_machine"), 1, 103), new ItemStack(GameRegistry.findItem("ChromatiCraft", "chromaticraft_item_placer"), 1, 9), new ItemStack(GameRegistry.findItem("ProjRed|Illumination", "projectred.illumination.cagelamp2")), new ItemStack(GameRegistry.findItem("Thaumcraft", "blockMetalDevice"), 1, 3), new ItemStack(GameRegistry.findItem("ThermalExpansion", "Frame"), 1, 9), new ItemStack(GameRegistry.findItem("EnderIO", "itemMaterial"), 1, 8), new ItemStack(GameRegistry.findItem("BigReactors", "BRMetalBlock"), 1, 4), FluxGearContent.timeyWimeyCarboard, FluxGearContent.timeyWimeyCarboard, new ItemStack(Items.clock), new ItemStack(Items.clock), new ItemStack(Items.clock), new ItemStack(Items.clock) };
		torchInfusionAspects = new AspectList().add(Aspect.ELDRITCH, 16).add(Aspect.MECHANISM, 16).add(Aspect.ENERGY, 24).add(Aspect.ARMOR, 8).add(Aspect.AURA, 8).add(Aspect.HARVEST, 16).add(Aspect.LIGHT, 8).add(ThaumicRevelations.tempus, 32);
		researchTorch = new FluxGearResearchItem("TIMEYWIMEY", "ELDRITCH", new AspectList().add(Aspect.ELDRITCH, 16).add(Aspect.MECHANISM, 16).add(Aspect.ENERGY, 24).add(Aspect.ARMOR, 8).add(Aspect.AURA, 8).add(Aspect.HARVEST, 16).add(Aspect.LIGHT, 8).add(ThaumicRevelations.tempus, 32), -5, 5, 3, new ItemStack(FluxGearContent.timeyWimeyTorch)).setSpecial().setParents("ADVALCHEMYFURNACE", "OUTERREV").setConcealed().registerResearchItem();
		researchTorch.setPages(new ResearchPage("0"), new ResearchPage(ThaumcraftApi.addInfusionCraftingRecipe("TIMEYWIMEY", new ItemStack(FluxGearContent.timeyWimeyTorch), 10, torchInfusionAspects, new ItemStack(GameRegistry.findItem("ExtraUtilities", "magnumTorch")), torchInfusionComponents)));
		ThaumcraftApi.addInfusionCraftingRecipe("TIMEYWIMEY", new ItemStack(FluxGearContent.timeyWimeyTorch), 10, torchInfusionAspects, new ItemStack(GameRegistry.findItem("ExtraUtilities", "magnumTorch")), torchInfusionComponents);
	}

	// TR Innerclasses...

	public static class FluxGearResearchItem extends ResearchItem {

		public int warp = 0;

		public FluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ItemStack icon) {
			super(key, category, tags, column, row, complexity, icon);
		}

		public FluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ResourceLocation icon) {
			super(key, category, tags, column, row, complexity, icon);
		}

		public void setWarp(int warp) {
			this.warp = warp;
		}


		@Override
		@SideOnly(Side.CLIENT)
		public String getName() {
			return StatCollector.translateToLocal("fluxgearresearch." + key + ".name");
		}

		@Override
		@SideOnly(Side.CLIENT)
		public String getText() {
			return (FluxGearConfig.useThaumicTooltips ? StatCollector.translateToLocal(getPrefix()) : "") + StatCollector.translateToLocal("fluxgearresearch." + key + ".lore");
		}

		String getPrefix() {
			return "[PFG] ";
		}

		@Override
		public ResearchItem setPages(ResearchPage... pages) {
			for (ResearchPage page : pages) {
				if (page.type == ResearchPage.PageType.TEXT) {
					page.text = "fluxgearresearch.page." + key + "." + page.text + ".text";
				}
			}
			checkInfusion(true, pages);
			return super.setPages(pages);
		}

		public ResearchItem setPages(boolean checkInfusion, ResearchPage... pages) {
			for (ResearchPage page : pages) {
				if (page.type == ResearchPage.PageType.TEXT) {
					page.text = "fluxgearresearch.page." + key + "." + page.text + ".text";
				}
			}
			checkInfusion(checkInfusion, pages);
			return super.setPages(pages);
		}

		public void checkInfusion(boolean checkInfusion, ResearchPage... pages) {
			for (ResearchPage page : pages) {
				if (checkInfusion && page.type == ResearchPage.PageType.INFUSION_CRAFTING) {
					if (parentsHidden == null || parentsHidden.length == 0)
						parentsHidden = new String[] { "INFUSION" };
					else {
						String[] newParents = new String[parentsHidden.length + 1];
						newParents[0] = "INFUSION";
						for (int i = 0; i < parentsHidden.length; i++) {
							newParents[i + 1] = parentsHidden[i];
						}
						parentsHidden = newParents;
					}
				}
			}
		}

		public void registerResearch() {
			registerResearchItem();
			if (warp != 0) {
				ThaumcraftApi.addWarpToResearch(key, warp);
			}
		}
	}

	public static class VortexFluxGearResearchItem extends FluxGearResearchItem {


		public static List<String> Blacklist = new ArrayList<String>();

		static {
			Blacklist.add("MINILITH");
		}

		public VortexFluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ItemStack icon) {
			super(key, category, tags, column, row, complexity, icon);
			setConcealed();
		}

		public VortexFluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ResourceLocation icon) {
			super(key, category, tags, column, row, complexity, icon);
			setConcealed();
		}

		@Override
		public ResearchItem setPages(ResearchPage... pages) {
			List<String> requirements = parentsHidden == null || parentsHidden.length == 0 ? new ArrayList<String>() : new ArrayList<String>(Arrays.asList(parentsHidden));
			if (!isAutoUnlock())
				for (String categoryStr : ResearchCategories.researchCategories.keySet()) {
					ResearchCategoryList category = ResearchCategories.researchCategories.get(categoryStr);
					for (String tag : category.research.keySet()) {
						ResearchItem research = category.research.get(tag);
						if (research.isLost() || (research.parentsHidden == null && research.parents == null) || research.isVirtual() || research instanceof VortexFluxGearResearchItem || requirements.contains(tag))
							continue;
						if (research.getAspectTriggers() != null || research.getEntityTriggers() != null || research.getItemTriggers() != null) {
							continue;
						}
						if (research.category.equals("FLUXGEAR") || research.category.equals("TT_CATEGORY") || research.category.equals("TX") || /*research.category.equals("rotarycraft") || research.category.equals("chromaticraft") ||*/ research.category.equals("FORBIDDEN") || /*research.category.equals("automagy") ||*/ research.category.equals("MAGICBEES") || research.category.equals("RAILCRAFT") || /*research.category.equals("op style wands") ||*/ research.category.equals("AOBD") || research.category.equals("trevelations") || /*research.category.equals("thaumic horizons") ||*/ research.category.equals("BASICS") || research.category.equals("GOLEMANCY") || research.category.equals("ARTIFICE") || research.category.equals("ALCHEMY") || research.category.equals("THAUMATURGY")) {
							boolean found = false;
							for (String black : Blacklist)
								if (tag.startsWith(black)) {
									found = true;
								}
							if (tag.endsWith("VORTEX"))
								found = true;
							if (found)
								continue;
							requirements.add(tag);
						}
					}
				}
			parentsHidden = requirements.toArray(new String[requirements.size()]);
			return super.setPages(false, pages);
		}

		@Override
		String getPrefix() {
			return super.getPrefix() + ".vortex";
		}
	}

	/* End Thaumic Revelations */






















	//TODO: Underp
	/*public void loadOres() {
		OreInformation.OrePair[] ores = OreEntries.ores;
		for (int q = 0; q < ores.length; q++) {
			OreInformation.OreBlockEntry oreBlock = ores[q].block;
			OreInformation.BasicOreEntry[] metaOres = ores[q].ores;
			GameRegistry.registerBlock(oreBlock.block, oreBlock.itemblock, oreBlock.name);
			for (int i = 0; i < ores[q].ores.length; i++) {
				metaOres[i].itemstack = new ItemStack(oreBlock.block, 1, i);
				oreBlock.block.setHarvestLevel(oreBlock.toolType, metaOres[i].harvestLevel, i);
				ItemHelper.registerWithHandlers(metaOres[i].oreDict, metaOres[i].itemstack);
				if (metaOres[i].oreDict2 != null) {
					ItemHelper.registerWithHandlers(metaOres[i].oreDict2, metaOres[i].itemstack);
				}
			}
		}
	}*/



	//public static BlockContainerFluxGear metaTest; //TODO

    public void loadBlocks() {
	    OreDictionary.registerOre("oreAluminium", oreBauxite);
	    OreDictionary.registerOre("orePalladium", oreBraggite);

	    OreDictionary.registerOre("oreMercury", oreCinnabar);
	    OreDictionary.registerOre("oreUranium", orePitchblende);
	    OreDictionary.registerOre("oreCadmium", oreGreenockite);
	    OreDictionary.registerOre("oreTellurium", oreGaotaiite);
	    OreDictionary.registerOre("oreOsmium", oreOsarsite);
	    OreDictionary.registerOre("oreIndium", oreZnamenskyite);
	    OreDictionary.registerOre("oreGallium", oreGallobeudanite);

	    blockPlant = new BlockPlant();
	    //GameRegistry.registerBlock(blockPlant, ItemBlockPlant.class, "plant"); //TODO
	    
	    timeyWimeyTorch = new BlockTimeyWimey();
	    GameRegistry.registerBlock(timeyWimeyTorch, timeyWimeyTorch.getLocalizedName());
	    GameRegistry.registerTileEntity(TileTimeyWimey.class, "tileTimeyWimeyTorch");

        blockOreMain = BlockInformation.blockOreMain;
        blockOreAux = BlockInformation.blockOreAux;
        blockStorageAux = BlockInformation.blockStorageAux;
        blockAlloyMain = BlockInformation.blockAlloyMain;
        blockAlloyAux = new BlockAlloyAux();
        blockStorageAlch = BlockInformation.blockStorageAlch;
        blockStorageAdv = BlockInformation.blockStorageAdv;
        blockEarthen = BlockInformation.blockEarthen;
        blockPoorOreMain = BlockInformation.blockPoorOreMain;
        blockPoorOreAux = BlockInformation.blockPoorOreAux;
        blockGravelOreMain = new BlockGravelOreMain();
        blockGravelOreAux = new BlockGravelOreAux();
	    blockPlant = new BlockPlant();

        blockFluidGhastTear = new BlockFluidGhastTears();

        GameRegistry.registerBlock(blockStorageAux, FluxGearItemBlock.class, "StorageAux",  BlockInformation.NAMES_STORE_AUX, BlockInformation.RARITY_STORE_AUX, blockStorageAux.getUnlocalizedName());
        blockAntimony = new ItemStack(blockStorageAux, 1, 0);
        blockArsenic = new ItemStack(blockStorageAux, 1, 1);
        blockNeodymium = new ItemStack(blockStorageAux, 1, 2);
        blockTesseractium = new ItemStack(blockStorageAux, 1, 3);
        blockCadmium = new ItemStack(blockStorageAux, 1, 4);
        blockTellurium = new ItemStack(blockStorageAux, 1, 5);
        blockOsmium = new ItemStack(blockStorageAux, 1, 6);
        blockIridium = new ItemStack(blockStorageAux, 1, 7);
        blockIndium = new ItemStack(blockStorageAux, 1, 8);
        blockArsenicalBronze = new ItemStack(blockStorageAux, 1, 9);
        blockAntimonialBronze = new ItemStack(blockStorageAux, 1, 10);
        blockVanadium = new ItemStack(blockStorageAux, 1, 11);
        blockUnobtainium = new ItemStack(blockStorageAux, 1, 12);
        blockDioptase = new ItemStack(blockStorageAux, 1, 13);
        blockPyrope = new ItemStack(blockStorageAux, 1, 14);
        blockMyuvil = new ItemStack(blockStorageAux, 1, 15);

        GameRegistry.registerBlock(blockAlloyMain, FluxGearItemBlock.class, "AlloyMain", BlockInformation.NAMES_ALLOY_MAIN, BlockInformation.RARITY_ALLOY_MAIN, blockAlloyMain.getUnlocalizedName());
        blockBronze = new ItemStack(blockAlloyMain, 1, 0);
        blockBrass = new ItemStack(blockAlloyMain, 1, 1);
        blockInvar = new ItemStack(blockAlloyMain, 1, 2);
        blockBismuthBronze = new ItemStack(blockAlloyMain, 1, 3);
        blockCupronickel = new ItemStack(blockAlloyMain, 1, 4);
        blockAluminiumBrass = new ItemStack(blockAlloyMain, 1, 5);
        blockElectrum = new ItemStack(blockAlloyMain, 1, 6);
        blockDullRedsolder = new ItemStack(blockAlloyMain, 1, 7);
        blockRedsolder = new ItemStack(blockAlloyMain, 1, 8);
        blockHCSteel = new ItemStack(blockAlloyMain, 1, 9);
        blockSteel = new ItemStack(blockAlloyMain, 1, 10);
        blockHSLA = new ItemStack(blockAlloyMain, 1, 11);
        blockStainlessSteel = new ItemStack(blockAlloyMain, 1, 12);
        blockTungstenSteel = new ItemStack(blockAlloyMain, 1, 13);
        blockElectriplatinum = new ItemStack(blockAlloyMain, 1, 14);
        blockMithril = new ItemStack(blockAlloyMain, 1, 15);

        GameRegistry.registerBlock(blockAlloyAux, FluxGearItemBlock.class, "AlloyAux", BlockInformation.NAMES_ALLOY_AUX, BlockInformation.RARITY_ALLOY_AUX, blockAlloyAux.getUnlocalizedName());
        blockTechnomancy = new ItemStack(blockAlloyAux, 1, 0);
        blockResonantTechnomancy = new ItemStack(blockAlloyAux, 1, 1);
        blockTungstenBlazing = new ItemStack(blockAlloyAux, 1, 2);
        blockPlatinumGelid = new ItemStack(blockAlloyAux, 1, 3);
        blockSilverLuminous = new ItemStack(blockAlloyAux, 1, 4);
        blockElectrumFlux = new ItemStack(blockAlloyAux, 1, 5);
        blockMolybdenumResonant = new ItemStack(blockAlloyAux, 1, 6);
        blockChromiumCarbide = new ItemStack(blockAlloyAux, 1, 7);
        blockBismuthBronzeColdfire = new ItemStack(blockAlloyAux, 1, 8);
        blockPyrum = new ItemStack(blockAlloyAux, 1, 9);
        blockGelinium = new ItemStack(blockAlloyAux, 1, 10);
        blockLumium = new ItemStack(blockAlloyAux, 1, 11);
        blockSignalum = new ItemStack(blockAlloyAux, 1, 12);
        blockEnderium = new ItemStack(blockAlloyAux, 1, 13);
        blockCarbonite = new ItemStack(blockAlloyAux, 1, 14);
        blockTherminate = new ItemStack(blockAlloyAux, 1, 15);

        GameRegistry.registerBlock(blockStorageAlch, FluxGearItemBlock.class, "StorageAlch", BlockInformation.NAMES_STORE_ALCH, BlockInformation.RARITY_STORE_ALCH, blockStorageAlch.getUnlocalizedName());
        blockNullmetal = new ItemStack(blockStorageAlch, 1, 0);
        blockIocarbide = new ItemStack(blockStorageAlch, 1, 1);
        blockCryocarbide = new ItemStack(blockStorageAlch, 1, 2);
        blockPyrocarbide = new ItemStack(blockStorageAlch, 1, 3);
        blockTenebride = new ItemStack(blockStorageAlch, 1, 4);
        blockIlluminide = new ItemStack(blockStorageAlch, 1, 5);
        blockZythoferride = new ItemStack(blockStorageAlch, 1, 6);
        blockCrystalFlux = new ItemStack(blockStorageAlch, 1, 7);
        blockLapiquartz = new ItemStack(blockStorageAlch, 1, 8);
        blockRust = new ItemStack(blockStorageAlch, 1, 9);
        blockWhitePointStar = new ItemStack(blockStorageAlch, 1, 10);
        blockVoidInfernoStar = new ItemStack(blockStorageAlch, 1, 11);
        blockSulfur = new ItemStack(blockStorageAlch, 1, 12);
        blockSaltpeter = new ItemStack(blockStorageAlch, 1, 13);
        blockMithrilFlux = new ItemStack(blockStorageAlch, 1, 14);
        blockMithrilTinker = new ItemStack(blockStorageAlch, 1, 15);

        GameRegistry.registerBlock(blockStorageAdv, FluxGearItemBlock.class, "StorageAdv",  BlockInformation.NAMES_STORE_ADV, BlockInformation.RARITY_STORE_ADV, blockStorageAdv.getUnlocalizedName());
        blockThorium = new ItemStack(blockStorageAdv, 1, 0);
        blockUranium235 = new ItemStack(blockStorageAdv, 1, 1);
        blockUranium238 = new ItemStack(blockStorageAdv, 1, 2);
        blockMagnetite = new ItemStack(blockStorageAdv, 1, 3);
        blockNdMagnet = new ItemStack(blockStorageAdv, 1, 4);
        blockFeMagnet = new ItemStack(blockStorageAdv, 1, 5);
        blockMnMagnet = new ItemStack(blockStorageAdv, 1, 6);
        blockCoMagnet = new ItemStack(blockStorageAdv, 1, 7);
        blockNiMagnet = new ItemStack(blockStorageAdv, 1, 8);
        blockInvarMagnet = new ItemStack(blockStorageAdv, 1, 9);
        blockHCSteelMagnet = new ItemStack(blockStorageAdv, 1, 10);
        blockSteelMagnet = new ItemStack(blockStorageAdv, 1, 11);
        blockHSLAMagnet = new ItemStack(blockStorageAdv, 1, 12);
        blockAmber = new ItemStack(blockStorageAdv, 1, 13);
        blockNichrome = new ItemStack(blockStorageAdv, 1, 14);
        blockPolycarbide = new ItemStack(blockStorageAdv, 1, 15);
    }

	public void loadStones() {
		stoneCobble = new BlockDecorStone(Material.rock, CreativeTabs.tabBlock, stoneHardness, stoneResistance, stoneLight, "cobble", Block.soundTypeStone).setUnlocalizedName("mechanicsstoneworks.decorStoneRaw");

		paintbrush = new ItemPaintbrush().setUnlocalizedName("paintedstone.brush");
		GameRegistry.registerItem(paintbrush, "paintbrush");

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(paintbrush), "w", "s", 'w', new ItemStack(Blocks.wool, 1, WILD), 's', "stickWood"));

		for(int i = 0; i < 16; i++) {
			int dyeAmount = 1;

			while (dyeAmount <= 8) {
				Object[] input = new Object[dyeAmount + 1];
				input[0] = paintbrush;
				String dyeType = "dye" + ColorHelper.TITLE_COLOR_NAMES[i];
				switch (dyeAmount) {
					case 8:
						input[8] = dyeType;
					case 7:
						input[7] = dyeType;
					case 6:
						input[6] = dyeType;
					case 5:
						input[5] = dyeType;
					case 4:
						input[4] = dyeType;
					case 3:
						input[3] = dyeType;
					case 2:
						input[2] = dyeType;
					case 1:
						input[1] = dyeType;
					default:
						GameRegistry.addRecipe(new RecipePaintbrush(paintbrush, i + 1, input));
						++dyeAmount;
				}
			}
		}
	}

    public void registerBlocks() {
        //Ore Storage Blocks
        ItemHelper.registerWithHandlers("blockAntimony", blockAntimony); //Mercury is a fluid
        ItemHelper.registerWithHandlers("blockArsenic", blockArsenic); //Uranium will have custom properties
        ItemHelper.registerWithHandlers("blockNeodymium", blockNeodymium);
        ItemHelper.registerWithHandlers("blockTesseractium", blockTesseractium); //Cadmium is next, so :P
        ItemHelper.registerWithHandlers("blockCadmium", blockCadmium);
        ItemHelper.registerWithHandlers("blockTellurium", blockTellurium);
        ItemHelper.registerWithHandlers("blockOsmium", blockOsmium);
        ItemHelper.registerWithHandlers("blockIridium", blockIridium); //Gallium is the Solid-Liquid guy :P
        ItemHelper.registerWithHandlers("blockIndium", blockIndium);
        ItemHelper.registerWithHandlers("blockAntimonialBronze", blockAntimonialBronze);
        ItemHelper.registerWithHandlers("blockArsenicalBronze", blockArsenicalBronze);
        ItemHelper.registerWithHandlers("blockVanadium", blockVanadium);
        ItemHelper.registerWithHandlers("blockUnobtainium", blockUnobtainium);
        ItemHelper.registerWithHandlers("blockDioptase", blockDioptase);
        ItemHelper.registerWithHandlers("blockPyrope", blockPyrope);
        ItemHelper.registerWithHandlers("blockMyuvil", blockMyuvil);

        ItemHelper.registerWithHandlers("blockBronze", blockBronze);
        ItemHelper.registerWithHandlers("blockBrass", blockBrass);
        ItemHelper.registerWithHandlers("blockInvar", blockInvar);
        ItemHelper.registerWithHandlers("blockBismuthBronze", blockBismuthBronze);
        ItemHelper.registerWithHandlers("blockCupronickel", blockCupronickel);
        ItemHelper.registerWithHandlers("blockAluminiumBrass", blockAluminiumBrass);
        ItemHelper.registerWithHandlers("blockElectrum", blockElectrum);
        ItemHelper.registerWithHandlers("blockDullResolder", blockDullRedsolder);
        ItemHelper.registerWithHandlers("blockRedsolder", blockRedsolder);
        ItemHelper.registerWithHandlers("blockSteel", blockHCSteel);
        ItemHelper.registerWithHandlers("blockRefinedSteel", blockSteel);
        ItemHelper.registerWithHandlers("blockHSLA", blockHSLA);
        ItemHelper.registerWithHandlers("blockStainlessSteel", blockStainlessSteel);
        ItemHelper.registerWithHandlers("blockTungstenSteel", blockTungstenSteel);
        ItemHelper.registerWithHandlers("blockElectriplatinum", blockElectriplatinum);
        ItemHelper.registerWithHandlers("blockMithrilBronze", blockMithril);

        ItemHelper.registerWithHandlers("blockTechnomancy", blockTechnomancy);
        ItemHelper.registerWithHandlers("blockResonantTechnomancy", blockResonantTechnomancy);
        ItemHelper.registerWithHandlers("blockTungstenBlazing", blockTungstenBlazing);
        ItemHelper.registerWithHandlers("blockPlatinumGelid", blockPlatinumGelid);
        ItemHelper.registerWithHandlers("blockSilverLuminous", blockSilverLuminous);
        ItemHelper.registerWithHandlers("blockElectrumFlux", blockElectrumFlux);
        ItemHelper.registerWithHandlers("blockMolybdenumResonant", blockMolybdenumResonant);
        ItemHelper.registerWithHandlers("blockChromiumCarbide", blockChromiumCarbide);
        ItemHelper.registerWithHandlers("blockBismuthBronzeColdfire", blockBismuthBronzeColdfire);
        ItemHelper.registerWithHandlers("blockPyrum", blockPyrum);
        ItemHelper.registerWithHandlers("blockGelinium", blockGelinium);
        ItemHelper.registerWithHandlers("blockLumium", blockLumium);
        ItemHelper.registerWithHandlers("blockSignalum", blockSignalum);
        ItemHelper.registerWithHandlers("blockEnderium", blockEnderium);
        ItemHelper.registerWithHandlers("blockCarbonite", blockCarbonite);
        ItemHelper.registerWithHandlers("blockTherminate", blockTherminate);

        ItemHelper.registerWithHandlers("blockNullmetal", blockNullmetal);
        ItemHelper.registerWithHandlers("blockIocarbide", blockIocarbide);
        ItemHelper.registerWithHandlers("blockCryocarbide", blockCryocarbide);
        ItemHelper.registerWithHandlers("blockPyrocarbide", blockPyrocarbide);
        ItemHelper.registerWithHandlers("blockTenebride", blockTenebride);
        ItemHelper.registerWithHandlers("blockIlluminide", blockIlluminide);
        ItemHelper.registerWithHandlers("blockZythoferride", blockZythoferride);
        ItemHelper.registerWithHandlers("blockCrystalFlux", blockCrystalFlux);
        ItemHelper.registerWithHandlers("blockLapiquartz", blockLapiquartz);
        ItemHelper.registerWithHandlers("blockRust", blockRust);
        ItemHelper.registerWithHandlers("blockWhitePointStar", blockWhitePointStar);
        ItemHelper.registerWithHandlers("blockVoidInfernoStar", blockVoidInfernoStar);
        ItemHelper.registerWithHandlers("blockSulfur", blockSulfur);
        ItemHelper.registerWithHandlers("blockSaltpeter", blockSaltpeter);
        ItemHelper.registerWithHandlers("blockMithrilFlux", blockMithrilFlux);
        ItemHelper.registerWithHandlers("blockMithrilTinker", blockMithrilTinker);

        ItemHelper.registerWithHandlers("blockThorium", blockThorium);
        ItemHelper.registerWithHandlers("blockUranium235", blockUranium235);
        ItemHelper.registerWithHandlers("blockUranium238", blockUranium238);
        ItemHelper.registerWithHandlers("blockMagnetite", blockMagnetite);
        ItemHelper.registerWithHandlers("blockNeodymiumMagnetMetal", blockNdMagnet);
        ItemHelper.registerWithHandlers("blockIronMagnetic", blockFeMagnet);
        ItemHelper.registerWithHandlers("blockManganeseMagnetic", blockMnMagnet);
        ItemHelper.registerWithHandlers("blockCobaltMagnetic", blockCoMagnet);
        ItemHelper.registerWithHandlers("blockNickelMagnetic", blockNiMagnet);
        ItemHelper.registerWithHandlers("blockInvarMagnetic", blockInvarMagnet);
        ItemHelper.registerWithHandlers("blockSteelMagnetic", blockHCSteelMagnet);
        ItemHelper.registerWithHandlers("blockRefinedSteelMagnetic", blockSteelMagnet);
        ItemHelper.registerWithHandlers("blockHSLAMagnetic", blockHSLAMagnet);
        ItemHelper.registerWithHandlers("blockAmber", blockAmber);
        ItemHelper.registerWithHandlers("blockNichrome", blockNichrome);
        ItemHelper.registerWithHandlers("blockPolycarbide", blockPolycarbide);
    }

    public void loadFluids() {
        //TODO: Redo
        fluidGhastTear = new Fluid("ghastTear").setLuminosity(3).setDensity(1850).setViscosity(1325).setTemperature(335).setRarity(EnumRarity.rare);
        fluidLye = new Fluid("lye").setLuminosity(0).setDensity(1750).setViscosity(1350).setTemperature(295).setRarity(EnumRarity.common);
        fluidAcid = new Fluid("acid").setLuminosity(0).setDensity(1750).setViscosity(1350).setTemperature(295).setRarity(EnumRarity.common);
        fluidEtchingAcid = new Fluid("etchingAcid").setLuminosity(0).setDensity(1250).setViscosity(3500).setTemperature(295).setRarity(EnumRarity.uncommon);
        fluidSmog = new Fluid("smog").setLuminosity(0).setDensity(1000).setViscosity(500).setTemperature(315).setRarity(EnumRarity.common);
        fluidBlood = new Fluid("blood").setLuminosity(0).setDensity(1350).setViscosity(13850).setTemperature(300).setRarity(EnumRarity.common);
        fluidGelidPyrotheum = new Fluid("gelidPyrotheum").setLuminosity(15).setDensity(1350).setViscosity(2350).setTemperature(295).setRarity(EnumRarity.epic);

        FluidRegistry.registerFluid(fluidGhastTear);
        FluidRegistry.registerFluid(fluidLye);
        FluidRegistry.registerFluid(fluidAcid);
        FluidRegistry.registerFluid(fluidEtchingAcid);
        FluidRegistry.registerFluid(fluidSmog);
        FluidRegistry.registerFluid(fluidBlood);
        FluidRegistry.registerFluid(fluidGelidPyrotheum);
    }

    public void loadItems() {
	    itemMaterial = (FluxGearItem) new FluxGearItem("fluxgear").setUnlocalizedName("material").setCreativeTab(tabMaterials);
        itemBucket = (BucketFluxGear) new BucketFluxGear("fluxgear").setUnlocalizedName("bucket").setCreativeTab(tabMaterials);
        itemFood = (FluxGearItem) new FluxGearItem("fluxgear").setUnlocalizedName("food").setCreativeTab(tabMaterials);
        itemInteractive = (ItemInteractivePFG) new ItemInteractivePFG().setUnlocalizedName("interactive").setCreativeTab(tabMaterials);
        itemProtoSonicWrench = (ItemPrototypeSonicWrench) new ItemPrototypeSonicWrench().setUnlocalizedName("tool", "prototypeSonicWrench");

        //Buckets
        bucketGhastTears = itemBucket.addItem(0, "bucketGhastTears", 1);
        bucketLye = itemBucket.addItem(1, "bucketLye", 0);
        bucketAcid = itemBucket.addItem(2, "bucketAcid", 0);
        bucketEtchingAcid = itemBucket.addItem(3, "bucketEtchingAcid", 0);
        bucketSmog = itemBucket.addItem(4, "bucketSmog", 0);
        bucketBlood = itemBucket.addItem(5, "bucketBlood", 1);
        bucketPyrotheum = itemBucket.addItem(6, "bucketPyrotheum", 2);
        bucketCyrotheum = itemBucket.addItem(7, "bucketCryotheum", 2);
        bucketRedstone = itemBucket.addItem(8, "bucketRedstone", 1);
        bucketGlowstone = itemBucket.addItem(9, "bucketGlowstone", 1);
        bucketEnder = itemBucket.addItem(10, "bucketEnder", 2);
        bucketCarbon = itemBucket.addItem(11, "bucketCarbon", 1);
        bucketGelidPyrotheum = itemBucket.addItem(12, "bucketGelidPyrotheum", 3);
        bucketEssence = itemBucket.addItem(13, "bucketEssence", 1);
        bucketEctoplasm = itemBucket.addItem(14, "bucketEctoplasm", 1);
        bucketRedWater = itemBucket.addItem(15, "bucketRedWater", 2);
        bucketUnstableEctoplasm = itemBucket.addItem(16, "bucketUnstableEctoplasm", 2);
        bucketAcidicEssence = itemBucket.addItem(17, "bucketAcidicEssence", 1);
        bucketMercury = itemBucket.addItem(18, "bucketMercury", 1);
        bucketGallium = itemBucket.addItem(19, "bucketGallium", 1);
        bucketKiernandio = itemBucket.addItem(20, "bucketKiernandio", 2);

        //Food
        foodMelonPan = itemFood.addItem(0, "foodMelonPan"/*3, 0.6F,*/);
        //foodRawPigeon
        //foodCookedPigeon
        //foodGroundPigeon
        //foodPigeonJerky
        //foodPotatoJerky
        //foodMeat
        //foodCongealedBloodChunk
        //foodRawHorse
        //foodCookedHorse
        //foodGarlicRutabagaHorseburger

        //Interactive
        dustThermite = itemInteractive.addOreDictItem(0, "dustThermite");
        coagulantAlum = itemInteractive.addItem(1, "coagulant");

        loadIngots();
        loadDusts();
        loadNuggets();
	    toolProtoSonicWrench = itemProtoSonicWrench.addItem(0, "protoSonicWrench");
        loadGems();
	    loadGears();
    }

    //0-499 Ingots and Gems
    public void loadIngots() {
        ingotCopper = itemMaterial.addOreDictItem(0, "ingotCopper"/*, 0, ColorLibrary.COLOR_MATERIAL_COPPER, "ingotGrayscale"*/);
        ingotTin = itemMaterial.addOreDictItem(1, "ingotTin"/*, 0, ColorLibrary.COLOR_MATERIAL_TIN, "ingotGrayscale"*/);
        ingotLead = itemMaterial.addOreDictItem(2, "ingotLead");
        ingotSilver = itemMaterial.addOreDictItem(3, "ingotSilver");
        ingotNickel = itemMaterial.addOreDictItem(4, "ingotNickel");
        ingotZinc = itemMaterial.addOreDictItem(5, "ingotZinc");
        ingotBismuth = itemMaterial.addOreDictItem(6, "ingotBismuth");
        ingotManganese = itemMaterial.addOreDictItem(7, "ingotManganese");
        ingotAluminium = itemMaterial.addOreDictItem(8, "ingotAluminium");
        ingotPlatinum = itemMaterial.addOreDictItem(9, "ingotPlatinum");
        ingotPalladium = itemMaterial.addOreDictItem(10, "ingotPalladium");
        ingotMolybdenum = itemMaterial.addOreDictItem(11, "ingotMolybdenum");
        ingotCobalt = itemMaterial.addOreDictItem(12, "ingotCobalt", "ingotNaturalCobalt");
        ingotTungsten = itemMaterial.addOreDictItem(13, "ingotTungsten");
        ingotTitanium = itemMaterial.addOreDictItem(14, "ingotTitanium");
        ingotChromium = itemMaterial.addOreDictItem(15, "ingotChromium");
        //* Arsenic
        //* Antimony
        ingotNeodymium = itemMaterial.addOreDictItem(18, "ingotNeodymium");
        ingotTesseractium = itemMaterial.addOreDictItem(19, "ingotTesseractium");
        ingotCadmium = itemMaterial.addOreDictItem(20, "ingotCadmium");
        ingotTellurium = itemMaterial.addOreDictItem(21, "ingotTellurium");
        ingotOsmium = itemMaterial.addOreDictItem(22, "ingotOsmium");
        ingotIridium = itemMaterial.addOreDictItem(23, "ingotIridium");
        ingotIndium = itemMaterial.addOreDictItem(24, "ingotIndium");
        ingotAntimonialBronze = itemMaterial.addOreDictItem(25, "ingotAntimonialBronze");
        ingotArsenicalBronze = itemMaterial.addOreDictItem(26, "ingotArsenicalBronze");
        ingotVanadium = itemMaterial.addOreDictItem(27, "ingotVanadium");
        ingotUnobtainium = itemMaterial.addOreDictItem(28, "ingotUnobtainium");
        gemDioptase = itemMaterial.addOreDictItem(29, "gemDioptase");
        gemPyrope = itemMaterial.addOreDictItem(30, "gemPyrope");
        //* Myuvil
        ingotBronze = itemMaterial.addOreDictItem(32, "ingotBronze");
        ingotBrass = itemMaterial.addOreDictItem(33, "ingotBrass");
        ingotInvar = itemMaterial.addOreDictItem(34, "ingotInvar");
        ingotBismuthBronze = itemMaterial.addOreDictItem(35, "ingotBismuthBronze");
        ingotCupronickel = itemMaterial.addOreDictItem(36, "ingotCupronickel");
        ingotAluminiumBrass = itemMaterial.addOreDictItem(37, "ingotAluminiumBrass");
        ingotElectrum = itemMaterial.addOreDictItem(38, "ingotElectrum");
        ingotDullRedsolder = itemMaterial.addOreDictItem(39, "ingotDullRedsolder");
        ingotRedsolder = itemMaterial.addOreDictItem(40, "ingotRedsolder");
        ingotHCSteel = itemMaterial.addOreDictItem(41, "ingotHighCarbonSteel", "ingotSteel");
        ingotSteel = itemMaterial.addOreDictItem(42, "ingotSteel", "ingotRefinedSteel");
        ingotHSLA = itemMaterial.addOreDictItem(43, "ingotHSLA");
        ingotStainlessSteel = itemMaterial.addOreDictItem(44, "ingotStainlessSteel");
        ingotTungstenSteel = itemMaterial.addOreDictItem(45, "ingotTungstenSteel");
        ingotEletriplatinum = itemMaterial.addOreDictItem(46, "ingotElectriplatinum");
        ingotMithril = itemMaterial.addOreDictItem(47, "ingotMithril", "ingotMitrilBronze");
        ingotTechnomancer = itemMaterial.addOreDictItem(48, "ingotTechnomancer");
        ingotTechnomancerResonant = itemMaterial.addOreDictItem(49, "ingotTechnomancerResonant");
        ingotTungstenBlazing = itemMaterial.addOreDictItem(50, "ingotTungstenBlazing");
        ingotPlatinumGelid = itemMaterial.addOreDictItem(51, "ingotPlatinumGelid");
        ingotSilverLuminous = itemMaterial.addOreDictItem(52, "ingotSilverLuminous");
        ingotElectrumFlux = itemMaterial.addOreDictItem(53, "ingotElectrumFlux");
        ingotMolybdenumResonant = itemMaterial.addOreDictItem(54, "ingotMolybdenumResonant");
        ingotChromiumCarbide = itemMaterial.addOreDictItem(55, "ingotChromiumCarbide");
        ingotBismuthBronzeColdfire = itemMaterial.addOreDictItem(56, "ingotBismuthBronzeColdfire");
        ingotPyrum = itemMaterial.addOreDictItem(57, "ingotPyrum");
        ingotGelinium = itemMaterial.addOreDictItem(58, "ingotGelinium");
        ingotLumium = itemMaterial.addOreDictItem(59, "ingotLumium");
        ingotSignalum = itemMaterial.addOreDictItem(60, "ingotSignalum");
        ingotEnderium = itemMaterial.addOreDictItem(61, "ingotEnderium");
        ingotCarbonite = itemMaterial.addOreDictItem(62, "ingotCarbonite");
        ingotTherminate = itemMaterial.addOreDictItem(63, "ingotTherminate");
        algotNullmetal = itemMaterial.addOreDictItem(64, "algotNullmetal");
        algotIocarbide = itemMaterial.addOreDictItem(65, "algotIocarbide");
        algotCryocarbide = itemMaterial.addOreDictItem(66, "algotCryocarbide");
        algotPyrocarbide = itemMaterial.addOreDictItem(67, "algotPyrocarbide");
        algotTenebride = itemMaterial.addOreDictItem(68, "algotTenebride");
        algotIlluminide = itemMaterial.addOreDictItem(69, "algotIlluminide");
        algotZythoferride = itemMaterial.addOreDictItem(70, "algotZythoferride");
        gemCrystalFlux = itemMaterial.addOreDictItem(71, "gemCrystalFlux");
        //* Lapiquartz
        //* Rust
        //* Sulfur
        //* Saltpeter
        ingotMithrilFlux = itemMaterial.addOreDictItem(76, "ingotMithrilFlux");
        ingotMithrilTinker = itemMaterial.addOreDictItem(77, "ingotMithrilTinker");
        //* Thorium     x+078
        //* U235        x+079
        //* U238        x+080
        gemMagnetite = itemMaterial.addOreDictItem(81, "gemMagnetite");
        ingotNeodymiumMagnet = itemMaterial.addOreDictItem(82, "ingotNeodymiumMagnetMetal");
        ingotIronMagnet = itemMaterial.addOreDictItem(83, "ingotIronMagnetic");
        ingotManganeseMagnet = itemMaterial.addOreDictItem(84, "ingotManganeseMagnetic");
        ingotCobaltMagnet = itemMaterial.addOreDictItem(85, "ingotCobaltMagnetic");
        ingotNickelMagnet = itemMaterial.addOreDictItem(86, "ingotNickelMagnetic");
        ingotInvarMagnet = itemMaterial.addOreDictItem(87, "ingotInvarMagnetic");
        ingotHCSteelMagnet = itemMaterial.addOreDictItem(88, "ingotHighCarbonSteelMagnetic", "ingotSteelMagnetic");
        ingotSteelMagnet = itemMaterial.addOreDictItem(89, "ingotSteelMagnetic", "ingotRefinedSteelMagnetic");
        ingotHSLAMagnet = itemMaterial.addOreDictItem(90, "ingotHSLAMagnetic");
        //* Amber       x+091
        ingotNichrome = itemMaterial.addOreDictItem(92, "ingotNichrome");
        ingotPolycarbide = itemMaterial.addOreDictItem(93, "ingotPolycarbide");
        ingotVorpal = itemMaterial.addOreDictItem(94, "ingotVorpal");
        //* Ashes       x+095
        //* Iron        x+096
        //* Gold        x+097
        //* Diamond     x+098
        //* Coal        x+099
        //* Charcoal    x+100
        //* Obsidian    x+101
        //* Blizz       x+102
        //* Cyrotheum   x+103
        //* Pyrotheum   x+104
        //* Iceflame    x+105
        //* Kroostyl    x+106
        ingotYttrium = itemMaterial.addOreDictItem(107, "ingotYtrium");
        ingotRuthenium = itemMaterial.addOreDictItem(108, "ingotRuthenium");
        ingotLanthanum = itemMaterial.addOreDictItem(109, "ingotLanthanum");
        ingotCerium = itemMaterial.addOreDictItem(110, "ingotCerium");
        //* Magnesium   x+111
        //* Calcium     x+112
        //* Strontium   x+113
    }

    //500-999 Dusts
    public void loadDusts() {
        dustCopper = itemMaterial.addOreDictItem(500, "dustCopper");
        dustTin = itemMaterial.addOreDictItem(501, "dustTin");
        dustLead = itemMaterial.addOreDictItem(502, "dustLead");
        dustSilver = itemMaterial.addOreDictItem(503, "dustSilver");
        dustNickel = itemMaterial.addOreDictItem(504, "dustNickel");
        dustZinc = itemMaterial.addOreDictItem(505, "dustZinc");
        dustBismuth = itemMaterial.addOreDictItem(506, "dustBismuth");
        dustManganese = itemMaterial.addOreDictItem(507, "dustManganese");
        dustAluminium = itemMaterial.addOreDictItem(508, "dustAluminium");
        dustPlatinum = itemMaterial.addOreDictItem(509, "dustPlatinum");
        dustPalladium = itemMaterial.addOreDictItem(510, "dustPalladium");
        dustMolybdenum = itemMaterial.addOreDictItem(511, "dustMolybdenum");
        dustCobalt = itemMaterial.addOreDictItem(512, "dustCobalt", "dustNaturalCobalt");
        dustTungsten = itemMaterial.addOreDictItem(513, "dustTungsten");
        dustTitanium = itemMaterial.addOreDictItem(514, "dustTitanium");
        dustChromium = itemMaterial.addOreDictItem(515, "dustChromium");
        dustArsenic = itemMaterial.addOreDictItem(516, "dustArsenic");
        dustAntimony = itemMaterial.addOreDictItem(517, "dustAntimony");
        dustNeodymium = itemMaterial.addOreDictItem(518, "dustNeodymium");
        dustTesseractium = itemMaterial.addOreDictItem(519, "dustTesseractium");
        dustCadmium = itemMaterial.addOreDictItem(520, "dustCadmium");
        dustTellurium = itemMaterial.addOreDictItem(521, "dustTellurium");
        dustOsmium = itemMaterial.addOreDictItem(522, "dustOsmium");
        dustIridium = itemMaterial.addOreDictItem(523, "dustIridium");
        dustIndium = itemMaterial.addOreDictItem(524, "dustIndium");
        dustAntimonialBronze = itemMaterial.addOreDictItem(525, "dustAntimonialBronze");
        dustArsenicalBronze = itemMaterial.addOreDictItem(526, "dustArsenicalBronze");
        dustVanadium = itemMaterial.addOreDictItem(527, "dustVanadium");
        dustUnobtainium = itemMaterial.addOreDictItem(528, "dustUnobtainium");
        dustDioptase = itemMaterial.addOreDictItem(529, "dustDioptase");
        dustPyrope = itemMaterial.addOreDictItem(530, "dustPyrope");
        dustMyuvil = itemMaterial.addOreDictItem(531, "dustMyuvil");
        dustBronze = itemMaterial.addOreDictItem(532, "dustBronze");
        dustBrass = itemMaterial.addOreDictItem(533, "dustBrass");
        dustInvar = itemMaterial.addOreDictItem(534, "dustInvar");
        dustBismuthBronze = itemMaterial.addOreDictItem(535, "dustBismuthBronze");
        dustCupronickel = itemMaterial.addOreDictItem(536, "dustCupronickel");
        dustAluminiumBrass = itemMaterial.addOreDictItem(537, "dustAluminiumBrass");
        dustElectrum = itemMaterial.addOreDictItem(538, "dustElectrum");
        dustDullRedsolder = itemMaterial.addOreDictItem(539, "dustDullRedsolder");
        dustRedsolder = itemMaterial.addOreDictItem(540, "dustRedsolder");
        dustHCSteel = itemMaterial.addOreDictItem(541, "dustHighCarbonSteel", "dustSteel");
        dustSteel = itemMaterial.addOreDictItem(542, "dustSteel", "dustRefinedSteel");
        dustHSLA = itemMaterial.addOreDictItem(543, "dustHSLA");
        dustStainlessSteel = itemMaterial.addOreDictItem(544, "dustStainlessSteel");
        dustTungstenSteel = itemMaterial.addOreDictItem(545, "dustTungstenSteel");
        dustEletriplatinum = itemMaterial.addOreDictItem(546, "dustElectriplatinum");
        dustMithril = itemMaterial.addOreDictItem(547, "dustMithril", "dustMitrilBronze");
        dustTechnomancer = itemMaterial.addOreDictItem(548, "dustTechnomancer");
        dustTechnomancerResonant = itemMaterial.addOreDictItem(549, "dustTechnomancerResonant");
        dustTungstenBlazing = itemMaterial.addOreDictItem(550, "dustTungstenBlazing");
        dustPlatinumGelid = itemMaterial.addOreDictItem(551, "dustPlatinumGelid");
        dustSilverLuminous = itemMaterial.addOreDictItem(552, "dustSilverLuminous");
        dustElectrumFlux = itemMaterial.addOreDictItem(553, "dustElectrumFlux");
        dustMolybdenumResonant = itemMaterial.addOreDictItem(554, "dustMolybdenumResonant");
        dustChromiumCarbide = itemMaterial.addOreDictItem(555, "dustChromiumCarbide");
        dustBismuthBronzeColdfire = itemMaterial.addOreDictItem(556, "dustBismuthBronzeColdfire");
        dustPyrum = itemMaterial.addOreDictItem(557, "dustPyrum");
        dustGelinium = itemMaterial.addOreDictItem(558, "dustGelinium");
        dustLumium = itemMaterial.addOreDictItem(559, "dustLumium");
        dustSignalum = itemMaterial.addOreDictItem(560, "dustSignalum");
        dustEnderium = itemMaterial.addOreDictItem(561, "dustEnderium");
        dustCarbonite = itemMaterial.addOreDictItem(562, "dustCarbonite");
        dustTherminate = itemMaterial.addOreDictItem(563, "dustTherminate");
        dustNullmetal = itemMaterial.addOreDictItem(564, "dustNullmetal");
        dustIocarbide = itemMaterial.addOreDictItem(565, "dustIocarbide");
        dustCryocarbide = itemMaterial.addOreDictItem(566, "dustCryocarbide");
        dustPyrocarbide = itemMaterial.addOreDictItem(567, "dustPyrocarbide");
        dustTenebride = itemMaterial.addOreDictItem(568, "dustTenebride");
        dustIlluminide = itemMaterial.addOreDictItem(569, "dustIlluminide");
        dustZythoferride = itemMaterial.addOreDictItem(570, "dustZythoferride");
        dustCrystalFlux = itemMaterial.addOreDictItem(571, "dustCrystalFlux");
        dustLapiquartz = itemMaterial.addOreDictItem(572, "dustLapiquartz");
        dustRust = itemMaterial.addOreDictItem(573 , "dustRust");
        dustSulfur = itemMaterial.addOreDictItem(574 , "dustSulfur");
        dustSaltpeter = itemMaterial.addOreDictItem(575 , "dustSaltpeter");
        dustMithrilFlux = itemMaterial.addOreDictItem(576, "dustMithrilFlux");
        dustMithrilTinker = itemMaterial.addOreDictItem(577, "dustMithrilTinker");
        dustThorium = itemMaterial.addOreDictItem(578 , "dustThorium");
        dustUranium235 = itemMaterial.addOreDictItem(579 , "dustUranium235");
        dustUranium238 = itemMaterial.addOreDictItem(580 , "dustUranium238");
        dustMagnetite = itemMaterial.addOreDictItem(581, "dustMagnetite");
        dustNeodymiumMagnet = itemMaterial.addOreDictItem(582, "dustNeodymiumMagnetMetal");
        dustIronMagnet = itemMaterial.addOreDictItem(583, "dustIronMagnetic");
        dustManganeseMagnet = itemMaterial.addOreDictItem(584, "dustManganeseMagnetic");
        dustCobaltMagnet = itemMaterial.addOreDictItem(585, "dustCobaltMagnetic");
        dustNickelMagnet = itemMaterial.addOreDictItem(586, "dustNickelMagnetic");
        dustInvarMagnet = itemMaterial.addOreDictItem(587, "dustInvarMagnetic");
        dustHCSteelMagnet = itemMaterial.addOreDictItem(588, "dustHighCarbonSteelMagnetic", "dustSteelMagnetic");
        dustSteelMagnet = itemMaterial.addOreDictItem(589, "dustSteelMagnetic");
        dustHSLAMagnet = itemMaterial.addOreDictItem(590, "dustHSLAMagnetic", "dustRefinedSteelMagnetic");
        //* Amber
        dustNichrome = itemMaterial.addOreDictItem(592, "dustNichrome");
        dustPolycarbide = itemMaterial.addOreDictItem(593, "dustPolycarbide");
        dustVorpal = itemMaterial.addOreDictItem(594, "dustVorpal");
        dustAshes = itemMaterial.addOreDictItem(595, "dustAshes");
        dustIron = itemMaterial.addOreDictItem(596 , "dustIron");
        dustGold = itemMaterial.addOreDictItem(597 , "dustGold");
        dustDiamond = itemMaterial.addOreDictItem(598 , "dustDiamond");
        dustCoal = itemMaterial.addOreDictItem(599 , "dustCoal");
        dustCharcoal = itemMaterial.addOreDictItem(600 , "dustCharcoal");
        dustObsidian = itemMaterial.addOreDictItem(601 , "dustObsidian");
        dustBlizz = itemMaterial.addOreDictItem(602 , "dustBlizz");
        dustCyrotheum = itemMaterial.addOreDictItem(603 , "dustCryotheum");
        dustPyrotheum = itemMaterial.addOreDictItem(604 , "dustPyrotheum");
        dustIceflame = itemMaterial.addOreDictItem(605 , "dustIceflame");
        dustKroostyl = itemMaterial.addOreDictItem(606 , "dustKroostyl");
        dustYttrium = itemMaterial.addOreDictItem(607, "dustYtrium");
        dustRuthenium = itemMaterial.addOreDictItem(608, "dustRuthenium");
        dustLanthanum = itemMaterial.addOreDictItem(609, "dustLanthanum");
        dustCerium = itemMaterial.addOreDictItem(610, "dustCerium");
        dustMagnesium = itemMaterial.addOreDictItem(611 , "dustMagnesium");
        dustCalcium = itemMaterial.addOreDictItem(612 , "dustCalcium");
        dustStrontium = itemMaterial.addOreDictItem(613 , "dustStrontium");

    }

    //1000-1499 Nuggets
    public void loadNuggets() {
        nuggetCopper = itemMaterial.addOreDictItem(1000, "nuggetCopper");
        nuggetTin = itemMaterial.addOreDictItem(1001, "nuggetTin");
        nuggetLead = itemMaterial.addOreDictItem(1002, "nuggetLead");
        nuggetSilver = itemMaterial.addOreDictItem(1003, "nuggetSilver");
        nuggetNickel = itemMaterial.addOreDictItem(1004, "nuggetNickel");
        nuggetZinc = itemMaterial.addOreDictItem(1005, "nuggetZinc");
        nuggetBismuth = itemMaterial.addOreDictItem(1006, "nuggetBismuth");
        nuggetManganese = itemMaterial.addOreDictItem(1007, "nuggetManganese");
        nuggetAluminium = itemMaterial.addOreDictItem(1008, "nuggetAluminium");
        nuggetPlatinum = itemMaterial.addOreDictItem(1009, "nuggetPlatinum");
        nuggetPalladium = itemMaterial.addOreDictItem(1010, "nuggetPalladium");
        nuggetMolybdenum = itemMaterial.addOreDictItem(1011, "nuggetMolybdenum");
        nuggetCobalt = itemMaterial.addOreDictItem(1012, "nuggetCobalt", "nuggetNaturalCobalt");
        nuggetTungsten = itemMaterial.addOreDictItem(1013, "nuggetTungsten");
        nuggetTitanium = itemMaterial.addOreDictItem(1014, "nuggetTitanium");
        nuggetChromium = itemMaterial.addOreDictItem(1015, "nuggetChromium");
        //* Antimony
        //* Arsenic
        nuggetNeodymium = itemMaterial.addOreDictItem(1018, "nuggetNeodymium");
        nuggetTesseractium = itemMaterial.addOreDictItem(1019, "nuggetTesseractium");
        nuggetCadmium = itemMaterial.addOreDictItem(1020, "nuggetCadmium");
        nuggetTellurium = itemMaterial.addOreDictItem(1021, "nuggetTellurium");
        nuggetOsmium = itemMaterial.addOreDictItem(1022, "nuggetOsmium");
        nuggetIridium = itemMaterial.addOreDictItem(1023, "nuggetIridium");
        nuggetIndium = itemMaterial.addOreDictItem(1024, "nuggetIndium");
        nuggetAntimonialBronze = itemMaterial.addOreDictItem(1025, "nuggetAntimonialBronze");
        nuggetArsenicalBronze = itemMaterial.addOreDictItem(1026, "nuggetArsenicalBronze");
        nuggetVanadium = itemMaterial.addOreDictItem(1027, "nuggetVanadium");
        nuggetUnobtainium = itemMaterial.addOreDictItem(1028, "nuggetUnobtainium");
        nuggetDioptase = itemMaterial.addOreDictItem(1029, "nuggetDioptase");
        nuggetPyrope = itemMaterial.addOreDictItem(1030, "nuggetPyrope");
        //* Myuvil
        nuggetBronze = itemMaterial.addOreDictItem(1032, "nuggetBronze");
        nuggetBrass = itemMaterial.addOreDictItem(1033, "nuggetBrass");
        nuggetInvar = itemMaterial.addOreDictItem(1034, "nuggetInvar");
        nuggetBismuthBronze = itemMaterial.addOreDictItem(1035, "nuggetBismuthBronze");
        nuggetCupronickel = itemMaterial.addOreDictItem(1036, "nuggetCupronickel");
        nuggetAluminiumBrass = itemMaterial.addOreDictItem(1037, "nuggetAluminiumBrass");
        nuggetElectrum = itemMaterial.addOreDictItem(1038, "nuggetElectrum");
        nuggetDullRedsolder = itemMaterial.addOreDictItem(1039, "nuggetDullRedsolder");
        nuggetRedsolder = itemMaterial.addOreDictItem(1040, "nuggetRedsolder");
        nuggetHCSteel = itemMaterial.addOreDictItem(1041, "nuggetHighCarbonSteel", "nuggetSteel");
        nuggetSteel = itemMaterial.addOreDictItem(1042, "nuggetSteel", "nuggetRefinedSteel");
        nuggetHSLA = itemMaterial.addOreDictItem(1043, "nuggetHSLA");
        nuggetStainlessSteel = itemMaterial.addOreDictItem(1044, "nuggetStainlessSteel");
        nuggetTungstenSteel = itemMaterial.addOreDictItem(1045, "nuggetTungstenSteel");
        nuggetEletriplatinum = itemMaterial.addOreDictItem(1046, "nuggetElectriplatinum");
        nuggetMithril = itemMaterial.addOreDictItem(1047, "nuggetMithril", "nuggetMitrilBronze");
        nuggetTechnomancer = itemMaterial.addOreDictItem(1048, "nuggetTechnomancer");
        nuggetTechnomancerResonant = itemMaterial.addOreDictItem(1049, "nuggetTechnomancerResonant");
        nuggetTungstenBlazing = itemMaterial.addOreDictItem(1050, "nuggetTungstenBlazing");
        nuggetPlatinumGelid = itemMaterial.addOreDictItem(1051, "nuggetPlatinumGelid");
        nuggetSilverLuminous = itemMaterial.addOreDictItem(1052, "nuggetSilverLuminous");
        nuggetElectrumFlux = itemMaterial.addOreDictItem(1053, "nuggetElectrumFlux");
        nuggetMolybdenumResonant = itemMaterial.addOreDictItem(1054, "nuggetMolybdenumResonant");
        nuggetChromiumCarbide = itemMaterial.addOreDictItem(1055, "nuggetChromiumCarbide");
        nuggetBismuthBronzeColdfire = itemMaterial.addOreDictItem(1056, "nuggetBismuthBronzeColdfire");
        nuggetPyrum = itemMaterial.addOreDictItem(1057, "nuggetPyrum");
        nuggetGelinium = itemMaterial.addOreDictItem(1058, "nuggetGelinium");
        nuggetLumium = itemMaterial.addOreDictItem(1059, "nuggetLumium");
        nuggetSignalum = itemMaterial.addOreDictItem(1060, "nuggetSignalum");
        nuggetEnderium = itemMaterial.addOreDictItem(1061, "nuggetEnderium");
        nuggetCarbonite = itemMaterial.addOreDictItem(1062, "nuggetCarbonite");
        nuggetTherminate = itemMaterial.addOreDictItem(1063, "nuggetTherminate");
        nuggetNullmetal = itemMaterial.addOreDictItem(1064, "nuggetNullmetal");
        nuggetIocarbide = itemMaterial.addOreDictItem(1065, "nuggetIocarbide");
        nuggetCryocarbide = itemMaterial.addOreDictItem(1066, "nuggetCryocarbide");
        nuggetPyrocarbide = itemMaterial.addOreDictItem(1067, "nuggetPyrocarbide");
        nuggetTenebride = itemMaterial.addOreDictItem(1068, "nuggetTenebride");
        nuggetIlluminide = itemMaterial.addOreDictItem(1069, "nuggetIlluminide");
        nuggetZythoferride = itemMaterial.addOreDictItem(1070, "nuggetZythoferride");
        nuggetCrystalFlux = itemMaterial.addOreDictItem(1071, "nuggetCrystalFlux");
        //* Lapiquartz
        //* Rust
        //* Sulfur
        //* Saltpeter
        nuggetMithrilFlux = itemMaterial.addOreDictItem(1076, "nuggetMithrilFlux");
        nuggetMithrilTinker = itemMaterial.addOreDictItem(1077, "nuggetMithrilTinker");
        //* Thorium     x+078
        //* U235        x+079
        //* U238        x+080
        nuggetMagnetite = itemMaterial.addOreDictItem(1081, "nuggetMagnetite");
        nuggetNeodymiumMagnet = itemMaterial.addOreDictItem(1082, "nuggetNeodymiumMagnetMetal");
        nuggetIronMagnet = itemMaterial.addOreDictItem(1083, "nuggetIronMagnetic");
        nuggetManganeseMagnet = itemMaterial.addOreDictItem(1084, "nuggetManganeseMagnetic");
        nuggetCobaltMagnet = itemMaterial.addOreDictItem(1085, "nuggetCobaltMagnetic");
        nuggetNickelMagnet = itemMaterial.addOreDictItem(1086, "nuggetNickelMagnetic");
        nuggetInvarMagnet = itemMaterial.addOreDictItem(1087, "nuggetInvarMagnetic");
        nuggetHCSteelMagnet = itemMaterial.addOreDictItem(1088, "nuggetHighCarbonSteelMagnetic", "nuggetSteelMagnetic");
        nuggetSteelMagnet = itemMaterial.addOreDictItem(1089, "nuggetSteelMagnetic", "nuggetRefinedSteelMagnetic");
        nuggetHSLAMagnet = itemMaterial.addOreDictItem(1090, "nuggetHSLAMagnetic");
        //* Amber       x+091
        nuggetNichrome = itemMaterial.addOreDictItem(1092, "nuggetNichrome");
        nuggetPolycarbide = itemMaterial.addOreDictItem(1093, "nuggetPolycarbide");
        nuggetVorpal = itemMaterial.addOreDictItem(1094, "nuggetVorpal");
        //* Ashes       x+095
        //* Iron        x+096
        //* Gold        x+097
        //* Diamond     x+098
        //* Coal        x+099
        //* Charcoal    x+100
        //* Obsidian    x+101
        //* Blizz       x+102
        //* Cyrotheum   x+103
        //* Pyrotheum   x+104
        //* Iceflame    x+105
        nuggetKroostyl = itemMaterial.addOreDictItem(1106, "nuggetKroostyl");
        nuggetYttrium = itemMaterial.addOreDictItem(1107, "nuggetYtrium");
        nuggetRuthenium = itemMaterial.addOreDictItem(1108, "nuggetRuthenium");
        nuggetLanthanum = itemMaterial.addOreDictItem(1109, "nuggetLanthanum");
        nuggetCerium = itemMaterial.addOreDictItem(1110, "nuggetCerium");
        //* Magnesium   x+111
        //* Calcium     x+112
        //* Strontium   x+113
    }

    //1500-1999 Gems
    public void loadGems() {
        crystalMolybdenum = itemMaterial.addOreDictItem(1500, "crystalMolybdenum");
        crystalDioptase = itemMaterial.addOreDictItem(1501, "crystalDioptase");
        crystalPyrope = itemMaterial.addOreDictItem(1502, "crystalPyrope");
        gemNaturalAmber = itemMaterial.addOreDictItem(1503, "gemAmber");

        gemMolybdenum = itemMaterial.addOreDictItem(1550, "gemMolybdenum");

        gemSyntheticMolybdenum = itemMaterial.addOreDictItem(1600, "gemSyntheticMolybdenum");
        gemSyntheticDioptase = itemMaterial.addOreDictItem(1601, "gemSyntheticDioptase");
        gemSyntheticPyrope = itemMaterial.addOreDictItem(1602, "gemSyntheticPyrope");
        gemSyntheticGreen = itemMaterial.addOreDictItem(1603, "gemSyntheticGreen");
    }

    //2000-2499 Gears
    public void loadGears() {
        // Parts
        partWiring = itemMaterial.addOreDictItem(64, "partWiring");
        partCircuitPlate = itemMaterial.addOreDictItem(65, "partCircuitPlate");
        partUnprocessedPCB = itemMaterial.addOreDictItem(66, "partUnprocessedPCB");
        partUnassembledPCB = itemMaterial.addOreDictItem(67, "partUnassembledPCB");
        partAssembledPCB = itemMaterial.addOreDictItem(68, "partAssembledPCB");
        partTransistor = itemMaterial.addOreDictItem(69, "partTransistor");
        partResistor = itemMaterial.addOreDictItem(70, "partResistor");
        partSpring = itemMaterial.addOreDictItem(71, "partSpring");
        partFluxFilter = itemMaterial.addOreDictItem(72, "partFluxFilter");
        partIonThruster = itemMaterial.addOreDictItem(73, "partIonThruster");
        partResonantThruster = itemMaterial.addOreDictItem(74, "partResonantIonThruster");
        partMagnet = itemMaterial.addOreDictItem(75, "partMagnet");
        partAlCoNiMagnet = itemMaterial.addOreDictItem(76, "partAlCoNiMagnet");
        partServoMotor = itemMaterial.addOreDictItem(77, "partServoMotor");
        partSolenoid = itemMaterial.addOreDictItem(78, "partSolenoid");
        partGearCore = itemMaterial.addOreDictItem(79, "partGearCore");

        // Capacitors
        partCapacitorLv1 = itemMaterial.addOreDictItem(88, "partCapacitorLv1");
    }

/*  //2500-2999 1/4 (Small) Dusts
    //3000-3499 1/9 (Tiny) Dusts
    //3500-3999 Foils
    //4000-4499 Plates
    //4500-4999 Dense Plates
    //5000-5499 Washers
    //5500-5999 Bolts
    //6000-6499 Nuts
    //6500-6999 Ball Bearings
    //7000-7499 Shafts
    //7500-7999 Panels

    //10000-10999 Tiered Components

    public void loadComponents() {
        //5000-5024 Etched Wires
        //5025-5049 Energy Circuits
        //5050-5074 Data Circuits
        //5075-5099 Circuit Parts
        //5100-5124 Circuits
        //5125-5149 Transmitters
        //5150-5174 Receivers
        //5175-5199 Tranceivers
        //5200-5224 Sensors
        //5225-5249 Field Generators
        //5250-5274 Flux Motors
        //5275-5299 Conveyors
        //5300-5324 Flux Pistons
        //5325-5349 Robotic Arms
        //5350-5374 Capacitors
        //5375-5399 Circuits
        //5400-5424 Filters
        //5425-5449 Pipes
        //5450-5475 Ducts
        //5475-5499 Lenses
        //5500-5524 Heating Coils
        //5525-5549 Flux Coils
    }

	//11000-11999 Non-Tiered Components (Parts)
    //12000-12999 Trace Minerals

    //13000-13999 Ore Chips
    //14000-14999 Dirty Ore Chunks
    //15000-15999 Clean Ore Chunks
    //16000-16999 Crushed Ores
    //17000-17999 Purified Crushed Ores
    //18000-18999 Dirty Ground Ores
    //19000-19999 Clean Ground Ores
    //20000-20999 Impure Ore Dusts
    //21000-21999 Purified Ore Dusts
    //22000-22999 Ore Slurries
    //23000-23999 Ore Solutions
    //24000-24999 Ore Flakes
    //25000-25999 Pulverized Ore Flakes
    //26000-26999 Centrifuged Ores
    //27000-27999 Purified Centrifuged Ores
    //28000-28999 Rendered Ore Chunks
    //29000-29999 Crystallized Ores */

    public void metalCraftingRecipes() {
        //TODO: UPDATE
        ItemHelper.addStorageRecipe(ingotZinc, "nuggetZinc");
        ItemHelper.addStorageRecipe(ingotBismuth, "nuggetBismuth");
        ItemHelper.addStorageRecipe(ingotManganese, "nuggetManganese");
        ItemHelper.addStorageRecipe(ingotPalladium, "nuggetPalladium");
        ItemHelper.addStorageRecipe(ingotMolybdenum, "nuggetMolybdenum");
        ItemHelper.addStorageRecipe(ingotCobalt, "nuggetNaturalCobalt");
        ItemHelper.addStorageRecipe(ingotTungsten, "nuggetTungsten");
        ItemHelper.addStorageRecipe(ingotAluminium, "nuggetAluminium");
        ItemHelper.addStorageRecipe(ingotChromium, "nuggetChromium");
        ItemHelper.addStorageRecipe(ingotTitanium, "nuggetTitanium");
        ItemHelper.addStorageRecipe(ingotIridium, "nuggetIridium");
        ItemHelper.addStorageRecipe(gemMagnetite, "nuggetMagnetite");

        ItemHelper.addReverseStorageRecipe(ingotZinc, "blockZinc");
        ItemHelper.addReverseStorageRecipe(ingotBismuth, "blockBismuth");
        ItemHelper.addReverseStorageRecipe(ingotManganese, "blockManganese");
        ItemHelper.addReverseStorageRecipe(ingotPalladium, "blockPalladium");
        ItemHelper.addReverseStorageRecipe(ingotMolybdenum, "blockMolybdenum");
        ItemHelper.addReverseStorageRecipe(ingotCobalt, "blockNaturalCobalt");
        ItemHelper.addReverseStorageRecipe(ingotTungsten, "blockTungsten");
        ItemHelper.addReverseStorageRecipe(ingotAluminium, "blockAluminium");
        ItemHelper.addReverseStorageRecipe(ingotChromium, "blockChromium");
        ItemHelper.addReverseStorageRecipe(ingotTitanium, "blockTitanium");
        ItemHelper.addReverseStorageRecipe(ingotIridium, "blockIridium");
        ItemHelper.addReverseStorageRecipe(gemMagnetite, "blockMagnetite");
        ItemHelper.addReverseStorageRecipe(gemDioptase, "blockDioptase");
        ItemHelper.addReverseStorageRecipe(gemPyrope, "blockPyrope");

        ItemHelper.addReverseStorageRecipe(nuggetZinc, "ingotZinc");
        ItemHelper.addReverseStorageRecipe(nuggetBismuth, "ingotBismuth");
        ItemHelper.addReverseStorageRecipe(nuggetManganese, "ingotManganese");
        ItemHelper.addReverseStorageRecipe(nuggetPalladium, "ingotPalladium");
        ItemHelper.addReverseStorageRecipe(nuggetMolybdenum, "ingotMolybdenum");
        ItemHelper.addReverseStorageRecipe(nuggetCobalt, "ingotNaturalCobalt");
        ItemHelper.addReverseStorageRecipe(nuggetTungsten, "ingotTungsten");
        ItemHelper.addReverseStorageRecipe(nuggetAluminium, "ingotAluminium");
        ItemHelper.addReverseStorageRecipe(nuggetChromium, "ingotChromium");
        ItemHelper.addReverseStorageRecipe(nuggetTitanium, "ingotTitanium");
        ItemHelper.addReverseStorageRecipe(nuggetIridium, "ingotIridium");
        ItemHelper.addReverseStorageRecipe(nuggetMagnetite, "ingotMagnetite");

        ItemHelper.addStorageRecipe(blockZinc, "ingotZinc");
        ItemHelper.addStorageRecipe(blockBismuth, "ingotBismuth");
        ItemHelper.addStorageRecipe(blockManganese, "ingotManganese");
        ItemHelper.addStorageRecipe(blockPalladium, "ingotPalladium");
        ItemHelper.addStorageRecipe(blockMolybdenum, "ingotMolybdenum");
        ItemHelper.addStorageRecipe(blockCobalt, "ingotNaturalCobalt");
        ItemHelper.addStorageRecipe(blockTungsten, "ingotTungsten");
        ItemHelper.addStorageRecipe(blockAluminium, "ingotAluminium");
        ItemHelper.addStorageRecipe(blockChromium, "ingotChromium");
        ItemHelper.addStorageRecipe(blockTitanium, "ingotTitanium");
        ItemHelper.addStorageRecipe(blockMagnetite, "gemMagnetite");
        ItemHelper.addStorageRecipe(blockDioptase, "gemDioptase");
        ItemHelper.addStorageRecipe(blockPyrope, "gemPyrope");
        ItemHelper.addStorageRecipe(blockMyuvil, "dustMyuvil");
        ItemHelper.addStorageRecipe(blockArsenic, "dustArsenic");
        ItemHelper.addStorageRecipe(blockAntimony, "dustAntimony");

        ItemHelper.addStorageRecipe(blockBrass, "ingotBrass");
        ItemHelper.addStorageRecipe(blockBismuthBronze, "ingotBismuthBronze");
        ItemHelper.addStorageRecipe(blockCupronickel, "ingotCupronickel");
        ItemHelper.addStorageRecipe(blockAluminiumBrass, "ingotAluminiumBrass");
        ItemHelper.addStorageRecipe(blockMithril, "ingotMithrilBronze");
        ItemHelper.addStorageRecipe(blockElectriplatinum, "ingotElectrplatinum");
        ItemHelper.addStorageRecipe(blockSteel, "ingotSteel");
        ItemHelper.addStorageRecipe(blockTungstenSteel, "ingotTungstenSteel");
        ItemHelper.addStorageRecipe(blockStainlessSteel, "ingotStainlessSteel");
        ItemHelper.addStorageRecipe(blockTechnomancy, "ingotTechnomancer");
        ItemHelper.addStorageRecipe(blockResonantTechnomancy, "ingotResonantTechnomancy");
        // Amber is a 2x2 recipe
        ItemHelper.addStorageRecipe(blockCrystalFlux, "gemCrystalFlux");
        // Lapiquartz is a resource recipe
        ItemHelper.addStorageRecipe(blockWhitePointStar, "gemWhitePointStar");
        ItemHelper.addStorageRecipe(blockVoidInfernoStar, "gemVoidInfernoStar");

        ItemHelper.addStorageRecipe(blockTungstenBlazing, "ingotTungstenBlazing");
        ItemHelper.addStorageRecipe(blockPlatinumGelid, "ingotBismuthBronzeGelid");
        ItemHelper.addStorageRecipe(blockSilverLuminous, "ingotSilverLuminous");
        ItemHelper.addStorageRecipe(blockElectrumFlux, "ingotElectrumFlux");
        ItemHelper.addStorageRecipe(blockMolybdenumResonant, "ingotMolybdenumResonant");
        ItemHelper.addStorageRecipe(blockChromiumCarbide, "ingotChromiumCarbide");
        ItemHelper.addStorageRecipe(blockCarbonite, "ingotCarbonite");
        ItemHelper.addStorageRecipe(blockPyrum, "ingotPyrum");
        ItemHelper.addStorageRecipe(blockGelinium, "ingotGelinium");
        ItemHelper.addStorageRecipe(blockDullRedsolder, "ingotDullRedsolder");
        ItemHelper.addStorageRecipe(blockRedsolder, "ingotRedsolder");
        ItemHelper.addStorageRecipe(blockIridium, "ingotIridium");
        ItemHelper.addStorageRecipe(blockSulfur, "dustSulfur");
        ItemHelper.addStorageRecipe(blockSulfur, "dustSulphur");
        ItemHelper.addStorageRecipe(blockSaltpeter, "dustSaltpeter");
        ItemHelper.addStorageRecipe(blockSaltpeter, "dustSaltpetre");
        ItemHelper.addStorageRecipe(blockRust, "dustRust");
        ItemHelper.addStorageRecipe(blockBismuthBronzeColdfire, "ingotBismuthBronzeColdfire");
    }

    public void machineCraftingRecipes() {

        /*//Weird Science Legacy Recipes //TODO
        // Nitrate Engine
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(nitrateEngineBlock, 1, 0), "sss", "gcg", "sbs", 's', "stone", 'c', Items.slime_ball, 'g', "ingotGold", 'b', Items.bucket));
        // Blood Donation Station
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(donationBlock, 1, 0), "aba", "aga", "aba", 'a', "ingotAluminium", 'g', Blocks.glass, 'b', Items.bucket));
        // Blood Engine
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bloodEngineBlock, 1, 0), "aba", "afa", "aaa", 'a', "ingotAluminium", 'f', Blocks.furnace, 'b', new ItemStack(Items.bucket)));
        // Occult Engine
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(occultEngineBlock, 1, 0), "gog", "oeo", "gog", 'e', bloodEngineBlock, 'o', Blocks.obsidian, 'g', "ingotGold"));
        // Blast Engine
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(gunpowderEngineBlock, 1, 0), "aia", "afa", "ana", 'a', "ingotAluminium", 'f', Blocks.furnace, 'n', Blocks.netherrack, 'i', Blocks.iron_bars));*/
    }

    public void otherCraftingRecipes() {
        //Weird Science Legacy Recipes
        GameRegistry.addRecipe(new ShapelessOreRecipe(bucketLye, Items.water_bucket, dustAshes));
        GameRegistry.addRecipe(new ShapelessOreRecipe(bucketAcid, Items.water_bucket, Items.gunpowder));
        GameRegistry.addRecipe(new ShapelessOreRecipe(dustThermite, "dustRust", "dustAluminium"));
        GameRegistry.addRecipe(new ShapedOreRecipe(toolProtoSonicWrench, "B B", "ADA", " B ", 'B', "ingotBronze", 'A', "ingotAluminium", 'D', "gemDioptase"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(foodMelonPan, Items.bread, Items.melon));
        if (LoadedHelper.isThermalExpansionLoaded) {
            GameRegistry.addRecipe(new ShapedOreRecipe(toolProtoSonicWrench, "B B", "WDW", " B ", 'B', "ingotMithrilBronze", 'W', "ingotTungsten", 'D', "gemDioptase"));
        }

    }

    public void furnaceRecipes() {
        //TODO: UPDATE
        // Ore to Ingot
        GameRegistry.addSmelting(oreSphalerite, ingotZinc, 0.7F);
        GameRegistry.addSmelting(oreBismuthinite, ingotBismuth, 0.9F);
        GameRegistry.addSmelting(orePyrolusite, ingotManganese, 0.9F);
        GameRegistry.addSmelting(oreBraggite, ingotPalladium, 1.1F);
        GameRegistry.addSmelting(oreMolybdenite, ingotMolybdenum, 1.0F);
        GameRegistry.addSmelting(oreCobaltite, ingotCobalt, 1.1F);
        // No Wolframite
        // Temporary Aluminium Processing
        GameRegistry.addSmelting(oreBauxite, ingotAluminium, 0.9F);
        GameRegistry.addSmelting(oreChromite, ingotChromium, 1.2F);
        // No Ilmenite
        // For when you Silk Touch the ores
        GameRegistry.addSmelting(oreMagnetite, gemMagnetite, 1.0F);
        GameRegistry.addSmelting(oreDioptase, gemDioptase, 1.0F);
        GameRegistry.addSmelting(orePyrope, gemPyrope, 1.0F);
        GameRegistry.addSmelting(oreMyuvil, ItemHelper.cloneStack(dustMyuvil, 4), 1.0F);
        GameRegistry.addSmelting(orePitchblende, dustPitchblende, 1.3F);
        GameRegistry.addSmelting(oreNiedermayrite, ItemHelper.cloneStack(dustNiedermayrite, 4), 1.1F);

        // Dust to Ingot
        GameRegistry.addSmelting(dustZinc, ingotZinc, 0.0F);
        GameRegistry.addSmelting(dustBismuth, ingotBismuth, 0.0F);
        GameRegistry.addSmelting(dustManganese, ingotManganese, 0.0F);
        GameRegistry.addSmelting(dustPalladium, ingotPalladium, 0.0F);
        GameRegistry.addSmelting(dustMolybdenum, ingotMolybdenum, 0.0F);
        GameRegistry.addSmelting(dustCobalt, ingotCobalt, 0.0F);
        // No Tungsten, requires Pyrotheum/Blast Furnace
        GameRegistry.addSmelting(dustAluminium, ingotAluminium, 0.0F);
        GameRegistry.addSmelting(dustChromium, ingotChromium, 0.0F);
        // Temporary Titanium Smelting until we have Blast Furnaces
        GameRegistry.addSmelting(dustTitanium, ingotTitanium, 0.0F);
        // No Iridium, requires Pyrotheum/Blast Furnace

        //GameRegistry.addSmelting(aluminiumSludge, coagulantAlum, 0.0F); //TODO

        // Temporary way to get Rust
        GameRegistry.addSmelting(blockRust, new ItemStack(Blocks.iron_block, 1, 0), 0.0F);
    }

	public void initTimeyWimey() {
		TileTimeyWimey.blacklistBlock(Blocks.air);

		TileTimeyWimey.blacklistBlock(FluxGearContent.timeyWimeyTorch);
		TileTimeyWimey.blacklistTile(TileTimeyWimey.class);

		TileTimeyWimey.blacklistBlock(Blocks.water);
		TileTimeyWimey.blacklistBlock(Blocks.flowing_water);

		TileTimeyWimey.blacklistBlock(Blocks.lava);
		TileTimeyWimey.blacklistBlock(Blocks.flowing_lava);
	}

    public void aluminiumArc() {
        OreDictionary.registerOre("ingotAluminum", ingotAluminium);
        OreDictionary.registerOre("dustAluminum", dustAluminium);
        OreDictionary.registerOre("nuggetAluminum", nuggetAluminium);
        OreDictionary.registerOre("ingotAluminumBrass", ingotAluminiumBrass);
        OreDictionary.registerOre("dustAluminumBrass", dustAluminiumBrass);
        OreDictionary.registerOre("nuggetAluminumBrass", nuggetAluminiumBrass);
        OreDictionary.registerOre("oreAluminum", oreBauxite);
        OreDictionary.registerOre("blockAluminum", blockAluminium);

        if (FluxGearConfig.cobaltAssimilation) {
            OreDictionary.registerOre("ingotCobalt", ingotCobalt);
            OreDictionary.registerOre("dustCobalt", dustCobalt);
            OreDictionary.registerOre("nuggetCobalt", nuggetCobalt);
            OreDictionary.registerOre("oreCobalt", oreCobaltite);
            OreDictionary.registerOre("blockCobalt", blockCobalt);
        }

        OreDictionary.registerOre("blockDalekanium", blockPolycarbide);

        ItemHelper.addStorageRecipe(ingotAluminium, "nuggetAluminum");
        ItemHelper.addReverseStorageRecipe(ingotAluminium, "blockAluminum");
        ItemHelper.addReverseStorageRecipe(nuggetAluminium, "ingotAluminum");
        ItemHelper.addStorageRecipe(blockAluminium, "ingotAluminum");
        ItemHelper.addStorageRecipe(blockAluminiumBrass, "ingotAluminumBrass");

    }

    public void registerDispenserHandlers() {
        BlockDispenser.dispenseBehaviorRegistry.putObject(FluxGearContent.itemBucket, new DispenserFilledBucketHandler());
        BlockDispenser.dispenseBehaviorRegistry.putObject(Items.bucket, new DispenserEmptyBucketHandler());
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel == dustThermite) {
            return FluxGearConfig.thermiteFuelValue;
        }
        return 0;
    }

    // Blocks
    public static BlockTimeyWimey timeyWimeyTorch;

	public static Block blockPlant;

	public static Block coloredStone;
	public static Block coloredCobble;
	public static Block coloredMossCobble;
	public static Block coloredStoneBrick;
	public static Block coloredMossStoneBrick;
	public static Block coloredCrackedStoneBrick;
	public static Block coloredStoneRoad;
	public static Block coloredStoneFancyBrick;
	public static Block coloredStoneSquareBrick;
	public static Block clayBrickSmall;

	//For Now
	public static float[] stoneHardness = { 1.5F };
	public static float[] stoneResistance = { 5 };
	public static int[] stoneLight = { 0 };

	public static Block stoneRaw;
	public static Block stoneCobble;
	public static Block stoneCleanBricks;
	public static Block stoneCleanFittedBricks;
	public static Block stoneCleanChiseledBricks;
	public static Block stoneEtchedBricks;
	public static Block stoneSmooth;
	public static Block stoneCubed;
	public static Block stoneWideBricks;
	public static Block stoneArtisanChiseled;
	public static Block stoneArtisanRunic;
	public static Block stoneArtisanFocal;
	public static Block stoneEnergistic;
	public static Block stoneOffsetCleanTiles;
	public static Block stoneMixedCleanTiles;
	public static Block stoneOffsetFancyBricks;
	public static Block stoneDetailedBricks;
	public static Block stoneDetailedChiseledBricks;
	public static Block stoneDetailedFittedBricks;
	public static Block stoneDetailedTiles;
	public static Block stoneEngraved;
	public static Block stoneInscribed;
	public static Block stoneGouged;
	public static Block stoneEmbossed;
	public static Block stoneCentered;
	public static Block stoneRaised;
	public static Block stoneEtched;
	public static Block stoneCarved;
	public static Block stoneFancyBricks;
	public static Block stoneCleanTiles;
	public static Block stoneArtisanTiles;
	public static Block stoneSmallOffsetTiles;

    public static Block blockOreMain;
    public static Block blockOreAux;
    public static Block blockStorageMain;
    public static Block blockStorageAux;
    public static Block blockAlloyMain;
    public static Block blockAlloyAux;
    public static Block blockStorageAlch;
    public static Block blockStorageAdv;
    public static Block blockStorageMisc;
    public static Block blockEarthen;
    public static Block blockPoorOreMain;
    public static Block blockPoorOreAux;
    public static Block blockGravelOreMain;
    public static Block blockGravelOreAux;
    public static Block blockTileMetal; //Metallic TileEntities with a static model
    public static Block blockTileTESR; //Metallic TileEntities with a non-static or animated model
    public static Block blockTileWood;

    // Base Items
    public static BucketFluxGear itemBucket;
    public static FluxGearItem itemMaterial; //If the item has no functionality itself (only used in crafting or as fuel, functionality is handled by an inventory, etc.) use this.
    public static ItemInteractivePFG itemInteractive;
    public static FluxGearItem /*FoodPFG*/ itemFood;
    public static ItemPrototypeSonicWrench itemProtoSonicWrench;
	public static Item paintbrush;

    // Fluids
    public static Fluid fluidGhastTear;
    public static Fluid fluidLye;
    public static Fluid fluidAcid;
    public static Fluid fluidEtchingAcid;
    public static Fluid fluidSmog;
    public static Fluid fluidBlood;
    public static Fluid fluidPyrotheum;
    public static Fluid fluidCyrotheum;
    public static Fluid fluidGlowstone;
    public static Fluid fluidRedstone;
    public static Fluid fluidEnder;
    public static Fluid fluidCarbon;
    public static Fluid fluidGelidPyrotheum;
    public static Fluid fluidEssence;
    public static Fluid fluidEctoplasm;
    public static Fluid fluidRedWater;
    public static Fluid fluidUnstableFlowstone;
    public static Fluid fluidEmptyWater;
    public static Fluid fluidUnstableEctoplasm;
    public static Fluid fluidAcidicEssence;
    public static Fluid fluidMercury;
    public static Fluid fluidGallium;
    public static Fluid fluidKiernandio;

    // Fluid Blocks
    public static BlockFluidCoFHBase blockFluidGhastTear;
    public static BlockFluidCoFHBase blockFluidEtchingAcid;
    public static BlockFluidCoFHBase blockFluidSmog;
    public static BlockFluidCoFHBase blockFluidAcid;
    public static BlockFluidCoFHBase blockFluidBlood;


    //Primary Ore Blocks
    public static ItemStack oreChalcocite;      //Cu_2S
    public static ItemStack oreCassiterite;     //SnO_2
    public static ItemStack oreGalena;          //PbS
    public static ItemStack oreAcanthite;       //Ag_2S
    public static ItemStack oreGarnierite;      //(Ni,Mg)_3(Si_4O_10)(OH)_2, Ni_3Si_4O_10(OH)_2*4H_2O, (Ni,Mg)_3(Si_2O_5)(OH)_4, (Ni,Mg)_4Si_6O_15(OH)_2·6H_2O
    public static ItemStack oreSphalerite;      //ZnS
    public static ItemStack oreBismuthinite;    //Bi_2S_3
    public static ItemStack orePyrolusite;      //MnO_2
    public static ItemStack oreBauxite;         //Al(OH)_3, AlO(OH){Boehmite}, AlO(OH){Diaspore}, FeO(OH), Fe^3+_2O_3, TiO_2
    public static ItemStack oreCooperite;       //PtS
    public static ItemStack oreBraggite;        //(Pt,Pd,Ni)S
    public static ItemStack oreMolybdenite;     //MoS_2
    public static ItemStack oreCobaltite;       //CoAsS
    public static ItemStack oreWolframite;      //(Fe^2+,Mn^2+)WO_4
    public static ItemStack oreIlmenite;        //Fe^2+TiO_3
    public static ItemStack oreChromite;        //Fe^2+Cr^3+_2O_4

    //Secondary Ore Blocks
    public static ItemStack oreCinnabar;        //HgS
    public static ItemStack orePitchblende;     //(U,Th{U*3==Th*1})O_2 +As +(Y, Ce, etc.)_2O_3@~10% +CaU(PO_4)_2*1-2H_2O
    public static ItemStack oreMonazite;        //(Ce,La,Nd,Th)(PO_4)
    public static ItemStack oreNiedermayrite;   //CdCu_4(SO_4)_2(OH)_6*4H_2O
    public static ItemStack oreGreenockite;     //CdS
    public static ItemStack oreGaotaiite;       //Ir_3Te_8
    public static ItemStack oreOsarsite;        //(Os,Ru)AsS
    public static ItemStack oreGallobeudanite;  //PbGa_3(AsO_4)(SO_4)(OH)
    public static ItemStack oreZnamenskyite;    //Pb_4In_2Bi_4S_13
    public static ItemStack oreTertahedrite;    //Cu_6[Cu_4(Fe,Zn)_2]Sb_4S_13
    public static ItemStack oreTennantite;      //Cu_6[Cu_4(Fe,Zn)_2]As_4S_13
    public static ItemStack oreSantafeite;      //(Na,Ca,Sr)_12(Mn^2+,Fe^3+,Al,Mg)_8Mn^4+_8(VO_4)_16(OH,O)_20*8H_2O
    public static ItemStack oreMagnetite;       //Fe^2+Fe^3+_2O_4
    public static ItemStack oreDioptase;        //CuSiO_3*H_2O
    public static ItemStack orePyrope;          //Mg_3Al_2(SiO_4)_3
    public static ItemStack oreMyuvil;          //It's Myuvil, we don't make sense of it!

    // Storage Blocks
    public static ItemStack blockCopper;
    public static ItemStack blockTin;
    public static ItemStack blockLead;
    public static ItemStack blockSilver;
    public static ItemStack blockNickel;
    public static ItemStack blockZinc;
    public static ItemStack blockBismuth;
    public static ItemStack blockManganese;
    public static ItemStack blockAluminium;
    public static ItemStack blockPlatinum;
    public static ItemStack blockPalladium;
    public static ItemStack blockMolybdenum;
    public static ItemStack blockCobalt;
    public static ItemStack blockTungsten;
    public static ItemStack blockTitanium;
    public static ItemStack blockChromium;

    public static ItemStack blockAntimony;
    public static ItemStack blockArsenic;
    public static ItemStack blockNeodymium;
    public static ItemStack blockCadmium;
    public static ItemStack blockTellurium;
    public static ItemStack blockOsmium;
    public static ItemStack blockIridium;
    public static ItemStack blockIndium;
    public static ItemStack blockAntimonialBronze;
    public static ItemStack blockArsenicalBronze;
    public static ItemStack blockVanadium;
    public static ItemStack blockTesseractium;
    public static ItemStack blockUnobtainium;
    public static ItemStack blockDioptase;
    public static ItemStack blockPyrope;
    public static ItemStack blockMyuvil;

    public static ItemStack blockBronze;
    public static ItemStack blockBrass;
    public static ItemStack blockInvar;
    public static ItemStack blockBismuthBronze;
    public static ItemStack blockCupronickel;
    public static ItemStack blockAluminiumBrass;
    public static ItemStack blockElectrum;
    public static ItemStack blockDullRedsolder;
    public static ItemStack blockRedsolder;
    public static ItemStack blockHCSteel;
    public static ItemStack blockSteel;
    public static ItemStack blockHSLA;
    public static ItemStack blockStainlessSteel;
    public static ItemStack blockTungstenSteel;
    public static ItemStack blockElectriplatinum;
    public static ItemStack blockMithril;

    public static ItemStack blockTechnomancy;
    public static ItemStack blockResonantTechnomancy;
    public static ItemStack blockTungstenBlazing;
    public static ItemStack blockPlatinumGelid;
    public static ItemStack blockSilverLuminous;
    public static ItemStack blockElectrumFlux;
    public static ItemStack blockMolybdenumResonant;
    public static ItemStack blockChromiumCarbide;
    public static ItemStack blockBismuthBronzeColdfire;
    public static ItemStack blockPyrum;
    public static ItemStack blockGelinium;
    public static ItemStack blockLumium;
    public static ItemStack blockSignalum;
    public static ItemStack blockEnderium;
    public static ItemStack blockCarbonite;
    public static ItemStack blockTherminate;

    public static ItemStack blockNullmetal;
    public static ItemStack blockIocarbide;
    public static ItemStack blockCryocarbide;
    public static ItemStack blockPyrocarbide;
    public static ItemStack blockTenebride;
    public static ItemStack blockIlluminide;
    public static ItemStack blockZythoferride;
    public static ItemStack blockCrystalFlux;
    public static ItemStack blockLapiquartz;
    public static ItemStack blockRust;
    public static ItemStack blockWhitePointStar;
    public static ItemStack blockVoidInfernoStar;
    public static ItemStack blockSulfur;
    public static ItemStack blockSaltpeter;
    public static ItemStack blockMithrilFlux;
    public static ItemStack blockMithrilTinker;

    public static ItemStack blockThorium;
    public static ItemStack blockUranium235;
    public static ItemStack blockUranium238;
    public static ItemStack blockMagnetite;
    public static ItemStack blockNdMagnet;
    public static ItemStack blockFeMagnet;
    public static ItemStack blockMnMagnet;
    public static ItemStack blockCoMagnet;
    public static ItemStack blockNiMagnet;
    public static ItemStack blockInvarMagnet;
    public static ItemStack blockHCSteelMagnet;
    public static ItemStack blockSteelMagnet;
    public static ItemStack blockHSLAMagnet;
    public static ItemStack blockAmber;
    public static ItemStack blockNichrome; //TODO: Make Standard Metal
    public static ItemStack blockPolycarbide;

    public static ItemStack blockIridiumSands;
    public static ItemStack blockPoorIridiumSands;
    public static ItemStack blockAluminosilicateSludge;

    //Primary Poor Ore Blocks
    public static ItemStack orePoorChalcocite;
    public static ItemStack orePoorCassiterite;
    public static ItemStack orePoorGalena;
    public static ItemStack orePoorAcanthite;
    public static ItemStack orePoorGarnierite;
    public static ItemStack orePoorSphalerite;
    public static ItemStack orePoorBismuthinite;
    public static ItemStack orePoorPyrolusite;
    public static ItemStack orePoorBauxite;
    public static ItemStack orePoorCooperite;
    public static ItemStack orePoorBraggite;
    public static ItemStack orePoorMolybdenite;
    public static ItemStack orePoorCobaltite;
    public static ItemStack orePoorWolframite;
    public static ItemStack orePoorIlmenite;
    public static ItemStack orePoorChromite;

    //Secondary Poor Ore Blocks
    public static ItemStack orePoorCinnabar;
    public static ItemStack orePoorPitchblende;
    public static ItemStack orePoorMonazite;
    public static ItemStack orePoorNiedermayrite;
    public static ItemStack orePoorGreenockite;
    public static ItemStack orePoorGaotaiite;
    public static ItemStack orePoorOsarsite;
    public static ItemStack orePoorGallobeudanite;
    public static ItemStack orePoorZnamenskyite;
    public static ItemStack orePoorTertahedrite;
    public static ItemStack orePoorTennantite;
    public static ItemStack orePoorSantafeite;
    public static ItemStack orePoorMagnetite;
    public static ItemStack orePoorDioptase;
    public static ItemStack orePoorPyrope;
    public static ItemStack orePoorMyuvil;

    //Primary Gravel Ore Blocks
    public static ItemStack oreGravelChalcocite;
    public static ItemStack oreGravelCassiterite;
    public static ItemStack oreGravelGalena;
    public static ItemStack oreGravelAcanthite;
    public static ItemStack oreGravelGarnierite;
    public static ItemStack oreGravelSphalerite;
    public static ItemStack oreGravelBismuthinite;
    public static ItemStack oreGravelPyrolusite;
    public static ItemStack oreGravelBauxite;
    public static ItemStack oreGravelCooperite;
    public static ItemStack oreGravelBraggite;
    public static ItemStack oreGravelMolybdenite;
    public static ItemStack oreGravelCobaltite;
    public static ItemStack oreGravelWolframite;
    public static ItemStack oreGravelIlmenite;
    public static ItemStack oreGravelChromite;

    //Secondary Gravel Ore Blocks
    public static ItemStack oreGravelCinnabar;
    public static ItemStack oreGravelPitchblende;
    public static ItemStack oreGravelMonazite;
    public static ItemStack oreGravelNiedermayrite;
    public static ItemStack oreGravelGreenockite;
    public static ItemStack oreGravelGaotaiite;
    public static ItemStack oreGravelOsarsite;
    public static ItemStack oreGravelGallobeudanite;
    public static ItemStack oreGravelZnamenskyite;
    public static ItemStack oreGravelTertahedrite;
    public static ItemStack oreGravelTennantite;
    public static ItemStack oreGravelSantafeite;
    public static ItemStack oreGravelMagnetite;
    public static ItemStack oreGravelDioptase;
    public static ItemStack oreGravelPyrope;
    public static ItemStack oreGravelMyuvil;

    public static ItemStack blockPotato;

    //Buckets
    public static ItemStack bucketGhastTears;
    public static ItemStack bucketLye;
    public static ItemStack bucketAcid;
    public static ItemStack bucketEtchingAcid;
    public static ItemStack bucketSmog;
    public static ItemStack bucketBlood;
    public static ItemStack bucketPyrotheum;
    public static ItemStack bucketCyrotheum;
    public static ItemStack bucketGlowstone;
    public static ItemStack bucketRedstone;
    public static ItemStack bucketEnder;
    public static ItemStack bucketCarbon;
    public static ItemStack bucketGelidPyrotheum;
    public static ItemStack bucketEssence;
    public static ItemStack bucketEctoplasm;
    public static ItemStack bucketRedWater;
    public static ItemStack bucketUnstableFlowstone;
    public static ItemStack bucketEmptyWater;
    public static ItemStack bucketUnstableEctoplasm;
    public static ItemStack bucketAcidicEssence;
    public static ItemStack bucketMercury;
    public static ItemStack bucketGallium;
    public static ItemStack bucketKiernandio;

    // Standard Ingots
    public static ItemStack ingotCopper;
    public static ItemStack ingotTin;
    public static ItemStack ingotLead;
    public static ItemStack ingotSilver;
    public static ItemStack ingotNickel;
    public static ItemStack ingotZinc;
    public static ItemStack ingotBismuth;
    public static ItemStack ingotManganese;
    public static ItemStack ingotAluminium;
    public static ItemStack ingotPlatinum;
    public static ItemStack ingotPalladium;
    public static ItemStack ingotMolybdenum;
    public static ItemStack ingotCobalt;
    public static ItemStack ingotTungsten;
    public static ItemStack ingotTitanium;
    public static ItemStack ingotChromium;
    //* Antimony
    //* Arsenic
    public static ItemStack ingotNeodymium;
    public static ItemStack ingotTesseractium;
    public static ItemStack ingotCadmium;
    public static ItemStack ingotTellurium;
    public static ItemStack ingotOsmium;
    public static ItemStack ingotIridium;
    public static ItemStack ingotIndium;
    public static ItemStack ingotArsenicalBronze;
    public static ItemStack ingotAntimonialBronze;
    public static ItemStack ingotVanadium;
    public static ItemStack ingotUnobtainium;
    public static ItemStack gemDioptase;
    public static ItemStack gemPyrope;
    //* Myuvil
    public static ItemStack ingotBronze;
    public static ItemStack ingotBrass;
    public static ItemStack ingotInvar;
    public static ItemStack ingotBismuthBronze;
    public static ItemStack ingotCupronickel;
    public static ItemStack ingotAluminiumBrass;
    public static ItemStack ingotElectrum;
    public static ItemStack ingotDullRedsolder;
    public static ItemStack ingotRedsolder;
    public static ItemStack ingotHCSteel;
    public static ItemStack ingotSteel;
    public static ItemStack ingotHSLA;
    public static ItemStack ingotStainlessSteel;
    public static ItemStack ingotTungstenSteel;
    public static ItemStack ingotEletriplatinum;
    public static ItemStack ingotMithril;
    public static ItemStack ingotTechnomancer;
    public static ItemStack ingotTechnomancerResonant;
    public static ItemStack ingotTungstenBlazing;
    public static ItemStack ingotPlatinumGelid;
    public static ItemStack ingotSilverLuminous;
    public static ItemStack ingotElectrumFlux;
    public static ItemStack ingotMolybdenumResonant;
    public static ItemStack ingotChromiumCarbide;
    public static ItemStack ingotBismuthBronzeColdfire;
    public static ItemStack ingotPyrum;
    public static ItemStack ingotGelinium;
    public static ItemStack ingotLumium;
    public static ItemStack ingotSignalum;
    public static ItemStack ingotEnderium;
    public static ItemStack ingotCarbonite;
    public static ItemStack ingotTherminate;
    public static ItemStack algotNullmetal;
    public static ItemStack algotIocarbide;
    public static ItemStack algotCryocarbide;
    public static ItemStack algotPyrocarbide;
    public static ItemStack algotTenebride;
    public static ItemStack algotIlluminide;
    public static ItemStack algotZythoferride;
    public static ItemStack gemCrystalFlux;
    //* Lapiquartz
    //* Rust
    //* Sulfur
    //* Saltpeter
    public static ItemStack ingotMithrilFlux;
    public static ItemStack ingotMithrilTinker;
    //* Thorium
    //* U235
    //* U238
    public static ItemStack gemMagnetite;
    public static ItemStack ingotNeodymiumMagnet;
    public static ItemStack ingotIronMagnet;
    public static ItemStack ingotManganeseMagnet;
    public static ItemStack ingotCobaltMagnet;
    public static ItemStack ingotNickelMagnet;
    public static ItemStack ingotInvarMagnet;
    public static ItemStack ingotHCSteelMagnet;
    public static ItemStack ingotSteelMagnet;
    public static ItemStack ingotHSLAMagnet;
    public static ItemStack gemAmber;
    public static ItemStack ingotNichrome;
    public static ItemStack ingotPolycarbide;
    public static ItemStack ingotVorpal;
    //* Ashes
    //* Iron
    //* Gold
    //* Diamond
    //* Coal
    //* Charcoal
    //* Obsidian
    //* Blizz Powder
    //* Cyrotheum
    //* Pyrotheum
    //* Mana
    //* Iceflame
    //* Kroostyl
    public static ItemStack ingotYttrium;
    public static ItemStack ingotRuthenium;
    public static ItemStack ingotLanthanum;
    public static ItemStack ingotCerium;
    //* Magnesium
    //* Calcium
    //* Strontium

    public static ItemStack dustCopper;
    public static ItemStack dustTin;
    public static ItemStack dustLead;
    public static ItemStack dustSilver;
    public static ItemStack dustNickel;
    public static ItemStack dustZinc;
    public static ItemStack dustBismuth;
    public static ItemStack dustManganese;
    public static ItemStack dustAluminium;
    public static ItemStack dustPlatinum;
    public static ItemStack dustPalladium;
    public static ItemStack dustMolybdenum;
    public static ItemStack dustCobalt;
    public static ItemStack dustTungsten;
    public static ItemStack dustTitanium;
    public static ItemStack dustChromium;
    public static ItemStack dustAntimony;
    public static ItemStack dustArsenic;
    public static ItemStack dustNeodymium;
    public static ItemStack dustTesseractium;
    public static ItemStack dustCadmium;
    public static ItemStack dustTellurium;
    public static ItemStack dustOsmium;
    public static ItemStack dustIridium;
    public static ItemStack dustIndium;
    public static ItemStack dustAntimonialBronze;
    public static ItemStack dustArsenicalBronze;
    public static ItemStack dustVanadium;
    public static ItemStack dustUnobtainium;
    public static ItemStack dustDioptase;
    public static ItemStack dustPyrope;
    public static ItemStack dustMyuvil;
    public static ItemStack dustBronze;
    public static ItemStack dustBrass;
    public static ItemStack dustInvar;
    public static ItemStack dustBismuthBronze;
    public static ItemStack dustCupronickel;
    public static ItemStack dustAluminiumBrass;
    public static ItemStack dustElectrum;
    public static ItemStack dustDullRedsolder;
    public static ItemStack dustRedsolder;
    public static ItemStack dustHCSteel;
    public static ItemStack dustSteel;
    public static ItemStack dustHSLA;
    public static ItemStack dustStainlessSteel;
    public static ItemStack dustTungstenSteel;
    public static ItemStack dustEletriplatinum;
    public static ItemStack dustMithril;
    public static ItemStack dustTechnomancer;
    public static ItemStack dustTechnomancerResonant;
    public static ItemStack dustTungstenBlazing;
    public static ItemStack dustPlatinumGelid;
    public static ItemStack dustSilverLuminous;
    public static ItemStack dustElectrumFlux;
    public static ItemStack dustMolybdenumResonant;
    public static ItemStack dustChromiumCarbide;
    public static ItemStack dustBismuthBronzeColdfire;
    public static ItemStack dustCarbonite;
    public static ItemStack dustPyrum;
    public static ItemStack dustLumium;
    public static ItemStack dustSignalum;
    public static ItemStack dustEnderium;
    public static ItemStack dustGelinium;
    public static ItemStack dustTherminate;
    public static ItemStack dustNullmetal;
    public static ItemStack dustIocarbide;
    public static ItemStack dustCryocarbide;
    public static ItemStack dustPyrocarbide;
    public static ItemStack dustTenebride;
    public static ItemStack dustIlluminide;
    public static ItemStack dustZythoferride;
    public static ItemStack dustCrystalFlux;
    public static ItemStack dustLapiquartz;
    public static ItemStack dustRust;
    public static ItemStack dustSulfur;
    public static ItemStack dustSaltpeter;
    public static ItemStack dustMithrilFlux;
    public static ItemStack dustMithrilTinker;
    public static ItemStack dustThorium;
    public static ItemStack dustUranium235;
    public static ItemStack dustUranium238;
    public static ItemStack dustMagnetite;
    public static ItemStack dustNeodymiumMagnet;
    public static ItemStack dustIronMagnet;
    public static ItemStack dustManganeseMagnet;
    public static ItemStack dustCobaltMagnet;
    public static ItemStack dustNickelMagnet;
    public static ItemStack dustInvarMagnet;
    public static ItemStack dustHCSteelMagnet;
    public static ItemStack dustSteelMagnet;
    public static ItemStack dustHSLAMagnet;
    //* Amber
    public static ItemStack dustNichrome;
    public static ItemStack dustPolycarbide;
    public static ItemStack dustVorpal;
    public static ItemStack dustAshes;
    public static ItemStack dustIron;
    public static ItemStack dustGold;
    public static ItemStack dustDiamond;
    public static ItemStack dustCoal;
    public static ItemStack dustCharcoal;
    public static ItemStack dustObsidian;
    public static ItemStack dustBlizz;
    public static ItemStack dustCyrotheum;
    public static ItemStack dustPyrotheum;
    public static ItemStack dustIceflame;
    public static ItemStack dustKroostyl;
    public static ItemStack dustYttrium;
    public static ItemStack dustRuthenium;
    public static ItemStack dustLanthanum;
    public static ItemStack dustCerium;
    public static ItemStack dustMagnesium;
    public static ItemStack dustCalcium;
    public static ItemStack dustStrontium;

    public static ItemStack nuggetCopper;
    public static ItemStack nuggetTin;
    public static ItemStack nuggetLead;
    public static ItemStack nuggetSilver;
    public static ItemStack nuggetNickel;
    public static ItemStack nuggetZinc;
    public static ItemStack nuggetBismuth;
    public static ItemStack nuggetManganese;
    public static ItemStack nuggetAluminium;
    public static ItemStack nuggetPlatinum;
    public static ItemStack nuggetPalladium;
    public static ItemStack nuggetMolybdenum;
    public static ItemStack nuggetCobalt;
    public static ItemStack nuggetTungsten;
    public static ItemStack nuggetTitanium;
    public static ItemStack nuggetChromium;
    //* Antimony
    //* Arsenic
    public static ItemStack nuggetNeodymium;
    public static ItemStack nuggetTesseractium;
    public static ItemStack nuggetCadmium;
    public static ItemStack nuggetTellurium;
    public static ItemStack nuggetOsmium;
    public static ItemStack nuggetIridium;
    public static ItemStack nuggetIndium;
    public static ItemStack nuggetAntimonialBronze;
    public static ItemStack nuggetArsenicalBronze;
    public static ItemStack nuggetVanadium;
    public static ItemStack nuggetUnobtainium;
    public static ItemStack nuggetDioptase;
    public static ItemStack nuggetPyrope;
    //* Myuvil
    public static ItemStack nuggetBronze;
    public static ItemStack nuggetBrass;
    public static ItemStack nuggetInvar;
    public static ItemStack nuggetBismuthBronze;
    public static ItemStack nuggetCupronickel;
    public static ItemStack nuggetAluminiumBrass;
    public static ItemStack nuggetElectrum;
    public static ItemStack nuggetDullRedsolder;
    public static ItemStack nuggetRedsolder;
    public static ItemStack nuggetHCSteel;
    public static ItemStack nuggetSteel;
    public static ItemStack nuggetHSLA;
    public static ItemStack nuggetStainlessSteel;
    public static ItemStack nuggetTungstenSteel;
    public static ItemStack nuggetEletriplatinum;
    public static ItemStack nuggetMithril;
    public static ItemStack nuggetTechnomancer;
    public static ItemStack nuggetTechnomancerResonant;
    public static ItemStack nuggetTungstenBlazing;
    public static ItemStack nuggetPlatinumGelid;
    public static ItemStack nuggetSilverLuminous;
    public static ItemStack nuggetElectrumFlux;
    public static ItemStack nuggetMolybdenumResonant;
    public static ItemStack nuggetChromiumCarbide;
    public static ItemStack nuggetBismuthBronzeColdfire;
    public static ItemStack nuggetPyrum;
    public static ItemStack nuggetGelinium;
    public static ItemStack nuggetLumium;
    public static ItemStack nuggetSignalum;
    public static ItemStack nuggetEnderium;
    public static ItemStack nuggetCarbonite;
    public static ItemStack nuggetTherminate;
    public static ItemStack nuggetNullmetal;
    public static ItemStack nuggetIocarbide;
    public static ItemStack nuggetCryocarbide;
    public static ItemStack nuggetPyrocarbide;
    public static ItemStack nuggetTenebride;
    public static ItemStack nuggetIlluminide;
    public static ItemStack nuggetZythoferride;
    public static ItemStack nuggetCrystalFlux;
    //* Lapiquartz
    //* Rust
    //* Sulfur
    //* Saltpeter
    public static ItemStack nuggetMithrilFlux;
    public static ItemStack nuggetMithrilTinker;
    //* Thorium
    //* U235
    //* U238
    public static ItemStack nuggetMagnetite;
    public static ItemStack nuggetNeodymiumMagnet;
    public static ItemStack nuggetIronMagnet;
    public static ItemStack nuggetManganeseMagnet;
    public static ItemStack nuggetCobaltMagnet;
    public static ItemStack nuggetNickelMagnet;
    public static ItemStack nuggetInvarMagnet;
    public static ItemStack nuggetHCSteelMagnet;
    public static ItemStack nuggetSteelMagnet;
    public static ItemStack nuggetHSLAMagnet;
    //* Amber
    public static ItemStack nuggetNichrome;
    public static ItemStack nuggetPolycarbide;
    public static ItemStack nuggetVorpal;
    //* Ashes
    //* Iron
    //* Gold
    //* Diamond
    //* Coal
    //* Charcoal
    //* Obsidian
    //* Blizz
    //* Cryotheum
    //* Pyrotheum
    //* Iceflame
    public static ItemStack nuggetKroostyl;
    public static ItemStack nuggetYttrium;
    public static ItemStack nuggetRuthenium;
    public static ItemStack nuggetLanthanum;
    public static ItemStack nuggetCerium;
    //* Magnesium
    //* Calcium
    //* Strontium


    // Random Dusts
    public static ItemStack dustPitchblende;
    public static ItemStack dustNiedermayrite;

    // Gems
    public static ItemStack gemMolybdenum;
    public static ItemStack crystalMolybdenum;
    public static ItemStack crystalDioptase;
    public static ItemStack crystalPyrope;
    public static ItemStack gemNaturalAmber;
    public static ItemStack gemSyntheticMolybdenum;
    public static ItemStack gemSyntheticDioptase;
    public static ItemStack gemSyntheticPyrope;
    public static ItemStack gemRoughDioptase;
    public static ItemStack gemRoughPyrope;
    public static ItemStack gemSyntheticGreen;

    // Crafting Parts
    public static ItemStack partWiring;
    public static ItemStack partCircuitPlate;
    public static ItemStack partUnprocessedPCB;
    public static ItemStack partUnassembledPCB;
    public static ItemStack partAssembledPCB;
    public static ItemStack partTransistor;
    public static ItemStack partResistor;
    public static ItemStack partSpring;
    public static ItemStack partFluxFilter;
    public static ItemStack partIonThruster;
    public static ItemStack partResonantThruster;
    public static ItemStack partMagnet;
    public static ItemStack partAlCoNiMagnet;
    public static ItemStack partServoMotor;
    public static ItemStack partSolenoid;
    public static ItemStack partGearCore;
    public static ItemStack partGearBushing;
    public static ItemStack coilHeatingRedstone;
    public static ItemStack coilHeatingCupronickel;
    public static ItemStack coilSteel;
    public static ItemStack partRedLED;
    public static ItemStack partGreenLED;
    public static ItemStack partBlueLED;
    public static ItemStack partUltravioletLight;
    public static ItemStack partFluxResonator;
    public static ItemStack partTankInternal;
    public static ItemStack partTankPressurized;
    public static ItemStack coilSignalum;
    public static ItemStack circuitMagitech;
    public static ItemStack partKkaylionicRainbow;
    public static ItemStack partImpeller;
    public static ItemStack partSaw;
    public static ItemStack partRotor;
    public static ItemStack partGearbox;
    public static ItemStack partTurbine;
    public static ItemStack circuitObscurity;
    public static ItemStack partShaftSteel;
    public static ItemStack partScreen;
    public static ItemStack partPropellerBlade;
    public static ItemStack partGrindingHeadTungsten;
    public static ItemStack partFluxMotor;
    public static ItemStack partMagneticGenerator;
    public static ItemStack partElectromagnetStandard;
    public static ItemStack partElectromagnetNeodymium;
    public static ItemStack partElectrode;
    public static ItemStack partOmnidirectionalHinge;
    public static ItemStack partLaserEmitter;
    public static ItemStack partFiberOpticCable;
    public static ItemStack partMatterConverter;
    public static ItemStack partFluxLaser;
    public static ItemStack partCopperPipe;
    public static ItemStack partPump;
    public static ItemStack partMirror;
    public static ItemStack partKeyboard;
    public static ItemStack partMouse;
    public static ItemStack partRAM;
    public static ItemStack partProcessor;
    public static ItemStack partMixer;
    public static ItemStack partHeadReader;
    public static ItemStack partLaserDetector;
    public static ItemStack partOblivionContinuum;

    // Capacitors
    public static ItemStack partCapacitorLv1;

    // White Point Stars and Void Inferno Stars
    public static ItemStack shardWhitePointStar;
    public static ItemStack gemWhitePointStar;
    public static ItemStack gemWhitePointStarInfused;
    public static ItemStack shardVoidInfernoStar;
    public static ItemStack shardVoidInfernoStarActive;
    public static ItemStack shardVoidInfernoStarEnergized;
    public static ItemStack shardVoidInfernoStarResonant;
    public static ItemStack shardVoidInfernoStarColdfire;
    public static ItemStack shardVoidInfernoStarQuantum;
    public static ItemStack shardVoidInfernoStarEntangled;
    public static ItemStack gemVoidInfernoStarDull;
    public static ItemStack gemVoidInfernoStar;
    public static ItemStack gemVoidInfernoStarFluxed;

    // Magnetar and Quasar Parts

    // Random Stuff
    public static ItemStack feeshSkeleton;

    // Interactive Items
    public static ItemStack dustThermite;
    public static ItemStack coagulantAlum;

    // Food
    public static ItemStack foodMelonPan;

    public static ItemStack toolProtoSonicWrench;

    public static Item itemQuantumCapacitor;

    public static ItemArmorRF itemHelmetInvarRF;
    public static ItemArmorRF itemChestInvarRF;
    public static ItemArmorRF itemLegsInvarRF;
    public static ItemArmorRF itemBootsInvarRF;

	final static short WILD = Short.MAX_VALUE;

	public static ItemStack multicoreProcessor;
	public static ItemStack disassemblyCore;
	public static ItemStack enderMiningCore;
	public static ItemStack enderCompCore;
	public static ItemStack dummyRotaryTitanium;
	public static ItemStack heatingCoil;
	public static ItemStack damascusSteelFrame;
	public static ItemStack ceramicPanel;
	public static ItemStack ceramicPanelRaw;
	public static ItemStack ceramicMix;
	public static ItemStack timeyWimeyCarboard;
	public static ItemStack denseRedstone;
	public static ItemStack dustEmerald;
	public static ItemStack dustRefinedEnder;
	public static ItemStack dustBerylliumMix;
	public static ItemStack dustBeryllium;
	public static ItemStack ingotBeryllium;
	public static ItemStack iodine;
	public static ItemStack potassiumIodide;
	public static ItemStack enrichedSalt;
	public static ItemStack ingotPlasteel;

	public static Fluid fluidPlastic;
	public static Fluid fluidPlasteel;

	public void loadStuff() {
		itemMaterial = (FluxGearItem) new FluxGearItem("oldcode/experditio").setUnlocalizedName("material").setCreativeTab(CreativeTabs.tabMisc);
		itemProtoSonicWrench = (ItemPrototypeSonicWrench) new ItemPrototypeSonicWrench().setUnlocalizedName("tool", "prototypeSonicWrench");

		toolProtoSonicWrench = itemProtoSonicWrench.addItem(0, "protoSonicWrench", 1);

		multicoreProcessor = itemMaterial.addItem(0, "multicoreProcessor");
		disassemblyCore = itemMaterial.addItem(1, "disassemblyCore");
		enderMiningCore = itemMaterial.addItem(2, "enderMiningCore");
		enderCompCore = itemMaterial.addItem(3, "enderCompCore");
		dummyRotaryTitanium = itemMaterial.addOreDictItem(5, "blastFurnaceMechanism", "ingotTitanium");
		dustCupronickel = itemMaterial.addOreDictItem(6 , "dustCupronickel");
		ingotCupronickel = itemMaterial.addOreDictItem(7 , "ingotCupronickel");
		nuggetCupronickel = itemMaterial.addOreDictItem(8 , "nuggetCupronickel");
		heatingCoil = itemMaterial.addItem(9 , "heatingCoil");
		damascusSteelFrame = itemMaterial.addItem(10 , "damascusSteelFrame");
		ceramicPanel = itemMaterial.addItem(11 , "ceramicPanel");
		ceramicPanelRaw = itemMaterial.addItem(12 , "ceramicPanelRaw");
		ceramicMix = itemMaterial.addItem(13 , "ceramicMix");
		timeyWimeyCarboard = itemMaterial.addItem(14, "timeyWimeyCardboard");
		denseRedstone = itemMaterial.addOreDictItem(15, "denseRedstone", "dustDenseRedstone");

		dustEmerald = itemMaterial.addOreDictItem(16, "dustEmerald");
		dustRefinedEnder = itemMaterial.addOreDictItem(17, "dustRefinedEnder");
		dustBerylliumMix = itemMaterial.addOreDictItem(18, "dustBerylliumMix");
		dustBeryllium = itemMaterial.addOreDictItem(19, "dustBeryllium");
		ingotBeryllium = itemMaterial.addOreDictItem(20, "ingotBeryllium");
		iodine = itemMaterial.addItem(21, "iodine");
		potassiumIodide = itemMaterial.addItem(22, "potassiumIodide");
		enrichedSalt = itemMaterial.addItem(23, "enrichedSalt");
		ingotPlasteel = itemMaterial.addOreDictItem(24, "ingotPlasteel");

		ItemStack resEndBucket = new ItemStack(GameRegistry.findItem("ThermalFoundation", "bucket"), 1, 2);
		ItemStack endCrystal = new ItemStack(GameRegistry.findItem("EnderIO", "itemMaterial"), 1, 8);
		ItemStack fluxPlate = new ItemStack(GameRegistry.findItem("RedstoneArsenal", "material"), 1, 128);
		boolean enderium = (LoadedHelper.isThermalExpansionLoaded || (LoadedHelper.isEnderIOLoaded && LoadedHelper.isThermalFoundationLoaded));

		GameRegistry.addRecipe(new ShapedOreRecipe(timeyWimeyCarboard, "pep", "nbd", "prp", 'p', Items.paper, 'e', LoadedHelper.isThermalExpansionLoaded ? resEndBucket : LoadedHelper.isEnderIOLoaded ? endCrystal : Items.ender_eye, 'r', LoadedHelper.isEnderIOLoaded ? endCrystal : LoadedHelper.isThermalExpansionLoaded ? resEndBucket : Items.ender_eye, 'n', enderium ? "ingotEnderium" : LoadedHelper.isRedstoneArsenalLoaded ? fluxPlate : "ingotGold", 'd', LoadedHelper.isRedstoneArsenalLoaded ? fluxPlate : enderium ? "ingotEnderium" : "ingotGold", 'b', LoadedHelper.isTinkersLoaded ? new ItemStack(GameRegistry.findItem("TConstruct", "slime.gel"), 1, 2) : LoadedHelper.isMFRLoaded ? new ItemStack(GameRegistry.findItem("MineFactoryReloaded", "pinkslime.block")) : LoadedHelper.isReliquaryLoaded ? new ItemStack(GameRegistry.findItem("xreliquary", "mob_ingredient"), 1, 4) : LoadedHelper.isBloodMagicLoaded ? new ItemStack(GameRegistry.findItem("AWWayofTime", "standardBindingAgent")) : LoadedHelper.isMagicBeesLoaded ? new ItemStack(GameRegistry.findItem("MagicBees", "propolis")) : LoadedHelper.isExtraTreesLoaded ? new ItemStack(GameRegistry.findItem("BinnieCore", "containerBucket"), 1, 129) : LoadedHelper.isForestryLoaded ? new ItemStack(GameRegistry.findItem("Forestry", "propolis")) : LoadedHelper.isBoPLoaded ? new ItemStack(GameRegistry.findItem("BiomesOPlenty", "bopBucket")) : LoadedHelper.isMetallurgyLoaded ? new ItemStack(GameRegistry.findItem("Metallurgy", "tar")) : LoadedHelper.isEnderIOLoaded ? new ItemStack(GameRegistry.findItem("EnderIO", "itemMaterial"), 1, 1) : Items.magma_cream));
		if (LoadedHelper.isForestryLoaded && FluxGearConfig.meadCardboard) {
			GameRegistry.addRecipe(new ShapedOreRecipe(timeyWimeyCarboard, "pep", "nbd", "prp", 'p', Items.paper, 'e', LoadedHelper.isThermalExpansionLoaded ? resEndBucket : LoadedHelper.isEnderIOLoaded ? endCrystal : Items.ender_eye, 'r', LoadedHelper.isEnderIOLoaded ? endCrystal : LoadedHelper.isThermalExpansionLoaded ? resEndBucket : Items.ender_eye, 'n', enderium ? "ingotEnderium" : LoadedHelper.isRedstoneArsenalLoaded ? fluxPlate : "ingotGold", 'd', LoadedHelper.isRedstoneArsenalLoaded ? fluxPlate : enderium ? "ingotEnderium" : "ingotGold", 'b', GameRegistry.findItem("Forestry", "beverage")));
		}
		String rs = "dustRedstone";
		GameRegistry.addRecipe(new ShapelessOreRecipe(ItemHelper.cloneStack(denseRedstone, 6), rs, rs, rs, rs, rs, rs, rs, rs));

	}

	public static void registerMaterials() {
		//ToolHandler.registerMaterials();
		TinkersHelper.registerMaterial(FluxGearConfig.tinkersID_Plasteel, "materialPlasteel", 4, 1500, 700, 8, 1.3F, 2, 0x91A5B3, 0, 5.0F, 7, 4.0F, EnumChatFormatting.DARK_GRAY.toString());
		TinkersHelper.registerMaterial(FluxGearConfig.tinkersID_ManaMithral, "materialManaMithral", 6, 2100, 1150, 14, 2.1F, 2, 0xC19BEB, 7, 8.0F, 10, 8.0F, EnumChatFormatting.AQUA.toString());
		TinkersHelper.registerMaterial(FluxGearConfig.tinkersID_Enderium, "materialEnderium", 6, 1850, 2150, 16, 2.5F, 2, 0x148C99, 9, 0.2F, 6, 3.0F, EnumChatFormatting.DARK_AQUA.toString());
		TinkersHelper.registerMaterial(FluxGearConfig.tinkersID_Lumium, "materialLumium", 3, 800, 1100, 6, 2.5F, 0, 0xFAFA9B, 2, 0.5F, 2, 8.0F, EnumChatFormatting.GOLD.toString());
		TinkersHelper.registerMaterial(FluxGearConfig.tinkersID_Signalum, "materialSignalum", 3, 800, 1300, 11, 1.0F, 0, 0xF78B3E, 3, 0.8F, 3, 7.0F, EnumChatFormatting.RED.toString());

		if (FluxGearConfig.tinkersID_Plasteel != -1) {
			fluidPlastic = MiscHelper.registerColoredFluid("plastic", 0xABABAB);
			fluidPlasteel = MiscHelper.registerColoredFluid("plasteel", 0x91A5B3);

			TinkersHelper.registerFluidType("Plastic", TinkersHelper.glueBlock, 0, 200, fluidPlastic, true);
			TinkersHelper.registerFluidType("Plasteel", TinkersHelper.glueBlock, 0, 200, fluidPlasteel, true);
		}

	}

	public static void initMaterials() {

		TinkersHelper.registerFullOreDictMaterial("ingotPlasteel", "materialPlasteel", FluxGearConfig.tinkersID_Plasteel);
		TinkersHelper.registerFullOreDictMaterial("ingotManaMithral", "materialManaMithral", FluxGearConfig.tinkersID_ManaMithral);
		TinkersHelper.registerFullOreDictMaterial("ingotEnderium", "materialEnderium", FluxGearConfig.tinkersID_Enderium);
		TinkersHelper.registerFullOreDictMaterial("ingotLumium", "materialLumium", FluxGearConfig.tinkersID_Lumium);
		TinkersHelper.registerFullOreDictMaterial("ingotSignalum", "materialSignalum", FluxGearConfig.tinkersID_Signalum);

		if (FluxGearConfig.tinkersID_Plasteel != -1) {
			TinkersHelper.addOreDictSmelting("dustPlastic", "Plastic", TinkersHelper.getIngotFluidValue() / 4);
			TinkersHelper.addOreDictSmelting("sheetPlastic", "Plastic", TinkersHelper.getIngotFluidValue() / 4);
		}

		TinkersHelper.registerCasting(fluidPlasteel, FluxGearConfig.tinkersID_Plasteel, ingotPlasteel, "Plasteel");

		TinkersHelper.registerCasting(TinkersHelper.moltenEnderium, FluxGearConfig.tinkersID_Enderium, "ingotEnderium");
		TinkersHelper.registerCasting(TinkersHelper.moltenLumium, FluxGearConfig.tinkersID_Lumium, "ingotLumium");
		TinkersHelper.registerCasting(TinkersHelper.moltenSignalum, FluxGearConfig.tinkersID_Signalum, "ingotSignalum");
		TinkersHelper.registerCasting(TinkersHelper.moltenManaMithral, FluxGearConfig.tinkersID_ManaMithral, FluxGearConfig.tweakManaMithral ? "ingotManaMithral" : "ingotMithril");

		TinkersHelper.addAlloying(new FluidStack(fluidPlasteel, 2), new FluidStack(TinkersHelper.moltenSteel, 2), new FluidStack(fluidPlastic, 1));
		TinkersHelper.registerActiveToolMod(new ActiveToolModFeedback());
	}

    //Pile of Weird Science Legacy Code
    public static void RegisterContent (ContentRegistry cr) {
        //Constants.
        final int smogDetailDefault = 8;
        //Init fluids.
        BlockFluidAcid fluidAcid = new BlockFluidAcid("acid");
        BlockFluidAcid fluidBase = new BlockFluidAcid("base");
        BlockFluidSmog fluidSmog = new BlockFluidSmog("smog");
        Fluid fluidBlood = new Fluid("blood");
        fluidAcid.setUnlocalizedName("fluidAcid");
        fluidSmog.setUnlocalizedName("fluidSmog");
        fluidBlood.setUnlocalizedName("fluidBlood");

        //Register fluids.
	    //TODO: Not only is the whole ContentRegistry thing poorly implemented, but the Fluid blocks are done all wrong.
	    //TODO: Actually they are not even Blocks because Gyro's derpcode...
        cr.registerFluid(fluidAcid);
        cr.registerFluid(fluidBase);
        //cr.registerFluid(fluidSmog); //TODO
        cr.registerFluid(fluidBlood);

        //Init fluid blocks.
        //Fluids used must be registered first.
        BlockFluidClassicWS acidBlock = new BlockFluidReactive(fluidAcid);
        BlockFluidClassicWS baseBlock = new BlockFluidReactive(fluidBase);
        BlockFluidClassicWS bloodBlock = new BlockFluidClassicWS("Blood", Material.water, fluidBlood);

        //Ugly gas init code goes here. //TODO
        /*GasWrapper smogManager = new GasWrapper(new GasFactory() {
            public BlockGasBase Make (Configuration config, String name, Fluid fluid) {
                return new BlockGasSmog(config, name, fluid);
            }
        }, "Smog", fluidSmog, smogDetailDefault);
        cr.generalRegister(smogManager);
        //Slaving multiple block IDs to one set of behavior is such a pain in this game.
        ((BlockGasSmog) smogManager.blocks.get(0)).setBlockAcid(acidBlock); */


	    acidBlock.setTextureName("gui:placeholderacid");
        baseBlock.setTextureName("gui:placeholderbase");
        bloodBlock.setTextureName("gui:bloodStill");
        // smogManager.setTextureName("gui:smog"); //TODO

        acidBlock.setUnlocalizedName("blockAcid");
        bloodBlock.setUnlocalizedName("blockBlood");

        // smogManager.setMBMax(1024); //TODO

        //Give fluids block IDs and icons.
        fluidAcid.setBlock(acidBlock);
        fluidBlood.setBlock(bloodBlock);
        fluidBase.setBlock(baseBlock);

        /*if (event.getSide() == Side.CLIENT) {
            fluidAcid.setIcons(acidBlock.getIcon(0, 0));
            fluidBase.setIcons(baseBlock.getIcon(0, 0));
            fluidBlood.setIcons(bloodBlock.getIcon(0, 0));
        }

        if (smogManager.isEnabled()) {
            if (event.getSide() == Side.CLIENT) {
                fluidSmog.setIcons(smogManager.blocks.get(0).getIcon(0, 0));
            }
            fluidSmog.setBlockID(smogManager.blocks.get(0).blockID);
        }*/ //TODO

        //Register normal fluid blocks
        cr.registerBlock(acidBlock);
        cr.registerBlock(baseBlock);
        cr.registerBlock(bloodBlock);

        BlockFluxGear aluminiumSludge = new BlockFluxGear("Aluminosilicate Sludge", Material.clay);
        aluminiumSludge.setTextureName("gui:aluminosilicate_sludge");
        aluminiumSludge.harvestType = "shovel";
        aluminiumSludge.harvestLevel = 0;
        aluminiumSludge.setHardness(0.3F);
        cr.registerBlock(aluminiumSludge);

        /*((BlockGasSmog) smogManager.blocks.get(0)).blockRust = blockRust;
        ((BlockGasSmog) smogManager.blocks.get(0)).metaRust = 0;*/ //TODO

        //Init & register tile-entity-bearing blocks.

        BlockNitrateEngine nitrateEngineBlock = new BlockNitrateEngine(Material.rock, "blockNitrateEngine");
        //BlockNitrateEngine.setWaste(fluidSmog); //TODO
        cr.registerBlock(nitrateEngineBlock);

        BlockBloodDyanmo bloodEngineBlock = new BlockBloodDyanmo(Material.rock, "Hemoionic Dynamo");
        bloodEngineBlock.setTextureName("gui:genericmachine");
        bloodEngineBlock.addTopTextureName("gui:genericmachine6_off");
        bloodEngineBlock.addTopTextureName("gui:genericmachine6_on");
        bloodEngineBlock.addSidesTextureName("gui:genericmachine_tank_0");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_1");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_2");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_3");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_4");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_5");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_6");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_7");
        bloodEngineBlock.addSidesTextureName("gui:blood_tank_8");
        cr.registerBlock(bloodEngineBlock);

        BlockBloodDonation donationBlock = new BlockBloodDonation(Material.rock, "Blood Donation Station");
        donationBlock.setUnlocalizedName("blockBloodDonation");
        BlockBloodDonation.setFluid(fluidBlood);
        donationBlock.setTextureName("gui:genericmachine");
        donationBlock.addTopTextureName("gui:blooddonationtop");
        donationBlock.addTopTextureName("gui:blooddonationtop");
        donationBlock.addSidesTextureName("gui:genericmachine_tank_0");
        donationBlock.addSidesTextureName("gui:blood_tank_1");
        donationBlock.addSidesTextureName("gui:blood_tank_2");
        donationBlock.addSidesTextureName("gui:blood_tank_3");
        donationBlock.addSidesTextureName("gui:blood_tank_4");
        donationBlock.addSidesTextureName("gui:blood_tank_5");
        donationBlock.addSidesTextureName("gui:blood_tank_6");
        donationBlock.addSidesTextureName("gui:blood_tank_7");
        donationBlock.addSidesTextureName("gui:blood_tank_8");
        cr.registerBlock(donationBlock);

        BlockOccultEngine occultEngineBlock = new BlockOccultEngine(Material.rock, "Occult Engine");
        occultEngineBlock.setTextureName("gui:occultengine_bottom");
        occultEngineBlock.addTopTextureName("gui:occultengine_top");
        occultEngineBlock.addSidesTextureName("gui:occultengine_empty");
        occultEngineBlock.addSidesTextureName("gui:occultengine_1");
        occultEngineBlock.addSidesTextureName("gui:occultengine_2");
        occultEngineBlock.addSidesTextureName("gui:occultengine_3");
        occultEngineBlock.addSidesTextureName("gui:occultengine_4");
        occultEngineBlock.addSidesTextureName("gui:occultengine_5");
        occultEngineBlock.addSidesTextureName("gui:occultengine_6");
        cr.registerBlock(occultEngineBlock);

        BlockGunpowderEngine gunpowderEngineBlock = new BlockGunpowderEngine(Material.rock, "blockGunpowderEngine");
        cr.registerBlock(gunpowderEngineBlock);

        BlockFuelBurner fuelBurnerBlock = new BlockFuelBurner(Material.rock, "blockFuelBurner");
        fuelBurnerBlock.setTextureName("gui:retardcube");
        cr.registerBlock(fuelBurnerBlock);

        //Init and register items.
        TileEntityGunpowderDynamo.thermite = dustThermite;

        //Register chemistry.
        //Clay to slurry reaction.
        ReactionSpec clayDissolve = new ReactionSpec(fluidAcid, new ItemStack(Items.clay_ball), aluminiumSludge, null, false, true, 0, 4); //Require 4 clay and delete the clay item when the reaction takes place.
        cr.registerReaction(clayDissolve);

        //Alum to aluminum dust reaction.
        ReactionSpec alumDissolve = new ReactionSpec(fluidBase, coagulantAlum, null, dustAluminium, true, false);
        cr.registerReaction(alumDissolve);

        //Acids and bases kill grass dead.
        ReactionSpec grassDissolveAcid = new ReactionSpec(fluidAcid, Blocks.grass, null, Blocks.dirt, false, true);
        cr.registerReaction(grassDissolveAcid);

        ReactionSpec grassDissolveBase = new ReactionSpec(fluidBase, Blocks.grass, null, Blocks.dirt, false, true);
        cr.registerReaction(grassDissolveBase);

        ArrayList<ItemStack> aluminiumIngots = OreDictionary.getOres("ingotAluminium");
        aluminiumIngots.addAll(OreDictionary.getOres("ingotAluminum"));

        ArrayList<ItemStack> aluminiumOres = OreDictionary.getOres("oreAluminium");
        aluminiumOres.addAll(OreDictionary.getOres("oreAluminum"));
        aluminiumOres.addAll(OreDictionary.getOres("oreBauxite"));
        //Register aluminum ingot dissolution.
        if (aluminiumIngots.size() > 0) {
            for (ItemStack item : aluminiumIngots) {
                ReactionSpec aluminumDissolve = new ReactionSpec(fluidAcid, item.copy(), null, coagulantAlum);
                aluminumDissolve.soluteMin = 1; //Should be 1 to 1
                aluminumDissolve.soluteAffected = true;
                aluminumDissolve.solventAffected = false;
                cr.registerReaction(aluminumDissolve);
            }
        }

        //Register aluminum ore dissolution.
        if (aluminiumOres.size() > 0) {
            for (ItemStack item : aluminiumOres) {
                /* Note the stack size of 5: This allows ore quintupling early-game for those willing to spend the effort and fuel
                 * to go the Ore -> Aluminosillicate Slurry -> Alum -> Dissolved Alum -> Aluminium Dust -> Aluminium Ingot path.
                 */
	            //TODO: Maybe 2.5 Al + 2 Fe + .5 Other for Bauxite
                ReactionSpec aluminumDissolve = new ReactionSpec(fluidAcid, item.copy(), null, new ItemStack(aluminiumSludge, 5, 0));
                aluminumDissolve.soluteMin = 1; //Should be 1 to 1
                aluminumDissolve.soluteAffected = true;
                aluminumDissolve.solventAffected = false;
                cr.registerReaction(aluminumDissolve);
            }
        }

        //Register furnace stuff.
        //Wood to ashes smelting.
        ArrayList<ItemStack> woodPlanks = OreDictionary.getOres("plankWood");
        if (woodPlanks != null && woodPlanks.size() > 0) {
            for (ItemStack item : woodPlanks) {
                GameRegistry.addSmelting(item, dustAshes, 0.0F);
            }
        }
    }
}