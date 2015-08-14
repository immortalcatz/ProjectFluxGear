package mortvana.projectfluxgear.lighting.common;

import cpw.mods.fml.common.event.*;

public class FluxGearLighting {

	public static void preInit(FMLPreInitializationEvent event) {
		LightingContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		LightingContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		LightingContent.postInit();
	}
}
