package mortvana.legacy.errored.crystalclimate.block.itemblock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.legacy.errored.crystalclimate.block.BlockCrystal;
import mortvana.melteddashboard.util.helpers.MiscHelper;

public class ItemBlockCrystal extends ItemBlock {

	public Block block;

	public ItemBlockCrystal(Block block) {
		super(block);
		setMaxDamage(0);
		this.block = block;
		//setHasSubtypes(true);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);

		if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
			side = 1;
		} else if (!MiscHelper.isBlockEqual(block, Blocks.vine, Blocks.tallgrass, Blocks.deadbush) && (Block.blocksList[block] == null || !Block.blocksList[block].isBlockReplaceable(world, x, y, z))) {
			switch (side) {
				case 0:
					y--;
				case 1:
					y++;
				case 2:
					z--;
				case 3:
					z++;
				case 4:
					x--;
				case 5:
					x++;
			}
		}

		if (stack.stackSize == 0 || !player.canPlayerEdit(x, y, z, side, stack) || y == 255 && Block.blocksList[block].blockMaterial.isSolid()) {
			return false;
		} else if (world.canPlaceEntityOnSide(block, x, y, z, false, side, player, stack)) {
			Block block2 = Block.blocksList[block];

			int crystalValue = 0;
			if (stack.hasTagCompound()) {
				crystalValue = stack.getTagCompound().getInteger("Value");
			}

			int placeMeta = getBaseMeta(crystalValue);

			if (placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, placeMeta)) {
				world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), block2.stepSound.getPlaceSound(), (block2.stepSound.getVolume() + 1.0F) / 2.0F, block2.stepSound.getPitch() * 0.8F);
				--stack.stackSize;

				int height = BlockCrystal.getCrystalHeight(crystalValue);
				if (height > 1) {
					Block localBlock = world.getBlock(x, y + 1, z);
					if (!MiscHelper.isBlockEqual(localBlock, Blocks.vine, Blocks.tallgrass, Blocks.deadbush) && (Block.blocksList[localBlock] == null || !Block.blocksList[localBlock].isBlockReplaceable(world, x, y + 1, z))) {
						placeBlockAt(stack, player, world, x, y + 1, z, side, hitX, hitY, hitZ, secondMeta(crystalValue));
					}
				}
				if (height > 2) {
					Block localBlock  = world.getBlock(x, y + 2, z);
					if (!MiscHelper.isBlockEqual(localBlock, Blocks.vine, Blocks.tallgrass, Blocks.deadbush) && (Block.blocksList[localBlock] == null || !Block.blocksList[localBlock].isBlockReplaceable(world, x, y + 2, z))) {
						placeBlockAt(stack, player, world, x, y + 2, z, side, hitX, hitY, hitZ, thirdMeta(crystalValue));
					}
				}
				if (height > 3) {
					Block localBlock  = world.getBlock(x, y + 3, z);
					if (!MiscHelper.isBlockEqual(localBlock, Blocks.vine, Blocks.tallgrass, Blocks.deadbush) && (Block.blocksList[localBlock] == null || !Block.blocksList[localBlock].isBlockReplaceable(world, x, y + 3, z))) {
						placeBlockAt(stack, player, world, x, y + 3, z, side, hitX, hitY, hitZ, topMeta(crystalValue));
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	int getBaseMeta(int crystalValue) {
		if (crystalValue >= 120) {
			return 5;
		}
		if (crystalValue >= 80) {
			return 4;
		}
		if (crystalValue >= 48) {
			return 3;
		}
		if (crystalValue >= 24) {
			return 2;
		}
		if (crystalValue >= 8) {
			return 1;
		}

		return 0;
	}

	int secondMeta(int crystalValue) {
		if (crystalValue >= 224) {
			return 9;
		}
		if (crystalValue >= 168) {
			return 8;
		}
		if (crystalValue >= 120) {
			return 7;
		}
		return 6;
	}

	int thirdMeta(int crystalValue) {
		if (crystalValue >= 440) {
			return 13;
		}
		if (crystalValue >= 360) {
			return 12;
		}
		if (crystalValue >= 288) {
			return 11;
		}
		return 10;
	}

	public int topMeta(int crystalValue) {
		return crystalValue >= 528 ? 15 : 14;
	}

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (stack.hasTagCompound()) {
			int value = stack.getTagCompound().getInteger("Value");
			list.add("Crystal Value: " + value);
		}
	}
}
