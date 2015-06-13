package oldcode.projectfluxgear.util.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;

public class EnergyHelper {
	public final int RF_PER_MJ = 10;
	public final int RF_PER_EU = 4;

	private EnergyHelper() {
	}

	public static int extractEnergyFromHeldContainer(EntityPlayer var0, int var1, boolean var2) {
		ItemStack var3 = var0.getCurrentEquippedItem();
		return isEnergyContainerItem(var3)?((IEnergyContainerItem)var3.getItem()).extractEnergy(var3, var1, var2):0;
	}

	public static int insertEnergyIntoHeldContainer(EntityPlayer var0, int var1, boolean var2) {
		ItemStack var3 = var0.getCurrentEquippedItem();
		return isEnergyContainerItem(var3)?((IEnergyContainerItem)var3.getItem()).receiveEnergy(var3, var1, var2):0;
	}

	public static boolean isPlayerHoldingEnergyContainerItem(EntityPlayer var0) {
		return isEnergyContainerItem(var0.getCurrentEquippedItem());
	}

	public static boolean isEnergyContainerItem(ItemStack var0) {
		return var0 != null && var0.getItem() instanceof IEnergyContainerItem;
	}

	public static ItemStack setDefaultEnergyTag(ItemStack var0, int var1) {
		if(var0.stackTagCompound == null) {
			var0.setTagCompound(new NBTTagCompound());
		}

		var0.stackTagCompound.setInteger("Energy", var1);
		return var0;
	}

	public static int extractEnergyFromAdjacentEnergyHandler(TileEntity var0, int var1, int var2, boolean var3) {
		TileEntity var4 = BlockHelper.getAdjacentTileEntity(var0, var1);
		return var4 instanceof IEnergyHandler ?((IEnergyHandler)var4).extractEnergy(ForgeDirection.VALID_DIRECTIONS[var1 ^ 1], var2, var3):0;
	}

	public static int insertEnergyIntoAdjacentEnergyHandler(TileEntity var0, int var1, int var2, boolean var3) {
		TileEntity var4 = BlockHelper.getAdjacentTileEntity(var0, var1);
		return var4 instanceof IEnergyHandler?((IEnergyHandler)var4).receiveEnergy(ForgeDirection.VALID_DIRECTIONS[var1 ^ 1], var2, var3):0;
	}

	public static boolean isAdjacentEnergyHandlerFromSide(TileEntity var0, int var1) {
		TileEntity var2 = BlockHelper.getAdjacentTileEntity(var0, var1);
		return isEnergyHandlerFromSide(var2, ForgeDirection.VALID_DIRECTIONS[var1 ^ 1]);
	}

	public static boolean isEnergyHandlerFromSide(TileEntity var0, ForgeDirection var1) {
		return var0 instanceof IEnergyHandler?((IEnergyHandler)var0).canConnectEnergy(var1):false;
	}
}

