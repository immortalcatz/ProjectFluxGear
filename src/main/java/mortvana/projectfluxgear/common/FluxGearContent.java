package mortvana.projectfluxgear.common;

import cofh.core.fluid.BlockFluidCoFHBase;
import cofh.core.item.ItemBucket;
import cofh.core.util.fluid.DispenserEmptyBucketHandler;
import cofh.core.util.fluid.DispenserFilledBucketHandler;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

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

import mortvana.fluxgearcore.item.ItemBase;
import mortvana.fluxgearcore.util.helper.ItemHelper;

import mortvana.projectfluxgear.block.basic.*;
import mortvana.projectfluxgear.item.tool.ItemProtoSonicWrench;
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
        initBlocks();
        //initMachines();
	    initItems();
        //initTools();
        //initAugments();
        //addOreDict();
	    metalCraftingRecipes();
        machineCraftingRecipes();
        otherCraftingRecipes();
	    furnaceRecipes();
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
        blockPotato = new ItemStack(blockStorageAdv, 1, 14);
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

    public void initBlocks() {
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
        ItemHelper.registerWithHandlers("blockArsenic", blockArsenic); //Uranium has custom properties
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
        ItemHelper.registerWithHandlers("blockIronMagnet", blockFeMagnet);
        ItemHelper.registerWithHandlers("blockManganeseMagnet", blockMnMagnet);
        ItemHelper.registerWithHandlers("blockCobaltMagnet", blockCoMagnet);
        ItemHelper.registerWithHandlers("blockNickelMagnet", blockNiMagnet);
        ItemHelper.registerWithHandlers("blockInvarMagnet", blockInvarMagnet);
        ItemHelper.registerWithHandlers("blockHCSteelMagnet", blockHCSteelMagnet);
        ItemHelper.registerWithHandlers("blockSteelMagnet", blockSteelMagnet);
        ItemHelper.registerWithHandlers("blockHSLAMagnet", blockHSLAMagnet);
        ItemHelper.registerWithHandlers("blockAmber", blockAmber);
        ItemHelper.registerWithHandlers("blockPotato", blockPotato);
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
        itemBucket = (ItemBucket) new ItemBucket("projectfluxgear").setUnlocalizedName("bucket").setCreativeTab(ProjectFluxGear.tab);
        //itemFood =
        itemInteractive = (ItemBase) new ItemBase("projectfluxgear").setUnlocalizedName("interactive").setCreativeTab(ProjectFluxGear.tab);

        //Buckets TODO-- Redo
        bucketGhastTears = itemBucket.addItem(0, "bucketGhastTears", 1);
        bucketLye = itemBucket.addItem(1, "bucketLye", 0);
        bucketAcid = itemBucket.addItem(2, "bucketAcid", 0);
        bucketEtchingAcid = itemBucket.addItem(3, "bucketEtchingAcid", 0);
        bucketSmog = itemBucket.addItem(4, "bucketSmog", 0);
        bucketBlood = itemBucket.addItem(5, "bucketBlood", 1);
        bucketGelidPyrotheum = itemBucket.addItem(6, "bucketGelidPyrotheum", 2);

        //Food

        //Interactive
        dustThermite = itemInteractive.addOreDictItem(0, "dustThermite");
        coagulantAlum = itemInteractive.addItem(1, "coagulant");


        //dust = itemMaterial.addOreDictItem(, "dust");
    }

    public void loadParts() {
        itemMaterial = (ItemBase) new ItemBase("projectfluxgear").setUnlocalizedName("material").setCreativeTab(ProjectFluxGear.tab);

        /*// Standard Ingots
        ingotZinc = itemMaterial.addOreDictItem(0, "ingotZinc");
        ingotBismuth = itemMaterial.addOreDictItem(1, "ingotBismuth");
        ingotManganese = itemMaterial.addOreDictItem(2, "ingotManganese");
        ingotPalladium = itemMaterial.addOreDictItem(3, "ingotPalladium");
        ingotMolybdenum = itemMaterial.addOreDictItem(4, "ingotMolybdenum");
        ingotCobalt = itemMaterial.addItem(5, "ingotCobalt");
        OreDictionary.registerOre("ingotNaturalCobalt", ingotCobalt);
        ingotTungsten = itemMaterial.addOreDictItem(6, "ingotTungsten");
        ingotAluminium = itemMaterial.addOreDictItem(7, "ingotAluminium");
        ingotChromium = itemMaterial.addOreDictItem(8, "ingotChromium");
        ingotTitanium = itemMaterial.addOreDictItem(9, "ingotTitanium");
        ingotIridium = itemMaterial.addOreDictItem(10, "ingotIridium");

        // Standard Dusts
        dustZinc = itemMaterial.addOreDictItem(16, "dustZinc");
        dustBismuth = itemMaterial.addOreDictItem(17, "dustBismuth");
        dustManganese = itemMaterial.addOreDictItem(18, "dustManganese");
        dustPalladium = itemMaterial.addOreDictItem(19, "dustPalladium");
        dustMolybdenum = itemMaterial.addOreDictItem(20, "dustMolybdenum");
        dustCobalt = itemMaterial.addItem(21, "dustCobalt");
        OreDictionary.registerOre("dustNaturalCobalt", dustCobalt);
        dustTungsten = itemMaterial.addOreDictItem(22, "dustTungsten");
        dustAluminium = itemMaterial.addOreDictItem(23, "dustAluminium");
        dustChromium = itemMaterial.addOreDictItem(24, "dustChromium");
        dustTitanium = itemMaterial.addOreDictItem(25, "dustTitanium");
        dustIridium = itemMaterial.addOreDictItem(26, "dustIridium");
        dustMagnetite = itemMaterial.addOreDictItem(27, "dustMagnetite");
        dustMyuvil = itemMaterial.addOreDictItem(28, "dustMyuvil");
        /*dustArsenic = itemMaterial.addOreDictItem(29, "dustArsenic");
        dustAntimony = itemMaterial.addOreDictItem(30, "dustAntimony");*/

        /*// Standard Nuggets
        nuggetZinc = itemMaterial.addOreDictItem(32, "nuggetZinc");
        nuggetBismuth = itemMaterial.addOreDictItem(33, "nuggetBismuth");
        nuggetManganese = itemMaterial.addOreDictItem(34, "nuggetManganese");
        nuggetPalladium = itemMaterial.addOreDictItem(35, "nuggetPalladium");
        nuggetMolybdenum = itemMaterial.addOreDictItem(36, "nuggetMolybdenum");
        nuggetCobalt = itemMaterial.addItem(37, "nuggetCobalt");
        OreDictionary.registerOre("nuggetNaturalCobalt", nuggetCobalt);
        nuggetTungsten = itemMaterial.addOreDictItem(38, "nuggetTungsten");
        nuggetAluminium = itemMaterial.addOreDictItem(39, "nuggetAluminium");
        nuggetChromium = itemMaterial.addOreDictItem(40, "nuggetChromium");
        nuggetTitanium = itemMaterial.addOreDictItem(41, "nuggetTitanium");
        nuggetIridium = itemMaterial.addOreDictItem(42, "nuggetIridium");
        nuggetMagnetite = itemMaterial.addOreDictItem(43, "nuggetMagnetite");

        // Gems
        gemDioptase = itemMaterial.addOreDictItem(48, "gemDioptase");
        gemPyrope = itemMaterial.addOreDictItem(49, "gemPyrope");
        gemMagnetite= itemMaterial.addOreDictItem(50, "gemMagnetite");
        gemAmber = itemMaterial.addOreDictItem(51, "gemSyntheticAmber");
        gemCrystalFlux = itemMaterial.addOreDictItem(52, "gemCrystalFlux");
        gemMolybdenum = itemMaterial.addOreDictItem(53, "gemMolybdenum");
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
        /*partCircuitPlate = itemMaterial.addOreDictItem(65, "partCircuitPlate");
        partUnprocessedPCB = itemMaterial.addOreDictItem(66, "partUnprocessedPCB");
        partUnassembledPCB = itemMaterial.addOreDictItem(67, "partUnassembledPCB");*/
        /*partAssembledPCB = itemMaterial.addOreDictItem(68, "partAssembledPCB");
        partTransistor = itemMaterial.addOreDictItem(69, "partTransistor");
        partResistor = itemMaterial.addOreDictItem(70, "partResistor");
        //partSpring = itemMaterial.addOreDictItem(71, "partSpring");
        partFluxFilter = itemMaterial.addOreDictItem(72, "partFluxFilter");
        /*partIonThruster = itemMaterial.addOreDictItem(73, "partIonThruster");
        partResonantThruster = itemMaterial.addOreDictItem(74, "partResonantIonThruster");
        partMagnet = itemMaterial.addOreDictItem(75, "partMagnet");
        partAlCoNiMagnet = itemMaterial.addOreDictItem(76, "partAlCoNiMagnet");*/
        /*partServoMotor = itemMaterial.addOreDictItem(77, "partServoMotor");
        partSolenoid = itemMaterial.addOreDictItem(78, "partSolenoid");
        //partGearCore = itemMaterial.addOreDictItem(79, "partGearCore");

        // Capacitors
        //partCapacitorLv1 = itemMaterial.addOreDictItem(88, "partCapacitorLv1");

        // Magnetized Ingots
        /*ingotIronMagnet = itemMaterial.addOreDictItem(96, "ingotIronMagnet");
        ingotManganeseMagnet = itemMaterial.addOreDictItem(97, "ingotManganeseMagnet");
        ingotCobaltMagnet = itemMaterial.addOreDictItem(98, "ingotCobaltMagnet");
        ingotNickelMagnet = itemMaterial.addOreDictItem(99, "ingotNickelMagnet");
        ingotSteelMagnet = itemMaterial.addOreDictItem(100, "ingotSteelMagnet");
        ingotInvarMagnet = itemMaterial.addOreDictItem(101, "ingotInvarMagnet");

        // Magnetized Nuggets
        nuggetIronMagnet = itemMaterial.addOreDictItem(112, "nuggetIronMagnet");
        nuggetManganeseMagnet = itemMaterial.addOreDictItem(113, "nuggetManganeseMagnet");
        nuggetCobaltMagnet = itemMaterial.addOreDictItem(114, "nuggetCobaltMagnet");
        //nuggetNickelMagnet = itemMaterial.addOreDictItem(114, "nuggetNickelMagnet");
        nuggetSteelMagnet = itemMaterial.addOreDictItem(115, "nuggetSteelMagnet");
        nuggetInvarMagnet = itemMaterial.addOreDictItem(116, "nuggetInvarMagnet");*/

        // Simple Alloys
        //Brass, BisBronze, Cupro, AluBrass, DullRedsolder, Redsolder
        /*ingotBrass = itemMaterial.addOreDictItem(128, "ingotBrass");
        ingotBismuthBronze = itemMaterial.addOreDictItem(129, "ingotBismuthBronze");
        ingotCupronickel = itemMaterial.addOreDictItem(130, "ingotCupronickel");
        ingotAluminiumBrass = itemMaterial.addOreDictItem(131, "ingotAluminiumBrass");

        // Simple Alloy Dusts
        dustBrass = itemMaterial.addOreDictItem(136, "dustBrass");
        dustBismuthBronze = itemMaterial.addOreDictItem(137, "dustBismuthBronze");
        dustCupronickel = itemMaterial.addOreDictItem(138, "dustCupronickel");
        dustAluminiumBrass = itemMaterial.addOreDictItem(139, "dustAluminiumBrass");

        // Simple Alloy Nuggets
        nuggetBrass = itemMaterial.addOreDictItem(144, "nuggetBrass");
        nuggetBismuthBronze =  itemMaterial.addOreDictItem(145, "nuggetBismuthBronze");
        nuggetCupronickel = itemMaterial.addOreDictItem(146, "nuggetCupronickel");
        nuggetAluminiumBrass = itemMaterial.addOreDictItem(147, "nuggetAluminiumBrass");*/

    }

    public void loadTools() {
	    itemProtoSonicWrench = (ItemProtoSonicWrench) new ItemProtoSonicWrench().setUnlocalizedName("tool", "prototypeSonicWrench");
    }

    //public void loadEnchants() {}

    //public void loadTiles() {}

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

	public void initItems() {
		toolProtoSonicWrench = itemProtoSonicWrench.addItem(0, "protoSonicWrench");
	}

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
        ItemHelper.addStorageRecipe(blockTechnomancy, "ingotTechnomancy");
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

    //public void modIntegration() {}

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

    // Fluid Blocks
    public static BlockFluidCoFHBase blockFluidGhastTear;
    public static BlockFluidCoFHBase blockFluidEtchingAcid;
    public static BlockFluidCoFHBase blockFluidSmog;
    public static BlockFluidCoFHBase blockFluidAcid;
    public static BlockFluidCoFHBase blockFluidBlood;

    // Base Items
    public static ItemBucket itemBucket;
    public static ItemBase itemMaterial;
    public static ItemBase/*InteracivePFG*/ itemInteractive;
    public static ItemProtoSonicWrench itemProtoSonicWrench;

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
    public static ItemStack blockPotato;
    public static ItemStack blockPolycarbide;

    public static ItemStack blockIridiumSands;
    public static ItemStack blockPoorIridiumSands;
    public static ItemStack blockAluminosilicateSludge;

    //Buckets
    public static ItemStack bucketGhastTears;
    public static ItemStack bucketLye;
    public static ItemStack bucketAcid;
    public static ItemStack bucketEtchingAcid;
    public static ItemStack bucketSmog;
    public static ItemStack bucketBlood;
    public static ItemStack bucketGelidPyrotheum;

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
    //* Dioptase
    //* Pyrope
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
    public static ItemStack ingotSteelStainless;
    public static ItemStack ingotTungstenSteel;
    public static ItemStack ingotEletriplatinum;
    public static ItemStack ingotMithril;
    public static ItemStack ingotTechnomancy;
    public static ItemStack ingotTechnomancyResonant;
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
    //* Crystal Flux
    //* Lapiquartz
    //* Rust
    //* WPS
    //* VIS
    //* Sulfur
    //* Saltpeter
    public static ItemStack ingotMithrilFlux;
    public static ItemStack ingotMithrilTinker;
    //* Thorium
    //* U235
    //* U238
    //* Magnetite
    public static ItemStack ingotNeodymiumMagnet;
    public static ItemStack ingotIronMagnet;
    public static ItemStack ingotManganeseMagnet;
    public static ItemStack ingotCobaltMagnet;
    public static ItemStack ingotNickelMagnet;
    public static ItemStack ingotInvarMagnet;
    public static ItemStack ingotHCSteelMagnet;
    public static ItemStack ingotSteelMagnet;
    public static ItemStack ingotHSLAMagnet;
    //* Amber
    //* Ashes
    //* Polycarbide
    //* Vorpal
    //* Iron
    //* Gold
    //* Diamond
    //* Coal
    //* Charcoal
    //* Obsidian
    //* Sufur
    //* Saltpeter
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

    // Standard Dusts
    public static ItemStack dustZinc;
    public static ItemStack dustBismuth;
    public static ItemStack dustManganese;
    public static ItemStack dustPalladium;
    public static ItemStack dustMolybdenum;
    public static ItemStack dustCobalt;
    public static ItemStack dustTungsten;
    public static ItemStack dustAluminium;
    public static ItemStack dustChromium;
    public static ItemStack dustTitanium;
    public static ItemStack dustIridium;

    // Random Dusts
    public static ItemStack dustPitchblende;
    public static ItemStack dustNiedermayrite;
    public static ItemStack dustRust;
    public static ItemStack dustAshes;
    public static ItemStack dustMagnetite;
    public static ItemStack dustArsenic;
    public static ItemStack dustAntimony;
    public static ItemStack dustMyuvil;

    // Standard Nuggets
    public static ItemStack nuggetZinc;
    public static ItemStack nuggetBismuth;
    public static ItemStack nuggetManganese;
    public static ItemStack nuggetPalladium;
    public static ItemStack nuggetMolybdenum;
    public static ItemStack nuggetCobalt;
    public static ItemStack nuggetTungsten;
    public static ItemStack nuggetAluminium;
    public static ItemStack nuggetChromium;
    public static ItemStack nuggetTitanium;
    public static ItemStack nuggetIridium;
    public static ItemStack nuggetMagnetite;

    // Gems
    public static ItemStack gemDioptase;
    public static ItemStack gemPyrope;
    public static ItemStack gemMagnetite;
    public static ItemStack gemAmber;
    public static ItemStack gemCrystalFlux;
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

    // Capacitors
    public static ItemStack partCapacitorLv1;

    // Magnetized Ingots


    // Magnetized Nuggets
    public static ItemStack nuggetIronMagnet;
    public static ItemStack nuggetManganeseMagnet;
    public static ItemStack nuggetCobaltMagnet;
    public static ItemStack nuggetNickelMagnet;
    public static ItemStack nuggetSteelMagnet;
    public static ItemStack nuggetInvarMagnet;

    // Simple Alloy Dusts
    public static ItemStack dustBrass;
    public static ItemStack dustBismuthBronze;
    public static ItemStack dustCupronickel;
    public static ItemStack dustAluminiumBrass;

    // Simple Alloy Nuggets
    public static ItemStack nuggetBrass;
    public static ItemStack nuggetBismuthBronze;
    public static ItemStack nuggetCupronickel;
    public static ItemStack nuggetAluminiumBrass;

    // Complex Alloy Ingots


    // Complex Alloy Dusts
    public static ItemStack dustMithrilBronze;
    public static ItemStack dustEletriplatinum;
    public static ItemStack dustSteel;
    public static ItemStack dustTungstenSteel;
    public static ItemStack dustSteelStainless;
    public static ItemStack dustTechnomancy;
    public static ItemStack dustTechnomancyResonant;
    public static ItemStack dustTungstenBlazing;
    public static ItemStack dustPlatinumGelid;
    public static ItemStack dustSilverLuminous;
    public static ItemStack dustElectrumFlux;
    public static ItemStack dustMolybdenumResonant;
    public static ItemStack dustChromiumCarbide;
    public static ItemStack dustColdfireBismuthBronze;
    public static ItemStack dustCarbonite;
    public static ItemStack dustPyrum;
    public static ItemStack dustGelinium;

    // Complex Alloy Nuggets
    public static ItemStack nuggetMithrilBronze;
    public static ItemStack nuggetEletriplatinum;
    public static ItemStack nuggetSteel;
    public static ItemStack nuggetTungstenSteel;
    public static ItemStack nuggetSteelStainless;
    public static ItemStack nuggetTechnomancy;
    public static ItemStack nuggetTechnomancyResonant;
    public static ItemStack nuggetTungstenBlazing;
    public static ItemStack nuggetPlatinumGelid;
    public static ItemStack nuggetSilverLuminous;
    public static ItemStack nuggetElectrumFlux;
    public static ItemStack nuggetMolybdenumResonant;
    public static ItemStack nuggetChromiumCarbide;
    public static ItemStack nuggetColdfireBismuthBronze;
    public static ItemStack nuggetCarbonite;
    public static ItemStack nuggetPyrum;
    public static ItemStack nuggetGelinium;



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

    // More Components
    public static ItemStack coilHeatingRedstone;
    public static ItemStack coilHeatingCupronickel;
    public static ItemStack coilSteel;
    public static ItemStack partRedLED;
    public static ItemStack partGreenLED;
    public static ItemStack partBlueLED;
    public static ItemStack partUltravioletLight;
    public static ItemStack panelSteel;
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



    // Random Stuff
    public static ItemStack feeshSkeleton;

    // Interactive Items
    public static ItemStack dustThermite;
    public static ItemStack coagulantAlum;


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