package mortvana.melteddashboard.util;

import cpw.mods.fml.common.event.*;

public class ModuleBase implements IEventInitialized {
	public String name;
	public boolean enabled;
	public IConfigInitialized content;
	public ConfigBase config;

	public ModuleBase(String name, boolean enabled, IConfigInitialized content, ConfigBase config) {
		this.name = name;
		this.enabled = enabled;
		this.content = content;
		this.config = config;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		content.preInit(config);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		content.init(config);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		content.postInit(config);
	}

	public boolean isEnabled() {
		return enabled;
	}
}
