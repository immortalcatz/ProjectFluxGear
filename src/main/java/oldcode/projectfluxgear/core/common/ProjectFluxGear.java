package oldcode.projectfluxgear.core.common;

import java.io.File;
import java.util.Random;

import oldcode.projectfluxgear.core.common.config.FluxGearConfig;
import oldcode.projectfluxgear.core.common.config.FluxGearConfigTweaks;
import oldcode.projectfluxgear.core.common.config.FluxGearConfigWorld;
import mortvana.projectfluxgear.to_refactor.block.tile.TileTimeyWimey;
import mortvana.projectfluxgear.to_refactor.FluxGearContent_;
import mortvana.projectfluxgear.thaumic.world.ExubituraGenerator;
import mortvana.projectfluxgear.to_refactor.util.gui.FluxGearTab;
import mortvana.projectfluxgear.to_refactor.world.PoorOreGenerator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

import mortvana.projectfluxgear.to_refactor.world.FluxGearWorldGenerator;
import mortvana.projectfluxgear.to_refactor.world.GravelOreGenEventHandler;

import mortvana.projectfluxgear.util.block.material.MaterialSoilOre;
import mortvana.projectfluxgear.util.handler.DummyHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProjectFluxGear {
    //public static final PacketPipeline packetPipeline = new PacketPipeline();

    public static final boolean debugWorldGen = true;

    @Instance(modID)
    public static ProjectFluxGear instance;
	public static Random random = new Random();

	//public static final String[] dyeTypes = new String[] { "White", "Orange", "Magenta", "LightBlue", "Yellow", "Lime", "Pink", "Gray", "LightGray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
	//public static final String[] colorNames = new String[] { "white", "orange", "magenta", "lightblue", "yellow", "lime", "pink", "gray", "silver", "aqua", "purple", "blue", "brown", "green", "red", "black" };

	@SidedProxy(clientSide = "mortvana.mortvana.projectfluxgear.core.client.ClientProxy", serverSide = "mortvana.mortvana.projectfluxgear.core.common.CommonProxy")
    public static CommonProxy proxy;
	public SimpleNetworkWrapper wrapper;

	public ProjectFluxGear() {
	    //EnvironmentChecks.verifyEnvironmentSanity();
    }

    public static FluxGearContent_ content = new FluxGearContent_();

	public String[] blacklistedBlocks;
	public String[] blacklistedTiles;

    //public static final GuiHandler guiHandler = new GuiHandler();

    public static final CreativeTabs tabMaterials = new FluxGearTab("PFG-Materials", "fluxgear.materialsTab", FluxGearContent_.gemDioptase);
    public static final CreativeTabs tabWorld = new FluxGearTab("PFG-World", "fluxgear.worldTab", FluxGearContent_.oreBauxite);
    public static final CreativeTabs techTab = new FluxGearTab("PFG-Tech", "fluxgear.techTab", FluxGearContent_.toolProtoSonicWrench);
	public static final CreativeTabs generalTab = new FluxGearTab("PFG-General", "fluxgear.generalTab", new ItemStack(Items.potato));
	public static final CreativeTabs stonesTab = new FluxGearTab("PFG-Stone", "fluxgear.stoneTab", new ItemStack(Blocks.obsidian));
    //MOAR Tabs?

	public static Material soilOre = new MaterialSoilOre();
	public static DummyHandler handler = new DummyHandler();

    //public static FluxGearCompat compat = new FluxGearCompat();

    // Doctor Octoartifact, BLAAHHHH...
    //public static ArrayList<String> sounds;// = CongealedBloodBlock.sounds;
    //public static ContentRegistry weirdRegistry;

    /** Initialization Sequence */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);

        FluxGearConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Main.cfg"));
        FluxGearConfigWorld.loadConfiguration(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-World.cfg"));
	    FluxGearConfigTweaks.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Tweaks.cfg"));

        //compat.preInitCompat();
        //compat.preInitIMC();
        content.preInit();
	    //TODO* if ()

        GameRegistry.registerWorldGenerator(new FluxGearWorldGenerator(), 1);
	    GameRegistry.registerWorldGenerator(new ExubituraGenerator(), 1);

        // Hey, listen. Hey, listen. Hey-- ARTIFACT DETECTED, EeEEXXxTtTEEeRRrMmMmIiIiNnNAAaA*boom*
        //MinecraftForge.EVENT_BUS.register(weirdRegistry.bucketMan);

        //if (FluxGearConfig.achievementsEnabled) {
        //    FluxGearAchievements.addAchievements();
        //}

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.registerRenderers();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        content.init();
        GameRegistry.registerFuelHandler(content);

        //packetPipeline.initalize();
        /*MinecraftForge.EVENT_BUS.register(WorldHandler.instance);

        if (retrogen) {
            FMLCommonHandler.instance().bus().register(new WorldTickHandler());
        }*/

        //loadWorldGeneration();

        /** Register Handlers */
        MinecraftForge.EVENT_BUS.register(proxy);
        PoorOreGenerator.registerOres();
        MinecraftForge.TERRAIN_GEN_BUS.register(new GravelOreGenEventHandler());

	    //TODO: mDerpohouse!!!!!!!!!!!!!!
		/*if(FluxGearCore.isTinkersLoaded) {
			ToolCore chisel = (ToolCore) GameRegistry.findItem("TConstruct", "chisel");
			Detailing chiseling = TConstructRegistry.getChiselDetailing();

			for(int i = 0; i < 16; ++i) {
				chiseling.addDetailing(content.coloredStone, i, content.coloredStoneBrick, i, chisel);
				chiseling.addDetailing(content.coloredStoneBrick, i, content.coloredStoneRoad, i, chisel);
				chiseling.addDetailing(content.coloredStoneRoad, i, content.coloredStoneFancyBrick, i, chisel);
				chiseling.addDetailing(content.coloredStoneFancyBrick, i, content.coloredStoneSquareBrick, i, chisel);
			}
		}*/
    }

    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {

	    blacklistedBlocks = FluxGearConfig.blacklistedBlocks;
	    blacklistedTiles = FluxGearConfig.blacklistedTiles;

	    for (String block : blacklistedBlocks)
		    blacklistBlock(block);
	    for (String tile : blacklistedTiles)
		    blacklistTile(tile);

        content.postInit();
	    //TODO* ModRecipes.init();
	    //TODO* ModResearch.init();

        proxy.registerEntities();

        //packetPipeline.postInitialize();

    }

    @EventHandler
    public void remapEvent (FMLMissingMappingsEvent event) {
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
		logger.error("Blacklisting block: " + block.getUnlocalizedName());
		TileTimeyWimey.blacklistBlock(block);
	}

	public SimpleNetworkWrapper getNetworkWrapper() {
		return wrapper;
	}

























	public void blacklistTile(final String string) {
		try {
			Class<?> clazz = this.getClass().getClassLoader().loadClass(string);
			if (clazz == null) {
				System.out.println("Class null: " + string);
				return;
			}
			if (!TileEntity.class.isAssignableFrom(clazz)) {
				System.out.println("Class not a TileEntity: " + string);
				return;
			}
			TileTimeyWimey.blacklistTile((Class<? extends TileEntity>) clazz);
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: " + string + ", ignoring");
		}
	}

	@EventHandler
	public void imcMessage(FMLInterModComms.IMCEvent event) {
		for (FMLInterModComms.IMCMessage message : event.getMessages()) {
			if (!message.isStringMessage()) {
				System.out.println("Received non-string message! Ignoring");
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
        //TileDynamo.configure();
        //log.info(StringHelper.localize("Restoring Client Configuration..."));
    }

    /*void loadWorldGeneration() {
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
    }*/

    /*@SubscribeEvent
    public void chunkDataSave (ChunkDataEvent.Save event) {
        event.getData().setBoolean("ProjectFluxGear.Retrogen", true);
    }*/

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
}
