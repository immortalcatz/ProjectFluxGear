package mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import mortvana.melteddashboard.common.MeltedDashboardCore;

import mortvana.legacy.LegacyHelper;
import mortvana.legacy.clean.fluxgearaddons.util.helpers.MultiblockHelper.TileLocation;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.dependent.firstdegree.fluxgearaddons.util.block.tile.TileObjectSync;

public class TileEnergyStorageCore extends TileObjectSync {

	public TileLocation[] stabilizers = new TileLocation[4];
	public int tier = 0;
	public boolean online = false;
	public float modelRotation = 0.0F;
	public double energy = 0.0D;
	public double capacity = 0.0D;
	public double lastTickCapacity = 0.0D;
	public int tick = 0;

	public int transferCap = 2147483647;

	public TileEnergyStorageCore() {
		for (int i = 0; i < stabilizers.length; ++i) {
			stabilizers[i] = new TileLocation();
		}
	}

	@Override
	public void updateEntity() {
		if (online) {
			if (worldObj.isRemote) {
				modelRotation = (float) ((double) modelRotation + 0.5D);
			} else {
				detectAndRendChanges();
			}
			tick++;
		}
	}

	public boolean tryActivate() {
		if (findStabilizers() && setTier(false) && testOrActivateStructureIfValid(false, false)) {
			online = true;
			if (!testOrActivateStructureIfValid(false, true)) {
				online = false;
				deactivateStabilizers();
				return false;
			} else {
				activateStabilizers();
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				return true;
			}
		} else {
			return false;
		}
	}

	public boolean creativeActivate() {
		if (findStabilizers() && setTier(false) && testOrActivateStructureIfValid(true, false)) {
			online = true;
			if (!testOrActivateStructureIfValid(false, true)) {
				online = false;
				deactivateStabilizers();
				return false;
			} else {
				activateStabilizers();
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				return true;
			}
		} else {
			return false;
		}
	}

