package mortvana.legacy.dependent.firstdegree.thaumicrevelations.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import mortvana.legacy.clean.thaumicrevelations.block.tile.TileWardChest;
import mortvana.legacy.errored.thaumicrevelations.ClientTickHandler;
import org.lwjgl.opengl.GL11;

public class RenderTileWardChest extends TileEntitySpecialRenderer {
	 private static final ResourceLocation resourceOverlay = new ResourceLocation("fluxgear:textures/misc/wardChestOverlay.png");
	private static final ResourceLocation resourceModel = new ResourceLocation("fluxgear:textures/model/chestWard.png");
	ModelChest chestModel;

	public RenderTileWardChest() {
		chestModel = new ModelChest();
	}

	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float ticks) {
		int meta = tileentity.getWorld() == null ? 3 : tileentity.getBlockMetadata();
		int rotation = meta == 4 ? 90 : meta == 3 ? 0 : meta == 2 ? 180 : 270;

		float f = 0.0625F;

		GL11.glPushMatrix();
		GL11.glEnable(32826);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x, (float) y, (float) z);

		Minecraft.getMinecraft().renderEngine.bindTexture(resourceModel);
		GL11.glTranslatef(0.0F, 1.0F, 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		chestModel.chestBelow.render(f);
		chestModel.chestKnob.render(f);
		GL11.glDisable(32826);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		GL11.glTranslatef((float) x, (float) y, (float) z);
		renderOverlay((TileWardChest) tileentity);
		GL11.glTranslatef((float) -x, (float) -y, (float) -z);

		Minecraft.getMinecraft().renderEngine.bindTexture(resourceModel);
		GL11.glPushMatrix();
		GL11.glEnable(32826);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDepthMask(false);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x, (float) y, (float) z);

		Minecraft.getMinecraft().renderEngine.bindTexture(resourceModel);
		GL11.glTranslatef(0.0F, 1.0F, 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		chestModel.chestLid.render(f);
		GL11.glDisable(3042);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderOverlay(TileWardChest chest) {
		Minecraft.getMinecraft().renderEngine.bindTexture(resourceOverlay);
		GL11.glPushMatrix();
		GL11.glDisable(2896);
		GL11.glEnable(3042);

		GL11.glTranslatef(0.5F, 0.65F, 0.5F);
		float deg = (float) ((chest.getWorld() == null ? ClientTickHandler.elapsedTicks : chest.ticksExisted) % 360.0D);
		GL11.glRotatef(deg, 0.0F, 1.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(-0.45D, 0.0D, 0.45D, 0.0D, 1.0D);
		tess.addVertexWithUV(0.45D, 0.0D, 0.45D, 1.0D, 1.0D);
		tess.addVertexWithUV(0.45D, 0.0D, -0.45D, 1.0D, 0.0D);
		tess.addVertexWithUV(-0.45D, 0.0D, -0.45D, 0.0D, 0.0D);
		tess.draw();
		GL11.glEnable(2896);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}
}