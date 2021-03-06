package mortvana.legacy.dependent.seconddegree.thaumicrevelations.entity;

import mortvana.legacy.dependent.firstdegree.thaumicrevelations.entity.EntityFleshProjectile;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFleshGolem extends EntityGolem implements IRangedAttackMob {

	public EntityFleshGolem(World world) {
		super(world);
		setSize(0.4F, 1.8F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
		tasks.addTask(2, new EntityAIWander(this, 1.0D));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	//TODO: Fix EntityFleshProjectile
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetentity, float par2) {
		final EntityFleshProjectile entitythrownflesh = new EntityFleshProjectile(worldObj, this);
		final double d0 = targetentity.posX - posX;
		final double d1 = (targetentity.posY + targetentity.getEyeHeight()) - 1.100000023841858D - entitythrownflesh.posY;
		final double d2 = targetentity.posZ - posZ;
		final float f1 = MathHelper.sqrt_double((d0 * d0) + (d2 * d2)) * 0.2F;
		entitythrownflesh.setThrowableHeading(d0, d1 + f1, d2, 1.6F, 12.0F);
		playSound("random.bow", 1.0F, 1.0F / ((getRNG().nextFloat() * 0.4F) + 0.8F));
		worldObj.spawnEntityInWorld(entitythrownflesh);
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	public boolean isAIEnabled() {
		return true;
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void onDeath(DamageSource par1DamageSource) {
		super.onDeath(par1DamageSource);
		doBreakParticleFX();
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public void doBreakParticleFX() {
		if (!worldObj.isRemote) return;
		final int i = 4;
		for (int j = 0; j < (i * 8); ++j) {
			final float f = rand.nextFloat() * (float) Math.PI * 2.0F;
			final float offset = (rand.nextFloat() * 0.2F) + 0.2F;
			final float xPos = MathHelper.sin(f) * i * 0.4F * offset;
			final float zPos = MathHelper.cos(f) * i * 0.4F * offset;
			worldObj.spawnParticle("flesh", posX + xPos, boundingBox.minY + 2, posZ + zPos, 0.0D, 0.0D, 0.0D);
		}
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return "mob.slime.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "mob.slime.death";
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(37.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D / 2);
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player.
	 * @param
	 * par2 - Level of Looting used to kill this mob.
	 */

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		dropItem(Items.rotten_flesh, 18);
	}
	/**
	 * Returns the item ID for the item the mob drops on death.
	 */

	@Override
	protected Item getDropItem() { return Items.rotten_flesh; }
}