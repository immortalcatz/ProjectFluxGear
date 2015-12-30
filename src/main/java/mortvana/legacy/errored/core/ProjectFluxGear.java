package mortvana.legacy.errored.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import mortvana.legacy.dependent.seconddegree.projectfluxgear.world.FluxGearWorldGenerator;
import mortvana.legacy.dependent.seconddegree.projectfluxgear.world.GravelOreGenEventHandler;
import mortvana.legacy.dependent.seconddegree.projectfluxgear.world.PoorOreGenerator;
import mortvana.legacy.dependent.thirddegree.fluxgearaddons.network.ParticleGenPacketHandler;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ChunkDataEvent;

import cofh.core.CoFHProps;
import cofh.core.util.CoreUtils;
import cofh.core.world.WorldHandler;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.genetics.AlleleManager;
import mortvana.legacy.clean.apiology.util.genetics.BeeSpecies;
import mortvana.legacy.clean.fluxgeartweaks.block.tileentity.TileTimeyWimey;
import mortvana.legacy.clean.core.client.gui.FluxGearGUIHandler;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.clean.apiology.common.VersionInfo;
import mortvana.legacy.dependent.seconddegree.fluxgearaddons.network.ObjectPacket;
import mortvana.legacy.dependent.seconddegree.fluxgearaddons.network.ObjectPacketHandler;
import mortvana.legacy.clean.fluxgearaddons.network.ParticleGenPacket;
import mortvana.legacy.clean.core.common.CommonProxy;
import mortvana.legacy.clean.core.common.FluxGearConfig;
import mortvana.legacy.clean.core.common.FluxGearConfigWorld;
import mortvana.legacy.clean.weirdscience.util.ContentRegistry;
import mortvana.legacy.clean.apiology.common.LocalizationManager;
import mortvana.legacy.clean.morttech.util.Modules;
import mortvana.melteddashboard.intermod.tinkers.TinkersHelper;
import mortvana.melteddashboard.util.helpers.LoadedHelper;
import mortvana.melteddashboard.util.helpers.StringHelper;

import mortvana.projectfluxgear.core.common.FluxGearAchievements;
import mortvana.projectfluxgear.thaumic.world.ExubituraGenerator;

import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.Detailing;
import tconstruct.library.tools.ToolCore;
//Informs forge that this is a base mod class, and gives it some info for the
//FML mod list. This is also where it looks to see if your client's version
//matches the server's.
@Mod(modid = ProjectFluxGear.modID, name = "MortTech", version = "0.0.0.1", dependencies = "required-after:Forge@[9.11,); required-after:Mantle; after:ForgeMutlipart", acceptedMinecraftVersions = VersionInfo.MCVersion)
//
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
public class ProjectFluxGear {
    //public static final PacketPipeline packetPipeline = new PacketPipeline(); //TODO

    public static final boolean debugWorldGen = true;

	public static final String modID = "MortTech";

	// The instance of your mod that Forge uses.
	// The instance of the mod that Forge will access. Note that it has to be
	//set by hand in the preInit step.
    @Instance(modID)
    public static ProjectFluxGear instance;
	public static Random random = new Random();

	public static final String[] dyeTypes = new String[] { "White", "Orange", "Magenta", "LightBlue", "Yellow", "Lime", "Pink", "Gray", "LightGray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
	public static final String[] colorNames = new String[] { "white", "orange", "magenta", "lightblue", "yellow", "lime", "pink", "gray", "silver", "aqua", "purple", "blue", "brown", "green", "red", "black" };

	//Tells Forge what classes to load for the client and server proxies. These
	//execute side-specific code like registering renderers (for the client) or
	//different tick handlers (for the server).
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "mortvana.projectfluxgear.core.client.ClientProxy", serverSide = "mortvana.projectfluxgear.core.common.CommonProxy")
    public static CommonProxy proxy;
	public SimpleNetworkWrapper wrapper;

	//Shared mod Logger
	//public static final Logger logger = LogManager.getLogger("MortTech");
	//public static void log(Level level, String message) {
	//	FMLLog.getLogger().log(level, "[MortTech] " + message);
	//}

	public ProjectFluxGear() {
	    EnvironmentChecks.verifyEnvironmentSanity();
    }

