package mortvana.projectfluxgear.common;

import cofh.core.CoFHProps;
import cofh.core.util.FMLEventHandler;
import cofh.core.util.CoreUtils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import mortvana.fluxgearcore.pulsar.config.ForgeCFG;
import mortvana.fluxgearcore.pulsar.control.PulseManager;
import mortvana.fluxgearcore.util.FluxGearData;
import mortvana.fluxgearcore.util.handler.ConfigHandler;
import mortvana.fluxgearcore.util.remapper.Remapper;

import mortvana.projectfluxgear.gui.FluxGearAchievements;
import mortvana.projectfluxgear.gui.PFGCreativeTab;
import mortvana.projectfluxgear.modules.ThermalKroostyl;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.world.ChunkDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = ProjectFluxGear.modID, name = "Project Flux Gear", version = ProjectFluxGear.version, dependencies = ProjectFluxGear.dependencies, canBeDeactivated = false, guiFactory = "mortvana.projectfluxgear.gui.config.ConfigGuiFactory")
public class ProjectFluxGear {

    public static final String version = "v0.1.0.2";
    public static final String modID = "ProjectFluxGear";
    public static final String dependencies = "required-after:FluxGearCore; required-after:CoFHCore; required-after:ThermalFoundation; required-after:ThermalExpansion";

    public static final Logger logger = LogManager.getLogger(modID);
    //public static final PacketPipeline packetPipeline = new PacketPipeline();

    @Instance(modID)
    public static ProjectFluxGear instance;

    @SidedProxy(clientSide = "mortvana.projectfluxgear.client.ClientProxy", serverSide = "mortvana.projectfluxgear.common.CommonProxy")
    public static CommonProxy proxy;

    // My favorite Pulsar is a Magnetar...
    // Loads modules in a way that doesn't clutter the @Mod list
    public static PulseManager pulsar = new PulseManager(modID, new ForgeCFG("ProjectFluxGear-Modules", "Modules: Disabling these will disable a chunk of the mod"));

    public ProjectFluxGear() {}

    FluxGearContent content;
    public static boolean retrogen;

    public static Remapper thermalRemapper;

    public static final ConfigHandler config = new ConfigHandler(version);
    //public static final GuiHandler guiHandler = new GuiHandler();


    public static final CreativeTabs tab = new PFGCreativeTab();
    //MOAR Tabs?

    public static File worldGen;
    public static final String worldGenInternal = "assets/projectfluxgear/world/ProjectFluxGear-Ores.json";

    // Doctor Octoartifact, BLAAHHHH...
    /*public static ArrayList<String> sounds;// = CongealedBloodBlock.sounds;
    public static ContentRegistry weirdRegistry;*/

    /** Initialization Sequence */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        thermalRemapper = new Remapper(Loader.instance().activeModContainer());
        MinecraftForge.EVENT_BUS.register(this);

        config.setConfiguration(new Configuration(new File(FluxGearData.configDir, "Mortvana")));

        /*
        pulsar.registerPulse(new ThermalCore());
        pulsar.registerPulse(new ThermalMachines());
        pulsar.registerPulse(new ThermalDynamos());
        pulsar.registerPulse(new WeirdScience());
        pulsar.registerPulse(new SolarDynamos());
        pulsar.registerPulse(new StarDynamos());
        pulsar.registerPulse(new AtomicPower());
        pulsar.registerPulse(new TinkersDynamos());
        pulsar.registerPulse(new ThermalTinkering());
        pulsar.registerPulse(new ThermalCoilguns());
        pulsar.registerPulse(new ThermalTools());
        pulsar.registerPulse(new ThermalArmor());
        pulsar.registerPulse(new ThermalTinkers());
        pulsar.registerPulse(new ThermalNEI());
        pulsar.registerPulse(new ThermalBees());
        pulsar.registerPulse(new ThermalTC4());
        pulsar.registerPulse(new ThermalWAILA());
        pulsar.registerPulse(new ThermalMek());
        pulsar.registerPulse(new ThermalIC3());
        pulsar.registerPulse(new ThermalMFR());
        pulsar.registerPulse(new ThermalMystcraft());
        pulsar.registerPulse(new ThermalBuildcraft());
        pulsar.registerPulse(new ThermalAE2());
        pulsar.registerPulse(new ThermalM4Synth());*/
        pulsar.registerPulse(new ThermalKroostyl());


        content = new FluxGearContent();
        content.preInit();

        FMLEventHandler.initialize();

        //Erroneous Errors of Erroneousness
        /*weirdRegistry = new ContentRegistry(config, logger, tabWeirdScience);
        MinecraftForge.EVENT_BUS.register(weirdRegistry.bucketMan);
        WeirdScienceContent.RegisterContent(config, weirdRegistry, event);*/

        pulsar.preInit(event);

        if (FluxGearConfig.achievementsEnabled) {
            FluxGearAchievements.addAchievements();
        }

        config.save();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.registerRenderer();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        content.init();
        GameRegistry.registerFuelHandler(content);

        //packetPipeline.initalize();
        /*MinecraftForge.EVENT_BUS.register(WorldHandler.instance);

        if (retrogen) {
            FMLCommonHandler.instance().bus().register(new WorldTickHandler());
        }*/

        loadWorldGeneration();

        /** Register Handlers */
        MinecraftForge.EVENT_BUS.register(proxy);

        pulsar.init(event);

    }

    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {

        content.postInit();

        proxy.registerEntities();

        //packetPipeline.postInitialize();

        pulsar.postInit(event);

        config.cleanUp(false, true);
    }

    @EventHandler
    public void remapEvent (FMLMissingMappingsEvent event) {
        thermalRemapper.processMigrationEvent(event);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        content.registerDispenserHandlers();
    }

    public void resetClientConfigs() {
        //TileDynamo.configure();
        //log.info(StringHelper.localize("Restoring Client Configuration..."));
    }


    void loadWorldGeneration() {
        if (!config.get("world", "GenerateWorldJSON", true, "If enabled, Project Flux Gear will create default world generation files - if it cannot find existing ones. Only disable this if you know what you are doing.")) {
            return;
        }
        worldGen = new File(CoFHProps.configDir, "/cofh/world/ProjectFluxGear-Ores.json");
        if (!worldGen.exists()) {
            try {
                worldGen.createNewFile();
                CoreUtils.copyFileUsingStream("assets/projectfluxgear/world/ProjectFluxGear-Ores.json", worldGen);
            } catch (Throwable localThrowable) {
                localThrowable.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void chunkDataSave (ChunkDataEvent.Save event)
    {
        event.getData().setBoolean("ProjectFluxGear.Retrogen", true);
    }
}
