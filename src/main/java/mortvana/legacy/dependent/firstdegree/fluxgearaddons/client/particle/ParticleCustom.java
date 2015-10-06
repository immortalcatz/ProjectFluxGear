package mortvana.legacy.dependent.firstdegree.fluxgearaddons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.errored.core.common.ProjectFluxGear;

public class ParticleCustom extends EntityFX {
	public int red = 0;
	public int green = 0;
	public int blue = 0;
	public int maxAge = 0;
	public int fadeTime = 0;
	public int fadeLength = 0;
	public float gravity = 0.0F;

	public ParticleCustom(World world, double spawnX, double spawnY, double spawnZ, float spawnMotionX, float spawnMotionY, float spawnMotionZ, float scale, boolean canCollide, int index) {
		super(world, spawnX, spawnY, spawnZ, 0.0D, 0.0D, 0.0D);
		motionX = (double)spawnMotionX;
		motionY = (double)spawnMotionY;
		motionZ = (double)spawnMotionZ;
		particleTextureIndexX = index - 1;
		particleTextureIndexY = 0;
		particleScale = scale;
		noClip = !canCollide;
	}

	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		if(particleAge++ >= this.maxAge) {
			particleAlpha = (float)fadeTime / (float)fadeLength;
			if(fadeTime <= 0) {
				setDead();
			}

			--fadeTime;
		}

		moveEntity(motionX, motionY, motionZ);
		motionY -= (double)(this.gravity / 100.0F);
	}

	@SideOnly(Side.CLIENT)
	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
		tessellator.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ProjectFluxGear.RESOURCESPREFIX + "textures/particle/particles.png"));
		tessellator.startDrawingQuads();
		tessellator.setBrightness(200);
		float minU = (float)particleTextureIndexX / 8.0F;
		float maxU = minU + 0.124F;
		float minV = (float)particleTextureIndexY / 8.0F;
		float maxV = minV + 0.124F;
		float drawScale = 0.1F * particleScale;
		if(particleIcon != null) {
			minU = particleIcon.getMinU();
			maxU = particleIcon.getMaxU();
			minV = particleIcon.getMinV();
			maxV = particleIcon.getMaxV();
		}

		float drawX = (float)(prevPosX + (posX - prevPosX) * (double)par2 - interpPosX);
		float drawY = (float)(prevPosY + (posY - prevPosY) * (double)par2 - interpPosY);
		float drawZ = (float)(prevPosZ + (posZ - prevPosZ) * (double)par2 - interpPosZ);
		tessellator.setColorRGBA(red, green, blue, (int) (particleAlpha * 255.0F));
		tessellator.addVertexWithUV((double) (drawX - par3 * drawScale - par6 * drawScale), (double) (drawY - par4 * drawScale), (double) (drawZ - par5 * drawScale - par7 * drawScale), (double) maxU, (double) maxV);
		tessellator.addVertexWithUV((double) (drawX - par3 * drawScale + par6 * drawScale), (double) (drawY + par4 * drawScale), (double) (drawZ - par5 * drawScale + par7 * drawScale), (double) maxU, (double) minV);
		tessellator.addVertexWithUV((double) (drawX + par3 * drawScale + par6 * drawScale), (double) (drawY + par4 * drawScale), (double) (drawZ + par5 * drawScale + par7 * drawScale), (double) minU, (double) minV);
		tessellator.addVertexWithUV((double) (drawX + par3 * drawScale - par6 * drawScale), (double) (drawY - par4 * drawScale), (double) (drawZ + par5 * drawScale - par7 * drawScale), (double) minU, (double) maxV);
		tessellator.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
		tessellator.startDrawingQuads();
	}
}
