package mortvana.projectfluxgear.core.common;

import java.io.File;
import java.util.Random;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.common.MinecraftForge;

import mortvana.projectfluxgear.core.config.FluxGearModuleConfig;
import mortvana.projectfluxgear.core.module.ModuleLoader;
import mortvana.projectfluxgear.core.network.CommonProxy;
import mortvana.projectfluxgear.core.network.FluxGearPacketHandler_;
import mortvana.projectfluxgear.util.network.FluxGearMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ProjectFluxGear.MOD_ID, name = ProjectFluxGear.MOD_NAME, version = ProjectFluxGear.MOD_VERSION, dependencies = ProjectFluxGear.MOD_DEPENDENCIES/*, guiFactory = "mortvana.projectfluxgear.core.client.config.ConfigGuiFactory"*/)
public class ProjectFluxGear {
	// Here's a way to help avoid frustration:
	// Imagine Mojang only hires dyslexic and 'slow' 10-year-olds that write code only when a bird is actively trying to eat their left eye.
	// It's a reminder that no matter where you look, you're going to find something and exclaim, "What fucking moron wrote this piece of shit!?"
	//     -Skyboy026, November 9th, 2014

	// "The phase 'only Botania' in my head has become a shampoo... or something"
	// "I don't even know, but Botania is now a shampoo"
	//      -MysteriousAges, Mid-March, 2015

	public static final String MOD_ID = "ProjectFluxGear";
	public static final String MOD_NAME = "Project Flux Gear";
	public static final String MOD_VERSION = "vPO.TA.TO.RANDOM-DEV";

	public static final Logger logger = LogManager.getLogger(MOD_ID);

	@Instance
	public static ProjectFluxGear instance;
	public static Random random = new Random();

	@SidedProxy(clientSide = "mortvana.projectfluxgear.core.network.ClientProxy", serverSide = "mortvana.projectfluxgear.core.network.CommonProxy", modId = MOD_ID)
	public static CommonProxy proxy;

	public static FluxGearPacketHandler_<FluxGearMessage> packetHandler;

	public ProjectFluxGear() {

	}

	/** Initialization Sequence */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		FluxGearModuleConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Modules.cfg"));
		ModuleLoader.preInit(event);
		FluxGearCoreContent.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModuleLoader.init(event);
		FluxGearCoreContent.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ModuleLoader.postInit(event);
		FluxGearCoreContent.postInit();
	}


	// This is this way so one can read it, even if the formatting is horrid.
	public static final String MOD_DEPENDENCIES = "";//"required-after:CoFHCore;" +
	//"after:ThermalExpansion;" +
	//"after:Artifice;" +
	//"after:ProjRed|Exploration;" +
	//"after:StevesFactoryManager;" +
	//"after:appliedenergistics2;" +
	//"after:ExtraUtilities;" +
	//"after:OpenBlocks;" +
	//"after:magicalcrops;" +
	//"after:JABBA;" +
	//"after:BigReactors;" +
	//"after:Mekanism;" +
	//"after:EnderStorage;" +
	//"after:StevesFactoryManager;" +
	//"after:RotaryCraft" +
	//"after:MagicBees[2.3.0,)"; //Required for Tempus in the API
}
