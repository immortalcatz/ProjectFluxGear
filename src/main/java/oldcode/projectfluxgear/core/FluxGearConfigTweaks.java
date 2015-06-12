package oldcode.projectfluxgear.core;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class FluxGearConfigTweaks {

	public static boolean steelReactorCasings;
	public static boolean glassFuelRods;
	public static boolean fourReactorGlass;
	public static boolean mortvanaReactors;
	public static boolean redPowerBreakersAndDeployers;
	public static boolean tweakSFM;
	public static boolean tweakJABBA;
	public static boolean fixExURecipes;
	public static boolean nerfEnderQuarry;
	public static boolean harderActivatorRecipe;
	public static boolean harderDisassemblerRecipe;
	public static boolean nerfMiner;

	public static boolean enderChestJABBA;
	public static boolean aluminiumChisel;
	public static boolean osmiumIO;
	public static boolean wheatToSeeds;
	public static int wheatToSeedsAmount;
	public static boolean oreDictPaste;
	public static int oreDictPasteOut;
	public static boolean craftableSaddle;
	public static boolean enderCapacityJABBA;
	public static boolean invarBloodSigil;
	public static boolean invarEndPump;
	public static boolean stoneChanging;
	public static boolean oreDictStuff;
	public static boolean timeyWimeyBoxes;
	public static boolean mekCrushingHelper;
	public static boolean blastMechanism;

	public static void loadConfig (File file) {
		ProjectFluxGear.logger.info("Loading configuration from disk.");
		Configuration config = new Configuration(file);
		config.load();

		steelReactorCasings = config.get("Big Reactors Tweaks", "Steel Reactor Casings", false, "Big Reactors Reactor Casings require Steel Ingots. Affects ONLY the casings.").getBoolean(false);
		mortvanaReactors = config.get("Big Reactors Tweaks", "Mortvana Style Reactors", true, "Big Reactors components require HSLA, Tungsten, Beryllium, Lead, and other materials to craft").getBoolean(true);
		fourReactorGlass = config.get("Big Reactors Tweaks", "Four Reactor Glass", false, "Big Reactors Reactor Glass recipe gives four Reactor Glass in exchange for the harder recipe.").getBoolean(false);
		glassFuelRods = config.get("Big Reactors Tweaks", "Glass Fuel Rods", false, "Big Reactors Yellorium Fuel Rods take Reactor Glass to craft.").getBoolean(false);

		//tweakJABBA = config.get("JABBA Tweak", "Tweak JABBA", true, "Alter the JABBA Better Barrel recipe to be cheaper").getBoolean(true);
		enderChestJABBA = config.get("JABBA Tweaks", "EnderStorage JABBA", true, "Register Ender Storage Chest and Tanks as transdimBlock for JABBA").getBoolean(true);
		enderCapacityJABBA = config.get("JABBA Tweaks", "Ender Capacity JABBA", true, "Capacity Upgrade requires Ender Pearls, but outputs 8").getBoolean(true);

		fixExURecipes = config.get("ExtraUtilities Tweaks", "Fix ExtraUtils Recipes", true, "A version of ExU is said to have broken recipes for the Unstable Ingot Block, This fixes that.").getBoolean(true);
		nerfEnderQuarry = config.get("ExtraUtilities Tweaks", "Nerf Ender Quarry", true, "Make the Extra Utilities Ender Quarry more expensive.").getBoolean(true);
		//invarEndPump = config.get("ExtraUtilities Tweaks", "Invar Ender-thermic Pump", true, "The Ender-thermic Pump requires an Invar Pickaxe instead of an Iron Pickaxe.").getBoolean(true);

		harderActivatorRecipe = config.get("CoFH Tweaks", "Harder Autonomous Activator Recipe", true, "Make the Autonomous Activator recipe slightly harder").getBoolean(true);

		harderDisassemblerRecipe = config.get("Mekanism Tweaks", "Harder Atomic Disassembler", true, "Makes the recipe for the Atomic Disassembler more difficult").getBoolean(true);
		nerfMiner = config.get("Mekanism Tweaks", "Nerf Digital Miner", true, "Make the recipe for the digital miner a decent bit harder").getBoolean(true);
		timeyWimeyBoxes = config.get("Mekanism Tweaks", "Timey-Wimey Cardboard Boxes", true, "Makes Cardboard Boxes later game").getBoolean(true);
		mekCrushingHelper = config.get("Mekanism Tweaks", "Additional Mekanism Crusher Recipes", true, "Allows processing of Ores, Yellorium, and Gems in the crusher.").getBoolean(true);

		redPowerBreakersAndDeployers = config.get("OpenMods Tweaks", "RedPower Style Breakers And Deployers", true, "OpenBlocks Block Breakers and Block Placers have the same recipes as Redpower 2's.").getBoolean(true);

		//aluminiumChisel = config.get("Chisel Tweaks", "Aluminium Chisel", true, "Chisel Blocks use Aluminium Nuggets instead of Iron Ingots to make decoration cheaper.").getBoolean(true);

		tweakSFM = config.get("Steve's Mod Tweaks", "Steve\'s Factory Manager AE Recipes", true, "Recipes from Steve's Factory Manager take items from Applied Energistics.").getBoolean(true);

		//invarBloodSigil = config.get("Blood Magic Tweaks", "Invar Fast Miner Sigil", true, "Sigil of the Fast Miner requires Invar tools instead of Iron ones.").getBoolean(true);

		//stoneChanging = config.get("Multi-mod Tweaks", "Stone Transmutation", true, "Allow Mystical Stone Transmutation Paste.").getBoolean(true);
		//oreDictStuff = config.get("Multi-mod Tweaks", "OreDict Stuff", true, "Adds various things to the Ore Dictionary.").getBoolean(true);

		//osmiumIO = config.get("Enderio Tweaks", "OsmiumIO", false, "Machine Chassis use Osmium.").getBoolean(false);

		//oreDictPaste = config.get("Other Mod Tweaks", "OreDicted Paste", true, "Redstone Paste uses the OreDictionary.").getBoolean(true);
		//oreDictPasteOut = config.get("Other Mod Tweaks", "oreDicted Paste Output", 4, "Number of Redstone Paste crafted.").getInt(4);

		//wheatToSeeds = config.get("Vanilla Tweaks", "Wheat to Seeds", true, "Ability to craft Wheat into Wheat Seeds.").getBoolean(true);
		//wheatToSeedsAmount = config.get("Vanilla Tweaks", "Wheat to Seeds Amount", 4, "Amount of Wheat Seeds from crafting Wheat to Wheat Seeds.").getInt(4);
		//craftableSaddle = config.get("Vanilla Tweaks", "Craftable Saddles", true, "Allows Crafting of Saddles.").getBoolean(true);

		blastMechanism = config.get("Reika Tweaks", "Blast Furnace requires a Blast Mechanism", false, "Disable if using a mod which adds Titanium, AND DO NOT REPORT RECIPE BUGS TO REIKA").getBoolean(false);

		config.save();
		ProjectFluxGear.logger.info("Configuration load completed.");
	}
}
