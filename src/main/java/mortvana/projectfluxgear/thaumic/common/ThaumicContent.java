package mortvana.projectfluxgear.thaumic.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import magicbees.api.MagicBeesAPI;
import oldcode.projectfluxgear.FluxGearTab;
import thaumcraft.api.aspects.Aspect;

public class ThaumicContent {

	public static final Aspect WARDEN = new Aspect("exubitor", 0x3CD4FC, new Aspect[] {Aspect.ELDRITCH, Aspect.DEATH}, new ResourceLocation("projectfluxgear", "textures/aspects/exubitor.png"), 771);
	public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] {Aspect.PLANT, Aspect.SENSES}, new ResourceLocation("projectfluxgear", "textures/aspects/citrus.png"), 771);
	public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] {Aspect.METAL, Aspect.ENERGY}, new ResourceLocation("projectfluxgear", "textures/aspects/magnes.png"), 771);
	public static final Aspect FLUX = new Aspect("fluctuatio", 0xAD0200, new Aspect[] {MAGNET, Aspect.MECHANISM}, new ResourceLocation("projectfluxgear", "textures/aspects/fluctuatio.png"), 771);
	public static final Aspect REVELATIONS = new Aspect("revelatio", 0x3971AD, new Aspect[] {Aspect.ELDRITCH, Aspect.MIND}, new ResourceLocation("projectfluxgear", "textures/aspects/revelatio.png"), 771);

	public static final String category = "FLUXGEAR";

	public static Aspect tempus;

	public static CreativeTabs thaumicRevelationsTab = new FluxGearTab("PFG-Thaumic", "fluxgear.tabThaumic", new ItemStack(oldcode.projectfluxgear.ThaumicContent.itemWardenAmulet));

	public static void preInit() {

	}

	public static void init() {

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
}
