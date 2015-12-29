package mortvana.melteddashboard.block.tile;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

import cofh.api.energy.IEnergyHandler;
import mortvana.melteddashboard.util.helpers.WorldHelper;

public abstract class FluxGearTileEntity extends TileEntity {

	//To make the coder's life easier and save performance via caching casts.
	public TileEntity[] adjTiles = new TileEntity[6];
	public FluxGearTileEntity[] adjFGTiles = new FluxGearTileEntity[6];
	public IEnergyHandler[] adjRFTiles = new IEnergyHandler[6];
	public IFluidHandler[] adjFluidTiles = new IFluidHandler[6];

	public boolean initialized = false;

	public FluxGearTileEntity() {}

	public void updateAdjacency(TileEntity neighbor, int side) {
		adjTiles[side] = neighbor;
		if (neighbor instanceof FluxGearTileEntity) {
			adjFGTiles[side] = (FluxGearTileEntity) neighbor;
		}
		if (neighbor instanceof IEnergyHandler) {
			adjRFTiles[side] = (IEnergyHandler) neighbor;
		}
		if (neighbor instanceof IFluidHandler) {
			adjFluidTiles[side] = (IFluidHandler) neighbor;
		}
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!initialized) {
			initialize();
			initialized = true;
		}
	}

	public void onKill() {}

	public void initialize() {
		for (int i = 0; i < 6; i++) {
			TileEntity tile = WorldHelper.getAdjacentTileEntity(worldObj, xCoord, yCoord, zCoord, ForgeDirection.VALID_DIRECTIONS[i]);
			if (tile != null) {
				updateAdjacency(tile, i);
			}
		}
	}

	public void onBroken(World world, int x, int y, int z, Block block, int metadata) {}

	public void onDismantled(World world, int x, int y, int z, Block block, int metadata) {
		onBroken(world, x, y, z, block, metadata);
	}

	public void onPlaced(World world, int x, int y, int z, Block block, int metadata) {}

	@Override
	public void onChunkUnload() {
		if (!tileEntityInvalid) {
			invalidate();
		}
	}
}
