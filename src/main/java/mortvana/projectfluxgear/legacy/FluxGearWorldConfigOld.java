package mortvana.projectfluxgear.legacy;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.common.ProjectFluxGear;

public class FluxGearWorldConfigOld {

	public static Configuration configuration;
	public static FluxGearWorldConfigOld config;
	public static File configFolder;

	public static void loadConfiguration(File mainConfigFolder) {
		ProjectFluxGear.logger.info("Loading configuration from disk.");
		try {
			configFolder = new File(mainConfigFolder.getCanonicalPath(), "Mortvana");
			if (!configFolder.exists())
				configFolder.mkdirs();
		} catch (IOException e) {
			ProjectFluxGear.logger.error("Error getting/creating Project Flux Gear configuration directory: " + e.getMessage());
		}
		configuration = new Configuration(new File(configFolder, ("ProjectFluxGear-World.cfg")));
		configuration.load();
	}


	public static boolean generates(String name) {
		boolean b = config.configuration.get("Generators", name, true).getBoolean(true);
		config.saveChanges();
		return b;
	}

	private static boolean getBoolean(String categories, String key, boolean defaultValue) {
		boolean b = config.configuration.get(categories, key, defaultValue).getBoolean(defaultValue);
		config.saveChanges();
		return b;
	}

	private static String getName(String categories, String key, String defaultValue) {
		String string = config.configuration.get(categories, key, defaultValue).toString();
		config.saveChanges();
		return string;
	}

	/**
	 * Gets ore generation dimension information
	 *
	 * @param oreTag
	 * Ore configuration tag, this will be use to create sub
	 * category under generation category.
	 * @param defaultDimensions
	 * Default dimensions information for ore generation
	 * @return
	 * dimensions ore generation information from configuration file
	 */
	public static String getOreGenerationDimInfo(String oreTag, String defaultDimensions) {
		String dimInfo = config.configuration.get("Generators." + oreTag, "dimensions", defaultDimensions).getString();
		return dimInfo;
	}

	/**
	 * Gets ore generation information from configuration file.
	 *
	 * @param oreTag
	 * Ore configuration tag, this will be use to create sub
	 * category under generation category.
	 * @param defaultInfo
	 * Integer array with the default info.
	 * @return
	 * Integer array with generation info from the configuration file
	 * with the following: <br />
	 * 0: Veins per Chunk, 1: Ores per vein, 2: Minimum Y Level, <br/>
	 * 3: Maximum Y Level, 4: Chunk chance.
	 */
	public static int[] getOreGenerationInformation(String oreTag, int[] defaultInfo) {
		int veinsPerChunk = config.configuration.get("Generators." + oreTag, "Veins per Chunk", defaultInfo[0]).getInt(defaultInfo[0]);
		int oresPreVein = config.configuration.get("Generators." + oreTag, "Ores per Vein", defaultInfo[1]).getInt(defaultInfo[1]);
		int minYLevel = config.configuration.get("Generators." + oreTag, "Min Y Level", defaultInfo[2]).getInt(defaultInfo[2]);
		int maxYLevel = config.configuration.get("Generators." + oreTag, "Max Y Level", defaultInfo[3]).getInt(defaultInfo[3]);
		int chunkChance = config.configuration.get("Generators." + oreTag, "Chance per Chunk", defaultInfo[4]).getInt(defaultInfo[4]);
		int[] generationInfo = { veinsPerChunk, oresPreVein, minYLevel, maxYLevel, chunkChance };
		return generationInfo;
	}

	public static boolean itemEnabled(String itemName) {
		boolean b = config.configuration.get("Items", itemName, true).getBoolean(true);
		config.saveChanges();
		return b;
	}

	public static boolean regen() {
		return config.getBoolean("Retrogen", "regenOres", false);
	}

	public static String regenKey() {
		return config.getName("Retrogen", "regenKey", "PO-TA-TO");
	}

	private static void saveChanges() {
		if (config.configuration.hasChanged()) {
			config.configuration.save();
		}
	}

	public static boolean setEnabled(String setName) {
		boolean b = config.configuration.get("Sets", setName, true).getBoolean(true);
		config.saveChanges();
		return b;
	}

	public static void setFile(File file) {
		config.configuration = new Configuration(file);
		config.configuration.load();
		config.saveChanges();
	}
}