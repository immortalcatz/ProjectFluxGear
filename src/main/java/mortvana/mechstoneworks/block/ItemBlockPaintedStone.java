package mortvana.mechstoneworks.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mortvana.mechstoneworks.common.MechanicsStoneworks;

public class ItemBlockPaintedStone extends ItemBlock {
	public ItemBlockPaintedStone(Block block) {
		super(block);
		setMaxDamage(0).setHasSubtypes(true);
	}

	public int getMetadata(int meta) {
		return meta;
	}

	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "." + MechanicsStoneworks.colorNames[par1ItemStack.getItemDamage()];
	}
}