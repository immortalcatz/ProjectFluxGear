package mortvana.legacy.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.common.FluxGearAddons;

@SideOnly(Side.CLIENT)
public class EnergyTransferParticle extends EntityFX {
	private double targetX;
	private double targetY;
	private double targetZ;
	private boolean passive;

	public EnergyTransferParticle(World world, double x, double y, double z, double targetX, double targetY, double targetZ, boolean passive) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.passive = passive;
		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;
		this.field_70159_w = 0.0D;
		this.field_70181_x = 0.0D;
		this.field_70179_y = 0.0D;
		this.field_94054_b = 0;
		this.field_94055_c = 0;
		this.field_70544_f = world.rand.nextFloat() + 0.5F;
		this.field_70547_e = 100;
		this.field_70145_X = true;
		if(!passive) {
			this.field_70552_h = 0.0F;
			this.field_70553_i = 0.36862746F;
			this.field_70551_j = 0.98039216F;
		}

	}

	public void onUpdate() {
		if(this.field_70546_d > this.field_70547_e) {
			this.func_70106_y();
		}

		if(this.field_70546_d > this.field_70547_e || this.func_70092_e(this.targetX, this.targetY, this.targetZ) < 0.05D) {
			this.func_70106_y();
		}

		++this.field_70546_d;
		this.field_70159_w += (this.targetX - this.field_70165_t) * 0.0010000000474974513D;
		this.field_70181_x += (this.targetY - this.field_70163_u) * 0.0010000000474974513D;
		this.field_70179_y += (this.targetZ - this.field_70161_v) * 0.0010000000474974513D;
		this.field_70169_q = this.field_70165_t;
		this.field_70167_r = this.field_70163_u;
		this.field_70166_s = this.field_70161_v;
		this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
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

		float drawX = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)par2 - field_70556_an);
		float drawY = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)par2 - field_70554_ao);
		float drawZ = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)par2 - field_70555_ap);
		if(this.passive) {
			tessellator.setColorRGBA_F((float) this.func_70092_e(this.targetX, this.targetY, this.targetZ) * 5.0F * this.field_70552_h, (float) this.func_70092_e(this.targetX, this.targetY, this.targetZ) * 5.0F * this.field_70553_i, this.field_70551_j, this.field_82339_as);
		} else {
			tessellator.setColorRGBA_F(this.field_70552_h, this.field_70553_i, this.field_70551_j, (float) this.func_70092_e(this.targetX, this.targetY, this.targetZ) * 5.0F);
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