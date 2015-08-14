package mortvana.projectfluxgear.integration.common;

import cpw.mods.fml.common.event.*;

public class FluxGearIntegration {

	public static void preInit(FMLPreInitializationEvent event) {
		IntegrationContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		IntegrationContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		IntegrationContent.postInit();
	}
}
