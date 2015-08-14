package mortvana.projectfluxgear.bees.common;

import cpw.mods.fml.common.event.*;

public class AppliedApiology {

	public static void preInit(FMLPreInitializationEvent event) {
		ApiologyContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		ApiologyContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		ApiologyContent.postInit();
	}
}
