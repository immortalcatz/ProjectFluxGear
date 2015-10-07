package mortvana.legacy.clean.wrenching;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.block.IDismantleable;

import Reika.RotaryCraft.API.Screwdriverable;
import Reika.RotaryCraft.API.ShaftMachine;
import ic2.api.tile.IWrenchable;
import main.flowstoneenergy.core.interfaces.IFlowWrenchable;
import mortvana.legacy.clean.core.util.helpers.BlockHelper;
import mortvana.melteddashboard.util.helpers.ServerHelper;
import pneumaticCraft.api.block.IPneumaticWrenchable;

public class NBTWrenchingHelper {
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
					if (ServerHelper.isServerWorld(world) && player.isSneaking() && block instanceof IDismantleable && ((IDismantleable) block).canDismantle(player, world, x, y, z)) {
						return WrenchingHelper.handleDismantling(world, x, y, z, player, block);
					}

					/* Crude RotaryCraft Screwdriver Support */
					if (nbt.hasKey("RotaryScrewdiver")) {
						if (tile instanceof ShaftMachine) {
							WrenchingHelper.handleShaftMachine(tile, world, x, y, z);
						}
						if (tile instanceof Screwdriverable) {
							return WrenchingHelper.handleScrewdriving(tile, world, x, y, z, player, hitSide);
						}
					}

					/* Flowstone Energy Compat */
					if (nbt.hasKey("FlowstoneEnergyWrench") && block instanceof IFlowWrenchable && player.isSneaking()) {
						WrenchingHelper.handleFlowWrenching(world, x, y, z, block, meta);
					}

					/* Industrial Craft 2 */
					if (nbt.hasKey("IndustrialCraftWrench") && tile instanceof IWrenchable) {
						return WrenchingHelper.handleIndustrialWrenching(tile, world, x, y, z, player, block, hitSide);
					}

					/* IPneumaticWrench */
					if (block instanceof IPneumaticWrenchable && ServerHelper.isServerWorld(world)) {
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
						return ServerHelper.isServerWorld(world);
					} else if (!player.isSneaking() && block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(hitSide))) {
						player.swingItem();
						return ServerHelper.isServerWorld(world);
					}
				}

				case 3: { //EnumWrenchMode.INFORMATION.ordinal()

				}
			}
		}


		return false;
	}
}
