package mortvana.legacy.clean.thaumicrevelations.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderFleshGolem extends RenderLiving {
	private static final ResourceLocation textures = new ResourceLocation("trevelation", "assets.mortvana.fluxgearzee.textures/mob/fleshgolem.png");
	private final ModelFleshGolem model;

	public RenderFleshGolem() {
		super(new ModelFleshGolem(), 0.5F);
		model = (ModelFleshGolem) super.mainModel;
		setRenderPassModel(model);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return textures;
	}
}