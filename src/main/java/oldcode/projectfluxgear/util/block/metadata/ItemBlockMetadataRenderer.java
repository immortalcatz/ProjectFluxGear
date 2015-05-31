package oldcode.projectfluxgear.util.block.metadata;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemBlockMetadataRenderer implements IItemRenderer{

	public ItemBlockMetadataRenderer() {}

	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		if (type == ItemRenderType.INVENTORY) {
			return helper == ItemRendererHelper.INVENTORY_BLOCK;
		}
		if (type == ItemRenderType.ENTITY) {
			return helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION;
		}
		if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			return helper == ItemRendererHelper.EQUIPPED_BLOCK;
		}
		return false;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslated(0.5D, 0.5D, 0.5D);
		}
		((RenderBlocks) data[0]).renderBlockAsItem(Block.getBlockFromItem(item.getItem()), TileEntityMetadata.getItemMetadata(item), 1.0F);
		GL11.glPopMatrix();
	}
}
