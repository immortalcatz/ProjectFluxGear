package mortvana.projectfluxgear.core.config;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.ConfigBase;

public class MortTweaksConfig extends ConfigBase {

	public static Configuration config;

	public MortTweaksConfig(FMLPreInitializationEvent event, String location) {
		super(event, location);
	}

	public void loadConfig(File file) {
		MeltedDashboardCore.logger.info("Loading MortTweaks Config");
		config = new Configuration(file);
		config.load();

		if (config.hasChanged()) {
			config.save();
		}
		MeltedDashboardCore.logger.info("MortTweaks Config Loaded");
	}
}
