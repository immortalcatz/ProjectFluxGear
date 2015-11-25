package mortvana.legacy.errored.morttweaks.entity;

import java.util.Calendar;
import java.util.List;

import mortvana.legacy.errored.morttweaks.common.MortTweaks;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.*;
//import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.ZombieEvent;

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
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!super.attackEntityFrom(source, amount)) {
			return false;
		} else {
			EntityLivingBase entity = getAttackTarget();

			if (entity == null && getEntityToAttack() instanceof EntityLivingBase) {
				entity = (EntityLivingBase) getEntityToAttack();
			}

			if (entity == null && source.getEntity() instanceof EntityLivingBase) {
				entity = (EntityLivingBase) source.getEntity();
			}

			/*if (MortTweaks.spawnZombieReinforcements && entity != null && worldObj.difficultySetting.ordinal() >= 3 && (double) rand.nextFloat() < getEntityAttribute(field_110186_bp).getAttributeValue()) {
				int i = MathHelper.floor_double(posX);
				int j = MathHelper.floor_double(posY);
				int k = MathHelper.floor_double(posZ);
				EntityZombie zombie = new EntityZombie(worldObj);

				for (int l = 0; l < 50; ++l) {
					int i1 = i + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
					int j1 = j + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
					int k1 = k + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);

					if (World.doesBlockHaveSolidTopSurface(worldObj, i1, j1 - 1, k1) && this.worldObj.getBlockLightValue(i1, j1, k1) < 10) {
						zombie.setPosition((double) i1, (double) j1, (double) k1);

						if (worldObj.checkNoEntityCollision(zombie.boundingBox) && worldObj.getCollidingBoundingBoxes(zombie, zombie.boundingBox).isEmpty() && !worldObj.isAnyLiquid(zombie.boundingBox)) {
							worldObj.spawnEntityInWorld(zombie);
							zombie.setAttackTarget(entity);
							zombie.onSpawnWithEgg((EntityLivingData) null);
							getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
							zombie.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
							break;
						}
					}
				}
			}*/

			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY);
			int k = MathHelper.floor_double(this.posZ);

			ZombieEvent.SummonAidEvent summonAid = ForgeEventFactory.fireZombieSummonAid(this, worldObj, i, j, k, entity, this.getEntityAttribute(field_110186_bp).getAttributeValue());

			if (summonAid.getResult() == Event.Result.DENY)
			{
				return true;
			}
			else if (summonAid.getResult() == Event.Result.ALLOW || entity != null && this.worldObj.difficultySetting == EnumDifficulty.HARD && (double)this.rand.nextFloat() < this.getEntityAttribute(field_110186_bp).getAttributeValue())
			{
				EntityZombie entityzombie;
				if (summonAid.customSummonedAid != null && summonAid.getResult() == Event.Result.ALLOW)
				{
					entityzombie = summonAid.customSummonedAid;
				}
				else
				{
					entityzombie = new EntityZombie(this.worldObj);
				}

				for (int l = 0; l < 50; ++l)
				{
					int i1 = i + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
					int j1 = j + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
					int k1 = k + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);

					if (World.doesBlockHaveSolidTopSurface(this.worldObj, i1, j1 - 1, k1) && this.worldObj.getBlockLightValue(i1, j1, k1) < 10)
					{
						entityzombie.setPosition((double)i1, (double)j1, (double)k1);

						if (this.worldObj.checkNoEntityCollision(entityzombie.boundingBox) && this.worldObj.getCollidingBoundingBoxes(entityzombie, entityzombie.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(entityzombie.boundingBox))
						{
							this.worldObj.spawnEntityInWorld(entityzombie);
							if (entity != null) entityzombie.setAttackTarget(entity);
							entityzombie.onSpawnWithEgg((IEntityLivingData)null);
							this.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
							entityzombie.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
							break;
						}
					}
				}
			}

			return true;
		}
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityData) {
		Object p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);
		float f = this.worldObj.getTensionFactorForBlock(this.posX, this.posY, this.posZ);
		this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);

		if (p_110161_1_1 == null)
		{
			p_110161_1_1 = new EntityZombie.GroupData(this.worldObj.rand.nextFloat() < ForgeModContainer.zombieBabyChance, this.worldObj.rand.nextFloat() < 0.05F, null);
		}

		if (p_110161_1_1 instanceof EntityZombie.GroupData)
		{
			EntityZombie.GroupData groupdata = (EntityZombie.GroupData)p_110161_1_1;

			if (groupdata.field_142046_b)
			{
				this.setVillager(true);
			}

			if (groupdata.field_142048_a)
			{
				this.setChild(true);

				if ((double)this.worldObj.rand.nextFloat() < 0.05D)
				{
					List list = this.worldObj.selectEntitiesWithinAABB(EntityChicken.class, this.boundingBox.expand(5.0D, 3.0D, 5.0D), IEntitySelector.field_152785_b);

					if (!list.isEmpty())
					{
						EntityChicken entitychicken = (EntityChicken)list.get(0);
						entitychicken.func_152117_i(true);
						this.mountEntity(entitychicken);
					}
				}
				else if ((double)this.worldObj.rand.nextFloat() < 0.05D)
				{
					EntityChicken entitychicken1 = new EntityChicken(this.worldObj);
					entitychicken1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
					entitychicken1.onSpawnWithEgg((IEntityLivingData)null);
					entitychicken1.func_152117_i(true);
					this.worldObj.spawnEntityInWorld(entitychicken1);
					this.mountEntity(entitychicken1);
				}
			}
		}

		this.func_146070_a(this.rand.nextFloat() < f * 0.1F);
		this.addRandomArmor();
		this.enchantEquipment();

		if (this.getEquipmentInSlot(4) == null)
		{
			Calendar calendar = this.worldObj.getCurrentDate();

			if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
			{
				this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
				this.equipmentDropChances[4] = 0.0F;
			}
		}

		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
		double d0 = this.rand.nextDouble() * 1.5D * (double)this.worldObj.getTensionFactorForBlock(this.posX, this.posY, this.posZ);

		if (d0 > 1.0D)
		{
			this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
		}

		if (this.rand.nextFloat() < f * 0.05F)
		{
			this.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
			this.func_146070_a(true);
		}

		return (IEntityLivingData)p_110161_1_1;

		/*this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
		Object par1EntityLivingData1 = entityData;//super.onSpawnWithEgg(par1EntityLivingData);
		float f = worldObj.getLocationTensionFactor(this.posX, this.posY, this.posZ);
		this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);

		if (par1EntityLivingData1 == null) {
			par1EntityLivingData1 = new ZombieGroupData(this, this.worldObj.rand.nextFloat() < 0.05F, this.worldObj.rand.nextFloat() < 0.05F, (EmptyZombieInner) null);
		}

		if (par1EntityLivingData1 instanceof ZombieGroupData) {
			ZombieGroupData entityzombiegroupdata = (ZombieGroupData) par1EntityLivingData1;

			if (entityzombiegroupdata.field_142046_b) {
				this.setVillager(true);
			}

			if (entityzombiegroupdata.field_142048_a) {
				this.setChild(true);
			}
		}

		this.addRandomArmor();
		this.enchantEquipment();

		if (getCurrentItemOrArmor(4) == null) {
			Calendar calendar = this.worldObj.getCurrentDate();

			if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
				this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
				this.equipmentDropChances[4] = 0.0F;
			}
		}

		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
		this.getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", this.rand.nextDouble() * 1.5D, 2));

		if (this.rand.nextFloat() < f * 0.05F) {
			if (MortTweaks.spawnZombieReinforcements)
				this.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
		}

		return (IEntityLivingData) par1EntityLivingData1;*/
	}

	class ZombieGroupData implements IEntityLivingData {
		public boolean field_142048_a;
		public boolean field_142046_b;

		/**/final EntityZombie field_142047_c;

		private ZombieGroupData(/**/EntityZombie par1EntityZombie, boolean par2, boolean par3) {
			/**/this.field_142047_c = par1EntityZombie;
			this.field_142048_a = false;
			this.field_142046_b = false;
			this.field_142048_a = par2;
			this.field_142046_b = par3;
		}

		ZombieGroupData(EntityZombie zombie, boolean par2, boolean par3, Object par4) {
			this(zombie, par2, par3);
		}
	}

	public boolean attackEntityAsMob(Entity entity) {
		boolean flag = super.attackEntityAsMob(entity);

		if (flag)
		{
			int i = this.worldObj.difficultySetting.getDifficultyId();

			if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float)i * 0.3F)
			{
				entity.setFire(2 * i);
			}
		}

		return flag;

		/*if (MortTweaks.disableZombieFire) {
			float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
			int i = 0;

			if (par1Entity instanceof EntityLivingBase) {
				f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) par1Entity);
				i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) par1Entity);
			}

			boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

			if (flag) {
				if (i > 0) {
					par1Entity.addVelocity((double) (-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F));
					this.motionX *= 0.6D;
					this.motionZ *= 0.6D;
				}

				int j = EnchantmentHelper.getFireAspectModifier(this);

				if (j > 0) {
					par1Entity.setFire(j * 4);
				}

				if (par1Entity instanceof EntityLivingBase) {
					EnchantmentThorns.func_92096_a(this, (EntityLivingBase) par1Entity, this.rand);
				}
			}

			return flag;
		} else
			return super.attackEntityAsMob(par1Entity);*/
	}
}
