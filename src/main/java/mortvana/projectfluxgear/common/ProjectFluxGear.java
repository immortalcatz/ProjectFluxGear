package mortvana.projectfluxgear.common;

import java.io.File;
import java.util.Random;

import mortvana.projectfluxgear.block.tile.TileTimeyWimey;
import mortvana.projectfluxgear.world.PoorOreGen;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

import mortvana.projectfluxgear.util.gui.FluxGearTab;
import mortvana.projectfluxgear.util.repack.pulsar.config.ForgeCFG;
import mortvana.projectfluxgear.util.repack.pulsar.control.PulseManager;

import mortvana.projectfluxgear.util.block.material.MaterialSoilOre;
import mortvana.projectfluxgear.util.helper.LoadedHelper;

import mortvana.projectfluxgear.common.config.*;
import mortvana.projectfluxgear.client.FluxGearAchievements;
import mortvana.projectfluxgear.world.FluxGearWorldGenerator;
import mortvana.projectfluxgear.world.GravelOreGenEventHandler;

import mortvana.projectfluxgear.world.GenExubitura;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ProjectFluxGear.modID, name = "Project Flux Gear", version = ProjectFluxGear.version, dependencies = ProjectFluxGear.dependencies, canBeDeactivated = false, guiFactory = "mortvana.mortvana.projectfluxgear.client.config.ConfigGuiFactory")
public class ProjectFluxGear {
    public static final String version = "v0.0.1.0.cactus";
    public static final String modID = "ProjectFluxGear";

	// Here's a way to help avoid frustration:
	// Imagine Mojang only hires dyslexic and 'slow' 10-year-olds that write code only when a bird is actively trying to eat their left eye.
	// It's a reminder that no matter where you look, you're going to find something and exclaim, "What fucking moron wrote this piece of shit!?"
	//     -Skyboy026, November 9th, 2014

	public static final Logger logger = LogManager.getLogger(modID);
    //public static final PacketPipeline packetPipeline = new PacketPipeline();

    public static final boolean debugWorldGen = true;

    @Instance(modID)
    public static ProjectFluxGear instance;
	public static Random random = new Random();

	public static final String[] dyeTypes = new String[] { "White", "Orange", "Magenta", "LightBlue", "Yellow", "Lime", "Pink", "Gray", "LightGray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
	public static final String[] colorNames = new String[] { "white", "orange", "magenta", "lightblue", "yellow", "lime", "pink", "gray", "silver", "aqua", "purple", "blue", "brown", "green", "red", "black" };

	@SidedProxy(clientSide = "mortvana.mortvana.projectfluxgear.client.ClientProxy", serverSide = "mortvana.mortvana.projectfluxgear.common.CommonProxy")
    public static CommonProxy proxy;

    // My favorite Pulsar is a Magnetar...
    // Loads modules in a way that doesn't clutter the @Mod list
    public static PulseManager pulsar = new PulseManager(modID, new ForgeCFG("ProjectFluxGear-Modules", "Modules: Disabling these will disable a chunk of the mod"));

    public ProjectFluxGear() {
	    //EnvironmentChecks.verifyEnvironmentSanity();
    }

    public static FluxGearContent content = new FluxGearContent();

	public String[] blacklistedBlocks;
	public String[] blacklistedTiles;

    //public static final GuiHandler guiHandler = new GuiHandler();

    public static final CreativeTabs tabMaterials = new FluxGearTab("PFG-Materials", "mortvana.projectfluxgear.materialsTab", FluxGearContent.gemDioptase);
    public static final CreativeTabs tabWorld = new FluxGearTab("PFG-World", "mortvana.projectfluxgear.worldTab", FluxGearContent.oreBauxite);
    public static final CreativeTabs tabTools = new FluxGearTab("PFG-Tools", "mortvana.projectfluxgear.toolsTab", FluxGearContent.toolProtoSonicWrench);
	public static final CreativeTabs generalTab = new FluxGearTab("MechStoneworks-General", "mortvana.projectfluxgear.generalTab", new ItemStack(Items.potato));
	public static CreativeTabs paintedStoneTab;
	public static final CreativeTabs stonesTab = new FluxGearTab("MechStoneworks-Stone", "mechanicsStoneworks.stoneTab", new ItemStack(Blocks.obsidian/*MechStoneworksContent.coloredStone, 1, 10*/));
    public static final CreativeTabs thaumicTab = new FluxGearTab("PFG-Thaumic", "fluxgear.thaumicTab", new ItemStack(FluxGearContent.itemWardenAmulet));
    //MOAR Tabs?

	public static Material soilOre = new MaterialSoilOre();

    public static FluxGearCompat compat = new FluxGearCompat();

    // Doctor Octoartifact, BLAAHHHH...
    //public static ArrayList<String> sounds;// = CongealedBloodBlock.sounds;
    //public static ContentRegistry weirdRegistry;

    /** Initialization Sequence */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);

