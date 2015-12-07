package mortvana.projectfluxgear.thaumic.augments;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import thaumcraft.api.aspects.Aspect;

public class ThaumicAugmentTerra extends ThaumicAugmentBase {
	public ThaumicAugmentTerra(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {
		super.onTick(world, player, stack);
		player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 0, 5));
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);
		if (event.source.damageType.equals("fall")) {
			DamageSource damageSource = new EntityDamageSource("warden", event.entity);
			List entities = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(event.entity, AxisAlignedBB.getBoundingBox(event.entity.posX - 6, event.entity.posY - 6, event.entity.posZ - 6, event.entity.posX + 6, event.entity.posY + 6, event.entity.posZ + 6));
			for (Object entity : entities) {
				if (entity instanceof Entity) {
					((Entity) entity).attackEntityFrom(damageSource, 4);
				}
			}
		}
	}
}
