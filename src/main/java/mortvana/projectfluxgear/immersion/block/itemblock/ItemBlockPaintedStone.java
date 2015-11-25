package mortvana.projectfluxgear.immersion.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.util.helpers.ColorHelper;

public class ItemBlockPaintedStone extends ItemBlock {
	public ItemBlockPaintedStone(Block block) {
		super(block);
		setMaxDurability(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return super.getUnlocalizedName() + "." + ColorHelper.LOWER_PAINT_NAMES[itemstack.getMetadata()];
	}
}