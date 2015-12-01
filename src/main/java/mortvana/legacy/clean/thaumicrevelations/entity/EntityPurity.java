package mortvana.legacy.clean.thaumicrevelations.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import mortvana.melteddashboard.intermod.thaumcraft.util.helpers.PurityHelper;

import thaumcraft.common.Thaumcraft;

public class EntityPurity extends EntityThrowable {

	public EntityPurity(World world) {
		super(world);
	}

	public EntityPurity(World world, EntityLivingBase entity) {
		super(world, entity);
	}

	public EntityPurity(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public float getGravityVelocity() {
		return 0.001F;
	}

	@Override
	public void onUpdate() {
		if (worldObj.isRemote) {
			for (int i = 0; i < 3; i++) {
				Thaumcraft.proxy.wispFX2(worldObj, posX + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F, posY + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F, posZ + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F, 0.3F, 2, true, false, 0.02F);

				double x2 = (posX + prevPosX) / 2.0D + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;
				double y2 = (posY + prevPosY) / 2.0D + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;
				double z2 = (posZ + prevPosZ) / 2.0D + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F;

				Thaumcraft.proxy.wispFX2(worldObj, x2, y2, z2, 0.3F, 2, true, false, 0.02F);
			}
		}
		super.onUpdate();
	}

	@Override
	public void onImpact(MovingObjectPosition mop) {
		for (int i = 0; i < 27; i++) {
			float fx = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F;
			float fy = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F;
			float fz = (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F;
			Thaumcraft.proxy.wispFX3(worldObj, posX + fx, posY + fy, posZ + fz, posX + fx * 8.0F, posY + fy * 8.0F, posZ + fz * 8.0F, 0.3F, 2, true, 0.02F);
		}

		if (!worldObj.isRemote) {
			PurityHelper.checkAndPurify(mop);
			setDead();
		}
	}
}