package oldcode.legacy.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import oldcode.legacy.common.FluxGearAddons;

public class ParticleDistortion extends EntityFX {
	double originalX;
	double originalZ;

	public ParticleDistortion(World world, double x, double y, double z, float motionX, float motionY, float motionZ, float scale) {
		this(world, x, y, z, 1.0F, motionX, motionY, motionZ, scale);
	}

	public ParticleDistortion(World world, double x, double y, double z, float size, float motionX, float motionY, float motionZ, float scale) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.motionX = (double)motionX;
		this.motionY = (double)motionY;
		this.motionZ = (double)motionZ;
		this.originalX = (double)motionX;
		this.originalZ = (double)motionZ;
		if(motionX == 0.0F) {
			motionX = 1.0F;
		}

		field_94054_b = 0;
		field_94055_c = 0;
		field_70552_h = 0.7F;
		field_70553_i = 0.8F;
		field_70551_j = 1.0F;
		field_70544_f = scale;
		field_70547_e = 40 + world.rand.nextInt(40);
		field_70145_X = true;
	}

	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		if(this.field_70546_d++ >= this.field_70547_e) {
			setDead();
		}

		moveEntity(this.field_70159_w, this.field_70181_x, this.field_70179_y);
		this.field_70159_w *= (double)(1.0F - this.field_70170_p.rand.nextFloat() / 10.0F);
		this.field_70181_x *= (double)(1.0F - this.field_70170_p.rand.nextFloat() / 10.0F);
		this.field_70179_y *= (double)(1.0F - this.field_70170_p.rand.nextFloat() / 10.0F);
		this.field_82339_as = (1.0F - (float)this.field_70546_d / (float)this.field_70547_e) * 0.5F;
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
		float drawScale = 0.1F * this.field_70544_f;
		if(this.field_70550_a != null) {
			minU = this.field_70550_a.getMinU();
			maxU = this.field_70550_a.getMaxU();
			minV = this.field_70550_a.getMinV();
			maxV = this.field_70550_a.getMaxV();
		}

		float drawX = (float)(prevPosX + (posX - prevPosX) * (double)par2 - field_70556_an);
		float drawY = (float)(prevPosY + (posY - prevPosY) * (double)par2 - field_70554_ao);
		float drawZ = (float)(prevPosZ + (posZ - prevPosZ) * (double)par2 - field_70555_ap);
		tessellator.setColorRGBA_F(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
		tessellator.setColorRGBA(0, 255, 255, (int) (this.field_82339_as * 255.0F));
		tessellator.addVertexWithUV((double) (drawX - par3 * drawScale - par6 * drawScale), (double) (drawY - par4 * drawScale), (double) (drawZ - par5 * drawScale - par7 * drawScale), (double) maxU, (double) maxV);
		tessellator.addVertexWithUV((double) (drawX - par3 * drawScale + par6 * drawScale), (double) (drawY + par4 * drawScale), (double) (drawZ - par5 * drawScale + par7 * drawScale), (double) maxU, (double) minV);
		tessellator.addVertexWithUV((double) (drawX + par3 * drawScale + par6 * drawScale), (double) (drawY + par4 * drawScale), (double) (drawZ + par5 * drawScale + par7 * drawScale), (double) minU, (double) minV);
		tessellator.addVertexWithUV((double) (drawX + par3 * drawScale - par6 * drawScale), (double) (drawY - par4 * drawScale), (double) (drawZ + par5 * drawScale - par7 * drawScale), (double) minU, (double) maxV);
		tessellator.draw();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
		tessellator.startDrawingQuads();
	}
}
