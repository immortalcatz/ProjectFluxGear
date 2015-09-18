package mortvana.projectfluxgear.thaumic.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.common.registry.GameRegistry;

import baubles.api.BaubleType;
import magicbees.api.MagicBeesAPI;
import mortvana.melteddashboard.block.FluxGearItemBlock;
import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.melteddashboard.intermod.baubles.util.DefaultBaubleData;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.item.FluxGearItem;
import mortvana.projectfluxgear.core.common.FluxGearCoreContent;
import mortvana.projectfluxgear.thaumic.block.BlockThaumicPlant;
import mortvana.projectfluxgear.thaumic.block.ItemBlockThaumicPlant;
import mortvana.projectfluxgear.thaumic.item.ItemThaumicBauble;
import mortvana.projectfluxgear.thaumic.world.ExubituraGenerator;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.ResearchCategories;

public class ThaumicContent {

	public static FluxGearItem generalItem = FluxGearCoreContent.generalItem;

	public static final Aspect WARDEN = new Aspect("exubitor", 0x3CD4FC, new Aspect[] { Aspect.ELDRITCH, Aspect.DEATH }, new ResourceLocation("fluxgear", "textures/aspects/exubitor.png"), 771);
	public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] { Aspect.PLANT, Aspect.SENSES }, new ResourceLocation("fluxgear", "textures/aspects/citrus.png"), 771);
	public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] { Aspect.METAL, Aspect.ENERGY }, new ResourceLocation("fluxgear", "textures/aspects/magnes.png"), 771);
	public static final Aspect FLUX = new Aspect("fluctuatio", 0xAD0200, new Aspect[] { MAGNET, Aspect.MECHANISM }, new ResourceLocation("fluxgear", "textures/aspects/fluctuatio.png"), 771);
	public static final Aspect REVELATIONS = new Aspect("revelatio", 0x3971AD, new Aspect[] {Aspect.ELDRITCH, Aspect.MIND }, new ResourceLocation("fluxgear", "textures/aspects/revelatiofez.png"), 771);

	public static final String CATEGORY = "FLUXGEAR";

	public static Aspect tempus;

	public static void preInit() {
		thaumicRevelationsTab = new FluxGearCreativeTab("PFG-Thaumic", "fluxgear.thaumic", wardenAmulet);

		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class, "blockThaumicPlant");
	}

	public static void init() {
		wardenAmulet = thaumicBauble.addMetaBauble(0, "wardenAmulet", new DefaultBaubleData(BaubleType.AMULET), 2);
		loveRing = thaumicBauble.addMetaBauble(1, "loveRing", new DefaultBaubleData(BaubleType.RING).setUnequip(false), 3);

		exubituraPetal = generalItem.addItem(15000, "exubituraPetal");
		wardenicCrystal = generalItem.addItem(15001, "wardenicCrystal");
		wardenicQuartz = generalItem.addItem(15002, "wardenicQuartz");

		GameRegistry.registerWorldGenerator(new ExubituraGenerator(), 1);
	}

	public static void postInit() {
		ResearchCategories.registerCategory(CATEGORY, new ResourceLocation("fluxgear", "textures/items/baubles/wardenamulet.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		determineTempus();
	}

	public static void determineTempus() {
		// Thanks for the API hook, Myst!
		Object protoTempus = MagicBeesAPI.thaumcraftAspectTempus;
		if (protoTempus != null && protoTempus instanceof Aspect) {
			tempus = (Aspect) protoTempus;
		} else {
			tempus = Aspect.VOID;
		}
	}

	public static CreativeTabs thaumicRevelationsTab;

	public static Block blockThaumicPlant = new BlockThaumicPlant();

	public static FluxGearItemBauble thaumicBauble = new ItemThaumicBauble();

	public static ItemStack wardenAmulet;
	public static ItemStack loveRing;

	public static ItemStack exubituraPetal;
	public static ItemStack wardenicCrystal;
	public static ItemStack wardenicQuartz;
}
