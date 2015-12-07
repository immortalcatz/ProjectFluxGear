package mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.clean.fluxgearaddons.client.particle.*;
import mortvana.legacy.clean.fluxgearaddons.util.helpers.MultiblockHelper.TileLocation;

public class TileParticleGenerator extends TileEntity {
	public int red = 0;
	public int green = 0;
	public int blue = 0;
	public int randomRed = 0;
	public int randomGreen = 0;
	public int randomBlue = 0;
	public float motionX = 0.0F;
	public float motionY = 0.0F;
	public float motionZ = 0.0F;
	public float randomMotionX = 0.0F;
	public float randomMotionY = 0.0F;
	public float randomMotionZ = 0.0F;
	public float scale = 1.0F;
	public float randomScale = 0.0F;
	public int life = 100;
	public int randomLife = 0;
	public float spawnX = 0.0F;
	public float spawnY = 0.0F;
	public float spawnZ = 0.0F;
	public float randomSpawnX = 0.0F;
	public float randomSpawnY = 0.0F;
	public float randomSpawnZ = 0.0F;
	public int page = 1;
	public int fade = 0;
	public int spawnRate = 1;
	public boolean collide = false;
	public int selectedParticle = 1;
	public float gravity = 0.0F;
	public boolean active = true;
	public boolean signal = false;
	public boolean inverted = false;
	TileLocation master = new TileLocation();
	public float rotation = 0.0F;
	public boolean stabilizerMode = false;
	public int tick = 0;