	public String[] blacklistedBlocks;
	public String[] blacklistedTiles;

    public static final FluxGearGUIHandler guiHandler = new FluxGearGUIHandler();

	public static final String networkChannelName = "FluxGearAddons";
	public static SimpleNetworkWrapper network;

    // Doctor Octoartifact, BLAAHHHH...
    public static ArrayList<String> sounds;// = CongealedBloodBlock.sounds;
    public static ContentRegistry weirdRegistry;

	public static FluxGearConfig config;


    /** Initialization Sequence */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
	    FluxGearConfig.setup(event.getModConfigurationDirectory() + "/MortTech/");
	    FluxGearConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear.cfg"));
	    FluxGearConfigWorld.loadConfiguration(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-World.cfg"));

	    NetworkRegistry.INSTANCE.registerGuiHandler(instance, new FluxGearGUIHandler());
	    proxy.registerRenderers();

	    for(Modules.Module module: Modules.modules) {
		    module.preInit();
	    }

	    FluxGearContent.preInit();

        MinecraftForge.EVENT_BUS.register(this);
	    // Hey, listen. Hey, listen. Hey-- ARTIFACT DETECTED, EeEEXXxTtTEEeRRrMmMmIiIiNnNAAaA*boom*
	    MinecraftForge.EVENT_BUS.register(weirdRegistry.bucketMan);
	    MinecraftForge.EVENT_BUS.register(new FluxGearEvents());

	    network = NetworkRegistry.INSTANCE.newSimpleChannel("FluxGearAddons");
	    network.registerMessage(ParticleGenPacketHandler.class, ParticleGenPacket.class, 1, Side.SERVER);
	    network.registerMessage(ObjectPacketHandler.class, ObjectPacket.class, 6, Side.CLIENT);
	    //thing = new Thing();

	    FluxGearCompat.preInitCompat();
	    FluxGearCompat.preInitIMC();

        GameRegistry.registerWorldGenerator(new FluxGearWorldGenerator(), 1);
	    GameRegistry.registerWorldGenerator(new ExubituraGenerator(), 1);

        if (FluxGearConfig.achievementsEnabled) {
	        FluxGearAchievements.addAchievements();
        }

	    // Compatibility Helpers
	    //ForestryHelper.preInit();
	    //BigReactorsHelper();

	    LocalizationManager.setupLocalizationInfo();

	    FluxGearContent.oreRegistry();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.registerRenderers();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        FluxGearContent.init();
        GameRegistry.registerFuelHandler(content);

        packetPipeline.initalize();
        MinecraftForge.EVENT_BUS.register(WorldHandler.instance);

        if (retrogen) {
            FMLCommonHandler.instance().bus().register(new WorldTickHandler());
        }

        loadWorldGeneration();

        /** Register Handlers */
        MinecraftForge.EVENT_BUS.register(proxy);
        PoorOreGenerator.registerOres();
        MinecraftForge.TERRAIN_GEN_BUS.register(new GravelOreGenEventHandler());

	    //TODO: mDerpohouse!!!!!!!!!!!!!!
		if (LoadedHelper.isTinkersLoaded) {
			ToolCore chisel = (ToolCore) GameRegistry.findItem("TConstruct", "chisel");
			Detailing chiseling = TConstructRegistry.getChiselDetailing();

			for (int i = 0; i < 16; ++i) {
				chiseling.addDetailing(FluxGearContent.coloredStone, i, FluxGearContent.coloredStoneBrick, i, chisel);
				chiseling.addDetailing(FluxGearContent.coloredStoneBrick, i, FluxGearContent.coloredStoneRoad, i, chisel);
				chiseling.addDetailing(FluxGearContent.coloredStoneRoad, i, FluxGearContent.coloredStoneFancyBrick, i, chisel);
				chiseling.addDetailing(FluxGearContent.coloredStoneFancyBrick, i, FluxGearContent.coloredStoneSquareBrick, i, chisel);
			}
		}

	    proxy.registerRenderers();

	    //GameRegistry.registerWorldGenerator(EventManager);

	    // Compatibility Helpers
	    //ForestryHelper.init();
	    //BigReactorsHelper.init();
    }

    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {
	    TinkersHelper.addMaterialRenderMappings(event, FluxGearConfig.tinkersID_Plasteel, FluxGearConfig.tinkersID_ManaMithral, FluxGearConfig.tinkersID_Enderium, FluxGearConfig.tinkersID_Lumium, FluxGearConfig.tinkersID_Signalum);

	    blacklistedBlocks = FluxGearConfig.blacklistedBlocks;
	    blacklistedTiles = FluxGearConfig.blacklistedTiles;

	    for (String block : blacklistedBlocks)
		    blacklistBlock(block);
	    for (String tile : blacklistedTiles)
		    blacklistTile(tile);

        FluxGearContent.postInit();
	    ModRecipes.init();
	    ModResearch.init();

        proxy.registerEntities();

        packetPipeline.postInitialize();


	    proxy.registerRenderer();
	    BeeManager.initializeBees();
	    VersionInfo.doVersionCheck();
	    // get bee root
	    BeeSpecies.beeRoot = (IBeeRoot) AlleleManager.alleleRegistry.getSpeciesRoot("rootBees");

	    // init bee branches
	    //branchTechnostatic = new BranchBees();
	    //AlleleManager.alleleRegistry.getClassification("family.apidae").addMemberGroup(branchTechnostatic);
    }

