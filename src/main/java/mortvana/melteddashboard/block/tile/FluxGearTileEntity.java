package mortvana.melteddashboard.block.tile;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.ISecurable;
import mortvana.melteddashboard.util.Constants;
import mortvana.melteddashboard.util.helpers.StringHelper;
import mortvana.melteddashboard.util.helpers.WorldHelper;

import com.mojang.authlib.GameProfile;

public abstract class FluxGearTileEntity extends TileEntity {

	//To make the coder's life easier and save performance via caching casts.
	public TileEntity[] adjTiles = new TileEntity[6];
	public FluxGearTileEntity[] adjFGTiles = new FluxGearTileEntity[6];
	public IEnergyHandler[] adjRFTiles = new IEnergyHandler[6];
	public IFluidHandler[] adjFluidTiles = new IFluidHandler[6];

	public boolean initialized = false;

	public ForgeDirection orientation = ForgeDirection.NORTH;
	public byte state = 0;
	public String name = "";
	public UUID owner = null;

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

	public void markChunkDirty() {
		worldObj.markTileEntityChunkModified(xCoord, yCoord, zCoord, this);
	}

	public void callNeighborBlockChange() {
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
	}

	public void callNeighborTileChange() {
		worldObj.updateNeighborsAboutBlockChange(xCoord, yCoord, zCoord, getBlockType());
	}

	public void onNeighborBlockChange() {}

	public void onNeighborTileChange(int x, int y, int z) {}

	public int getComparatorInput(int side) {
		return 0;
	}

	public int getLightValue() {
		return 0;
	}

	/*public boolean canPlayerAccess(EntityPlayer player) {
		if (!(this instanceof ISecurable)) {
			return true;
		}
		ISecurable.AccessMode access = ((ISecurable) this).getAccess();
		String name = player.getCommandSenderName();
		if (access.isPublic() *//*|| (CoFHProps.enableOpSecureAccess && CoreUtils.isOp(name))TODO: OP Access*//*) {
			return true;
		}

		GameProfile profile = ((ISecurable) this).getOwner();
		UUID ownerID = profile.getId();
		if (SecurityHelper.isDefaultUUID(ownerID)) {
			return true;
		}

		UUID otherID = SecurityHelper.getID(player);
		if (ownerID.equals(otherID)) {
			return true;
		}
		return access.isRestricted() && SocialRegistry.playerHasAccess(name, profile);
	}*/

	public boolean canPlayerDismantle(EntityPlayer player) {
		return true;
	}

	public boolean isUsable(EntityPlayer player) {
		return player.getDistanceSq(xCoord, yCoord, zCoord) <= 64.0D && worldObj.getTileEntity(xCoord, yCoord, zCoord) == this;
	}

	public boolean onWrench(EntityPlayer player, int side) {
		return false;
	}

	protected final boolean timeCheck() {
		return worldObj.getTotalWorldTime() % Constants.TIME == 0;
	}

	protected final boolean timeCheckEighth() {
		return worldObj.getTotalWorldTime() % Constants.TIME_EIGHTH == 0;
	}

	public abstract String getName();

	public abstract int getType();

}
