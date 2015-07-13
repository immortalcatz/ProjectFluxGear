package oldcode.morttech;

import cpw.mods.fml.common.registry.LanguageRegistry;
import mantle.lib.TabTools;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


public class MTContent /*implements IFuelHandler*/{

	public void preInit ()
    {
		//Unlocalized Names
		
		//Class Registry
		basicOre = new BlockBasicOre().setBlockName("BasicOre");
		gemOre = new BlockGemOre();
		complexOre = new BlockComplexOre();
		
		GameRegistry.registerBlock(basicOre, ItemBlockBasicOre.class, "BasicOre");
		GameRegistry.registerBlock(gemOre, ItemBlockGemOre.class, "GemOre");
		GameRegistry.registerBlock(complexOre, ItemBlockComplexOre.class, "ComplexOre");
		// GameRegistry.addRecipe(new ItemStack(snakeBag, 1, 0), "sss", "sss", "sss", 's', snake);

		//Harvest Levels

		
		//Ore Dictionary
		
		//Registry Thing
	    tileMachineTier0 = new BlockWoodmill().setResistance(5.0f).setCreativeTab(MTCreativeTab.tab).setHardness(2.3f).setBlockName("Woodmill");
	    GameRegistry.registerBlock(tileMachineTier0, "Tier0Machine");
	    tileCrank = new BlockCrank().setResistance(5.0f).setHardness(2.3f).setCreativeTab(MTCreativeTab.tab).setBlockName("Crank");
	    GameRegistry.registerBlock(tileCrank, "Crank");
	    itemDust = new ItemDust().setCreativeTab(MTCreativeTab.tab).setUnlocalizedName("dust");
	    GameRegistry.registerItem(itemDust, "Dust");
	    wrenchSonic = new WrenchSonic().setCreativeTab(MTCreativeTab.tab).setUnlocalizedName("wrenchSonic");
	    GameRegistry.registerItem(wrenchSonic, "Sonic Wrench");

	    LanguageRegistry.addName(tileMachineTier0, "Woodmill");
	    LanguageRegistry.addName(itemDust, "Sawdust");
	    LanguageRegistry.addName(tileCrank, "Crank");
	    LanguageRegistry.addName(wrenchSonic, "Sonic Wrench");

	    OreDictionary.registerOre("dustSawdust", itemDust);

	    GameRegistry.registerTileEntity(TileWoodmill.class, tileWoodmillID);

	    tileMachine = new BlockMachine(Material.iron).setResistance(5.0f).setCreativeTab(machineTab).setHardness(2.5f);
	    GameRegistry.registerBlock(tileMachine, "machine");

	    tileCrank = new BlockCrank(Material.wood).setResistance(5.0f).setCreativeTab(machineTab).setHardness(2.0f);
	    GameRegistry.registerBlock(tileCrank, "tempTE");

	    itemDust = new ItemDust().setCreativeTab(componentsTab).setUnlocalizedName("dust");
	    GameRegistry.registerItem(itemDust, "dust");

	    wrenchSonic = new WrenchSonic().setCreativeTab(toolsTab).setUnlocalizedName("sonicWrench");
	    GameRegistry.registerItem(wrenchSonic, "sonicWrench");

	    debugSpork = new DebuggingSpork().setCreativeTab(toolsTab).setUnlocalizedName("debuggingSpork");
	    GameRegistry.registerItem(debugSpork, "debuggingSpork");

	    itemCrafting = new ItemCrafting().setCreativeTab(componentsTab).setUnlocalizedName("crafting");
	    GameRegistry.registerItem(itemCrafting, "crafting");

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


        /*
        //Tier 1
        GameRegistry.registerCustomItemStack("machineAlloyFurnace", new ItemStack(tileMachine, 1, 0));
        GameRegistry.registerCustomItemStack("machineGrinder", new ItemStack(tileMachine, 1, 1));

        //Tier 2
        GameRegistry.registerCustomItemStack("machineCrucible", new ItemStack(tileMachine, 1, 2));
        GameRegistry.registerCustomItemStack("machineCasting", new ItemStack(tileMachine, 1, 3));*/
	    GameRegistry.registerCustomItemStack("machineWoodmill", new ItemStack(tileMachine, 1, 4));/*
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

        //TODO
        //Refactor commented areas for Tier changes
        GameRegistry.registerCustomItemStack("machineWireCut", new ItemStack(tileMachine, 1, 8));
        GameRegistry.registerCustomItemStack("machineAssemblyBase", new ItemStack(tileMachine, 1, 10));
        GameRegistry.registerCustomItemStack("machineAssembler", new ItemStack(tileMachine, 1, 11));
        GameRegistry.registerCustomItemStack("machineFurnace", new ItemStack(tileMachine, 1, 17));
        GameRegistry.registerCustomItemStack("machineRGrinder", new ItemStack(tileMachine, 1, 18));
        */

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
	public static Item itemCrafting;
	public static Item wrenchSonic;
	public static Item debugSpork;

}