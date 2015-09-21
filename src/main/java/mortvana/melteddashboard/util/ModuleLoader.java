package mortvana.melteddashboard.util;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.event.*;

public class ModuleLoader implements IEventInitialized {

	public List<ModuleBase> modules = new ArrayList<ModuleBase>();

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		for (ModuleBase module : modules) {
			if (module.isEnabled()) {
				module.preInit(event);
			}
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		for (ModuleBase module : modules) {
			if (module.isEnabled()) {
				module.init(event);
			}
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		for (ModuleBase module : modules) {
			if (module.isEnabled()) {
				module.postInit(event);
			}
		}
	}

	public void addModule(String name, boolean enabled, IConfigInitialized content, ConfigBase config) {
		modules.add(new ModuleBase(name, enabled, content, config));
	}
}
