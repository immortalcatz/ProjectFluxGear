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

import mortvana.projectfluxgear.core.config.FluxGearCoreConfig;
import mortvana.projectfluxgear.core.module.ModuleLoader;
import mortvana.projectfluxgear.core.network.CommonProxy;


@Mod(modid = ProjectFluxGear.MOD_ID, name = ProjectFluxGear.MOD_NAME, version = ProjectFluxGear.MOD_VERSION, dependencies = ProjectFluxGear.MOD_DEPENDENCIES/*, guiFactory = "mortvana.projectfluxgear.core.client.config.ConfigGuiFactory"*/)
public class ProjectFluxGear {

	public static final String MOD_ID = "ProjectFluxGear";
	public static final String MOD_NAME = "Project Flux Gear";
	public static final String MOD_VERSION = "vPO.TA.TO.RANDOM-DEV";

	@Instance(MOD_ID)
	public static ProjectFluxGear instance;

	@SidedProxy(clientSide = "mortvana.projectfluxgear.core.network.ClientProxy", serverSide = "mortvana.projectfluxgear.core.network.CommonProxy", modId = MOD_ID)
	public static CommonProxy proxy;

	public ProjectFluxGear() {}

	/* *=-=-=-=* Initialization Sequence *=-=-=-=* */
	/**
	 *  In the preInit step you only want to load configs, inform Forge if your mod has to be loaded after any others,
	 *  and load any framework stuff. No heavy loading or registering should occur here, because that happens during
	 *  init, as there is no guarantee stuff wont explode when they start Minecraft.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//MinecraftForge.EVENT_BUS.register(this);

		FluxGearCoreConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Core.cfg"));
		FluxGearCoreContent.preInit();
		ModuleLoader.preInit(event);
	}

	/**
	 *  This is where all the heavy loading and registering of handlers goes. Initialization of blocks and items,
	 *  TileEntity stuff, etc.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModuleLoader.init(event);
		FluxGearCoreContent.init();
	}


	/**
	 *  Stuff to do before finalizing, like intermod interactions.
	 *  This is for things that need to wait until the game is almost done loading before initializing.
	 */
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
