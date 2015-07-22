package mortvana.melteddashboard.common;

import java.util.Random;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import mortvana.melteddashboard.network.FluxGearMessage;
import mortvana.melteddashboard.network.FluxGearPacketHandler_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = MeltedDashboardCore.MOD_ID, name = MeltedDashboardCore.MOD_NAME, version = MeltedDashboardCore.MOD_VERSION, dependencies = MeltedDashboardCore.MOD_DEPENDENCIES)
public class MeltedDashboardCore {
	// Here's a way to help avoid frustration:
	// Imagine Mojang only hires dyslexic and 'slow' 10-year-olds that write code only when a bird is actively trying to eat their left eye.
	// It's a reminder that no matter where you look, you're going to find something and exclaim, "What fucking moron wrote this piece of shit!?"
	//     -Skyboy026, November 9th, 2014

	// "The phase 'only Botania' in my head has become a shampoo... or something"
	// "I don't even know, but Botania is now a shampoo"
	//      -MysteriousAges, Mid-March, 2015

	public static final String MOD_ID = "MeltedDashboardCore";
	public static final String MOD_NAME = "Melted Dashboard Core";
	public static final String MOD_VERSION = "0.0.0.1";
	public static final String MOD_DEPENDENCIES = "";

	public static Random random = new Random();
	public static final int WILDCARD = Short.MAX_VALUE; //32767

	public static final Logger logger = LogManager.getLogger("Flux Gear");
	public static FluxGearPacketHandler_<FluxGearMessage> packetHandler;

	/* *=-=-=-=* Initialization Sequence *=-=-=-=* */
	/**
	 *  In the preInit step you only want to load configs, inform Forge if your mod has to be loaded after any others,
	 *  and load any framework stuff. No heavy loading or registering should occur here, because that happens during
	 *  init, as there is no guarantee stuff wont explode when they start Minecraft.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {}

	/**
	 *  This is where all the heavy loading and registering of handlers goes. Initialization of blocks and items,
	 *  TileEntity stuff, etc.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {}

	/**
	 *  Stuff to do before finalizing, like intermod interactions.
	 *  This is for things that need to wait until the game is almost done loading before initializing.
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
}
