package mortvana.legacy.crystaltweaks.tweak;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TweakStew extends ItemSoup {
	public TweakStew(int id, int amount) {
		super(id, amount);
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		player.getFoodStats().addStats(this);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(stack, world, player);

		if (!player.capabilities.isCreativeMode) {
			--stack.stackSize;
			if (stack.stackSize <= 0) {
				if (!player.inventory.hasItem(Items.bowl))
					return new ItemStack(Items.bowl);
			}
			if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bowl))) {
				if (!player.worldObj.isRemote) {
					EntityItem entityitem = new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, stack);
					entityitem.delayBeforeCanPickup = 10;
					player.worldObj.spawnEntityInWorld(entityitem);
				}
			}
		}

		return stack;
	}
}
