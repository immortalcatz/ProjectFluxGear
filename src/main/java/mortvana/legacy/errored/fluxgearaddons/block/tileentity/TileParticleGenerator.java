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
	public float motion_y = 0.0F;
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
		if(this.field_145850_b.isRemote) {
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
					Random rand = this.field_145850_b.rand;
					float MX = motion_x + this.random_motion_x * rand.nextFloat();
					float MY = motion_y + this.random_motion_y * rand.nextFloat();
					float MZ = motion_z + this.random_motion_z * rand.nextFloat();
					float SCALE = scale + this.random_scale * rand.nextFloat();
					double spawnX = (double)((float)this.field_145851_c + this.spawn_x + this.random_spawn_x * rand.nextFloat());
					double spawnY = (double)((float)this.field_145848_d + this.spawn_y + this.random_spawn_y * rand.nextFloat());
					double spawnZ = (double)((float)this.field_145849_e + this.spawn_z + this.random_spawn_z * rand.nextFloat());
					ParticleCustom particle = new ParticleCustom(this.field_145850_b, spawnX + 0.5D, spawnY + 0.5D, spawnZ + 0.5D, MX, MY, MZ, SCALE, this.collide, this.selected_particle);
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
		if(this.getMaster() != null && this.field_145850_b.getTotalWorldTime() % 20L == 1L) {
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

			EnergyBeamParticle particle = new EnergyBeamParticle(this.field_145850_b, x, y, z, (double)this.getMaster().field_145851_c + 0.5D, (double)this.getMaster().field_145849_e + 0.5D, direction, false);
			EnergyBeamParticle particle2 = new EnergyBeamParticle(this.field_145850_b, x, y, z, (double)this.getMaster().field_145851_c + 0.5D, (double)this.getMaster().field_145849_e + 0.5D, direction, true);
			ParticleHandler.spawnCustomParticle(particle, 60.0D);
			ParticleHandler.spawnCustomParticle(particle2, 60.0D);
		}
	}

	public void toggleInverted() {
		this.inverted = !this.inverted;
	}

	public Packet func_145844_m() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.func_145841_b(tagCompound);
		return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, tagCompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.func_145839_a(pkt.func_148857_g());
		this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
	}

	public void func_145841_b(NBTTagCompound compound) {
		this.master.writeToNBT(compound, "Key");
		compound.setBoolean("StabalizerMode", stabilizerMode);
		compound.setInteger("Red", red);
		compound.setInteger("Green", this.green);
		compound.setInteger("Blue", this.blue);
		compound.setInteger("RandomRed", this.random_red);
		compound.setInteger("RandomGreen", this.random_green);
		compound.setInteger("RandomBlue", this.random_blue);
		compound.setFloat("MotionX", this.motion_x);
		compound.setFloat("MotionY", this.motion_y);
		compound.setFloat("MotionZ", this.motion_z);
		compound.setFloat("RandomMotionX", this.random_motion_x);
		compound.setFloat("RandomMotionY", this.random_motion_y);
		compound.setFloat("RandomMotionZ", this.random_motion_z);
		compound.setFloat("Scale", this.scale);
		compound.setFloat("RandomScale", this.random_scale);
		compound.setInteger("Life", this.life);
		compound.setInteger("RandomLife", this.random_life);
		compound.setFloat("SpawnX", this.spawn_x);
		compound.setFloat("SpawnY", this.spawn_y);
		compound.setFloat("SpawnZ", this.spawn_z);
		compound.setFloat("RandomSpawnX", this.random_spawn_x);
		compound.setFloat("RandomSpawnY", this.random_spawn_y);
		compound.setFloat("RandomSpawnZ", this.random_spawn_z);
		compound.setInteger("Page", this.page);
		compound.setInteger("SpawnRate", this.spawn_rate);
		compound.setBoolean("CanCollide", this.collide);
		compound.setInteger("Fade", this.fade);
		compound.setInteger("SelectedParticle", this.selected_particle);
		compound.setFloat("Gravity", this.gravity);
		compound.setBoolean("Active", this.active);
		compound.setBoolean("Signal", this.signal);
		compound.setBoolean("Inverted", this.inverted);
		super.writeToNBT(compound);
	}

	public void func_145839_a(NBTTagCompound compound) {
		master.readFromNBT(compound, "Key");
		stabilizerMode = compound.getBoolean("StabalizerMode");
		red = compound.getInteger("Red");
		green = compound.getInteger("Green");
		blue = compound.getInteger("Blue");
		random_red = compound.getInteger("RandomRed");
		random_green = compound.getInteger("RandomGreen");
		random_blue = compound.getInteger("RandomBlue");
		motion_x = compound.getFloat("MotionX");
		this.motion_y = compound.getFloat("MotionY");
		this.motion_z = compound.getFloat("MotionZ");
		this.random_motion_x = compound.getFloat("RandomMotionX");
		this.random_motion_y = compound.getFloat("RandomMotionY");
		this.random_motion_z = compound.getFloat("RandomMotionZ");
		this.scale = compound.getFloat("Scale");
		this.random_scale = compound.getFloat("RandomScale");
		this.life = compound.getInteger("Life");
		this.random_life = compound.getInteger("RandomLife");
		this.spawn_x = compound.getFloat("SpawnX");
		this.spawn_y = compound.getFloat("SpawnY");
		this.spawn_z = compound.getFloat("SpawnZ");
		this.random_spawn_x = compound.getFloat("RandomSpawnX");
		this.random_spawn_y = compound.getFloat("RandomSpawnY");
		this.random_spawn_z = compound.getFloat("RandomSpawnZ");
		this.page = compound.getInteger("Page");
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
