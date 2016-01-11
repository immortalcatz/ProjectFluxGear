package mortvana.projectfluxgear.immersion.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.melteddashboard.util.helpers.ColorHelper;
import mortvana.projectfluxgear.immersion.item.ItemPaintbrush;
import mortvana.projectfluxgear.immersion.util.PaintEntry;

import static mortvana.melteddashboard.util.helpers.science.MathHelper.offsetIntToFloat;

public class PaintingHelper {

	public static TMap<Integer, PaintEntry> paintTypes = new THashMap<Integer, PaintEntry>();
	public static Block.SoundType sound = Blocks.stone.stepSound;
	static {
		for (int i = 0; i < 16; i++) {
			paintTypes.put(i, new PaintEntry(ColorHelper.LOWER_PAINT_NAMES[i], ColorHelper.MC_DYE_COLORS[i]));
		}
	}

	public static boolean paintBlocks(World world, int x, int y, int z, ItemStack itemstack, EntityLivingBase player, int paintRadius) {
		int damage = itemstack.getMetadata();
		if (itemstack.hasTagCompound()) {
			int type = itemstack.getTagCompound().getInteger("PaintType");
			if (paintTypes.containsKey(type) && itemstack.getItem() instanceof ItemPaintbrush) {
				int amount = 0;//colorBlocks(world, x, y, z, type, paintRadius, ((ItemPaintbrush) itemstack.getItem()).maxPaint - damage);
				if (amount > 0) {
					if (!player.worldObj.isRemote) {
						player.worldObj.playSoundEffect(offsetIntToFloat(x), offsetIntToFloat(y), offsetIntToFloat(z), sound.getPlaceSound(), (sound.getVolume() + 1.0F) / 2.0F, sound.getFrequency() * 0.8F);
					}

					if (amount + damage >= ((ItemPaintbrush) itemstack.getItem()).maxPaint) {
						resetBrush(itemstack);
					} else {
						itemstack.damageItem(amount, player);
					}
					return true;
				}
			}
		}
		return false;
	}

	public static void resetBrush(ItemStack itemstack) {
		itemstack.setMetadata(0);
		itemstack.getTagCompound().setInteger("PaintType", -1);
	}




















	/*public static int colorStoneBlocks(World world, int x, int y, int z, int inputMeta, int range, int maxBlocks) {
		boolean changed = false;
		int amount = 0;

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
	}*/
}
