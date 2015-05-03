package mortvana.projectfluxgear.thaumic.common;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.util.EnumHelper;

import magicbees.api.MagicBeesAPI;
import mortvana.projectfluxgear.core.common.FluxGearContent;
import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.thaumic.util.ItemWardenArmor;
import mortvana.projectfluxgear.thaumic.util.research.FluxGearResearchItem;
import mortvana.projectfluxgear.to_refactor.block.tile.TileWitor;
import mortvana.projectfluxgear.to_refactor.FluxGearContent_;
import mortvana.projectfluxgear.thaumic.entity.EntityPurity;
import mortvana.projectfluxgear.thaumic.item.armor.*;
import mortvana.projectfluxgear.thaumic.item.baubles.ItemLoveRing;
import mortvana.projectfluxgear.thaumic.item.baubles.ItemWardenAmulet;
import mortvana.projectfluxgear.thaumic.item.foci.ItemFocusIllumination;
import mortvana.projectfluxgear.thaumic.item.foci.ItemFocusPurity;
import mortvana.projectfluxgear.thaumic.item.tool.ItemWardenicBlade;
import mortvana.projectfluxgear.thaumic.item.tool.ItemWaslieHammer;
import mortvana.projectfluxgear.util.item.armor.EnumArmorType;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

public class ThaumicContent {

	public static final Aspect WARDEN = new Aspect("exubitor", 0x3CD4FC, new Aspect[] {Aspect.ELDRITCH, Aspect.DEATH}, new ResourceLocation("projectfluxgear", "textures/aspects/exubitor.png"), 771);
	public static final Aspect CITRUS = new Aspect("citrus", 0xFF6E00, new Aspect[] {Aspect.PLANT, Aspect.SENSES}, new ResourceLocation("projectfluxgear", "textures/aspects/citrus.png"), 771);
	public static final Aspect MAGNET = new Aspect("magnes", 0x515970, new Aspect[] {Aspect.METAL, Aspect.ENERGY}, new ResourceLocation("projectfluxgear", "textures/aspects/magnes.png"), 771);
	public static final Aspect FLUX = new Aspect("fluctuatio", 0xAD0200, new Aspect[] {MAGNET, Aspect.MECHANISM}, new ResourceLocation("projectfluxgear", "textures/aspects/fluctuatio.png"), 771);
	public static final Aspect REVELATIONS = new Aspect("revelatio", 0x3971AD, new Aspect[] {Aspect.ELDRITCH, Aspect.MIND}, new ResourceLocation("projectfluxgear", "textures/aspects/revelatiofez.png"), 771);

	public static ResearchItem researchTorch;
	public static Aspect tempus;
	public static AspectList torchInfusionAspects;
	public static ItemStack[] torchInfusionComponents;

	public static final String category = "FLUXGEAR";
	
	public static ItemArmor.ArmorMaterial materialWarden = EnumHelper.addArmorMaterial("WARDEN", 50, new int[] {3, 8, 6, 3}, 0);

	public static void determineTempus() {
		// Thanks for the API hook, Myst!
		Object protoTempus = MagicBeesAPI.thaumcraftAspectTempus;
		if (protoTempus != null) {
			tempus = (Aspect) protoTempus;
		} else {
			tempus = Aspect.VOID;
		}
	}

	public static void loadBlocks() {

		blockExubitura = new ItemStack(FluxGearContent.blockPlant, 1, 9);
		GameRegistry.registerBlock(blockInfusedQuartzNormal, "blockInfusedQuartzNormal");
		GameRegistry.registerBlock(blockInfusedQuartzChiseled, "blockInfusedQuartzChiseled");
		GameRegistry.registerBlock(blockInfusedQuartzPillar, "blockInfusedQuartzPillar");
		GameRegistry.registerBlock(blockInfusedQuartzSlab, "blockInfusedQuartzSlab");
		GameRegistry.registerBlock(blockInfusedQuartzStair, "blockInfusedQuartzStair");
		GameRegistry.registerBlock(blockWitor, "blockWitor");

		GameRegistry.registerTileEntity(TileWitor.class, "tileWitor");
	}

	public static void loadItems() {
		GameRegistry.registerItem(itemFocusPurity, "itemFocusPurity");
		GameRegistry.registerItem(itemWardenSword, "itemWardenWeapon");
		GameRegistry.registerItem(itemWardenAmulet, "itemWardenAmulet");
		GameRegistry.registerItem(itemWardenHelm, "itemWardenHelm");
		GameRegistry.registerItem(itemWardenChest, "itemWardenChest");
		GameRegistry.registerItem(itemWardenLegs, "itemWardenLegs");
		GameRegistry.registerItem(itemWardenBoots, "itemWardenBoots");
		GameRegistry.registerItem(itemLoveRing, "itemLoveRing");
		GameRegistry.registerItem(itemWaslieHammer, "itemWaslieHammer");
		GameRegistry.registerItem(itemFocusIllumination, "itemFocusIllumination");

		exubituraPetal = FluxGearContent.itemMaterial.addItem(15000, "exubituraPetal");
		wardenicCrystal = FluxGearContent.itemMaterial.addItem(15001, "wardenicCrystal");
		wardenicQuartz = FluxGearContent.itemMaterial.addItem(15002, "wardenicQuartz");
	}

