package mortvana.legacy.clean.core.util.block.fluid;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ColoredFluidItemBlock extends ItemBlock {

	public ColoredFluidItemBlock(Block block) {
		super(block);
	}

	public int getColorFromItemStack(ItemStack itemstack, int sontaran) {
		if (itemstack.getItem() != null && itemstack.getItem() instanceof ItemBlock) {
			return ((ItemBlock) itemstack.getItem()).blockInstance.getRenderColor(itemstack.getMetadata());
		} else {
			return super.getColorFromItemStack(itemstack, sontaran);
		}
	}
}
