package mortvana.legacy.clean.projectfluxgear.util.helpers;

import java.util.List;

import Reika.RotaryCraft.API.Interfaces.Screwdriverable;
import Reika.RotaryCraft.API.Power.ShaftMachine;
import main.flowstoneenergy.core.interfaces.IFlowWrenchable;
import mortvana.legacy.clean.core.util.helpers.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.block.IDismantleable;

import ic2.api.tile.IWrenchable;
import ic2.api.util.Keys;
import pneumaticCraft.api.block.IPneumaticWrenchable;

public class WrenchingHelper {

	public static boolean delegateWrenching(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int hitSide) {
		Block block = world.getBlock(x, y, z);
		TileEntity tile = world.getTileEntity(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if (itemstack.hasTagCompound() && itemstack.stackTagCompound.hasKey("Mode")) {
			NBTTagCompound nbt = itemstack.stackTagCompound;
			int mode = nbt.getByte("Mode");

			switch (mode) {
				case 0: { //EnumWrenchMode.STANDARD.ordinal()
					/* IDismantleable Compat */
					if (!world.isRemote && player.isSneaking() && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(player, world, x, y, z)) {
						return handleDismantling(world, x, y, z, player, block);
					}

					/* Crude RotaryCraft Screwdriver Support */
					if (nbt.hasKey("RotaryScrewdiver")) {
						if (tile instanceof ShaftMachine) {
							handleShaftMachine(tile, world, x, y, z);
						}
						if (tile instanceof Screwdriverable) {
							return handleScrewdriving(tile, world, x, y, z, player, hitSide);
						}
					}

					/* Flowstone Energy Compat */
					if (nbt.hasKey("FlowstoneEnergyWrench") && block instanceof IFlowWrenchable && player.isSneaking()) {
						handleFlowWrenching(world, x, y, z, block, meta);
					}

					/* Industrial Craft 2 */
					if (nbt.hasKey("IndustrialCraftWrench") && tile instanceof IWrenchable) {
						return handleIndustrialWrenching(tile, world, x, y, z, player, block, hitSide);
					}

					/* IPneumaticWrench */
					if (block instanceof IPneumaticWrenchable && !world.isRemote) {
						if (((IPneumaticWrenchable) block).rotateBlock(world, player, x, y, z, ForgeDirection.getOrientation(hitSide))) {
							//TODO: Fix
							//NetworkHandler.sendToAllAround(new PacketPlaySound(Sounds.PNEUMATIC_WRENCH, x, y, z, 1.0F, 1.0F, false), world);
							return true;
						}
					}
				}

				case 1: { //EnumWrenchMode.CONFIGURATION.ordinal()
				}

				case 2: { //EnumWrenchMode.ROTATION.ordinal()
					/* Rotation Handling */
					if (BlockHelper.canRotate(block)) {
						if (player.isSneaking()) {
							world.setBlockMetadataWithNotify(x, y, z, BlockHelper.rotateVanillaBlockAlt(world, block, x, y, z), 3);
							//TODO: Fix
						/*if (ServerHelper.isClientWorld(world)) {
							String soundName = block.stepSound.getBreakSound();
							FMLClientHandler.instance().getClient().getSoundHandler().playSound(new SoundBase(soundName, 1.0F, 0.6F));
						}*/
						} else {
							world.setBlockMetadataWithNotify(x, y, z, BlockHelper.rotateVanillaBlock(world, block, x, y, z), 3);
							//TODO: Fix
						/*if (ServerHelper.isClientWorld(world)) {
							String soundName = block.stepSound.getBreakSound();
							FMLClientHandler.instance().getClient().getSoundHandler().playSound(new SoundBase(soundName, 1.0F, 0.8F));
						}*/
						}
						return !world.isRemote;
					} else if (!player.isSneaking() && block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(hitSide))) {
						player.swingItem();
						return !world.isRemote;
					}
				}

				case 3: { //EnumWrenchMode.INFORMATION.ordinal()

				}
			}
		}


		return false;
	}

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
		if (!world.isRemote) {
			world.spawnEntityInWorld(new EntityItem(world, (double) x, (double) y, (double) z, new ItemStack(block, 1, meta)));
		}
	}

	public static boolean handleIndustrialWrenching(TileEntity tile, World world, int x, int y, int z, EntityPlayer player, Block block, int side) {
		IWrenchable wrenchable = (IWrenchable) tile;
		if (Keys.instance.isAltKeyDown(player)) {
			for (int step = 1; step < 6; step++) {
				if (player.isSneaking()) {
					side = (wrenchable.getFacing() + 6 - step) % 6;
				} else {
					side = (wrenchable.getFacing() + step) % 6;
				}
				if (wrenchable.wrenchCanSetFacing(player, side)) {
					break;
				}
			}
		} else if (player.isSneaking()) {
			side = BlockHelper.SIDE_OPPOSITE[side];
		}
		if (wrenchable.wrenchCanSetFacing(player, side)) {
			if (!world.isRemote) {
				wrenchable.setFacing((short) side);
			}
		} else if (wrenchable.wrenchCanRemove(player)) {
			ItemStack dropBlock = wrenchable.getWrenchDrop(player);

			if (dropBlock != null) {
				world.setBlockToAir(x, y, z);
				if (!world.isRemote) {
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
		return !world.isRemote;
	}
}
