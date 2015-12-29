package mortvana.legacy.dependent.seconddegree.crystalclimate.block.tile;

import java.awt.geom.Ellipse2D;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import mortvana.legacy.dependent.firstdegree.crystalclimate.common.CrystalClimate;

public class TerragrowerLogic extends TileEntity {
	Random random = new Random();
	boolean init;
	int xScan = 0;
	int yScan = 1;
	int zScan = 0;
	int xRange;
	int zRange;
	boolean islandDone;
	boolean populated;
	Ellipse2D.Double ellipse;

	@Override
	public void updateEntity() {
		if (!init) {
			init = true;
			getNewEllipse();
		}
		if (worldObj.isRemote) {
			for (int i = 0; i < 16; i++)
				CrystalClimate.proxy.spawnParticle("leaf", xCoord + random.nextFloat() * 25 - 12, yCoord + random.nextFloat() * 10, zCoord + random.nextFloat() * 25 - 12, 0, 0, 0);
		} else {
			if (!islandDone) {
				if (worldObj.getTotalWorldTime() % 5 == 0) {
					placeBlock();
					incrementPosition();
				}
			} else {
				if (!populated) {
					populated = true;
					yScan = 3;
					xRange = random.nextInt(14 - yScan) + 22 - yScan * 2;
					zRange = random.nextInt(14 - yScan) + 22 - yScan * 2;
					for (int i = 0; i < 10; i++) {
						int xPos = xCoord + random.nextInt(xRange) - xRange / 2, yPos = yCoord - 1, zPos = zCoord + random.nextInt(zRange) - zRange / 2;
						ItemStack bonemeal = new ItemStack(Items.dye, 1, 15);
						ItemDye.applyBonemeal(bonemeal, worldObj, xPos, yPos, zPos, null);
					}
					for (int i = 0; i < 40; i++) {
						int xPos = xCoord + random.nextInt(xRange) - xRange / 2, yPos = yCoord, zPos = zCoord + random.nextInt(zRange) - zRange / 2;
						worldObj.setBlock(xPos, yPos, zPos, Blocks.sapling);
					}
				}
			}
		}
	}

	void placeBlock() {
		if (ellipse.contains(xScan, zScan)) {
			worldObj.setBlock(xCoord - xRange / 2 + xScan, yCoord - yScan, zCoord - zRange / 2 + zScan, yScan == 1 ? Blocks.grass: Blocks.dirt);
		}
	}

	void incrementPosition() {
		xScan++;
		if (xScan >= 25) {
			xScan = 0;
			zScan++;
			if (zScan >= 25) {
				zScan = 0;
				yScan++;
				System.out.println("Moving to " + yScan);
				getNewEllipse();
				if (yScan > 8) {
					islandDone = true;
					System.out.println("Terragrower is done");
				}
			}
		}
	}

	void getNewEllipse() {
		xRange = random.nextInt(14 - yScan) + 22 - yScan * 2;
		zRange = random.nextInt(14 - yScan) + 22 - yScan * 2;
		ellipse = new Ellipse2D.Double(0, 0, xRange, zRange);
	}

	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		init = tags.getBoolean("Initialized");
		xScan = tags.getInteger("xScan");
		yScan = tags.getInteger("yScan");
		zScan = tags.getInteger("zScan");
		xRange = tags.getInteger("xRange");
		zRange = tags.getInteger("zRange");
		ellipse = new Ellipse2D.Double(0, 0, xRange, zRange);
		islandDone = populated = tags.getBoolean("CompleteIsland");
	}

	@Override
	public void writeToNBT(NBTTagCompound tags) {
		super.writeToNBT(tags);
		tags.setBoolean("Initialized", init);
		tags.setInteger("xScan", xScan);
		tags.setInteger("yScan", yScan);
		tags.setInteger("zScan", zScan);
		tags.setInteger("xRange", xRange);
		tags.setInteger("zRange", zRange);
		tags.setBoolean("CompleteIsland", islandDone);
	}
}
