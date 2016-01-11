package mortvana.legacy.clean.crystalclimate.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EssenceFX extends EntityFX {

	public EssenceFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		float f = rand.nextFloat() * 0.1F + 0.2F;
		particleRed = f;
		particleGreen = f;
		particleBlue = f;
		setParticleTextureIndex(0);
		setSize(0.02F, 0.02F);
		particleScale *= rand.nextFloat() * 0.6F + 0.5F;
		motionX *= 0.02D;
		motionY *= 0.02D;
		motionZ *= 0.02D;
		motionX += par8;
		motionY += par10;
		motionZ += par12;
		particleMaxAge = (int) (20.0D / (Math.random() * 0.8D + 0.2D));
		noClip = true;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.95D;
		motionY *= 0.95D;
		motionZ *= 0.95D;
		//this.particleScale *= 1.01;

		if (particleMaxAge-- <= 0) {
			setDead();
		}
	}
}
