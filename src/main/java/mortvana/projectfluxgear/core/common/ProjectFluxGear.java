package mortvana.projectfluxgear.core.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import mortvana.melteddashboard.util.ModuleLoader;
import mortvana.melteddashboard.util.helpers.LoadedHelper;
import mortvana.projectfluxgear.core.config.*;
import mortvana.projectfluxgear.core.network.CommonProxy;
import mortvana.projectfluxgear.immersion.common.FluxGearImmersion;
import mortvana.projectfluxgear.integration.common.FluxGearIntegration;
import mortvana.projectfluxgear.oreberries.common.Oreberries;
import mortvana.projectfluxgear.tech.common.FluxGearTech;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import mortvana.projectfluxgear.tinkers.common.TinkersFoundry;
import mortvana.projectfluxgear.morttweaks.common.MortTweaks;


@Mod(modid = ProjectFluxGear.MOD_ID, name = ProjectFluxGear.MOD_NAME, version = ProjectFluxGear.MOD_VERSION, dependencies = ProjectFluxGear.MOD_DEPENDENCIES/*, guiFactory = "mortvana.projectfluxgear.core.client.config.ConfigGuiFactory"*/)
public class ProjectFluxGear {

	public static final String MOD_ID = "ProjectFluxGear";
	public static final String MOD_NAME = "Project Flux Gear";
	public static final String MOD_VERSION = "vPO.TA.TO.RANDOM-DEV";

	@Instance(MOD_ID)
	public static ProjectFluxGear instance;

	@SidedProxy(clientSide = "mortvana.projectfluxgear.core.network.ClientProxy", serverSide = "mortvana.projectfluxgear.core.network.CommonProxy", modId = MOD_ID)
	public static CommonProxy proxy;

	public ModuleLoader moduleLoader = new ModuleLoader();

	public ProjectFluxGear() {}

	/* *=-=-=-=* Initialization Sequence *=-=-=-=* */
	/**
	 *  In the preInit step you only want to load configs, inform Forge if your mod has to be loaded after any others,
	 *  and load any framework stuff. No heavy loading or registering should occur here, because that should happen
	 *  during init, as there is no guarantee stuff wont explode when they start Minecraft.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//MinecraftForge.EVENT_BUS.register(this);

		moduleLoader.addModule("Core", true, new FluxGearCoreContent(), new FluxGearCoreConfig(event, "/Mortvana/ProjectFluxGear-Core.cfg"));
		moduleLoader.addModule("Tech", FluxGearCoreConfig.enableTech, new FluxGearTech(), new ProjectFluxGearConfig(event, "/Mortvana/ProjectFluxGear-Tech.cfg"));
		moduleLoader.addModule("Immersion", FluxGearCoreConfig.enableImmersion, new FluxGearImmersion(), new FluxGearImmersionConfig(event, "/Mortvana/ProjectFluxGear-Immersion.cfg"));
		moduleLoader.addModule("Oreberries", FluxGearCoreConfig.enableOreberries, new Oreberries(), new OreberriesConfig(event, "/Mortvana/ProjectFluxGear-Oreberries.cfg"));
		moduleLoader.addModule("TRevelations", FluxGearCoreConfig.enableThaumic && LoadedHelper.isThaumcraftLoaded, new ThaumicRevelations(), new ThaumicRevelationsConfig(event, "/Mortvana/ProjectFluxGear-ThaumicRevelations.cfg"));
		moduleLoader.addModule("TiFoundry", FluxGearCoreConfig.enableTinkers && LoadedHelper.isTinkersLoaded, new TinkersFoundry(), new TinkersFoundryConfig(event, "/Mortvana/ProjectFluxGear-TinkersFoundry.cfg"));
		moduleLoader.addModule("Integration", FluxGearCoreConfig.enableIntegration, new FluxGearIntegration(), new FluxGearIntegrationConfig(event, "/Mortvana/ProjectFluxGear-Integration.cfg"));
		moduleLoader.addModule("MortTweaks", FluxGearCoreConfig.enableTweaks, new MortTweaks(), new MortTweaksConfig(event, "/Mortvana/ProjectFluxGear-MortTweaks.cfg"));

		moduleLoader.preInit(event);
	}

	/**
	 *  This is where all the heavy loading and registering of handlers goes. Initialization of blocks and items,
	 *  TileEntity stuff, etc.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		moduleLoader.init(event);
	}

	/**
	 *  Stuff to do before finalizing, like intermod interactions.
	 *  This is for things that need to wait until the game is almost done loading before initializing.
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		moduleLoader.postInit(event);
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
