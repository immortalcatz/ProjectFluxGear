package oldcode.legacy.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleHandler {

	public static Minecraft mc = Minecraft.getMinecraft();
	public static World theWorld;

	public ParticleHandler() {
	}

	public static EntityFX spawnParticle(String particleName, double x, double y, double z, double motionX, double motionY, double motionZ, float scale) {
		if(mc != null && mc.renderViewEntity != null && mc.effectRenderer != null) {
			int gameSettings = mc.gameSettings.particleSetting;
			if(gameSettings == 1 && theWorld.rand.nextInt(3) == 0) {
				gameSettings = 2;
			}

			double var15 = mc.renderViewEntity.posX - x;
			double var17 = mc.renderViewEntity.posY - y;
			double var19 = mc.renderViewEntity.posZ - z;
			ParticleDistortion particleDistortion = null;
			double var22 = 16.0D;
			if(var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22) {
				return null;
			} else if(gameSettings > 1) {
				return null;
			} else {
				if(particleName.equals("distortionParticle")) {
					particleDistortion = new ParticleDistortion(theWorld, x, y, z, (float)motionX, (float)motionY, (float)motionZ, scale);
				}

				mc.effectRenderer.addEffect(particleDistortion);
				return particleDistortion;
			}
		} else {
			return null;
		}
	}

	public static EntityFX spawnCustomParticle(EntityFX particle) {
		return spawnCustomParticle(particle, 256.0D);
	}

	public static EntityFX spawnCustomParticle(EntityFX particle, double vewRange) {
		if(mc != null && mc.renderViewEntity != null && mc.effectRenderer != null) {
			int var14 = mc.gameSettings.particleSetting;
			if(var14 == 1 && theWorld.rand.nextInt(3) == 0) {
				var14 = 2;
			}

			double var15 = mc.renderViewEntity.posX - particle.posX;
			double var17 = mc.renderViewEntity.posY - particle.posY;
			double var19 = mc.renderViewEntity.posZ - particle.posZ;
			if(var15 * var15 + var17 * var17 + var19 * var19 > vewRange * vewRange) {
				return null;
			} else if(var14 > 1) {
				return null;
			} else {
				mc.effectRenderer.addEffect(particle);
				return particle;
			}
		} else {
			return null;
		}
	}

	static {
		theWorld = mc.theWorld;
	}
}
