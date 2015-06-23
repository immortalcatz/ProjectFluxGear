package mortvana.projectfluxgear.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class FluxGearWorldConfig {

	public static Configuration config;

	public static void loadConfig(File file) {
		ProjectFluxGear.logger.info("Loading Flux Gear World Config");
		config = new Configuration(file);
		config.load();

		debugWorldgen = config.get("Debug", "Enable Debug Worldgen", false, "For testing only, as it multiplies oregen drastically").getBoolean(false);

		naturalAl = config.get("Registry", "Register Aluminium as NaturalAluminum", true, "Provided because I can?").getBoolean(true);
		coAssimilation = config.get("Registry", "Cobalt Assimilation", false, "Is our NaturalCobalt the same as TiC Cobalt").getBoolean(false);

		seaLevel = config.get("World", "World Sea Level", 63, "Offered for mods that alter world heights, looking at you TFC.").getInt(63);


		if (config.hasChanged()) {
			config.save();
		}
		ProjectFluxGear.logger.info("Flux Gear World Config Loaded");
	}



	public static boolean debugWorldgen;

	public static boolean naturalAl;
	public static boolean coAssimilation;

	public static int seaLevel;
}
