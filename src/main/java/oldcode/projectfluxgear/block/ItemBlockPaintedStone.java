package oldcode.projectfluxgear.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import oldcode.projectfluxgear.core.ProjectFluxGear;

public class ItemBlockPaintedStone extends ItemBlock {
	public ItemBlockPaintedStone(Block block) {
		super(block);
		setMaxDamage(0).setHasSubtypes(true);
	}

	public int getMetadata(int meta) {
		return meta;
	}

	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "." + ProjectFluxGear.colorNames[par1ItemStack.getItemDamage()];
	}
}