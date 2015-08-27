package mortvana.legacy.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import mortvana.legacy.errored.core.common.FluxGearContent;
import org.lwjgl.opengl.GL11;

public class GuiWaslieHammer extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation("fluxgear", "textures/gui/guihammer.png");
	private final int guiWidth = 176;
	private final int guiHeight = 166;
	private int startX, startY;

	public GuiWaslieHammer(EntityPlayer inv) {
		super(new FluxGearContent.ContainerHammer(inv));
	}

	@Override
	public void initGui() {
		super.initGui();

		startX = (width - guiWidth) / 2;
		startY = (height - guiHeight) / 2;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);

		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(startX, startY, 0, 0, guiWidth, guiHeight);
	}

}