	@EventHandler
	public void loadComplete(FMLLoadCompleteEvent event) {

	}

    @EventHandler
    public void remapEvent (FMLMissingMappingsEvent event) {
	    //Dummy
        //for (FMLMissingMappingsEvent.MissingMapping mapping : event.get()) {
            /*if (mapping.name.equals("Thingy:ItemOrBlock")) {
                mapping.remap(LoadingClass.blockOrItem.item/block());
            }*/
        //}
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        content.registerDispenserHandlers();
    }

	public void blacklistBlock(String string) {
		String[] parts = string.split(":");
		if (parts.length != 2) {
			logger.error("Received malformed message: " + string);
			return;
		}
		Block block = GameRegistry.findBlock(parts[0], parts[1]);
		if (block == null) {
			logger.error("Could not find block: " + string + ", ignoring");
			return;
		}
		logger.info("Blacklisting block: " + block.getUnlocalizedName());
		TileTimeyWimey.blacklistBlock(block);
	}

	public SimpleNetworkWrapper getNetworkWrapper() {
		return wrapper;
	}




/*public static void register(BlockFluxGear block) {
		String name = block.getUnwrappedUnlocalizedName(block.func_149739_a());
		GameRegistry.registerBlock(block, name.substring(name.indexOf(":") + 1));
	}*/

/*
* ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyStorageCore.class, new RenderTileEnergyStorageCore());
* ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyPylon.class, new RenderTileEnergyPylon());
* MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.particleGenerator), new RenderParticleGen());
*
*
* GameRegistry.registerTileEntity(TileEnergyStorageCore.class, References.RESOURCESPREFIX + "TileEnergyStorageCore");
* GameRegistry.registerTileEntity(TileInvisibleMultiblock.class, References.RESOURCESPREFIX + "TileInvisibleMultiblock");
* GameRegistry.registerTileEntity(TileEnergyPylon.class, References.RESOURCESPREFIX + "TileEnergyPylon");
* GameRegistry.registerTileEntity(TileParticleGenerator.class, References.RESOURCESPREFIX + "TileParticleGenerator");
*
* add((Block)ModBlocks.particleGenerator, new Object[]{"RBR", "BCB", "RBR", Character.valueOf('R'), Blocks.redstone_block, Character.valueOf('B'), Items.blaze_rod, Character.valueOf('C'), ModItems.draconicCore});
* add((Block)ModBlocks.energyStorageCore, new Object[]{"CCC", "SMS", "CCC", Character.valueOf('C'), ModItems.draconiumIngot, Character.valueOf('S'), ModItems.wyvernEnergyCore, Character.valueOf('M'), ModItems.wyvernCore});
* addOre((ItemStack)(new ItemStack(ModBlocks.energyPylon, 2)), new Object[]{"IEI", "MCM", "IDI", Character.valueOf('I'), ModItems.draconiumIngot, Character.valueOf('E'), Items.ender_eye, Character.valueOf('C'), ModItems.draconicCore, Character.valueOf('D'), "gemDiamond", Character.valueOf('M'), "gemEmerald"});
* addOre((Item)ModItems.draconicCore, new Object[]{"CSC", "SMS", "CSC", Character.valueOf('C'), "ingotGold", Character.valueOf('S'), ModItems.draconiumIngot, Character.valueOf('M'), "gemDiamond"});
* add((Item)ModItems.wyvernCore, new Object[]{"CSC", "SMS", "CSC", Character.valueOf('C'), ModItems.draconiumIngot, Character.valueOf('S'), ModItems.draconicCore, Character.valueOf('M'), Items.nether_star});
* add((ItemStack)ModItems.wyvernEnergyCore, new Object[]{"CSC", "SMS", "CSC", Character.valueOf('C'), ModItems.draconiumIngot, Character.valueOf('S'), Blocks.redstone_block, Character.valueOf('M'), ModItems.draconicCore});
*/




















