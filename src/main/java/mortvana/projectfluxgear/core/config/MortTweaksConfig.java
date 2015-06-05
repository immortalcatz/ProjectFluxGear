package mortvana.projectfluxgear.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class MortTweaksConfig {

	public static Configuration config;

	public static void loadConfig(File file) {
		ProjectFluxGear.logger.info("Loading MortTweaks Config");
		config = new Configuration(file);
		config.load();

		if (config.hasChanged()) {
			config.save();
		}
		ProjectFluxGear.logger.info("MortTweaks Config Loaded");
	}
}
