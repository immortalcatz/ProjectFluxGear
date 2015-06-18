package mortvana.projectfluxgear.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class FluxGearCoreConfig {

	public static Configuration config;

	public static void loadConfig(File file) {
		ProjectFluxGear.logger.info("Loading Flux Gear Core Config");
		config = new Configuration(file);
		config.load();

			enableDebug = config.get("Debug", "Enable Debug", false, "Do not enable unless you know what you are doing.").getBoolean(false);
			//unsupportedLogging = config.get("Debug", "Enable Environment Stability Logging", true, "Disable this if you hate people warning you about breaking things.").getBoolean(true);

			//enableAchievements = config.get("General", "Enable Project Flux Gear's Achievements", true, "Disable this if you are a derp and port this to 1.6.4 :P").getBoolean(true);
			registryOreDict = config.get("General", "Force OreDict Propriety", false, "Enable this if someone registers there stuff wrong, and as such it has a broken OreDict entry.").getBoolean(false);

			seaLevel = config.get("World", "World Sea Level", 63, "Offered for mods that alter world heights, looking at you TFC.").getInt(63);


		if (config.hasChanged()) {
			config.save();
		}
		ProjectFluxGear.logger.info("Flux Gear Core Config Loaded");
	}

	public static boolean enableDebug;
	public static boolean unsupportedLogging;

	public static boolean enableAchievements;
	public static boolean registryOreDict;

	public static int seaLevel;

}