	public TileParticleGenerator() {}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateEntity() {
		if (worldObj.isRemote) {
			if (stabilizerMode) {
				rotation += 0.5F;
				spawnStabilizerParticle();
			} else {
				active = signal != inverted;

				if (tick >= spawnRate && active) {
					tick = 0;
					Random rand = worldObj.rand;
					ParticleCustom particle = new ParticleCustom(worldObj, (float) xCoord + spawnX + 0.5D + randomSpawnX * rand.nextFloat(), (float) yCoord + spawnY + 0.5D + randomSpawnY * rand.nextFloat(), (float) zCoord + spawnZ + 0.5D + randomSpawnZ * rand.nextFloat(), motionX + randomMotionX * rand.nextFloat(), motionY + randomMotionY * rand.nextFloat(), motionZ + randomMotionZ * rand.nextFloat(), scale + randomScale * rand.nextFloat(), collide, selectedParticle);
					particle.red = red + rand.nextInt(randomRed + 1);
					particle.green = green + rand.nextInt(randomGreen + 1);
					particle.blue = blue + rand.nextInt(randomBlue + 1);
					particle.maxAge = life + rand.nextInt(randomLife + 1);
					particle.fadeTime = fade;
					particle.fadeLength = fade;
					particle.gravity = gravity;
					ParticleHandler.spawnCustomParticle(particle);
				} else {
					tick++;
				}

			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnStabilizerParticle() {
		if (getMaster() != null && worldObj.getTotalWorldTime() % 20L == 1L) {
			double x = (double) xCoord + 0.5D;
			double y = (double) yCoord + 0.5D;
			double z = (double) zCoord + 0.5D;
			byte direction = 0;
			if (getMaster().xCoord > xCoord) {
				direction = 0;
			} else if (getMaster().xCoord < xCoord) {
				direction = 1;
			} else if (getMaster().zCoord > zCoord) {
				direction = 2;
			} else if (getMaster().zCoord < zCoord) {
				direction = 3;
			}

			ParticleHandler.spawnCustomParticle(new EnergyBeamParticle(worldObj, x, y, z, (double) getMaster().xCoord + 0.5D, (double) getMaster().zCoord + 0.5D, direction, false), 60.0D);
			ParticleHandler.spawnCustomParticle(new EnergyBeamParticle(worldObj, x, y, z, (double) getMaster().xCoord + 0.5D, (double) getMaster().zCoord + 0.5D, direction, true), 60.0D);
		}
	}

	public void toggleInverted() {
		inverted = !inverted;
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
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		master.writeToNBT(nbt, "Key");
		nbt.setBoolean("StabalizerMode", stabilizerMode);
		nbt.setInteger("Red", red);
		nbt.setInteger("Green", green);
		nbt.setInteger("Blue", blue);
		nbt.setInteger("RandomRed", randomRed);
		nbt.setInteger("RandomGreen", randomGreen);
		nbt.setInteger("RandomBlue", randomBlue);
		nbt.setFloat("MotionX", motionX);
		nbt.setFloat("MotionY", motionY);
		nbt.setFloat("MotionZ", motionZ);
		nbt.setFloat("RandomMotionX", randomMotionX);
		nbt.setFloat("RandomMotionY", randomMotionY);
		nbt.setFloat("RandomMotionZ", randomMotionZ);
		nbt.setFloat("Scale", scale);
		nbt.setFloat("RandomScale", randomScale);
		nbt.setInteger("Life", life);
		nbt.setInteger("RandomLife", randomLife);
		nbt.setFloat("SpawnX", spawnX);
		nbt.setFloat("SpawnY", spawnY);
		nbt.setFloat("SpawnZ", spawnZ);
		nbt.setFloat("RandomSpawnX", randomSpawnX);
		nbt.setFloat("RandomSpawnY", randomSpawnY);
		nbt.setFloat("RandomSpawnZ", randomSpawnZ);
		nbt.setInteger("Page", page);
		nbt.setInteger("SpawnRate", spawnRate);
		nbt.setBoolean("CanCollide", collide);
		nbt.setInteger("Fade", fade);
		nbt.setInteger("SelectedParticle", selectedParticle);
		nbt.setFloat("Gravity", gravity);
		nbt.setBoolean("Active", active);
		nbt.setBoolean("Signal", signal);
		nbt.setBoolean("Inverted", inverted);
		super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		master.readFromNBT(nbt, "Key");
		stabilizerMode = nbt.getBoolean("StabalizerMode");
		red = nbt.getInteger("Red");
		green = nbt.getInteger("Green");
		blue = nbt.getInteger("Blue");
		randomRed = nbt.getInteger("RandomRed");
		randomGreen = nbt.getInteger("RandomGreen");
		randomBlue = nbt.getInteger("RandomBlue");
		motionX = nbt.getFloat("MotionX");
		motionY = nbt.getFloat("MotionY");
		motionZ = nbt.getFloat("MotionZ");
		randomMotionX = nbt.getFloat("RandomMotionX");
		randomMotionY = nbt.getFloat("RandomMotionY");
		randomMotionZ = nbt.getFloat("RandomMotionZ");
		scale = nbt.getFloat("Scale");
		randomScale = nbt.getFloat("RandomScale");
		life = nbt.getInteger("Life");
		randomLife = nbt.getInteger("RandomLife");
		spawnX = nbt.getFloat("SpawnX");
		spawnY = nbt.getFloat("SpawnY");
		spawnZ = nbt.getFloat("SpawnZ");
		randomSpawnX = nbt.getFloat("RandomSpawnX");
		randomSpawnY = nbt.getFloat("RandomSpawnY");
		randomSpawnZ = nbt.getFloat("RandomSpawnZ");
		page = nbt.getInteger("Page");
		spawnRate = nbt.getInteger("SpawnRate");
		collide = nbt.getBoolean("CanCollide");
		fade = nbt.getInteger("Fade");
		selectedParticle = nbt.getInteger("SelectedParticle");
		gravity = nbt.getFloat("Gravity");
		active = nbt.getBoolean("Active");
		signal = nbt.getBoolean("Signal");
		inverted = nbt.getBoolean("Inverted");
		super.readFromNBT(nbt);
	}

	public TileEnergyStorageCore getMaster() {
		TileEntity masterTile = worldObj.getTileEntity(master.getXCoord(), master.getYCoord(), master.getZCoord());
		return master != null && masterTile != null && masterTile instanceof TileEnergyStorageCore ? (TileEnergyStorageCore) masterTile : null;
	}

	public void setMaster(TileLocation master) {
		this.master = master;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}
}
