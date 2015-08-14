package mortvana.projectfluxgear.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.common.MeltedDashboardCore;

public class AppliedApiologyConfig {

	public static Configuration config;

	public static void loadConfig(File file) {
		MeltedDashboardCore.logger.info("Loading Applied Apiology Config");
		config = new Configuration(file);
		config.load();


		if (config.hasChanged()) {
			config.save();
		}
		MeltedDashboardCore.logger.info("Applied Apiology Config Loaded");
	}
}
