package mortvana.projectfluxgear.common;

import cofh.core.fluid.BlockFluidCoFHBase;
import cofh.core.item.ItemBucket;
import cofh.core.util.fluid.DispenserEmptyBucketHandler;
import cofh.core.util.fluid.DispenserFilledBucketHandler;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import mortvana.projectfluxgear.block.BlockAlloy;
import mortvana.projectfluxgear.block.BlockOre;
import mortvana.projectfluxgear.block.BlockStorage;
import mortvana.fluxgearcore.item.ItemBase;
import mortvana.fluxgearcore.util.helper.ItemHelper;

import mortvana.projectfluxgear.item.tool.ItemProtoSonicWrench;

public class FluxGearContent implements IFuelHandler{

	public void preInit() {
        loadBlocks();
        loadMachines();
        loadFluids();
        loadItems();
        loadTools();
	    //loadEnchants();
		loadTiles();
        //loadAugments();
    }

    public void init() {
        //initBlocks();
        //initMachines();
	    initItems();
        //initTools();
        //initAugments();
        addOreDict();
	    craftingRecipes();
	    furnaceRecipes();
    }

    public void postInit() {
        aluminiumArc();
        modIntegration();
        //postInitBlocks();
        blockStorage.postInit();
        blockAlloy.postInit();
        //blockFluidicAlloy.postInit();
    }

    public void loadBlocks() {
        blockOre = new BlockOre();
        blockStorage = new BlockStorage();
        blockAlloy = new BlockAlloy();
        //blockFluidicAlloy = new BlockFluidicAlloy();

        //blockTileEntity = new BlockTileEntity();

        //blockFluidGhastTear = new BlockFluidGhastTear();

        blockOre.preInit();
        blockStorage.preInit();
	    blockAlloy.preInit();

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

    }

    public void loadMachines() {

    }

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
        //itemBucket = (ItemBucket) new ItemBucket("thermaltinkerer").setUnlocalizedName("bucket").setCreativeTab(ThermalTinkerer.tab);
        itemMaterial = (ItemBase) new ItemBase("thermaltinkerer").setUnlocalizedName("material").setCreativeTab(ProjectFluxGear.tab);
        //itemFood =

        //Buckets
        /*bucketGhastTears = itemBucket.addItem(0, "bucketGhastTears", 1);
        bucketEtchingAcid = itemBucket.addItem(1, "bucketEtchingAcid", 0);
        bucketBlood = itemBucket.addItem(2, "bucketBlood", 1);
        bucketAcid = itemBucket.addItem(3, "bucketAcid", 0);
        bucketSmog = itemBucket.addItem(4, "bucketSmog", 0);*/

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

