package mortvana.legacy.dependent.seconddegree.projectfluxgear.util;

import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.dependent.firstdegree.projectfluxgear.block.BlockGravelOreAux;
import mortvana.legacy.dependent.firstdegree.projectfluxgear.block.BlockGravelOreMain;

import mortvana.legacy.dependent.seconddegree.projectfluxgear.block.BlockAlloyAux;
import mortvana.melteddashboard.block.FluxGearBlock;
import mortvana.melteddashboard.block.FluxGearItemBlock;
import mortvana.melteddashboard.util.enums.EnumBlockType;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.ItemBlock;

import static mortvana.projectfluxgear.library.FluxGearLibrary.*;

public class BlockInformation {
    /* *=-=-=-=* Fields *=-=-=-=* */

    // Materials
    public static final Material materialFluidGhastTear = new MaterialLiquid(MapColor.snowColor);
    public static final Material materialFluidSmog = new MaterialLiquid(MapColor.brownColor);
    public static Material materialSoilOre = new Material(MapColor.sandColor)/*.setRequiresTool()*/; //TODO: Effing AccessTransformer!

    // Names
    public static final String[] NAMES_STORE_AUX = { "antimony", "arsenic", "neodymium", "tesseractium", "cadmium", "tellurium", "osmium", "iridium", "indium", "antimonialBronze", "arsenicalBronze", "vanadium", "unobtainium", "dioptase", "pyrope", "myuvil" };
    public static final String[] NAMES_ALLOY_MAIN = { "bronze", "brass", "invar", "bismuthBronze", "cupronickel", "aluminiumBrass", "electrum", "dullRedsolder", "redsolder", "highCarbonSteel", "steel", "hsla", "stainlessSteel", "tungstenSteel", "electriplatinum", "mithril" };
    public static final String[] NAMES_ALLOY_AUX = { "technomancy", "resonantTechnomancy", "tungstenBlazing", "platinumGelid", "silverLuminous", "electrumFlux", "molybdenumResonant", "chromiumCarbide", "bismuthBronzeColdfire", "pyrum", "gelinum", "lumium", "signalum", "enderium", "carbonite", "therminate" };
    public static final String[] NAMES_STORE_ALCH = { "nullmetal", "iocarbide", "cryocarbide", "pyrocarbide", "tenebride", "illuminide", "zythoferride", "crystalFlux", "lapiquartz", "rust", "whitePointStar", "voidInfernoStar", "sulfur", "saltpeter", "mithrilFlux", "mithrilTinker" };
    //TODO: Move Amber
    public static final String[] NAMES_STORE_ADV = { "thorium", "uranium235", "uranium238", "magnetite", "neodymiumM"/**/, "ironM", "manganeseM"/**/, "cobaltM", "nickelM", "invarM", "highCarbonSteelM", "steelM", "hslaM", "amber", "nichrome", "polycarbide" };

    // Hardness
    public static final float[] HARDNESS_STORE_AUX = { 3, 3, 5, 8, 5, 5, 11, 13, 5, 6, 6, 7, 42, 7, 7, 4 };
    public static final float[] HARDNESS_ALLOY_MAIN = { 5, 5, 6, 5, 5, 5, 5, 5, 5, 7, 8, 8, 8, 13, 6, 5 };
    public static final float[] HARDNESS_ALLOY_AUX = { 7, 11, 13, 8, 8, 8, 13, 11, 16, 11, 6, 5, 5, 40, 7, 13 };
    public static final float[] HARDNESS_STORE_ALCH = { 1, 5, 5, 5, 7, 7, 11, 5, 5, 0.6F, 8, 8, 5, 5, 8, 11 };
    public static final float[] HARDNESS_STORE_ADV  = { 5, 5, 5, 5, 5, 5, 5, 7, 7, 6, 7, 8, 8, 4, 2, 42 };

