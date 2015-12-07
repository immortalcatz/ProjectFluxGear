package mortvana.legacy.clean.fluxgearaddons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.LegacyHelper;

public class ParticleDistortion extends EntityFX {

	double originalX;
	double originalZ;

	public ParticleDistortion(World world, double x, double y, double z, float motionX, float motionY, float motionZ, float scale) {
		this(world, x, y, z, 1.0F, motionX, motionY, motionZ, scale);
	}

	public ParticleDistortion(World world, double x, double y, double z, float size, float motionX, float motionY, float motionZ, float scale) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.motionX = (double) motionX;
		this.motionY = (double) motionY;
		this.motionZ = (double) motionZ;
		this.originalX = (double) motionX;
		this.originalZ = (double) motionZ;
		if (motionX == 0.0F) {
			motionX = 1.0F;
		}

		particleTextureIndexX = 0;
		particleTextureIndexY = 0;
		particleRed = 0.7F;
		particleGreen = 0.8F;
		particleBlue= 1.0F;
		particleScale = scale;
		particleMaxAge = 40 + world.rand.nextInt(40);
		noClip = true;
	}

	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		if (particleAge++ >= particleMaxAge) {
			setDead();
		}

		moveEntity(motionX, motionY, motionZ);
		motionX *= (double) (1.0F - worldObj.rand.nextFloat() / 10.0F);
		motionY *= (double) (1.0F - worldObj.rand.nextFloat() / 10.0F);
		motionZ *= (double)(1.0F - worldObj.rand.nextFloat() / 10.0F);
		particleAlpha = (1.0F - (float) particleAge / (float) particleMaxAge) * 0.5F;
	}

	@SideOnly(Side.CLIENT)
	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
		tessellator.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(LegacyHelper.RESOURCE_PREFIX + "textures/particle/particles.png"));
		tessellator.startDrawingQuads();
		tessellator.setBrightness(200);
		float minU = 0.0F;
		float maxU = 0.1245F;
		float minV = 0.0F;
		float maxV = 0.1245F;
		float drawScale = 0.1F * particleScale;
		if (particleIcon != null) {
			minU = particleIcon.getMinU();
			maxU = particleIcon.getMaxU();
			minV = particleIcon.getMinV();
			maxV = particleIcon.getMaxV();
		}

		float drawX = (float) (prevPosX + (posX - prevPosX) * (double) par2 - interpPosX);
		float drawY = (float) (prevPosY + (posY - prevPosY) * (double) par2 - interpPosY);
		float drawZ = (float) (prevPosZ + (posZ - prevPosZ) * (double) par2 - interpPosZ);
		tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, particleAlpha);
		tessellator.setColorRGBA(0, 255, 255, (int) (particleAlpha * 255.0F));
		tessellator.addVertexWithUV((double) (drawX - par3 * drawScale - par6 * drawScale), (double) (drawY - par4 * drawScale), (double) (drawZ - par5 * drawScale - par7 * drawScale), (double) maxU, (double) maxV);
		tessellator.addVertexWithUV((double) (drawX - par3 * drawScale + par6 * drawScale), (double) (drawY + par4 * drawScale), (double) (drawZ - par5 * drawScale + par7 * drawScale), (double) maxU, (double) minV);
		tessellator.addVertexWithUV((double) (drawX + par3 * drawScale + par6 * drawScale), (double) (drawY + par4 * drawScale), (double) (drawZ + par5 * drawScale + par7 * drawScale), (double) minU, (double) minV);
		tessellator.addVertexWithUV((double) (drawX + par3 * drawScale - par6 * drawScale), (double) (drawY - par4 * drawScale), (double) (drawZ + par5 * drawScale - par7 * drawScale), (double) minU, (double) maxV);
		tessellator.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
		tessellator.startDrawingQuads();
	}
}
