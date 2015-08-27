package mortvana.legacy.refactored.crystalclimate.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CrystalBlockRender implements ISimpleBlockRenderingHandler {

	public static int model = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if (modelID == model) {
			renderer.setRenderBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.125F, 0.625F);
			CrystalProxyClient.renderStandardInvBlock(renderer, block, metadata);
			renderer.setRenderBounds(0.3125F, 0.125F, 0.3125F, 0.6875F, 0.25F, 0.6875F);
			CrystalProxyClient.renderStandardInvBlock(renderer, block, metadata);
			renderer.setRenderBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.75F, 0.75F);
			CrystalProxyClient.renderStandardInvBlock(renderer, block, metadata);
			renderer.setRenderBounds(0.3125F, 0.75F, 0.3125F, 0.6875F, 0.875F, 0.6875F);
			CrystalProxyClient.renderStandardInvBlock(renderer, block, metadata);
			renderer.setRenderBounds(0.375F, 0.875F, 0.375F, 0.625F, 1.0F, 0.625F);
			CrystalProxyClient.renderStandardInvBlock(renderer, block, metadata);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) {
		if (modelID == model) {
			int metadata = world.getBlockMetadata(x, y, z);

			if (metadata < 3) { //Bits
				renderer.setRenderBounds(0.0625F, 0.0F, 0.0625F, 0.25F, 0.0625F, 0.375F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.5F, 0.0F, 0.75F, 0.625F, 0.125F, 0.8125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.5F, 0.0F, 0.8125F, 0.5625F, 0.0625F, 0.875F);
				renderer.renderStandardBlock(block, x, y, z);
			}

			//Base
			if (metadata == 0) {
				renderer.setRenderBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.125F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 1) {
				renderer.setRenderBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.25, 0.6875F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375F, 0.25, 0.375F, 0.625F, 0.375, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.1875, 0.0F, 0.5F, 0.3125F, 0.1875, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.0F, 0.1875, 0.625, 0.125, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625F, 0.0F, 0.5F, 0.8125f, 0.125, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 2) {
				renderer.setRenderBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.5F, 0.6875F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375F, 0.5F, 0.375F, 0.625F, 0.625F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.125F, 0.0F, 0.5F, 0.3125F, 0.25F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.0F, 0.125F, 0.6875F, 0.375F, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625F, 0.0F, 0.5F, 0.8125f, 0.375F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 3) {
				renderer.setRenderBounds(0.3125F, 0.75F, 0.3125F, 0.6875F, 0.875F, 0.6875F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375F, 0.875F, 0.375F, 0.625F, 0.9325F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.75F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.125F, 0.0F, 0.5F, 0.375F, 0.375, 0.875F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.0F, 0.0625, 0.625, 0.5, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625F, 0.0F, 0.5F, 0.875F, 0.625, 0.875F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 4) { //Transition
				renderer.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.125F, 0.0F, 0.5F, 0.375F, 0.5, 0.875F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.0F, 0.0625, 0.625, 1.0, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625F, 0.0F, 0.5F, 0.875F, 0.75, 0.875F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 5) { //Transition
				renderer.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.125F, 0.0F, 0.5F, 0.375F, 0.75F, 0.875F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.0F, 0.0625, 0.625, 1.0, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625F, 0.0F, 0.5F, 0.875F, 1.0, 0.875F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 6) { //2nd Layer
				renderer.setRenderBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.25, 0.6875F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375F, 0.25, 0.375F, 0.625F, 0.375, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.25, 0.0F, 0.5F, 0.375, 0.1875, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.0F, 0.1875, 0.625, 0.125, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.5625F, 0.0F, 0.5F, 0.75, 0.125, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 7) {
				renderer.setRenderBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.5F, 0.6875F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375F, 0.5F, 0.375F, 0.625F, 0.625F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.25F, 0.0F, 0.5F, 0.4325F, 0.25F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.0F, 0.125F, 0.625F, 0.375F, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625F, 0.0F, 0.5F, 0.8125f, 0.375F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 8) {
				renderer.setRenderBounds(0.3125F, 0.75F, 0.3125F, 0.6875F, 0.875F, 0.6875F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375F, 0.875F, 0.375F, 0.625F, 0.9325F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.75F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.4325F, 0.0F, 0.0625, 0.625, 0.6875F, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625F, 0.0F, 0.5F, 0.8125F, 0.625, 0.8125F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 9) { //Transition
				renderer.setRenderBounds(0.3125F, 0.75F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.75F, 0.75F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.4325F, 0.0F, 0.0625, 0.625, 0.75F, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.75F, 0.125F, 0.625, 1.0F, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625F, 0.0F, 0.5F, 0.8125F, 0.625, 0.8125F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 10) { //3rd Layer
				renderer.setRenderBounds(0.375F, 0F, 0.3125F, 0.625F, 0.1875F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.1875F, 0.4375F, 0.5625F, 0.25F, 0.5625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0F, 0.1875F, 0.5625F, 0.125F, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 11) {
				renderer.setRenderBounds(0.375F, 0F, 0.3125F, 0.625F, 0.4325F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0.4325F, 0.375F, 0.5625F, 0.5F, 0.5625F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.4325F, 0F, 0.1875F, 0.625F, 0.3125F, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0F, 0.4325F, 0.6875F, 0.3125, 0.6875);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 12) {
				renderer.setRenderBounds(0.3125F, 0F, 0.3125F, 0.625F, 0.75F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375F, 0.75F, 0.375F, 0.5625F, 0.875F, 0.5625F);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.4325F, 0F, 0.1875F, 0.625F, 0.675F, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0F, 0.4325F, 0.6875F, 0.5625F, 0.6875);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 13) {//Transition
				renderer.setRenderBounds(0.3125F, 0F, 0.3125F, 0.625F, 1.0, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
	            //renderer.setRenderBounds(0.375F, 0.75F, 0.375F, 0.5625F, 0.875F, 0.5625F);
                //renderer.renderStandardBlock(block, x, y, z);

				renderer.setRenderBounds(0.4325F, 0F, 0.1875F, 0.625F, 0.875F, 0.3125F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4325F, 0F, 0.4325F, 0.6875F, 0.75, 0.6875);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 14) { //4th Layer
				renderer.setRenderBounds(0.3125F, 0F, 0.3125F, 0.625F, 0.1875F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4375F, 0.1875F, 0.3125F, 0.625F, 0.3125F, 0.5F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375F, 0.1875F, 0.375F, 0.5625F, 0.375F, 0.5625F);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (metadata == 15) {
				renderer.setRenderBounds(0.3125F, 0F, 0.3125F, 0.625F, 0.25F, 0.625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4375F, 0.25F, 0.3125F, 0.625F, 0.5F, 0.5F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375F, 0.1875F, 0.375F, 0.5625F, 0.6875F, 0.5625F);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.4375F, 0.6875F, 0.4375F, 0.5625F, 0.75F, 0.5625F);
				renderer.renderStandardBlock(block, x, y, z);
			}

			//Unused
            /*else if (metadata == 4) //Leftover fragments
            {
                renderer.setRenderBounds(0.125F, 0.0F, 0.5F, 0.375F, 0.125F, 0.75F);
                renderer.renderStandardBlock(block, x, y, z);
                renderer.setRenderBounds(0.4325F, 0.0F, 0.0625, 0.625, 0.25F, 0.3125F);
                renderer.renderStandardBlock(block, x, y, z);
                renderer.setRenderBounds(0.625F, 0.0F, 0.5F, 0.75F, 0.1875F, 0.75F);
                renderer.renderStandardBlock(block, x, y, z);
            }*/
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return model;
	}
}
