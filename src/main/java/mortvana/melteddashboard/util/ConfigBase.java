package mortvana.melteddashboard.util;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class ConfigBase {

	public ConfigBase(FMLPreInitializationEvent event, String location) {
		loadConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + location));
	}

	public abstract void loadConfig(File location);
}