	public boolean isStructureStillValid(boolean update) {
		if (!checkStabilizers() || !testOrActivateStructureIfValid(false, false) || !areStabilizersActive()) {
			online = false;
		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		if (!online) {
			deactivateStabilizers();
			if (update) {
				reIntegrate();
			}
		}

		return online;
	}

	public void reIntegrate() {
		for (int x = xCoord - 1; x <= xCoord + 1; ++x) {
			for (int y = yCoord - 1; y <= yCoord + 1; ++y) {
				for (int z = zCoord - 1; z <= zCoord + 1; ++z) {
					if (worldObj.getBlock(x, y, z) == FluxGearContent.invisibleMultiblock) {
						if (worldObj.getBlockMetadata(x, y, z) == 0) {
							worldObj.setBlock(x, y, z, LegacyHelper.blockPolycarbide);
						} else if (worldObj.getBlockMetadata(x, y, z) == 1) {
							worldObj.setBlock(x, y, z, Blocks.redstone_block);
						}
					}
				}
			}
		}

	}

	public boolean findStabilizers() {
		boolean flag = true;

		int z;
		for (z = xCoord; z <= xCoord + 11; ++z) {
			if (worldObj.getBlock(z, yCoord, zCoord) == FluxGearContent.particleGenerator) {
				if (worldObj.getBlockMetadata(z, yCoord, zCoord) == 1) {
					flag = false;
				} else {
					stabilizers[0] = new TileLocation(z, yCoord, zCoord);
				}
				break;
			}

			if (z == xCoord + 11) {
				flag = false;
			}
		}

		for (z = xCoord; z >= xCoord - 11; --z) {
			if (worldObj.getBlock(z, yCoord, zCoord) == FluxGearContent.particleGenerator) {
				if (worldObj.getBlockMetadata(z, yCoord, zCoord) == 1) {
					flag = false;
				} else {
					stabilizers[1] = new TileLocation(z, yCoord, zCoord);
				}
				break;
			}

			if (z == xCoord - 11) {
				flag = false;
			}
		}

		for (z = zCoord; z <= zCoord + 11; ++z) {
			if (worldObj.getBlock(xCoord, yCoord, z) == FluxGearContent.particleGenerator) {
				if (worldObj.getBlockMetadata(xCoord, yCoord, z) == 1) {
					flag = false;
				} else {
					stabilizers[2] = new TileLocation(xCoord, yCoord, z);
				}
				break;
			}

			if (z == zCoord + 11) {
				flag = false;
			}
		}

		for (z = zCoord; z >= zCoord - 11; --z) {
			if (worldObj.getBlock(xCoord, yCoord, z) == FluxGearContent.particleGenerator) {
				if (worldObj.getBlockMetadata(xCoord, yCoord, z) == 1) {
					flag = false;
				} else {
					stabilizers[3] = new TileLocation(xCoord, yCoord, z);
				}
				break;
			}

			if (z == zCoord - 11) {
				flag = false;
			}
		}

		return flag;
	}

	public boolean setTier(boolean force) {
		if (force) {
			return true;
		} else {
			int xPos = 0;
			int xNeg = 0;
			int yPos = 0;
			int yNeg = 0;
			int zPos = 0;
			int zNeg = 0;
			byte range = 5;

			int z;
			for (z = 0; z <= range; ++z) {
				if (testForOrActivatePolycarbide(xCoord + z, yCoord, zCoord, false, false)) {
					xPos = z;
					break;
				}

			}

			for (z = 0; z <= range; ++z) {
				if (testForOrActivatePolycarbide(xCoord - z, yCoord, zCoord, false, false)) {
					xNeg = z;
					break;
				}
			}

			for (z = 0; z <= range; ++z) {
				if (testForOrActivatePolycarbide(xCoord, yCoord + z, zCoord, false, false)) {
					yPos = z;
					break;
				}
			}

			for (z = 0; z <= range; ++z) {
				if (testForOrActivatePolycarbide(xCoord, yCoord - z, zCoord, false, false)) {
					yNeg = z;
					break;
				}
			}

			for (z = 0; z <= range; ++z) {
				if (testForOrActivatePolycarbide(xCoord, yCoord, zCoord + z, false, false)) {
					zPos = z;
					break;
				}
			}

			for (z = 0; z <= range; ++z) {
				if (testForOrActivatePolycarbide(xCoord, yCoord, zCoord - z, false, false)) {
					zNeg = z;
					break;
				}
			}

			if (zNeg == zPos && zNeg == yNeg && zNeg == yPos && zNeg == xNeg && zNeg == xPos) {
				tier = xPos;
				if (tier > 1) {
					tier++;
				}

				if (tier == 1 && testForOrActivatePolycarbide(xCoord + 1, yCoord + 1, zCoord, false, false)) {
					tier = 2;
				}

				return true;
			} else {
				return false;
			}
		}
	}

	public boolean testOrActivateStructureIfValid(boolean setBlocks, boolean activate) {
		switch (tier) {
			case 0:
				return testOrActivateRect(1, 1, 1, "air", setBlocks, activate);
			case 1:
				return testForOrActivatePolycarbide(xCoord + 1, yCoord, zCoord, setBlocks, activate) && testForOrActivatePolycarbide(xCoord - 1, yCoord, zCoord, setBlocks, activate) && testForOrActivatePolycarbide(xCoord, yCoord + 1, zCoord, setBlocks, activate) && testForOrActivatePolycarbide(xCoord, yCoord - 1, zCoord, setBlocks, activate) && testForOrActivatePolycarbide(xCoord, yCoord, zCoord + 1, setBlocks, activate) && testForOrActivatePolycarbide(xCoord, yCoord, zCoord - 1, setBlocks, activate) && isAir(xCoord + 1, yCoord + 1, zCoord, setBlocks) && isAir(xCoord, yCoord + 1, zCoord + 1, setBlocks) && isAir(xCoord - 1, yCoord + 1, zCoord, setBlocks) && isAir(xCoord, yCoord + 1, zCoord - 1, setBlocks) && isAir(xCoord + 1, yCoord - 1, zCoord, setBlocks) && isAir(xCoord, yCoord - 1, zCoord + 1, setBlocks) && isAir(xCoord - 1, yCoord - 1, zCoord, setBlocks) && isAir(xCoord, yCoord - 1, zCoord - 1, setBlocks) && isAir(xCoord + 1, yCoord, zCoord + 1, setBlocks) && isAir(xCoord - 1, yCoord, zCoord - 1, setBlocks) && isAir(xCoord + 1, yCoord, zCoord - 1, setBlocks) && isAir(xCoord - 1, yCoord, zCoord + 1, setBlocks) && isAir(xCoord + 1, yCoord + 1, zCoord + 1, setBlocks) && isAir(xCoord - 1, yCoord + 1, zCoord - 1, setBlocks) && isAir(xCoord + 1, yCoord + 1, zCoord - 1, setBlocks) && isAir(xCoord - 1, yCoord + 1, zCoord + 1, setBlocks) && isAir(xCoord + 1, yCoord - 1, zCoord + 1, setBlocks) && isAir(xCoord - 1, yCoord - 1, zCoord - 1, setBlocks) && isAir(xCoord + 1, yCoord - 1, zCoord - 1, setBlocks) && isAir(xCoord - 1, yCoord - 1, zCoord + 1, setBlocks);
			case 2:
				return testOrActivateRect(1, 1, 1, "polycarbideBlock", setBlocks, activate);
			case 3:
				return testOrActivateSides(1, "polycarbideBlock", setBlocks, activate) && testOrActivateRect(1, 1, 1, "redstone", setBlocks, activate);
			case 4:
				return testOrActivateSides(2, "polycarbideBlock", setBlocks, activate) && testOrActivateRect(2, 1, 1, "redstone", setBlocks, activate) && testOrActivateRect(1, 2, 1, "redstone", setBlocks, activate) && testOrActivateRect(1, 1, 2, "redstone", setBlocks, activate) && testOrActivateRings(2, 2, "polycarbideBlock", setBlocks, activate);
			case 5:
				return testOrActivateSides(3, "polycarbideBlock", setBlocks, activate) && testOrActivateSides(2, "redstone", setBlocks, activate) && testOrActivateRect(2, 2, 2, "redstone", setBlocks, activate) && testOrActivateRings(2, 3, "polycarbideBlock", setBlocks, activate);
			case 6:
				return testOrActivateSides(4, "polycarbideBlock", setBlocks, activate) && testOrActivateSides(3, "redstone", setBlocks, activate) && testOrActivateRect(3, 2, 2, "redstone", setBlocks, activate) && testOrActivateRect(2, 3, 2, "redstone", setBlocks, activate) && testOrActivateRect(2, 2, 3, "redstone", setBlocks, activate) && testOrActivateRings(2, 4, "polycarbideBlock", setBlocks, activate) && testOrActivateRings(3, 3, "polycarbideBlock", setBlocks, activate);
		}
		return false;
	}

	public boolean isValidBlock(int x, int y, int z, String block, boolean set, boolean activate) {
		if (block.equals("air") && (x != xCoord || y != yCoord || z != zCoord) && !isAir(x, y, z, set)) {
			return false;
		} else if (block.equals("redstone") && (x != xCoord || y != yCoord || z != zCoord) && !testForOrActivateRedstone(x, y, z, set, activate)) {
			return false;
		} else if (block.equals("polycarbideBlock") && (x != xCoord || y != yCoord || z != zCoord) && !testForOrActivatePolycarbide(x, y, z, set, activate)) {
			return false;
		} else if(!block.equals("polycarbideBlock") && !block.equals("redstone") && !block.equals("air")) {
			MeltedDashboardCore.logger.error("Invalid String In Multiblock Structure Code!!!");
			return false;
		}
		return true;
	}

	public boolean testOrActivateRect(int xDim, int yDim, int zDim, String block, boolean set, boolean activate) {
		for (int x = xCoord - xDim; x <= xCoord + xDim; x++) {
			for (int y = yCoord - yDim; y <= yCoord + yDim; y++) {
				for (int z = zCoord - zDim; z <= zCoord + zDim; z++) {
					if (!isValidBlock(xDim, yDim, zDim, block, set, activate)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean testOrActivateRings(int size, int dist, String block, boolean set, boolean activate) {
		int dim1;
		int dim2;

		for (dim1 = yCoord - size; dim1 <= yCoord + size; dim1++) {
			for (dim2 = zCoord - size; dim2 <= zCoord + size; dim2++) {
				if (dim1 == yCoord - size || dim1 == yCoord + size || dim2 == zCoord - size || dim2 == zCoord + size) {
					if (!isValidBlock(xCoord + dist, dim1, dim2, block, set, activate) || !isValidBlock(xCoord - dist, dim1, dim2, block, set, activate)) {
						return false;
					}
				}
			}
		}

		for (dim1 = xCoord - size; dim1 <= xCoord + size; dim1++) {
			for (dim2 = zCoord - size; dim2 <= zCoord + size; dim2++) {
				if (dim1 == xCoord - size || dim1 == xCoord + size || dim2 == zCoord - size || dim2 == zCoord + size) {
					if (!isValidBlock(dim1, yCoord + dist, dim2, block, set, activate) || !isValidBlock(dim1, yCoord - dist, dim2, block, set, activate)) {
						return false;
					}
				}
			}
		}

		for (dim1 = xCoord - size; dim1 <= xCoord + size; dim1++) {
			for (dim2 = yCoord - size; dim2 <= yCoord + size; dim2++) {
				if (dim1 == xCoord - size || dim1 == xCoord + size || dim2 == yCoord - size || dim2 == yCoord + size) {
					if (!isValidBlock(dim1, dim2, zCoord + dist, block, set, activate) || !isValidBlock(dim1, dim2, zCoord - dist, block, set, activate)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public boolean testOrActivateSides(int dist, String block, boolean set, boolean activate) {
		dist++;

		int dim1;
		int dim2;
		for (dim1 = yCoord - 1; dim1 <= yCoord + 1; dim1++) {
			for (dim2 = zCoord - 1; dim2 <= zCoord + 1; dim2++) {
				if (!isValidBlock(xCoord + dist, dim1, dim2, block, set, activate) || !isValidBlock(xCoord - dist, dim1, dim2, block, set, activate)) {
					return false;
				}
			}
		}

		for (dim1 = xCoord - 1; dim1 <= xCoord + 1; dim1++) {
			for (dim2 = zCoord - 1; dim2 <= zCoord + 1; dim2++) {
				if (!isValidBlock(dim1, yCoord + dist, dim2, block, set, activate) || !isValidBlock(dim1, yCoord - dist, dim2, block, set, activate)) {
					return false;
				}
			}
		}

		for (dim1 = xCoord - 1; dim1 <= xCoord + 1; dim1++) {
			for (dim2 = yCoord - 1; dim2 <= yCoord + 1; dim2++) {
				if (!isValidBlock(dim1, dim2, zCoord + dist, block, set, activate) || !isValidBlock(dim1, dim2, zCoord - dist, block, set, activate)) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean testForOrActivatePolycarbide(int x, int y, int z, boolean set, boolean activate) {
		if (activate) {
			return activatePolycarbide(x, y, z);
		} else if (set) {
			worldObj.setBlock(x, y, z, LegacyHelper.blockPolycarbide);
			return true;
		} else {
			return worldObj.getBlock(x, y, z) == LegacyHelper.blockPolycarbide || (worldObj.getBlock(x, y, z) == FluxGearContent.invisibleMultiblock && worldObj.getBlockMetadata(x, y, z) == 0);
		}
	}

	public boolean testForOrActivateRedstone(int x, int y, int z, boolean set, boolean activate) {
		if (activate) {
			return activateRedstone(x, y, z);
		} else if(set) {
			worldObj.setBlock(x, y, z, Blocks.redstone_block);
			return true;
		} else {
			return worldObj.getBlock(x, y, z) == Blocks.redstone_block || (worldObj.getBlock(x, y, z) == FluxGearContent.invisibleMultiblock && worldObj.getBlockMetadata(x, y, z) == 1);
		}
	}

	public TileInvisibleMultiblock getTileInvis(int x, int y, int z) {
		TileEntity tile = worldObj.getTileEntity(x, y, z);
		return tile != null && tile instanceof TileInvisibleMultiblock ? (TileInvisibleMultiblock) tile : null;
	}

	public TileParticleGenerator getTileStable(int x, int y, int z) {
		TileEntity tile = worldObj.getTileEntity(x, y, z);
		return tile != null && tile instanceof TileParticleGenerator ? (TileParticleGenerator) tile : null;
	}

	public TileParticleGenerator getTileStable(int i) {
		return getTileStable(getStabilizerX(i), getStabilizerY(i), getStabilizerZ(i));
	}

	public boolean activatePolycarbide(int x, int y, int z) {
		if (testForOrActivatePolycarbide(x, y, z, false, false)) {
			worldObj.setBlock(x, y, z, FluxGearContent.invisibleMultiblock, 0, 2);
			TileInvisibleMultiblock tile = getTileInvis(x, y, z);
			if (tile != null) {
				tile.master = new TileLocation(xCoord, yCoord, zCoord);
			}

			return true;
		} else {
			MeltedDashboardCore.logger.error("Failed to activate structure (activatePolycarbide)");
			return false;
		}
	}

	public boolean activateRedstone(int x, int y, int z) {
		if (testForOrActivateRedstone(x, y, z, false, false)) {
			worldObj.setBlock(x, y, z, FluxGearContent.invisibleMultiblock, 1, 2);
			TileInvisibleMultiblock tile = getTileInvis(x, y, z);
			if (tile != null) {
				tile.master = new TileLocation(xCoord, yCoord, zCoord);
			}

			return true;
		} else {
			MeltedDashboardCore.logger.error("Failed to activate structure (activateRedstone)");
			return false;
		}
	}

	public boolean isAir(int x, int y, int z, boolean set) {
		if (set) {
			worldObj.setBlockToAir(x, y, z);
			return true;
		} else {
			return worldObj.isAirBlock(x, y, z);
		}
	}

	public boolean isOnline() {
		return online;
	}

	public int getStabilizerX(int index) {
		return stabilizers[index].getXCoord();
	}

	public int getStabilizerY(int index) {
		return stabilizers[index].getYCoord();
	}

	public int getStabilizerZ(int index) {
		return stabilizers[index].getZCoord();
	}

	public void activateStabilizers() {
		for (int i = 0; i < stabilizers.length; i++) {
			if (stabilizers[i] == null) {
				MeltedDashboardCore.logger.error("activateStabilizers stabalizers[" + i + "] == null!!!");
				return;
			}

			TileParticleGenerator tile = getTileStable(i);
			if (tile == null) {
				MeltedDashboardCore.logger.error("Missing Tile Entity (Particle Generator)");
				return;
			}

			tile.stabilizerMode = true;
			tile.setMaster(new TileLocation(xCoord, yCoord, zCoord));
			worldObj.setBlockMetadataWithNotify(getStabilizerX(i), getStabilizerY(i), getStabilizerZ(i), 1, 2);
		}

		double fluxCap = 0.0D;
		switch (tier) {
			case 0:
				fluxCap = 4.55e7D;
				break;
			case 1:
				fluxCap = 2.73e8D;
				break;
			case 2:
				fluxCap = 1.64e9D;
				break;
			case 3:
				fluxCap = 9.88e9D;
				break;
			case 4:
				fluxCap = 5.93e10D;
				break;
			case 5:
				fluxCap = 3.56e11D;
				break;
			case 6:
				fluxCap = 2.14e12D;
		}

		capacity = fluxCap;
		if (energy > fluxCap) {
			energy = fluxCap;
		}

	}

	public void deactivateStabilizers() {
		for (int i = 0; i < stabilizers.length; i++) {
			if (stabilizers[i] == null) {
				MeltedDashboardCore.logger.error("deactivateStabilizers stabilizers[" + i + "] == null!!!");
			} else {
				TileParticleGenerator tile = getTileStable(i);
				if (tile != null) {
					tile.stabilizerMode = false;
					worldObj.setBlockMetadataWithNotify(getStabilizerX(i), getStabilizerY(i), getStabilizerZ(i), 0, 2);
				}
			}
		}

	}

	public boolean areStabilizersActive() {
		for (int i = 0; i < stabilizers.length; i++) {
			if (stabilizers[i] == null) {
				MeltedDashboardCore.logger.error("activateStabilizers stabalizers[" + i + "] == null!!!");
				return false;
			}

			TileParticleGenerator tile = getTileStable(i);
			if (tile == null || !tile.stabilizerMode || worldObj.getBlockMetadata(getStabilizerX(i), getStabilizerY(i), getStabilizerZ(i)) != 1) {
				return false;
			}
		}

		return true;
	}

	public boolean checkStabilizers() {
		int i = 0;

		while (i < stabilizers.length) {
			if (stabilizers[i] == null) {
				return false;
			}

			TileParticleGenerator tile = getTileStable(i);
			if (tile != null && tile.stabilizerMode) {
				if (masterCoordsMatch(tile)) {
					i++;
					continue;
				}

				return false;
			}

			return false;
		}

		return true;
	}

	public boolean masterCoordsMatch(TileParticleGenerator tile) {
		return tile.getMaster().xCoord == xCoord && tile.getMaster().yCoord == yCoord && tile.getMaster().zCoord == zCoord;
	}

	public int getTier() {
		return tier;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("Online", online);
		compound.setShort("Tier", (short) tier);
		compound.setDouble("Energy", energy);

		for (int i = 0; i < stabilizers.length; i++) {
			if (stabilizers[i] != null) {
				stabilizers[i].writeToNBT(compound, String.valueOf(i));
			}
		}

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		online = compound.getBoolean("Online");
		tier = compound.getShort("Tier");
		energy = compound.getDouble("Energy");

		for (int i = 0; i < stabilizers.length; ++i) {
			if (stabilizers[i] != null) {
				stabilizers[i].readFromNBT(compound, String.valueOf(i));
			}
		}

		double fluxCap = 0.0D;
		switch (tier) {
			case 0:
				fluxCap = 4.55E7D;
				break;
			case 1:
				fluxCap = 2.73E8D;
				break;
			case 2:
				fluxCap = 1.64E9D;
				break;
			case 3:
				fluxCap = 9.88E9D;
				break;
			case 4:
				fluxCap = 5.93E10D;
				break;
			case 5:
				fluxCap = 3.56E11D;
				break;
			case 6:
				fluxCap = 2.14E12D;
		}

		capacity = fluxCap;
		if (energy > fluxCap) {
			energy = fluxCap;
		}

		super.readFromNBT(compound);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

	public int receiveEnergy(int maxReceive, boolean simulate) {
		double energyReceived = Math.min(capacity - energy, (double) Math.min(transferCap, maxReceive));
		if (!simulate) {
			energy += energyReceived;
		}

		return (int) energyReceived;
	}

	public int extractEnergy(int maxExtract, boolean simulate) {
		double energyExtracted = Math.min(energy, (double) Math.min(transferCap, maxExtract));
		if (!simulate) {
			energy -= energyExtracted;
		}

		return (int) energyExtracted;
	}

	public double getEnergyStored() {
		return energy;
	}

	public double getMaxEnergyStored() {
		return capacity;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

	public void detectAndRendChanges() {
		if ((int) Math.abs(lastTickCapacity - energy) > 100000) {
			lastTickCapacity = (Double) sendObject((byte) 5, 0, energy);
		}

	}

	@Override
	public void receiveObject(int index, Object object) {
		energy = (Double) object;
	}
}
