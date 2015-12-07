package mortvana.legacy.dependent.seconddegree.fluxgearaddons.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile.TileEnergyStorageCore;
import org.lwjgl.opengl.GL11;

public class RenderTileEnergyStorageCore extends TileEntitySpecialRenderer {
	public static final ResourceLocation iner_model_texture = new ResourceLocation("projectfluxgear", "textures/models/power_sphere_layer_1.png");
	public static final ResourceLocation outer_model_texture = new ResourceLocation("projectfluxgear", "textures/models/power_sphere_layer_2.png");
	public IModelCustom iner_model = AdvancedModelLoader.loadModel(new ResourceLocation("projectfluxgear", "models/power_sphere_layer_1.obj"));
	public IModelCustom outer_model;

	public RenderTileEnergyStorageCore() {
	}

	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeSinceLastTick) {
		if (tile != null && tile instanceof TileEnergyStorageCore) {
			TileEnergyStorageCore core = (TileEnergyStorageCore)tile;
			if (core.isOnline()) {
				float scale = 0.0F;
				float rotation = core.modelRotation + timeSinceLastTick / 2.0F;
				switch (core.getTier()) {
					case 0:
						scale = 0.7F;
						break;
					case 1:
						scale = 1.2F;
						break;
					case 2:
						scale = 1.7F;
						break;
					case 3:
						scale = 2.5F;
						break;
					case 4:
						scale = 3.5F;
						break;
					case 5:
						scale = 4.5F;
						break;
					case 6:
						scale = 5.5F;
				}

				GL11.glPushMatrix();
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 150.0F, 150.0F);
				GL11.glDisable(2896);
				GL11.glDisable(2884);
				GL11.glTranslated(x + 0.5D, y + 0.5D, z + 0.5D);
				FMLClientHandler.instance().getClient().getTextureManager().bindTexture(iner_model_texture);
				double color = ((TileEnergyStorageCore) tile).getEnergyStored() / ((TileEnergyStorageCore) tile).getMaxEnergyStored();
				float brightness = (float)Math.abs(Math.sin((double)((float) Minecraft.getSystemTime() / 3000.0F)) * 100.0D);
				color = 1.0D - color;
				GL11.glScalef(scale, scale, scale);
				GL11.glPushMatrix();
				GL11.glRotatef(rotation, 0.0F, 1.0F, 0.5F);
				GL11.glColor4d(1.0D, color * 0.30000001192092896D, color * 0.699999988079071D, 1.0D);
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 80.0F + brightness, 80.0F + brightness);
				iner_model.renderAll();
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(0.8F, 0.8F, 0.8F);
				GL11.glColor4d(1.0D, 1.0D, 0.20000000298023224D, 1.0D);
				GL11.glRotatef(rotation, 0.0F, 1.0F, 0.5F);
				iner_model.renderAll();
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(0.9F, 0.9F, 0.9F);
				GL11.glColor4d(1.0D, 0.0D, 0.20000000298023224D, 1.0D);
				GL11.glRotatef(rotation, 0.0F, 1.0F, 0.5F);
				iner_model.renderAll();
				GL11.glPopMatrix();
				GL11.glScalef(1.1F, 1.1F, 1.1F);
				GL11.glDepthMask(false);
				FMLClientHandler.instance().getClient().getTextureManager().bindTexture(outer_model_texture);
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 200.0F, 200.0F);
				GL11.glBlendFunc(770, 771);
				GL11.glEnable(3042);
				GL11.glRotatef(rotation * 0.5F, 0.0F, -1.0F, -0.5F);
				GL11.glColor4f(0.5F, 2.0F, 2.0F, 0.7F);
				iner_model.renderAll();
				GL11.glDisable(3042);
				GL11.glDepthMask(true);
				GL11.glPopMatrix();
			}
		}
	}
}