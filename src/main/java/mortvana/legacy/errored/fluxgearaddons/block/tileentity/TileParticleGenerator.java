package mortvana.legacy.errored.fluxgearaddons.block.tileentity;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.dependent.firstdegree.fluxgearaddons.client.particle.*;
import mortvana.legacy.clean.fluxgearaddons.util.helpers.MultiblockHelper.TileLocation;

public class TileParticleGenerator extends TileEntity {
	public int red = 0;
	public int green = 0;
	public int blue = 0;
	public int random_red = 0;
	public int random_green = 0;
	public int random_blue = 0;
	public float motion_x = 0.0F;
	public float motionY = 0.0F;
	public float motion_z = 0.0F;
	public float random_motion_x = 0.0F;
	public float random_motion_y = 0.0F;
	public float random_motion_z = 0.0F;
	public float scale = 1.0F;
	public float random_scale = 0.0F;
	public int life = 100;
	public int random_life = 0;
	public float spawn_x = 0.0F;
	public float spawn_y = 0.0F;
	public float spawn_z = 0.0F;
	public float random_spawn_x = 0.0F;
	public float random_spawn_y = 0.0F;
	public float random_spawn_z = 0.0F;
	public int page = 1;
	public int fade = 0;
	public int spawn_rate = 1;
	public boolean collide = false;
	public int selected_particle = 1;
	public float gravity = 0.0F;
	public boolean active = true;
	public boolean signal = false;
	public boolean inverted = false;
	TileLocation master = new TileLocation();
	public float rotation = 0.0F;
	public boolean stabilizerMode = false;
	private int tick = 0;

	public TileParticleGenerator() {
	}

	@SideOnly(Side.CLIENT)
	public void func_145845_h() {
		if(worldObj.isRemote) {
			if(this.stabilizerMode) {
				this.rotation += 0.5F;
				this.spawnStabilizerParticle();
			}

			if(!this.stabilizerMode) {
				if(this.signal && !this.inverted) {
					this.active = true;
				} else if(!this.signal && this.inverted) {
					this.active = true;
				} else {
					this.active = false;
				}

				if(tick >= spawn_rate && active) {
					this.tick = 0;
					Random rand = worldObj.rand;
					float MX = motion_x + this.random_motion_x * rand.nextFloat();
					float MY = motionY + this.random_motion_y * rand.nextFloat();
					float MZ = motion_z + this.random_motion_z * rand.nextFloat();
					float SCALE = scale + this.random_scale * rand.nextFloat();
					double spawnX = (double)((float)this.field_145851_c + this.spawn_x + random_spawn_x * rand.nextFloat());
					double spawnY = (double)((float)this.field_145848_d + this.spawn_y + random_spawn_y * rand.nextFloat());
					double spawnZ = (double)((float)this.field_145849_e + this.spawn_z + random_spawn_z * rand.nextFloat());
					ParticleCustom particle = new ParticleCustom(worldObj, spawnX + 0.5D, spawnY + 0.5D, spawnZ + 0.5D, MX, MY, MZ, SCALE, this.collide, this.selected_particle);
					particle.red = this.red + rand.nextInt(this.random_red + 1);
					particle.green = this.green + rand.nextInt(this.random_green + 1);
					particle.blue = this.blue + rand.nextInt(this.random_blue + 1);
					particle.maxAge = this.life + rand.nextInt(this.random_life + 1);
					particle.fadeTime = this.fade;
					particle.fadeLength = this.fade;
					particle.gravity = this.gravity;
					ParticleHandler.spawnCustomParticle(particle);
				} else {
					++this.tick;
				}

			}
		}
	}

	@SideOnly(Side.CLIENT)
	private void spawnStabilizerParticle() {
		if(this.getMaster() != null && worldObj.getTotalWorldTime() % 20L == 1L) {
			double x = (double)this.field_145851_c + 0.5D;
			double y = (double)this.field_145848_d + 0.5D;
			double z = (double)this.field_145849_e + 0.5D;
			byte direction = 0;
			if(this.getMaster().field_145851_c > this.field_145851_c) {
				direction = 0;
			} else if(this.getMaster().field_145851_c < this.field_145851_c) {
				direction = 1;
			} else if(this.getMaster().field_145849_e > this.field_145849_e) {
				direction = 2;
			} else if(this.getMaster().field_145849_e < this.field_145849_e) {
				direction = 3;
			}

			EnergyBeamParticle particle = new EnergyBeamParticle(worldObj, x, y, z, (double)this.getMaster().field_145851_c + 0.5D, (double)this.getMaster().field_145849_e + 0.5D, direction, false);
			EnergyBeamParticle particle2 = new EnergyBeamParticle(worldObj, x, y, z, (double)this.getMaster().field_145851_c + 0.5D, (double)this.getMaster().field_145849_e + 0.5D, direction, true);
			ParticleHandler.spawnCustomParticle(particle, 60.0D);
			ParticleHandler.spawnCustomParticle(particle2, 60.0D);
		}
	}

