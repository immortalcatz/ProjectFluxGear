package mortvana.fluxgearcore.common;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;


public class FluxGearCoreConfig {

	static Configuration config;
	public static File configFolder;

	public static void loadConfiguration (File mainConfigFolder) {
		FluxGearCore.log.info("Loading configuration from disk.");
		try {
			configFolder = new File(mainConfigFolder.getCanonicalPath(), "Mortvana");
			if (!configFolder.exists())
				configFolder.mkdirs();
		}
		catch (IOException e) {
			FluxGearCore.log.error("Error getting/creating Flux Gear Core configuration directory: " + e.getMessage());
		}
		config = new Configuration(new File(configFolder, ("FluxGearCore.cfg")));
		config.load();
		silenceEnvChecks = config.get("Environment", "unsupportedLogging", false).getBoolean(false);
		enableDebug = config.get("Debug", "Enable Debug", false).getBoolean(false);
		seaLevel = config.get("Misc", "Sea Level", 64, "Offered for mods that alter world heights, looking at you TFC").getInt(64);
		config.save();
		FluxGearCore.log.info("Configuration load completed.");
	}

	// BEGIN CONFIG VARS
	// Env Checks
	public static boolean silenceEnvChecks;
	// Debug Helpers
	public static boolean enableDebug;
	public static int seaLevel;
}