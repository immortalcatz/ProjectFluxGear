package mortvana.projectfluxgear.common;

import java.io.File;
import java.lang.reflect.Method;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import mortvana.fluxgearcore.common.FluxGearCore;
import mortvana.fluxgearcore.gui.FluxGearTab;
import mortvana.fluxgearcore.util.handler.ConfigHandler;

import mortvana.projectfluxgear.common.config.FluxGearConfig;
import mortvana.projectfluxgear.gui.FluxGearAchievements;
import mortvana.projectfluxgear.world.FluxGearWorldGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ProjectFluxGear.modID, name = "Project Flux Gear", version = ProjectFluxGear.version, dependencies = ProjectFluxGear.dependencies, canBeDeactivated = false, guiFactory = "mortvana.projectfluxgear.gui.config.ConfigGuiFactory")
public class ProjectFluxGear {

    public static final String version = "v0.0.1.0.cactus";
    public static final String modID = "ProjectFluxGear";
    public static final String dependencies = "required-after:FluxGearCore; required-after:CoFHCore; after:ThermalExpansion";

    public static final Logger logger = LogManager.getLogger(modID);
    //public static final PacketPipeline packetPipeline = new PacketPipeline();

    @Instance(modID)
    public static ProjectFluxGear instance;

    @SidedProxy(clientSide = "mortvana.projectfluxgear.client.ClientProxy", serverSide = "mortvana.projectfluxgear.common.CommonProxy")
    public static CommonProxy proxy;

    // My favorite Pulsar is a Magnetar...
    // Loads modules in a way that doesn't clutter the @Mod list
    //public static PulseManager pulsar = new PulseManager(modID, new ForgeCFG("ProjectFluxGear-Modules", "Modules: Disabling these will disable a chunk of the mod"));

    public ProjectFluxGear() {}

    public static FluxGearContent content = new FluxGearContent();

    public static final ConfigHandler config = new ConfigHandler(version);
    //public static final GuiHandler guiHandler = new GuiHandler();


    public static final CreativeTabs tabResources = new FluxGearTab("PFG-Resources", "projectfluxgear.resourceTab", FluxGearContent.gemDioptase);
    public static final CreativeTabs tabOres = new FluxGearTab("PFG-Ores", "projectfluxgear.oreTab", FluxGearContent.oreBauxite);
    //MOAR Tabs?

    public static File worldGen;
    public static FluxGearCompat compat = new FluxGearCompat();
    public static final String worldGenInternal = "assets/projectfluxgear/world/ProjectFluxGear-Ores.json";

    // Doctor Octoartifact, BLAAHHHH...
    //public static ArrayList<String> sounds;// = CongealedBloodBlock.sounds;
    //public static ContentRegistry weirdRegistry;

    /** Initialization Sequence */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        //thermalRemapper = new Remapper(Loader.instance().activeModContainer());
        MinecraftForge.EVENT_BUS.register(this);

        config.setConfiguration(new Configuration(new File(FluxGearCore.configDir, "Mortvana")));

        compat.preInitCompat();
        compat.preInitIMC();
        content.preInit();

        GameRegistry.registerWorldGenerator(new FluxGearWorldGenerator(), 1);

        // Hey, listen. Hey, listen. Hey-- ARTIFACT DETECTED, EeEEXXxTtTEEeRRrMmMmIiIiNnNAAaA*boom*
        //MinecraftForge.EVENT_BUS.register(weirdRegistry.bucketMan);

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

        //loadWorldGeneration();

        /** Register Handlers */
        MinecraftForge.EVENT_BUS.register(proxy);

    }

    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {

        content.postInit();

        proxy.registerEntities();

        //packetPipeline.postInitialize();

        config.cleanUp(false, true);

        //Cheap and Dirty addon loading
        try {
            Class pfgAddons = Class.forName("mortvana.fluxgearaddons.common.FluxGearAddons");
            Method method = pfgAddons.getMethod("initialize");
            method.invoke(null);
        } catch (Exception e) {}
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
                CoreUtils.copyFileUsingStream("assets/projectfluxgear/world/ProjectFluxGear-Ores.json", worldGen);
            } catch (Throwable localThrowable) {
                localThrowable.printStackTrace();
            }
        }
    }*/

    /*@SubscribeEvent
    public void chunkDataSave (ChunkDataEvent.Save event) {
        event.getData().setBoolean("ProjectFluxGear.Retrogen", true);
    }*/
}
