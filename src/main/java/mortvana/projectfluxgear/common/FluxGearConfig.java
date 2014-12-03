package mortvana.projectfluxgear.common;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

public class FluxGearConfig {

	public static Configuration config;
	public static File configFolder;

	public static boolean enableDebug;
	public static boolean cobaltAssimilation;
	public static boolean autoBalanceEnergy;
	public static float solarPanelHeight;
	public static boolean achievementsEnabled;
	public static int thermiteFuelValue;

	public static void loadConfiguration (File mainConfigFolder) {
		ProjectFluxGear.logger.info("Loading configuration from disk.");
		try {
			configFolder = new File(mainConfigFolder.getCanonicalPath(), "Mortvana");
			if (!configFolder.exists())
				configFolder.mkdirs();
		} catch (IOException e) {
			ProjectFluxGear.logger.error("Error getting/creating Thermal Tinkerer configuration directory: " + e.getMessage());
		}
		config = new Configuration(new File(configFolder, ("ThermalTinkerer.cfg")));
		config.load();
		enableDebug = config.get("DebugHelpers", "Enable Debug", false).getBoolean(false);
		cobaltAssimilation = config.get("Misc.", "Cobalt Assimilation", false).getBoolean(false);
		autoBalanceEnergy = config.get("Misc.", "Balance Energy", true, "Neighbo(u)ring Solar Panels share their RF (Like old RedPower 2 ones).").getBoolean(true);
		solarPanelHeight = config.getFloat("SolarPanelHeight", "Misc.", 0.375F, 0.1F, 1.0F, "Height of a Solar Panel");
		achievementsEnabled = config.get("Misc.", "Enable Achievements", false, "Enable Thermal Tinkerer Achievements").getBoolean(false);
		thermiteFuelValue = config.get("Misc.", "Furnace fuel value of Thermite", 5000).getInt(5000);

		config.save();
		ProjectFluxGear.logger.info("Configuration load completed.");
	}

	public static Configuration getConfig() {
		return config;
	}
}
