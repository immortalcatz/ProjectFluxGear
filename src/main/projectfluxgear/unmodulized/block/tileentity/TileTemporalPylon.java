package mortvana.projectfluxgear.unmodulized.block.tileentity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import mortvana.projectfluxgear.unmodulized.common.FluxGearContent;

public class TileTemporalPylon extends TileEntity {
	private static final String[] MODES = new String[]{"Stopped", "Radius: +1, Area: 3x3x3", "Radius: +2, Area: 5x3x5", "Radius: +3, Area: 7x3x7", "Radius: +4, Area: 9x3x9"};
	private static final String[] SPEEDS = new String[]{"Stopped", "100% increase", "200% increase", "300% increase", "400% increase"};
	private boolean isActive;
	private byte speed;
	private byte mode;
	private byte cachedMode;
	private Box box;
	private Random rand;

	public TileTemporalPylon() {
		this.isActive = true;
		this.cachedMode = -1;
		this.rand = new Random();
	}

	@Override
	public void updateEntity() {
		if (this.worldObj.isRemote)
			return;
		if (this.cachedMode != mode) {
			this.box = new Box(xCoord - mode, yCoord - 1, zCoord - mode, xCoord + mode, yCoord + 1, zCoord + mode);
			this.cachedMode = mode;
		}
		if (!isActive || mode == 0 || speed == 0)
			return;
		for (int x = this.box.xMin; x <= this.box.xMax; x++) {
			for (int y = this.box.yMin; y <= this.box.yMax; y++) {
				for (int z = this.box.zMin; z <= this.box.zMax; z++) {
					final Block block = this.worldObj.getBlock(x, y, z);
					if (block == Blocks.air || block == FluxGearContent.blockTemporalPylon)
						continue;
					if (block.getTickRandomly()) {
						for (int i = 0; i < this.speed; i++)
							block.updateTick(this.worldObj, x, y, z, this.rand);
					}
					final TileEntity tile = this.worldObj.getTileEntity(x, y, z);
					if (tile != null && !(tile instanceof TileTemporalPylon) && !tile.isInvalid()) {
						for (int i = 0; i < this.speed; i++)
							tile.updateEntity();
					}
				}
			}
		}
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

	public void changeMode(boolean sneaking) {
		if (sneaking) {
			if (speed < SPEEDS.length - 1)
				speed++;
			else
				speed = 0;
		} else {
			if (mode < MODES.length - 1)
				mode++;
			else
				mode = 0;
		}
	}

	public String getSpeedDescription() {
		return SPEEDS[speed];
	}

	public String getModeDescription() {
		return MODES[mode];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("Speed", speed);
		nbt.setByte("Mode", mode);
		nbt.setBoolean("IsActive", isActive);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		speed = nbt.getByte("Speed");
		mode = nbt.getByte("Mode");
		isActive = nbt.getBoolean("IsActive");
	}

	public class Box {
		public int xMin;
		public int yMin;
		public int zMin;
		public int xMax;
		public int yMax;
		public int zMax;
		public Box(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax) {
			this.xMin = xMin;
			this.yMin = yMin;
			this.zMin = zMin;
			this.xMax = xMax;
			this.yMax = yMax;
			this.zMax = zMax;
		}

		@Override
		public String toString() {
			return "(" + xMin + ", " + yMin + ", " + zMin + ") to (" + xMax + ", " + yMax + ", " + zMax + ")";
		}
	}
}