package mortvana.projectfluxgear.thaumic;

import java.lang.reflect.Field;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;

import mortvana.projectfluxgear.common.FGZContent;
import mortvana.projectfluxgear.common.ProjectFluxGear;
import mortvana.projectfluxgear.common.FluxGearContent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ThaumicThings {
	public static ResearchItem researchTorch;
	public static Aspect tempus;
	public static AspectList torchInfusionAspects;
	public static ItemStack[] torchInfusionComponents;

	public static Object tempusObj;

	public static void init() {
		//Hacky way of getting Tempus..
		try {
			Class clazz = Class.forName("magicbees.main.utils.compat.ThaumcraftHelper");
			Field tempusField = clazz.getDeclaredField("aspectTime");
			tempusField.setAccessible(true);
			tempusObj = tempusField.get(null);
			tempus = (Aspect) tempusObj;
		} catch (Exception e) {
			ProjectFluxGear.logger.error("Error finding Tempus, using Vacuos instead:" + e.getMessage());
			tempus = Aspect.VOID;
		}

		torchInfusionComponents =  new ItemStack[] { new ItemStack(GameRegistry.findItem("MagicBees", "jellyBabies")), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_gearunits"), 1, 15), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_borecraft"), 1, 15), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_machine"), 1, 103), new ItemStack(GameRegistry.findItem("ChromatiCraft", "chromaticraft_item_placer"), 1, 9), new ItemStack(GameRegistry.findItem("ProjRed|Illumination", "projectred.illumination.cagelamp2")), new ItemStack(GameRegistry.findItem("Thaumcraft", "blockMetalDevice"), 1, 3), new ItemStack(GameRegistry.findItem("ThermalExpansion", "Frame"), 1, 9), new ItemStack(GameRegistry.findItem("EnderIO", "itemMaterial"), 1, 8), new ItemStack(GameRegistry.findItem("BigReactors", "BRMetalBlock"), 1, 4), FGZContent.timeyWimeyCarboard, FGZContent.timeyWimeyCarboard, new ItemStack(Items.clock), new ItemStack(Items.clock), new ItemStack(Items.clock), new ItemStack(Items.clock) };
		torchInfusionAspects = new AspectList().add(Aspect.ELDRITCH, 16).add(Aspect.MECHANISM, 16).add(Aspect.ENERGY, 24).add(Aspect.ARMOR, 8).add(Aspect.AURA, 8).add(Aspect.HARVEST, 16).add(Aspect.LIGHT, 8).add(tempus, 32);
		researchTorch = new FluxGearResearchItem("TIMEYWIMEY", "ELDRITCH", new AspectList().add(Aspect.ELDRITCH, 16).add(Aspect.MECHANISM, 16).add(Aspect.ENERGY, 24).add(Aspect.ARMOR, 8).add(Aspect.AURA, 8).add(Aspect.HARVEST, 16).add(Aspect.LIGHT, 8).add(tempus, 32), -5, 5, 3, new ItemStack(FluxGearContent.timeyWimeyTorch)).setSpecial().setParents("ADVALCHEMYFURNACE", "OUTERREV").setConcealed().registerResearchItem();
		researchTorch.setPages(new ResearchPage("0"), new ResearchPage(ThaumcraftApi.addInfusionCraftingRecipe("TIMEYWIMEY", new ItemStack(FluxGearContent.timeyWimeyTorch), 10, torchInfusionAspects, new ItemStack(GameRegistry.findItem("ExtraUtilities", "magnumTorch")), torchInfusionComponents)));
		ThaumcraftApi.addInfusionCraftingRecipe("TIMEYWIMEY", new ItemStack(FluxGearContent.timeyWimeyTorch), 10, torchInfusionAspects, new ItemStack(GameRegistry.findItem("ExtraUtilities", "magnumTorch")), torchInfusionComponents);
	}
}
