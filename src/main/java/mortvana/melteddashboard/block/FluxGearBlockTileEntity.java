package mortvana.melteddashboard.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.inventory.IInventoryRetainer;
import mortvana.melteddashboard.block.tile.FluxGearTileEntity;
import mortvana.melteddashboard.util.helpers.MiscHelper;
import mortvana.melteddashboard.util.helpers.WorldHelper;

public abstract class FluxGearBlockTileEntity extends FluxGearBlock implements ITileEntityProvider {

	/**
	 *  Literally just a wrapper for a default block, stupidly simple!
	 *  @param material - The material of the block.
	 */
	public FluxGearBlockTileEntity(Material material) {
		super(material);
		isBlockContainer = true;
	}

	/**
	 * The really simple way to initialize a block and add it to a creative tab.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 */
	public FluxGearBlockTileEntity(Material material, CreativeTabs tab) {
		super(material, tab);
		isBlockContainer = true;
	}

	public FluxGearBlockTileEntity(Material material, String name) {
		super(material, name);
		isBlockContainer = true;
	}

	/**
	 * The simple way to initialize a block and add it to a creative tab, while also setting
	 * hardness and blast resistance.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 * @param hardness - The hardness of the block (how long it takes to mine).
	 * @param resistance - The blast resistance of the block (how resistant it is to explosions).
	 */
	public FluxGearBlockTileEntity(Material material, CreativeTabs tab, float hardness, float resistance) {
		super(material, tab, hardness, resistance);
		isBlockContainer = true;
	}

	//public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type)



























	@Override //TODO:
	public int damageDropped(int i) {
		return i;
	}

	/* FluxGearBlockTileEntity */

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return createNewTileEntity(world, metadata);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return null;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		TileEntity tile = world.getTileEntity(x, y, z);

		if (tile instanceof FluxGearTileEntity) {
			((FluxGearTileEntity) tile).onBroken(world, x, y, z, block, metadata);
		}
		if (!(tile instanceof IInventoryRetainer) && tile instanceof IInventory) {
			MiscHelper.dropInventoryContents(world, x, y, z, (IInventory) tile);
		}
		if (tile != null) {
			world.removeTileEntity(x, y, z);
		}
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int eventID, int eventData) {
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null && tile.receiveClientEvent(eventID, eventData);
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {
		super.onBlockPreDestroy(world, x, y, z, metadata);

		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof FluxGearTileEntity) {
			((FluxGearTileEntity) tile).onKill();
		}
	}

	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
		super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null && tile instanceof FluxGearTileEntity) {
			TileEntity neighbor = world.getTileEntity(tileX, tileY, tileZ);
			for (int i = 0; i < 6; i++) {
				if (WorldHelper.isBlockAdjacent(x - tileX, y - tileY, z - tileZ, ForgeDirection.VALID_DIRECTIONS[i])) {
					((FluxGearTileEntity) tile).updateAdjacency(neighbor, i);
					break;
				}
			}
		}
	}

	//Legacy
	public boolean isEnabled () {
		return true;
	}

	@Override
	public int getHarvestLevel (int metadata) {
		//By default, no metadata-based sub-blocks.
		return 1;
	}
}
