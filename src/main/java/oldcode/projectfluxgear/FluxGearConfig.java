package oldcode.projectfluxgear;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

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

	public static String[] blacklistedBlocks;
	public static String[] blacklistedTiles;
	public static boolean thaumicTorch;
	public static boolean meadCardboard;

	public static boolean silenceEnvChecks;
	public static int seaLevel;
	public static boolean doTweaks;
	public static boolean useThaumicTooltips;

	public static void loadConfig (File file) {
		cobaltAssimilation = config.get("Misc", "Cobalt Assimilation", false).getBoolean(false);
		autoBalanceEnergy = config.get("Solar Panels", "Balance Energy", true, "Neighboring Solar Panels share their RF (Like old RedPower 2 ones).").getBoolean(true);
		solarPanelHeight = config.getFloat("Solar Panel Height", "Solar Panels", 0.375F, 0.1F, 1.0F, "Height of a Solar Panel");
		thermiteFuelValue = config.get("Misc", "Furnace fuel value of Thermite", 5000, "200 is 1 smelting operation, 5000 is 25, setting this to 0 disable Thermite as fuel").getInt(5000);
		mbPerBloodDonation = config.get("Blood", "Blood Donation Station milibuckets of blood per donation", 200).getInt(200);
		dmgPerBloodDonation = config.get("Blood", "Blood Donation Station damage per donation", 2).getInt(2);
		meadCardboard = config.get("Misc", "Short Mead Cardboard", false, "Do you drink Short Mead while watching SOTMead").getBoolean(false);
		useThaumicTooltips = config.get("Thaumic Revelations", "Use Indicator Tooltips", true, "Puts [PFG] before our researches").getBoolean(true);

		//recursionTorches = config.get("Timey-Wimey Torches", "Recursion Torches", false, "Allow Torches to speed each other up?").getBoolean(false); TODO: Make upgrade, as this causes infinite ticking
		thaumicTorch = config.get("Timey-Wimey Torches", "Thaumic Timey-Wimey Torches", true, "Would you like a jelly baby?").getBoolean(true);
		blacklistedBlocks = config.getStringList("Blacklisted Blocks", "Timey-Wimey Torches", new String[]{}, "Format as modid:unlocalizedname, for example Forestry:beehives or ThermalExpansion:Dynamo");
		blacklistedTiles = config.getStringList("Blacklisted TileEntities", "Timey-Wimey Torches", new String[]{}, "Format as the fully qualified class name, for example Reika.RotaryCraft.Blocks.BlockEngine or xreliquary.blocks.BlockWraithNode");
	}

	public static Configuration getConfig() {
		return config;
	}

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
	public static boolean aluminiumArc;
	public static boolean blastMechanism;
	public static boolean timeyWimeyBoxes;
	public static boolean mekCrushingHelper;

	public static boolean modifyTinkersBotania;
	public static int tinkersID_Plasteel;
	public static int tinkersID_ManaMithral;
	public static int tinkersID_Enderium;
	public static int tinkersID_Lumium;
	public static int tinkersID_Signalum;

	public static boolean tweakManaMithral;

	public static void loadExPConfig (File file) {
		ExPerditio.logger.info("Loading configuration from disk.");
		Configuration config = new Configuration(file);
		config.load();

		thaumicTorch = config.get("Timey-Wimey Torches", "Thaumic Timey-Wimey Torches", true, "Would you like a jelly baby?").getBoolean(true);
		blacklistedBlocks = config.getStringList("Blacklisted Blocks", "Timey-Wimey Torches", new String[] {}, "Format as modid:unlocalizedname, for example Forestry:beehives or ThermalExpansion:Dynamo");
		blacklistedTiles = config.getStringList("Blacklisted TileEntities", "Timey-Wimey Torches", new String[] {}, "Format as the fully qualified class name, for example Reika.RotaryCraft.Blocks.BlockEngine or xreliquary.blocks.BlockWraithNode");

		blastMechanism = config.get("Reika Tweaks", "Blast Furnace requires a Blast Mechanism", true, "Disable if using a mod which adds Titanium").getBoolean(true);


		steelReactorCasings = config.get("Big Reactors Tweaks", "Steel Reactor Casings", false, "Big Reactors Reactor Casings require Steel Ingots. Affects ONLY the casings.").getBoolean(false);
		mortvanaReactors = config.get("Big Reactors Tweaks", "Mortvana Style Reactors", true, "Big Reactors components require HSLA, Tungsten, Beryllium, Lead, and other materials to craft, we have our own Beryllium").getBoolean(true);
		fourReactorGlass = config.get("Big Reactors Tweaks", "Four Reactor Glass", false, "Big Reactors Reactor Glass recipe gives four Reactor Glass in exchange for the harder recipe.").getBoolean(false);
		glassFuelRods = config.get("Big Reactors Tweaks", "Glass Fuel Rods", false, "Big Reactors Yellorium Fuel Rods take Reactor Glass to craft.").getBoolean(false);

		tweakJABBA = config.get("JABBA Tweak", "Tweak JABBA", true, "Alter the JABBA Better Barrel recipe to be cheaper").getBoolean(true);
		enderChestJABBA = config.get("JABBA Tweaks", "EnderStorage JABBA", true, "Register Ender Storage Chest and Tanks as transdimBlock for JABBA").getBoolean(true);
		enderCapacityJABBA = config.get("JABBA Tweaks", "Ender Capacity JABBA", true, "Capacity Upgrade requires Ender Pearls, but outputs 8").getBoolean(true);

		fixExURecipes = config.get("ExtraUtilities Tweaks", "Fix ExtraUtils Recipes", true, "A version of ExU is said to have broken recipes for the Unstable Ingot Block, This fixes that.").getBoolean(true);
		nerfEnderQuarry = config.get("ExtraUtilities Tweaks", "Nerf Ender Quarry", true, "Make the Extra Utilities Ender Quarry more expensive.").getBoolean(true);
		//invarEndPump = config.get("ExtraUtilities Tweaks", "Invar Ender-thermic Pump", true, "The Ender-thermic Pump requires an Invar Pickaxe instead of an Iron Pickaxe.").getBoolean(true);

		harderActivatorRecipe = config.get("CoFH Tweaks", "Harder Autonomous Activator Recipe", true, "Make the Autonomous Activator recipe slightly harder").getBoolean(true);
		tweakManaMithral = config.get("CoFH Tweaks", "Mana Infused Tweak", true, "Mana Infused Metal is now Mana Mithral").getBoolean(true);

		harderDisassemblerRecipe = config.get("Mekanism Tweaks", "Harder Atomic Disassembler", true, "Makes the recipe for the Atomic Disassembler more difficult").getBoolean(true);
		nerfMiner = config.get("Mekanism Tweaks", "Nerf Digital Miner", true, "Make the recipe for the digital miner a decent bit harder").getBoolean(true);
		mekCrushingHelper = config.get("Mekanism Tweaks", "Additional Mekanism Crusher Recipes", true, "Allows processing of Ores, Yellorium, and Gems in the crusher.").getBoolean(true);
		timeyWimeyBoxes = config.get("Mekanism Tweaks", "Timey-Wimey Cardboard Boxes", true, "Makes Cardboard Boxes later game").getBoolean(true);

		redPowerBreakersAndDeployers = config.get("OpenMods Tweaks", "RedPower Style Breakers And Deployers", true, "OpenBlocks Block Breakers and Block Placers have the same recipes as Redpower 2's.").getBoolean(true);

		//aluminiumChisel = config.get("Chisel Tweaks", "Aluminium Chisel", true, "Chisel Blocks use Aluminium Nuggets instead of Iron Ingots to make decoration cheaper.").getBoolean(true);

		tweakSFM = config.get("Steve's Mod Tweaks", "Steve's Factory Manager AE Recipes", true, "Recipes from Steve's Factory Manager take items from Applied Energistics.").getBoolean(true);

		//invarBloodSigil = config.get("Blood Magic Tweaks", "Invar Fast Miner Sigil", true, "Sigil of the Fast Miner requires Invar tools instead of Iron ones.").getBoolean(true);

		//stoneChanging = config.get("Multi-mod Tweaks", "Stone Transmutation", true, "Allow Mystical Stone Transmutation Paste.").getBoolean(true);
		aluminiumArc = config.get("Multi-mod Tweaks", "Aluminium Arc", true, "Adds various things to the Ore Dictionary.").getBoolean(true);

		//osmiumIO = config.get("Enderio Tweaks", "OsmiumIO", false, "Machine Chassis use Osmium.").getBoolean(false);

		//oreDictPaste = config.get("Other Mod Tweaks", "OreDicted Paste", true, "Redstone Paste uses the OreDictionary.").getBoolean(true);
		//oreDictPasteOut = config.get("Other Mod Tweaks", "oreDicted Paste Output", 4, "Number of Redstone Paste crafted.").getInt(4);

		wheatToSeeds = config.get("Vanilla Tweaks", "Wheat to Seeds", true, "Ability to craft Wheat into Wheat Seeds.").getBoolean(true);
		wheatToSeedsAmount = config.get("Vanilla Tweaks", "Wheat to Seeds Amount", 4, "Amount of Wheat Seeds from crafting Wheat to Wheat Seeds.").getInt(4);
		//craftableSaddle = config.get("Vanilla Tweaks", "Craftable Saddles", true, "Allows Crafting of Saddles.").getBoolean(true);

		meadCardboard = config.get("Other Tweaks", "Short Mead Cardboard", false, "Do you drink Short Mead while watching SOTMead").getBoolean(false);

		modifyTinkersBotania = config.get("Tinker's Tweaks", "Modify Tinker's-Botania", true, "Extra TiC's Botania metals get the writable modifier").getBoolean(true);
		tinkersID_Plasteel = config.get("Tinker's Materials", "Plasteel Material ID", 700, "Set to -1 to disable").getInt(700);
		tinkersID_ManaMithral = config.get("Tinker's Materials", "Mana Mithral Material ID", 701, "Set to -1 to disable").getInt(701);
		tinkersID_Enderium = config.get("Tinker's Materials", "Enderium Material ID", 702, "Set to -1 to disable").getInt(702);
		tinkersID_Lumium = config.get("Tinker's Materials", "Lumium Material ID", 703, "Set to -1 to disable").getInt(703);
		tinkersID_Signalum = config.get("Tinker's Materials", "Signalum Material ID", 704, "Set to -1 to disable").getInt(704);

		if (config.hasChanged()){
			config.save();
		}
		ExPerditio.logger.info("Configuration load completed.");
	}
}
