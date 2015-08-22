/*   1:    */
package mortvana.legacy.crystaltweaks.client;
/*   2:    */ 
/*   3:    */

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import mortvana.legacy.crystaltweaks.block.logic.TileWardChest;
import org.lwjgl.opengl.GL11;
import vazkii.tinkerer.client.core.handler.ClientTickHandler;

/*   4:    */
/*   5:    */
/*   6:    */
/*   7:    */
/*   8:    */
/*   9:    */
/*  10:    */
/*  11:    */
/*  12:    */
/*  13:    */

/*  14:    */
/*  15:    */ public class RenderTileWardChest
/*  16:    */ extends TileEntitySpecialRenderer
/*  17:    */ {
	/*  18: 33 */   private static final ResourceLocation resourceOverlay = new ResourceLocation("ttinkerer:textures/misc/wardChestOverlay.png");
	/*  19: 34 */   private static final ResourceLocation resourceModel = new ResourceLocation("ttinkerer:textures/model/chestWard.png");
	/*  20:    */ ModelChest chestModel;

	/*  21:    */
/*  22:    */
	public RenderTileWardChest()
/*  23:    */ {
/*  24: 39 */
		this.chestModel = new ModelChest();
/*  25:    */
	}

	/*  26:    */
/*  27:    */
	public void func_76894_a(TileEntity tileentity, double x, double y, double z, float ticks)
/*  28:    */ {
/*  29: 44 */
		int meta = tileentity.field_70331_k == null ? 3 : tileentity.func_70322_n();
/*  30: 45 */
		int rotation = meta == 4 ? 90 : meta == 3 ? 0 : meta == 2 ? 180 : 270;
/*  31:    */     
/*  32: 47 */
		float f = 0.0625F;
/*  33:    */     
/*  34: 49 */
		GL11.glPushMatrix();
/*  35: 50 */
		GL11.glEnable(32826);
/*  36: 51 */
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  37: 52 */
		GL11.glTranslatef((float) x, (float) y, (float) z);
/*  38:    */     
/*  39: 54 */
		Minecraft.func_71410_x().field_71446_o.func_110577_a(resourceModel);
/*  40: 55 */
		GL11.glTranslatef(0.0F, 1.0F, 1.0F);
/*  41: 56 */
		GL11.glScalef(1.0F, -1.0F, -1.0F);
/*  42: 57 */
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*  43: 58 */
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*  44: 59 */
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  45: 60 */
		this.chestModel.field_78232_b.func_78785_a(f);
/*  46: 61 */
		this.chestModel.field_78233_c.func_78785_a(f);
/*  47: 62 */
		GL11.glDisable(32826);
/*  48: 63 */
		GL11.glPopMatrix();
/*  49: 64 */
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  50:    */     
/*  51: 66 */
		GL11.glTranslatef((float) x, (float) y, (float) z);
/*  52: 67 */
		renderOverlay((TileWardChest) tileentity);
/*  53: 68 */
		GL11.glTranslatef((float) -x, (float) -y, (float) -z);
/*  54:    */     
/*  55: 70 */
		Minecraft.func_71410_x().field_71446_o.func_110577_a(resourceModel);
/*  56: 71 */
		GL11.glPushMatrix();
/*  57: 72 */
		GL11.glEnable(32826);
/*  58: 73 */
		GL11.glEnable(3042);
/*  59: 74 */
		GL11.glBlendFunc(770, 771);
/*  60: 75 */
		GL11.glDepthMask(false);
/*  61: 76 */
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  62: 77 */
		GL11.glTranslatef((float) x, (float) y, (float) z);
/*  63:    */     
/*  64: 79 */
		Minecraft.func_71410_x().field_71446_o.func_110577_a(resourceModel);
/*  65: 80 */
		GL11.glTranslatef(0.0F, 1.0F, 1.0F);
/*  66: 81 */
		GL11.glScalef(1.0F, -1.0F, -1.0F);
/*  67: 82 */
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*  68: 83 */
		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
/*  69: 84 */
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  70: 85 */
		this.chestModel.field_78234_a.func_78785_a(f);
/*  71: 86 */
		GL11.glDisable(3042);
/*  72: 87 */
		GL11.glDepthMask(true);
/*  73: 88 */
		GL11.glPopMatrix();
/*  74: 89 */
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  75:    */
	}

	/*  76:    */
/*  77:    */
	private void renderOverlay(TileWardChest chest)
/*  78:    */ {
/*  79: 93 */
		Minecraft mc = Minecraft.func_71410_x();
/*  80: 94 */
		mc.field_71446_o.func_110577_a(resourceOverlay);
/*  81: 95 */
		GL11.glPushMatrix();
/*  82: 96 */
		GL11.glDisable(2896);
/*  83: 97 */
		GL11.glEnable(3042);
/*  84:    */     
/*  85: 99 */
		GL11.glTranslatef(0.5F, 0.65F, 0.5F);
/*  86:100 */
		float deg = (float) ((chest.field_70331_k == null ? ClientTickHandler.elapsedTicks : chest.ticksExisted) % 360.0D);
/*  87:101 */
		GL11.glRotatef(deg, 0.0F, 1.0F, 0.0F);
/*  88:102 */
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  89:103 */
		Tessellator tess = Tessellator.field_78398_a;
/*  90:104 */
		tess.func_78382_b();
/*  91:105 */
		tess.func_78374_a(-0.45D, 0.0D, 0.45D, 0.0D, 1.0D);
/*  92:106 */
		tess.func_78374_a(0.45D, 0.0D, 0.45D, 1.0D, 1.0D);
/*  93:107 */
		tess.func_78374_a(0.45D, 0.0D, -0.45D, 1.0D, 0.0D);
/*  94:108 */
		tess.func_78374_a(-0.45D, 0.0D, -0.45D, 0.0D, 0.0D);
/*  95:109 */
		tess.func_78381_a();
/*  96:110 */
		GL11.glEnable(2896);
/*  97:111 */
		GL11.glDisable(3042);
/*  98:112 */
		GL11.glPopMatrix();
/*  99:    */
	}
/* 100:    */
}



/* Location:           C:\Users\nyehc_000\Desktop\Coding\Tools\Thaumic Enthalpy-deobf.jar

 * Qualified Name:     mortvana.legacy.crystaltweaks.client.RenderTileWardChest

 * JD-Core Version:    0.7.0.1

 */