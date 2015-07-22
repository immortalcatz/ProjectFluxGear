package mortvana.melteddashboard.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.*;

public class EnergyHelper {
	public static final float RF_PER_EU = 4; //IdustrialCraft 3
	public static final float JOULES_PER_RF = 520; //RotaryCraft
	//public static final float RF_PER_MEKJOULE;
	//public static final float RF_PER_MILLIBAR;
	//public static final float RF_PER_MANA;

	private EnergyHelper() {
	}

	/* *=-=-=-=* IEnergyContainerItem Interaction (RF) *=-=-=-=* */

	public static int extractEnergyFromContainer(ItemStack container, int maxExtraction, boolean simulate) {
		return isEnergyContainerItem(container) ? ((IEnergyContainerItem) container.getItem()).extractEnergy(container, maxExtraction, simulate) : 0;
	}

	public static int insertEnergyIntoContainer(ItemStack container, int maxInsertion, boolean simulate) {
		return isEnergyContainerItem(container) ? ((IEnergyContainerItem) container.getItem()).receiveEnergy(container, maxInsertion, simulate) : 0;
	}

	public static int extractEnergyFromHeldContainer(EntityPlayer player, int maxExtraction, boolean simulate) {
		ItemStack container = player.getCurrentEquippedItem();
		return isEnergyContainerItem(container) ? ((IEnergyContainerItem) container.getItem()).extractEnergy(container, maxExtraction, simulate) : 0;
	}

	public static int insertEnergyIntoHeldContainer(EntityPlayer player, int maxInsertion, boolean simulate) {
		ItemStack container = player.getCurrentEquippedItem();
		return isEnergyContainerItem(container) ? ((IEnergyContainerItem) container.getItem()).receiveEnergy(container, maxInsertion, simulate) : 0;
	}

	public static boolean isPlayerHoldingContainerItem(EntityPlayer player) {
		return isEnergyContainerItem(player.getCurrentEquippedItem());
	}

	public static boolean isEnergyContainerItem(ItemStack container) {
		return container != null && container.getItem() instanceof IEnergyContainerItem;
	}

	public static ItemStack setDefaultEnergyTag(ItemStack container, int flux) {
		if (container.stackTagCompound == null) {
			container.setTagCompound(new NBTTagCompound());
		}

		container.stackTagCompound.setInteger("Energy", flux);
		return container;
	}

	/* *=-=-=-=* IEnergyHandler Interaction (RF) *=-=-=-=* */

	public static int extractEnergyFromAdjacentEnergyProvider(TileEntity tile, int side, int flux, boolean simulate) {
		TileEntity provider = WorldHelper.getAdjacentTileEntity(tile, side);
		return provider instanceof IEnergyProvider ? ((IEnergyProvider) provider).extractEnergy(ForgeDirection.VALID_DIRECTIONS[side], flux, simulate) : 0;
	}

	public static int insertEnergyIntoAdjacentEnergyReceiver(TileEntity tile, int side, int flux, boolean simulate) {
		TileEntity receiver = WorldHelper.getAdjacentTileEntity(tile, side);
		return receiver instanceof IEnergyReceiver ? ((IEnergyReceiver) receiver).receiveEnergy(ForgeDirection.VALID_DIRECTIONS[side], flux, simulate) : 0;
	}

	public static boolean isEnergyConnectionFromSide(TileEntity tile, ForgeDirection from) {
		return tile instanceof IEnergyConnection && ((IEnergyConnection) tile).canConnectEnergy(from);
	}

	public static boolean isEnergyProviderFromSide(TileEntity tile, ForgeDirection from) {
		return tile instanceof IEnergyProvider && ((IEnergyProvider) tile).canConnectEnergy(from);
	}

	public static boolean isEnergyReceiverFromSide(TileEntity tile, ForgeDirection from) {
		return tile instanceof IEnergyReceiver && ((IEnergyReceiver) tile).canConnectEnergy(from);
	}

	public static boolean isEnergyHandlerFromSide(TileEntity tile, ForgeDirection from) {
		return tile instanceof IEnergyHandler && ((IEnergyHandler) tile).canConnectEnergy(from);
	}

	public static boolean isAdjacentEnergyConnectionFromSide(TileEntity tile, int side) {
		TileEntity connection = WorldHelper.getAdjacentTileEntity(tile, side);
		return isEnergyConnectionFromSide(connection, ForgeDirection.VALID_DIRECTIONS[side]);
	}

	public static boolean isAdjacentEnergyProviderFromSide(TileEntity tile, int side) {
		TileEntity provider = WorldHelper.getAdjacentTileEntity(tile, side);
		return isEnergyProviderFromSide(provider, ForgeDirection.VALID_DIRECTIONS[side]);
	}

	public static boolean isAdjacentEnergyRecieverFromSide(TileEntity tile, int side) {
		TileEntity receiver = WorldHelper.getAdjacentTileEntity(tile, side);
		return isEnergyReceiverFromSide(receiver, ForgeDirection.VALID_DIRECTIONS[side]);
	}

	public static boolean isAdjacentEnergyHandlerFromSide(TileEntity tile, int side) {
		TileEntity handler = WorldHelper.getAdjacentTileEntity(tile, side);
		return isEnergyHandlerFromSide(handler, ForgeDirection.VALID_DIRECTIONS[side]);
	}
}
