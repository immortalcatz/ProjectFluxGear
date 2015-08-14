package mortvana.projectfluxgear.thaumic.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import baubles.api.BaubleType;
import magicbees.api.MagicBeesAPI;
import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.melteddashboard.intermod.baubles.util.BaubleData;
import mortvana.melteddashboard.intermod.baubles.util.DefaultBaubleData;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.projectfluxgear.thaumic.item.ItemThaumicBauble;
import thaumcraft.api.aspects.Aspect;

public class ThaumicContent {

	public static final Aspect WARDEN = new Aspect("exubitor", 0x3CD4FC, new Aspect[] { Aspect.ELDRITCH, Aspect.DEATH }, new ResourceLocation("fluxgear", "textures/aspects/exubitor.png"), 771);
	public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] { Aspect.PLANT, Aspect.SENSES }, new ResourceLocation("fluxgear", "textures/aspects/citrus.png"), 771);
	public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] { Aspect.METAL, Aspect.ENERGY }, new ResourceLocation("fluxgear", "textures/aspects/magnes.png"), 771);
	public static final Aspect FLUX = new Aspect("fluctuatio", 0xAD0200, new Aspect[] { MAGNET, Aspect.MECHANISM }, new ResourceLocation("fluxgear", "textures/aspects/fluctuatio.png"), 771);
	public static final Aspect REVELATIONS = new Aspect("revelatio", 0x3971AD, new Aspect[] {Aspect.ELDRITCH, Aspect.MIND }, new ResourceLocation("fluxgear", "textures/aspects/revelatio.png"), 771);

	public static final String category = "FLUXGEAR";

	public static Aspect tempus;

	public static void preInit() {
		thaumicRevelationsTab = new FluxGearCreativeTab("PFG-Thaumic", "fluxgear.tabThaumic", wardenAmulet);
	}

	public static void init() {
		wardenAmulet = thaumicBauble.addMetaBauble(0, "wardenAmulet", new DefaultBaubleData(BaubleType.AMULET), 2);
		loveRing = thaumicBauble.addMetaBauble(1, "loveRing", new DefaultBaubleData(BaubleType.RING).setUnequip(false), 3);
	}

	public static void postInit() {
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

	public static FluxGearItemBauble thaumicBauble = new ItemThaumicBauble();

	public static ItemStack wardenAmulet;
	public static ItemStack loveRing;

}
