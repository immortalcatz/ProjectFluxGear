package mortvana.legacy.common.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class BeeConfig {

    public static File configFolder;
    private static Configuration config;

    public static void setConfigFolderBase(File folder) {
        configFolder = new File(folder.getAbsolutePath() + "/Mortvana");
    }

    public static boolean DisableUpdateNotification;

    public static void init(Configuration config) {
        // Here we will set up the config file for the mod
        // First: Create a folder inside the config folder
        // Second: Create the actual config file
        // Note: Configs are a pain, but absolutely necessary for every mod.
        BeeConfig.config = config;
        config.load();

        // Define the mod's IDs.
        // Avoid values below 4096 for items and in the 250-450 range for blocks
    }
}
