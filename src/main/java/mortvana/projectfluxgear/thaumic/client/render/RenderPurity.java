package mortvana.projectfluxgear.thaumic.client.render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPurity extends Render {

	public RenderPurity() {
		shadowSize = 0.1F;
	}

	//TODO: Ummm...
	public void renderEntityAt(Entity entity, double x, double y, double z, float freq, float pTicks) {}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float freq, float f1) {
		renderEntityAt(entity, x, y, z, freq, f1);
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return AbstractClientPlayer.locationStevePng;
	}
}
