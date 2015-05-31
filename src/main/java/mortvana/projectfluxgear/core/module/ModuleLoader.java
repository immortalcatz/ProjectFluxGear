package mortvana.projectfluxgear.core.module;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.core.config.FluxGearModuleConfig;

public class ModuleLoader {

	public static boolean moduleWorldEnabled;
	public static boolean moduleTechEnabled;
	public static boolean moduleThaumicEnabled;
	public static boolean moduleTinkersEnabled;
	public static boolean moduleDecorEnabled;
	public static boolean moduleOreberriesEnabled;
	public static boolean moduleTweaksEnabled;

	public static boolean moduleTechSanity;

	public static void preInit() {
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
			ProjectFluxGear.logger.warn("Caution, Wet Snake: The Tech Module is loaded, but the World Module is not, this will be 'fun'...");
			moduleTechSanity = false;
		}
	}

	public static void init() {

	}

	public static void postInit() {

	}
}
