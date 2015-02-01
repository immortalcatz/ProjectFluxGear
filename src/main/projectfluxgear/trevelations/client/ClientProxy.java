package mortvana.projectfluxgear.trevelations.client;

import cpw.mods.fml.client.registry.RenderingRegistry;

//import mortvana.trevelations.client.render.RenderFleshGolem;
import mortvana.projectfluxgear.trevelations.client.render.RenderPurity;
import mortvana.projectfluxgear.trevelations.common.CommonProxy;
//import mortvana.trevelations.entity.EntityFleshProjectile;
import mortvana.projectfluxgear.trevelations.entity.EntityPurity;
//import mortvana.trevelations.entity.FleshGolem;

public class ClientProxy extends CommonProxy {

	//public static final TRParticle PARTICLE_HANDLER = new TRParticle();

	@Override
	public void initRenderers() {

		RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());
		//RenderingRegistry.registerEntityRenderingHandler(FleshGolem.class, new RenderFleshGolem());
		//RenderingRegistry.registerEntityRenderingHandler(EntityFleshProjectile.class, new RenderSnowball(Items.rotten_flesh));

	}

}