    // Resistance
    public static final float[] RESISTANCE_STORE_AUX = { 5, 5, 8, 42, 6, 6, 13, 16, 6, 8, 8, 9, 500, 13, 13, 6 };
    public static final float[] RESISTANCE_ALLOY_MAIN = { 7, 6, 8, 8, 6, 6, 6, 6, 6, 12, 13, 13, 13, 42, 8, 10 };
    public static final float[] RESISTANCE_ALLOY_AUX = { 16, 128, 135, 85, 85, 85, 192, 192, 256, 35, 11, 9, 9, 120, 64, 128 };
    public static final float[] RESISTANCE_STORE_ALCH = { 1, 6, 7, 7, 10, 10, 42, 8, 8, 1, 42, 507, 5, 5, 32, 64 };
    public static final float[] RESISTANCE_STORE_ADV  = { 5, 5, 5, 8, 8, 10, 6, 8, 6, 8, 12, 13, 13, 6, 11, 1000 };

    // Light
    public static final int[] LIGHT_STORE_AUX = { 0, 0, 0, 7, 0, 0, 1, 4, 0, 0, 0, 1, 15, 8, 4, 4 };
    public static final int[] LIGHT_ALLOY_MAIN =  { 0, 0, 0, 2, 0, 2, 4, 2, 2, 0, 0, 0, 0, 0, 4, 2 };
    public static final int[] LIGHT_ALLOY_AUX = { 4, 8, 12, 4, 15, 7, 4, 2, 15, 12, 4, 15, 7, 4, 2, 15 };
    public static final int[] LIGHT_STORE_ALCH = { 0, 0, 4, 4, 0, 15, 7, 4, 2, 0, 15, 15, 0, 0, 7, 15 };
    public static final int[] LIGHT_STORE_ADV  = { 2, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    // Rarity
    public static final int[] RARITY_STORE_AUX = { 0, 0, 0, 2, 0, 0, 1, 2, 0, 0, 0, 1, 3, 2, 2, 1 };
    public static final int[] RARITY_ALLOY_MAIN = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 };
    public static final int[] RARITY_ALLOY_AUX = { 1, 2, 2, 2, 2, 2, 3, 2, 3, 1, 1, 1, 1, 2, 1, 2 };
    public static final int[] RARITY_STORE_ALCH = { 0, 0, 0, 0, 1, 1, 2, 1, 1, 0, 2, 3, 0, 0, 1, 2 };
    public static final int[] RARITY_STORE_ADV  = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 3 };


    public static String unlocDefault = "tile.fluxgear.block.";
    public static String unlocOre = "tile.fluxgear.ore.";
    public static String unlocStore = "tile.fluxgear.storage.";
    public static String unlocGravel = "tile.fluxgear.oreGravel.";
    public static String unlocPoore = "tile.fluxgear.orePoor.";

    //TODO: More Redstone Signal Stuff
	/* *=-=-=-=* Automagically Constructed Blocks *=-=-=-=* */

    public static Block blockOreMain = new FluxGearBlock(Material.rock, FluxGearContent.tabWorld, EnumBlockType.ORE, NAMES_ORE_PRIMARY, HARDNESS_ORE_PRIMARY, RESISTANCE_ORE_PRIMARY, LIGHT_ORES_PRIMARY, TEX_LOC_ORE).setBlockName("mortvana.projectfluxgear.oreMain");
    public static Block blockOreAux = new FluxGearBlock(Material.rock, FluxGearContent.tabWorld, EnumBlockType.ORE, NAMES_ORE_SECONDARY, HARDNESS_ORE_SECONDARY, RESISTANCE_ORE_SECONDARY, LIGHT_ORES_SECONDARY, TEX_LOC_ORE).setBlockName("mortvana.projectfluxgear.oreAux");
    public static Block blockStorageAux = new FluxGearBlock(Material.iron, FluxGearContent.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_AUX, HARDNESS_STORE_AUX, RESISTANCE_STORE_AUX, LIGHT_STORE_AUX, TEX_LOC_BLOCK).setBlockName("mortvana.projectfluxgear.storageAux");
    public static Block blockAlloyMain = new FluxGearBlock(Material.iron, FluxGearContent.tabMaterials, EnumBlockType.STORAGE, NAMES_ALLOY_MAIN, HARDNESS_ALLOY_MAIN, RESISTANCE_ALLOY_MAIN, LIGHT_ALLOY_MAIN, TEX_LOC_BLOCK).setBlockName("mortvana.projectfluxgear.alloyMain");
    public static Block blockStorageAdv = new FluxGearBlock(Material.iron, FluxGearContent.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_ADV, HARDNESS_STORE_ADV, RESISTANCE_STORE_ADV, LIGHT_STORE_ADV, TEX_LOC_BLOCK).setBlockName("mortvana.projectfluxgear.storageAdv");
    public static Block blockStorageAlch = new FluxGearBlock(Material.iron, FluxGearContent.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_ALCH, HARDNESS_STORE_ALCH, RESISTANCE_STORE_ALCH, LIGHT_STORE_ALCH, TEX_LOC_BLOCK).setBlockName("mortvana.projectfluxgear.storageAux");
    public static Block blockEarthen = new FluxGearBlock(materialSoilOre, FluxGearContent.tabMaterials, EnumBlockType.SOIL_ORE, NAMES_EARTH, HARDNESS_EARTH, RESISTANCE_EARTH, LIGHT_EARTH, TEX_LOC_DEFAULT).setBlockName("mortvana.projectfluxgear.earthen");
    public static Block blockPoorOreMain = new FluxGearBlock(Material.rock, FluxGearContent.tabWorld, EnumBlockType.ORE, NAMES_ORE_PRIMARY, HARDNESS_ORE_PRIMARY, RESISTANCE_ORE_PRIMARY, LIGHT_ORES_PRIMARY, TEX_LOC_POOR_ORE).setBlockName("mortvana.projectfluxgear.poorOreMain");
    public static Block blockPoorOreAux = new FluxGearBlock(Material.rock, FluxGearContent.tabWorld, EnumBlockType.ORE, NAMES_ORE_SECONDARY, HARDNESS_ORE_SECONDARY, RESISTANCE_ORE_SECONDARY, LIGHT_ORES_SECONDARY, TEX_LOC_POOR_ORE).setBlockName("mortvana.projectfluxgear.poorOreAux");

    public static ItemBlock itemBlockOreMain = new FluxGearItemBlock(blockOreMain, NAMES_ORE_PRIMARY, RARITY_ORES_PRIMARY, unlocOre);
    public static ItemBlock itemBlockOreAux = new FluxGearItemBlock(blockOreAux, NAMES_ORE_SECONDARY, RARITY_ORES_SECONDARY, unlocOre);
    public static ItemBlock ItemBlockStorageAux = new FluxGearItemBlock(blockStorageAux, NAMES_STORE_AUX, RARITY_STORE_AUX, unlocStore);
    public static ItemBlock ItemBlockAlloyMain = new FluxGearItemBlock(blockAlloyMain, NAMES_ALLOY_MAIN, RARITY_ALLOY_MAIN, unlocStore);
    public static ItemBlock ItemBlockAlloyAux = new FluxGearItemBlock(new BlockAlloyAux(), NAMES_ALLOY_AUX, RARITY_ALLOY_AUX, unlocStore);
    public static ItemBlock itemBlockStorageAlch = new FluxGearItemBlock(blockStorageAlch, NAMES_STORE_ALCH, RARITY_STORE_ALCH, unlocStore);
    public static ItemBlock itemBlockStorageAdv = new FluxGearItemBlock(blockStorageAdv, NAMES_STORE_ADV, RARITY_STORE_ADV, unlocStore);
    public static ItemBlock itemBlockEarthen = new FluxGearItemBlock(blockEarthen, NAMES_EARTH, RARITY_EARTH, unlocDefault);
    public static ItemBlock itemBlockGravelOreMain = new FluxGearItemBlock(new BlockGravelOreMain(), NAMES_ORE_PRIMARY, RARITY_ORES_PRIMARY, unlocGravel);
    public static ItemBlock itemBlockGravelOreAux = new FluxGearItemBlock(new BlockGravelOreAux(), NAMES_ORE_SECONDARY, RARITY_ORES_SECONDARY, unlocGravel);
    public static ItemBlock itemBlockPoorOreMain = new FluxGearItemBlock(blockPoorOreMain, NAMES_ORE_PRIMARY, RARITY_ORES_PRIMARY, unlocPoore);
    public static ItemBlock itemBlockPoorOreAux = new FluxGearItemBlock(blockPoorOreAux, NAMES_ORE_SECONDARY, RARITY_ORES_SECONDARY, unlocPoore);

}
