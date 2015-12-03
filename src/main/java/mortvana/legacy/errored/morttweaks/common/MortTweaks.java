package mortvana.legacy.errored.morttweaks.common;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.helpers.LoadedHelper;
import mortvana.melteddashboard.util.helpers.RegistryHelper;

import mortvana.legacy.clean.morttweaks.block.BlockTweakedPortal;
import mortvana.legacy.clean.morttweaks.item.ItemTweakedArmor;
import mortvana.legacy.clean.morttweaks.item.ItemTweakedFlesh;
import mortvana.legacy.clean.morttweaks.util.TweakedFoodStats;
import mortvana.legacy.dependent.firstdegree.morttweaks.potion.TweakedPoisonStatus;
import mortvana.legacy.errored.morttweaks.block.BlockTweakedFire;
import mortvana.legacy.errored.morttweaks.block.BlockTweakedTNT;
import mortvana.legacy.clean.morttweaks.block.BlockTweakedSugarCane;
import mortvana.legacy.errored.morttweaks.entity.EntityTweakedZombie;
import mortvana.legacy.clean.morttweaks.item.ItemTweakedStew;
import mortvana.legacy.errored.morttweaks.util.*;

@Mod(modid = "MortTweaks", name = "MortTweaks", version = "1.0.0.0", dependencies = "after:MineFactoryReloaded")
public class MortTweaks {
	@SidedProxy(clientSide = "mortvana.tweaks.client.ClientProxy", serverSide = "mortvana.legacy.errored.morttweaks.common.CommonProxy")
	public static CommonProxy proxy;

	/* TODO: These things:
	 *
	 * Good:
	 * //Kill all achievements
	 * 2x2 trees
	 * Right-click crops to harvest
	 *
	 * Decent:
	 * Skeleton strafing
	 * Skeleton Panic AI change to pulling out sword
	 * Zombies with bows, skeletons with swords
	 * Activate jukebox with redstone
	 */
	Random random = new Random();

	public static List<ArmorOverrideEntry> armorOverrides = new ArrayList<ArmorOverrideEntry>(20);

	public static boolean overrideHungerHud = false; //Should be false until set

	//Crosshair
	public static boolean[] crosshairBlacklist = new boolean[32000];
	public static boolean[] rangedCrosshair = new boolean[32000];

	//Hunger
	public static boolean disableHunger = false;
	public static int maxHungerHeal = 8;
	public static boolean overrideArmorHud = true;

	public static boolean tweakHunger = false;
	public static int maxFoodLevel = 20;
	public static int regenerationThreshold = 18;
	public static boolean alwaysHungerRegenerate = true;
	public static float foodExhaustion = 1.0f;

	//Gameplay
	public static boolean addNametagRecipe = true;
	public static boolean disableExp = false;
	public static boolean nastyFlesh = true;
	public static boolean alwaysDropExp = false;
	public static boolean nerfFoodStackSize = false;
	public static boolean stackableSoup = true;
	public static int sugarCaneHeight = 3;
	public static int poisonTime = 50;

	//Mobs
	public static boolean animalBones = true;
	public static boolean leather = true;
	public static boolean creeperBehavior = true;
	public static boolean mounts = true;
	public static boolean endermenDontPickUpBlocks = true;
	public static boolean spawnZombieReinforcements = false;
	public static boolean keepBabyZombies = false;
	public static boolean disableZombieFire = true;

	//Classic
	public static boolean potentFire = true;
	public static boolean feathers = true;
	public static boolean revertTNT = false;
	public static boolean fleshToFeathers = false;
	public static boolean changeArmorCalculations = false;
	public static boolean overridePortal = false;

	//Render
	public static boolean fancyGrass = true;
	public static boolean disableExpBar = false;
	public static boolean removeVoidParticles = true;
	public static boolean removeVoidFog = true;

