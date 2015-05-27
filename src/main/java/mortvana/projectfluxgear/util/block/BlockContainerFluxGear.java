package mortvana.projectfluxgear.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import mortvana.projectfluxgear.util.block.BlockFluxGear;
import mortvana.projectfluxgear.util.block.EnumBlockType;

public class BlockContainerFluxGear extends BlockFluxGear implements ITileEntityProvider {

	public static TileEntity tile;

	public BlockContainerFluxGear(Material material, CreativeTabs tab, TileEntity tile) {
		super(material, tab);
		this.tile = tile;
		isBlockContainer = true;
	}


	public BlockContainerFluxGear(Material material, CreativeTabs tab, EnumBlockType type, TileEntity tile) {
		super(material, tab, type);
		this.tile = tile;
		isBlockContainer = true;
	}

	public BlockContainerFluxGear(Material material, CreativeTabs tab, boolean spawn, boolean base, boolean colorized, TileEntity tile) {
		super(material, tab, spawn, base, colorized);
		this.tile = tile;
		isBlockContainer = true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return tile;
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
	}

	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
		world.removeTileEntity(x, y, z);
	}

	public boolean onBlockEventReceived(World world, int x, int y, int z, int p_149696_5_, int p_149696_6_) {
		super.onBlockEventReceived(world, x, y, z, p_149696_5_, p_149696_6_);
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null && tile.receiveClientEvent(p_149696_5_, p_149696_6_);
	}
}
