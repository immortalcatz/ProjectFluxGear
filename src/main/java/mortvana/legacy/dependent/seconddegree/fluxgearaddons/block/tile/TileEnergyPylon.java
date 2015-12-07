package mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyHandler;

import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.clean.fluxgearaddons.client.particle.EnergyTransferParticle;
import mortvana.legacy.clean.fluxgearaddons.client.particle.ParticleHandler;
import mortvana.legacy.dependent.firstdegree.fluxgearaddons.util.block.tile.TileObjectSync;
import mortvana.legacy.clean.fluxgearaddons.util.helpers.MultiblockHelper.TileLocation;

public class TileEnergyPylon extends TileObjectSync implements IEnergyHandler {
	public boolean active = false;
	public boolean lastTickActive = false;
	public boolean receiveEnergy = false;
	public boolean lastTickReceiveEnergy = false;
	public float modelRotation = 0.0F;
	public float modelScale = 0.0F;
	public List<TileLocation> coreLocations = new ArrayList<TileLocation>();
	public int selectedCore = 0;
	public byte particleRate = 0;
	public byte lastTickParticleRate = 0;

	public double maxInt = Integer.MAX_VALUE;

	public TileEnergyPylon() {}

	@Override
	public void updateEntity() {
		if (active && worldObj.isRemote) {
			modelRotation = (float) ((double) modelRotation + 1.5D);
			modelScale += !receiveEnergy ? -0.01F : 0.01F;
			if (modelScale < 0.0F && !receiveEnergy) {
				modelScale = 10000.0F;
			}

			if (modelScale < 0.0F && receiveEnergy) {
				modelScale = 0.0F;
			}

			spawnParticles();
		} else if (worldObj.isRemote) {
			modelScale = 0.5F;
		}

		if (!worldObj.isRemote) {
			if (active && !receiveEnergy) {
				for (int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++) {
					ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[i];
					TileEntity tile = worldObj.getTileEntity(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);
					if (tile != null && tile instanceof IEnergyHandler) {
						extractEnergy(d, ((IEnergyHandler) tile).receiveEnergy(d.getOpposite(), extractEnergy(d, 2147483647, true), false), false);
					}
				}
			}

			detectAndSendChanges();
			if (particleRate > 0) {
				particleRate--;
			}

		}
	}

	public void onActivated() {
		if (!active) {
			active = isValidStructure();
		}

		findCores();
	}

	public TileEnergyStorageCore getMaster() {
		if (coreLocations.isEmpty()) {
			return null;
		} else {
			if (selectedCore >= coreLocations.size()) {
				selectedCore = coreLocations.size() - 1;
			}

			TileLocation core = coreLocations.get(selectedCore);
			TileEntity tile = worldObj.getTileEntity(core.getXCoord(), core.getYCoord(), core.getZCoord());
			return tile != null && tile instanceof TileEnergyStorageCore ? (TileEnergyStorageCore) tile : null;
		}
	}

	public void findCores() {
		int yMod = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1 ? 3 : -3;
		byte range = 15;
		List<TileLocation> locations = new ArrayList<TileLocation>();

		for (int x = xCoord - range; x <= xCoord + range; ++x) {
			for (int y = yCoord + yMod - range / 4; y <= yCoord + yMod + range / 4; ++y) {
				for (int z = zCoord - range; z <= zCoord + range; ++z) {
					if (worldObj.getBlock(x, y, z) == FluxGearContent.energyStorageCore) {
						locations.add(new TileLocation(x, y, z));
					}
				}
			}
		}

		if (locations != coreLocations) {
			coreLocations.clear();
			coreLocations.addAll(locations);
			selectedCore = selectedCore >= coreLocations.size() ? 0 : selectedCore;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}

	}

