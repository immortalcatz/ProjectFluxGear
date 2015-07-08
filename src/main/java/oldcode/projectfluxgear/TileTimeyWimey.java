package oldcode.projectfluxgear;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.BlockFluidBase;

public class TileTimeyWimey extends TileEntity {

	public static final String[] MODES = new String[] { "Stopped", "Radius: +1, Area: 3x3x3", "Radius: +2, Area: 5x3x5", "Radius: +3, Area: 7x3x7", "Radius: +4, Area: 9x3x9" };
	public static final String[] SPEEDS = new String[] { "Stopped", "100% increase", "200% increase", "300% increase", "400% increase", "500% increase", "600% increase", "700% increase" };
	public boolean isActive;
	public byte speed;
	public byte mode;
	public byte cachedMode;
	public Random rand;
	public int xMin;
	public int yMin;
	public int zMin;
	public int xMax;
	public int yMax;
	public int zMax;

	public TileTimeyWimey() {
		isActive = true;
		cachedMode = -1;
		rand = new Random();
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			return;
		}
		if (!isActive || mode == 0 || speed == 0) {
			return;
		}
		if (cachedMode != mode){
			xMin = xCoord - mode;
			yMin = yCoord - 1;
			zMin = zCoord - mode;
			xMax = xCoord + mode;
			yMax = yCoord + 1;
			zMax = zCoord + mode;
			cachedMode = mode;
		}
		for (int x = xMin; x <= xMax; x++){
			for (int y = yMin; y <= yMax; y++){
				for (int z = zMin; z <= zMax; z++){
					Block block = worldObj.getBlock(x, y, z);
					if (block == null || blacklistedBlocks.contains(block) || block instanceof BlockFluidBase) {
						continue;
					}
					if (block.getTickRandomly()) {
						for (int i = 0; i < speed; i++) {
							block.updateTick(worldObj, x, y, z, rand);
						}
					}
					if (block.hasTileEntity(worldObj.getBlockMetadata(x, y, z))) {
						TileEntity tile = this.worldObj.getTileEntity(x, y, z);
						if (tile != null && !tile.isInvalid()) {
							if (blacklistedTiles.contains(tile.getClass())) {
								continue;
							}
							for (int i = 0; i < speed; i++) {
								if (tile.isInvalid()) {
									break;
								}
								tile.updateEntity();
							}
						}
					}
				}
			}
		}
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public void changeMode(boolean sneaking) {
		if (sneaking) {
			if (speed < SPEEDS.length - 1) {
				speed++;
			} else {
				speed = 0;
			}
		} else {
			if (mode < MODES.length - 1) {
				mode++;
			} else {
				mode = 0;
			}
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

	public static void blacklistBlock(Block block){
		blacklistedBlocks.add(block);
	}

	public static void blacklistTile(Class<? extends TileEntity> tile){
		blacklistedTiles.add(tile);
	}

	protected static Set<Block> blacklistedBlocks = new HashSet<Block>();
	protected static Set<Class<? extends TileEntity>> blacklistedTiles = new HashSet<Class<? extends TileEntity>>();
}
