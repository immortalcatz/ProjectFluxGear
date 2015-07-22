package mortvana.legacy.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

import mortvana.melteddashboard.util.helpers.LoadedHelper;
import mortvana.legacy.common.FluxGearContent;
import mortvana.legacy.common.ProjectFluxGear;

public class PaintingHelper {
	public static int colorStoneBlocks(World world, int x, int y, int z, int inputMeta, int range, int maxBlocks) {
		boolean changed = false;
		int amount = 0;
		FluxGearContent content = ProjectFluxGear.content;

		for(int xPos = -range; xPos <= range && amount <= maxBlocks; ++xPos) {
			for(int yPos = -range; yPos <= range && amount <= maxBlocks; ++yPos) {
				for(int zPos = -range; zPos <= range && amount <= maxBlocks; ++zPos) {
					Block block = world.getBlock(x + xPos, y + yPos, z + zPos);
					if(block == Blocks.stone) {
						++amount;
						world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStone, inputMeta, 3);
					} else if(block == Blocks.cobblestone) {
						++amount;						world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredCobble, inputMeta, 3);
					} else if(block == Blocks.mossy_cobblestone) {
						++amount;
						world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredMossCobble, inputMeta, 3);
					} else {
						int meta;
						if(block == Blocks.stonebrick) {
							++amount;
							meta = world.getBlockMetadata(x + xPos, y + yPos, z + zPos);
							if(meta == 0) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneBrick, inputMeta, 3);
							} else if(meta == 1) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredMossStoneBrick, inputMeta, 3);
							} else if(meta == 2) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredCrackedStoneBrick, inputMeta, 3);
							} else if(meta == 3) {
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneSquareBrick, inputMeta, 3);
							}
						} else if(block == Blocks.wool) {
							++amount;
							world.setBlock(x + xPos, y + yPos, z + zPos, Blocks.wool, inputMeta, 3);
						} else if(block == Blocks.brick_block) {
							++amount;
							world.setBlock(x + xPos, y + yPos, z + zPos, content.clayBrickSmall, inputMeta, 3);
						} else if(LoadedHelper.isTinkersLoaded && block == GameRegistry.findBlock("TConstruct", "decoration.multibrickfancy")) {
							meta = world.getBlockMetadata(x + xPos, y + yPos, z + zPos);
							if(meta == 14) {
								++amount;
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneFancyBrick, inputMeta, 3);
							} else if(meta == 15) {
								++amount;
								world.setBlock(x + xPos, y + yPos, z + zPos, content.coloredStoneRoad, inputMeta, 3);
							}
						}
					}
				}
			}
		}

		return amount;
	}
}
