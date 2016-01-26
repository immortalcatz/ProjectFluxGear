package mortvana.projectfluxgear.library;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import mortvana.melteddashboard.block.metatile.IMetaTileEntity;
import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.item.FluxGearItem;

import thaumcraft.api.aspects.Aspect;

import static thaumcraft.api.aspects.Aspect.*;

public class FluxGearLibrary {

	/** MOD CONSTANTS **/
	public static final String RESOURCE_PREFIX = "fluxgear";

	// NBT TAGS
	// Does the item interact with Blood Magic (generally used for charging with LP)
	public static final String SANGUINE = "PFG_IS_SANGUINE";

	// BLOCK NAMES
	public static final String[] NAMES_ORE_PRIMARY = { "chalcocite", "cassiterite", "galena", "acanthite", "garnierite", "sphalerite", "bismuthinite", "pyrolusite", "bauxite", "cooperite", "braggite", "molybdenite", "cobaltite", "wolframite", "ilmenite", "chromite" };
	public static final String[] NAMES_ORE_SECONDARY = { "cinnabar", "pitchblende", "monazite", "niedermayrite", "greenockite", "gaotaiite", "osarsite", "znamenskyite", "gallobeudanite", "tetrahedrite", "tennantite", "santafeite", "magnetite", "dioptase", "pyrope", "myuvil" };
	public static final String[] NAMES_ORE_TERTIARY = { "jacenite", "scryngestone", "hymetusite", "metebelisSapphire", "trisilicate", "nikolite", "nigelite", "pentlandite", "onyx", "tellurium", "carbonado", "amethyst", "voidstone"/*, "tanzanite", "euxenite", "soarynium"*/ };
	public static final String[] NAMES_EARTH = { "clayIridium", "clayIridiumPoor", "aluminosilicateSludge" };
	public static final String[] NAMES_ROCKS = new String[] { "mica", "andesite", "rhyolite", "gabrro", "diorite", "dolomite", "chert", "phyllite", "kimberlite", "dacite", "serpentine", "larvikite", "schalstein", "greenschist", "hornblendeBiotiteGranite", "pitchstone" };

	// UNLOCALIZED NAMES
	public static final String UNLOCALIZED_NAME_BASE = "tile.fluxgear.";
	public static final String UNLOCALIZED_NAME_ORE = UNLOCALIZED_NAME_BASE + "ore.";
	public static final String UNLOCALIZED_NAME_GRAVEL_ORE = UNLOCALIZED_NAME_BASE + "oreGravel.";
	public static final String UNLOCALIZED_NAME_POOR_ORE = UNLOCALIZED_NAME_BASE + "orePoor.";

	// HARDNESS ARRAYS
	public static final float[] HARDNESS_ORE_PRIMARY = { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 }; //TODO
	public static final float[] HARDNESS_ORE_SECONDARY = { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 }; //TODO
	public static final float[] HARDNESS_ORE_TERTIARY = { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 }; //TODO
	public static final float[] HARDNESS_EARTH = { 5, 5, 0.3F };

	// RESISTANCE ARRAYS
	public static final float[] RESISTANCE_ORE_PRIMARY = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }; //TODO
	public static final float[] RESISTANCE_ORE_SECONDARY = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }; //TODO
	public static final float[] RESISTANCE_ORE_TERTIARY = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }; //TODO
	public static final float[] RESISTANCE_EARTH = { 5, 5, 5 };

	// LIGHT ARRAYS
	public static final int[] LIGHT_ORES_PRIMARY = { 0, 0, 1, 4, 0, 0, 2, 0, 0, 4, 1, 2, 0, 0, 0, 0 };
	public static final int[] LIGHT_ORES_SECONDARY = { 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 8, 4, 4 };
	public static final int[] LIGHT_ORES_TERTIARY = {}; //TODO
	public static final int[] LIGHT_EARTH = { 0, 0, 0 };

	// RARITY ARRAYS
	public static final int[] RARITY_ORES_PRIMARY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 1, 1, 0 };
	public static final int[] RARITY_ORES_SECONDARY = { 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 2, 2, 1 };
	public static final int[] RARITY_ORES_TERTIARY = { }; //TODO
	public static final int[] RARITY_EARTH = { 2, 1, 0 };

	// TEXTURE LOCATIONS
	public static final String TEX_LOC_DEFAULT = "fluxgear:";
	public static final String TEX_LOC_ORE = TEX_LOC_DEFAULT + "ore/ore";
	public static final String TEX_LOC_BLOCK = TEX_LOC_DEFAULT + "storage/block";
	public static final String TEX_LOC_POOR_ORE = TEX_LOC_ORE + "Poor";
	public static final String TEX_LOC_GRAVEL_ORE = TEX_LOC_ORE + "Gravel";


	/** ASPECTS **/
	public static final Aspect WARDEN = new Aspect("exubitor", 0x3CD4FC, new Aspect[] { ELDRITCH, DEATH }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/exubitor.png"), 771);
	public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] { PLANT, SENSES }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/citrus.png"), 771);
	public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] { METAL, ENERGY }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/magnes.png"), 771);
	public static final Aspect FLUX = new Aspect("fluxus", 0xAD0200, new Aspect[] { MAGNET, MECHANISM }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/fluxus.png"), 771);
	public static final Aspect REVELATIONS = new Aspect("revelatio", 0x3971AD, new Aspect[] { ELDRITCH, MIND }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/revelatiofez.png"), 771);


	/** RESEARCH CATEGORIES **/
	public static final String CATEGORY = "FLUXGEAR";


	/** ENCHANTMENTS **/
	public static Enchantment enchantStabilizing;


	/** CREATIVE TABS **/
    public static CreativeTabs debugTab;
	public static CreativeTabs dynMaterialTab;
	public static CreativeTabs componentsTab;
	public static CreativeTabs equipmentTab;
	public static CreativeTabs thaumicRevelationsTab; //= new FluxGearCreativeTab("PFG-Thaumic", "fluxgear.thaumicTab", wardenAmulet);


	/** BLOCKS **/
	public static Block blockThaumicPlant;
    public static Block blockFakeAir;


	/** ITEMS **/
	public static FluxGearItem generalItem;
	public static FluxGearItemBauble thaumicBauble;


	/** ITEMSTACKS **/
	public static ItemStack exubituraPetal;                 //15000
	public static ItemStack wardenicCrystal;                //15001
	public static ItemStack wardenicQuartz;                 //15002

	public static ItemStack wardenAmulet;                   //00000
	public static ItemStack loveRing;                       //00001


	/** META TILE ENTITIES **/
	public static final IMetaTileEntity[] META_TILES = new IMetaTileEntity[32000];


	/* RENDER IDs */
	public static int wardedChestRenderID = -1;
}
