package mortvana.legacy.client.render;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import mortvana.legacy.block.tileentity.TileEnergyPylon;
import org.lwjgl.opengl.GL11;

public class RenderTileEnergyPylon extends TileEntitySpecialRenderer {
	public static final ResourceLocation model_texture = new ResourceLocation("projectfluxgear", "textures/models/pylon_sphere_texture.png");
	public IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("projectfluxgear", "models/pylon_sphere.obj"));

	public RenderTileEnergyPylon() {
	}

	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeSinceLastTick) {
		if(tile != null && tile instanceof TileEnergyPylon) {
			TileEnergyPylon pylon = (TileEnergyPylon)tile;
			if(pylon.active) {
				float scale = pylon.modelScale + (timeSinceLastTick *= !pylon.reciveEnergy ? -0.01F : 0.01F);
				float rotation = pylon.modelRotation + timeSinceLastTick / 2.0F;
				GL11.glPushMatrix();
				GL11.glPushAttrib(1048575);
				GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
				if(pylon.getWorldObj().getBlockMetadata(pylon.xCoord, pylon.yCoord, pylon.zCoord) == 1) {
					GL11.glTranslated(0.0D, 1.0D, 0.0D);
				} else {
					GL11.glTranslated(0.0D, -1.0D, 0.0D);
				}

				GL11.glAlphaFunc(516, 0.1F);
				bindTexture(model_texture);
				GL11.glTexParameterf(3553, 10242, 10497.0F);
				GL11.glTexParameterf(3553, 10243, 10497.0F);
				GL11.glDisable(2896);
				GL11.glDisable(2884);
				GL11.glEnable(3042);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				GL11.glDepthMask(false);
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 200.0F, 200.0F);
				GL11.glPushMatrix();
				float scale1 = scale % 1.0F;
				GL11.glScalef(scale1, scale1, scale1);
				GL11.glRotatef(rotation * 0.5F, 0.0F, -1.0F, -0.5F);
				GL11.glColor4d(1.0D, 1.0D, 1.0D, (double)(1.0F - scale1));
				model.renderAll();
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				float scale2 = (scale + 0.25F) % 1.0F;
				GL11.glScalef(scale2, scale2, scale2);
				GL11.glRotatef(rotation * 0.5F, 0.0F, -1.0F, -0.5F);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F - scale2);
				model.renderAll();
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				float scale3 = (scale + 0.5F) % 1.0F;
				GL11.glScalef(scale3, scale3, scale3);
				GL11.glRotatef(rotation * 0.5F, 0.0F, -1.0F, -0.5F);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F - scale3);
				model.renderAll();
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				float scale4 = (scale + 0.75F) % 1.0F;
				GL11.glScalef(scale4, scale4, scale4);
				GL11.glRotatef(rotation * 0.5F, 0.0F, -1.0F, -0.5F);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F - scale4);
				model.renderAll();
				GL11.glPopMatrix();
				GL11.glEnable(2896);
				GL11.glEnable(3553);
				GL11.glDepthMask(true);
				GL11.glEnable(2896);
				GL11.glEnable(3553);
				GL11.glDisable(3042);
				GL11.glDepthMask(true);
				GL11.glPopAttrib();
				GL11.glPopMatrix();
			}
		}
	}
}
