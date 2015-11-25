package mortvana.melteddashboard.util.handlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
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

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onBucketFill(FillBucketEvent event) {}

	//public static ItemStack fillContainer() {
	//}

	public static boolean canEmptyContainer(World world, int x, int y, int z, int sideHit, ItemStack itemstack, EntityPlayer player, boolean canEmpty) {
		return canEmpty && player.canPlayerEdit(x, y, z, sideHit, itemstack) && (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).getMaterial().isSolid()) && !player.capabilities.isCreativeMode;
	}

	public static ItemStack emptyContainer(World world, int x, int y, int z, int sideHit, ItemStack itemstack, EntityPlayer player, boolean canEmpty) {
		if (containers.containsKey(itemstack) && canEmpty && (!world.getBlock(x, y, z).getMaterial().isSolid() || world.isAirBlock(x, y, z))) {
			if (!world.isRemote && !world.getBlock(x, y, z).getMaterial().isLiquid()) {
				world.breakBlock(x, y, z, true);
			}
			world.setBlock(x, y, z, containers.get(itemstack).getBlock(), containers.get(itemstack).getMetadata(), 3);
			world.markBlockForUpdate(x, y, z);
		}
		return canEmptyContainer(world, x, y, z, sideHit, itemstack, player, canEmpty) ? containers.get(itemstack).getEmptyContainer() : itemstack;
	}
}
