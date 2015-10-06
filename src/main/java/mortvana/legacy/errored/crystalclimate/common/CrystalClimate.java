package mortvana.legacy.errored.crystalclimate.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import mortvana.legacy.clean.crystalclimate.block.itemblock.AggregatorItem;
import mortvana.legacy.clean.crystalclimate.item.EssenceCrystal;
import mortvana.legacy.clean.crystalclimate.block.itemblock.TerraformerItem;
import mortvana.legacy.clean.crystalclimate.common.CrystalProxy;
import mortvana.legacy.clean.crystalclimate.util.TabCrystal;
import mortvana.legacy.errored.crystalclimate.block.tileentity.*;
import mortvana.legacy.clean.crystalclimate.block.*;
import mortvana.legacy.clean.crystalclimate.block.tileentity.*;
import mortvana.legacy.errored.crystalclimate.block.*;

@Mod(modid = "CrystalClimate", name = "CrystalClimate", version = "Byakuren")
public class CrystalClimate {

	@Instance("CrystalClimate")
	public static CrystalClimate instance;

	public static TabCrystal tab;

	@SidedProxy(clientSide = "crystal.util.CrystalProxyClient", serverSide = "crystal.util.CrystalProxy")
	public static CrystalProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		tab = new TabCrystal("crystalclimate");
		createContent();
		createRecipes();
	}


	public static void createContent() {
		//Blocks
		essenceExtractor = new EssenceExtractor().setHardness(12f).setUnlocalizedName("extractor.essence");
		GameRegistry.registerBlock(essenceExtractor, "extractor.essence");
		GameRegistry.registerTileEntity(EssenceExtractorLogic.class, "extractor.essence");

		terraformer = new Terraformer().setHardness(50f).setUnlocalizedName("terraformer");
		GameRegistry.registerBlock(terraformer, TerraformerItem.class, "terraformer");
		GameRegistry.registerTileEntity(TerraformerLogic.class, "Crystal:Terraformer");
		GameRegistry.registerTileEntity(TerraleecherLogic.class, "Crystal:Terraleecher");
		GameRegistry.registerTileEntity(TerragrowerLogic.class, "Crystal:Terragrower");

		aggregator = new Aggregator().setHardness(10f).setUnlocalizedName("aggregator.redstone");
		GameRegistry.registerBlock(aggregator, AggregatorItem.class, "aggregator");
		GameRegistry.registerTileEntity(RedstoneAggregator.class, "aggregator.redstone");

		crystalBlock = new CrystalBlock().setHardness(1.0f).setUnlocalizedName("crystal");
		GameRegistry.registerBlock(crystalBlock, CrystalBlockItem.class, "crystalblock");
		GameRegistry.registerTileEntity(CrystalLogic.class, "Crystal:Crystallogic");

		ash = new Ash().setHardness(0.1F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("ash").setTextureName("crystal:ash");
		GameRegistry.registerBlock(ash, "ash");
		ashBlock = new Block(Material.sand).setHardness(0.2F).setStepSound(Block.soundSandFootstep).setUnlocalizedName("ash").setCreativeTab(CrystalClimate.tab).setTextureName("crystal:ash");
		GameRegistry.registerBlock(ashBlock, "ashBlock");

		finiteWaterFluid = new Fluid("water.finite");
		if (!FluidRegistry.registerFluid(finiteWaterFluid))
			finiteWaterFluid = FluidRegistry.getFluid("water.finite");
		finiteWater = new FiniteWater(PHCrystal.finiteWater, finiteWaterFluid, Material.water).setUnlocalizedName(Blocks.water.getUnlocalizedName()).setTextureName("water_still");
		finiteWaterFluid.setBlockID(finiteWater).setUnlocalizedName(Blocks.water.getUnlocalizedName());
		FluidContainerRegistry.registerFluidContainer(new FluidContainerRegistry.FluidContainerData(new FluidStack(finiteWaterFluid, 1000), new ItemStack(Item.bucketWater), new ItemStack(Item.bucketEmpty)));
		GameRegistry.registerBlock(finiteWater, "water.finite");

		leechedStone = new Block(Material.rock).setHardness(3F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("stone.leeched")
				.setCreativeTab(CrystalClimate.tab).setTextureName("crystal:leechedstone");
		GameRegistry.registerBlock(leechedStone, "leechedstone");

		sugarBlock = new SugarBlock().setHardness(0.3F).setUnlocalizedName("sugar");
		GameRegistry.registerBlock(sugarBlock, ItemBlockWithMetadata.class, "sugarblock");

		//Items
		essenceCrystal = (EssenceCrystal) new EssenceCrystal().setUnlocalizedName("crystal.essence");
		essenceStack = new ItemStack(essenceCrystal);
	}

	public static void createRecipes() {
		GameRegistry.addRecipe(new ItemStack(essenceExtractor, 1, 0), " b ", "eme", "mmm", 'b', Items.book, 'e', Items.emerald, 'm', Blocks.end_stone);
		GameRegistry.addRecipe(new ItemStack(sugarBlock, 1, 7), "###", "###", "###", '#', Items.sugar);
		GameRegistry.addRecipe(new ItemStack(aggregator, 1, 0), "#d#", "#r#", "#e#", '#', Items.quartz, 'd', Items.diamond, 'e', Items.ender_pearl, 'r', Blocks.redstone_block);
		GameRegistry.addRecipe(new ItemStack(aggregator, 1, 0), "#e#", "#r#", "#d#", '#', Items.quartz, 'd', Items.diamond, 'e', Items.ender_pearl, 'r', Blocks.redstone_block);
	}

	public static Block essenceExtractor;
	public static Block terraformer;
	public static Block aggregator;

	public static Block ash;
	public static Block ashBlock;
	public static Block finiteWater;
	public static Block leechedStone;
	public static Block crystalBlock;
	public static Block sugarBlock;

	public static Fluid finiteWaterFluid;

	public static EssenceCrystal essenceCrystal;
	public static ItemStack essenceStack;
}
