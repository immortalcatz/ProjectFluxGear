package moddev;

import cpw.mods.fml.common.event.*;

public abstract interface IModInitSequence {
	public abstract void preInit(FMLPreInitializationEvent event);
	public abstract void init(FMLInitializationEvent event);
	public abstract void postInit(FMLPostInitializationEvent event);
}
