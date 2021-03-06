package mortvana.projectfluxgear.core.config;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.ConfigBase;

public class TinkersFoundryConfig extends ConfigBase {

	public static Configuration config;

	public TinkersFoundryConfig(FMLPreInitializationEvent event, String location) {
		super(event, location);
	}

	@Override
	public void loadConfig(File file) {
		MeltedDashboardCore.logger.info("Loading Tinker's Foundry Config");
		config = new Configuration(file);
		config.load();

		if (config.hasChanged()) {
			config.save();
		}
		MeltedDashboardCore.logger.info("Tinker's Foundry Config Loaded");
	}
}
