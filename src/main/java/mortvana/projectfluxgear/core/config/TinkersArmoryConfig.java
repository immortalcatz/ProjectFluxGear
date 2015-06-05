package mortvana.projectfluxgear.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class TinkersArmoryConfig {

	public static Configuration config;

	public static void loadConfig(File file) {
		ProjectFluxGear.logger.info("Loading Tinker's Armory Config");
		config = new Configuration(file);
		config.load();

		if (config.hasChanged()) {
			config.save();
		}
		ProjectFluxGear.logger.info("Tinker's Armory Config Loaded");
	}
}
