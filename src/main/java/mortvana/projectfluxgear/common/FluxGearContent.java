package mortvana.projectfluxgear.common;

import cofh.core.fluid.BlockFluidCoFHBase;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

import mortvana.fluxgearcore.item.tool.BucketFluxGear;
import mortvana.fluxgearcore.util.handler.DispenserEmptyBucketHandler;
import mortvana.fluxgearcore.util.handler.DispenserFilledBucketHandler;
import mortvana.projectfluxgear.block.basic.itemblock.*;
import mortvana.projectfluxgear.common.config.FluxGearConfig;
import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import mortvana.fluxgearcore.item.ItemFluxGear;
import mortvana.fluxgearcore.util.helper.ItemHelper;

import mortvana.projectfluxgear.block.basic.*;
import mortvana.projectfluxgear.item.tool.ItemFluxGearProtoSonicWrench;
import mortvana.projectfluxgear.block.BlockTemporalPylon;
import mortvana.projectfluxgear.block.BlockWoodenTileEntity;

public class FluxGearContent implements IFuelHandler{

	public void preInit() {
        loadBlocks();
        //loadMachines();
        loadFluids();
        loadItems();
        loadParts();
        loadTools();
	    //loadEnchants();
		//loadTiles();
        //loadAugments();
    }

    public void init() {
        registerBlocks();
        //initMachines();
        //initTools();
        //initAugments();
        //addOreDict();
	    metalCraftingRecipes();
        machineCraftingRecipes();
        otherCraftingRecipes();
	    furnaceRecipes();
        registerPoorOres();
    }

    public void postInit() {
        aluminiumArc();
        //modIntegration();
    }

    public void loadBlocks() {
        blockOreMain = new BlockOreMain();
        blockOreAux = new BlockOreAux();
        blockStorageMain = new BlockStorageMain();
        blockStorageAux = new BlockStorageAux();
        blockAlloyMain = new BlockAlloyMain();
        blockAlloyAux = new BlockAlloyAux();
        blockStorageAlch = new BlockStorageAlch();
        blockStorageAdv = new BlockStorageAdv();
        blockEarthen = new BlockEarthen();
        //blockTemporalPylon = new BlockTemporalPylon();
        //woodenTileEntity = new BlockWoodenTileEntity();

        //blockTileEntity = new BlockTileEntity();

        //blockFluidGhastTear = new BlockFluidGhastTear();

        //blockTemporalPylon.preInit();
        //woodenTileEntity.preInit();

        GameRegistry.registerBlock(blockOreMain, ItemBlockOreMain.class, "OreMain");
        oreChalcocite = new ItemStack(blockOreMain, 1, 0);
        oreCassiterite = new ItemStack(blockOreMain, 1, 1);
        oreGalena = new ItemStack(blockOreMain, 1, 2);
        oreAcanthite = new ItemStack(blockOreMain, 1, 3);
        oreGarnierite = new ItemStack(blockOreMain, 1, 4);
        oreSphalerite = new ItemStack(blockOreMain, 1, 5);
        oreBismuthinite = new ItemStack(blockOreMain, 1, 6);
        orePyrolusite = new ItemStack(blockOreMain, 1, 7);
        oreBauxite = new ItemStack(blockOreMain, 1, 8);
        oreCooperite = new ItemStack(blockOreMain, 1, 9);
        oreBraggite = new ItemStack(blockOreMain, 1, 10);
        oreMolybdenite = new ItemStack(blockOreMain, 1, 11);
        oreCobaltite = new ItemStack(blockOreMain, 1, 12);
        oreWolframite = new ItemStack(blockOreMain, 1, 13);
        oreIlmenite = new ItemStack(blockOreMain, 1, 14);
        oreChromite = new ItemStack(blockOreMain, 1, 15);

        GameRegistry.registerBlock(blockOreAux, ItemBlockOreAux.class, "OreAux");
        oreCinnabar = new ItemStack(blockOreAux, 1, 0);
        orePitchblende = new ItemStack(blockOreAux, 1, 1);
        oreMonazite = new ItemStack(blockOreAux, 1, 2);
        oreNiedermayrite = new ItemStack(blockOreAux, 1, 3);
        oreGreenockite = new ItemStack(blockOreAux, 1, 4);
        oreGaotaiite = new ItemStack(blockOreAux, 1, 5);
        oreOsarsite = new ItemStack(blockOreAux, 1, 6);
        oreZnamenskyite = new ItemStack(blockOreAux, 1, 7);
        oreGallobeudanite = new ItemStack(blockOreAux, 1, 8);
        oreTertahedrite = new ItemStack(blockOreAux, 1, 9);
        oreTennantite = new ItemStack(blockOreAux, 1, 10);
        oreSantafeite = new ItemStack(blockOreAux, 1, 11);
        oreMagnetite = new ItemStack(blockOreAux, 1, 12);
        oreDioptase = new ItemStack(blockOreAux, 1, 13);
        orePyrope = new ItemStack(blockOreAux, 1, 14);
        oreMyuvil = new ItemStack(blockOreAux, 1, 15);

        GameRegistry.registerBlock(blockStorageMain, ItemBlockStorageMain.class, "StorageMain");
        blockCopper = new ItemStack(blockStorageMain, 1, 0);
        blockTin = new ItemStack(blockStorageMain, 1, 1);
        blockLead = new ItemStack(blockStorageMain, 1, 2);
        blockSilver = new ItemStack(blockStorageMain, 1, 3);
        blockNickel = new ItemStack(blockStorageMain, 1, 4);
        blockZinc = new ItemStack(blockStorageMain, 1, 5);
        blockBismuth = new ItemStack(blockStorageMain, 1, 6);
        blockManganese = new ItemStack(blockStorageMain, 1, 7);
        blockAluminium = new ItemStack(blockStorageMain, 1, 8);
        blockPlatinum = new ItemStack(blockStorageMain, 1, 9);
        blockPalladium = new ItemStack(blockStorageMain, 1, 10);
        blockMolybdenum = new ItemStack(blockStorageMain, 1, 11);
        blockCobalt = new ItemStack(blockStorageMain, 1, 12);
        blockTungsten = new ItemStack(blockStorageMain, 1, 13);
        blockTitanium = new ItemStack(blockStorageMain, 1, 14);
        blockChromium = new ItemStack(blockStorageMain, 1, 15);

        GameRegistry.registerBlock(blockStorageAux, ItemBlockStorageAux.class, "StorageAux");
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

        GameRegistry.registerBlock(blockAlloyMain, ItemBlockAlloyMain.class, "AlloyMain");
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

        GameRegistry.registerBlock(blockAlloyAux, ItemBlockAlloyAux.class, "AlloyAux");
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

        GameRegistry.registerBlock(blockStorageAlch, ItemBlockStorageAlch.class, "StorageAlch");
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

        GameRegistry.registerBlock(blockStorageAdv, ItemBlockStorageAdv.class, "StorageAdv");
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

        GameRegistry.registerBlock(blockEarthen, ItemBlockEarthen.class, "Earthen");
        blockIridiumSands = new ItemStack(blockEarthen, 1, 0);
        blockPoorIridiumSands = new ItemStack(blockEarthen, 1, 1);
        blockAluminosilicateSludge = new ItemStack(blockEarthen, 1, 2);

        blockOreMain.setHarvestLevel("pickaxe", 1, 0);
        blockOreMain.setHarvestLevel("pickaxe", 1, 1);
        blockOreMain.setHarvestLevel("pickaxe", 2, 2);
        blockOreMain.setHarvestLevel("pickaxe", 2, 3);
        blockOreMain.setHarvestLevel("pickaxe", 2, 4);
        blockOreMain.setHarvestLevel("pickaxe", 1, 5);
        blockOreMain.setHarvestLevel("pickaxe", 1, 6);
        blockOreMain.setHarvestLevel("pickaxe", 1, 7);
        blockOreMain.setHarvestLevel("pickaxe", 1, 8);
        blockOreMain.setHarvestLevel("pickaxe", 2, 9);
        blockOreMain.setHarvestLevel("pickaxe", 2, 10);
        blockOreMain.setHarvestLevel("pickaxe", 2, 11);
        blockOreMain.setHarvestLevel("pickaxe", 2, 12);
        blockOreMain.setHarvestLevel("pickaxe", 3, 13);
        blockOreMain.setHarvestLevel("pickaxe", 2, 14);
        blockOreMain.setHarvestLevel("pickaxe", 2, 15);

        blockOreAux.setHarvestLevel("pickaxe", 2, 0);
        blockOreAux.setHarvestLevel("pickaxe", 2, 1);
        blockOreAux.setHarvestLevel("pickaxe", 2, 2);
        blockOreAux.setHarvestLevel("pickaxe", 2, 3);
        blockOreAux.setHarvestLevel("pickaxe", 2, 4);
        blockOreAux.setHarvestLevel("pickaxe", 3, 5);
        blockOreAux.setHarvestLevel("pickaxe", 2, 6);
        blockOreAux.setHarvestLevel("pickaxe", 2, 7);
        blockOreAux.setHarvestLevel("pickaxe", 2, 8);
        blockOreAux.setHarvestLevel("pickaxe", 1, 9);
        blockOreAux.setHarvestLevel("pickaxe", 1, 10);
        blockOreAux.setHarvestLevel("pickaxe", 2, 11);
        blockOreAux.setHarvestLevel("pickaxe", 2, 12);
        blockOreAux.setHarvestLevel("pickaxe", 3, 13);
        blockOreAux.setHarvestLevel("pickaxe", 3, 14);
        blockOreAux.setHarvestLevel("pickaxe", 3, 15);

    }

