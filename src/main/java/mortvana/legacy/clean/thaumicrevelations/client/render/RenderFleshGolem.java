package mortvana.legacy.clean.thaumicrevelations.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderFleshGolem extends RenderLiving {

	public RenderFleshGolem() {
		super(new ModelFleshGolem(), 0.5F);
		setRenderPassModel(super.mainModel);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("trevelation", "assets.mortvana.fluxgearzee.textures/mob/fleshgolem.png");
	}
}