	public void nextCore() {
		findCores();
		selectedCore++;
		if (selectedCore >= coreLocations.size()) {
			selectedCore = 0;
		}

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@SideOnly(Side.CLIENT)
	public void spawnParticles() {
		Random rand = new Random();
		if (getMaster() != null && getMaster().isOnline()) {
			int x = getMaster().xCoord;
			int y = getMaster().yCoord;
			int z = getMaster().zCoord;
			int cYCoord = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1 ? yCoord + 1 : yCoord - 1;
			float disMod = getMaster().getTier() == 0 ? 0.5F : (getMaster().getTier() == 1 ? 1.0F : (getMaster().getTier() == 2 ? 1.0F : (getMaster().getTier() == 3 ? 2.0F : (getMaster().getTier() == 4 ? 2.0F : (getMaster().getTier() == 5 ? 3.0F : 4.0F)))));
			if (particleRate > 20) {
				particleRate = 20;
			}

			double spawnX, spawnY, spawnZ, targetX, targetY, targetZ;
			EnergyTransferParticle passiveParticle;
			EnergyTransferParticle passiveParticle1;
			int var21;
			if (!receiveEnergy) {
				spawnX = (double) x + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
				spawnY = (double) y + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
				spawnZ = (double) z + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
				targetX = (double) xCoord + 0.5D;
				targetY = (double) cYCoord + 0.5D;
				targetZ = (double) zCoord + 0.5D;
				if (rand.nextFloat() < 0.05F) {
					passiveParticle = new EnergyTransferParticle(worldObj, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, true);
					ParticleHandler.spawnCustomParticle(passiveParticle, 35.0D);
				}

				if (particleRate > 0) {
					if (particleRate > 10) {
						for (var21 = 0; var21 <= this.particleRate / 10; ++var21) {
							spawnX = (double) x + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
							spawnY = (double) y + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
							spawnZ = (double) z + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
							passiveParticle1 = new EnergyTransferParticle(worldObj, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, false);
							ParticleHandler.spawnCustomParticle(passiveParticle1, 35.0D);
						}
					} else if (rand.nextInt(Math.max(1, 10 - particleRate)) == 0) {
						spawnX = (double) x + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
						spawnY = (double) y + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
						spawnZ = (double) z + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
						passiveParticle = new EnergyTransferParticle(worldObj, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, false);
						ParticleHandler.spawnCustomParticle(passiveParticle, 35.0D);
					}
				}
			} else {
				targetX = (double) x + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
				targetY = (double) y + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
				targetZ = (double) z + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
				spawnX = (double) xCoord + 0.5D;
				spawnY = (double) cYCoord + 0.5D;
				spawnZ = (double) zCoord + 0.5D;
				if (rand.nextFloat() < 0.05F) {
					passiveParticle = new EnergyTransferParticle(worldObj, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, true);
					ParticleHandler.spawnCustomParticle(passiveParticle, 35.0D);
				}

				if (particleRate > 0) {
					if (particleRate > 10) {
						for(var21 = 0; var21 <= particleRate / 10; ++var21) {
							targetX = (double) x + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
							targetY = (double) y + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
							targetZ = (double) z + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
							passiveParticle1 = new EnergyTransferParticle(worldObj, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, false);
							ParticleHandler.spawnCustomParticle(passiveParticle1, 35.0D);
						}
					} else if (rand.nextInt(Math.max(1, 10 - particleRate)) == 0) {
						targetX = (double) x + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
						targetY = (double) y + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
						targetZ = (double) z + 0.5D - (double) disMod + (double) (rand.nextFloat() * disMod * 2.0F);
						passiveParticle = new EnergyTransferParticle(worldObj, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, false);
						ParticleHandler.spawnCustomParticle(passiveParticle, 35.0D);
					}
				}
			}

		}
	}

	public boolean isValidStructure() {
		return (isGlass(xCoord, yCoord + 1, zCoord) || isGlass(xCoord, yCoord - 1, zCoord)) && (!isGlass(xCoord, yCoord + 1, zCoord) || !isGlass(xCoord, yCoord - 1, zCoord));
	}

	public boolean isGlass(int x, int y, int z) {
		return worldObj.getBlock(x, y, z) == FluxGearContent.invisibleMultiblock && worldObj.getBlockMetadata(x, y, z) == 2;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		active = nbt.getBoolean("Active");
		receiveEnergy = nbt.getBoolean("Input");
		int i = nbt.getInteger("Cores");
		ArrayList<TileLocation> list = new ArrayList<TileLocation>();

		for (int j = 0; j < i; ++j) {
			TileLocation loc = new TileLocation();
			loc.readFromNBT(nbt, "Core" + j);
			list.add(loc);
		}

		coreLocations = list;
		particleRate = nbt.getByte("ParticleRate");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("Active", active);
		nbt.setBoolean("Input", receiveEnergy);
		int i = coreLocations.size();
		nbt.setInteger("Cores", i);

		for (int j = 0; j < i; ++j) {
			(coreLocations.get(j)).writeToNBT(nbt, "Core" + j);
		}

		nbt.setInteger("SelectedCore", selectedCore);
		nbt.setByte("ParticleRate", particleRate);
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

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		if (getMaster() == null) {
			return 0;
		} else {
			int received = receiveEnergy ? getMaster().receiveEnergy(maxReceive, simulate) : 0;
			if (!simulate && received > 0) {
				particleRate = (byte) Math.min(20, received < 500 && received > 0 ? 1 : received / 500);
			}
			return received;
		}
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		if (getMaster() == null) {
			return 0;
		} else {
			int extracted = receiveEnergy ? 0 : getMaster().extractEnergy(maxExtract, simulate);
			if (!simulate && extracted > 0) {
				particleRate = (byte) Math.min(20, extracted < 500 && extracted > 0 ? 1 : extracted / 500);
			}
			return extracted;
		}
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return getMaster() == null ? 0 : (int) Math.min(maxInt, getMaster().getEnergyStored());
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return getMaster() == null ? 0 : (int) Math.min(maxInt, getMaster().getMaxEnergyStored());
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

	public void detectAndSendChanges() {
		if (lastTickActive != active) {
			lastTickActive = ((Boolean) sendObject((byte) 6, 0, active, new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, (double) xCoord, (double) yCoord, (double) zCoord, 256.0D)));
		}

		if (lastTickReceiveEnergy != receiveEnergy) {
			lastTickReceiveEnergy = ((Boolean) sendObject((byte) 6, 1, receiveEnergy, new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, (double) xCoord, (double) yCoord, (double) zCoord, 256.0D)));
		}

		if (lastTickParticleRate != particleRate) {
			lastTickParticleRate = ((Byte) sendObject((byte) 0, 2, particleRate));
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void receiveObject(int index, Object object) {
		switch(index) {
			case 0:
				active = ((Boolean)object);
				break;
			case 1:
				receiveEnergy = ((Boolean)object);
				break;
			case 2:
				particleRate = ((Byte)object);
		}
	}
}
