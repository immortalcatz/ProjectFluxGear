package mortvana.projectfluxgear.tinkers.common;

import cpw.mods.fml.common.event.*;

public class TinkersArmory {

	public static void preInit(FMLPreInitializationEvent event) {
		TinkersContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		TinkersContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		TinkersContent.postInit();
	}
}