	public void blacklistTile(final String string) {
		try {
			Class<?> clazz = this.getClass().getClassLoader().loadClass(string);
			if (clazz == null) {
				logger.error("Class null: " + string);
				return;
			}
			if (!TileEntity.class.isAssignableFrom(clazz)) {
				logger.error("Class not a TileEntity: " + string);
				return;
			}
			TileTimeyWimey.blacklistTile((Class<? extends TileEntity>) clazz);
		} catch (ClassNotFoundException e) {
			logger.error("Class not found: " + string + ", ignoring");
		}
	}

	@EventHandler
	public void imcMessage(FMLInterModComms.IMCEvent event) {
		for (FMLInterModComms.IMCMessage message : event.getMessages()) {
			if (!message.isStringMessage()) {
				logger.error("Received non-string message! Ignoring");
				continue;
			}
			final String string = message.getStringValue();
			if (message.key.equals("blacklist-block")) {
				blacklistBlock(string);
			} else if (message.key.equals("blacklist-tile")) {
				blacklistTile(string);
			}
		}
	}

    public void resetClientConfigs() {
        TileDynamo.configure();
        logger.info(StringHelper.localize("Restoring Client Configuration..."));
    }

    void loadWorldGeneration() {
        if (!config.get("world", "GenerateWorldJSON", true, "If enabled, Project Flux Gear will create default world generation files - if it cannot find existing ones. Only disable this if you know what you are doing.")) {
            return;
        }
        worldGen = new File(CoFHProps.configDir, "/cofh/world/ProjectFluxGear-Ores.json");
        if (!worldGen.exists()) {
            try {
                worldGen.createNewFile();
                CoreUtils.copyFileUsingStream("assets/mortvana.projectfluxgear/world/ProjectFluxGear-Ores.json", worldGen);
            } catch (Throwable localThrowable) {
                localThrowable.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void chunkDataSave (ChunkDataEvent.Save event) {
        event.getData().setBoolean("ProjectFluxGear.Retrogen", true);
    }

	/* Utility Code, any public utility goes here */

	/* Global Constants */
	public static final String DEFAULT_OWNER = "[None]";
	public static final int TIME_CONSTANT = 32;
	public static final int TIME_CONSTANT_HALF = TIME_CONSTANT / 2;
	public static final int TIME_CONSTANT_QUARTER = TIME_CONSTANT / 4;
	public static final int TIME_CONSTANT_EIGHTH = TIME_CONSTANT / 8;
	public static final int RF_PER_EU = 4;

	/* Network */
	public static final int NETWORK_UPDATE_RANGE = 192;

	/* Audio */
	public static float soundVolume = 1.0f;

	/* Options */
	public static boolean enableUpdateNotice = true;
	public static boolean enableDismantleLogging = false;
	public static boolean enableOpSecureAccess = false;
	public static boolean enableOpSecureAccessWarning = true;

	public class FluxGearCompat {
		boolean kroostyl, mfr, ic3, mek, tc4, m4synth, tinkers, waila, nei;
		boolean bc, ae2, myst;

		public void appliedEnergisticsIMC() {

		}

		public void buildcraftIMC() {

		}

		public void mystcraftIMC() {

		}
	}
}
