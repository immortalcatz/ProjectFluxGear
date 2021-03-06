package mortvana.projectfluxgear.thaumic.augments;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import thaumcraft.api.aspects.Aspect;

public class ThaumicAugmentAqua extends ThaumicAugmentBase {
	public ThaumicAugmentAqua(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {
		super.onTick(world, player, stack);
		if (player.isInWater()) {
			player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 0, 5));
		}
	}
}
