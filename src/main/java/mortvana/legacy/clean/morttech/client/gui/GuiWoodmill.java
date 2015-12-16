package mortvana.legacy.clean.morttech.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import mortvana.legacy.clean.morttech.inventory.ContainerWoodmill;
import mortvana.legacy.clean.morttech.block.tile.WoodmillLogic;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiWoodmill extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("morttech:woodmill"/*"textures/gui/container/furnace.png"*/);
    private WoodmillLogic logic;

    public GuiWoodmill(InventoryPlayer inventory, WoodmillLogic woodmill) {
        super(new ContainerWoodmill(inventory, woodmill));
        logic = woodmill;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer (int par1, int par2) {
        String s = logic.isInvNameLocalized() ? logic.getInvName() : I18n.format(logic.getInvName());
        this.fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer (float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        int i1;

        if (logic.isWorking())  {
            i1 = logic.gaugePowerScaled(12)/*furnaceInventory.getBurnTimeRemainingScaled(12)*/;
            drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = logic.gaugeProgressScaled(24);
        drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
}