	public void toggleInverted() {
		inverted = !inverted;
	}

	public Packet func_145844_m() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.func_145841_b(tagCompound);
		return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, tagCompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
		worldObj.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
	}

	public void writeToNBT(NBTTagCompound nbt) {
		master.writeToNBT(nbt, "Key");
		nbt.setBoolean("StabalizerMode", stabilizerMode);
		nbt.setInteger("Red", red);
		nbt.setInteger("Green", green);
		nbt.setInteger("Blue", blue);
		nbt.setInteger("RandomRed", random_red);
		nbt.setInteger("RandomGreen", random_green);
		nbt.setInteger("RandomBlue", random_blue);
		nbt.setFloat("MotionX", motion_x);
		nbt.setFloat("MotionY", motionY);
		nbt.setFloat("MotionZ", motion_z);
		nbt.setFloat("RandomMotionX", random_motion_x);
		nbt.setFloat("RandomMotionY", random_motion_y);
		nbt.setFloat("RandomMotionZ", random_motion_z);
		nbt.setFloat("Scale", scale);
		nbt.setFloat("RandomScale", random_scale);
		nbt.setInteger("Life", life);
		nbt.setInteger("RandomLife", random_life);
		nbt.setFloat("SpawnX", spawn_x);
		nbt.setFloat("SpawnY", spawn_y);
		nbt.setFloat("SpawnZ", spawn_z);
		nbt.setFloat("RandomSpawnX", random_spawn_x);
		nbt.setFloat("RandomSpawnY", random_spawn_y);
		nbt.setFloat("RandomSpawnZ", random_spawn_z);
		nbt.setInteger("Page", page);
		nbt.setInteger("SpawnRate", spawn_rate);
		nbt.setBoolean("CanCollide", collide);
		nbt.setInteger("Fade", fade);
		nbt.setInteger("SelectedParticle", selected_particle);
		nbt.setFloat("Gravity", gravity);
		nbt.setBoolean("Active", active);
		nbt.setBoolean("Signal", signal);
		nbt.setBoolean("Inverted", inverted);
		super.writeToNBT(nbt);
	}

	public void readFromNBT(NBTTagCompound compound) {
		master.readFromNBT(compound, "Key");
		stabilizerMode = compound.getBoolean("StabalizerMode");
		red = compound.getInteger("Red");
		green = compound.getInteger("Green");
		blue = compound.getInteger("Blue");
		random_red = compound.getInteger("RandomRed");
		random_green = compound.getInteger("RandomGreen");
		random_blue = compound.getInteger("RandomBlue");
		motion_x = compound.getFloat("MotionX");
		motionY = compound.getFloat("MotionY");
		motion_z = compound.getFloat("MotionZ");
		random_motion_x = compound.getFloat("RandomMotionX");
		random_motion_y = compound.getFloat("RandomMotionY");
		random_motion_z = compound.getFloat("RandomMotionZ");
		scale = compound.getFloat("Scale");
		random_scale = compound.getFloat("RandomScale");
		life = compound.getInteger("Life");
		random_life = compound.getInteger("RandomLife");
		spawn_x = compound.getFloat("SpawnX");
		spawn_y = compound.getFloat("SpawnY");
		spawn_z = compound.getFloat("SpawnZ");
		random_spawn_x = compound.getFloat("RandomSpawnX");
		random_spawn_y = compound.getFloat("RandomSpawnY");
		random_spawn_z = compound.getFloat("RandomSpawnZ");
		page = compound.getInteger("Page");
		spawn_rate = compound.getInteger("SpawnRate");
		collide = compound.getBoolean("CanCollide");
		fade = compound.getInteger("Fade");
		selected_particle = compound.getInteger("SelectedParticle");
		gravity = compound.getFloat("Gravity");
		active = compound.getBoolean("Active");
		signal = compound.getBoolean("Signal");
		inverted = compound.getBoolean("Inverted");
		super.readFromNBT(compound);
	}

	public TileEnergyStorageCore getMaster() {
		TileEntity masterTile = field_145850_b.getTileEntity(master.getXCoord(), master.getYCoord(), master.getZCoord());
		return master != null && masterTile != null && masterTile instanceof TileEnergyStorageCore ? (TileEnergyStorageCore) masterTile : null;
	}

	public void setMaster(TileLocation master) {
	this.master = master;
	}

	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}
}
