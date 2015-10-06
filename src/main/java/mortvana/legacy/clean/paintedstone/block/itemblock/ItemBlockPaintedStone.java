package mortvana.legacy.clean.paintedstone.block.itemblock;

import mortvana.melteddashboard.util.helpers.ColorHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockPaintedStone extends ItemBlock {
    public ItemBlockPaintedStone(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int getMetadata(int meta) {
        return meta;
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        return super.getUnlocalizedName() + "." + ColorHelper.LOWER_PAINT_NAMES[itemstack.getItemDamage()];
    }
}