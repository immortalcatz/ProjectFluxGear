package oldcode.projectfluxgear.util.helper;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

public class FluidHelper {
	public static final FluidStack WATER;
	public static final FluidStack LAVA;

	private FluidHelper() {
	}

	public static FluidStack extractFluidFromHeldContainer(EntityPlayer var0, int var1, boolean var2) {
		ItemStack var3 = var0.getCurrentEquippedItem();
		return isFluidContainerItem(var3)?((IFluidContainerItem)var3.getItem()).drain(var3, var1, var2):null;
	}

	public static int insertFluidIntoHeldContainer(EntityPlayer var0, FluidStack var1, boolean var2) {
		ItemStack var3 = var0.getCurrentEquippedItem();
		return isFluidContainerItem(var3)?((IFluidContainerItem)var3.getItem()).fill(var3, var1, var2):0;
	}

	public static boolean isPlayerHoldingFluidContainerItem(EntityPlayer var0) {
		return isFluidContainerItem(var0.getCurrentEquippedItem());
	}

	public static boolean isFluidContainerItem(ItemStack var0) {
		return var0 != null && var0.getItem() instanceof IFluidContainerItem;
	}

	public static FluidStack getFluidStackFromContainerItem(ItemStack var0) {
		return ((IFluidContainerItem)var0.getItem()).getFluid(var0);
	}

	public static ItemStack setDefaultFluidTag(ItemStack var0, FluidStack var1) {
		var0.setTagCompound(new NBTTagCompound());
		NBTTagCompound var2 = var1.writeToNBT(new NBTTagCompound());
		var0.stackTagCompound.setTag("Fluid", var2);
		return var0;
	}

	public static FluidStack extractFluidFromAdjacentFluidHandler(TileEntity var0, int var1, int var2, boolean var3) {
		TileEntity var4 = BlockHelper.getAdjacentTileEntity(var0, var1);
		return var4 instanceof IFluidHandler ?((IFluidHandler)var4).drain(ForgeDirection.VALID_DIRECTIONS[var1 ^ 1], var2, var3):null;
	}

	public static int insertFluidIntoAdjacentFluidHandler(TileEntity var0, int var1, FluidStack var2, boolean var3) {
		TileEntity var4 = BlockHelper.getAdjacentTileEntity(var0, var1);
		return var4 instanceof IFluidHandler?((IFluidHandler)var4).fill(ForgeDirection.VALID_DIRECTIONS[var1 ^ 1], var2, var3):0;
	}

	public static boolean isAdjacentFluidHandler(TileEntity var0, int var1) {
		return BlockHelper.getAdjacentTileEntity(var0, var1) instanceof IFluidHandler;
	}

	public static boolean isFluidHandler(TileEntity var0) {
		return var0 instanceof IFluidHandler;
	}

