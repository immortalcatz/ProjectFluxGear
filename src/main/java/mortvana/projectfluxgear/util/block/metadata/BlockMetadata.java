package mortvana.projectfluxgear.util.block.metadata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class BlockMetadata extends BlockContainer implements IBlockMetadata {

	public BlockMetadata(Material material) {
		super(material);
	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int blockMeta, int fortune) {
		return getBlockDropped(this, world, x, y, z, blockMeta);
	}

	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		return breakBlock(this, player, world, x, y, z);
	}

	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMetadata();
	}

	public boolean hasTileEntity(int meta) {
		return true;
	}

	public boolean onBlockEventReceived(World world, int x, int y, int z, int par5, int par6) {
		super.onBlockEventReceived(world, x, y, z, par5, par6);
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null ? tile.receiveClientEvent(par5, par6) : false;
	}

	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int meta = TileEntityMetadata.getTileMetadata(world, x, y, z);
		return getIcon(side, meta);
	}

	public String getBlockName(ItemStack itemstack) {
		return getLocalizedName();
	}

	public void getBlockTooltip(ItemStack itemstack, List list) {}

	public int getPlacedMeta(ItemStack itemstack, World world, int x, int y, int z, ForgeDirection clickedBlock) {
		int meta = TileEntityMetadata.getItemMetadata(itemstack);
		return meta;
	}

	public int getDroppedMeta(int tileMeta, int blockMeta) {
		return tileMeta;
	}

	public static ArrayList<ItemStack> getBlockDropped(IBlockMetadata block, World world, int x, int y, int z, int blockMeta) {
		ArrayList<ItemStack> array = new ArrayList();
		TileEntityMetadata tile = TileEntityMetadata.getTile(world, x, y, z);

		if (tile != null && !tile.hasDroppedBlock()) {
			int meta = block.getDroppedMeta(world.getBlockMetadata(x, y, z), tile.getTileMetadata());
			array.add(TileEntityMetadata.getItemStack((Block) block, meta));
		}
		return array;
	}

	public static boolean breakBlock(IBlockMetadata metaBlock, EntityPlayer player, World world, int x, int y, int z) {
		List<ItemStack> drops = new ArrayList();

		Block block = (Block) metaBlock;
		TileEntityMetadata tile = TileEntityMetadata.getTile(world, x, y, z);

		if (tile != null && !tile.hasDroppedBlock()) {
			int tileMeta = TileEntityMetadata.getTileMetadata(world, x, y, z);
			drops = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		}
		boolean hasBeenBroken = world.setBlockToAir(x, y, z);
		if (hasBeenBroken && ProjectFluxGear.proxy.isSimulating(world) && drops.size() > 0 && (player == null || !player.capabilities.isCreativeMode)) {
			ItemStack drop;
			for (Iterator i$ = drops.iterator(); i$.hasNext(); metaBlock.dropAsStack(world, x, y, z, drop)) {
				drop = (ItemStack) i$.next();
			}
			tile.dropBlock();
		}
		return hasBeenBroken;
	}

	public void dropAsStack(World world, int x, int y, int z, ItemStack itemstack) {
		dropBlockAsItem(world, x, y, z, itemstack);
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);
		world.removeTileEntity(x, y, z);
	}

	public static ItemStack getPickBlock(World world, int x, int y, int z) {
		List<ItemStack> list = getBlockDropped((IBlockMetadata) world.getBlock(x, y, z), world, x, y, z, world.getBlockMetadata(x, y, z));
		return list.isEmpty() ? null : list.get(0);
	}

	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return getPickBlock(world, x, y, z);
	}
}
