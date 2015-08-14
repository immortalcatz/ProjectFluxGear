package mortvana.projectfluxgear.tweaks.common;

import cpw.mods.fml.common.event.*;

public class MortTweaks {

	public static void preInit(FMLPreInitializationEvent event) {
		TweakContent.preInit();
	}

	public static void init(FMLInitializationEvent event) {
		TweakContent.init();
	}

	public static void postInit(FMLPostInitializationEvent event) {
		TweakContent.postInit();
	}
}
