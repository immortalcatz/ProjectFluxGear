package mortvana.legacy.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.common.FluxGearAddons;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EnergyBeamParticle extends EntityFX {
	private int direction = 0;
	private double masterX = 0.0D;
	private double masterZ = 0.0D;
	private float rotation = 0.0F;
	private boolean mirror = false;
	private double[] trailX = new double[15];
	private double[] trailY = new double[15];
	private double[] trailZ = new double[15];

	public EnergyBeamParticle(World world, double x, double y, double z, double x1, double z1, int direction, boolean mirror) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		float speed = 0.04F;
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		masterX = x1;
		masterZ = z1;
		this.direction = direction;
		this.mirror = mirror;
		switch(direction) {
			case 0:
				motionX = (double)speed;
				break;
			case 1:
				motionX = (double)(-speed);
				break;
			case 2:
				motionZ = (double)speed;
				break;
			case 3:
				motionZ = (double)(-speed);
		}

		particleTextureIndexX = 0;
		particleTextureIndexY = 0;
		particleScale = 1.0F;
		noClip = true;
		particleMaxAge = 300;
	}

	public void onUpdate() {
		if(particleAge > particleMaxAge || getDistanceSq(masterX, posY, masterZ) < 0.02D) {
			setDead();
		}

		if(getDistanceSq(masterX, posY, masterZ) < 0.1D) {
			motionX = (masterX - posX) * 0.10000000149011612D;
			motionY = (Math.floor(posY) + 0.5D - posY) * 0.10000000149011612D;
			motionZ = (masterZ - posZ) * 0.10000000149011612D;
			prevPosX = posX;
			prevPosY = posY;
			prevPosZ = posZ;
			moveEntity(motionX, motionY, motionZ);
		} else {
			this.trailX[0] = posX;
			this.trailY[0] = posY;
			this.trailZ[0] = posZ;

			for(int multiplier = 14; multiplier >= 0; --multiplier) {
				if(multiplier > 0) {
					trailX[multiplier] = this.trailX[multiplier - 1];
					trailY[multiplier] = this.trailY[multiplier - 1];
					trailZ[multiplier] = this.trailZ[multiplier - 1];
				}
			}

			float var5 = 0.27F;
			double masterY = Math.floor(posY) + 0.5D;
			if(this.direction != 0 && this.direction != 1) {
				posX = this.masterX + Math.sin((double)this.rotation) * (double)var5;
				posY = masterY + Math.cos((double)this.rotation) * (double)var5;
			} else {
				posZ = this.masterZ + Math.sin((double)this.rotation) * (double)var5;
				posY = masterY + Math.cos((double)this.rotation) * (double)var5;
			}

			if(this.mirror) {
				float modifier = 3.0F;
				if(this.direction != 0 && this.direction != 1) {
					posX = this.masterX + Math.sin((double)(this.rotation + modifier)) * (double)var5;
					posY = masterY + Math.cos((double)(this.rotation + modifier)) * (double)var5;
				} else {
					posZ = this.masterZ + Math.sin((double)(this.rotation + modifier)) * (double)var5;
					posY = masterY + Math.cos((double)(this.rotation + modifier)) * (double)var5;
				}
			}

			setPosition(posX, posY, posZ);
			prevPosX = posX;
			prevPosY = posY;
			prevPosZ = posZ;
			if(this.direction != 0 && this.direction != 1) {
				moveEntity(0.0D, 0.0D, motionZ);
			} else {
				moveEntity(motionX, 0.0D, 0.0D);
			}

			++this.particleAge;
			if(this.direction != 0 && this.direction != 3) {
				this.rotation = (float)((double)this.rotation + 0.15D);
			} else {
				this.rotation = (float)((double)this.rotation - 0.15D);
			}

		}
	}

	@SideOnly(Side.CLIENT)
	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
		tessellator.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(FluxGearAddons.RESOURCESPREFIX + "textures/particle/particles.png"));
		tessellator.startDrawingQuads();
		tessellator.setBrightness(200);
		float minU = 0.0F;
		float maxU = 0.1245F;
		float minV = 0.0F;
		float maxV = 0.1245F;
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
		tessellator.setColorRGBA_F(particleRed, particleGreen, particleBlue, particleAlpha);
		tessellator.addVertexWithUV((double)(drawX - par3 * drawScale - par6 * drawScale), (double)(drawY - par4 * drawScale), (double)(drawZ - par5 * drawScale - par7 * drawScale), (double)maxU, (double)maxV);
		tessellator.addVertexWithUV((double)(drawX - par3 * drawScale + par6 * drawScale), (double)(drawY + par4 * drawScale), (double)(drawZ - par5 * drawScale + par7 * drawScale), (double)maxU, (double)minV);
		tessellator.addVertexWithUV((double)(drawX + par3 * drawScale + par6 * drawScale), (double)(drawY + par4 * drawScale), (double)(drawZ + par5 * drawScale + par7 * drawScale), (double)minU, (double)minV);
		tessellator.addVertexWithUV((double)(drawX + par3 * drawScale - par6 * drawScale), (double)(drawY - par4 * drawScale), (double)(drawZ + par5 * drawScale - par7 * drawScale), (double)minU, (double)maxV);

		for(int i = 0; i <= 14; ++i) {
			GL11.glPushMatrix();
			drawX = (float)(trailX[i] + (trailX[i] - trailX[i]) * (double)par2 - interpPosX);
			drawY = (float)(trailY[i] + (trailY[i] - trailY[i]) * (double)par2 - interpPosY);
			drawZ = (float)(trailZ[i] + (trailZ[i] - trailZ[i]) * (double)par2 - interpPosZ);
			float scale = 0.1F * (1.0F - (float)i / 14.0F);
			float scale2 = 1.0F - (float)i / 14.0F;
			if(!this.mirror) {
				tessellator.setColorRGBA_F(1.0F, scale2, scale2, scale2);
			} else {
				tessellator.setColorRGBA_F(scale2, 1.0F, scale2, scale2);
			}

			tessellator.addVertexWithUV((double)(drawX - par3 * scale - par6 * scale), (double)(drawY - par4 * scale), (double)(drawZ - par5 * scale - par7 * scale), (double)maxU, (double)maxV);
			tessellator.addVertexWithUV((double)(drawX - par3 * scale + par6 * scale), (double)(drawY + par4 * scale), (double)(drawZ - par5 * scale + par7 * scale), (double)maxU, (double)minV);
			tessellator.addVertexWithUV((double)(drawX + par3 * scale + par6 * scale), (double)(drawY + par4 * scale), (double)(drawZ + par5 * scale + par7 * scale), (double)minU, (double)minV);
			tessellator.addVertexWithUV((double)(drawX + par3 * scale - par6 * scale), (double)(drawY - par4 * scale), (double)(drawZ + par5 * scale - par7 * scale), (double)minU, (double)maxV);
			GL11.glPopMatrix();
		}

		tessellator.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
		tessellator.startDrawingQuads();
	}
}