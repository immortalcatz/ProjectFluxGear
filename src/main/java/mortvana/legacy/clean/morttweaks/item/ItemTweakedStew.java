package mortvana.legacy.clean.morttweaks.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTweakedStew extends ItemSoup {
	public ItemTweakedStew(int amount, String unlocalizedName, String textureName) {
		super(amount);
		setUnlocalizedName(unlocalizedName);
		setTextureName(textureName);
	}

	public ItemTweakedStew() {
		this(6, "mushroomStew", "mushroom_stew");
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
		player.getFoodStats().addStats(this, stack);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		onFoodEaten(stack, world, player);

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
