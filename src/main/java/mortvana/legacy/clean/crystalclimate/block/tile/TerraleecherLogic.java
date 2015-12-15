package mortvana.legacy.clean.crystalclimate.block.tile;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import mortvana.legacy.errored.crystalclimate.CrystalClimate;

public class TerraleecherLogic extends TileEntity {
	Random random = new Random();
	int xScan = 0;
	int yScan = 0;
	int zScan = 0;
	int pass;
	boolean init;

	@Override
	public void updateEntity() {
		if (!init) {
			init = true;
			yScan = yCoord - 1;
			if (yScan > 64)
				yScan = 64;
		}
		if (worldObj.isRemote) {
			for (int i = 0; i < 16; i++) {
				worldObj.spawnParticle("enchantmenttable", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, -1, 0);
			}
		} else if (pass < 3) {
			if (worldObj.getTotalWorldTime() % 5 == 0) /*for (int i = 0; i < 5; i++)*/ {
				eatBlock(xCoord - 12 + xScan, zCoord - 12 + zScan);
				incrementPosition();
			}
		}
	}

	void eatBlock(int x, int z) {
		Block block = worldObj.getBlock(x, yScan, z);
		if (pass == 0) {
			if (block != null && block != Blocks.stone && block != Blocks.netherrack && block != CrystalClimate.leechedStone && block.getBlockHardness(worldObj, x, yScan, z) >= 0 && block.getMaterial() == Material.rock) {
				int meta = worldObj.getBlockMetadata(x, yScan, z);
				if (placeBlockAbove(x, yCoord - 8, z, block, meta)) {
					worldObj.setBlock(x, yScan, z, CrystalClimate.leechedStone);
				}
			}
		} else if (pass == 1) {
			if (block != null && block != Blocks.stone && block != Blocks.netherrack && block.getBlockHardness(worldObj, x, yScan, z) >= 0 && (block.getMaterial() == Material.rock || block.getMaterial() == Material.ground || block.getMaterial() == Material.sand)) {
				int meta = worldObj.getBlockMetadata(x, yScan, z);
				if (placeBlockAbove(x, yCoord - 8, z, block, meta)) {
					worldObj.setBlockToAir(x, yScan, z);
				}
			}
		} else if (pass == 2) {
			if (block != null && block.getBlockHardness(worldObj, x, yScan, z) >= 0 && (block.getMaterial() == Material.rock || block.getMaterial() == Material.ground || block.getMaterial() == Material.sand)) {
				eatBlock(x, yScan, z);
			}
		}
	}

	private boolean placeBlockAbove(int x, int y, int z, Block block, int meta) {
		boolean placed = false;
		do {
			y++;
			if (y > 255)
				break;

			Block check = worldObj.getBlock(x, y, z);
			if (check == null || check.isReplaceable(worldObj, x, y, z)) {
				if (worldObj.setBlock(x, y, z, block, meta, 3))
					placed = true;
			}
		} while (!placed);
	    /*if (placed)
            System.out.println("Placed block at " + new CoordTuple(x, y, z));*/

		return placed;
	}

	void eatBlock(int x, int y, int z) { //TODO: Make this better
		worldObj.setBlockToAir(x, y, z);
	}

	void incrementPosition() {
		xScan++;
		if (xScan >= 25) {
			xScan = 0;
			zScan++;
			//System.out.println("Now checking z " + zLeech);
			if (zScan >= 25) {
				zScan = 0;
				yScan--;
				//System.out.println("Now checking layer " + yLeech);
				if (yScan <= 0) {
					xScan = 0;
					yScan = yCoord - 1;
					zScan = 0;
					pass++;
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		xScan = tags.getInteger("xScan");
		yScan = tags.getInteger("yScan");
		zScan = tags.getInteger("zScan");
		pass = tags.getByte("TerraformPass");
		init = tags.getBoolean("Initialized");
	}

	@Override
	public void writeToNBT(NBTTagCompound tags) {
		super.writeToNBT(tags);
		tags.setInteger("xScan", xScan);
		tags.setInteger("yScan", yScan);
		tags.setInteger("zScan", zScan);
		tags.setByte("TerraformPass", (byte) pass);
		tags.setBoolean("Initialized", init);
	}
}
