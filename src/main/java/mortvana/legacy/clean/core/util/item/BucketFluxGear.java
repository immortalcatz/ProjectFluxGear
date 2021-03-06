package mortvana.legacy.clean.core.util.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import mortvana.legacy.clean.core.util.handlers.BucketHandler;
import mortvana.melteddashboard.item.FluxGearItem;

public class BucketFluxGear extends FluxGearItem {
	Item container;

	public BucketFluxGear() {
		this.container = Items.bucket;
		this.setMaxStackSize(1);
		this.setContainerItem(this.container);
	}

	public BucketFluxGear(String var1) {
		super(var1);
		this.container = Items.bucket;
		this.setMaxStackSize(1);
		this.setContainerItem(this.container);
	}

	public BucketFluxGear(Item var1) {
		this.container = Items.bucket;
		this.setMaxStackSize(1);
		this.container = var1;
		this.setContainerItem(var1);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(world, player, false);
		if(position != null && position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			int x = position.blockX;
			int y = position.blockY;
			int z = position.blockZ;
			switch(position.sideHit) {
				case 0:
					--y;
					break;
				case 1:
					++y;
					break;
				case 2:
					--z;
					break;
				case 3:
					++z;
					break;
				case 4:
					--x;
					break;
				case 5:
					++x;
			}

			if ((player.canPlayerEdit(x, y, z, position.sideHit, itemstack) && (world.isAirBlock(x, y, z) || !world.getBlock(x, y, z).getMaterial().isSolid())) && (BucketHandler.emptyBucket(world, x, y, z, itemstack) && !player.capabilities.isCreativeMode)) {
				return new ItemStack(container);
			}
		}
		return itemstack;
	}
}