	public static boolean fillContainerFromHandler(World var0, IFluidHandler var1, EntityPlayer var2, FluidStack var3) {
		ItemStack var4 = var2.getCurrentEquippedItem();
		if(FluidContainerRegistry.isEmptyContainer(var4)) {
			ItemStack var5 = FluidContainerRegistry.fillFluidContainer(var3, var4);
			FluidStack var6 = FluidContainerRegistry.getFluidForFilledItem(var5);
			if(var6 != null && var5 != null) {
				if(!var2.capabilities.isCreativeMode) {
					if(var4.stackSize == 1) {
						var4 = var4.copy();
						var2.inventory.setInventorySlotContents(var2.inventory.currentItem, var5);
					} else if(!var2.inventory.addItemStackToInventory(var5)) {
						return false;
					}

					var1.drain(ForgeDirection.UNKNOWN, var6.amount, true);
					--var4.stackSize;
					if(var4.stackSize <= 0) {
						var4 = null;
					}
				} else {
					var1.drain(ForgeDirection.UNKNOWN, var6.amount, true);
				}

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean fillHandlerWithContainer(World var0, IFluidHandler var1, EntityPlayer var2) {
		ItemStack var3 = var2.getCurrentEquippedItem();
		FluidStack var4 = FluidContainerRegistry.getFluidForFilledItem(var3);
		if(var4 != null && (var1.fill(ForgeDirection.UNKNOWN, var4, false) == var4.amount || var2.capabilities.isCreativeMode)) {
			if(ServerHelper.isClientWorld(var0)) {
				return true;
			} else {
				var1.fill(ForgeDirection.UNKNOWN, var4, true);
				if(!var2.capabilities.isCreativeMode) {
					var2.inventory.setInventorySlotContents(var2.inventory.currentItem, ItemHelper.consumeItem(var3));
				}

				return true;
			}
		} else {
			return false;
		}
	}

	public static void writeFluidStackToPacket(FluidStack var0, DataOutput var1) throws IOException {
		if(!isValidFluidStack(var0)) {
			var1.writeShort(-1);
		} else {
			byte[] var2 = CompressedStreamTools.compress(var0.writeToNBT(new NBTTagCompound()));
			var1.writeShort((short)var2.length);
			var1.write(var2);
		}

	}

	public static FluidStack readFluidStackFromPacket(DataInput var0) throws IOException {
		short var1 = var0.readShort();
		if(var1 < 0) {
			return null;
		} else {
			byte[] var2 = new byte[var1];
			var0.readFully(var2);
			return FluidStack.loadFluidStackFromNBT(CompressedStreamTools.func_152457_a(var2, new NBTSizeTracker(2097152L)));
		}
	}

	public static boolean isValidFluidStack(FluidStack var0) {
		return var0 == null?false:(var0.fluidID == 0?false: FluidRegistry.getFluidName(var0) != null);
	}

	public static int getFluidLuminosity(FluidStack var0) {
		return var0 == null?0:getFluidLuminosity((Fluid)var0.getFluid());
	}

	public static int getFluidLuminosity(Fluid var0) {
		return var0 == null?0:var0.getLuminosity();
	}

	public static FluidStack getFluidFromWorld(World var0, int var1, int var2, int var3) {
		Block var4 = var0.getBlock(var1, var2, var3);
		int var5 = var0.getBlockMetadata(var1, var2, var3);
		if(Block.isEqualTo(var4, Blocks.water)) {
			return var5 == 0?WATER.copy():null;
		} else if(Block.isEqualTo(var4, Blocks.lava)) {
			return var5 == 0?LAVA.copy():null;
		} else if(var4 instanceof IFluidBlock) {
			IFluidBlock var6 = (IFluidBlock)var4;
			return var6.drain(var0, var1, var2, var3, true);
		} else {
			return null;
		}
	}

	public static Fluid lookupFluidForBlock(Block var0) {
		return var0 == Blocks.flowing_water?FluidRegistry.WATER:(var0 == Blocks.flowing_lava?FluidRegistry.LAVA:FluidRegistry.lookupFluidForBlock(var0));
	}

	public static FluidStack getFluidForFilledItem(ItemStack var0) {
		return var0 != null && var0.getItem() instanceof IFluidContainerItem?((IFluidContainerItem)var0.getItem()).getFluid(var0):FluidContainerRegistry.getFluidForFilledItem(var0);
	}

	public static boolean isFluidEqualOrNull(FluidStack var0, FluidStack var1) {
		return var0 == null || var1 == null || var0.isFluidEqual(var1);
	}

	public static boolean isFluidEqualOrNull(Fluid var0, FluidStack var1) {
		return var0 == null || var1 == null || var0 == var1.getFluid();
	}

	public static boolean isFluidEqualOrNull(Fluid var0, Fluid var1) {
		return var0 == null || var1 == null || var0 == var1;
	}

	public static boolean isFluidEqual(FluidStack var0, FluidStack var1) {
		return var0 != null && var0.isFluidEqual(var1);
	}

	public static boolean isFluidEqual(Fluid var0, FluidStack var1) {
		return var0 != null && var1 != null && var0 == var1.getFluid();
	}

	public static boolean isFluidEqual(Fluid var0, Fluid var1) {
		return var0 != null && var1 != null && var0.equals(var1);
	}

	static {
		WATER = new FluidStack(FluidRegistry.WATER, 1000);
		LAVA = new FluidStack(FluidRegistry.LAVA, 1000);
	}
}

