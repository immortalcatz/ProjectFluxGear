package mortvana.legacy.common;

import java.io.File;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import mantle.lib.TabTools;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.LanguageRegistry;

import mortvana.legacy.block.BlockBasicOre;
import mortvana.legacy.block.BlockComplexOre;
import mortvana.legacy.block.BlockGemOre;
import mortvana.legacy.block.BlockHandler;
import mortvana.legacy.block.itemblock.ItemBlockBasicOre;
import mortvana.legacy.block.itemblock.ItemBlockComplexOre;
import mortvana.legacy.block.itemblock.ItemBlockGemOre;
import mortvana.legacy.common.config.MTConfig;
import mortvana.legacy.util.runtime.EnvironmentChecks;
import mortvana.legacy.util.Modules;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Informs forge that this is a base mod class, and gives it some info for the
//FML mod list. This is also where it looks to see if your client's version
//matches the server's.
@Mod(modid = MortTech.modid, name = "MortTech", version = "0.0.0.1", dependencies = "required-after:Forge@[9.11,); required-after:Mantle; after:ForgeMutlipart")

//Informs forge of the requirements:
//
//clientSideRequired means players can't connect without it. True for things
//that add new blocks/items, false for things like bukkit plugins.
//
//serverSideRequired means clients can't connect to servers that don't have it.
//This isn't a strict restriction currently but it can cause problems if the
//mod does anything potentially incompatible in its preInit function. True for
//things that add new blocks/items, false for things like Rei's Minimap or
//Inventory Tweaks.
//@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class MortTech {
	public static final String modid = "MortTech";
	
	public static BlockBasicOre BasicOre;
	public static BlockGemOre GemOre;
	public static BlockComplexOre ComplexOre;
	
    // The instance of your mod that Forge uses.
    // The instance of the mod that Forge will access. Note that it has to be
//set by hand in the preInit step.
    @Instance("MortTech")
    public static MortTech INSTANCE;

	//Tells Forge what classes to load for the client and server proxies. These
//execute side-specific code like registering renderers (for the client) or
//different tick handlers (for the server).
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="mortvana.legacy.client.ClientProxy", serverSide="OLD.morttech.morttech.common.MTServerProxy")
    public static CommonProxy proxy;

	//Shared mod Logger
	public static final Logger logger = LogManager.getLogger("MortTech");
	public static void log(Level level, String message) {
		FMLLog.getLogger().log(level, "[MortTech] " + message);
	}

	public MortTech(){
		EnvironmentChecks.verifyEnvironmentSanity();
	}


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        INSTANCE = this;
        File Config = new File(event.getModConfigurationDirectory() + "/MortTech/MortTech.cfg");
        MTConfig.init(new Configuration(Config));
        MTConfig.setConfigFolderBase(event.getModConfigurationDirectory());
		BlockHandler.registerBlocks(new GameRegistry());
		BlockHandler.setNames(new LanguageRegistry());

	    BasicOre = new BlockBasicOre(3000, Material.rock).setUnlocalizedName("BasicOre");
	    GemOre = new BlockBasicOre(3001, Material.rock).setUnlocalizedName("GemOre");
	    ComplexOre = new BlockBasicOre(3002, Material.rock).setUnlocalizedName("ComplexOre");

	    GameRegistry.registerBlock(BasicOre, ItemBasicOreBlock.class, modid + BasicOre.getUnlocalizedName().substring(5));
	    GameRegistry.registerBlock(GemOre, ItemGemOreBlock.class, modid + GemOre.getUnlocalizedName().substring(5));
	    GameRegistry.registerBlock(ComplexOre, ItemComplexOreBlock.class, modid + ComplexOre.getUnlocalizedName().substring(5));


	    NetworkRegistry.instance().registerGuiHandler(INSTANCE, new GuiHandler());
	    proxy.registerRenderers();

	    content = new MTContent();
	    MTContent.preInit();
	    content.addOredictSupport();
	    content.postIntermodCommunication();


	    root = event.getModConfigurationDirectory();
	    Config.setup(root + "/MortTech/");
	    for(Modules.Module module: Modules.modules) {
	        module.preInit();
	    }
	    MTContent.componentsTab = new TabTools("MTComponents");
	    MTContent.toolsTab = new TabTools("MTTools");
	    MTContent.machineTab = new TabTools("MTMachines");

	    MTContent.preInit();
    }

    public static MTConfig config;


	public void load(FMLInitializationEvent event) {
		
		BasicOre = new BlockBasicOre();	
		GemOre = new BlockGemOre();
		ComplexOre = new BlockComplexOre();
		
		proxy.registerRenderers();
		
		GameRegistry.registerBlock(BasicOre, ItemBlockBasicOre.class, "BasicOre");
		GameRegistry.registerBlock(GemOre, ItemBlockGemOre.class, "GemOre");
		GameRegistry.registerBlock(ComplexOre, ItemBlockComplexOre.class, "ComplexOre");
		
		MinecraftForge.setBlockHarvestLevel(BasicOre, 0, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BasicOre, 1, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BasicOre, 2, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BasicOre, 3, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BasicOre, 4, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BasicOre, 5, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BasicOre, 6, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(BasicOre, 7, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BasicOre, 8, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(BasicOre, 9, "pickaxe", 2);
		
		MinecraftForge.setBlockHarvestLevel(GemOre, 0, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(GemOre, 1, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(GemOre, 2, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(GemOre, 3, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(GemOre, 4, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(GemOre, 5, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(GemOre, 6, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(GemOre, 7, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(GemOre, 8, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(GemOre, 9, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(GemOre, 10, "pickaxe", 2);
		
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 0, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 1, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 2, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 3, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 4, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 5, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 6, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 7, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 8, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 9, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 10, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 11, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 12, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 13, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 14, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ComplexOre, 15, "pickaxe", 2);
		
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 0), "Chalcopyrite Ore"); //ADD CHALCOCITE
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 1), "Cassiterite Ore");
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 2), "Argentite Ore"); //ACTUALLY ACANTHITE
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 3), "Galena Ore");
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 4), "Sphalerite Ore");
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 5), "Bismuthinite Ore");
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 6), "Garnierite Ore"); //ADD MILLERITE
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 7), "Chromite Ore");
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 8), "Cobaltite Ore");
		LanguageRegistry.addName(new ItemStack(BasicOre, 1, 9), "Wolframite Ore");
		
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 0), "Dioptase Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 1), "Ruby Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 2), "Sapphire Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 3), "Green Sapphire Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 4), "Pink Sapphire Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 5), "Purple Sapphire Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 6), "Topaz Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 7), "Tanzanite Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 8), "Pyrope Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 9), "Malachite Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 10), "Uranite Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 11), "Olivine Ore");
		LanguageRegistry.addName(new ItemStack(GemOre, 1, 12), "Soarynium Ore");
		
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 0), "Bauxite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 1), "Monazite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 2), "Chalcocite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 3), "Millerite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 4), "Bornite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 5), "Limonite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 6), "Magnetite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 7), "Hematite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 8), "Pyrolusite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 9), "Molybdenite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 10), "Cooprite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 11), "Ilmenite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 12), "Tetrahedrite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 13), "Tennatite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 14), "Pentalandite Ore");
		LanguageRegistry.addName(new ItemStack(ComplexOre, 1, 15), "Nierdermayrite Ore");
		
		//GameRegistry.registerWorldGenerator(EventManager);
		 

		}
	 }
