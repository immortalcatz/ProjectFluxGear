package mortvana.projectfluxgear.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class FluxGearModuleConfig {

	public static Configuration config;

	public static void loadConfig(File file) {
		ProjectFluxGear.logger.info("Loading Module Config");
		config = new Configuration(file);
		config.load();

			enableDecor = config.get("Modules", "Enable Decor Module", true, "Contains pretty stones, and other shinies.").getBoolean(true);
			enableTech = config.get("Modules", "Enable Project FLUX Gear", true, "The heart of the mod, contains all that delicious, insane technology!").getBoolean(true);
			enableTinkers = config.get("Modules", "Enable Tinker's Armory", true, "The spleen of the mod, because pick-mattocks! REQUIRES TINKER'S CONSTRUCT").getBoolean(true);
			enableThaumic = config.get("Modules", "Enable Thaumic Revelations", true, "The soul of the mod, now with more doom and alchemy! REQUIRES THAUMCRAFT").getBoolean(true);
			enableTweaks = config.get("Modules", "Enable MortTweaks", false, "Mortvana's silly tweaks, disabled by default out of courtesy").getBoolean(false);
			enableWorld = config.get("Modules", "Enable World Module", true, "All non-decor world-gen and crops, required for our tech to be craftable").getBoolean(true);
			enableOreberries = config.get("Modules", "Enable Oreberries", true, "IMC-based Oreberries, for science, yo! Now with extra Glassmakers!").getBoolean(true);

		if (config.hasChanged()) {
			config.save();
		}
		ProjectFluxGear.logger.info("Module Config Loaded");
	}

	public static boolean enableDecor;
	public static boolean enableTech;
	public static boolean enableTinkers;
	public static boolean enableThaumic;
	public static boolean enableTweaks;
	public static boolean enableWorld;
	public static boolean enableOreberries;
}
