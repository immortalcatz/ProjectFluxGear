package oldcode.morttech;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;


public class MTConfig {

	public static File configFolder;
	
	private static Configuration config;

	public static void setup(String dir) {
		initMachines(new Configuration(new File(dir, "Machines")));
		//initWorld(new Configuration(new File(dir, "World")));
	}

	private static void initMachines(Configuration config) {
		try{
			config.load();
		} catch (Exception e) {
			LogHandler.log(Level.ERROR, "There was an issue with when adjusting machine settings");
			e.printStackTrace();
		} finally {
			config.save();
		}
	}

	public static void init(Configuration config) {
		MTConfig.config = config;
		config.load();

		config.save();
	}

	public static String getNetworkChannelName() {
		return "MortTech";
	}
	
	public static Configuration getConfig() {
		return config;
	}
    
	public static void setConfigFolderBase(File folder){
		configFolder = new File(folder.getAbsolutePath() + "/MortTech");
	}
}