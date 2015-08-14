package mortvana.projectfluxgear.world.common;

import cpw.mods.fml.common.event.*;

import mortvana.melteddashboard.common.MeltedDashboardConfig;

public class FluxGearWorld {

	public static boolean debugWorldgen = MeltedDashboardConfig.debugWorldgen;

	public static void preInit(FMLPreInitializationEvent event) {
		WorldContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		WorldContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		WorldContent.postInit();
	}
}
