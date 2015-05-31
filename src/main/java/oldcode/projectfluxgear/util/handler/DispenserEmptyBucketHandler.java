package oldcode.projectfluxgear.util.handler;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public final class DispenserEmptyBucketHandler extends BehaviorDefaultDispenseItem {
	private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();

	public DispenserEmptyBucketHandler() {
	}

	public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
		EnumFacing var3 = BlockDispenser.func_149937_b(var1.getBlockMetadata());
		World var4 = var1.getWorld();
		int var5 = var1.getXInt() + var3.getFrontOffsetX();
		int var6 = var1.getYInt() + var3.getFrontOffsetY();
		int var7 = var1.getZInt() + var3.getFrontOffsetZ();
		ItemStack var8 = BucketHandler.fillBucket(var4, var5, var6, var7);
		if(var8 == null) {
			return this.defaultDispenserItemBehavior.dispense(var1, var2);
		} else {
			if(--var2.stackSize == 0) {
				var2 = var8.copy();
			} else if(((TileEntityDispenser)var1.getBlockTileEntity()).func_146019_a(var8) < 0) {
				return this.defaultDispenserItemBehavior.dispense(var1, var2);
			}

			return var2;
		}
	}
}