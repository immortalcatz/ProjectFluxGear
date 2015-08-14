package mortvana.legacy.util.block.metadata;

import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public interface IBlockMetadata extends ITileEntityProvider {
	public int getPlacedMeta(ItemStack itemstack, World world, int x, int y, int z, ForgeDirection direction);
	public String getBlockName(ItemStack itemstack);
	public void getBlockTooltip(ItemStack itemstack, List list);
}
