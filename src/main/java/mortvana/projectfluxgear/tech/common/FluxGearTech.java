package mortvana.projectfluxgear.tech.common;

import cpw.mods.fml.common.event.*;

public class FluxGearTech {

	public static void preInit(FMLPreInitializationEvent event) {
		TechContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		TechContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		TechContent.postInit();
	}
}