    public void registerBlocks() {
        // OreDict Ores
        ItemHelper.registerWithHandlers("oreChalcocite", oreChalcocite);
        ItemHelper.registerWithHandlers("oreCassiterite", oreCassiterite);
        ItemHelper.registerWithHandlers("oreGalena", oreGalena);
        ItemHelper.registerWithHandlers("oreAcanthite", oreAcanthite);
        ItemHelper.registerWithHandlers("oreGarnierite", oreGarnierite);
        ItemHelper.registerWithHandlers("oreSphalerite", oreSphalerite);
        ItemHelper.registerWithHandlers("oreBismuthinite", oreBismuthinite);
        ItemHelper.registerWithHandlers("orePyrolusite", orePyrolusite);
        ItemHelper.registerWithHandlers("oreBauxite", oreBauxite);
        ItemHelper.registerWithHandlers("oreCooperite", oreCooperite);
        ItemHelper.registerWithHandlers("oreBraggite", oreBraggite);
        ItemHelper.registerWithHandlers("oreMolybdenite", oreMolybdenite);
        ItemHelper.registerWithHandlers("oreCobaltite", oreCobaltite);
        ItemHelper.registerWithHandlers("oreWolframite", oreWolframite);
        ItemHelper.registerWithHandlers("oreIlmenite", oreIlmenite);
        ItemHelper.registerWithHandlers("oreChromite", oreChromite);

        ItemHelper.registerWithHandlers("oreCinnabar", oreCinnabar);
        ItemHelper.registerWithHandlers("orePitchblende", orePitchblende);
        ItemHelper.registerWithHandlers("oreMonazite", oreMonazite);
        ItemHelper.registerWithHandlers("oreNiedermayrite", oreNiedermayrite);
        ItemHelper.registerWithHandlers("oreGreenockite", oreGreenockite);
        ItemHelper.registerWithHandlers("oreGaotaiite", oreGaotaiite);
        ItemHelper.registerWithHandlers("oreOsarsite", oreOsarsite);
        ItemHelper.registerWithHandlers("oreZnamenskyite", oreZnamenskyite);
        ItemHelper.registerWithHandlers("oreGallobeudanite", oreGallobeudanite);
        ItemHelper.registerWithHandlers("oreTetrahedrite", oreTertahedrite);
        ItemHelper.registerWithHandlers("oreTennantite", oreTennantite);
        ItemHelper.registerWithHandlers("oreSantafeite", oreSantafeite);
        ItemHelper.registerWithHandlers("oreMagnetite", oreMagnetite);
        ItemHelper.registerWithHandlers("oreDioptase", oreDioptase);
        ItemHelper.registerWithHandlers("orePyrope", orePyrope);
        ItemHelper.registerWithHandlers("oreMyuvil", oreMyuvil);

        //Lame-man's Ores
        OreDictionary.registerOre("oreCopper", oreChalcocite);
        OreDictionary.registerOre("oreTin", oreCassiterite);
        OreDictionary.registerOre("oreLead", oreGalena);
        OreDictionary.registerOre("oreSilver", oreAcanthite);
        OreDictionary.registerOre("oreNickel", oreGarnierite);
        OreDictionary.registerOre("oreZinc", oreSphalerite);
        OreDictionary.registerOre("oreBismuth", oreBismuthinite);
        OreDictionary.registerOre("oreManganese", orePyrolusite);
        OreDictionary.registerOre("oreAluminium", oreBauxite);
        OreDictionary.registerOre("orePlatinum", oreCooperite);
        OreDictionary.registerOre("orePalladium", oreBraggite);
        OreDictionary.registerOre("oreMolybdenum", oreMolybdenite);
        OreDictionary.registerOre("oreNaturalCobalt", oreCobaltite);
        OreDictionary.registerOre("oreTungsten", oreWolframite);
        OreDictionary.registerOre("oreTitanium", oreIlmenite);
        OreDictionary.registerOre("oreChromium", oreChromite);

        OreDictionary.registerOre("oreMercury", oreCinnabar);
        OreDictionary.registerOre("oreUranium", orePitchblende);
        OreDictionary.registerOre("oreCadmium", oreGreenockite);
        OreDictionary.registerOre("oreTellurium", oreGaotaiite);
        OreDictionary.registerOre("oreOsmium", oreOsarsite);
        OreDictionary.registerOre("oreIndium", oreZnamenskyite);
        OreDictionary.registerOre("oreGallium", oreGallobeudanite);
        OreDictionary.registerOre("oreCopper", oreTertahedrite);
        OreDictionary.registerOre("oreCopper", oreTennantite);
        OreDictionary.registerOre("oreVanadium", oreSantafeite);

        //Ore Storage Blocks
        ItemHelper.registerWithHandlers("blockCopper", blockCopper);
        ItemHelper.registerWithHandlers("blockTin", blockTin);
        ItemHelper.registerWithHandlers("blockLead", blockLead);
        ItemHelper.registerWithHandlers("blockSilver", blockSilver);
        ItemHelper.registerWithHandlers("blockNickel", blockNickel);
        ItemHelper.registerWithHandlers("blockZinc", blockZinc);
        ItemHelper.registerWithHandlers("blockBismuth", blockBismuth);
        ItemHelper.registerWithHandlers("blockManganese", blockManganese);
        ItemHelper.registerWithHandlers("blockAluminium", blockAluminium);
        ItemHelper.registerWithHandlers("blockPlatinum", blockPlatinum);
        ItemHelper.registerWithHandlers("blockPalladium", blockPalladium);
        ItemHelper.registerWithHandlers("blockMolybdenum", blockMolybdenum);
        ItemHelper.registerWithHandlers("blockNaturalCobalt", blockCobalt);
        ItemHelper.registerWithHandlers("blockTungsten", blockTungsten);
        ItemHelper.registerWithHandlers("blockTitanium", blockTitanium);
        ItemHelper.registerWithHandlers("blockChromium", blockChromium);

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

    //public void loadMachines() {}

    public void loadFluids() {
        //TODO-- Redo
        fluidGhastTear = new Fluid("ghastTear").setLuminosity(3).setDensity(1850).setViscosity(2675).setTemperature(375).setRarity(EnumRarity.rare);
        fluidLye = new Fluid("lye").setLuminosity(0).setDensity(1750).setViscosity(3500).setTemperature(300).setRarity(EnumRarity.common);
        fluidAcid = new Fluid("acid").setLuminosity(0).setDensity(1750).setViscosity(3500).setTemperature(300).setRarity(EnumRarity.common);
        fluidEtchingAcid = new Fluid("etchingAcid").setLuminosity(0).setDensity(1750).setViscosity(3500).setTemperature(300).setRarity(EnumRarity.uncommon);
        fluidSmog = new Fluid("smog").setLuminosity(0).setDensity(1000).setViscosity(500).setTemperature(315).setRarity(EnumRarity.common);
        fluidBlood = new Fluid("blood").setLuminosity(0).setDensity(1350).setViscosity(1850).setTemperature(300).setRarity(EnumRarity.common);
        fluidGelidPyrotheum = new Fluid("gelidPyrotheum").setLuminosity(15).setDensity(1350).setViscosity(2350).setTemperature(1).setRarity(EnumRarity.epic);

        FluidRegistry.registerFluid(fluidGhastTear);
        FluidRegistry.registerFluid(fluidLye);
        FluidRegistry.registerFluid(fluidAcid);
        FluidRegistry.registerFluid(fluidEtchingAcid);
        FluidRegistry.registerFluid(fluidSmog);
        FluidRegistry.registerFluid(fluidBlood);
        FluidRegistry.registerFluid(fluidGelidPyrotheum);
    }

    public void loadItems() {
        itemMaterial = (ItemFluxGear) new ItemFluxGear("projectfluxgear").setUnlocalizedName("material").setCreativeTab(ProjectFluxGear.tabResources);
        itemBucket = (BucketFluxGear) new BucketFluxGear("projectfluxgear").setUnlocalizedName("bucket").setCreativeTab(ProjectFluxGear.tabResources);
        itemFood = (ItemFluxGear) new ItemFluxGear("projectfluxgear").setUnlocalizedName("food").setCreativeTab(ProjectFluxGear.tabResources);
        itemInteractive = (ItemFluxGear) new ItemFluxGear("projectfluxgear").setUnlocalizedName("interactive").setCreativeTab(ProjectFluxGear.tabResources);

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

        //Interactive
        dustThermite = itemInteractive.addOreDictItem(0, "dustThermite");
        coagulantAlum = itemInteractive.addItem(1, "coagulant");

        loadIngots();
        loadDusts();
        loadNuggets();
    }

    //0-199 Ingots and Gems
    public void loadIngots() {
        ingotCopper = itemMaterial.addOreDictItem(0, "ingotCopper");
        ingotTin = itemMaterial.addOreDictItem(1, "ingotTin");
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

    //200-399 Dusts
    public void loadDusts() {
        dustCopper = itemMaterial.addOreDictItem(200, "dustCopper");
        dustTin = itemMaterial.addOreDictItem(201, "dustTin");
        dustLead = itemMaterial.addOreDictItem(202, "dustLead");
        dustSilver = itemMaterial.addOreDictItem(203, "dustSilver");
        dustNickel = itemMaterial.addOreDictItem(204, "dustNickel");
        dustZinc = itemMaterial.addOreDictItem(205, "dustZinc");
        dustBismuth = itemMaterial.addOreDictItem(206, "dustBismuth");
        dustManganese = itemMaterial.addOreDictItem(207, "dustManganese");
        dustAluminium = itemMaterial.addOreDictItem(208, "dustAluminium");
        dustPlatinum = itemMaterial.addOreDictItem(209, "dustPlatinum");
        dustPalladium = itemMaterial.addOreDictItem(210, "dustPalladium");
        dustMolybdenum = itemMaterial.addOreDictItem(211, "dustMolybdenum");
        dustCobalt = itemMaterial.addOreDictItem(212, "dustCobalt", "dustNaturalCobalt");
        dustTungsten = itemMaterial.addOreDictItem(213, "dustTungsten");
        dustTitanium = itemMaterial.addOreDictItem(214, "dustTitanium");
        dustChromium = itemMaterial.addOreDictItem(215, "dustChromium");
        dustArsenic = itemMaterial.addOreDictItem(216, "dustArsenic");
        dustAntimony = itemMaterial.addOreDictItem(217, "dustAntimony");
        dustNeodymium = itemMaterial.addOreDictItem(218, "dustNeodymium");
        dustTesseractium = itemMaterial.addOreDictItem(219, "dustTesseractium");
        dustCadmium = itemMaterial.addOreDictItem(220, "dustCadmium");
        dustTellurium = itemMaterial.addOreDictItem(221, "dustTellurium");
        dustOsmium = itemMaterial.addOreDictItem(222, "dustOsmium");
        dustIridium = itemMaterial.addOreDictItem(223, "dustIridium");
        dustIndium = itemMaterial.addOreDictItem(224, "dustIndium");
        dustAntimonialBronze = itemMaterial.addOreDictItem(225, "dustAntimonialBronze");
        dustArsenicalBronze = itemMaterial.addOreDictItem(226, "dustArsenicalBronze");
        dustVanadium = itemMaterial.addOreDictItem(227, "dustVanadium");
        dustUnobtainium = itemMaterial.addOreDictItem(228, "dustUnobtainium");
        dustDioptase = itemMaterial.addOreDictItem(229, "dustDioptase");
        dustPyrope = itemMaterial.addOreDictItem(230, "dustPyrope");
        dustMyuvil = itemMaterial.addOreDictItem(231, "dustMyuvil");
        dustBronze = itemMaterial.addOreDictItem(232, "dustBronze");
        dustBrass = itemMaterial.addOreDictItem(233, "dustBrass");
        dustInvar = itemMaterial.addOreDictItem(234, "dustInvar");
        dustBismuthBronze = itemMaterial.addOreDictItem(235, "dustBismuthBronze");
        dustCupronickel = itemMaterial.addOreDictItem(236, "dustCupronickel");
        dustAluminiumBrass = itemMaterial.addOreDictItem(237, "dustAluminiumBrass");
        dustElectrum = itemMaterial.addOreDictItem(238, "dustElectrum");
        dustDullRedsolder = itemMaterial.addOreDictItem(239, "dustDullRedsolder");
        dustRedsolder = itemMaterial.addOreDictItem(240, "dustRedsolder");
        dustHCSteel = itemMaterial.addOreDictItem(241, "dustHighCarbonSteel", "dustSteel");
        dustSteel = itemMaterial.addOreDictItem(242, "dustSteel", "dustRefinedSteel");
        dustHSLA = itemMaterial.addOreDictItem(243, "dustHSLA");
        dustStainlessSteel = itemMaterial.addOreDictItem(244, "dustStainlessSteel");
        dustTungstenSteel = itemMaterial.addOreDictItem(245, "dustTungstenSteel");
        dustEletriplatinum = itemMaterial.addOreDictItem(246, "dustElectriplatinum");
        dustMithril = itemMaterial.addOreDictItem(247, "dustMithril", "dustMitrilBronze");
        dustTechnomancer = itemMaterial.addOreDictItem(248, "dustTechnomancer");
        dustTechnomancerResonant = itemMaterial.addOreDictItem(249, "dustTechnomancerResonant");
        dustTungstenBlazing = itemMaterial.addOreDictItem(250, "dustTungstenBlazing");
        dustPlatinumGelid = itemMaterial.addOreDictItem(251, "dustPlatinumGelid");
        dustSilverLuminous = itemMaterial.addOreDictItem(252, "dustSilverLuminous");
        dustElectrumFlux = itemMaterial.addOreDictItem(253, "dustElectrumFlux");
        dustMolybdenumResonant = itemMaterial.addOreDictItem(254, "dustMolybdenumResonant");
        dustChromiumCarbide = itemMaterial.addOreDictItem(255, "dustChromiumCarbide");
        dustBismuthBronzeColdfire = itemMaterial.addOreDictItem(256, "dustBismuthBronzeColdfire");
        dustPyrum = itemMaterial.addOreDictItem(257, "dustPyrum");
        dustGelinium = itemMaterial.addOreDictItem(258, "dustGelinium");
        dustLumium = itemMaterial.addOreDictItem(259, "dustLumium");
        dustSignalum = itemMaterial.addOreDictItem(260, "dustSignalum");
        dustEnderium = itemMaterial.addOreDictItem(261, "dustEnderium");
        dustCarbonite = itemMaterial.addOreDictItem(262, "dustCarbonite");
        dustTherminate = itemMaterial.addOreDictItem(263, "dustTherminate");
        dustNullmetal = itemMaterial.addOreDictItem(264, "dustNullmetal");
        dustIocarbide = itemMaterial.addOreDictItem(265, "dustIocarbide");
        dustCryocarbide = itemMaterial.addOreDictItem(266, "dustCryocarbide");
        dustPyrocarbide = itemMaterial.addOreDictItem(267, "dustPyrocarbide");
        dustTenebride = itemMaterial.addOreDictItem(268, "dustTenebride");
        dustIlluminide = itemMaterial.addOreDictItem(269, "dustIlluminide");
        dustZythoferride = itemMaterial.addOreDictItem(270, "dustZythoferride");
        dustCrystalFlux = itemMaterial.addOreDictItem(271, "dustCrystalFlux");
        dustLapiquartz = itemMaterial.addOreDictItem(272, "dustLapiquartz");
        dustRust = itemMaterial.addOreDictItem(273 , "dustRust");
        dustSulfur = itemMaterial.addOreDictItem(274 , "dustSulfur");
        dustSaltpeter = itemMaterial.addOreDictItem(275 , "dustSaltpeter");
        dustMithrilFlux = itemMaterial.addOreDictItem(276, "dustMithrilFlux");
        dustMithrilTinker = itemMaterial.addOreDictItem(277, "dustMithrilTinker");
        dustThorium = itemMaterial.addOreDictItem(278 , "dustThorium");
        dustUranium235 = itemMaterial.addOreDictItem(279 , "dustUranium235");
        dustUranium238 = itemMaterial.addOreDictItem(280 , "dustUranium238");
        dustMagnetite = itemMaterial.addOreDictItem(281, "dustMagnetite");
        dustNeodymiumMagnet = itemMaterial.addOreDictItem(282, "dustNeodymiumMagnetMetal");
        dustIronMagnet = itemMaterial.addOreDictItem(283, "dustIronMagnetic");
        dustManganeseMagnet = itemMaterial.addOreDictItem(284, "dustManganeseMagnetic");
        dustCobaltMagnet = itemMaterial.addOreDictItem(285, "dustCobaltMagnetic");
        dustNickelMagnet = itemMaterial.addOreDictItem(286, "dustNickelMagnetic");
        dustInvarMagnet = itemMaterial.addOreDictItem(287, "dustInvarMagnetic");
        dustHCSteelMagnet = itemMaterial.addOreDictItem(288, "dustHighCarbonSteelMagnetic", "dustSteelMagnetic");
        dustSteelMagnet = itemMaterial.addOreDictItem(289, "dustSteelMagnetic");
        dustHSLAMagnet = itemMaterial.addOreDictItem(290, "dustHSLAMagnetic", "dustRefinedSteelMagnetic");
        //* Amber
        dustNichrome = itemMaterial.addOreDictItem(292, "dustNichrome");
        dustPolycarbide = itemMaterial.addOreDictItem(293, "dustPolycarbide");
        dustVorpal = itemMaterial.addOreDictItem(294, "dustVorpal");
        dustAshes = itemMaterial.addOreDictItem(295, "dustAshes");
        dustIron = itemMaterial.addOreDictItem(296 , "dustIron");
        dustGold = itemMaterial.addOreDictItem(297 , "dustGold");
        dustDiamond = itemMaterial.addOreDictItem(298 , "dustDiamond");
        dustCoal = itemMaterial.addOreDictItem(299 , "dustCoal");
        dustCharcoal = itemMaterial.addOreDictItem(300 , "dustCharcoal");
        dustObsidian = itemMaterial.addOreDictItem(301 , "dustObsidian");
        dustBlizz = itemMaterial.addOreDictItem(302 , "dustBlizz");
        dustCyrotheum = itemMaterial.addOreDictItem(303 , "dustCryotheum");
        dustPyrotheum = itemMaterial.addOreDictItem(304 , "dustPyrotheum");
        dustIceflame = itemMaterial.addOreDictItem(305 , "dustIceflame");
        dustKroostyl = itemMaterial.addOreDictItem(306 , "dustKroostyl");
        dustYttrium = itemMaterial.addOreDictItem(307, "dustYtrium");
        dustRuthenium = itemMaterial.addOreDictItem(308, "dustRuthenium");
        dustLanthanum = itemMaterial.addOreDictItem(319, "dustLanthanum");
        dustCerium = itemMaterial.addOreDictItem(310, "dustCerium");
        dustMagnesium = itemMaterial.addOreDictItem(311 , "dustMagnesium");
        dustCalcium = itemMaterial.addOreDictItem(312 , "dustCalcium");
        dustStrontium = itemMaterial.addOreDictItem(313 , "dustStrontium");

    }

    //400-599 Nuggets
    public void loadNuggets() {
        nuggetCopper = itemMaterial.addOreDictItem(400, "nuggetCopper");
        nuggetTin = itemMaterial.addOreDictItem(401, "nuggetTin");
        nuggetLead = itemMaterial.addOreDictItem(402, "nuggetLead");
        nuggetSilver = itemMaterial.addOreDictItem(403, "nuggetSilver");
        nuggetNickel = itemMaterial.addOreDictItem(404, "nuggetNickel");
        nuggetZinc = itemMaterial.addOreDictItem(405, "nuggetZinc");
        nuggetBismuth = itemMaterial.addOreDictItem(406, "nuggetBismuth");
        nuggetManganese = itemMaterial.addOreDictItem(407, "nuggetManganese");
        nuggetAluminium = itemMaterial.addOreDictItem(408, "nuggetAluminium");
        nuggetPlatinum = itemMaterial.addOreDictItem(409, "nuggetPlatinum");
        nuggetPalladium = itemMaterial.addOreDictItem(410, "nuggetPalladium");
        nuggetMolybdenum = itemMaterial.addOreDictItem(411, "nuggetMolybdenum");
        nuggetCobalt = itemMaterial.addOreDictItem(412, "nuggetCobalt", "nuggetNaturalCobalt");
        nuggetTungsten = itemMaterial.addOreDictItem(413, "nuggetTungsten");
        nuggetTitanium = itemMaterial.addOreDictItem(414, "nuggetTitanium");
        nuggetChromium = itemMaterial.addOreDictItem(415, "nuggetChromium");
        //* Antimony
        //* Arsenic
        nuggetNeodymium = itemMaterial.addOreDictItem(418, "nuggetNeodymium");
        nuggetTesseractium = itemMaterial.addOreDictItem(419, "nuggetTesseractium");
        nuggetCadmium = itemMaterial.addOreDictItem(420, "nuggetCadmium");
        nuggetTellurium = itemMaterial.addOreDictItem(421, "nuggetTellurium");
        nuggetOsmium = itemMaterial.addOreDictItem(422, "nuggetOsmium");
        nuggetIridium = itemMaterial.addOreDictItem(423, "nuggetIridium");
        nuggetIndium = itemMaterial.addOreDictItem(424, "nuggetIndium");
        nuggetAntimonialBronze = itemMaterial.addOreDictItem(425, "nuggetAntimonialBronze");
        nuggetArsenicalBronze = itemMaterial.addOreDictItem(426, "nuggetArsenicalBronze");
        nuggetVanadium = itemMaterial.addOreDictItem(427, "nuggetVanadium");
        nuggetUnobtainium = itemMaterial.addOreDictItem(428, "nuggetUnobtainium");
        nuggetDioptase = itemMaterial.addOreDictItem(429, "nuggetDioptase");
        nuggetPyrope = itemMaterial.addOreDictItem(430, "nuggetPyrope");
        //* Myuvil
        nuggetBronze = itemMaterial.addOreDictItem(432, "nuggetBronze");
        nuggetBrass = itemMaterial.addOreDictItem(433, "nuggetBrass");
        nuggetInvar = itemMaterial.addOreDictItem(434, "nuggetInvar");
        nuggetBismuthBronze = itemMaterial.addOreDictItem(435, "nuggetBismuthBronze");
        nuggetCupronickel = itemMaterial.addOreDictItem(436, "nuggetCupronickel");
        nuggetAluminiumBrass = itemMaterial.addOreDictItem(437, "nuggetAluminiumBrass");
        nuggetElectrum = itemMaterial.addOreDictItem(438, "nuggetElectrum");
        nuggetDullRedsolder = itemMaterial.addOreDictItem(439, "nuggetDullRedsolder");
        nuggetRedsolder = itemMaterial.addOreDictItem(440, "nuggetRedsolder");
        nuggetHCSteel = itemMaterial.addOreDictItem(441, "nuggetHighCarbonSteel", "nuggetSteel");
        nuggetSteel = itemMaterial.addOreDictItem(442, "nuggetSteel", "nuggetRefinedSteel");
        nuggetHSLA = itemMaterial.addOreDictItem(443, "nuggetHSLA");
        nuggetStainlessSteel = itemMaterial.addOreDictItem(444, "nuggetStainlessSteel");
        nuggetTungstenSteel = itemMaterial.addOreDictItem(445, "nuggetTungstenSteel");
        nuggetEletriplatinum = itemMaterial.addOreDictItem(446, "nuggetElectriplatinum");
        nuggetMithril = itemMaterial.addOreDictItem(447, "nuggetMithril", "nuggetMitrilBronze");
        nuggetTechnomancer = itemMaterial.addOreDictItem(448, "nuggetTechnomancer");
        nuggetTechnomancerResonant = itemMaterial.addOreDictItem(449, "nuggetTechnomancerResonant");
        nuggetTungstenBlazing = itemMaterial.addOreDictItem(450, "nuggetTungstenBlazing");
        nuggetPlatinumGelid = itemMaterial.addOreDictItem(451, "nuggetPlatinumGelid");
        nuggetSilverLuminous = itemMaterial.addOreDictItem(452, "nuggetSilverLuminous");
        nuggetElectrumFlux = itemMaterial.addOreDictItem(453, "nuggetElectrumFlux");
        nuggetMolybdenumResonant = itemMaterial.addOreDictItem(454, "nuggetMolybdenumResonant");
        nuggetChromiumCarbide = itemMaterial.addOreDictItem(455, "nuggetChromiumCarbide");
        nuggetBismuthBronzeColdfire = itemMaterial.addOreDictItem(456, "nuggetBismuthBronzeColdfire");
        nuggetPyrum = itemMaterial.addOreDictItem(457, "nuggetPyrum");
        nuggetGelinium = itemMaterial.addOreDictItem(458, "nuggetGelinium");
        nuggetLumium = itemMaterial.addOreDictItem(459, "nuggetLumium");
        nuggetSignalum = itemMaterial.addOreDictItem(460, "nuggetSignalum");
        nuggetEnderium = itemMaterial.addOreDictItem(461, "nuggetEnderium");
        nuggetCarbonite = itemMaterial.addOreDictItem(462, "nuggetCarbonite");
        nuggetTherminate = itemMaterial.addOreDictItem(463, "nuggetTherminate");
        nuggetNullmetal = itemMaterial.addOreDictItem(464, "nuggetNullmetal");
        nuggetIocarbide = itemMaterial.addOreDictItem(465, "nuggetIocarbide");
        nuggetCryocarbide = itemMaterial.addOreDictItem(466, "nuggetCryocarbide");
        nuggetPyrocarbide = itemMaterial.addOreDictItem(467, "nuggetPyrocarbide");
        nuggetTenebride = itemMaterial.addOreDictItem(468, "nuggetTenebride");
        nuggetIlluminide = itemMaterial.addOreDictItem(469, "nuggetIlluminide");
        nuggetZythoferride = itemMaterial.addOreDictItem(470, "nuggetZythoferride");
        nuggetCrystalFlux = itemMaterial.addOreDictItem(471, "nuggetCrystalFlux");
        //* Lapiquartz
        //* Rust
        //* Sulfur
        //* Saltpeter
        nuggetMithrilFlux = itemMaterial.addOreDictItem(476, "nuggetMithrilFlux");
        nuggetMithrilTinker = itemMaterial.addOreDictItem(477, "nuggetMithrilTinker");
        //* Thorium     x+078
        //* U235        x+079
        //* U238        x+080
        nuggetMagnetite = itemMaterial.addOreDictItem(481, "nuggetMagnetite");
        nuggetNeodymiumMagnet = itemMaterial.addOreDictItem(482, "nuggetNeodymiumMagnetMetal");
        nuggetIronMagnet = itemMaterial.addOreDictItem(483, "nuggetIronMagnetic");
        nuggetManganeseMagnet = itemMaterial.addOreDictItem(484, "nuggetManganeseMagnetic");
        nuggetCobaltMagnet = itemMaterial.addOreDictItem(485, "nuggetCobaltMagnetic");
        nuggetNickelMagnet = itemMaterial.addOreDictItem(486, "nuggetNickelMagnetic");
        nuggetInvarMagnet = itemMaterial.addOreDictItem(487, "nuggetInvarMagnetic");
        nuggetHCSteelMagnet = itemMaterial.addOreDictItem(488, "nuggetHighCarbonSteelMagnetic", "nuggetSteelMagnetic");
        nuggetSteelMagnet = itemMaterial.addOreDictItem(489, "nuggetSteelMagnetic", "nuggetRefinedSteelMagnetic");
        nuggetHSLAMagnet = itemMaterial.addOreDictItem(490, "nuggetHSLAMagnetic");
        //* Amber       x+091
        nuggetNichrome = itemMaterial.addOreDictItem(492, "nuggetNichrome");
        nuggetPolycarbide = itemMaterial.addOreDictItem(493, "nuggetPolycarbide");
        nuggetVorpal = itemMaterial.addOreDictItem(494, "nuggetVorpal");
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
        nuggetKroostyl = itemMaterial.addOreDictItem(506, "nuggetKroostyl");
        nuggetYttrium = itemMaterial.addOreDictItem(507, "nuggetYtrium");
        nuggetRuthenium = itemMaterial.addOreDictItem(508, "nuggetRuthenium");
        nuggetLanthanum = itemMaterial.addOreDictItem(509, "nuggetLanthanum");
        nuggetCerium = itemMaterial.addOreDictItem(510, "nuggetCerium");
        //* Magnesium   x+111
        //* Calcium     x+112
        //* Strontium   x+113
    }

    //600-799 Gems
    public void loadGems() {}

    //1000-1199 Gears
    public void loadGears() {
        /*gemMolybdenum = itemMaterial.addOreDictItem(53, "gemMolybdenum");
        crystalMolybdenum = itemMaterial.addOreDictItem(54, "crystalMolybdenum");
        crystalDioptase = itemMaterial.addOreDictItem(55, "crystalDioptase");
        crystalPyrope = itemMaterial.addOreDictItem(56, "crystalPyrope");
        gemNaturalAmber = itemMaterial.addOreDictItem(57, "gemAmber");
        gemSyntheticMolybdenum = itemMaterial.addOreDictItem(58, "gemSyntheticMolybdenum");
        gemSyntheticDioptase = itemMaterial.addOreDictItem(59, "gemSyntheticDioptase");
        gemSyntheticPyrope = itemMaterial.addOreDictItem(60, "gemSyntheticPyrope");
        gemSyntheticGreen = itemMaterial.addOreDictItem(61, "gemSyntheticGreen");

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
        //partGearCore = itemMaterial.addOreDictItem(79, "partGearCore");

        // Capacitors
        //partCapacitorLv1 = itemMaterial.addOreDictItem(88, "partCapacitorLv1");*/

    }

    //1200-1399 1/4 Dusts
    public void loadDustsSmall() {}

    //1400-1599 1/9 Dusts
    public void loadDustsTiny() {}

    //1600-1799 Foils
    public void loadFoils() {}

    //1800-1999 Plates
    public void loadPlates() {}

    //2000-2199 Dense Plates
    public void loadPlatesDense() {}

    //2200-2399 Washers
    public void loadWashers() {}

    //2400-2599 Bolts
    public void loadBolts() {}

    //2600-2799 Nuts
    public void loadNuts() {}

    //2800-2999 Ball Bearings
    public void loadBearings() {}

    //3000-3199 Shafts
    public void loadShafts() {}

    //3200-3399 Panels
    public void loadPanels() {}

    //5000-5999 Tiered Components
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

    //6000-6999 Non-Tiered Components
    public void loadParts() {}

    //10000-10199 Trace Minerals

    //11000-11199 Ore Chips

    //11200-11399 Dirty Ore Chunks

    //11400-11599 Clean Ore Chunks

    //11600-11799 Crushed Ores

    //11800-11999 Purified Crushed Ores

    //12000-12199 Dirty Ground Ores

    //12200-12399 Clean Ground Ores

    //12400-12599 Impure Ore Dusts

    //12600-12799 Purified Ore Dusts

    //12800-12999 Ore Slurries

    //13000-13199 Ore Solutions

    //13200-13399 Ore Flakes

    //13400-13599 Pulverized Ore Flakes

    //13600-13799 Centrifuged Ores

    //13800-13999 Purified Centrifuged Ores

    //14000-14199 Rendered Ore Chunks

    //14200-14399 Crystallized Ores



    public void loadTools() {
	    itemProtoSonicWrench = (ItemFluxGearProtoSonicWrench) new ItemFluxGearProtoSonicWrench().setUnlocalizedName("tool", "prototypeSonicWrench");
        toolProtoSonicWrench = itemProtoSonicWrench.addItem(0, "protoSonicWrench");
    }

    //public void loadEnchants() {}

    //public void loadTiles() {}

    public void metalCraftingRecipes() {
        //TODO-- UPDATE
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

        /* Weird Science Legacy Recipes
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
        //GameRegistry.addRecipe(new ShapelessOreRecipe(melonPan, Items.bread, Items.melon));
        //GameRegistry.addRecipe(new ShapelessOreRecipe(bucketLye, Items.water_bucket, dustAshes));
        //GameRegistry.addRecipe(new ShapelessOreRecipe(bucketAcid, Items.water_bucket, Items.gunpowder));
        //GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(itemInteractive, 1, 0), "dustRust", "dustAluminium"));
        GameRegistry.addRecipe(new ShapedOreRecipe(toolProtoSonicWrench, "B B", "ADA", " B ", 'B', "ingotBronze", 'A', "ingotAluminium", 'D', "gemDioptase"));
        GameRegistry.addRecipe();
        //GameRegistry.addRecipe(new ShapedOreRecipe(toolProtoSonicWrench, "B B", "WDW", " B ", 'B', "ingotMithrilBronze", 'W', "ingotTungsten", 'D', "gemDioptase"));
    }

    public void furnaceRecipes() {
        //TODO-- UPDATE
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

        //GameRegistry.addSmelting(aluminumSludge, new ItemStack(itemAlum), 0.0F);

        // Temporary way to get Rust
        //GameRegistry.addSmelting(blockRust, new ItemStack(Blocks.iron_block, 1, 0), 0.0F);*/
    }

    public void registerPoorOres() {
        /*
		*   if(FluxGearConfigWorld.generatePoor$ == 0 || (FluxGearConfigWorld.generatePoor$ == 2 && FluxGearData.isRailcraftLoaded == true))
		*       MinecraftForge.ORE_GEN_BUS.register(new Poor$Generator);
		*/
    }

    //public void modIntegration() {}

    public void aluminiumArc() {
        OreDictionary.registerOre("ingotAluminum", ingotAluminium);
        OreDictionary.registerOre("dustAluminum", dustAluminium);
        //OreDictionary.registerOre("nuggetAluminum", nuggetAluminium);
        OreDictionary.registerOre("ingotAluminumBrass", ingotAluminiumBrass);
        OreDictionary.registerOre("dustAluminumBrass", dustAluminiumBrass);
        //OreDictionary.registerOre("nuggetAluminumBrass", nuggetAluminiumBrass);
        OreDictionary.registerOre("oreAluminum", oreBauxite);
        OreDictionary.registerOre("blockAluminum", blockAluminium);

        if (FluxGearConfig.cobaltAssimilation) {
            OreDictionary.registerOre("ingotCobalt", ingotCobalt);
            OreDictionary.registerOre("dustCobalt", dustCobalt);
            //OreDictionary.registerOre("nuggetCobalt", nuggetCobalt);
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
    public static BlockOreMain blockOreMain;
    public static BlockOreAux blockOreAux;
    public static BlockStorageMain blockStorageMain;
    public static BlockStorageAux blockStorageAux;
    public static BlockAlloyMain blockAlloyMain;
    public static BlockAlloyAux blockAlloyAux;
    public static BlockStorageAlch blockStorageAlch;
    public static BlockStorageAdv blockStorageAdv;
    public static BlockEarthen blockEarthen;
    public static BlockPoorOreMain blockPoorOreMain;
    public static BlockPoorOreAux blockPoorOreAux;

    // Base Items
    public static BucketFluxGear itemBucket;
    public static ItemFluxGear itemMaterial;
    public static ItemFluxGear/*InteracivePFG*/ itemInteractive;
    public static ItemFluxGear /*FoodPFG*/ itemFood;
    public static ItemFluxGearProtoSonicWrench itemProtoSonicWrench;

    //public static BlockTileEntity blockTileEntity;

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
    public static ItemStack blockNichrome;
    public static ItemStack blockPolycarbide;

    public static ItemStack blockIridiumSands;
    public static ItemStack blockPoorIridiumSands;
    public static ItemStack blockAluminosilicateSludge;


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

    /*public static ItemArmorRF itemHelmetInvarRF;
    public static ItemArmorRF itemChestInvarRF;
    public static ItemArmorRF itemLegsInvarRF;
    public static ItemArmorRF itemBootsInvarRF;*/

    // Mechanic's Utilities
    public static BlockTemporalPylon blockTemporalPylon;
    public static BlockWoodenTileEntity woodenTileEntity;

    public static ItemStack temporalPylon;
    public static ItemStack namingTable;
}