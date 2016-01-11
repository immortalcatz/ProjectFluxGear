package mortvana.legacy.clean.crystalclimate.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LeafFX extends EntityFX {

	public LeafFX(World world, double par2, double par4, double par6, IIcon icon) {
		super(world, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		setParticleIcon(icon);
		particleRed = particleBlue = 0.5F;
		particleGreen = 0.7F;
		particleGravity = 0.1f;
		particleMaxAge = (int) (8.0F / (rand.nextFloat() * 0.9F + 0.1F));
		particleScale /= 1.5F;
	}

	public LeafFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, IIcon icon) {
		this(par1World, par2, par4, par6, icon);
		motionX += par8;
		motionY += par10;
		motionZ += par12;
	}

	public int getFXLayer() {
		return 2;
	}

	public void renderParticle(Tessellator tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
		float minU = particleIcon.getMinU();
		float maxU = particleIcon.getMaxU();
		float minV = particleIcon.getMinV();
		float maxV = particleIcon.getMaxV();
		float f10 = 0.1F * particleScale;

		float f11 = (float) (prevPosX + (posX - prevPosX) * (double) par2 - interpPosX);
		float f12 = (float) (prevPosY + (posY - prevPosY) * (double) par2 - interpPosY);
		float f13 = (float) (prevPosZ + (posZ - prevPosZ) * (double) par2 - interpPosZ);
		tessellator.setColorOpaque_F(particleRed, particleGreen, particleBlue);
		//x, y, z, u, v
		tessellator.addVertexWithUV((double) (f11 - par3 * f10 - par6 * f10), (double) (f12 - par4 * f10), (double) (f13 - par5 * f10 - par7 * f10), (double) minU, (double) maxV);
		tessellator.addVertexWithUV((double) (f11 - par3 * f10 + par6 * f10), (double) (f12 + par4 * f10), (double) (f13 - par5 * f10 + par7 * f10), (double) minU, (double) minV);
		tessellator.addVertexWithUV((double) (f11 + par3 * f10 + par6 * f10), (double) (f12 + par4 * f10), (double) (f13 + par5 * f10 + par7 * f10), (double) maxU, (double) minV);
		tessellator.addVertexWithUV((double) (f11 + par3 * f10 - par6 * f10), (double) (f12 - par4 * f10), (double) (f13 + par5 * f10 - par7 * f10), (double) maxU, (double) maxV);
	}
}
