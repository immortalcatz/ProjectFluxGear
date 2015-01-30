package mortvana.mechstoneworks.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import mortvana.fluxgearcore.item.ItemFluxGear;

import mortvana.mechstoneworks.block.BlockDecorStone;
import mortvana.mechstoneworks.block.BlockPaintedStone;
import mortvana.mechstoneworks.block.ItemBlockPaintedStone;
import mortvana.mechstoneworks.item.tool.ItemPaintbrush;
import mortvana.mechstoneworks.recipe.RecipePaintbrush;

public class MechStoneworksContent {

	public void loadStuff() {

		itemMaterial = (ItemFluxGear) new ItemFluxGear("assets/fluxgeartweaks").setUnlocalizedName("material").setCreativeTab(MechanicsStoneworks.generalTab);

		multicoreProcessor = itemMaterial.addItem(0, "multicoreProcessor");
		disassemblyCore = itemMaterial.addItem(1, "disassemblyCore");
		enderMiningCore = itemMaterial.addItem(2, "enderMiningCore");
		enderCompCore = itemMaterial.addItem(3, "enderCompCore");

		registerRocks();

		coloredCobble = new BlockPaintedStone(Material.rock, 2.0F, "stone_cobble", "stone.cobble").setBlockName("paintedstone.cobble");
		GameRegistry.registerBlock(coloredCobble, ItemBlockPaintedStone.class, "paintedstone.cobble");

		coloredStone = new BlockPaintedStone(Material.rock, 1.5F, "stone_raw", "stone.raw", coloredCobble).setBlockName("paintedstone.raw");
		GameRegistry.registerBlock(coloredStone, ItemBlockPaintedStone.class, "paintedstone.raw");

		coloredMossCobble = new BlockPaintedStone(Material.rock, 2.0F, "stone_mosscobble", "stone.mosscobble").setBlockName("paintedstone.mosscobble");
		GameRegistry.registerBlock(coloredMossCobble, ItemBlockPaintedStone.class, "paintedstone.mosscobble");

		coloredStoneBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_brick", "stone.brick").setBlockName("paintedstone.brick");
		GameRegistry.registerBlock(coloredStoneBrick, ItemBlockPaintedStone.class, "paintedstone.brick");

		coloredMossStoneBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_mossbrick", "stone.mossbrick").setBlockName("paintedstone.mossbrick");
		GameRegistry.registerBlock(coloredMossStoneBrick, ItemBlockPaintedStone.class, "paintedstone.mossbrick");

		coloredCrackedStoneBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_crackedbrick", "stone.crackedbrick").setBlockName("paintedstone.crackedbrick");
		GameRegistry.registerBlock(coloredCrackedStoneBrick, ItemBlockPaintedStone.class, "paintedstone.crackedbrick");

		coloredStoneRoad = new BlockPaintedStone(Material.rock, 1.5F, "stone_road", "stone.road").setBlockName("paintedstone.road");
		GameRegistry.registerBlock(coloredStoneRoad, ItemBlockPaintedStone.class, "paintedstone.road");

		coloredStoneFancyBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_fancy", "stone.fancy").setBlockName("paintedstone.fancy");
		GameRegistry.registerBlock(coloredStoneFancyBrick, ItemBlockPaintedStone.class, "paintedstone.fancy");

		coloredStoneSquareBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_square", "stone.chiseled").setBlockName("paintedstone.chiseled");
		GameRegistry.registerBlock(coloredStoneSquareBrick, ItemBlockPaintedStone.class, "paintedstone.chiseled");

		clayBrickSmall = new BlockPaintedStone(Material.rock, 2.0F, "clay_smallbrick", "clay.brick").setBlockName("paintedstone.clay.smallbrick");
		GameRegistry.registerBlock(clayBrickSmall, ItemBlockPaintedStone.class, "paintedstone.clay.smallbrick");

		paintbrush = new ItemPaintbrush().setUnlocalizedName("paintedstone.brush");
		GameRegistry.registerItem(paintbrush, "paintbrush");

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(paintbrush), "w", "s", 'w', new ItemStack(Blocks.wool, 1, 32767), 's', "stickWood"));

		for(int i = 0; i < 16; ++i) {
			FurnaceRecipes.smelting().func_151393_a(coloredCobble, new ItemStack(coloredStone, 1, i), 0.2F);

			GameRegistry.addRecipe(new ItemStack(coloredStoneBrick, 4, i), "##", "##", '#', new ItemStack(coloredStone, 1, i));

			OreDictionary.registerOre("stone", new ItemStack(coloredStone, 1, i));
			OreDictionary.registerOre("cobblestone", new ItemStack(coloredCobble, 1, i));

			int dyeAmount = 1;

			while (dyeAmount <= 8) {
				Object[] input = new Object[dyeAmount + 1];
				input[0] = paintbrush;
				String dyeType = "dye" + MechanicsStoneworks.dyeTypes[i];
				switch (dyeAmount) {
					case 8:
						input[8] = dyeType;
					case 7:
						input[7] = dyeType;
					case 6:
						input[6] = dyeType;
					case 5:
						input[5] = dyeType;
					case 4:
						input[4] = dyeType;
					case 3:
						input[3] = dyeType;
					case 2:
						input[2] = dyeType;
					case 1:
						input[1] = dyeType;
					default:
						GameRegistry.addRecipe(new RecipePaintbrush(paintbrush, i + 1, input));
						++dyeAmount;
				}
			}
		}
	}

	public void registerRocks() {
		//stoneRaw = new BlockDecorStone(Material.rock, stoneHardness, /*stoneResistance,*/ "stone_raw", "stone.raw" /*, overlay, */).setBlockName("mechanicsstoneworks.decorStoneRaw");
	}

	//For Now
	public static float stoneHardness = 1.5F;

	public static Block coloredStone;
	public static Block coloredCobble;
	public static Block coloredMossCobble;
	public static Block coloredStoneBrick;
	public static Block coloredMossStoneBrick;
	public static Block coloredCrackedStoneBrick;
	public static Block coloredStoneRoad;
	public static Block coloredStoneFancyBrick;
	public static Block coloredStoneSquareBrick;
	public static Block clayBrickSmall;

	public static Block stoneRaw;
	public static Block stoneCobble;
	public static Block stoneCleanBricks;
	public static Block stoneCleanFittedBricks;
	public static Block stoneCleanChiseledBricks;
	public static Block stoneEtchedBricks;
	public static Block stoneSmooth;
	public static Block stoneCubed;
	public static Block stoneWideBricks;
	public static Block stoneArtisanChiseled;
	public static Block stoneArtisanRunic;
	public static Block stoneArtisanFocal;
	public static Block stoneEnergistic;
	public static Block stoneOffsetCleanTiles;
	public static Block stoneMixedCleanTiles;
	public static Block stoneOffsetFancyBricks;
	public static Block stoneDetailedBricks;
	public static Block stoneDetailedChiseledBricks;
	public static Block stoneDetailedFittedBricks;
	public static Block stoneDetailedTiles;
	public static Block stoneEngraved;
	public static Block stoneInscribed;
	public static Block stoneGouged;
	public static Block stoneEmbossed;
	public static Block stoneCentered;
	public static Block stoneRaised;
	public static Block stoneEtched;
	public static Block stoneCarved;
	public static Block stoneFancyBricks;
	public static Block stoneCleanTiles;
	public static Block stoneArtisanTiles;
	public static Block stoneSmallOffsetTiles;


	public static Item paintbrush;

	public static ItemFluxGear itemMaterial;

	public static ItemStack multicoreProcessor;
	public static ItemStack disassemblyCore;
	public static ItemStack enderMiningCore;
	public static ItemStack enderCompCore;

	final static short WILD = Short.MAX_VALUE;
}