	public static void loadEntities() {
		EntityRegistry.registerModEntity(EntityPurity.class, "PurityOrb", 0, ProjectFluxGear.instance, 64, 10, true);
		//EntityRegistry.registerModEntity(EntityFleshProjectile.class, "ThrownFlesh", 1, TRevelations.instance, 64, 3, true);
		//EntityRegistry.registerGlobalEntityID(FleshGolem.class, "FleshGolem", EntityRegistry.findGlobalUniqueEntityId(), 0xE4A2A9, 0x96452E);
	}

	public static void loadBasicRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzNormal), "XX", "XX", 'X', new ItemStack(itemResource, 1, 2));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzChiseled), "X", "X", 'X', new ItemStack(blockInfusedQuartzSlab));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzPillar, 2), "X", "X", 'X', new ItemStack(blockInfusedQuartzNormal));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzSlab, 6), "XXX", 'X', new ItemStack(blockInfusedQuartzNormal));
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzStair, 4), "X  ", "XX ", "XXX", 'X', new ItemStack(blockInfusedQuartzNormal));
	}
	
	public static void loadThaumicRecipes() {
		recipeQuartz = ThaumcraftApi.addCrucibleRecipe("QUARTZ", wardenicQuartz, new ItemStack(Items.quartz), new AspectList().add(Aspect.MAGIC, 4).add(Aspect.ELDRITCH, 4));
		recipeCrystal = ThaumcraftApi.addCrucibleRecipe("CRYSTAL", wardenicCrystal, exubituraPetal, new AspectList().add(Aspect.MAGIC, 32).add(Aspect.CRYSTAL, 32));

		recipeWardenHelm = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenHelm, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XXX", "XOX", "   ", 'X', wardenicQuartz, 'O', wardenicCrystal);
		recipeWardenChest = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenChest, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"X X", "XOX", "XXX", 'X', wardenicQuartz, 'O', wardenicCrystal);
		recipeWardenLegs = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenLegs, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XXX", "XOX", "X X", 'X', wardenicQuartz, 'O', wardenicCrystal);
		recipeWardenBoots = ThaumcraftApi.addArcaneCraftingRecipe("WARDENARMOR", new ItemStack(itemWardenBoots, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"   ", "XOX", "X X", 'X', wardenicQuartz, 'O', wardenicCrystal);
		recipeWardenSword = ThaumcraftApi.addArcaneCraftingRecipe("WARDENWEAPON", new ItemStack(itemWardenSword, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				" X ", " X ", " O ", 'X', wardenicQuartz, 'O', wardenicCrystal);

		recipeWaslieHammer = ThaumcraftApi.addArcaneCraftingRecipe("WASLIEHAMMER", new ItemStack(itemWaslieHammer, 1), new AspectList().add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125).add(Aspect.AIR, 125).add(Aspect.EARTH, 125).add(Aspect.FIRE, 125).add(Aspect.WATER, 125),
				"XXX", "XOX", " I ", 'X', wardenicCrystal, 'O', wardenicQuartz, 'I', new ItemStack(ConfigBlocks.blockMagicalLog));

		recipeFocusIllumination = ThaumcraftApi.addArcaneCraftingRecipe("ILLUMINATION", new ItemStack(itemFocusIllumination, 1), new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50),
				" X ", "XOX", " X ", 'X', new ItemStack(ConfigItems.itemResource, 1, 1), 'O', new ItemStack(ConfigItems.itemFocusFire));

	}
	
	public static void loadResearch() {
		ResearchCategories.registerCategory("FLUXGEAR", new ResourceLocation("projectfluxgear", "textures/items/wardenamulet.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

		researchTWarden = new FluxGearResearchItem("TREVELATIONS", category, new AspectList(), 0, 0, 0, new ItemStack(itemWardenAmulet)).setRound().setSpecial().setAutoUnlock().registerResearchItem();
		researchTWarden.setPages(new ResearchPage("0"));
		researchExubitura = new FluxGearResearchItem("EXUBITURA", category, new AspectList(), 0, -2, 0, blockExubitura).setParents("TREVELATIONS").setAutoUnlock().registerResearchItem();
		researchExubitura.setPages(new ResearchPage("0"));
		researchQuartz = new FluxGearResearchItem("QUARTZ", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4), 2, 0, 2, wardenicQuartz).setParents("TREVELATIONS").setRound().registerResearchItem();
		researchQuartz.setPages(new ResearchPage("0"), new ResearchPage(recipeQuartz));
		researchCrystal = new FluxGearResearchItem("CRYSTAL", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4), -2, 0, 2, wardenicCrystal).setParents("TREVELATIONS").setSecondary().registerResearchItem();
		researchCrystal.setPages(new ResearchPage("0"), new ResearchPage(recipeCrystal));
		researchWardenArmor = new FluxGearResearchItem("WARDENARMOR", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4).add(Aspect.ARMOR, 4), 6, 2, 3, new ItemStack(itemWardenChest)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenArmor.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenHelm), new ResearchPage(recipeWardenChest), new ResearchPage(recipeWardenLegs), new ResearchPage(recipeWardenBoots));
		researchWardenWeapon = new FluxGearResearchItem("WARDENWEAPON", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4).add(Aspect.WEAPON, 4), 6, 6, 3, new ItemStack(itemWardenSword)).setParents("WASLIEHAMMER").setRound().setSpecial().registerResearchItem();
		researchWardenWeapon.setPages(new ResearchPage("0"), new ResearchPage(recipeWardenSword));
		researchWaslieHammer = new FluxGearResearchItem("WASLIEHAMMER", category, new AspectList().add(WARDEN, 4).add(Aspect.CRYSTAL, 4), 4, 4, 3, new ItemStack(itemWaslieHammer)).setParentsHidden("CRYSTAL", "QUARTZ").setParents("LORE4").setRound().setSpecial().registerResearchItem();
		researchWaslieHammer.setPages(new ResearchPage("0"), new ResearchPage(recipeWaslieHammer));

		researchIllumination = new FluxGearResearchItem("ILLUMINATION", category, new AspectList().add(Aspect.AIR, 2).add(Aspect.FIRE, 2), 0, 4, 1, new ItemStack(itemFocusIllumination)).setRound().setParentsHidden("FOCUSFIRE").registerResearchItem();
		researchIllumination.setPages(new ResearchPage("0"), new ResearchPage(recipeFocusIllumination));

		researchLore1 = new FluxGearResearchItem("LORE1", category, new AspectList().add(WARDEN, 4).add(Aspect.MIND, 4), 0, 2, 3, new ItemStack(itemWardenAmulet)).setParents("TREVELATIONS").setRound().setSpecial().registerResearchItem();
		researchLore1.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"));
		researchLore2 = new FluxGearResearchItem("LORE2", category, new AspectList().add(WARDEN, 4).add(Aspect.MIND, 4), -2, 4, 3, new ItemStack(itemWardenAmulet)).setParents("LORE1").setRound().setSpecial().registerResearchItem();
		researchLore2.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"));
		researchLore3 = new FluxGearResearchItem("LORE3", category, new AspectList().add(WARDEN, 4).add(Aspect.MIND, 4), 0, 6, 3, new ItemStack(itemWardenAmulet)).setParents("LORE2").setRound().setSpecial().registerResearchItem();
		researchLore3.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"), new ResearchPage("4"), new ResearchPage("5"));
		researchLore4 = new FluxGearResearchItem("LORE4", category, new AspectList().add(WARDEN, 4).add(Aspect.MIND, 4), 2, 4, 3, new ItemStack(itemWardenAmulet)).setParents("LORE3").setRound().setSpecial().registerResearchItem();
		researchLore4.setPages(new ResearchPage("0"), new ResearchPage("1"), new ResearchPage("2"), new ResearchPage("3"));
	}

	public static void loadTimeyWimey() {
		torchInfusionComponents =  new ItemStack[] { new ItemStack(GameRegistry.findItem("MagicBees", "jellyBabies")), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_gearunits"), 1, 15), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_borecraft"), 1, 15), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_machine"), 1, 103), new ItemStack(GameRegistry.findItem("ChromatiCraft", "chromaticraft_item_placer"), 1, 9), new ItemStack(GameRegistry.findItem("ProjRed|Illumination", "projectred.illumination.cagelamp2")), new ItemStack(GameRegistry.findItem("Thaumcraft", "blockMetalDevice"), 1, 3), new ItemStack(GameRegistry.findItem("ThermalExpansion", "Frame"), 1, 9), new ItemStack(GameRegistry.findItem("EnderIO", "itemMaterial"), 1, 8), new ItemStack(GameRegistry.findItem("BigReactors", "BRMetalBlock"), 1, 4), FluxGearContent_.timeyWimeyCarboard, FluxGearContent_.timeyWimeyCarboard, new ItemStack(Items.clock), new ItemStack(Items.clock), new ItemStack(Items.clock), new ItemStack(Items.clock) };
		torchInfusionAspects = new AspectList().add(Aspect.ELDRITCH, 16).add(Aspect.MECHANISM, 16).add(Aspect.ENERGY, 24).add(Aspect.ARMOR, 8).add(Aspect.AURA, 8).add(Aspect.HARVEST, 16).add(Aspect.LIGHT, 8).add(tempus, 32);
		researchTorch = new FluxGearResearchItem("TIMEYWIMEY", "ELDRITCH", new AspectList().add(Aspect.ELDRITCH, 16).add(Aspect.MECHANISM, 16).add(Aspect.ENERGY, 24).add(Aspect.ARMOR, 8).add(Aspect.AURA, 8).add(Aspect.HARVEST, 16).add(Aspect.LIGHT, 8).add(tempus, 32), -5, 5, 3, new ItemStack(FluxGearContent_.timeyWimeyTorch)).setSpecial().setParents("ADVALCHEMYFURNACE", "OUTERREV").setConcealed().registerResearchItem();
		researchTorch.setPages(new ResearchPage("0"), new ResearchPage(ThaumcraftApi.addInfusionCraftingRecipe("TIMEYWIMEY", new ItemStack(FluxGearContent_.timeyWimeyTorch), 10, torchInfusionAspects, new ItemStack(GameRegistry.findItem("ExtraUtilities", "magnumTorch")), torchInfusionComponents)));
		ThaumcraftApi.addInfusionCraftingRecipe("TIMEYWIMEY", new ItemStack(FluxGearContent_.timeyWimeyTorch), 10, torchInfusionAspects, new ItemStack(GameRegistry.findItem("ExtraUtilities", "magnumTorch")), torchInfusionComponents);
	}

	public static void loadAspects() {
		ThaumcraftApi.registerObjectTag(exubituraPetal, new AspectList().add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(wardenicCrystal, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(wardenicQuartz, new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenHelm), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenChest), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenLegs), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenBoots), new AspectList().add(Aspect.ARMOR, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(itemWardenSword), new AspectList().add(Aspect.WEAPON, 4).add(Aspect.ELDRITCH, 4).add(WARDEN, 1));
	}
	
	/* Research */
	public static ResearchItem researchTWarden;
	public static ResearchItem researchExubitura;
	public static ResearchItem researchQuartz;
	public static ResearchItem researchCrystal;
	public static ResearchItem researchWardenArmor;
	public static ResearchItem researchWardenWeapon;
	public static ResearchItem researchWaslieHammer;
	public static ResearchItem researchLore1;
	public static ResearchItem researchLore2;
	public static ResearchItem researchLore3;
	public static ResearchItem researchLore4;
	public static ResearchItem researchIllumination;
	
	/* Recipes */
	public static CrucibleRecipe recipeQuartz;
	public static CrucibleRecipe recipeCrystal;
	public static ShapedArcaneRecipe recipeWardenHelm;
	public static ShapedArcaneRecipe recipeWardenChest;
	public static ShapedArcaneRecipe recipeWardenLegs;
	public static ShapedArcaneRecipe recipeWardenBoots;
	public static ShapedArcaneRecipe recipeWardenSword;
	public static ShapedArcaneRecipe recipeWaslieHammer;
	public static ShapedArcaneRecipe recipeFocusIllumination;
	
	/* Items */
	public static Item itemWardenAmulet = new ItemWardenAmulet();
	public static Item itemWardenSword = new ItemWardenicBlade();
	public static Item itemFocusPurity = new ItemFocusPurity();
	public static Item itemWardenHelm = new ItemWardenArmor(EnumArmorType.HELMET, "itemWardenHelm", "warden", "fluxgear:wardenhelm");
	public static Item itemWardenChest = new ItemWardenArmor(EnumArmorType.CHESTPLATE, "itemWardenChest", "warden", "fluxgear:wardenchest");
	public static Item itemWardenLegs = new ItemWardenArmor(EnumArmorType.PANTS, "itemWardenLegs", "warden", "fluxgear:wardenlegs");
	public static Item itemWardenBoots = new ItemWardenArmor(EnumArmorType.BOOTS, "itemWardenBoots", "warden", "fluxgear:wardenboots");
	public static Item itemLoveRing = new ItemLoveRing();
	public static Item itemWaslieHammer = new ItemWaslieHammer();
	public static Item itemFocusIllumination = new ItemFocusIllumination();

	/* ItemStacks (Block) */
	public static ItemStack blockExubitura;

	/* ItemStacks (Items) */
	public static ItemStack exubituraPetal;
	public static ItemStack wardenicCrystal;
	public static ItemStack wardenicQuartz;
}
