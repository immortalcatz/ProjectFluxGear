package mortvana.projectfluxgear.oreberries.common;

import cpw.mods.fml.common.event.*;

public class Oreberries {

	public static void preInit(FMLPreInitializationEvent event) {
		OreberriesContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		OreberriesContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		OreberriesContent.postInit();
	}
}
