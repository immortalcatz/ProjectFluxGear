package mortvana.legacy.clean.fluxgearaddons.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.LegacyHelper;

@SideOnly(Side.CLIENT)
public class EnergyTransferParticle extends EntityFX {
	public double targetX;
	public double targetY;
	public double targetZ;
	public boolean passive;

	public EnergyTransferParticle(World world, double x, double y, double z, double targetX, double targetY, double targetZ, boolean passive) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.passive = passive;
		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		particleTextureIndexX = 0;
		particleTextureIndexY = 0;
		particleScale = world.rand.nextFloat() + 0.5F;
		particleMaxAge = 100;
		noClip = true;
		if (!passive) {
			particleRed = 0.0F;
			particleGreen = 0.36862746F;
			particleBlue = 0.98039216F;
		}

	}

	@Override
	public void onUpdate() {
		if (particleAge > particleMaxAge) {
			setDead();
		}

		if (particleAge > particleMaxAge || getDistanceSq(targetX, targetY, targetZ) < 0.05D) {
			setDead();
		}

		particleAge++;
		motionX += (targetX - posX) * 0.0010000000474974513D;
		motionY += (targetY - posY) * 0.0010000000474974513D;
		motionZ += (targetZ - posZ) * 0.0010000000474974513D;
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		moveEntity(motionX, motionY, motionZ);
	}

	@Override
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
		if (passive) {
			tessellator.setColorRGBA_F((float) getDistanceSq(targetX, targetY, targetZ) * 5.0F * particleRed, (float) getDistanceSq(targetX, targetY, targetZ) * 5.0F * particleGreen, particleBlue, particleAlpha);
		} else {
			tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, (float) getDistanceSq(targetX, targetY, targetZ) * 5.0F);
		}

		tessellator.addVertexWithUV((double) (drawX - par3 * drawScale - par6 * drawScale), (double) (drawY - par4 * drawScale), (double) (drawZ - par5 * drawScale - par7 * drawScale), (double) maxU, (double) maxV);
		tessellator.addVertexWithUV((double) (drawX - par3 * drawScale + par6 * drawScale), (double) (drawY + par4 * drawScale), (double) (drawZ - par5 * drawScale + par7 * drawScale), (double) maxU, (double) minV);
		tessellator.addVertexWithUV((double) (drawX + par3 * drawScale + par6 * drawScale), (double) (drawY + par4 * drawScale), (double) (drawZ + par5 * drawScale + par7 * drawScale), (double) minU, (double) minV);
		tessellator.addVertexWithUV((double) (drawX + par3 * drawScale - par6 * drawScale), (double) (drawY - par4 * drawScale), (double) (drawZ + par5 * drawScale - par7 * drawScale), (double) minU, (double) maxV);
		tessellator.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
		tessellator.startDrawingQuads();
	}
}