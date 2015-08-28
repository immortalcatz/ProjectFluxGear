package mortvana.melteddashboard.util.handlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.fluids.Fluid;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.melteddashboard.util.data.FluidContainerData;

public class BucketHandler {
	public static TMap<ItemStack, FluidContainerData> containers = new THashMap<ItemStack, FluidContainerData>();


	public static boolean registerBucket(ItemStack container, ItemStack emptyContainer, Fluid fluid, Block block, int metadata) {
		FluidContainerData data = new FluidContainerData(container, emptyContainer, fluid, block, metadata);
		if (container != null && block != null && metadata >= 0 && !containers.containsKey(container))  {
			containers.put(container, data);
			return true;
		} else {
			return false;
		}
	}

	public static boolean canEmptyContainer(World world, int x, int y, int z, int sideHit, ItemStack itemstack, EntityPlayer player, boolean deny) {
		return deny && player.canPlayerEdit(x, y, z, sideHit, itemstack) && (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).getMaterial().isSolid()) && !player.capabilities.isCreativeMode;
	}

	public static ItemStack emptyContainer(World world, int x, int y, int z, int sideHit, ItemStack itemstack, EntityPlayer player, boolean deny) {
		

		return canEmptyContainer(world, x, y, z, sideHit, itemstack, player, deny) ? containers.get(itemstack).getEmptyContainer() : itemstack;
	}
}
