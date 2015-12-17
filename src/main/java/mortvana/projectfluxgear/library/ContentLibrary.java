package mortvana.projectfluxgear.library;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.melteddashboard.item.FluxGearItem;

import thaumcraft.api.aspects.Aspect;

import static thaumcraft.api.aspects.Aspect.*;

public class ContentLibrary {

	/** MOD CONSTANTS **/
	public static final String RESOURCE_PREFIX = "fluxgear";

	/** ASPECTS **/
	public static final Aspect WARDEN = new Aspect("exubitor", 0x3CD4FC, new Aspect[] { ELDRITCH, DEATH }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/exubitor.png"), 771);
	public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] { PLANT, SENSES }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/citrus.png"), 771);
	public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] { METAL, ENERGY }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/magnes.png"), 771);
	public static final Aspect FLUX = new Aspect("fluxus", 0xAD0200, new Aspect[] { MAGNET, MECHANISM }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/fluxus.png"), 771);
	public static final Aspect REVELATIONS = new Aspect("revelatio", 0x3971AD, new Aspect[] { ELDRITCH, MIND }, new ResourceLocation(RESOURCE_PREFIX, "textures/aspects/revelatiofez.png"), 771);

	/** RESEARCH CATEGORIES **/
	public static final String CATEGORY = "FLUXGEAR";

	/** CREATIVE TABS **/
	public static CreativeTabs dynMaterialTab;
	public static CreativeTabs componentsTab;
	public static CreativeTabs equipmentTab;
	public static CreativeTabs thaumicRevelationsTab;

	/** ENCHANTMENTS **/
	public static Enchantment enchantStabilizing;

	/** BLOCKS **/
	public static Block blockThaumicPlant;


	/** ITEMS **/
	public static FluxGearItem generalItem;
	public static FluxGearItemBauble thaumicBauble;


	/** ITEMSTACKS **/
	public static ItemStack exubituraPetal;                 //15000
	public static ItemStack wardenicCrystal;                //15001
	public static ItemStack wardenicQuartz;                 //15002

	public static ItemStack wardenAmulet;                   //00000
	public static ItemStack loveRing;                       //00001

}