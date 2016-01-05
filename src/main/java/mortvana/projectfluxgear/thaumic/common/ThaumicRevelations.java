package mortvana.projectfluxgear.thaumic.common;

import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.common.registry.GameRegistry;

import baubles.api.BaubleType;
import magicbees.api.MagicBeesAPI;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.ResearchCategories;

import mortvana.melteddashboard.intermod.baubles.util.DefaultBaubleData;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.util.ConfigBase;
import mortvana.melteddashboard.util.IConfigInitialized;

import mortvana.projectfluxgear.thaumic.block.BlockThaumicPlant;
import mortvana.projectfluxgear.thaumic.block.ItemBlockThaumicPlant;
import mortvana.projectfluxgear.thaumic.enchant.EnchantmentStabilizing;
import mortvana.projectfluxgear.thaumic.item.ItemThaumicBauble;
import mortvana.projectfluxgear.thaumic.world.ExubituraGenerator;

import static mortvana.projectfluxgear.library.FluxGearLibrary.*;

public class ThaumicRevelations implements IConfigInitialized {

	public static Aspect tempus;

	@Override
	public void preInit(ConfigBase config) {
		thaumicRevelationsTab = new FluxGearCreativeTab("PFG-Thaumic", "fluxgear.thaumic", wardenAmulet);

		GameRegistry.registerBlock(blockThaumicPlant, ItemBlockThaumicPlant.class, "blockThaumicPlant");
	}

	@Override
	public void init(ConfigBase config) {
		blockThaumicPlant = new BlockThaumicPlant();
		thaumicBauble = new ItemThaumicBauble();

		//enchantStabilizing = new EnchantmentStabilizing();

		wardenAmulet = thaumicBauble.addMetaBauble(0, "wardenAmulet", new DefaultBaubleData(BaubleType.AMULET), 2);
		loveRing = thaumicBauble.addMetaBauble(1, "loveRing", new DefaultBaubleData(BaubleType.RING).setUnequip(false), 3);

		exubituraPetal = generalItem.addItem(15000, "exubituraPetal");
		wardenicCrystal = generalItem.addItem(15001, "wardenicCrystal");
		wardenicQuartz = generalItem.addItem(15002, "wardenicQuartz");

		GameRegistry.registerWorldGenerator(new ExubituraGenerator(), 1);
	}

	@Override
	public void postInit(ConfigBase config) {
		ResearchCategories.registerCategory(CATEGORY, new ResourceLocation("fluxgear", "textures/items/baubles/wardenamulet.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
		determineTempus();
	}

	public void determineTempus() {
		// Thanks for the API hook, Myst!
		Object protoTempus = MagicBeesAPI.thaumcraftAspectTempus;
		if (protoTempus != null && protoTempus instanceof Aspect) {
			tempus = (Aspect) protoTempus;
		} else {
			tempus = Aspect.VOID;
		}
	}
}
