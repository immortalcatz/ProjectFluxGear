package mortvana.projectfluxgear.common;

import cofh.core.fluid.BlockFluidCoFHBase;
import cofh.core.item.ItemBucket;
import cofh.core.util.fluid.DispenserEmptyBucketHandler;
import cofh.core.util.fluid.DispenserFilledBucketHandler;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import mortvana.projectfluxgear.block.BlockAlloy;
import mortvana.projectfluxgear.block.BlockFluidicAlloy;
import mortvana.projectfluxgear.block.BlockOre;
import mortvana.projectfluxgear.block.BlockStorage;
import mortvana.fluxgearcore.item.ItemBase;
import mortvana.fluxgearcore.util.helper.ItemHelper;

import mortvana.projectfluxgear.item.tool.ItemProtoSonicWrench;
import net.minecraftforge.oredict.ShapelessOreRecipe;

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
        //initBlocks();
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
        blockOre = new BlockOre();
        blockStorage = new BlockStorage();
        blockAlloy = new BlockAlloy();
        blockFluidicAlloy = new BlockFluidicAlloy();

        //blockTileEntity = new BlockTileEntity();

        //blockFluidGhastTear = new BlockFluidGhastTear();

        blockOre.preInit();
        blockStorage.preInit();
	    blockAlloy.preInit();
        blockFluidicAlloy.preInit();

        // OreDict Ores
        ItemHelper.registerWithHandlers("oreSphalerite", oreSphalerite);
        ItemHelper.registerWithHandlers("oreBismuthinite", oreBismuthinite);
        ItemHelper.registerWithHandlers("orePyrolusite", orePyrolusite);
        ItemHelper.registerWithHandlers("oreBraggite", oreBraggite);
        ItemHelper.registerWithHandlers("oreMolybdenite", oreMolybdenite);
        ItemHelper.registerWithHandlers("oreCobaltite", oreCobaltite);
        ItemHelper.registerWithHandlers("oreWolframite", oreWolframite);
        ItemHelper.registerWithHandlers("oreBauxite", oreBauxite);
        ItemHelper.registerWithHandlers("oreChromite", oreChromite);
        ItemHelper.registerWithHandlers("oreIlmenite", oreIlmenite);
        ItemHelper.registerWithHandlers("oreMagnetite", oreMagnetite);
        ItemHelper.registerWithHandlers("oreDioptase", oreDioptase);
        ItemHelper.registerWithHandlers("orePyrope", orePyrope);
        ItemHelper.registerWithHandlers("oreMyuvil", oreMyuvil);
        ItemHelper.registerWithHandlers("orePitchblende", orePitchblende);
        ItemHelper.registerWithHandlers("oreNeirdermayrite", oreNierdermayrite);

        //Lame-man's Ores
        OreDictionary.registerOre("oreZinc", oreSphalerite);
        OreDictionary.registerOre("oreBismuth", oreBismuthinite);
        OreDictionary.registerOre("oreManganese", orePyrolusite);
        OreDictionary.registerOre("orePalladium", oreBraggite);
        OreDictionary.registerOre("oreMolybdenum", oreMolybdenite);
        OreDictionary.registerOre("oreNaturalCobalt", oreCobaltite);
        OreDictionary.registerOre("oreTungsten", oreWolframite);
        OreDictionary.registerOre("oreAluminium", oreBauxite);
        OreDictionary.registerOre("oreChromium", oreChromite);
        OreDictionary.registerOre("oreTitanium", oreIlmenite);

        //Ore Storage Blocks
        ItemHelper.registerWithHandlers("blockZinc", blockZinc);
        ItemHelper.registerWithHandlers("blockBismuth", blockBismuth);
        ItemHelper.registerWithHandlers("blockManganese", blockManganese);
        ItemHelper.registerWithHandlers("blockPalladium", blockPalladium);
        ItemHelper.registerWithHandlers("blockMolybdenum", blockMolybdenum);
        ItemHelper.registerWithHandlers("blockNaturalCobalt", blockCobalt);
        ItemHelper.registerWithHandlers("blockTungsten", blockTungsten);
        ItemHelper.registerWithHandlers("blockAluminium", blockAluminium);
        ItemHelper.registerWithHandlers("blockChromium", blockChromium);
        ItemHelper.registerWithHandlers("blockTitanium", blockTitanium);
        ItemHelper.registerWithHandlers("blockMagnetite", blockMagnetite);
        ItemHelper.registerWithHandlers("blockDioptase", blockDioptase);
        ItemHelper.registerWithHandlers("blockPyrope", blockPyrope);
        ItemHelper.registerWithHandlers("blockMyuvil", blockMyuvil);
        ItemHelper.registerWithHandlers("blockArsenic", blockArsenic);
        ItemHelper.registerWithHandlers("blockAntimony", blockAntimony);

        ItemHelper.registerWithHandlers("blockBrass", blockBrass);
        ItemHelper.registerWithHandlers("blockBismuthBronze", blockBismuthBronze);
        ItemHelper.registerWithHandlers("blockCupronickel", blockCupronickel);
        ItemHelper.registerWithHandlers("blockAluminiumBrass", blockAluminiumBrass);
        ItemHelper.registerWithHandlers("blockMithrilBronze", blockMithrilBronze);
        ItemHelper.registerWithHandlers("blockElectriplatinum", blockElectriplatinum);
        ItemHelper.registerWithHandlers("blockSteel", blockSteel);
        ItemHelper.registerWithHandlers("blockTungstenSteel", blockTungstenSteel);
        ItemHelper.registerWithHandlers("blockStainlessSteel", blockStainlessSteel);
        ItemHelper.registerWithHandlers("blockTechnomancy", blockTechnomancy);
        ItemHelper.registerWithHandlers("blockResonantTechnomancy", blockResonantTechnomancy);
        ItemHelper.registerWithHandlers("blockAmber", blockAmber);
        ItemHelper.registerWithHandlers("blockCrystalFlux", blockCrystalFlux);
        ItemHelper.registerWithHandlers("blockLapiquartz", blockLapiquartz);
        ItemHelper.registerWithHandlers("blockWhitePointStar", blockWhitePointStar);
        ItemHelper.registerWithHandlers("blockVoidInfernoStar", blockVoidInfernoStar);

        ItemHelper.registerWithHandlers("blockTungstenBlazing", blockTungstenBlazing);
        ItemHelper.registerWithHandlers("blockPlatinumGelid", blockPlatinumGelid);
        ItemHelper.registerWithHandlers("blockSilverLuminous", blockSilverLuminous);
        ItemHelper.registerWithHandlers("blockElectrumFlux", blockElectrumFlux);
        ItemHelper.registerWithHandlers("blockMolybdenumResonant", blockMolybdenumResonant);
        ItemHelper.registerWithHandlers("blockChromiumCarbide", blockChromiumCarbide);
        ItemHelper.registerWithHandlers("blockCarbonite", blockCarbonite);
        ItemHelper.registerWithHandlers("blockPyrum", blockPyrum);
        ItemHelper.registerWithHandlers("blockGelinium", blockGelinium);
        ItemHelper.registerWithHandlers("blockDullResolder", blockDullRedsolder);
        ItemHelper.registerWithHandlers("blockRedsolder", blockRedsolder);
        ItemHelper.registerWithHandlers("blockIridium", blockIridium);
        ItemHelper.registerWithHandlers("blockSulfur", blockSulfur);
        ItemHelper.registerWithHandlers("blockSaltpeter", blockSaltpeter);
        ItemHelper.registerWithHandlers("blockRust", blockRust);
        ItemHelper.registerWithHandlers("blockColdfireBismuthBronze", blockColdfireBismuthBronze);
    }

    //public void loadMachines() {}

    public void loadFluids() {
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

        //Buckets
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

        // Standard Ingots
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

        // Standard Nuggets
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
        partAssembledPCB = itemMaterial.addOreDictItem(68, "partAssembledPCB");
        partTransistor = itemMaterial.addOreDictItem(69, "partTransistor");
        partResistor = itemMaterial.addOreDictItem(70, "partResistor");
        //partSpring = itemMaterial.addOreDictItem(71, "partSpring");
        partFluxFilter = itemMaterial.addOreDictItem(72, "partFluxFilter");
        /*partIonThruster = itemMaterial.addOreDictItem(73, "partIonThruster");
        partResonantThruster = itemMaterial.addOreDictItem(74, "partResonantIonThruster");
        partMagnet = itemMaterial.addOreDictItem(75, "partMagnet");
        partAlCoNiMagnet = itemMaterial.addOreDictItem(76, "partAlCoNiMagnet");*/
        partServoMotor = itemMaterial.addOreDictItem(77, "partServoMotor");
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
        ingotBrass = itemMaterial.addOreDictItem(128, "ingotBrass");
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
        nuggetAluminiumBrass = itemMaterial.addOreDictItem(147, "nuggetAluminiumBrass");

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
        ItemHelper.addStorageRecipe(blockMithrilBronze, "ingotMithrilBronze");
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
        ItemHelper.addStorageRecipe(blockColdfireBismuthBronze, "ingotColdfireBismuthBronze");
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
        GameRegistry.addSmelting(oreNierdermayrite, ItemHelper.cloneStack(dustNierdermayrite, 4), 1.1F);

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
        //GameRegistry.addSmelting(blockRust, new ItemStack(Blocks.iron_block, 1, 0), 0.0F);
    }

    //public void addOreDict() {}

    //public void modIntegration() {}

    public void registerDispenserHandlers() {
        BlockDispenser.dispenseBehaviorRegistry.putObject(FluxGearContent.itemBucket, new DispenserFilledBucketHandler());
        BlockDispenser.dispenseBehaviorRegistry.putObject(Items.bucket, new DispenserEmptyBucketHandler());
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        return 0;
    }

    // Blocks
    public static BlockOre blockOre;
    public static BlockStorage blockStorage;
    public static BlockAlloy blockAlloy;
    public static BlockFluidicAlloy blockFluidicAlloy;

    //public static BlockTileEntity blockTileEntity;

    // Fluids
    public static Fluid fluidGhastTear;
    public static Fluid fluidLye;
    public static Fluid fluidAcid;
    public static Fluid fluidEtchingAcid;
    public static Fluid fluidSmog;
    public static Fluid fluidBlood;
    public static Fluid fluidGelidPyrotheum;

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








    // Ore Blocks
    public static ItemStack oreSphalerite;
    public static ItemStack oreBismuthinite;
    public static ItemStack orePyrolusite;
    public static ItemStack oreBraggite;
    public static ItemStack oreMolybdenite;
    public static ItemStack oreCobaltite;
    public static ItemStack oreWolframite;
    public static ItemStack oreBauxite;
    public static ItemStack oreChromite;
    public static ItemStack oreIlmenite;
    public static ItemStack oreMagnetite;
    public static ItemStack oreDioptase;
    public static ItemStack orePyrope;
    public static ItemStack oreMyuvil;
    public static ItemStack orePitchblende;
    public static ItemStack oreNierdermayrite;

    // Ore Storage Blocks
    public static ItemStack blockZinc;
    public static ItemStack blockBismuth;
    public static ItemStack blockManganese;
    public static ItemStack blockPalladium;
    public static ItemStack blockMolybdenum;
    public static ItemStack blockCobalt;
    public static ItemStack blockTungsten;
    public static ItemStack blockAluminium;
    public static ItemStack blockChromium;
    public static ItemStack blockTitanium;
    public static ItemStack blockMagnetite;
    public static ItemStack blockDioptase;
    public static ItemStack blockPyrope;
    public static ItemStack blockMyuvil;
    public static ItemStack blockArsenic;
    public static ItemStack blockAntimony;

    // Alloy Storage Blocks
    public static ItemStack blockBrass;
    public static ItemStack blockBismuthBronze;
    public static ItemStack blockCupronickel;
    public static ItemStack blockAluminiumBrass;
    public static ItemStack blockMithrilBronze;
    public static ItemStack blockElectriplatinum;
    public static ItemStack blockSteel;
    public static ItemStack blockTungstenSteel;
    public static ItemStack blockStainlessSteel;
    public static ItemStack blockTechnomancy;
    public static ItemStack blockResonantTechnomancy;
    public static ItemStack blockAmber;
    public static ItemStack blockCrystalFlux;
    public static ItemStack blockLapiquartz;
    public static ItemStack blockWhitePointStar;
    public static ItemStack blockVoidInfernoStar;

    // Fluidic Alloy Storage Blocks
    public static ItemStack blockTungstenBlazing;
    public static ItemStack blockPlatinumGelid;
    public static ItemStack blockSilverLuminous;
    public static ItemStack blockElectrumFlux;
    public static ItemStack blockMolybdenumResonant;
    public static ItemStack blockChromiumCarbide;
    public static ItemStack blockCarbonite;
    public static ItemStack blockPyrum;
    public static ItemStack blockGelinium;
    public static ItemStack blockDullRedsolder;
    public static ItemStack blockRedsolder;
    public static ItemStack blockIridium;
    public static ItemStack blockSulfur;
    public static ItemStack blockSaltpeter;
    public static ItemStack blockRust;
    public static ItemStack blockColdfireBismuthBronze;


    //Buckets
    public static ItemStack bucketGhastTears;
    public static ItemStack bucketLye;
    public static ItemStack bucketAcid;
    public static ItemStack bucketEtchingAcid;
    public static ItemStack bucketSmog;
    public static ItemStack bucketBlood;
    public static ItemStack bucketGelidPyrotheum;



    // Standard Ingots
    public static ItemStack ingotZinc;
    public static ItemStack ingotBismuth;
    public static ItemStack ingotManganese;
    public static ItemStack ingotPalladium;
    public static ItemStack ingotMolybdenum;
    public static ItemStack ingotCobalt;
    public static ItemStack ingotTungsten;
    public static ItemStack ingotAluminium;
    public static ItemStack ingotChromium;
    public static ItemStack ingotTitanium;
    public static ItemStack ingotIridium;

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
    public static ItemStack ingotIronMagnet;
    public static ItemStack ingotManganeseMagnet;
    public static ItemStack ingotCobaltMagnet;
    public static ItemStack ingotNickelMagnet;
    public static ItemStack ingotSteelMagnet;
    public static ItemStack ingotInvarMagnet;

    // Magnetized Nuggets
    public static ItemStack nuggetIronMagnet;
    public static ItemStack nuggetManganeseMagnet;
    public static ItemStack nuggetCobaltMagnet;
    public static ItemStack nuggetNickelMagnet;
    public static ItemStack nuggetSteelMagnet;
    public static ItemStack nuggetInvarMagnet;

    // Simple Alloy Ingots
    public static ItemStack ingotBrass;
    public static ItemStack ingotBismuthBronze;
    public static ItemStack ingotCupronickel;
    public static ItemStack ingotAluminiumBrass;

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
    public static ItemStack ingotMithrilBronze;
    public static ItemStack ingotEletriplatinum;
    public static ItemStack ingotSteel;
    public static ItemStack ingotTungstenSteel;
    public static ItemStack ingotSteelStainless;
    public static ItemStack ingotTechnomancy;
    public static ItemStack ingotTechnomancyResonant;

    // Complex Alloy Dusts
    public static ItemStack dustMithrilBronze;
    public static ItemStack dustEletriplatinum;
    public static ItemStack dustSteel;
    public static ItemStack dustTungstenSteel;
    public static ItemStack dustSteelStainless;
    public static ItemStack dustTechnomancy;
    public static ItemStack dustTechnomancyResonant;
    public static ItemStack dustPitchblende;
    public static ItemStack dustNierdermayrite;
    public static ItemStack dustRust;
    public static ItemStack dustAshes;

    // Complex Alloy Nuggets
    public static ItemStack nuggetMithrilBronze;
    public static ItemStack nuggetEletriplatinum;
    public static ItemStack nuggetSteel;
    public static ItemStack nuggetTungstenSteel;
    public static ItemStack nuggetSteelStainless;
    public static ItemStack nuggetTechnomancy;
    public static ItemStack nuggetTechnomancyResonant;

    // Fluid Alloy Ingots
    public static ItemStack ingotTungstenBlazing;
    public static ItemStack ingotPlatinumGelid;
    public static ItemStack ingotSilverLuminous;
    public static ItemStack ingotElectrumFlux;
    public static ItemStack ingotMolybdenumResonant;
    public static ItemStack ingotChromiumCarbide;
    public static ItemStack ingotColdfireBismuthBronze;

    // Fluid Alloy Dusts
    public static ItemStack dustTungstenBlazing;
    public static ItemStack dustPlatinumGelid;
    public static ItemStack dustSilverLuminous;
    public static ItemStack dustElectrumFlux;
    public static ItemStack dustMolybdenumResonant;
    public static ItemStack dustChromiumCarbide;
    public static ItemStack dustColdfireBismuthBronze;

    // Fluid Alloy Nuggets
    public static ItemStack nuggetTungstenBlazing;
    public static ItemStack nuggetPlatinumGelid;
    public static ItemStack nuggetSilverLuminous;
    public static ItemStack nuggetElectrumFlux;
    public static ItemStack nuggetMolybdenumResonant;
    public static ItemStack nuggetChromiumCarbide;
    public static ItemStack nuggetColdfireBismuthBronze;

    // Fluid-Doped Alloy Ingots
    public static ItemStack ingotCarbonite;
    public static ItemStack ingotPyrum;
    public static ItemStack ingotGelinium;

    // Fluid-Doped Alloy Dusts
    public static ItemStack dustCarbonite;
    public static ItemStack dustPyrum;
    public static ItemStack dustGelinium;

    // Fluid-Doped Alloy Nuggets
    public static ItemStack nuggetCarbonite;
    public static ItemStack nuggetPyrum;
    public static ItemStack nuggetGelinium;

    // White Point Stars and Void Inferno Stars

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
    public static ItemStack partRotaryOrb;
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
}