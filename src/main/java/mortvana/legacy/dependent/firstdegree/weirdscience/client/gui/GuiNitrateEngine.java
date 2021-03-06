package mortvana.legacy.dependent.firstdegree.weirdscience.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.util.helpers.StringHelper;

import mortvana.legacy.dependent.firstdegree.weirdscience.inventory.ContainerNitrateEngine;
import mortvana.legacy.errored.weirdscience.TileEntityNitrateDynamo;
import org.lwjgl.opengl.GL11;

@Deprecated
@SideOnly(Side.CLIENT)
public class GuiNitrateEngine extends GuiContainer {

    private static final ResourceLocation engineGuiTextures = new ResourceLocation("gui:assets.mortvana.fluxgearzee.textures/gui/genericmachinegui.png");

	public GuiNitrateEngine (InventoryPlayer inventoryPlayer, TileEntityNitrateDynamo tileEntity) {
		//Instantiate the container.
		super(new ContainerNitrateEngine(inventoryPlayer, tileEntity));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		//draw text and stuff here
		//the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Nitrate Engine", 8, 6, 4210752);
		//draws "Inventory" or your regional equivalent
		fontRendererObj.drawString(StringHelper.localize("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		//draw your Gui here, only thing you need to change is the path
		mc.getTextureManager().bindTexture(engineGuiTextures);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(engineGuiTextures);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
