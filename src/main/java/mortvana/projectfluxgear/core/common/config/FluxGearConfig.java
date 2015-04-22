package mortvana.projectfluxgear.core.common.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class FluxGearConfig {

	public static Configuration config;

	public static boolean enableDebug;
	public static boolean cobaltAssimilation;
	public static boolean autoBalanceEnergy;
	public static float solarPanelHeight;
	public static boolean achievementsEnabled;
	public static int thermiteFuelValue;
	public static int mbPerBloodDonation;
	public static int dmgPerBloodDonation;
	public static boolean regen;

	public static boolean recursionTorches;
	public static String[] blacklistedBlocks;
	public static String[] blacklistedTiles;
	public static boolean thaumicTorch;
	public static boolean meadCardboard;

	public static boolean silenceEnvChecks;
	public static int seaLevel;
	public static boolean doTweaks;
	public static boolean useThaumicTooltips;

	public static void loadConfig (File file) {
		ProjectFluxGear.logger.info("Loading configuration from disk.");
		config = new Configuration(file);
		config.load();

		doTweaks = config.get("Tweaks", "Enable Tweaks", false, "Off by default, these tweaks are individually toggleable.").getBoolean(false);
		enableDebug = config.get("DebugHelpers", "Enable Debug", false).getBoolean(false);
		cobaltAssimilation = config.get("Misc", "Cobalt Assimilation", false).getBoolean(false);
		autoBalanceEnergy = config.get("Solar Panels", "Balance Energy", true, "Neighboring Solar Panels share their RF (Like old RedPower 2 ones).").getBoolean(true);
		solarPanelHeight = config.getFloat("Solar Panel Height", "Solar Panels", 0.375F, 0.1F, 1.0F, "Height of a Solar Panel");
		achievementsEnabled = config.get("Misc", "Enable Achievements", false, "Enable Project Flux Gear Achievements").getBoolean(false);
		thermiteFuelValue = config.get("Misc", "Furnace fuel value of Thermite", 5000, "200 is 1 smelting operation, 5000 is 25, setting this to 0 disable Thermite as fuel").getInt(5000);
		mbPerBloodDonation = config.get("Blood", "Blood Donation Station milibuckets of blood per donation", 200).getInt(200);
		dmgPerBloodDonation = config.get("Blood", "Blood Donation Station damage per donation", 2).getInt(2);
		meadCardboard = config.get("Misc", "Short Mead Cardboard", false, "Do you drink Short Mead while watching SOTMead").getBoolean(false);
		useThaumicTooltips = config.get("Thaumic Revelations", "Use Indicator Tooltips", true, "Puts [PFG] before our researches").getBoolean(true);

		//recursionTorches = config.get("Timey-Wimey Torches", "Recursion Torches", false, "Allow Torches to speed each other up?").getBoolean(false); TODO: Make upgrade, as this causes infinite ticking
		thaumicTorch = config.get("Timey-Wimey Torches", "Thaumic Timey-Wimey Torches", true, "Would you like a jelly baby?").getBoolean(true);
		blacklistedBlocks = config.getStringList("Blacklisted Blocks", "Timey-Wimey Torches", new String[]{}, "Format as modid:unlocalizedname, for example Forestry:beehives or ThermalExpansion:Dynamo");
		blacklistedTiles = config.getStringList("Blacklisted TileEntities", "Timey-Wimey Torches", new String[]{}, "Format as the fully qualified class name, for example Reika.RotaryCraft.Blocks.BlockEngine or xreliquary.blocks.BlockWraithNode");

		silenceEnvChecks = config.get("Environment", "unsupportedLogging", false).getBoolean(false);
		seaLevel = config.get("Misc", "Sea Level", 64, "Offered for mods that alter world heights, looking at you TFC").getInt(64);

		if (config.hasChanged())
			config.save();
		ProjectFluxGear.logger.info("Configuration load completed.");
	}

	public static Configuration getConfig() {
		return config;
	}
}
