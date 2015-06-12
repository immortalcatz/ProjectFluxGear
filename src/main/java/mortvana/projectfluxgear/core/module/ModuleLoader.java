package mortvana.projectfluxgear.core.module;

import java.io.File;

import cpw.mods.fml.common.event.*;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.core.config.*;
import mortvana.projectfluxgear.decor.common.FluxGearDecor;
import mortvana.projectfluxgear.oreberries.common.Oreberries;
import mortvana.projectfluxgear.tech.common.FluxGearTech;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import mortvana.projectfluxgear.tinker.common.TinkersArmory;
import mortvana.projectfluxgear.tweaks.common.MortTweaks;
import mortvana.projectfluxgear.world.common.FluxGearWorld;

public class ModuleLoader {

	public static boolean moduleWorldEnabled;
	public static boolean moduleTechEnabled;
	public static boolean moduleThaumicEnabled;
	public static boolean moduleTinkersEnabled;
	public static boolean moduleDecorEnabled;
	public static boolean moduleOreberriesEnabled;
	public static boolean moduleTweaksEnabled;

	public static boolean moduleTechSanity;

	public static void preInit(FMLPreInitializationEvent event) {

		/** Initialize Variables */
		moduleWorldEnabled = FluxGearModuleConfig.enableWorld;
		moduleTechEnabled = FluxGearModuleConfig.enableTech;
		moduleThaumicEnabled = FluxGearModuleConfig.enableThaumic;
		moduleTinkersEnabled = FluxGearModuleConfig.enableTinkers;
		moduleDecorEnabled = FluxGearModuleConfig.enableDecor;
		moduleOreberriesEnabled = FluxGearModuleConfig.enableOreberries;
		moduleTweaksEnabled = FluxGearModuleConfig.enableTweaks;

		if (moduleWorldEnabled && moduleTechEnabled) {
			moduleTechSanity = true;
		}
		if (moduleTechEnabled && !moduleWorldEnabled) {
			ProjectFluxGear.logger.warn("Caution, Wet Snake: The Tech Module is loaded, but the World Module is not, this will be 'fun'... With a capital derp...");
			moduleTechSanity = false;
		}
		
		if (moduleWorldEnabled) {
			FluxGearWorldConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-World.cfg"));
			FluxGearWorld.preInit(event);
		}
		if (moduleTechEnabled) {
			ProjectFluxGearConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Tech.cfg"));
			FluxGearTech.preInit(event);
		}
		if (moduleThaumicEnabled) {
			ThaumicRevelationsConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-ThaumicRevelations.cfg"));
			ThaumicRevelations.preInit(event);
		}
		if (moduleTinkersEnabled) {
			TinkersArmoryConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-TinkersArmory.cfg"));
			TinkersArmory.preInit(event);
		}
		if (moduleDecorEnabled) {
			FluxGearDecorConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Decor.cfg"));
			FluxGearDecor.preInit(event);
		}
		if (moduleOreberriesEnabled) {
			OreberriesConfig.loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/Mortvana/ProjectFluxGear-Oreberries.cfg"));
			Oreberries.preInit(event);
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
		if (moduleThaumicEnabled) {
			ThaumicRevelations.init(event);
		}
		if (moduleTinkersEnabled) {
			TinkersArmory.init(event);
		}
		if (moduleDecorEnabled) {
			FluxGearDecor.init(event);
		}
		if (moduleOreberriesEnabled) {
			Oreberries.init(event);
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
		if (moduleThaumicEnabled) {
			ThaumicRevelations.postInit(event);
		}
		if (moduleTinkersEnabled) {
			TinkersArmory.postInit(event);
		}
		if (moduleDecorEnabled) {
			FluxGearDecor.postInit(event);
		}
		if (moduleOreberriesEnabled) {
			Oreberries.postInit(event);
		}
		if (moduleTweaksEnabled) {
			MortTweaks.postInit(event);
		}
	}
}
