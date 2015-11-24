package mortvana.legacy.clean.morttweaks.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTweakedWitch extends EntityWitch {

	public EntityTweakedWitch(World world) {
		super(world);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float par2) {
		if (!this.getAggressive()) {
			ItemStack stack = new ItemStack(Items.potionitem, 1, 16384);
			double d0 = entity.posX + entity.motionX - this.posX;
			double d1 = entity.posY + (double) entity.getEyeHeight() - 1.100000023841858D - this.posY;
			double d2 = entity.posZ + entity.motionZ - this.posZ;
			float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

			if (f1 >= 8.0F && !entity.isPotionActive(Potion.moveSlowdown)) {
				stack.setMetadata(16392);

				PotionEffect pe = new PotionEffect(Potion.moveSlowdown.id, 20 * 20);
				NBTTagCompound potionTag = new NBTTagCompound();
				pe.writeCustomPotionEffectToNBT(potionTag);

				NBTTagCompound base = new NBTTagCompound();
				base.setTag("CustomPotionEffects", potionTag);
				stack.setTagCompound(base);
			} else if (entity.getHealth() >= 8.0F && !entity.isPotionActive(Potion.poison)) {
				stack.setMetadata(16388);

				PotionEffect pe = new PotionEffect(Potion.poison.id, 20 * 20, 10);
				NBTTagCompound potionTag = new NBTTagCompound();
				pe.writeCustomPotionEffectToNBT(potionTag);

				NBTTagCompound base = new NBTTagCompound();
				base.setTag("CustomPotionEffects", potionTag);
				stack.setTagCompound(base);
			} else if (f1 <= 3.0F && !entity.isPotionActive(Potion.weakness) && this.rand.nextFloat() < 0.25F) {
				stack.setMetadata(16392);

				PotionEffect pe = new PotionEffect(Potion.weakness.id, 20 * 20);
				NBTTagCompound potionTag = new NBTTagCompound();
				pe.writeCustomPotionEffectToNBT(potionTag);

				NBTTagCompound base = new NBTTagCompound();
				base.setTag("CustomPotionEffects", potionTag);
				stack.setTagCompound(base);
			}
			EntityPotion entitypotion = new EntityPotion(this.worldObj, this, stack);
			entitypotion.rotationPitch -= -20.0F;

			entitypotion.setThrowableHeading(d0, d1 + (double) (f1 * 0.2F), d2, 0.75F, 8.0F);
			this.worldObj.spawnEntityInWorld(entitypotion);
		}
	}
}
