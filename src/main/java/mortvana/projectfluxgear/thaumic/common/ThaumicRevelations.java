package mortvana.projectfluxgear.thaumic.common;

import cpw.mods.fml.common.event.*;

public class ThaumicRevelations {

	public static void preInit(FMLPreInitializationEvent event) {
		ThaumicContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		ThaumicContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		ThaumicContent.postInit();
	}
}
