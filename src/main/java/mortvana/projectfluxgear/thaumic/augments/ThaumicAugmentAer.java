package mortvana.projectfluxgear.thaumic.augments;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import mortvana.melteddashboard.util.enums.EnumAugmentHolder;
import thaumcraft.api.aspects.Aspect;

public class ThaumicAugmentAer extends ThaumicAugmentBase {
	public ThaumicAugmentAer(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack itemstack) {
		EnumAugmentHolder holder = super.getHolder(itemstack);

		/*switch (holder) {
			case HELMET:
			case CHESTPLATE:
			case PANTS:
			case BOOTS:*/
				super.onTick(world, player, itemstack);
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 0, 5));
				if (player.fallDistance > 5) {
					player.fallDistance = 5;
				}

			/*default:
				break;
		}*/
	}
}
