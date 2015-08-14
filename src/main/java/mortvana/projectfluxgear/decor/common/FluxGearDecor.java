package mortvana.projectfluxgear.decor.common;

import cpw.mods.fml.common.event.*;

public class FluxGearDecor {

	public static void preInit(FMLPreInitializationEvent event) {
		DecorContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		DecorContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		DecorContent.postInit();
	}
}
