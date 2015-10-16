package mortvana.legacy.errored.fluxgearaddons.block.tileentity;

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

import mortvana.legacy.errored.fluxgearaddons.client.particle.EnergyTransferParticle;
import mortvana.legacy.dependent.firstdegree.fluxgearaddons.client.particle.ParticleHandler;
import mortvana.legacy.dependent.firstdegree.fluxgearaddons.util.block.tile.TileObjectSync;
import mortvana.legacy.clean.fluxgearaddons.util.helpers.MultiblockHelper.TileLocation;

public class TileEnergyPylon extends TileObjectSync implements IEnergyHandler {
	public boolean active = false;
	public boolean lastTickActive = false;
	public boolean reciveEnergy = false;
	public boolean lastTickReciveEnergy = false;
	public float modelRotation = 0.0F;
	public float modelScale = 0.0F;
	private List<TileLocation> coreLocatios = new ArrayList<TileLocation>();
	private int selectedCore = 0;
	private byte particleRate = 0;
	private byte lastTickParticleRate = 0;

	public TileEnergyPylon() {
	}

	public void func_145845_h() {
		if(this.active && this.field_145850_b.isRemote) {
			this.modelRotation = (float)((double)this.modelRotation + 1.5D);
			this.modelScale += !this.reciveEnergy?-0.01F:0.01F;
			if(this.modelScale < 0.0F && !this.reciveEnergy) {
				this.modelScale = 10000.0F;
			}

			if(this.modelScale < 0.0F && this.reciveEnergy) {
				this.modelScale = 0.0F;
			}

			this.spawnParticles();
		} else if(this.field_145850_b.isRemote) {
			this.modelScale = 0.5F;
		}

		if (!this.field_145850_b.isRemote) {
			if (active && !reciveEnergy) {
				for(int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++) {
					ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[i];
					TileEntity tile = this.field_145850_b.getTileEntity(this.field_145851_c + d.offsetX, this.field_145848_d + d.offsetY, this.field_145849_e + d.offsetZ);
					if(tile != null && tile instanceof IEnergyHandler) {
						this.extractEnergy(d, ((IEnergyHandler)tile).receiveEnergy(d.getOpposite(), this.extractEnergy(d, 2147483647, true), false), false);
					}
				}
			}

			this.detectAndSendChanges();
			if(this.particleRate > 0) {
				--this.particleRate;
			}

		}
	}

	public void onActivated() {
		if(!this.active) {
			this.active = this.isValidStructure();
		}

		this.findCores();
	}

	private TileEnergyStorageCore getMaster() {
		if(this.coreLocatios.isEmpty()) {
			return null;
		} else {
			if(this.selectedCore >= this.coreLocatios.size()) {
				this.selectedCore = this.coreLocatios.size() - 1;
			}

			TileLocation core = (TileLocation)this.coreLocatios.get(this.selectedCore);
			return core != null && this.field_145850_b.getTileEntity(core.getXCoord(), core.getYCoord(), core.getZCoord()) instanceof TileEnergyStorageCore?(TileEnergyStorageCore)this.field_145850_b.getTileEntity(core.getXCoord(), core.getYCoord(), core.getZCoord()):null;
		}
	}

	private void findCores() {
		int yMod = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e) == 1?3:-3;
		byte range = 15;
		ArrayList locations = new ArrayList();

		for(int x = this.field_145851_c - range; x <= this.field_145851_c + range; ++x) {
			for(int y = this.field_145848_d + yMod - range / 4; y <= this.field_145848_d + yMod + range / 4; ++y) {
				for(int z = this.field_145849_e - range; z <= this.field_145849_e + range; ++z) {
					if(this.field_145850_b.getBlock(x, y, z) == ModBlocks.energyStorageCore) {
						locations.add(new TileLocation(x, y, z));
					}
				}
			}
		}

