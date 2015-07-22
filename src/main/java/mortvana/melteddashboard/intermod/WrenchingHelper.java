package mortvana.melteddashboard.intermod;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.block.IDismantleable;

import Reika.RotaryCraft.API.Screwdriverable;
import Reika.RotaryCraft.API.ShaftMachine;
import ic2.api.tile.IWrenchable;
import mortvana.melteddashboard.util.helpers.ServerHelper;

public class WrenchingHelper {

	public static boolean handleDismantling(World world, int x, int y, int z, EntityPlayer player, Block block) {
		((IDismantleable) block).dismantleBlock(player, world, x, y, z, false);
		return true;
	}

	public static void handleShaftMachine(TileEntity tile, World world, int x, int y, int z) {
		ShaftMachine machine = (ShaftMachine) tile;
		machine.setIORenderAlpha(512);
		world.markBlockForUpdate(x, y, z);
	}

	public static boolean handleScrewdriving(TileEntity tile, World world, int x, int y, int z, EntityPlayer player, int hitSide) {
		Screwdriverable screwdriverable = (Screwdriverable) tile;
		boolean flag;
		if (player.isSneaking()) {
			flag = screwdriverable.onShiftRightClick(world, x, y, z, ForgeDirection.VALID_DIRECTIONS[hitSide]);
		} else {
			flag = screwdriverable.onRightClick(world, x, y, z, ForgeDirection.VALID_DIRECTIONS[hitSide]);
		}
		return flag;
	}

	public static void handleFlowWrenching(World world, int x, int y, int z, Block block, int meta) {
		world.setBlockToAir(x, y, z);
		if (ServerHelper.isServerWorld(world)) {
			world.spawnEntityInWorld(new EntityItem(world, (double) x, (double) y, (double) z, new ItemStack(block, 1, meta)));
		}
	}

	public static boolean handleIndustrialWrenching(TileEntity tile, World world, int x, int y, int z, EntityPlayer player, Block block, int hitSide) {
		IWrenchable wrenchable = (IWrenchable) tile;
		if (player.isSneaking()) {
			//hitSide = BlockHelper.SIDE_OPPOSITE[hitSide];
		}
		if (wrenchable.wrenchCanSetFacing(player, hitSide)) {
			if (ServerHelper.isServerWorld(world)) {
				wrenchable.setFacing((short) hitSide);
			}
		} else if (wrenchable.wrenchCanRemove(player)) {
			ItemStack dropBlock = wrenchable.getWrenchDrop(player);

			if (dropBlock != null) {
				world.setBlockToAir(x, y, z);
				if (ServerHelper.isServerWorld(world)) {
					List<ItemStack> drops = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
					if (drops.isEmpty()) {
						drops.add(dropBlock);
					} else {
						drops.set(0, dropBlock);
					}
					for (ItemStack drop : drops) {
						float f = 1.0F;
						double x2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
						double y2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
						double z2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
						EntityItem entity = new EntityItem(world, x + x2, y + y2, z + z2, drop);
						entity.delayBeforeCanPickup = 10;
						world.spawnEntityInWorld(entity);
					}
				}
			}
		}
		return ServerHelper.isServerWorld(world);
	}
}