        //dust = itemMaterial.addOreDictItem(, "dust");
    }

    public void loadTools() {
	    itemProtoSonicWrench = (ItemProtoSonicWrench) new ItemProtoSonicWrench().setUnlocalizedName("tool", "prototypeSonicWrench");
    }

    public void loadEnchants() {

    }

    public void loadTiles() {}

    public void aluminiumArc() {
        OreDictionary.registerOre("ingotAluminum", ingotAluminium);
        OreDictionary.registerOre("dustAluminum", dustAluminium);
        OreDictionary.registerOre("nuggetAluminum", nuggetAluminium);
        OreDictionary.registerOre("ingotAluminumBrass", ingotAluminiumBrass);
        OreDictionary.registerOre("dustAluminumBrass", dustAluminiumBrass);
        OreDictionary.registerOre("nuggetAluminumBrass", nuggetAluminiumBrass);

        OreDictionary.registerOre("oreAluminum", oreBauxite);

        if (FluxGearConfig.cobaltAssimilation) {
            OreDictionary.registerOre("ingotCobalt", ingotCobalt);
            OreDictionary.registerOre("dustCobalt", dustCobalt);
            OreDictionary.registerOre("nuggetCobalt", nuggetCobalt);
            OreDictionary.registerOre("oreCobalt", oreCobaltite);
        }
    }

	public void initItems() {
		toolProtoSonicWrench = itemProtoSonicWrench.addItem(0, "protoSonicWrench");
	}

    public void craftingRecipes() {
        ItemHelper.addStorageRecipe(ingotZinc, "nuggetZinc");
        ItemHelper.addStorageRecipe(ingotBismuth, "nuggetBismuth");
        ItemHelper.addStorageRecipe(ingotManganese, "nuggetManganese");
        ItemHelper.addStorageRecipe(ingotPalladium, "nuggetPalladium");
        ItemHelper.addStorageRecipe(ingotMolybdenum, "nuggetMolybdenum");
        ItemHelper.addStorageRecipe(ingotCobalt, "nuggetNaturalCobalt");
        ItemHelper.addStorageRecipe(ingotTungsten, "nuggetTungsten");
        ItemHelper.addStorageRecipe(ingotAluminium, "nuggetAluminium");
        ItemHelper.addStorageRecipe(ingotAluminium, "nuggetAluminum"); //We Americans are so naive
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
        ItemHelper.addReverseStorageRecipe(ingotAluminium, "blockAluminum"); //We Americans are so naive
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
        ItemHelper.addReverseStorageRecipe(nuggetAluminium, "ingotAluminum"); //We Americans are so naive
        ItemHelper.addReverseStorageRecipe(nuggetChromium, "ingotChromium");
        ItemHelper.addReverseStorageRecipe(nuggetTitanium, "ingotTitanium");
        ItemHelper.addReverseStorageRecipe(nuggetIridium, "ingotIridium");
        ItemHelper.addReverseStorageRecipe(nuggetMagnetite, "ingotMagnetite");

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
        //No Wolframite
        // Temporary Aluminium Processing
        GameRegistry.addSmelting(oreBauxite, ingotAluminium, 0.9F);
        GameRegistry.addSmelting(oreChromite, ingotChromium, 1.2F);
        GameRegistry.addSmelting(oreIlmenite, ingotTitanium, 1.2F);
        // For when you Silk Touch the ores
        GameRegistry.addSmelting(oreMagnetite, gemMagnetite, 1.0F);
        GameRegistry.addSmelting(oreDioptase, gemDioptase, 1.0F);
        GameRegistry.addSmelting(orePyrope, gemPyrope, 1.0F);
        GameRegistry.addSmelting(oreMyuvil, dustMyuvil, 1.0F);

        // Dust to Ingot
        GameRegistry.addSmelting(dustZinc, ingotZinc, 0.0F);
        GameRegistry.addSmelting(dustBismuth, ingotBismuth, 0.0F);
        GameRegistry.addSmelting(dustManganese, ingotManganese, 0.0F);
        GameRegistry.addSmelting(dustPalladium, ingotPalladium, 0.0F);
        GameRegistry.addSmelting(dustMolybdenum, ingotMolybdenum, 0.0F);
        GameRegistry.addSmelting(dustCobalt, ingotCobalt, 0.0F);
        // No Tungsten
        GameRegistry.addSmelting(dustAluminium, ingotAluminium, 0.0F);
        GameRegistry.addSmelting(dustChromium, ingotChromium, 0.0F);
        // No Titanium
        // No Iridium
    }

    public void addOreDict() {}

    public void modIntegration() {}

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
    /*public static BlockFluidicAlloy blockFluidicAlloy;

    public static BlockTileEntity blockTileEntity;*/

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

    //Ore Blocks
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

    // Base Items
    public static ItemBucket itemBucket;
    public static ItemBase itemMaterial;

	public static ItemProtoSonicWrench itemProtoSonicWrench;
	public static ItemStack toolProtoSonicWrench;

    //Buckets
    public static ItemStack bucketGhastTears;
    public static ItemStack bucketEtchingAcid;
    public static ItemStack bucketBlood;
    public static ItemStack bucketAcid;
    public static ItemStack bucketSmog;


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
    public static ItemStack ingotBismuthBronzeGelid;
    public static ItemStack ingotSilverLuminous;
    public static ItemStack ingotElectrumFlux;
    public static ItemStack ingotMolybdenumResonant;
    public static ItemStack ingotChromiumCarbide;

    // Fluid Alloy Dusts
    public static ItemStack dustTungstenBlazing;
    public static ItemStack dustBismuthBronzeGelid;
    public static ItemStack dustSilverLuminous;
    public static ItemStack dustElectrumFlux;
    public static ItemStack dustMolybdenumResonant;
    public static ItemStack dustChromiumCarbide;

    // Fluid Alloy Nuggets
    public static ItemStack nuggetTungstenBlazing;
    public static ItemStack nuggetBismuthBronzeGelid;
    public static ItemStack nuggetSilverLuminous;
    public static ItemStack nuggetElectrumFlux;
    public static ItemStack nuggetMolybdenumResonant;
    public static ItemStack nuggetChromiumCarbide;

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

    public static Item itemQuantumCapacitor;

    /*public static ItemArmorRF itemHelmetInvarRF;
    public static ItemArmorRF itemChestInvarRF;
    public static ItemArmorRF itemLegsInvarRF;
    public static ItemArmorRF itemBootsInvarRF;*/
}