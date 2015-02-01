package mortvana.mechstoneworks.common;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import mortvana.fluxgearcore.common.FluxGearCore;
import mortvana.fluxgearcore.util.helper.TweakHelper;

public class MechStoneworksTweaks {
	final static short WILD = Short.MAX_VALUE;
	public static void removal() {
		if (MechStoneworksConfig.tweakJABBA && FluxGearCore.isJABBALoaded) {
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("JABBA", "barrel"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to be cheaper");
		}
		if (FluxGearCore.isThermalExpansionLoaded) {
			if (MechStoneworksConfig.harderActivatorRecipe) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ThermalExpansion", "Device"), WILD, 2), TweakHelper.TweakReason.CHANGED, "Recipe requires steel", "to make this a later game item");
			}
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), WILD, 513), TweakHelper.TweakReason.NOTE, "Recipe edited to be", "ore dictionary.");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), WILD, 0), TweakHelper.TweakReason.NOTE, "Recipe edited to be", "ore dictionary");
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), WILD, 1), TweakHelper.TweakReason.NOTE, "Recipe edited to be", "ore dictionary");

		}
		if (FluxGearCore.isMagicalCropsLoaded) {
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_ModMagicSeedsPlatinum"), WILD, 0));
		}
		if (FluxGearCore.isBigReactorsLoaded) {
			if (MechStoneworksConfig.steelReactorCasings && FluxGearCore.isSteelRegistered) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe requires steel to", "make the mod later game");
			}
			if (MechStoneworksConfig.glassFuelRods) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "YelloriumFuelRod"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe requires hardened glass", "to make the mod later game");
			}
			if (MechStoneworksConfig.fourReactorGlass) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe output quadrupled", "to offset the expensive glass");
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), WILD, 1), TweakHelper.TweakReason.CHANGED, "Recipe output quadrupled", "to offset the expensive glass");
			}
			if (MechStoneworksConfig.mortvanaReactors&& !OreDictionary.getOres("ingotHSLA").isEmpty() && !OreDictionary.getOres("dustLead").isEmpty() && !OreDictionary.getOres("ingotTungsten").isEmpty() && !OreDictionary.getOres("element_Be").isEmpty() && FluxGearCore.isRedstoneArsenalLoaded && FluxGearCore.isLaserCraftLoaded) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe requires many resources", "to make the mod later game");
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "YelloriumFuelRod"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe requires hardened glass", "to make the mod later game");
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe output quadrupled", "to offset the expensive glass");
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), WILD, 1), TweakHelper.TweakReason.CHANGED, "Recipe output quadrupled", "to offset the expensive glass");
			}
		}
		if (FluxGearCore.isOpenBlocksLoaded && MechStoneworksConfig.redPowerBreakersAndDeployers) {
			if (GameRegistry.findItem("OpenBlocks", "blockbreaker") != null) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("OpenBlocks", "blockbreaker"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to bring back", "RP2-like recipes");
			}
			if (GameRegistry.findItem("OpenBlocks", "blockPlacer") != null) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("OpenBlocks", "blockPlacer"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to bring back", "RP2-like recipes");
			}
		}
		if (MechStoneworksConfig.nerfEnderQuarry && FluxGearCore.isExtraUtilitiesLoaded) {
			TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("ExtraUtilities", "enderQuarry"), WILD, 0), TweakHelper.TweakReason.CHANGED, "Recipe changed to", "balance its power");
		}
		if (FluxGearCore.isMekanismLoaded) {
			if (MechStoneworksConfig.harderDisassemblerRecipe) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("Mekanism", "AtomicDisassembler"), WILD, 100), TweakHelper.TweakReason.CHANGED, "Changed to ensure", "balance with all other tools");
			}
			if (MechStoneworksConfig.nerfMiner) {
				TweakHelper.markItemForRecipeRemoval(new ItemStack(GameRegistry.findItem("Mekanism", "MachineBlock"), WILD, 4), TweakHelper.TweakReason.CHANGED, "Changed to balance better", "with other quarry-like blocks");
			}
		}
		if (FluxGearCore.isStevesFactoryLoaded && FluxGearCore.isAppliedEnergisticsLoaded && MechStoneworksConfig.tweakSFM) {
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

	public static void addition() {
		if (FluxGearCore.isJABBALoaded) {
			if (MechStoneworksConfig.tweakJABBA) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("JABBA", "barrel"), 1, 0), "wsw", "wpw", "www", 'w', "logWood", 's', "slabWood", 'p', Items.paper));
			}
			if (MechStoneworksConfig.enderChestJABBA) {
				OreDictionary.registerOre("transdimBlock", new ItemStack(GameRegistry.findItem("EnderStorage", "enderChest"), 1, Short.MAX_VALUE));
			}
		}
		if (MechStoneworksConfig.oreDictStuff) {
			MechanicsStoneworks.logger.info("*Dalek Voice* Activating the Alumin(i)um Arc!!!!");
			if (FluxGearCore.isProjectRedExploration) {
				OreDictionary.registerOre("blockMarble", new ItemStack(GameRegistry.findItem("ProjRed|Exploration", "projectred.exploration.stone"), 1, 0));
				OreDictionary.registerOre("marble", new ItemStack(GameRegistry.findItem("ProjRed|Exploration", "projectred.exploration.stone"), 1, 0));
				OreDictionary.registerOre("blockBasalt", new ItemStack(GameRegistry.findItem("ProjRed|Exploration", "projectred.exploration.stone"), 1, 3));
				OreDictionary.registerOre("basalt", new ItemStack(GameRegistry.findItem("ProjRed|Exploration", "projectred.exploration.stone"), 1, 3));
			}
			/*if (FluxGearCore.isArtificeLoaded) {
				//TODO: Add Stuff
			}*/
			if (FluxGearCore.isThermalExpansionLoaded) {
				OreDictionary.registerOre("dustWood", new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 512));
				OreDictionary.registerOre("itemSlag", new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 514));
			}
		}
		if (FluxGearCore.isThermalExpansionLoaded) {
			if (MechStoneworksConfig.harderActivatorRecipe) {
				//TODO: Dederp TE GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "Device"), 1, 2), "scs", "tpt", "sns", 's', OreDictionary.getOres("ingotSteel").isEmpty() ? "ingotInvar" : "ingotSteel", 'p', Blocks.piston, 't', "gearTin", 'c', Blocks.chest, 'n', thermalexpansion.item.TEItems.pneumaticServo));
			}
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 513), "sss", "s s", "sss", 's', "dustWood"));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), 1, 0), "dustWood", "itemSlag", "slimeball"));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), 1, 1), "dustWood", "itemSlag", "slimeball", "dustBlaze"));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("ThermalExpansion", "florb"), 1, 1), "dustWood", "itemSlag", "magmaCream"));
		}
		if (FluxGearCore.isMagicalCropsLoaded) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_ModMagicSeedsPlatinum"), 1, 0), "nep", "ese", "pen", 'n', new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_ModMagicSeedsNickel"), 1, 0), 'e', new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_MagicEssence"), 1, 4), 'p', "ingotPlatinum", 's', Items.wheat_seeds));
			//if (FluxGearCore.isThermalExpansionLoaded){
			//ItemStack pulv = thermalexpansion.block.machine.BlockMachine.pulverizer;
			//TweakHelper.addTweakedTooltip(pulv.itemID, pulv.getItemDamage(), TweakHelper.TweakReason.ADDED, "Recipes for Essence Ore and", "Nether Essence Ore");
			//thermalexpansion.util.crafting.PulverizerManager.addRecipe(2400, OreDictionary.getOres("oreMCropsEssence").get(0), new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_MagicEssence"), 8, 0), new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_CropEssence"), 1, 0), 5);
			//thermalexpansion.util.crafting.PulverizerManager.addRecipe(2400, OreDictionary.getOres("oreMCropsNetherEssence").get(0), new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_MagicEssence"), 12, 0), new ItemStack(GameRegistry.findItem("magicalcrops", "magicalcrops_CropEssence"), 1, 0), 10);
			//}
		}
		if (FluxGearCore.isBigReactorsLoaded) {
			if (MechStoneworksConfig.steelReactorCasings && FluxGearCore.isSteelRegistered) {
				// Reactor Casing
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "BRReactorPart"), 4, 0), "scs", "cuc", "scs", 's', "ingotSteel", 'c', "ingotGraphite", 'u', "ingotYellorium"));
			}
			if (MechStoneworksConfig.glassFuelRods && MechStoneworksConfig.steelReactorCasings && FluxGearCore.isSteelRegistered) {
				// Yellorium Fuel Rod
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "YelloriumFuelRod"), 1, 0), "csc", "gug", "csc", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 1, 0), 's', "ingotSteel", 'u', "ingotYellorium", 'g', "ingotGraphite"));
			}
			if (MechStoneworksConfig.glassFuelRods && (!MechStoneworksConfig.steelReactorCasings || (MechStoneworksConfig.steelReactorCasings && !FluxGearCore.isSteelRegistered))) {
				// Yellorium Fuel Rod
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("BigReactors", "YelloriumFuelRod"), 1, 0), "cic", "gug", "cic", 'c', new ItemStack(GameRegistry.findItem("BigReactors", "BRMultiblockGlass"), 1, 0), 's', "ingotIron", 'u', "ingotYellorium", 'g', "ingotGraphite"));
			}
			if (MechStoneworksConfig.fourReactorGlass) {
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
			if (MechStoneworksConfig.mortvanaReactors && !OreDictionary.getOres("ingotHSLA").isEmpty() && !OreDictionary.getOres("dustLead").isEmpty() && !OreDictionary.getOres("ingotTungsten").isEmpty() && !OreDictionary.getOres("element_Be").isEmpty() /*&& Loader.isModLoaded("RedstoneArsenal")*/) {
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
		if (FluxGearCore.isOpenBlocksLoaded && MechStoneworksConfig.redPowerBreakersAndDeployers) {
			if (GameRegistry.findItem("OpenBlocks", "blockbreaker") != null) {
				// Block Breaker
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("OpenBlocks", "blockbreaker"), 1, 0), "cac", "cpc", "crc", 'c', "cobblestone", 'a', Items.iron_pickaxe, 'p', Blocks.piston, 'r', Items.redstone));
			}
			if (GameRegistry.findItem("OpenBlocks", "blockPlacer") != null) {
				// Block Placer
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("OpenBlocks", "blockPlacer"), 1, 0), "csc", "cpc", "crc", 'c', "cobblestone", 's', Blocks.chest, 'p', Blocks.piston, 'r', Items.redstone));
			}
		}
		if (FluxGearCore.isExtraUtilitiesLoaded) {
			if (MechStoneworksConfig.nerfEnderQuarry) {
				// Ender Mining Mechanism
				GameRegistry.addRecipe(new ShapedOreRecipe(MechStoneworksContent.enderMiningCore, "pws", "dbd", "cec", 'p', new ItemStack(GameRegistry.findItem("ExtraUtilities", "destructionpickaxe"), 1, 0), 'w', new ItemStack(GameRegistry.findItem("ExtraUtilities", "builderswand"), 1, 0), 's', new ItemStack(GameRegistry.findItem("ExtraUtilities", "erosionShovel"), 1, 0), 'd', new ItemStack(GameRegistry.findItem("ExtraUtilities", "dark_portal")) , 'b', Blocks.iron_bars, 'c', MechStoneworksContent.enderCompCore, 'e', /*FluxGearCore.isThermalExpansionLoaded ? thermalexpansion.block.cell.BlockCell.cellResonant.copy() :*/ MechStoneworksContent.enderCompCore)); //TODO: Dederp TE
				// Ender Quarry
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("ExtraUtilities", "enderQuarry"), 1, 0), "oqo", "cdc", "plp", 'o', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 1), 'q', new ItemStack(GameRegistry.findItem("ExtraUtilities", "cobblestone_compressed"), 1, 11), 'c', MechStoneworksContent.enderCompCore, 'd', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 12), 'l', MechStoneworksContent.enderMiningCore, 'p', GameRegistry.findItem("ExtraUtilities", "enderThermicPump") == null ? new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 12) : GameRegistry.findItem("ExtraUtilities", "enderThermicPump")));
				// Ender Computation Matrix
				GameRegistry.addRecipe(new ShapedOreRecipe(MechStoneworksContent.enderCompCore, "wcw", "nen", "wcw", 'w', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 8), 'c', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 12), 'e', Items.ender_eye, 'n', new ItemStack(GameRegistry.findItem("ExtraUtilities", "decorativeBlock1"), 1, 11)));
			}
		}
		if (FluxGearCore.isMekanismLoaded) {
			if (MechStoneworksConfig.nerfMiner) {
				// Digital Miner
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("Mekanism", "MachineBlock"), 1, 4), "aca", "lrl", "mdm", 'a', "alloyUltimate", 'c', "circuitUltimate", 'l', new ItemStack(GameRegistry.findItem("Mekanism", "MachineBlock"), 1, 15), 'r', GameRegistry.findItem("Mekanism", "Robit"), 'm', new ItemStack(GameRegistry.findItem("Mekanism", "BasicBlock"), 1, 8), 'd', GameRegistry.findItem("Mekanism", "AtomicDisassembler")));
			}
			if (MechStoneworksConfig.harderDisassemblerRecipe) {
				// Atomic Disassembler
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("Mekanism", "AtomicDisassembler"), 1, 100), "ata", "ada", " o ", 'd', MechStoneworksContent.disassemblyCore, 't', GameRegistry.findItem("Mekanism", "EnergyTablet"), 'o', "ingotRefinedObsidian", 'a', "alloyUltimate"));

				// Disassembly Core
				GameRegistry.addRecipe(new ShapedOreRecipe(MechStoneworksContent.disassemblyCore, "tst", "eae", "tst", 't', GameRegistry.findItem("Mekanism", "TeleportationCore"), 's', GameRegistry.findItem("Mekanism", "SpeedUpgrade"), 'e', GameRegistry.findItem("Mekanism", "EnergyUpgrade"), 'a', "alloyUltimate"));
			}

			// Mekanism Crusher Recipes
			ArrayList<ItemStack> oreIn, dustOut;
			if (!(oreIn = OreDictionary.getOres("oreAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				out.stackSize++;
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("oreVinteum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustVinteum")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				out.stackSize++;
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("oreYellorite")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				out.stackSize++;
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("oreRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				out.stackSize++;
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("oreSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				out.stackSize++;
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("oreOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				out.stackSize++;
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addEnrichmentChamberRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("ingotYellorium")).isEmpty() && !(dustOut = OreDictionary.getOres("dustYellorium")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("gemRuby")).isEmpty() && !(dustOut = OreDictionary.getOres("dustRuby")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("gemSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustSapphire")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("gemGreenSapphire")).isEmpty() && !(dustOut = OreDictionary.getOres("dustGreenSapphire")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("gemOlivine")).isEmpty() && !(dustOut = OreDictionary.getOres("dustOlivine")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("ingotAluminum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustAluminum")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("ingotPlatinum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustPlatinum")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("ingotElectrum")).isEmpty() && !(dustOut = OreDictionary.getOres("dustElectrum")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			if (!(oreIn = OreDictionary.getOres("ingotInvar")).isEmpty() && !(dustOut = OreDictionary.getOres("dustInvar")).isEmpty()) {
				ItemStack out = dustOut.get(0).copy();
				for (ItemStack i : oreIn)
					mekanism.api.RecipeHelper.addCrusherRecipe(i.copy(), out);
			}
			mekanism.api.RecipeHelper.addCrusherRecipe(new ItemStack(Items.bone), new ItemStack(Items.dye, 5, 15));
			mekanism.api.RecipeHelper.addCrusherRecipe(new ItemStack(Blocks.red_flower), new ItemStack(Items.dye, 2, 1));
			mekanism.api.RecipeHelper.addCrusherRecipe(new ItemStack(Blocks.yellow_flower), new ItemStack(Items.dye, 2, 11));
		}
		if (FluxGearCore.isStevesFactoryLoaded && FluxGearCore.isAppliedEnergisticsLoaded && MechStoneworksConfig.tweakSFM) {
			// Machine Inventory Manager
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockMachineManagerName"), 1, 0), "iii", "apf", "sis", 'p', MechStoneworksContent.multicoreProcessor, 'a', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 44), 'f', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 43), 'i', FluxGearCore.isSteelRegistered ? "ingotSteel" : "ingotIron", 's', "stone"));
			// Inventory Cable
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 8, 0), "ipi", "gfg", "ipi", 'f', "dustFluix", 'g', "glass", 'i', FluxGearCore.isSteelRegistered ? "ingotSteel" : "ingotIron", 'p', Blocks.light_weighted_pressure_plate));
			// Advanced Inventory Relay
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableRelayName"), 1, 8), "fbf", "brb", "fbf", 'b', "blockLapis", 'r', new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableRelayName"), 1, 0), 'f', "dustFluix"));
			// Redston Receiver
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableInputName"), 1, 0), " r ", "rcr", " r ", 'r', "dustRedstone", 'c', new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 1, 0)));
			// Redstone Emitter
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableOutputName"), 1, 0), "rer", "rcr", "rrr", 'r', "dustRedstone", 'e', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiPart"), 1, 280), 'c', new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 1, 0)));
			// Multicore Processor
			GameRegistry.addRecipe(new ShapedOreRecipe(MechStoneworksContent.multicoreProcessor, "cfc", "fsf", "cfc", 'c', new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 23), 's', "itemSilicon", 'f', "dustFluix"));
			// Item Valve
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableIntakeName"), 1, 0), new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 1, 0), Blocks.hopper, Blocks.dropper, new ItemStack(GameRegistry.findItem("appliedenergistics2", "BlockInterface"), 1, 0)));
			// Rapid Item Valve
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableIntakeName"), 1, 8), new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableIntakeName"), 1, 0), new ItemStack(GameRegistry.findItem("appliedenergistics2", "item.ItemMultiMaterial"), 1, 23)));
			// Block Gate
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableBreakerName"), 1, 0), new ItemStack(GameRegistry.findItem("StevesFactoryManager", "BlockCableName"), 1, 0), Items.iron_pickaxe, Blocks.dispenser, new ItemStack(GameRegistry.findItem("appliedenergistics2", "BlockInterface"), 1, 0)));
		}
		if (MechStoneworksConfig.wheatToSeeds) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.wheat_seeds, MechStoneworksConfig.wheatToSeedsAmount, 0), Items.wheat));
		}
	}
}
