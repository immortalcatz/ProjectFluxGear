package mortvana.melteddashboard.util;

import cpw.mods.fml.common.event.*;

public interface IEventInitialized {
	public void preInit(FMLPreInitializationEvent event);
	public void init(FMLInitializationEvent event);
	public void postInit(FMLPostInitializationEvent event);
}
