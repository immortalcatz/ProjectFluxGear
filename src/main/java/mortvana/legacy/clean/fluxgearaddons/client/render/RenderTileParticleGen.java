package mortvana.legacy.clean.fluxgearaddons.client.render;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import mortvana.legacy.errored.fluxgearaddons.block.tileentity.TileEnergyStorageCore;
import mortvana.legacy.errored.fluxgearaddons.block.tileentity.TileParticleGenerator;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderTileParticleGen extends TileEntitySpecialRenderer {
	public final ResourceLocation texture = new ResourceLocation("DraconicEvolution".toLowerCase(), "textures/models/ParticleGenTextureSheet.png");
	public final ResourceLocation beamTexture = new ResourceLocation("DraconicEvolution".toLowerCase(), "textures/models/stabilizer_beam.png");
	public static final ResourceLocation modelTexture = new ResourceLocation("DraconicEvolution".toLowerCase(), "textures/models/stabilizer_sphere.png");
	public IModelCustom stabilizerSphereModel = AdvancedModelLoader.loadModel(new ResourceLocation("DraconicEvolution".toLowerCase(), "models/stabilizer_sphere.obj"));
	public float pxl = 0.015625F;

	public RenderTileParticleGen() {
	}

	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		TileParticleGenerator tileEntityGen = (TileParticleGenerator) tileEntity;
		renderBlock(tileEntityGen, f);
		GL11.glPopMatrix();
	}

	public void renderBlock(TileParticleGenerator tile, float f3) {
		Tessellator tessellator = Tessellator.instance;
		boolean inverted = tile.inverted;
		boolean stabilizerMode = tile.stabilizerMode;
		GL11.glPushMatrix();
		bindTexture(texture);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		tessellator.setColorRGBA(255, 255, 255, 255);
		float f = 0.4F;
		drawCornerCube(tessellator, f, f, f, 1.0F - f, inverted, stabilizerMode);
		drawCornerCube(tessellator, f, -f, f, 1.0F - f, inverted, stabilizerMode);
		drawCornerCube(tessellator, -f, f, -f, 1.0F - f, inverted, stabilizerMode);
		drawCornerCube(tessellator, -f, -f, -f, 1.0F - f, inverted, stabilizerMode);
		drawCornerCube(tessellator, -f, f, f, 1.0F - f, inverted, stabilizerMode);
		drawCornerCube(tessellator, f, f, -f, 1.0F - f, inverted, stabilizerMode);
		drawCornerCube(tessellator, f, -f, -f, 1.0F - f, inverted, stabilizerMode);
		drawCornerCube(tessellator, -f, -f, f, 1.0F - f, inverted, stabilizerMode);
		f = 0.45F;
		float f2 = 0.4F;
		drawBeamX(tessellator, 0.0F, f2, f2, 1.0F - f);
		drawBeamX(tessellator, 0.0F, -f2, f2, 1.0F - f);
		drawBeamX(tessellator, 0.0F, f2, -f2, 1.0F - f);
		drawBeamX(tessellator, 0.0F, -f2, -f2, 1.0F - f);
		drawBeamY(tessellator, f2, 0.0F, f2, 1.0F - f);
		drawBeamY(tessellator, -f2, 0.0F, f2, 1.0F - f);
		drawBeamY(tessellator, f2, 0.0F, -f2, 1.0F - f);
		drawBeamY(tessellator, -f2, 0.0F, -f2, 1.0F - f);
		drawBeamZ(tessellator, f2, f2, 0.0F, 1.0F - f);
		drawBeamZ(tessellator, -f2, f2, 0.0F, 1.0F - f);
		drawBeamZ(tessellator, f2, -f2, 0.0F, 1.0F - f);
		drawBeamZ(tessellator, -f2, -f2, 0.0F, 1.0F - f);
		tessellator.draw();
		GL11.glPopMatrix();
		if (stabilizerMode) {
			drawEnergyBeam(tessellator, tile, f3);
		}

	}

	public void drawEnergyBeam(Tessellator tess, TileParticleGenerator gen, float f) {
		TileEnergyStorageCore master = gen.getMaster();
		if (master != null) {
			float length = 0.0F;
			GL11.glPushMatrix();
			if (master.xCoord > gen.xCoord) {
				GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslated(-1.0D, 0.5D, 0.0D);
				length = (float) (master.xCoord - gen.xCoord) - 0.2F;
			} else if (master.xCoord < gen.xCoord) {
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslated(0.0D, -0.5D, 0.0D);
				length = (float) (gen.xCoord - master.xCoord) - 0.2F;
			} else if (master.zCoord > gen.zCoord) {
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslated(0.0D, 0.5D, -1.0D);
				length = (float) (master.zCoord - gen.zCoord) - 0.2F;
			} else if (master.zCoord < gen.zCoord) {
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslated(0.0D, -0.5D, 0.0D);
				length = (float) (gen.zCoord - master.zCoord) - 0.2F;
			}

			renderStabilizerSphere(gen);
			renderEnergyBeam(tess, gen, length, f);
			GL11.glPopMatrix();
		}
	}

	public void renderStabilizerSphere(TileParticleGenerator tile) {
		GL11.glPushMatrix();
		GL11.glColor4f(0.0F, 2.0F, 0.0F, 1.0F);
		GL11.glTranslated(0.5D, 0.0D, 0.5D);
		GL11.glScalef(0.4F, 0.4F, 0.4F);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 200.0F, 200.0F);
		GL11.glDisable(2896);
		GL11.glDisable(2884);
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(modelTexture);
		GL11.glRotatef(tile.rotation, 0.0F, 1.0F, 0.0F);
		stabilizerSphereModel.renderAll();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 200.0F, 200.0F);
		GL11.glRotatef(tile.rotation * 2.0F, 0.0F, -1.0F, 0.0F);
		GL11.glDisable(2896);
		GL11.glDepthMask(false);
		GL11.glColor4f(0.0F, 1.0F, 1.0F, 0.5F);
		GL11.glEnable(3042);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glScalef(1.3F, 1.3F, 1.3F);
		stabilizerSphereModel.renderAll();
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}

	public void renderEnergyBeam(Tessellator tess, TileParticleGenerator tile, float length, float f) {
		byte x = 0;
		byte y = 0;
		byte z = 0;
		GL11.glPushMatrix();
		GL11.glAlphaFunc(516, 0.1F);
		bindTexture(beamTexture);
		GL11.glTexParameterf(3553, 10242, 10497.0F);
		GL11.glTexParameterf(3553, 10243, 10497.0F);
		GL11.glDisable(2896);
		GL11.glDisable(2884);
		GL11.glDisable(3042);
		GL11.glDepthMask(true);
		OpenGlHelper.glBlendFunc(770, 1, 1, 0);
		float time = tile.rotation + f;
		float upMot = -time * 0.2F - (float) MathHelper.floor_float(-time * 0.1F);
		byte scaleMult = 1;
		double rotation = (double) time * 0.025D * (1.0D - (double) (scaleMult & 1) * 2.5D);
		tess.startDrawingQuads();
		tess.setColorRGBA(255, 255, 255, 32);
		double scale = (double) scaleMult * 0.2D;
		double d7 = 0.5D + Math.cos(rotation + 2.356194490192345D) * scale;
		double d9 = 0.5D + Math.sin(rotation + 2.356194490192345D) * scale;
		double d11 = 0.5D + Math.cos(rotation + 0.7853981633974483D) * scale;
		double d13 = 0.5D + Math.sin(rotation + 0.7853981633974483D) * scale;
		double d15 = 0.5D + Math.cos(rotation + 3.9269908169872414D) * scale;
		double d17 = 0.5D + Math.sin(rotation + 3.9269908169872414D) * scale;
		double d19 = 0.5D + Math.cos(rotation + 5.497787143782138D) * scale;
		double d21 = 0.5D + Math.sin(rotation + 5.497787143782138D) * scale;
		double height = (double) length;
		double texXMin = 0.0D;
		double texXMax = 1.0D;
		double d28 = (double) (-1.0F + upMot);
		double texHeight = (double) length * (0.5D / scale) + d28;
		tess.addVertexWithUV((double) x + d7, (double) y + height, (double) z + d9, texXMax, texHeight);
		tess.addVertexWithUV((double) x + d7, (double) y, (double) z + d9, texXMax, d28);
		tess.addVertexWithUV((double) x + d11, (double) y, (double) z + d13, texXMin, d28);
		tess.addVertexWithUV((double) x + d11, (double) y + height, (double) z + d13, texXMin, texHeight);
		tess.addVertexWithUV((double) x + d19, (double) y + height, (double) z + d21, texXMax, texHeight);
		tess.addVertexWithUV((double) x + d19, (double) y, (double) z + d21, texXMax, d28);
		tess.addVertexWithUV((double) x + d15, (double) y, (double) z + d17, texXMin, d28);
		tess.addVertexWithUV((double) x + d15, (double) y + height, (double) z + d17, texXMin, texHeight);
		tess.addVertexWithUV((double) x + d11, (double) y + height, (double) z + d13, texXMax, texHeight);
		tess.addVertexWithUV((double) x + d11, (double) y, (double) z + d13, texXMax, d28);
		tess.addVertexWithUV((double) x + d19, (double) y, (double) z + d21, texXMin, d28);
		tess.addVertexWithUV((double) x + d19, (double) y + height, (double) z + d21, texXMin, texHeight);
		tess.addVertexWithUV((double) x + d15, (double) y + height, (double) z + d17, texXMax, texHeight);
		tess.addVertexWithUV((double) x + d15, (double) y, (double) z + d17, texXMax, d28);
		tess.addVertexWithUV((double) x + d7, (double) y, (double) z + d9, texXMin, d28);
		tess.addVertexWithUV((double) x + d7, (double) y + height, (double) z + d9, texXMin, texHeight);
		rotation += 0.7699999809265137D;
		d7 = 0.5D + Math.cos(rotation + 2.356194490192345D) * scale;
		d9 = 0.5D + Math.sin(rotation + 2.356194490192345D) * scale;
		d11 = 0.5D + Math.cos(rotation + 0.7853981633974483D) * scale;
		d13 = 0.5D + Math.sin(rotation + 0.7853981633974483D) * scale;
		d15 = 0.5D + Math.cos(rotation + 3.9269908169872414D) * scale;
		d17 = 0.5D + Math.sin(rotation + 3.9269908169872414D) * scale;
		d19 = 0.5D + Math.cos(rotation + 5.497787143782138D) * scale;
		d21 = 0.5D + Math.sin(rotation + 5.497787143782138D) * scale;
		d28 = (double) (-1.0F + upMot * 1.0F);
		texHeight = (double) length * (0.5D / scale) + d28;
		tess.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
		tess.addVertexWithUV((double) x + d7, (double) y + height, (double) z + d9, texXMax, texHeight);
		tess.addVertexWithUV((double) x + d7, (double) y, (double) z + d9, texXMax, d28);
		tess.addVertexWithUV((double) x + d11, (double) y, (double) z + d13, texXMin, d28);
		tess.addVertexWithUV((double) x + d11, (double) y + height, (double) z + d13, texXMin, texHeight);
		tess.addVertexWithUV((double) x + d19, (double) y + height, (double) z + d21, texXMax, texHeight);
		tess.addVertexWithUV((double) x + d19, (double) y, (double) z + d21, texXMax, d28);
		tess.addVertexWithUV((double) x + d15, (double) y, (double) z + d17, texXMin, d28);
		tess.addVertexWithUV((double) x + d15, (double) y + height, (double) z + d17, texXMin, texHeight);
		tess.addVertexWithUV((double) x + d11, (double) y + height, (double) z + d13, texXMax, texHeight);
		tess.addVertexWithUV((double) x + d11, (double) y, (double) z + d13, texXMax, d28);
		tess.addVertexWithUV((double) x + d19, (double) y, (double) z + d21, texXMin, d28);
		tess.addVertexWithUV((double) x + d19, (double) y + height, (double) z + d21, texXMin, texHeight);
		tess.addVertexWithUV((double) x + d15, (double) y + height, (double) z + d17, texXMax, texHeight);
		tess.addVertexWithUV((double) x + d15, (double) y, (double) z + d17, texXMax, d28);
		tess.addVertexWithUV((double) x + d7, (double) y, (double) z + d9, texXMin, d28);
		tess.addVertexWithUV((double) x + d7, (double) y + height, (double) z + d9, texXMin, texHeight);
		tess.draw();
		GL11.glPushMatrix();
		GL11.glTranslated(0.0D, 0.4D, 0.0D);
		length -= 0.5F;
		GL11.glEnable(3042);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glDepthMask(false);
		tess.startDrawingQuads();
		tess.setColorRGBA(255, 255, 255, 32);
		double d30 = 0.2D;
		double d4 = 0.2D;
		double d6 = 0.8D;
		double d8 = 0.2D;
		double d10 = 0.2D;
		double d12 = 0.8D;
		double d14 = 0.8D;
		double d16 = 0.8D;
		double d18 = (double) length;
		double d20 = 0.0D;
		double d22 = 1.0D;
		double d24 = (double) (-1.0F + upMot);
		double d26 = (double) length + d24;
		tess.addVertexWithUV((double) x + d30, (double) y + d18, (double) z + d4, d22, d26);
		tess.addVertexWithUV((double) x + d30, (double) y, (double) z + d4, d22, d24);
		tess.addVertexWithUV((double) x + d6, (double) y, (double) z + d8, d20, d24);
		tess.addVertexWithUV((double) x + d6, (double) y + d18, (double) z + d8, d20, d26);
		tess.addVertexWithUV((double) x + d14, (double) y + d18, (double) z + d16, d22, d26);
		tess.addVertexWithUV((double) x + d14, (double) y, (double) z + d16, d22, d24);
		tess.addVertexWithUV((double) x + d10, (double) y, (double) z + d12, d20, d24);
		tess.addVertexWithUV((double) x + d10, (double) y + d18, (double) z + d12, d20, d26);
		tess.addVertexWithUV((double) x + d6, (double) y + d18, (double) z + d8, d22, d26);
		tess.addVertexWithUV((double) x + d6, (double) y, (double) z + d8, d22, d24);
		tess.addVertexWithUV((double) x + d14, (double) y, (double) z + d16, d20, d24);
		tess.addVertexWithUV((double) x + d14, (double) y + d18, (double) z + d16, d20, d26);
		tess.addVertexWithUV((double) x + d10, (double) y + d18, (double) z + d12, d22, d26);
		tess.addVertexWithUV((double) x + d10, (double) y, (double) z + d12, d22, d24);
		tess.addVertexWithUV((double) x + d30, (double) y, (double) z + d4, d20, d24);
		tess.addVertexWithUV((double) x + d30, (double) y + d18, (double) z + d4, d20, d26);
		tess.draw();
		GL11.glPopMatrix();
		GL11.glEnable(2896);
		GL11.glEnable(3553);
		GL11.glDepthMask(true);
		GL11.glEnable(2896);
		GL11.glEnable(3553);
		GL11.glPopMatrix();
	}

	public void drawCornerCube(Tessellator tess, float x, float y, float z, float FP, boolean inverted, boolean stabalizerMode) {
		float srcXMin = inverted ? 38.0F * this.pxl : 32.0F * this.pxl;
		float srcYMin = 0.0F;
		float srcXMax = inverted ? 44.0F * this.pxl : 38.0F * this.pxl;
		float srcYMax = 6.0F * this.pxl;
		float FN = 1.0F - FP;
		if (stabalizerMode) {
			srcXMin = 44.0F * this.pxl;
			srcXMax = 50.0F * this.pxl;
		}

		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) (FN + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) (FN + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) (FP + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) (FP + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) (FP + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) (FP + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) (FN + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) (FN + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) (FN + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) (FP + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) (FP + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) (FN + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) (FN + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) (FN + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) (FP + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) (FP + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) (FP + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) (FP + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) (FP + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) (FP + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) (FN + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) (FN + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) (FN + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) (FN + z), (double) srcXMin, (double) srcYMax);
	}

	public void drawBeamX(Tessellator tess, float x, float y, float z, float FP) {
		float srcXMin = 0.0F;
		float srcYMin = 0.0F;
		float srcXMax = 32.0F * this.pxl;
		float srcYMax = 4.0F * this.pxl;
		float FN = 1.0F - FP;
		float XX = 0.9F;
		float XM = 0.1F;
		tess.addVertexWithUV((double) XM, (double) (FP + y), (double) (FN + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) XM, (double) (FP + y), (double) (FP + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) XX, (double) (FP + y), (double) (FP + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) XX, (double) (FP + y), (double) (FN + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) XM, (double) (FN + y), (double) (FN + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) XX, (double) (FN + y), (double) (FN + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) XX, (double) (FN + y), (double) (FP + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) XM, (double) (FN + y), (double) (FP + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) XX, (double) (FN + y), (double) (FP + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) XX, (double) (FP + y), (double) (FP + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) XM, (double) (FP + y), (double) (FP + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) XM, (double) (FN + y), (double) (FP + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) XM, (double) (FN + y), (double) (FN + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) XM, (double) (FP + y), (double) (FN + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) XX, (double) (FP + y), (double) (FN + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) XX, (double) (FN + y), (double) (FN + z), (double) srcXMax, (double) srcYMax);
	}

	public void drawBeamY(Tessellator tess, float x, float y, float z, float FP) {
		float srcXMin = 0.0F;
		float srcYMin = 0.0F;
		float srcXMax = 32.0F * this.pxl;
		float srcYMax = 4.0F * this.pxl;
		float FN = 1.0F - FP;
		float XX = 0.9F;
		float XM = 0.1F;
		tess.addVertexWithUV((double) (FP + x), (double) XM, (double) (FN + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) XX, (double) (FN + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) XX, (double) (FP + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) XM, (double) (FP + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) XM, (double) (FP + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) XX, (double) (FP + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) XX, (double) (FN + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) XM, (double) (FN + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) XM, (double) (FP + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) XX, (double) (FP + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) XX, (double) (FP + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) XM, (double) (FP + z), (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) XM, (double) (FN + z), (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) XX, (double) (FN + z), (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) XX, (double) (FN + z), (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) XM, (double) (FN + z), (double) srcXMin, (double) srcYMax);
	}

	public void drawBeamZ(Tessellator tess, float x, float y, float z, float FP) {
		float srcXMin = 0.0F;
		float srcYMin = 0.0F;
		float srcXMax = 32.0F * this.pxl;
		float srcYMax = 4.0F * this.pxl;
		float FN = 1.0F - FP;
		float XX = 0.9F;
		float XM = 0.1F;
		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) XM, (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) XM, (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) XX, (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) XX, (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) XX, (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) XX, (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) XM, (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) XM, (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) XM, (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) (FP + y), (double) XX, (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) XX, (double) srcXMax, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) (FP + y), (double) XM, (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) XM, (double) srcXMin, (double) srcYMax);
		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) XM, (double) srcXMin, (double) srcYMin);
		tess.addVertexWithUV((double) (FP + x), (double) (FN + y), (double) XX, (double) srcXMax, (double) srcYMin);
		tess.addVertexWithUV((double) (FN + x), (double) (FN + y), (double) XX, (double) srcXMax, (double) srcYMax);
	}
}