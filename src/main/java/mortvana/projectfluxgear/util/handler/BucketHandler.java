package mortvana.projectfluxgear.util.handler;

import java.util.Iterator;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.IFluidBlock;

import mortvana.projectfluxgear.util.helper.ServerHelper;
import mortvana.projectfluxgear.util.wrapper.BlockWrapper;
import mortvana.projectfluxgear.util.wrapper.ItemWrapper;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BucketHandler {
	public static BucketHandler instance = new BucketHandler();
	private static BiMap<BlockWrapper, ItemWrapper> buckets = HashBiMap.create();

	public static void initialize() {
	}

	private BucketHandler() {
		if(instance != null) {
			throw new IllegalArgumentException();
		} else {
			MinecraftForge.EVENT_BUS.register(this);
		}
	}

	@SubscribeEvent(
			priority = EventPriority.HIGHEST
	)
	public void onBucketFill(FillBucketEvent var1) {
		if(!(ServerHelper.isClientWorld(var1.world) | var1.result != null) && var1.getResult() == Event.Result.DEFAULT) {
			ItemStack var2 = var1.current;
			if(var1.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				boolean var3 = true;
				int var4 = var1.target.blockX;
				int var5 = var1.target.blockY;
				int var6 = var1.target.blockZ;
				int var7 = var1.target.sideHit;
				if(!var2.getItem().equals(Items.bucket)) {
					if(!FluidContainerRegistry.isBucket(var2)) {
						return;
					}

					ForgeDirection var8 = ForgeDirection.getOrientation(var7);
					Block var9 = var1.world.getBlock(var4, var5, var6);
					var4 += var8.offsetX;
					var5 += var8.offsetY;
					var6 += var8.offsetZ;
					if(!var9.isReplaceable(var1.world, var4, var5, var6) && var9.getMaterial().isSolid()) {
						var4 -= var8.offsetX;
						var5 -= var8.offsetY;
						var6 -= var8.offsetZ;
					}

					var3 = false;
				}

				if(var1.entityPlayer != null && (var3 && !var1.world.canMineBlock(var1.entityPlayer, var4, var5, var6) || !var1.entityPlayer.canPlayerEdit(var4, var5, var6, var7, var2))) {
					var1.setCanceled(true);
				} else {
					ItemStack var10 = null;
					if(var3) {
						var10 = fillBucket(var1.world, var4, var5, var6);
					} else if(emptyBucket(var1.world, var4, var5, var6, var2)) {
						var10 = new ItemStack(Items.bucket);
					}

					if(var10 != null) {
						var1.result = var10;
						var1.setResult(Event.Result.ALLOW);
					}
				}
			}
		}
	}

	public static boolean registerBucket(Block var0, int var1, ItemStack var2) {
		if(var0 != null && var1 >= 0 && var2 != null && !buckets.containsKey(new BlockWrapper(var0, var1))) {
			buckets.put(new BlockWrapper(var0, var1), new ItemWrapper(var2));
			return true;
		} else {
			return false;
		}
	}

	public static ItemStack fillBucket(World var0, int var1, int var2, int var3) {
		Block var4 = var0.getBlock(var1, var2, var3);
		int var5 = var0.getBlockMetadata(var1, var2, var3);
		if(!buckets.containsKey(new BlockWrapper(var4, var5))) {
			if(!var4.equals(Blocks.water) && !var4.equals(Blocks.flowing_water)) {
				if(!var4.equals(Blocks.lava) && !var4.equals(Blocks.flowing_lava)) {
					if(var4 instanceof IFluidBlock) {
						IFluidBlock var8 = (IFluidBlock)var4;
						if(var8.canDrain(var0, var1, var2, var3)) {
							ItemStack var7 = new ItemStack(Items.bucket);
							var7 = FluidContainerRegistry.fillFluidContainer(var8.drain(var0, var1, var2, var3, false), var7);
							if(var7 != null) {
								var8.drain(var0, var1, var2, var3, true);
								return var7;
							}
						}
					}

					return null;
				} else if(var0.getBlockMetadata(var1, var2, var3) == 0) {
					var0.setBlockToAir(var1, var2, var3);
					return new ItemStack(Items.lava_bucket);
				} else {
					return null;
				}
			} else if(var0.getBlockMetadata(var1, var2, var3) == 0) {
				var0.setBlockToAir(var1, var2, var3);
				return new ItemStack(Items.water_bucket);
			} else {
				return null;
			}
		} else if(!var0.setBlockToAir(var1, var2, var3)) {
			return null;
		} else {
			ItemWrapper var6 = (ItemWrapper)buckets.get(new BlockWrapper(var4, var5));
			return new ItemStack(var6.item, 1, var6.metadata);
		}
	}

	public static boolean emptyBucket(World var0, int var1, int var2, int var3, ItemStack var4) {
		boolean var5 = false;
		if(!buckets.inverse().containsKey(new ItemWrapper(var4))) {
			if(var4.getItem() instanceof ItemBucket) {
				var5 = ((ItemBucket)var4.getItem()).tryPlaceContainedLiquid(var0, var1, var2, var3);
				var0.markBlockForUpdate(var1, var2, var3);
			}

			return var5;
		} else {
			BlockWrapper var6 = (BlockWrapper)buckets.inverse().get(new ItemWrapper(var4));
			Material var7 = var0.getBlock(var1, var2, var3).getMaterial();
			boolean var8 = !var7.isSolid();
			if(var0.isAirBlock(var1, var2, var3) || var8) {
				if(!var0.isRemote && var8 && !var7.isLiquid()) {
					var0.func_147480_a(var1, var2, var3, true);
				}

				var5 = var0.setBlock(var1, var2, var3, var6.block, var6.metadata, 3);
				var0.markBlockForUpdate(var1, var2, var3);
			}

			return var5;
		}
	}

	public static void refreshMap() {
		HashBiMap var0 = HashBiMap.create(buckets.size());
		Iterator var1 = buckets.entrySet().iterator();

		while(var1.hasNext()) {
			Map.Entry var2 = (Map.Entry)var1.next();
			BlockWrapper var3 = new BlockWrapper(((BlockWrapper)var2.getKey()).block, ((BlockWrapper)var2.getKey()).metadata);
			ItemWrapper var4 = new ItemWrapper(((ItemWrapper)var2.getValue()).item, ((ItemWrapper)var2.getValue()).metadata);
			var0.put(var3, var4);
		}

		buckets.clear();
		buckets = var0;
	}
}
