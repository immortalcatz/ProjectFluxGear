package mortvana.legacy.clean.morttweaks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemTweakedFlesh extends ItemFood {

	public ItemTweakedFlesh(int amount, float saturation, boolean wolfFood, String unlocalizedName, String textureName) {
		this(amount, saturation, wolfFood);
		setUnlocalizedName(unlocalizedName);
		setTextureName(textureName);
	}

	public ItemTweakedFlesh(int amount, float saturation, boolean wolfFood) {
		super(amount, saturation, wolfFood);
	}

	public ItemTweakedFlesh() {
		this(1, 0.1F, true, "rottenFlesh", "rotten_flesh");
	}

	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			player.addPotionEffect(new PotionEffect(Potion.hunger.id, 30 * 20, 1));
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 15 * 20, 1));
			player.addPotionEffect(new PotionEffect(Potion.poison.id, 5 * 20, 1));
		}
	}
}
