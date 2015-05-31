package oldcode.projectfluxgear.util.block.metadata;

import java.util.List;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract interface IBlockMetadata extends ITileEntityProvider {

	public abstract int getPlacedMeta(ItemStack itemstack, World world, int x, int y, int z, ForgeDirection direction);

	public abstract int getDroppedMeta(int tileMeta, int blockMeta);

	public abstract String getBlockName(ItemStack itemstack);

	public abstract void getBlockTooltip(ItemStack itemstack, List list);

	public abstract void dropAsStack(World world, int x, int y, int z, ItemStack itemstack);
}
