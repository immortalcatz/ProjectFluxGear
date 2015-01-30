package mortvana.mechstoneworks.client;

import cpw.mods.fml.client.registry.RenderingRegistry;

import mortvana.mechstoneworks.client.render.DualPassCubeRenderer;
import mortvana.mechstoneworks.common.CommonProxy;

public class ClientProxy extends CommonProxy {

	public static int renderPass;
	public static int dualPassCubeID;

	@Override
	public void registerRenderers() {
	}

	public static void registerRenderPassing() {
		dualPassCubeID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new DualPassCubeRenderer());
	}
}
