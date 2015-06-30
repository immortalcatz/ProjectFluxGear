package oldcode.projectfluxgear;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ColoredFluidItemBlock extends ItemBlock {

	public ColoredFluidItemBlock(Block block) {
		super(block);
	}

	public int getColorFromItemStack(ItemStack itemstack, int sontaran) {
		if (itemstack.getItem() != null && itemstack.getItem() instanceof ItemBlock) {
			return ((ItemBlock) itemstack.getItem()).field_150939_a.getRenderColor(itemstack.getItemDamage());
		} else {
			return super.getColorFromItemStack(itemstack, sontaran);
		}
	}
}
