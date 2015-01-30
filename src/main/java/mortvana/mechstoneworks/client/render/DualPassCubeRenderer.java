package mortvana.mechstoneworks.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import mortvana.mechstoneworks.client.ClientProxy;

public class DualPassCubeRenderer implements ISimpleBlockRenderingHandler {
	Block renderBlock;
	Block renderOverlay;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		// Which rendering pass are we doing today?
		if(ClientProxy.renderPass == 0) {
			// We are rendering the solid part of it, lets render it!
			renderer.renderStandardBlock(renderBlock, x, y, z);
		} else if(ClientProxy.renderPass == 1) {
			// We are rendering the transparent part of it, lets render it, or at least the visible part...
			renderer.renderStandardBlock(renderOverlay, x, y, z);
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.dualPassCubeID;
	}
}
