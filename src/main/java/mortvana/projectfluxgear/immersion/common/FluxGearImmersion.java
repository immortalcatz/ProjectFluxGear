package mortvana.projectfluxgear.immersion.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.util.ConfigBase;
import mortvana.melteddashboard.util.IConfigInitialized;
import mortvana.projectfluxgear.immersion.block.BlockPaintedStone;
import mortvana.projectfluxgear.immersion.item.ItemPaintbrush;

public class FluxGearImmersion implements IConfigInitialized {

	@Override
	public void preInit(ConfigBase config) {
		paintedStoneTab = new FluxGearCreativeTab("PFG-PaintedStone", "fluxgear.paintedstone", new ItemStack(Items.potato));

		coloredCobble = new BlockPaintedStone(Material.rock, 2.0F, "stone_cobble", "stone.cobble", coloredCobble, "fluxgear.paintedcobble").addSmelting(coloredStone).addOreDict("cobblestone");
		coloredStone = new BlockPaintedStone(Material.rock, 1.5F, "stone_raw", "stone.raw", coloredCobble, "fluxgear.paintedstone").addOreDict("stone");
		coloredMossyCobble = new BlockPaintedStone(Material.rock, 2.0F, "stone_mossycobble", "stone.mossycobble", coloredMossyCobble, "fluxgear.paintedmossycobble");
		coloredStoneBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_brick", "stone.brick", coloredStoneBrick, "fluxgear.paintedstonebrick").addCubingRecipe(coloredStone);
		coloredCrackedBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_crackedbrick", "stone.crackedbrick", coloredCrackedBrick, "fluxgear.paintedcrackedbrick");
		coloredMossyBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_mossybrick", "stone.mossybrick", coloredMossyBrick, "fluxgear.paintedmossybrick");
		coloredFancyBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_fancy", "stone.fancybrick", coloredFancyBrick, "fluxgear.paintedfancybrick");
		coloredRoad = new BlockPaintedStone(Material.rock, 1.5F, "stone_road", "stone.road", coloredRoad, "fluxgear.paintedroad");
		coloredSquareBrick = new BlockPaintedStone(Material.rock, 1.5F, "stone_chiseled", "stone.square", coloredSquareBrick , "fluxgear.paintedchiseledbrick");
		coloredClayBrick = new BlockPaintedStone(Material.rock, 1.5F, "clay_smallbrick", "clay.brick", coloredClayBrick, "fluxgear.paintedclaybrick");

		paintbrush = new ItemPaintbrush().register();
	}

	@Override
	public void init(ConfigBase config) {

	}

	@Override
	public void postInit(ConfigBase config) {

	}

	public static FluxGearCreativeTab paintedStoneTab;

	public static Block coloredCobble;
	public static Block coloredStone;
	public static Block coloredMossyCobble;
	public static Block coloredStoneBrick;
	public static Block coloredCrackedBrick;
	public static Block coloredMossyBrick;
	public static Block coloredFancyBrick;
	public static Block coloredRoad;
	public static Block coloredSquareBrick;
	public static Block coloredClayBrick;

	public static Item paintbrush;
}
