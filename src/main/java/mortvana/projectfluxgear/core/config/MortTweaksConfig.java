package mortvana.projectfluxgear.core.config;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.ConfigBase;

public class MortTweaksConfig extends ConfigBase {

	public static Configuration config;

	public MortTweaksConfig(FMLPreInitializationEvent event, String location) {
		super(event, location);
	}

	@Override
	public void loadConfig(File file) {
		MeltedDashboardCore.logger.info("Loading MortTweaks Config");
		config = new Configuration(file);
		config.load();

		alterStackSizes = config.get("Gameplay Tweaks", "More stackable items", true, "Doors, Boats, Minecarts, and Cake become stackable.").getBoolean(true);

		if (config.hasChanged()) {
			config.save();
		}
		MeltedDashboardCore.logger.info("MortTweaks Config Loaded");
	}

	public static boolean cobaltHijacking;

	public static boolean alterStackSizes;

	public static boolean disableExp;

	public static boolean claySpawn;
	public static int clayVeinQuantity;
	public static int claySpawnMinY;
	public static int claySpawnMaxY;
	public static int clayVeinMinSize;
	public static int clayVeinMaxSize;

	public static boolean pluckChickens;
	public static boolean chicksDropFeathers;
	public static boolean adultsDropFeathers;
	public static int chickFeatherRarity;
	public static int adultFeatherRarity;
	public static int chickMinFeathers;
	public static int chickMaxFeathers;
	public static int adultMinFeathers;
	public static int adultMaxFeathers;

	public static boolean enderDumps;

	public static boolean decayPlanter;

	public static int batSpawnPercent;
	public static int squidSpawnPercent;
}
