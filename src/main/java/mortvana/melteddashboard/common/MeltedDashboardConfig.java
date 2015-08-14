package mortvana.melteddashboard.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.util.helpers.LoadedHelper;

public class MeltedDashboardConfig {

	public static Configuration config;

	public static void loadConfig(File file) {
		MeltedDashboardCore.logger.info("Oh the dashboard melted, but we still have the radio!~ - Loading Melted Dashboard Core Config");
		config = new Configuration(file);
		config.load();

		enableDebug = config.get("Debug", "Enable Debug", false, "Do not enable unless you know what you are doing.").getBoolean(false);
		//unsupportedLogging = config.get("Debug", "Enable Environment Stability Logging", true, "Disable this if you hate people warning you about breaking things.").getBoolean(true);
		debugWorldgen = config.get("Debug", "Enable Debug Worldgen", false, "For testing only, as it multiplies oregen drastically").getBoolean(false);

		registryOreDict = config.get("Registry", "Force OreDict Propriety", false, "Enable this if someone registers their stuff wrong, and as such it has a broken OreDict entry.").getBoolean(false);
		minimalRegistry = config.get("Registry", "Force Colorized Textures", false, "Enable this if you like monotonic blandness, or hate the ability to use custom textures.").getBoolean(false);

		seaLevel = config.get("World", "World Sea Level", SEA_LEVEL, "Offered for mods that alter world heights, looking at you TFC.").getInt(SEA_LEVEL);

		if (config.hasChanged()) {
			config.save();
		}
		MeltedDashboardCore.logger.info("Flux Gear Core Config Loaded");
	}

	public static final int SEA_LEVEL = LoadedHelper.isTFCLoaded ? 144 : 63;

	public static boolean enableDebug;
	public static boolean unsupportedLogging;
	public static boolean debugWorldgen;

	public static boolean registryOreDict;
	public static boolean minimalRegistry;

	public static int seaLevel;
}
