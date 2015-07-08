package oldcode.projectfluxgear;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mortvana.projectfluxgear.util.enums.EnumArmorType;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.api.research.*;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.*;
import thaumcraft.common.entities.monster.*;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ThaumicContent {

	public static ResearchItem researchTorch;
	public static AspectList torchInfusionAspects;
	public static ItemStack[] torchInfusionComponents;
	
	public static ItemArmor.ArmorMaterial materialWarden = EnumHelper.addArmorMaterial("WARDEN", 50, new int[] {3, 8, 6, 3}, 0);

	public static final Aspect WARDEN = mortvana.projectfluxgear.thaumic.common.ThaumicContent.WARDEN;

	public static String category;

	public static void loadBlocks() {

		blockExubitura = new ItemStack(FluxGearContent.blockPlant, 1, 9);
		GameRegistry.registerBlock(blockInfusedQuartzNormal, "blockInfusedQuartzNormal");
		GameRegistry.registerBlock(blockInfusedQuartzChiseled, "blockInfusedQuartzChiseled");
		GameRegistry.registerBlock(blockInfusedQuartzPillar, "blockInfusedQuartzPillar");
		GameRegistry.registerBlock(blockInfusedQuartzSlab, "blockInfusedQuartzSlab");
		GameRegistry.registerBlock(blockInfusedQuartzStair, "blockInfusedQuartzStair");
		GameRegistry.registerBlock(blockWitor, "blockWitor");

		GameRegistry.registerTileEntity(BlockFluxGear.TileWitor.class, "tileWitor");
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
		EntityRegistry.registerModEntity(EntityFleshProjectile.class, "ThrownFlesh", 1, ProjectFluxGear.instance, 64, 3, true);
		EntityRegistry.registerGlobalEntityID(FleshGolem.class, "FleshGolem", EntityRegistry.findGlobalUniqueEntityId(), 0xE4A2A9, 0x96452E);
	}

	public static void loadBasicRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(blockInfusedQuartzNormal), "XX", "XX", 'X', wardenicQuartz);
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
		torchInfusionComponents =  new ItemStack[] { new ItemStack(GameRegistry.findItem("MagicBees", "jellyBabies")), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_gearunits"), 1, 15), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_borecraft"), 1, 15), new ItemStack(GameRegistry.findItem("RotaryCraft", "rotarycraft_item_machine"), 1, 103), new ItemStack(GameRegistry.findItem("ChromatiCraft", "chromaticraft_item_placer"), 1, 9), new ItemStack(GameRegistry.findItem("ProjRed|Illumination", "projectred.illumination.cagelamp2")), new ItemStack(GameRegistry.findItem("Thaumcraft", "blockMetalDevice"), 1, 3), new ItemStack(GameRegistry.findItem("ThermalExpansion", "Frame"), 1, 9), new ItemStack(GameRegistry.findItem("EnderIO", "itemMaterial"), 1, 8), new ItemStack(GameRegistry.findItem("BigReactors", "BRMetalBlock"), 1, 4), FluxGearContent.timeyWimeyCarboard, FluxGearContent.timeyWimeyCarboard, new ItemStack(Items.clock), new ItemStack(Items.clock), new ItemStack(Items.clock), new ItemStack(Items.clock) };
		torchInfusionAspects = new AspectList().add(Aspect.ELDRITCH, 16).add(Aspect.MECHANISM, 16).add(Aspect.ENERGY, 24).add(Aspect.ARMOR, 8).add(Aspect.AURA, 8).add(Aspect.HARVEST, 16).add(Aspect.LIGHT, 8).add(mortvana.projectfluxgear.thaumic.common.ThaumicContent.tempus, 32);
		researchTorch = new FluxGearResearchItem("TIMEYWIMEY", "ELDRITCH", new AspectList().add(Aspect.ELDRITCH, 16).add(Aspect.MECHANISM, 16).add(Aspect.ENERGY, 24).add(Aspect.ARMOR, 8).add(Aspect.AURA, 8).add(Aspect.HARVEST, 16).add(Aspect.LIGHT, 8).add(mortvana.projectfluxgear.thaumic.common.ThaumicContent.tempus, 32), -5, 5, 3, new ItemStack(FluxGearContent.timeyWimeyTorch)).setSpecial().setParents("ADVALCHEMYFURNACE", "OUTERREV").setConcealed().registerResearchItem();
		researchTorch.setPages(new ResearchPage("0"), new ResearchPage(ThaumcraftApi.addInfusionCraftingRecipe("TIMEYWIMEY", new ItemStack(FluxGearContent.timeyWimeyTorch), 10, torchInfusionAspects, new ItemStack(GameRegistry.findItem("ExtraUtilities", "magnumTorch")), torchInfusionComponents)));
		ThaumcraftApi.addInfusionCraftingRecipe("TIMEYWIMEY", new ItemStack(FluxGearContent.timeyWimeyTorch), 10, torchInfusionAspects, new ItemStack(GameRegistry.findItem("ExtraUtilities", "magnumTorch")), torchInfusionComponents);
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

	//TODO: Generalize this for future uses *cough* Alchemic Tools *cough*
	public static final WardenicUpgrade UPGRADE_WARDEN = new WardenicUpgradeWarden(WARDEN);
	public static final WardenicUpgrade UPGRADE_FIRE = new WardenicUpgradeFire(Aspect.FIRE);
	public static final WardenicUpgrade UPGRADE_ARMOR = new WardenicUpgradeArmor(Aspect.ARMOR);
	public static final WardenicUpgrade UPGRADE_WATER = new WardenicUpgradeWater(Aspect.WATER);
	public static final WardenicUpgrade UPGRADE_AIR = new WardenicUpgradeAir(Aspect.AIR);
	public static final WardenicUpgrade UPGRADE_EARTH = new WardenicUpgradeEarth(Aspect.EARTH);
	public static final WardenicUpgrade UPGRADE_HEAL = new WardenicUpgradeHeal(Aspect.HEAL);

	public static void init() {
		WardenicChargeHelper.addUpgrade(UPGRADE_WARDEN);
		WardenicChargeHelper.addUpgrade(UPGRADE_FIRE);
		WardenicChargeHelper.addUpgrade(UPGRADE_ARMOR);
		WardenicChargeHelper.addUpgrade(UPGRADE_WATER);
		WardenicChargeHelper.addUpgrade(UPGRADE_AIR);
		WardenicChargeHelper.addUpgrade(UPGRADE_EARTH);
		WardenicChargeHelper.addUpgrade(UPGRADE_HEAL);
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


	public static Block blockInfusedQuartzNormal;
	public static Block blockInfusedQuartzChiseled;
	public static Block blockInfusedQuartzPillar;
	public static Block blockInfusedQuartzSlab;
	public static Block blockInfusedQuartzStair;
	public static Block blockWitor;

	public static class ItemWardenAmulet extends Item implements IBauble {

		public ItemWardenAmulet() {

			super();
			setUnlocalizedName("itemWardenAmulet");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);

		}

		@Override
		public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.rare;}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister register) {

			itemIcon = register.registerIcon("trevelations:wardenamulet");

		}

		@Override
		public BaubleType getBaubleType(ItemStack itemStack) {return BaubleType.AMULET;}

		@Override
		public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {

		}

		@Override
		public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

		@Override
		public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

		@Override
		public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return true;}

		@Override
		public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return true;}

	}

	public static class ItemLoveRing extends Item implements IBauble {

		public ItemLoveRing() {

			super();
			setUnlocalizedName("itemLoveRing");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);

		}

		@Override
		public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.epic;}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister register) {

			itemIcon = register.registerIcon("trevelations:lovering");

		}

		@Override
		public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

			world.playSoundAtEntity(player, "trevelations:abderp", 1, 1);

			return super.onItemRightClick(stack, world, player);

		}

		@Override
		public BaubleType getBaubleType(ItemStack itemStack) {return BaubleType.RING;}

		@Override
		public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

		@Override
		public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

		@Override
		public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}

		@Override
		public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return true;}

		@Override
		public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {return false;}

	}

	public static class ItemWardenicBlade extends Item {

		public ItemWardenicBlade() {

			super();
			setUnlocalizedName("itemWardenicBlade");
			setCreativeTab(ProjectFluxGear.thaumicTab);
			setMaxStackSize(1);

			setFull3D();

		}

		@Override
		public boolean getShareTag() {return true;}

		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) {return false;}

		@Override
		public int getMaxDamage(ItemStack stack) {return 50;}

		@Override
		public boolean isDamageable() {return false;}

		@Override
		public EnumRarity getRarity(ItemStack par1ItemStack) {return EnumRarity.epic;}

		@Override
		public EnumAction getItemUseAction(ItemStack par1ItemStack) {return EnumAction.block;}

		@Override
		public int getMaxItemUseDuration(ItemStack par1ItemStack) {return 72000;}

		@Override
		public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

			par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tooltip.wardenic.charge") + ": " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) + "/" + par1ItemStack.getMaxDamage());
			par3List.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(par1ItemStack).getQuote());

			super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);

		}

		@Override
		public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

			if (stack.getItemDamage() != stack.getMaxDamage()) {

				DamageSource damageSource = new DamageSourceFluxGear("warden", player);

				entity.attackEntityFrom(damageSource, 5);

				WardenicChargeHelper.getUpgrade(stack).onAttack(stack, player, entity);

				stack.setItemDamage(stack.getItemDamage() + 1);

			}

			return super.onLeftClickEntity(stack, player, entity);

		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister register) {

			itemIcon = register.registerIcon("trevelations:wardensword");

		}

	}

	public static class ItemWardenArmor extends ItemArmorFluxGear implements ISpecialArmor, IVisDiscountGear {

		public ItemWardenArmor(EnumArmorType type, String name, String sheet, String icon) {
			super(materialWarden, 3, type, name, sheet, icon);
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);
		}

		@Override
		public boolean getShareTag() {
			return true;
		}

		@Override
		public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
			return false;
		}

		@Override
		public int getMaxDamage(ItemStack stack) {
			return 50;
		}

		@Override
		public boolean isDamageable() {
			return false;
		}

		@Override
		public EnumRarity getRarity(ItemStack par1ItemStack) {
			return EnumRarity.epic;
		}

		@Override
		public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
			par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tooltip.wardenic.charge") + ": " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) + "/" + par1ItemStack.getMaxDamage());
			par3List.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(par1ItemStack).getQuote());
			super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		}

		@Override
		public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
			WardenicChargeHelper.getUpgrade(itemStack).onTick(world, player, itemStack);
			super.onArmorTick(world, player, itemStack);
		}

		@Override
		public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
			if (armor.getItemDamage() != armor.getMaxDamage()) {
				return new ArmorProperties(0, getArmorMaterial().getDamageReductionAmount(slot) / 25D, 20);
			} else {
				return new ArmorProperties(0, 0, 0);
			}
		}

		@Override
		public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
			return getArmorMaterial().getDamageReductionAmount(slot);
		}

		@Override
		public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

		}

		@Override
		public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
			return 5;
		}

	}

	public static class ItemWaslieHammer extends Item {

		public ItemWaslieHammer() {

			super();
			setUnlocalizedName("itemWaslieHammer");
			setCreativeTab(ProjectFluxGear.thaumicTab);
			setMaxStackSize(1);
			canRepair = false;

		}

		@Override
		public EnumRarity getRarity(ItemStack stack) {

			return EnumRarity.rare;

		}

		@Override
		public boolean isItemTool(ItemStack stack) {

			return true;

		}

		@Override
		public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

			par3EntityPlayer.openGui(ProjectFluxGear.instance, 0, par2World, 0, 0, 0);

			return par1ItemStack;

		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister register) {

			itemIcon = register.registerIcon("projectfluxgear:tool/wasliehammer");

		}

	}

	public static class ItemFocusIllumination extends ItemFocusBasic {

		private IIcon depth, orn;

		public ItemFocusIllumination() {

			super();
			setUnlocalizedName("itemFocusIllumination");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);

		}

		@Override
		public void registerIcons(IIconRegister register) {

			icon = register.registerIcon("trevelations:purityfocus");
			depth = register.registerIcon("trevelations:puritydepth");
			orn = register.registerIcon("trevelations:purityorn");

		}

		@Override
		public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {return depth;}

		@Override
		public IIcon getOrnament(ItemStack itemstack) {return orn;}

		@Override
		public int getFocusColor(ItemStack itemstack) {return 0x6698FF;}


		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
			ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
			if (mop != null) {
				if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					if (!world.isRemote) {
						if (wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, false)) {

							int x = mop.blockX;
							int y = mop.blockY;
							int z = mop.blockZ;

							if (mop.sideHit == 0) {
								y--;
							}
							if (mop.sideHit == 1) {
								y++;
							}
							if (mop.sideHit == 2) {
								z--;
							}
							if (mop.sideHit == 3) {
								z++;
							}
							if (mop.sideHit == 4) {
								x--;
							}
							if (mop.sideHit == 5) {
								x++;
							}
							world.setBlock(x, y, z, ThaumicContent.blockWitor, 0, 2);
						}
					}
				}
			}
			player.swingItem();
			return itemstack;
		}

		@Override
		public String getSortingHelper(ItemStack itemStack) {return "ILLUMINATION";}

		@Override
		public AspectList getVisCost(ItemStack itemstack) {

			return new AspectList().add(Aspect.AIR, 50).add(Aspect.FIRE, 50);

		}

		@Override
		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
			return new FocusUpgradeType[0];
		}


	}

	public static class ItemFocusPurity extends ItemFocusBasic {

		private IIcon depth, orn;

		public ItemFocusPurity() {

			super();
			setUnlocalizedName("itemFocusPurity");
			setCreativeTab(mortvana.projectfluxgear.thaumic.common.ThaumicContent.thaumicRevelationsTab);

		}

		@Override
		public void registerIcons(IIconRegister register) {

			icon = register.registerIcon("trevelations:purityfocus");
			depth = register.registerIcon("trevelations:puritydepth");
			orn = register.registerIcon("trevelations:purityorn");

		}

		@Override
		public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {return depth;}

		@Override
		public IIcon getOrnament(ItemStack itemstack) {return orn;}

		@Override
		public int getFocusColor(ItemStack itemstack) {return 0x6698FF;}

		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {

			ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
			EntityPurity purityOrb = new EntityPurity(world, player);
			if (!world.isRemote) {
				if (wand.consumeAllVis(itemstack, player, getVisCost(itemstack), true, false)) {
					world.spawnEntityInWorld(purityOrb);
					world.playSoundAtEntity(purityOrb, "thaumcraft:ice", 0.3F, 0.8F + world.rand.nextFloat() * 0.1F);
				}
			}
			player.swingItem();
			return itemstack;
		}

		@Override
		public String getSortingHelper(ItemStack itemStack) { return "PURITY"; }

		@Override
		public AspectList getVisCost(ItemStack itemstack) {
			return new AspectList().add(Aspect.AIR, 500).add(Aspect.EARTH, 500).add(Aspect.FIRE, 500).add(Aspect.WATER, 500).add(Aspect.ORDER, 500).add(Aspect.ENTROPY, 500);
		}

		@Override
		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int i) {
			return new FocusUpgradeType[0];
		}

	}

	public static class EntityPurity extends EntityThrowable {

		public EntityPurity(World par1World) {

			super(par1World);

		}

		public EntityPurity(World par1World, EntityLivingBase par2EntityLivingBase) {

			super(par1World, par2EntityLivingBase);

		}

		public EntityPurity(World par1World, double par2, double par4, double par6) {

			super(par1World, par2, par4, par6);

		}

		@Override
		protected float getGravityVelocity() {

			return 0.001F;

		}

		@Override
		public void onUpdate() {

			if (this.worldObj.isRemote) {

				for (int i = 0; i < 3; i++) {

					Thaumcraft.proxy.wispFX2(this.worldObj, this.posX + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.posY + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.posZ + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, 0.3F, 2, true, false, 0.02F);

					double x2 = (this.posX + this.prevPosX) / 2.0D + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;
					double y2 = (this.posY + this.prevPosY) / 2.0D + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;
					double z2 = (this.posZ + this.prevPosZ) / 2.0D + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F;

					Thaumcraft.proxy.wispFX2(this.worldObj, x2, y2, z2, 0.3F, 2, true, false, 0.02F);

				}

			}

			super.onUpdate();

		}

		@Override
		protected void onImpact(MovingObjectPosition mop) {

			for (int i = 0; i < 9; i++) {

				float fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				float fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				float fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + fx * 8.0F, this.posY + fy * 8.0F, this.posZ + fz * 8.0F, 0.3F, 2, true, 0.02F);

				fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + fx * 8.0F, this.posY + fy * 8.0F, this.posZ + fz * 8.0F, 0.3F, 0, true, 0.02F);

				fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.3F;
				Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + fx * 8.0F, this.posY + fy * 8.0F, this.posZ + fz * 8.0F, 0.3F, 2, true, 0.02F);

			}

			if (!worldObj.isRemote) {

				PurityHelper.checkAndPurify(mop);
				setDead();

			}

		}

	}

	public static class ExubituraGenerator implements IWorldGenerator {

		@Override
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

			int X = chunkX * 16 + random.nextInt(128);
			int Z = chunkZ * 16 + random.nextInt(128);
			int Y = world.getHeightValue(X, Z);

			if (world.isAirBlock(X, Y, Z) && FluxGearContent.blockPlant.canBlockStay(world, X, Y, Z) && random.nextInt(1000) <= 10) {
				world.setBlock(X, Y, Z, FluxGearContent.blockPlant, 0, 2);
			}
		}
	}

	public static class ContainerHammer extends Container {

		public InventoryPlayer playerInv;
		public InventoryCrafting hammerInv;
		public IInventory resultInv;

		public ContainerHammer(EntityPlayer player) {

			playerInv = player.inventory;
			hammerInv = new InventoryCrafting(this, 2, 1);
			resultInv = new InventoryCraftResult();

			for (int hotbar = 0; hotbar < 9; hotbar++) {
				addSlotToContainer(new Slot(playerInv, hotbar, 8 + 18 * hotbar, 142));
			}
			for (int row = 0; row < 3; row++) {
				for (int collumn = 0; collumn < 9; collumn++) {
					addSlotToContainer(new Slot(playerInv, 9 + row * 9 + collumn, 8 + 18 * collumn, 84 + row * 18));
				}
			}

			addSlotToContainer(new SlotEssentia(hammerInv, 0, 80, 54));
			addSlotToContainer(new Slot(hammerInv, 1, 80, 33));
			addSlotToContainer(new SlotCrafting(player, hammerInv, resultInv, 0, 80, 12));

			onCraftMatrixChanged(hammerInv);

		}

		@Override
		public void onCraftMatrixChanged(IInventory craftingMatrix) {

			ItemStack essentia = craftingMatrix.getStackInSlot(0);
			ItemStack item = craftingMatrix.getStackInSlot(1);

			if (item != null) {
				if (!(item.getItem() instanceof ItemWardenArmor || item.getItem() instanceof ItemWardenicBlade)) {
					ItemStack repairedItem = new ItemStack(item.getItem());
					if (item.getItemDamage() != 0 && item.getItem().isRepairable()) {
						repairedItem.setItemDamage(0);
						resultInv.setInventorySlotContents(0, repairedItem);
					}
				} else if (essentia != null) {
					ItemStack infusedArmor = new ItemStack(item.getItem());
					String aspectKey = ((IEssentiaContainerItem) essentia.getItem()).getAspects(essentia).getAspects()[0].getName();
					if (WardenicChargeHelper.upgrades.containsKey(aspectKey)) {
						WardenicChargeHelper.setUpgradeOnStack(infusedArmor, aspectKey);
					}
					resultInv.setInventorySlotContents(0, infusedArmor);
				} else {
					resultInv.setInventorySlotContents(0, null);
				}
			} else {
				resultInv.setInventorySlotContents(0, null);
			}
		}

		@Override
		public void onContainerClosed(EntityPlayer player) {

			super.onContainerClosed(player);

			ItemStack essentia = this.hammerInv.getStackInSlotOnClosing(0);
			if (essentia != null) {
				player.dropPlayerItemWithRandomChoice(essentia, false);
			}

			ItemStack item = this.hammerInv.getStackInSlotOnClosing(1);
			if (item != null) {
				player.dropPlayerItemWithRandomChoice(item, false);
			}

		}

		@Override
		public boolean canInteractWith(EntityPlayer player) {return true;}

		@Override
		public ItemStack transferStackInSlot(EntityPlayer player, int slot) {return null;}

		@Override
		public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {

			if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
				return null;
			}
			return super.slotClick(slot, button, flag, player);

		}

	}

	public static class PurityHelper {

		public static boolean isTainted(Entity entity) {
			if (entity instanceof ITaintedMob) {
				return true;
			}
			return false;
		}

		public static boolean isTainted(MovingObjectPosition mop) {
			if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
				if (mop.entityHit != null) {
					return isTainted(mop.entityHit);
				}
			}
			return false;
		}

		public static void purifyEntity(Entity toPurify) {
			if (toPurify != null) {
				World world = toPurify.worldObj;
				if (isTainted(toPurify)) {
					if (!world.isRemote) {
						Entity purified = getPureState(toPurify);
						purified.setPositionAndRotation(toPurify.posX, toPurify.posY, toPurify.posZ, toPurify.rotationYaw, toPurify.rotationPitch);

						toPurify.setDead();
						world.spawnEntityInWorld(purified);
					}
				}
			}
		}

		public static void checkAndPurify(MovingObjectPosition mop) {
			if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
				purifyEntity(mop.entityHit);
			}
		}

		public static Entity getPureState(Entity entity) {
			if (entity instanceof EntityTaintChicken) {
				return new EntityChicken(entity.worldObj);
			}
			if (entity instanceof EntityTaintCow) {
				return new EntityCow(entity.worldObj);
			}
			if (entity instanceof EntityTaintCreeper) {
				return new EntityCreeper(entity.worldObj);
			}
			if (entity instanceof EntityTaintPig) {
				return new EntityPig(entity.worldObj);
			}
			if (entity instanceof EntityTaintSheep) {
				return new EntitySheep(entity.worldObj);
			}
			if (entity instanceof EntityTaintSpider) {
				return new EntitySpider(entity.worldObj);
			}
			if (entity instanceof EntityTaintVillager) {
				return new EntityVillager(entity.worldObj);
			}
			return entity;
		}
	}

	public static class SlotEssentia extends Slot {

		public SlotEssentia(IInventory inv, int id, int x, int y) {
			super(inv, id, x, y);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			if (stack.getItem() instanceof IEssentiaContainerItem) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static class WardenicUpgrade {

		public Aspect aspect;
		public Random random = new Random();

		public WardenicUpgrade(Aspect aspect) {this.aspect = aspect;}

		public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {}

		public void onTick(World world, EntityPlayer player, ItemStack stack) {}

		public void onAttacked(LivingHurtEvent event) {}

		public String getQuote() {

			return StatCollector.translateToLocal("upgrade." + aspect.getName() + ".quote");

		}
	}

	public static class WardenicUpgradeAir extends WardenicUpgrade {

		public WardenicUpgradeAir(Aspect aspect) {
			super(aspect);
		}

		@Override
		public void onTick(World world, EntityPlayer player, ItemStack stack) {
			super.onTick(world, player, stack);
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 0, 5));
			if (player.fallDistance > 5) {
				player.fallDistance = 5;
			}
		}
	}

	public static class WardenicUpgradeArmor extends WardenicUpgrade {

		public WardenicUpgradeArmor(Aspect aspect) {super(aspect);}

		@Override
		public void onAttacked(LivingHurtEvent event) {
			super.onAttacked(event);
			if (event.source.getEntity() != null) {
				DamageSource damageSource = new DamageSourceFluxGear("warden", event.entity);
				event.source.getEntity().attackEntityFrom(damageSource, event.ammount / 2);
			}
		}
	}

	public static class WardenicUpgradeDestruction extends WardenicUpgrade {

		//TODO: Something...
		public WardenicUpgradeDestruction(Aspect aspect) {
			super(aspect);
		}
	}

	public static class WardenicUpgradeEarth extends WardenicUpgrade {

		public WardenicUpgradeEarth(Aspect aspect) {super(aspect);}

		@Override
		public void onTick(World world, EntityPlayer player, ItemStack stack) {
			super.onTick(world, player, stack);
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 0, 5));
		}

		@Override
		public void onAttacked(LivingHurtEvent event) {
			super.onAttacked(event);
			if (event.source.damageType.equals("fall")) {
				List entities = new ArrayList<Entity>();
				DamageSource damageSource = new DamageSourceFluxGear("warden", event.entity);
				entities = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(event.entity, AxisAlignedBB.getBoundingBox(event.entity.posX - 6, event.entity.posY - 6, event.entity.posZ - 6, event.entity.posX + 6, event.entity.posY + 6, event.entity.posZ + 6));
				for (int i = 0; i < entities.size(); i++) {
					if (entities.get(i) instanceof Entity) {
						((Entity) entities.get(i)).attackEntityFrom(damageSource, 4);
					}
				}
			}
		}
	}

	public static class WardenicUpgradeFire extends WardenicUpgrade {

		public WardenicUpgradeFire(Aspect aspect) {super(aspect);}

		@Override
		public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
			super.onAttack(stack, player, entity);
			entity.setFire(5);
		}

		@Override
		public void onTick(World world, EntityPlayer player, ItemStack stack) {
			super.onTick(world, player, stack);
			if (player.isBurning()) {
				player.extinguish();
			}
		}

		@Override
		public void onAttacked(LivingHurtEvent event) {
			super.onAttacked(event);
			if (event.source.getEntity() != null) {
				event.source.getEntity().setFire(2);
			}
		}
	}

	public static class WardenicUpgradeHeal extends WardenicUpgrade {

		public WardenicUpgradeHeal(Aspect aspect) {super(aspect);}

		@Override
		public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
			super.onAttack(stack, player, entity);
			player.heal(2);
		}
	}

	public static class WardenicUpgradeWarden extends WardenicUpgrade {

		public WardenicUpgradeWarden(Aspect aspect) {
			super(aspect);
		}

		@Override
		public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
			super.onAttack(stack, player, entity);
			if (entity instanceof IEldritchMob || entity instanceof ITaintedMob || entity instanceof EntityCultist || entity instanceof EntityCultistLeader || entity instanceof EntityEldritchCrab || entity instanceof EntityInhabitedZombie) {
				DamageSource damageSource = new DamageSourceFluxGear("warden", player);
				entity.attackEntityFrom(damageSource, 20);
			}
		}

		@Override
		public void onTick(World world, EntityPlayer player, ItemStack stack) {
			super.onTick(world, player, stack);
			if (player.isPotionActive(Config.potionDeathGazeID)) {
				if (random.nextInt(5) == 1) {
					player.removePotionEffect(Config.potionDeathGazeID);
				}
			}
			if (player.isPotionActive(Config.potionTaintPoisonID)) {
				if (random.nextInt(5) == 1) {
					player.removePotionEffect(Config.potionTaintPoisonID);
				}
			}
			if (player.isPotionActive(Potion.wither.getId())) {
				if (random.nextInt(5) == 1) {
					player.removePotionEffect(Potion.wither.getId());
				}
			}
		}

		@Override
		public void onAttacked(LivingHurtEvent event) {
			super.onAttacked(event);
			if (event.source.getEntity() instanceof IEldritchMob || event.source.getEntity() instanceof ITaintedMob || event.source.getEntity() instanceof EntityCultist || event.source.getEntity() instanceof EntityCultistLeader || event.source.getEntity() instanceof EntityEldritchCrab || event.source.getEntity() instanceof EntityInhabitedZombie) {
				event.setCanceled(true);
			}
		}
	}

	public static class WardenicUpgradeWater extends WardenicUpgrade {

		public WardenicUpgradeWater(Aspect aspect) {super(aspect);}

		@Override
		public void onTick(World world, EntityPlayer player, ItemStack stack) {
			super.onTick(world, player, stack);
			if (player.isInWater()) {
				player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 0, 5));
			}
		}
	}

	public static class WardenicChargeHelper {

		public static HashMap<String, WardenicUpgrade> upgrades = new HashMap<String, WardenicUpgrade>();

		public static void addUpgrade(WardenicUpgrade upgrade) {
			addUpgrade(upgrade.aspect.getName(), upgrade);
		}

		public static void addUpgrade(String key, WardenicUpgrade upgrade) {
			upgrades.put(key, upgrade);
		}

		public static WardenicUpgrade getUpgrade(ItemStack stack) {
			if (stack.stackTagCompound != null) {
				if (stack.stackTagCompound.hasKey("upgrade")) {
					return upgrades.get(stack.stackTagCompound.getString("upgrade"));
				} else {
					return upgrades.get(WARDEN.getName());
				}
			} else {
				return upgrades.get(WARDEN.getName());
			}
		}

		public static void setUpgradeOnStack(ItemStack stack, String key) {
			if (stack.stackTagCompound == null) {
				stack.setTagCompound(new NBTTagCompound());
			}
			stack.stackTagCompound.setString("upgrade", key);
		}

	}

	public static class WardenicChargeEvents {

		private Random random = new Random();

		public static void init() {
			MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());
		}

		@SubscribeEvent
		public void onTick(LivingEvent.LivingUpdateEvent event) {
			if (event.entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.entity;
				for (int i = 0; i < 5; i++) {
					if (player.getEquipmentInSlot(i) != null && (player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor || player.getEquipmentInSlot(i).getItem() instanceof ItemWardenicBlade) && (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage()) && (random.nextInt(50) == 49)) {
						player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() - 1);
					}
				}
			}
		}

		@SubscribeEvent
		public void onHurt(LivingHurtEvent event) {
			if (event.entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.entity;
				for (int i = 1; i < 5; i++) {
					if (player.getEquipmentInSlot(i) != null && (player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor) && (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage())) {
						player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() + 1);
						WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(i)).onAttacked(event);
					}
				}
			}
		}
	}

	public static class FluxGearResearchItem extends ResearchItem {

		public int warp = 0;

		public FluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ItemStack icon) {
			super(key, category, tags, column, row, complexity, icon);
		}

		public FluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ResourceLocation icon) {
			super(key, category, tags, column, row, complexity, icon);
		}

		public void setWarp(int warp) {
			this.warp = warp;
		}


		@Override
		@SideOnly(Side.CLIENT)
		public String getName() {
			return StatCollector.translateToLocal("fluxgearresearch." + key + ".name");
		}

		@Override
		@SideOnly(Side.CLIENT)
		public String getText() {
			return (FluxGearConfig.useThaumicTooltips ? StatCollector.translateToLocal(getPrefix()) : "") + StatCollector.translateToLocal("fluxgearresearch." + key + ".lore");
		}

		String getPrefix() {
			return "[PFG] ";
		}

		@Override
		public ResearchItem setPages(ResearchPage... pages) {
			for (ResearchPage page : pages) {
				if (page.type == ResearchPage.PageType.TEXT) {
					page.text = "fluxgearresearch.page." + key + "." + page.text + ".text";
				}
			}
			checkInfusion(true, pages);
			return super.setPages(pages);
		}

		public ResearchItem setPages(boolean checkInfusion, ResearchPage... pages) {
			for (ResearchPage page : pages) {
				if (page.type == ResearchPage.PageType.TEXT) {
					page.text = "fluxgearresearch.page." + key + "." + page.text + ".text";
				}
			}
			checkInfusion(checkInfusion, pages);
			return super.setPages(pages);
		}

		public void checkInfusion(boolean checkInfusion, ResearchPage... pages) {
			for (ResearchPage page : pages) {
				if (checkInfusion && page.type == ResearchPage.PageType.INFUSION_CRAFTING) {
					if (parentsHidden == null || parentsHidden.length == 0)
						parentsHidden = new String[] { "INFUSION" };
					else {
						String[] newParents = new String[parentsHidden.length + 1];
						newParents[0] = "INFUSION";
						for (int i = 0; i < parentsHidden.length; i++) {
							newParents[i + 1] = parentsHidden[i];
						}
						parentsHidden = newParents;
					}
				}
			}
		}

		public void registerResearch() {
			registerResearchItem();
			if (warp != 0) {
				ThaumcraftApi.addWarpToResearch(key, warp);
			}
		}
	}

	public static class VortexFluxGearResearchItem extends FluxGearResearchItem {


		public static List<String> Blacklist = new ArrayList<String>();

		static {
			Blacklist.add("MINILITH");
		}

		public VortexFluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ItemStack icon) {
			super(key, category, tags, column, row, complexity, icon);
			setConcealed();
		}

		public VortexFluxGearResearchItem(String key, String category, AspectList tags, int column, int row, int complexity, ResourceLocation icon) {
			super(key, category, tags, column, row, complexity, icon);
			setConcealed();
		}

		@Override
		public ResearchItem setPages(ResearchPage... pages) {
			List<String> requirements = parentsHidden == null || parentsHidden.length == 0 ? new ArrayList() : new ArrayList(Arrays.asList(parentsHidden));
			if (!isAutoUnlock())
				for (String categoryStr : ResearchCategories.researchCategories.keySet()) {
					ResearchCategoryList category = ResearchCategories.researchCategories.get(categoryStr);
					for (String tag : category.research.keySet()) {
						ResearchItem research = category.research.get(tag);
						if (research.isLost() || (research.parentsHidden == null && research.parents == null) || research.isVirtual() || research instanceof VortexFluxGearResearchItem || requirements.contains(tag))
							continue;
						if (research.getAspectTriggers() != null || research.getEntityTriggers() != null || research.getItemTriggers() != null) {
							continue;
						}
						if (research.category.equals("FLUXGEAR") || research.category.equals("TT_CATEGORY") || research.category.equals("TX") || /*research.category.equals("rotarycraft") || research.category.equals("chromaticraft") ||*/ research.category.equals("FORBIDDEN") || /*research.category.equals("automagy") ||*/ research.category.equals("MAGICBEES") || research.category.equals("RAILCRAFT") || /*research.category.equals("op style wands") ||*/ research.category.equals("AOBD") || research.category.equals("trevelations") || /*research.category.equals("thaumic horizons") ||*/ research.category.equals("BASICS") || research.category.equals("GOLEMANCY") || research.category.equals("ARTIFICE") || research.category.equals("ALCHEMY") || research.category.equals("THAUMATURGY")) {
							boolean found = false;
							for (String black : Blacklist)
								if (tag.startsWith(black)) {
									found = true;
								}
							if (tag.endsWith("VORTEX"))
								found = true;
							if (found)
								continue;
							requirements.add(tag);
						}
					}
				}
			parentsHidden = requirements.toArray(new String[requirements.size()]);
			return super.setPages(false, pages);
		}

		@Override
		String getPrefix() {
			return super.getPrefix() + ".vortex";
		}
	}
}
