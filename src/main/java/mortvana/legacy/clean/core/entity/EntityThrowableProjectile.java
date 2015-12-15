package mortvana.legacy.clean.core.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.legacy.clean.core.client.ParticleRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrowableProjectile extends EntityThrowable {

    public static final Block[] BREAKABLE_DEFAULT = new Block[] { Blocks.glass, Blocks.glass_pane, Blocks.stained_glass, Blocks.stained_glass_pane, Blocks.glowstone };
    private int knockbackStrength = 1;
    private Block[] breakables = BREAKABLE_DEFAULT;

    public EntityThrowableProjectile(World world) {
        super(world);
    }

    public EntityThrowableProjectile(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityThrowableProjectile(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public void setKnockbackStrength(int strength) {
        knockbackStrength = strength;
    }

    public void setBreakableList(Block[] list) {
        breakables = list;
    }

    public boolean canBreakBlock(Block block) {
        if (breakables == null) {
            return false;
        }
        for (Block breakable : breakables) {
            if (breakable.equals(block)) {
                return true;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getParticleID() {
        return -1;
    }

    @SideOnly(Side.CLIENT)
    public void doBreakParticleFX() {
        int particle = getParticleID();
        if (particle == -1) return;
        final int i = 2;
        for (int j = 0; j < (i * 4); ++j) {
            final float f = rand.nextFloat() * (float) Math.PI * 2.0F;
            final float offset = (rand.nextFloat() * 0.1F) + 0.1F;
            final float xPos = MathHelper.sin(f) * i * 0.5F * offset;
            final float zPos = MathHelper.cos(f) * i * 0.5F * offset;
            ParticleRegistry.spawnParticle(particle, posX + xPos, boundingBox.minY, posZ + zPos, 0.0D, 0.0D, 0.0D);
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (mop.entityHit != null) {
            final int dmg = mop.entityHit instanceof EntityCreeper ? 4 : 1;
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), dmg);
            final float speed = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
            mop.entityHit.addVelocity((motionX * knockbackStrength * 0.6000000238418579D) / speed, 0.1D, (motionZ * knockbackStrength * 0.6000000238418579D) / speed);
        }
        if (worldObj.isRemote)
            doBreakParticleFX();
        if (canBreakBlock(worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ)))
            worldObj.breakBlock(mop.blockX, mop.blockY, mop.blockZ, true);
        if (!worldObj.isRemote)
            dropOnImpact();
        setDead();
    }

    protected void dropOnImpact() {}
}