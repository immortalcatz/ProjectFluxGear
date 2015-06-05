package mortvana.projectfluxgear.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class ThaumicRevelationsConfig {

	public static Configuration config;

	public static void loadConfig(File file) {
		ProjectFluxGear.logger.info("Loading Thaumic Revelations Config");
		config = new Configuration(file);
		config.load();



		if (config.hasChanged()) {
			config.save();
		}
		ProjectFluxGear.logger.info("Thaumic Revelations Config Loaded");
	}
}
