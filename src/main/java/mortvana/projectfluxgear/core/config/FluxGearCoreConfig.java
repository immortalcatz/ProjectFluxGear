package mortvana.projectfluxgear.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.common.MeltedDashboardCore;

public class FluxGearCoreConfig {
	public static Configuration config;

	public static void loadConfig(File file) {
		MeltedDashboardCore.logger.info("Loading MortTweaks Config");
		config = new Configuration(file);
		config.load();

		//enableAchievements = config.get("General", "Enable Project Flux Gear's Achievements", true, "Disable this if you are a derp and port this to 1.6.4 :P").getBoolean(true);

		enableWorld = config.get("Modules", "Enable World Module", true, "All non-decor world-gen and crops, required for our tech!").getBoolean(true);
		enableTech = config.get("Modules", "Enable Project FLUX Gear", true, "The heart of the mod, contains all that delicious, insane science and alchemy!").getBoolean(true);
		enableLighting = config.get("Modules", "Enable Lighting Module", true, "A stand alone floodlighting module while I make tech a thing!").getBoolean(true);
		enableDecor = config.get("Modules", "Enable Decor Module", true, "Contains pretty stones, and other shinies.").getBoolean(true);
		enableOreberries = config.get("Modules", "Enable Oreberries", true, "IMC-based Oreberries, for science, yo! Now with extra Glassmakers!").getBoolean(true);
		enableThaumic = config.get("Modules", "Enable Thaumic Revelations", true, "The soul of the mod, now with more doom and alchemy! REQUIRES THAUMCRAFT!").getBoolean(true);
		enableTinkers = config.get("Modules", "Enable Tinker's Armory", true, "The spleen of the mod, because pick-mattocks! REQUIRES TINKER'S CONSTRUCT!").getBoolean(true);
		enableApiology = config.get("Modules", "Enable Applied Apiology", true, "Not the BEES!!!!").getBoolean(true);
		enableIntegration = config.get("Modules", "Enable Integration Module", true, "Automatic crossmod integration for fun and profit!").getBoolean(true);
		enableTweaks = config.get("Modules", "Enable MortTweaks", false, "Mortvana's silly tweaks, disabled by default out of courtesy!").getBoolean(false);

		naturalAl = config.get("Registry", "Register Aluminium as NaturalAluminum", true, "Provided because I can?").getBoolean(true);
		coAssimilation = config.get("Registry", "Cobalt Assimilation", false, "Is our NaturalCobalt the same as TiC Cobalt").getBoolean(false);

		if (config.hasChanged()) {
			config.save();
		}
		MeltedDashboardCore.logger.info("MortTweaks Config Loaded");
	}

	public static boolean enableAchievements;

	public static boolean enableWorld;
	public static boolean enableTech;
	public static boolean enableLighting;
	public static boolean enableDecor;
	public static boolean enableOreberries;
	public static boolean enableThaumic;
	public static boolean enableTinkers;
	public static boolean enableApiology;
	public static boolean enableIntegration;
	public static boolean enableTweaks;

	public static boolean naturalAl;
	public static boolean coAssimilation;
}