	static {
		armorOverrides.add(new ArmorOverrideEntry(Items.leather_helmet, "minecraft:leather_helmet"));
		armorOverrides.add(new ArmorOverrideEntry(Items.leather_chestplate, "minecraft:leather_chestplate"));
		armorOverrides.add(new ArmorOverrideEntry(Items.leather_leggings, "minecraft:leather_leggings"));
		armorOverrides.add(new ArmorOverrideEntry(Items.leather_boots, "minecraft:leather_boots"));
		armorOverrides.add(new ArmorOverrideEntry(Items.chainmail_helmet, "minecraft:chainmail_helmet"));
		armorOverrides.add(new ArmorOverrideEntry(Items.chainmail_chestplate, "minecraft:chainmail_chestplate"));
		armorOverrides.add(new ArmorOverrideEntry(Items.chainmail_leggings, "minecraft:chainmail_leggings"));
		armorOverrides.add(new ArmorOverrideEntry(Items.chainmail_boots, "minecraft:chainmail_boots"));
		armorOverrides.add(new ArmorOverrideEntry(Items.iron_helmet, "minecraft:iron_helmet"));
		armorOverrides.add(new ArmorOverrideEntry(Items.iron_chestplate, "minecraft:iron_chestplate"));
		armorOverrides.add(new ArmorOverrideEntry(Items.iron_leggings, "minecraft:iron_leggings"));
		armorOverrides.add(new ArmorOverrideEntry(Items.iron_boots, "minecraft:iron_boots"));
		armorOverrides.add(new ArmorOverrideEntry(Items.diamond_helmet, "minecraft:diamond_helmet"));
		armorOverrides.add(new ArmorOverrideEntry(Items.diamond_chestplate, "minecraft:diamond_chestplate"));
		armorOverrides.add(new ArmorOverrideEntry(Items.diamond_leggings, "minecraft:diamond_leggings"));
		armorOverrides.add(new ArmorOverrideEntry(Items.diamond_boots, "minecraft:diamond_boots"));
		armorOverrides.add(new ArmorOverrideEntry(Items.golden_helmet, "minecraft:golden_helmet"));
		armorOverrides.add(new ArmorOverrideEntry(Items.golden_chestplate, "minecraft:golden_chestplate"));
		armorOverrides.add(new ArmorOverrideEntry(Items.golden_leggings, "minecraft:golden_leggings"));
		armorOverrides.add(new ArmorOverrideEntry(Items.golden_boots, "minecraft:golden_boots"));
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		File file = event.getSuggestedConfigurationFile();
		Configuration config = new Configuration(file);
		disableHunger = config.get("Hunger Disable", "Disable Hunger", false, "Completely disables by locking it at 7 hunger and redirects food to HP").getBoolean(false);
		maxHungerHeal = config.get("Hunger Disable", "Maximum food heal amount", 8, "Only works if hunger is disabled").getInt(8);

		tweakHunger = config.get("Hunger Tweak", "Tweak Hunger", true, "Incompatible with disabled hunger, allows for other tweaks").getBoolean(true);
		maxFoodLevel = config.get("Hunger Tweak", "Maximum food level", 20, "Vanilla default: 20").getInt(20);
		regenerationThreshold = config.get("Hunger Tweak", "Hunger regeneration threshold", 18, "Incompatible with constant regeneration. Vanilla default: 18").getInt(18);
		alwaysHungerRegenerate = config.get("Hunger Tweak", "Always regenerate from hunger", true, "Player always regenerates from hunger. Goes slower at lower food levels").getBoolean(true);
		foodExhaustion = (float) config.get("Hunger Tweak", "Food Depletion", 0, "How much hunger should be drained per heart healed. Vanilla default: 3.0").getDouble(0);

		overrideArmorHud = config.get("Render Tweak", "Rearrange Armor HUD", true, "Moves the armor HUD if hunger or exp is disabled").getBoolean(true);
		fancyGrass = config.get("Render Tweak", "Force fancy grass", true).getBoolean(true);
	    /*removeVoidParticles = config.get("Render Tweak", "Remove Void Particles", true).getBoolean(true);
        removeVoidFog = config.get("Render Tweak", "Remove Void Fog", true).getBoolean(true);*/

		addNametagRecipe = config.get("Gameplay Tweak", "Add Nametag Recipe", true, "String, slimeball, paper").getBoolean(true);
		animalBones = config.get("Gameplay Tweak", "Animal Bones", true, "Passive mobs drop bones on peaceful mode").getBoolean(true);
		feathers = config.get("Gameplay Tweak", "More Feathers", true, "Chickens drop many more feathers").getBoolean(true);
		leather = config.get("Gameplay Tweak", "Minimum Leather", true, "Cows drop at least one leather").getBoolean(true);
		nastyFlesh = config.get("Gameplay Tweak", "Nasty Flesh", true, "Rotten flesh turns absolutely nasty").getBoolean(true);
		nerfFoodStackSize = config.get("Gameplay Tweak", "Food Shrinkage", false, "Reduces maximum stack size on many foods").getBoolean(false);
		stackableSoup = config.get("Gameplay Tweak", "Stackable Soup", true, "Overrides mushroom soup to make it stackable").getBoolean(true);
		alwaysDropExp = config.get("Gameplay Tweak", "Everywhere Exp", false, "Experience orbs drop from more than player kills").getBoolean(false);
		sugarCaneHeight = config.get("Gameplay Tweak", "Sugar Cane Height", 3).getInt(3);
		poisonTime = config.get("Gameplay Tweak", "Poison Time", 50, "Ticks between poison damage. Vanilla Default: 25").getInt(50);
		//animalBones

		creeperBehavior = config.get("Mob Behavior", "Slightly Smarter Creepers", true, "Creepers change targets towards the closer aggressor when hit").getBoolean(true);
		mounts = config.get("Mob Behavior", "Jockeyfication", true, "Certain mobs may spawn with extra mobs attached").getBoolean(true);
		endermenDontPickUpBlocks = config.get("Mob Behavior", "Ender Nender", true, "Prevent endermen from moving blocks around").getBoolean(true);
		spawnZombieReinforcements = !config.get("Mob Behavior", "Disable Zombie Reinforcements", true, "Overrides the vanilla zombie entity").getBoolean(true);
		keepBabyZombies = !config.get("Mob Behavior", "Disable Zombie Babies", true, "Overrides the vanilla zombie entity").getBoolean(true);
		disableZombieFire = config.get("Mob Behavior", "Disable Zombie Fire", true, "Overrides the vanilla zombie entity").getBoolean(true);
		//nicerWitches = config.get("Mob Behavior", "Nicer Witches", true, "Witches throw less powerful potions. Overrides the vanilla witch entity").getBoolean(true);

		changeArmorCalculations = config.get("Classic Mechanics", "Classic Armor", false, "Overrides vanilla armor to give alpha armor feel").getBoolean(false);
		revertTNT = config.get("Classic Mechanics", "TNT Puncher", true, "TNT can only be harvested with shears or silk touch").getBoolean(true);
		potentFire = config.get("Classic Mechanics", "Hungry Fire", true, "Fire will burn down entire forests").getBoolean(true);
		disableExpBar = config.get("Classic Mechanics", "Disable XP bar", false).getBoolean(false);
		disableExp = config.get("Classic Mechanics", "Disable XP orbs", false).getBoolean(false);
		fleshToFeathers = config.get("Classic Mechanics", "Flesh to Feathers", false, "Zombies drop feathers instead of rotten flesh").getBoolean(false);
		overridePortal = config.get("Classic Mechanics", "Portal Override", false).getBoolean(false);

		//int[] blacklist = config.get("Crosshair Tweaks", "Blacklist", new int[] {Items.map.itemID}, "Add block or item IDs that should not show the crosshair").getIntList();
		//int[] rangeTarget = config.get("Crosshair Tweaks", "Crosshair", new int[] {Items.bow.itemID, Items.snowball.itemID, Items.egg.itemID, Items.fishing_rod.itemID, Items.ender_pearl.itemID}, "Add block or item IDs that should render a target crosshair").getIntList();

		/*for (int i = 0; i < blacklist.length; i++)
			MortTweaks.crosshairBlacklist[blacklist[i]] = true;
		for (int i = 0; i < rangeTarget.length; i++)
			MortTweaks.rangedCrosshair[rangeTarget[i]] = true;*/
		config.save();

		GameRegistry.registerPlayerTracker(new TweakPlayerTracker()); //TODO: 1.7.10 Version...
		MinecraftForge.EVENT_BUS.register(proxy);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();

		if (potentFire) {
			Blocks.fire.setFireInfo(Blocks.planks, 25, 20);
			Blocks.fire.setFireInfo(Blocks.double_wooden_slab, 25, 20);
			Blocks.fire.setFireInfo(Blocks.wooden_slab, 25, 20);
			Blocks.fire.setFireInfo(Blocks.fence, 25, 20);
			Blocks.fire.setFireInfo(Blocks.oak_stairs, 25, 20);
			Blocks.fire.setFireInfo(Blocks.spruce_stairs, 25, 20);
			Blocks.fire.setFireInfo(Blocks.birch_stairs, 25, 20);
			Blocks.fire.setFireInfo(Blocks.jungle_stairs, 25, 20);
			Blocks.fire.setFireInfo(Blocks.acacia_stairs, 25, 20);
			Blocks.fire.setFireInfo(Blocks.dark_oak_stairs, 25, 20);
			Blocks.fire.setFireInfo(Blocks.log, 25, 5);
			Blocks.fire.setFireInfo(Blocks.log2, 25, 5);
			Blocks.fire.setFireInfo(Blocks.leaves, 90, 60);
			Blocks.fire.setFireInfo(Blocks.leaves2, 90, 60);
			Blocks.fire.setFireInfo(Blocks.bookshelf, 90, 20);
			Blocks.fire.setFireInfo(Blocks.tnt, 45, 100);
			Blocks.fire.setFireInfo(Blocks.tallgrass, 180, 100);
			Blocks.fire.setFireInfo(Blocks.wool, 90, 60);
			Blocks.fire.setFireInfo(Blocks.vine, 45, 100);
			Blocks.fire.setFireInfo(Blocks.coal_block, 25, 5);
			Blocks.fire.setFireInfo(Blocks.hay_block, 180, 20);
		}

		if (addNametagRecipe) {
			OreDictionary.registerOre("slimeball", new ItemStack(Items.slime_ball));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.name_tag), "P~ ", "~O ", "  ~", '~', Items.string, 'P', Items.paper, 'O', "slimeball"));
		}

		if (endermenDontPickUpBlocks) {
			EntityEnderman.carriableBlocks = new boolean[Block.blocksList.length]; //TODO: 1.7.10 Version...
		}

		if (changeArmorCalculations) {
			ItemArmor armor;
			for (ArmorOverrideEntry entry : armorOverrides) {
				armor = (ItemArmor) new ItemTweakedArmor(entry.material, entry.renderIndex, entry.armorType).setUnlocalizedName(entry.unlocalizedName).setTextureName(entry.textureName);
				RegistryHelper.overwriteEntry(Item.itemRegistry, entry.id, armor);
			}

				/*Items.leather_helmet = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.CLOTH, 0, 0).setUnlocalizedName("helmetCloth").setTextureName("leather_helmet");
				Items.leather_chestplate = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.CLOTH, 0, 1).setUnlocalizedName("chestplateCloth").setTextureName("leather_chestplate");
				Items.leather_leggings = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.CLOTH, 0, 2).setUnlocalizedName("leggingsCloth").setTextureName("leather_leggings");
				Items.leather_boots = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.CLOTH, 0, 3).setUnlocalizedName("bootsCloth").setTextureName("leather_boots");
				Items.chainmail_helmet = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.CHAIN, 1, 0).setUnlocalizedName("helmetChain").setTextureName("chainmail_helmet");
				Items.chainmail_chestplate = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.CHAIN, 1, 1).setUnlocalizedName("chestplateChain").setTextureName("chainmail_chestplate");
				Items.chainmail_leggings = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.CHAIN, 1, 2).setUnlocalizedName("leggingsChain").setTextureName("chainmail_leggings");
				Items.chainmail_boots = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.CHAIN, 1, 3).setUnlocalizedName("bootsChain").setTextureName("chainmail_boots");
				Items.iron_helmet = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.IRON, 2, 0).setUnlocalizedName("helmetIron").setTextureName("iron_helmet");
				Items.iron_chestplate = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.IRON, 2, 1).setUnlocalizedName("chestplateIron").setTextureName("iron_chestplate");
				Items.iron_leggings= (ItemArmor) new ItemTweakedArmor(ArmorMaterial.IRON, 2, 2).setUnlocalizedName("leggingsIron").setTextureName("iron_leggings");
				Items.iron_boots = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.IRON, 2, 3).setUnlocalizedName("bootsIron").setTextureName("iron_boots");
				Items.diamond_helmet = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.DIAMOND, 3, 0).setUnlocalizedName("helmetDiamond").setTextureName("diamond_helmet");
				Items.diamond_chestplate = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.DIAMOND, 3, 1).setUnlocalizedName("chestplateDiamond").setTextureName("diamond_chestplate");
				Items.diamond_leggings = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.DIAMOND, 3, 2).setUnlocalizedName("leggingsDiamond").setTextureName("diamond_leggings");
				Items.diamond_boots = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.DIAMOND, 3, 3).setUnlocalizedName("bootsDiamond").setTextureName("diamond_boots");
				Items.golden_helmet = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.GOLD, 4, 0).setUnlocalizedName("helmetGold").setTextureName("gold_helmet");
				Items.golden_chestplate = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.GOLD, 4, 1).setUnlocalizedName("chestplateGold").setTextureName("gold_chestplate");
				Items.golden_leggings = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.GOLD, 4, 2).setUnlocalizedName("leggingsGold").setTextureName("gold_leggings");
				Items.golden_boots = (ItemArmor) new ItemTweakedArmor(ArmorMaterial.GOLD, 4, 3).setUnlocalizedName("bootsGold").setTextureName("gold_boots");*/
		}

		if (revertTNT) {
			RegistryHelper.overwriteEntry(Block.blockRegistry, "minecraft:tnt", new BlockTweakedTNT());
		}

		if (nastyFlesh) {
			RegistryHelper.overwriteEntry(Item.itemRegistry, "minecraft:rotten_flesh", new ItemTweakedFlesh());
		} else if (disableHunger) {
			Item flesh = new ItemFood(1, 0.1F, true).setPotionEffect(Potion.poison.id, 5, 0, 0.8F).setUnlocalizedName("rottenFlesh").setTextureName("rotten_flesh");
			RegistryHelper.overwriteEntry(Item.itemRegistry, "minecraft:rotten_flesh", flesh);
		}

		if (nerfFoodStackSize) {
			Item[] nerfs = new Item[] {Items.apple, Items.bread, Items.porkchop, Items.cooked_porkchop, Items.golden_apple, Items.fish, Items.cooked_fish, Items.beef, Items.cooked_beef, Items.chicken, Items.cooked_chicken, Items.rotten_flesh, Items.carrot, Items.potato, Items.baked_potato, Items.golden_carrot, Items.pumpkin_pie};
			Item[] bigNerfs = new Item[] {Items.cookie, Items.melon};

			for (Item nerf : nerfs) {
				nerf.setMaxStackSize(4);
			}

			for (Item bigNerf : bigNerfs) {
				bigNerf.setMaxStackSize(8);
			}

			//TODO: GameRegistry version
			if (LoadedHelper.isTinkersLoaded) {
				Object o = getStaticItem("strangeFood", "tconstruct.common.TContent");
				if (o != null && o instanceof Item)
					((Item) o).setMaxStackSize(4);

				o = getStaticItem("jerky", "tconstruct.common.TContent");
				if (o != null && o instanceof Item)
					((Item) o).setMaxStackSize(4);
			}

			if (LoadedHelper.isNaturaLoaded) {
				Object o = getStaticItem("waterDrop", "mods.natura.common.NContent");
				if (o != null && o instanceof Item)
					((Item) o).setMaxStackSize(8);

				o = getStaticItem("netherBerryItem", "mods.natura.common.NContent");
				if (o != null && o instanceof Item)
					((Item) o).setMaxStackSize(4);

				o = getStaticItem("berryItem", "mods.natura.common.NContent");
				if (o != null && o instanceof Item)
					((Item) o).setMaxStackSize(4);

				o = getStaticItem("berryMedley", "mods.natura.common.NContent");
				if (o != null && o instanceof Item)
					((Item) o).setMaxStackSize(4);

				o = getStaticItem("seedFood", "mods.natura.common.NContent");
				if (o != null && o instanceof Item)
					((Item) o).setMaxStackSize(4);

				o = getStaticItem("impMeat", "mods.natura.common.NContent");
				if (o != null && o instanceof Item)
					((Item) o).setMaxStackSize(4);

				o = getStaticItem("bowlStew", "mods.natura.common.NContent");
				if (o != null && o instanceof Item)
					((Item) o).setMaxStackSize(4);
			}
		}

		if (stackableSoup) {
			RegistryHelper.overwriteEntry(Item.itemRegistry, "minecraft:mushroom_stew", new ItemTweakedStew().setMaxStackSize(nerfFoodStackSize ? 4 : 64));
		}

		if (!Loader.isModLoaded("ZAMod") && !spawnZombieReinforcements || !keepBabyZombies || disableZombieFire) {
			EntityList.addMapping(EntityTweakedZombie.class, "Zombie", 54, 44975, 7969893);
		}

		if (sugarCaneHeight != 3) {
			RegistryHelper.overwriteEntry(Block.blockRegistry, "minecraft:reeds", new BlockTweakedSugarCane());
		}

		if (poisonTime != 25) {
			Potion.potionTypes[Potion.poison.id] = new TweakedPoisonStatus(19, true, 5149489).setPotionName("potion.poison").setIconIndex(6, 0); //TODO: AccessTransformer Stuff
		}

        if (overridePortal) {
	        RegistryHelper.overwriteEntry(Block.blockRegistry, "minecraft:portal", new BlockTweakedPortal());
	        RegistryHelper.overwriteEntry(Block.blockRegistry, "minecraft:fire", new BlockTweakedFire());
        }

		//Doesn't work for some reason
        /*if (nicerWitches)
        {
            EntityList.addMapping(EntityWitch.class, "Witch", 66, 3407872, 5349438);            
        }*/
	}

	public static void overrideFoodStats(EntityPlayer player) {
		overrideHungerHud = disableHunger;
		player.foodStats = new TweakedFoodStats(player); //TODO: Effing AccessTransformer
	}

	public static Object getStaticItem(String name, String classPackage) {
		try {
			Class clazz = Class.forName(classPackage);
			Field field = clazz.getDeclaredField(name);
			Object ret = field.get(null);
			if (ret != null && (ret instanceof ItemStack || ret instanceof Item)) {
				return ret;
			}
			return null;
		} catch (Exception e) {
			MeltedDashboardCore.logger.warn("Could not find " + name);
			return null;
		}
	}
}
