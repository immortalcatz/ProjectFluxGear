package mortvana.projectfluxgear.core.module;

import java.io.File;

import cpw.mods.fml.common.event.*;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.helpers.LoadedHelper;
import mortvana.projectfluxgear.bees.common.AppliedApiology;
import mortvana.projectfluxgear.core.config.*;
import mortvana.projectfluxgear.decor.common.FluxGearDecor;
import mortvana.projectfluxgear.integration.common.FluxGearIntegration;
import mortvana.projectfluxgear.lighting.common.FluxGearLighting;
import mortvana.projectfluxgear.oreberries.common.Oreberries;
import mortvana.projectfluxgear.tech.common.FluxGearTech;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import mortvana.projectfluxgear.tinkers.common.TinkersArmory;
import mortvana.projectfluxgear.tweaks.common.MortTweaks;
import mortvana.projectfluxgear.world.common.FluxGearWorld;

public class ModuleLoader {

	public static boolean moduleWorldEnabled;
	public static boolean moduleTechEnabled;
	public static boolean moduleLightingEnabled;
	public static boolean moduleDecorEnabled;
	public static boolean moduleOreberriesEnabled;
	public static boolean moduleThaumicEnabled;
	public static boolean moduleTinkersEnabled;
	public static boolean moduleApiologyEnabled;
	public static boolean moduleIntegrationEnabled;
	public static boolean moduleTweaksEnabled;

	public static boolean moduleTechSanity;

	public static void preInit(FMLPreInitializationEvent event) {

		/** Initialize Variables */
		moduleWorldEnabled = FluxGearCoreConfig.enableWorld;
		moduleTechEnabled = FluxGearCoreConfig.enableTech && moduleWorldEnabled;
		moduleLightingEnabled = FluxGearCoreConfig.enableLighting;
		moduleDecorEnabled = FluxGearCoreConfig.enableDecor;
		moduleOreberriesEnabled = FluxGearCoreConfig.enableOreberries;
		moduleThaumicEnabled = FluxGearCoreConfig.enableThaumic && LoadedHelper.isThaumcraftLoaded;
		moduleTinkersEnabled = FluxGearCoreConfig.enableTinkers && LoadedHelper.isTinkersLoaded;
		moduleApiologyEnabled = FluxGearCoreConfig.enableApiology && LoadedHelper.isForestryLoaded;
		moduleIntegrationEnabled = FluxGearCoreConfig.enableIntegration;
		moduleTweaksEnabled = FluxGearCoreConfig.enableTweaks;

		/*if (moduleWorldEnabled && moduleTechEnabled) {
			moduleTechSanity = true;
		}
		if (moduleTechEnabled && !moduleWorldEnabled) {
			MeltedDashboardCore.logger.warn("Caution, Wet Snake: The Tech Module is loaded, but the World Module is not, this will be 'fun'... With a capital derp...");
			moduleTechSanity = false;
		}*/

		if (moduleWorldEnabled) {
			FluxGearWorldConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-World.cfg"));
			FluxGearWorld.preInit(event);
		}
		if (moduleTechEnabled) {
			ProjectFluxGearConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Tech.cfg"));
			FluxGearTech.preInit(event);
		}
		if (moduleLightingEnabled) {
			FluxGearLightingConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Lighting.cfg"));
			FluxGearLighting.preInit(event);
		}
		if (moduleDecorEnabled) {
			FluxGearDecorConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Decor.cfg"));
			FluxGearDecor.preInit(event);
		}
		if (moduleOreberriesEnabled) {
			OreberriesConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Oreberries.cfg"));
			Oreberries.preInit(event);
		}
		if (moduleThaumicEnabled) {
			ThaumicRevelationsConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-ThaumicRevelations.cfg"));
			ThaumicRevelations.preInit(event);
		}
		if (moduleTinkersEnabled) {
			TinkersArmoryConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-TinkersArmory.cfg"));
			TinkersArmory.preInit(event);
		}
		if (moduleApiologyEnabled) {
			AppliedApiologyConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-AppliedApiology.cfg"));
			AppliedApiology.preInit(event);
		}
		if (moduleIntegrationEnabled) {
			FluxGearIntegrationConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Integration.cfg"));
			FluxGearIntegration.preInit(event);
		}
		if (moduleTweaksEnabled) {
			MortTweaksConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-MortTweaks.cfg"));
			MortTweaks.preInit(event);
		}
	}

	public static void init(FMLInitializationEvent event) {
		if (moduleWorldEnabled) {
			FluxGearWorld.init(event);
		}
		if (moduleTechEnabled) {
			FluxGearTech.init(event);
		}
		if (moduleLightingEnabled) {
			FluxGearLighting.init(event);
		}
		if (moduleDecorEnabled) {
			FluxGearDecor.init(event);
		}
		if (moduleOreberriesEnabled) {
			Oreberries.init(event);
		}
		if (moduleThaumicEnabled) {
			ThaumicRevelations.init(event);
		}
		if (moduleTinkersEnabled) {
			TinkersArmory.init(event);
		}
		if (moduleApiologyEnabled) {
			AppliedApiology.init(event);
		}
		if (moduleIntegrationEnabled) {
			FluxGearIntegration.init(event);
		}
		if (moduleTweaksEnabled) {
			MortTweaks.init(event);
		}
	}

	public static void postInit(FMLPostInitializationEvent event) {
		if (moduleWorldEnabled) {
			FluxGearWorld.postInit(event);
		}
		if (moduleTechEnabled) {
			FluxGearTech.postInit(event);
		}
		if (moduleLightingEnabled) {
			FluxGearLighting.postInit(event);
		}
		if (moduleDecorEnabled) {
			FluxGearDecor.postInit(event);
		}
		if (moduleOreberriesEnabled) {
			Oreberries.postInit(event);
		}
		if (moduleThaumicEnabled) {
			ThaumicRevelations.postInit(event);
		}
		if (moduleTinkersEnabled) {
			TinkersArmory.postInit(event);
		}
		if (moduleApiologyEnabled) {
			AppliedApiology.postInit(event);
		}
		if (moduleIntegrationEnabled) {
			FluxGearIntegration.postInit(event);
		}
		if (moduleTweaksEnabled) {
			MortTweaks.postInit(event);
		}
	}
}