		if(locations != this.coreLocatios) {
			this.coreLocatios.clear();
			this.coreLocatios.addAll(locations);
			this.selectedCore = this.selectedCore >= this.coreLocatios.size()?0:this.selectedCore;
			this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
		}

	}

	public void nextCore() {
		this.findCores();
		++this.selectedCore;
		if(this.selectedCore >= this.coreLocatios.size()) {
			this.selectedCore = 0;
		}

		this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
	}

	@SideOnly(Side.CLIENT)
	private void spawnParticles() {
		Random rand = new Random();
		if(this.getMaster() != null && this.getMaster().isOnline()) {
			int x = this.getMaster().field_145851_c;
			int y = this.getMaster().field_145848_d;
			int z = this.getMaster().field_145849_e;
			int cYCoord = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e) == 1?this.field_145848_d + 1:this.field_145848_d - 1;
			float disMod = this.getMaster().getTier() == 0?0.5F:(this.getMaster().getTier() == 1?1.0F:(this.getMaster().getTier() == 2?1.0F:(this.getMaster().getTier() == 3?2.0F:(this.getMaster().getTier() == 4?2.0F:(this.getMaster().getTier() == 5?3.0F:4.0F)))));
			if(this.particleRate > 20) {
				this.particleRate = 20;
			}

			double spawnX;
			double spawnY;
			double spawnZ;
			double targetX;
			double targetY;
			double targetZ;
			EnergyTransferParticle passiveParticle;
			EnergyTransferParticle passiveParticle1;
			int var21;
			if(!this.reciveEnergy) {
				spawnX = (double)x + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
				spawnY = (double)y + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
				spawnZ = (double)z + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
				targetX = (double)this.field_145851_c + 0.5D;
				targetY = (double)cYCoord + 0.5D;
				targetZ = (double)this.field_145849_e + 0.5D;
				if(rand.nextFloat() < 0.05F) {
					passiveParticle = new EnergyTransferParticle(this.field_145850_b, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, true);
					ParticleHandler.spawnCustomParticle(passiveParticle, 35.0D);
				}

				if(this.particleRate > 0) {
					if(this.particleRate > 10) {
						for(var21 = 0; var21 <= this.particleRate / 10; ++var21) {
							spawnX = (double)x + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
							spawnY = (double)y + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
							spawnZ = (double)z + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
							passiveParticle1 = new EnergyTransferParticle(this.field_145850_b, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, false);
							ParticleHandler.spawnCustomParticle(passiveParticle1, 35.0D);
						}
					} else if(rand.nextInt(Math.max(1, 10 - this.particleRate)) == 0) {
						spawnX = (double)x + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
						spawnY = (double)y + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
						spawnZ = (double)z + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
						passiveParticle = new EnergyTransferParticle(this.field_145850_b, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, false);
						ParticleHandler.spawnCustomParticle(passiveParticle, 35.0D);
					}
				}
			} else {
				targetX = (double)x + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
				targetY = (double)y + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
				targetZ = (double)z + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
				spawnX = (double)this.field_145851_c + 0.5D;
				spawnY = (double)cYCoord + 0.5D;
				spawnZ = (double)this.field_145849_e + 0.5D;
				if(rand.nextFloat() < 0.05F) {
					passiveParticle = new EnergyTransferParticle(this.field_145850_b, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, true);
					ParticleHandler.spawnCustomParticle(passiveParticle, 35.0D);
				}

				if(this.particleRate > 0) {
					if(this.particleRate > 10) {
						for(var21 = 0; var21 <= this.particleRate / 10; ++var21) {
							targetX = (double)x + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
							targetY = (double)y + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
							targetZ = (double)z + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
							passiveParticle1 = new EnergyTransferParticle(this.field_145850_b, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, false);
							ParticleHandler.spawnCustomParticle(passiveParticle1, 35.0D);
						}
					} else if(rand.nextInt(Math.max(1, 10 - this.particleRate)) == 0) {
						targetX = (double)x + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
						targetY = (double)y + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
						targetZ = (double)z + 0.5D - (double)disMod + (double)(rand.nextFloat() * disMod * 2.0F);
						passiveParticle = new EnergyTransferParticle(this.field_145850_b, spawnX, spawnY, spawnZ, targetX, targetY, targetZ, false);
						ParticleHandler.spawnCustomParticle(passiveParticle, 35.0D);
					}
				}
			}

		}
	}

	private boolean isValidStructure() {
		return (this.isGlass(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) || this.isGlass(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) && (!this.isGlass(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) || !this.isGlass(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e));
	}

	private boolean isGlass(int x, int y, int z) {
		return this.field_145850_b.getBlock(x, y, z) == ModBlocks.invisibleMultiblock && this.field_145850_b.getBlockMetadata(x, y, z) == 2;
	}

	public void func_145839_a(NBTTagCompound compound) {
		super.func_145839_a(compound);
		this.active = compound.getBoolean("Active");
		this.reciveEnergy = compound.getBoolean("Input");
		int i = compound.getInteger("Cores");
		ArrayList list = new ArrayList();

		for(int j = 0; j < i; ++j) {
			TileLocation l = new TileLocation();
			l.readFromNBT(compound, "Core" + j);
			list.add(l);
		}

		this.coreLocatios = list;
		this.particleRate = compound.getByte("ParticleRate");
	}

	public void func_145841_b(NBTTagCompound compound) {
		super.func_145841_b(compound);
		compound.setBoolean("Active", this.active);
		compound.setBoolean("Input", this.reciveEnergy);
		int i = this.coreLocatios.size();
		compound.setInteger("Cores", i);

		for(int j = 0; j < i; ++j) {
			((TileLocation)this.coreLocatios.get(j)).writeToNBT(compound, "Core" + j);
		}

		compound.setInteger("SelectedCore", this.selectedCore);
		compound.setByte("ParticleRate", this.particleRate);
	}

	public Packet func_145844_m() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.func_145841_b(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbttagcompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.func_145839_a(pkt.func_148857_g());
	}

	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		if(this.getMaster() == null) {
			return 0;
		} else {
			int received = this.reciveEnergy?this.getMaster().receiveEnergy(maxReceive, simulate):0;
			if(!simulate && received > 0) {
				this.particleRate = (byte)Math.min(20, received < 500 && received > 0?1:received / 500);
			}

			return received;
		}
	}

	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		if(this.getMaster() == null) {
			return 0;
		} else {
			int extracted = this.reciveEnergy?0:this.getMaster().extractEnergy(maxExtract, simulate);
			if(!simulate && extracted > 0) {
				this.particleRate = (byte)Math.min(20, extracted < 500 && extracted > 0?1:extracted / 500);
			}

			return extracted;
		}
	}

	public int getEnergyStored(ForgeDirection from) {
		return this.getMaster() == null?0:(int)Math.min(2.147483647E9D, this.getMaster().getEnergyStored());
	}

	public int getMaxEnergyStored(ForgeDirection from) {
		return this.getMaster() == null?0:(int)Math.min(2.147483647E9D, this.getMaster().getMaxEnergyStored());
	}

	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

	private void detectAndSendChanges() {
		if(this.lastTickActive != this.active) {
			this.lastTickActive = ((Boolean)sendObject((byte)6, 0, active, new NetworkRegistry.TargetPoint(this.field_145850_b.provider.dimensionId, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 256.0D))).booleanValue();
		}

		if(this.lastTickReciveEnergy != this.reciveEnergy) {
			this.lastTickReciveEnergy = ((Boolean)sendObject((byte)6, 1, reciveEnergy, new NetworkRegistry.TargetPoint(this.field_145850_b.provider.dimensionId, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 256.0D))).booleanValue();
		}

		if(this.lastTickParticleRate != this.particleRate) {
			this.lastTickParticleRate = ((Byte)sendObject((byte) 0, 2, this.particleRate));
		}

	}

	@SideOnly(Side.CLIENT)
	public void receiveObject(int index, Object object) {
		switch(index) {
			case 0:
				this.active = ((Boolean)object);
				break;
			case 1:
				this.reciveEnergy = ((Boolean)object);
				break;
			case 2:
				this.particleRate = ((Byte)object);
		}

	}
}
