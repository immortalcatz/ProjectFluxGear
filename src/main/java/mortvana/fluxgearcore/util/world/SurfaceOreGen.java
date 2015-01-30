package mortvana.fluxgearcore.util.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import mortvana.fluxgearcore.common.FluxGearCoreConfig;

public class SurfaceOreGen extends WorldGenerator{

	/** The block of the ore to be placed using this generator. */
	public Block minableBlock;
	public int minableBlockMeta = 0;

	/** The number of blocks to generate. */
	public int numberOfBlocks;
	public Block[] replaceBlocks;
	public boolean alterSize;

	public SurfaceOreGen(Block block, int meta, int number, boolean changeSize) {
		this(block, meta, number, changeSize, Blocks.stone, Blocks.grass, Blocks.dirt, Blocks.water, Blocks.sand, Blocks.gravel, Blocks.snow);
	}

	public SurfaceOreGen(Block block, int meta, int number, boolean changeSize, Block... target) {
		minableBlock = block;
		numberOfBlocks = number;
		replaceBlocks = target;
		alterSize = changeSize;
		minableBlockMeta = meta;
	}

	public int findGround (World world, int x, int y, int z) {
		int returnHeight = -1;
		Block block = world.getBlock(x, y - 1, z);
		if (!world.getBlock(x, y, z).isOpaqueCube() && (block == Blocks.dirt || block == Blocks.grass)) {
			return y;
		}
		int height = FluxGearCoreConfig.seaLevel + 64;
		do {
			if (height < FluxGearCoreConfig.seaLevel - 30) {
				break;
			}
			Block b = world.getBlock(x, height, z);
			if (b == Blocks.dirt || b == Blocks.grass) {
				if (!world.getBlock(x, height + 1, z).isOpaqueCube()) {
					returnHeight = height + 1;
				}
				break;
			}
			height--;
		} while (height > 0);
		return returnHeight;
	}

	@Override
	public boolean generate (World world, Random random, int startX, int startY, int startZ) {
		if (alterSize) {
			startY = findGround(world, startX, startY, startZ);
			if (startY == -1)
				return false;
		}

		float f = random.nextFloat() * (float) Math.PI;
		int blockNumber = numberOfBlocks;
		if (alterSize)
			blockNumber = numberOfBlocks * 2 / 5 + random.nextInt(numberOfBlocks * 3 / 5);
		double d0 = (double) ((float) (startX + 8) + MathHelper.sin(f) * (float) blockNumber / 8.0F);
		double d1 = (double) ((float) (startX + 8) - MathHelper.sin(f) * (float) blockNumber / 8.0F);
		double d2 = (double) ((float) (startZ + 8) + MathHelper.cos(f) * (float) blockNumber / 8.0F);
		double d3 = (double) ((float) (startZ + 8) - MathHelper.cos(f) * (float) blockNumber / 8.0F);
		double d4 = (double) (startY + random.nextInt(3) - 2);
		double d5 = (double) (startY + random.nextInt(3) - 2);

		for (int l = 0; l <= blockNumber; ++l) {
			double d6 = d0 + (d1 - d0) * (double) l / (double) blockNumber;
			double d7 = d4 + (d5 - d4) * (double) l / (double) blockNumber;
			double d8 = d2 + (d3 - d2) * (double) l / (double) blockNumber;
			double d9 = random.nextDouble() * (double) blockNumber / 16.0D;
			double d10 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) blockNumber) + 1.0F) * d9 + 1.0D;
			double d11 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) blockNumber) + 1.0F) * d9 + 1.0D;
			int i1 = MathHelper.floor_double(d6 - d10 / 2.0D);
			int j1 = MathHelper.floor_double(d7 - d11 / 2.0D);
			int k1 = MathHelper.floor_double(d8 - d10 / 2.0D);
			int l1 = MathHelper.floor_double(d6 + d10 / 2.0D);
			int i2 = MathHelper.floor_double(d7 + d11 / 2.0D);
			int j2 = MathHelper.floor_double(d8 + d10 / 2.0D);

			for (int k2 = i1; k2 <= l1; ++k2) {
				double d12 = ((double) k2 + 0.5D - d6) / (d10 / 2.0D);

				if (d12 * d12 < 1.0D) {
					for (int l2 = j1; l2 <= i2; ++l2) {
						double d13 = ((double) l2 + 0.5D - d7) / (d11 / 2.0D);

						if (d12 * d12 + d13 * d13 < 1.0D) {
							for (int i3 = k1; i3 <= j2; ++i3) {
								double d14 = ((double) i3 + 0.5D - d8) / (d10 / 2.0D);

								Block block = world.getBlock(k2, l2, i3);
								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
									if (block == null || !world.getBlock(k2, l2, i3).isOpaqueCube())
										world.setBlock(k2, l2, i3, this.minableBlock, minableBlockMeta, 2);
									else {
										for (int iter = 0; iter < replaceBlocks.length; iter++) {
											if (world.getBlock(k2, l2, i3).isReplaceableOreGen(world, k2, l2, i3, replaceBlocks[iter])) {
												world.setBlock(k2, l2, i3, this.minableBlock, minableBlockMeta, 2);
												break;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
