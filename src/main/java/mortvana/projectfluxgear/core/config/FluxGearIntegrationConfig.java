package mortvana.projectfluxgear.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.common.MeltedDashboardCore;

public class FluxGearIntegrationConfig {

	public static Configuration config;

	public static void loadConfig(File file) {
		MeltedDashboardCore.logger.info("Loading Flux Gear Integration Config");
		config = new Configuration(file);
		config.load();


		if (config.hasChanged()) {
			config.save();
		}
		MeltedDashboardCore.logger.info("Flux Gear World Integration Loaded");
	}
}
