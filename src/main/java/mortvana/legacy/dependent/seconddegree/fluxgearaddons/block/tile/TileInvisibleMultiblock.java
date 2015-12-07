package mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import mortvana.melteddashboard.common.MeltedDashboardCore;

import mortvana.legacy.LegacyHelper;
import mortvana.legacy.clean.fluxgearaddons.util.helpers.MultiblockHelper.TileLocation;

public class TileInvisibleMultiblock extends TileEntity {
	public TileLocation master = new TileLocation();

	public TileInvisibleMultiblock() {}

	public boolean isMasterOnline() {
		TileEnergyStorageCore tile = isMaster();
		return tile != null && tile.online;
	}

	public TileEnergyStorageCore getMaster() {
		if (master == null) {
			return null;
		} else {
			return isMaster();
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		master.writeToNBT(compound, "Key");
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		master.readFromNBT(compound, "Key");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

	public void isStructureStillValid() {
		if (getMaster() == null) {
			MeltedDashboardCore.logger.error("{Tile} Master = null reverting!");
			revert();
		} else if (!getMaster().isOnline()) {
			revert();
		}
	}

	public void revert() {
		int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if (metadata == 0) {
			worldObj.setBlock(xCoord, yCoord, zCoord, LegacyHelper.blockPolycarbide);
		} else if (metadata == 1) {
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.redstone_block);
		}
	}

	public TileEnergyStorageCore isMaster() {
		TileEntity tile = worldObj.getTileEntity(master.getXCoord(), master.getYCoord(), master.getZCoord());
		return tile != null && tile instanceof TileEnergyStorageCore ? (TileEnergyStorageCore) tile : null;
	}
}
