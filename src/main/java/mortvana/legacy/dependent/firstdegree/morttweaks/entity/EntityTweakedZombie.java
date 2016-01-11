package mortvana.legacy.dependent.firstdegree.morttweaks.entity;

import java.util.Calendar;
import java.util.List;

import mortvana.legacy.errored.morttweaks.common.MortTweaks;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

public class EntityTweakedZombie extends EntityZombie {

	public EntityTweakedZombie(World world) {
		super(world);
	}

	@Override
	public void setChild(boolean flag) {
		if (MortTweaks.keepBabyZombies)
			super.setChild(flag);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
		if (MortTweaks.spawnZombieReinforcements) {
			getAttributeMap().registerAttribute(field_110186_bp).setBaseValue(rand.nextDouble() * 0.10000000149011612D);
		}
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityData) {
		getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random spawn bonus", rand.nextGaussian() * 0.05D, 1));
		Object entityLivingData = super.onSpawnWithEgg(entityData);
		float tension = worldObj.getTensionFactorForBlock(posX, posY, posZ);
		setCanPickUpLoot(rand.nextFloat() < 0.55F * tension);

		if (entityLivingData == null) {
			entityLivingData = new ZombieGroupData(worldObj.rand.nextFloat() < ForgeModContainer.zombieBabyChance, worldObj.rand.nextFloat() < 0.05F);
		}

		if (entityLivingData instanceof ZombieGroupData) {
			ZombieGroupData groupdata = (ZombieGroupData) entityLivingData;

			if (groupdata.villager) {
				setVillager(true);
			}

			if (groupdata.midget) {
				setChild(true);
				EntityChicken chicken;

				if ((double) worldObj.rand.nextFloat() < 0.05D) {
					List list = worldObj.selectEntitiesWithinAABB(EntityChicken.class, boundingBox.expand(5.0D, 3.0D, 5.0D), IEntitySelector.field_152785_b);

					if (!list.isEmpty()) {
						chicken = (EntityChicken)list.get(0);
						chicken.func_152117_i(true);
						mountEntity(chicken);
					}
				} else if ((double) worldObj.rand.nextFloat() < 0.05D) {
					chicken = new EntityChicken(worldObj);
					chicken.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
					chicken.onSpawnWithEgg(null);
					chicken.func_152117_i(true);
					worldObj.spawnEntityInWorld(chicken);
					mountEntity(chicken);
				}
			}
		}

		this.func_146070_a(this.rand.nextFloat() < tension * 0.1F);
		this.addRandomArmor();
		this.enchantEquipment();

		if (getEquipmentInSlot(4) == null) {
			Calendar calendar = worldObj.getCurrentDate();

			if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
				setCurrentItemOrArmor(4, new ItemStack(rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
				equipmentDropChances[4] = 0.0F;
			}
		}

		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
		double modTension = rand.nextDouble() * 1.5D * (double) tension;

		if (modTension > 1.0D) {
			getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", modTension, 2));
		}

		if (rand.nextFloat() < tension * 0.05F) {
			if (MortTweaks.spawnZombieReinforcements) {
				getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Leader zombie bonus", rand.nextDouble() * 0.25D + 0.5D, 0));
			}
			getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader zombie bonus", rand.nextDouble() * 3.0D + 1.0D, 2));
			func_146070_a(true);
		}

		return (IEntityLivingData) entityLivingData;
	}

	public boolean attackEntityAsMob(Entity entity) {
		if (MortTweaks.disableZombieFire) {
			float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
			int i = 0;

			if (entity instanceof EntityLivingBase) {
				f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) entity);
				i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) entity);
			}

			boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

			if (flag) {
				if (i > 0) {
					entity.addVelocity((double) (-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F));
					motionX *= 0.6D;
					motionZ *= 0.6D;
				}

				int j = EnchantmentHelper.getFireAspectModifier(this);

				if (j > 0) {
					entity.setFire(j * 4);
				}


				if (entity instanceof EntityLivingBase) {
					//Enchantment.thorns.func_151367_b(this, entity, EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.thorns.effectId, getInventory()));
					EnchantmentHelper.func_151384_a((EntityLivingBase) entity, this);
				}

				EnchantmentHelper.func_151385_b(this, entity);
			}

			return flag;
		} else {
			return super.attackEntityAsMob(entity);
		}
	}

	public class ZombieGroupData implements IEntityLivingData {
		public boolean midget;
		public boolean villager;

		public ZombieGroupData(boolean midget, boolean villager) {
			this.midget = midget;
			this.villager = villager;
		}

	}
}
