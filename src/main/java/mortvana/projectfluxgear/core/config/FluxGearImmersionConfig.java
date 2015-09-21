package mortvana.projectfluxgear.core.config;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.ConfigBase;

public class FluxGearImmersionConfig extends ConfigBase {

	public static Configuration config;

	public FluxGearImmersionConfig(FMLPreInitializationEvent event, String location) {
		super(event, location);
	}

	public void loadConfig(File file) {
		MeltedDashboardCore.logger.info("Loading Flux Gear Immersion Config");
		config = new Configuration(file);
		config.load();

		if (config.hasChanged()) {
			config.save();
		}
		MeltedDashboardCore.logger.info("Flux Gear Immersion Config Loaded");
	}
}