        FluxGearConfig.loadConfiguration(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Main.cfg"));
        FluxGearConfigWorld.loadConfiguration(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-World.cfg"));
	    FluxGearConfig.loadConfiguration(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Tweaks.cfg"));

        compat.preInitCompat();
        compat.preInitIMC();
        content.preInit();

        GameRegistry.registerWorldGenerator(new FluxGearWorldGenerator(), 1);
	    GameRegistry.registerWorldGenerator(new GenExubitura(), 1);

        // Hey, listen. Hey, listen. Hey-- ARTIFACT DETECTED, EeEEXXxTtTEEeRRrMmMmIiIiNnNAAaA*boom*
        //MinecraftForge.EVENT_BUS.register(weirdRegistry.bucketMan);

        if (FluxGearConfig.achievementsEnabled) {
            FluxGearAchievements.addAchievements();
        }

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
        PoorOreGen.registerOres();
        MinecraftForge.TERRAIN_GEN_BUS.register(new GravelOreGenEventHandler());

	    paintedStoneTab = new FluxGearTab("MechStoneworks-PaintedStone", "mortvana.projectfluxgear.paintedStoneTab", new ItemStack(MechStoneworksContent.coloredStone, 1, 13));
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
	    ModRecipes.init();
	    ModResearch.init();

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
			System.out.println("Received malformed message: " + string);
			return;
		}
		Block block = GameRegistry.findBlock(parts[0], parts[1]);
		if (block == null) {
			System.out.println("Could not find block: " + string + ", ignoring");
			return;
		}
		System.out.println("Blacklisting block: " + block.getUnlocalizedName());
		TileTimeyWimey.blacklistBlock(block);
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

	public int colorStoneBlocks(World world, int x, int y, int z, int inputMeta, int range, int maxBlocks) {
		boolean changed = false;
		int amount = 0;

		for(int xPos = -range; xPos <= range && amount <= maxBlocks; ++xPos) {
			for(int yPos = -range; yPos <= range && amount <= maxBlocks; ++yPos) {
				for(int zPos = -range; zPos <= range && amount <= maxBlocks; ++zPos) {
					Block block = world.getBlock(x + xPos, y + yPos, z + zPos);
					if(block == Blocks.stone) {
						++amount;
						world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStone, inputMeta, 3);
					} else if(block == Blocks.cobblestone) {
						++amount;						world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredCobble, inputMeta, 3);
					} else if(block == Blocks.mossy_cobblestone) {
						++amount;
						world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredMossCobble, inputMeta, 3);
					} else {
						int meta;
						if(block == Blocks.stonebrick) {
							++amount;
							meta = world.getBlockMetadata(x + xPos, y + yPos, z + zPos);
							if(meta == 0) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneBrick, inputMeta, 3);
							} else if(meta == 1) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredMossStoneBrick, inputMeta, 3);
							} else if(meta == 2) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredCrackedStoneBrick, inputMeta, 3);
							} else if(meta == 3) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneSquareBrick, inputMeta, 3);
							}
						} else if(block == Blocks.wool) {
							++amount;
							world.setBlock(x + xPos, y + yPos, z + zPos, Blocks.wool, inputMeta, 3);
						} else if(block == Blocks.brick_block) {
							++amount;
							world.setBlock(x + xPos, y + yPos, z + zPos, content.clayBrickSmall, inputMeta, 3);
						} else if(LoadedHelper.isTinkersLoaded && block == GameRegistry.findBlock("TConstruct", "decoration.multibrickfancy")) {
							meta = world.getBlockMetadata(x + xPos, y + yPos, z + zPos);
							if(meta == 14) {
								++amount;
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneFancyBrick, inputMeta, 3);
							} else if(meta == 15) {
								++amount;
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneRoad, inputMeta, 3);
							}
						}
					}
				}
			}
		}

		return amount;
	}

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

	// This is this way so one can read it, even if yhe formatting is horrid.
	public static final String dependencies =
					"required-after:CoFHCore;" +
					"after:ThermalExpansion;" +
					"after:Artifice;" +
					"after:ProjRed|Exploration;" +
					"after:StevesFactoryManager;" +
					"after:appliedenergistics2;" +
					"after:ExtraUtilities;" +
					"after:OpenBlocks;" +
					"after:magicalcrops;" +
					"after:JABBA;" +
					"after:BigReactors;" +
					"after:Mekanism;" +
					"after:EnderStorage;" +
					"after:StevesFactoryManager;" +
					"after:RotaryCraft";
}
