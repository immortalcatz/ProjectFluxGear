package oldcode.projectfluxgear.util.handler;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public final class DispenserFilledBucketHandler extends BehaviorDefaultDispenseItem {
	private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();

	public DispenserFilledBucketHandler() {
	}

	public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
		EnumFacing var3 = BlockDispenser.func_149937_b(var1.getBlockMetadata());
		World var4 = var1.getWorld();
		int var5 = var1.getXInt() + var3.getFrontOffsetX();
		int var6 = var1.getYInt() + var3.getFrontOffsetY();
		int var7 = var1.getZInt() + var3.getFrontOffsetZ();
		return !var4.isAirBlock(var5, var6, var7) && var4.getBlock(var5, var6, var7).getMaterial().isSolid() ? var2:(BucketHandler.emptyBucket(var1.getWorld(), var5, var6, var7, var2)?new ItemStack(Items.bucket) : this.defaultDispenserItemBehavior.dispense(var1, var2));
	}
}
