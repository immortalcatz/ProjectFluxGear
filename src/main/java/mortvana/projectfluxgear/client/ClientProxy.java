package mortvana.projectfluxgear.client;

import cpw.mods.fml.client.registry.RenderingRegistry;

import mortvana.projectfluxgear.client.render.DualPassCubeRenderer;
import mortvana.projectfluxgear.common.CommonProxy;
import mortvana.projectfluxgear.entity.EntityPurity;
import mortvana.projectfluxgear.client.render.RenderPurity;

public class ClientProxy extends CommonProxy {

	public static int renderPass;
	public static int dualPassCubeID;

	//public static final TRParticle PARTICLE_HANDLER = new TRParticle();

    @Override
    public void registerRenderers() {
	    dualPassCubeID = RenderingRegistry.getNextAvailableRenderId();
	    RenderingRegistry.registerBlockHandler(new DualPassCubeRenderer());

	    RenderingRegistry.registerEntityRenderingHandler(EntityPurity.class, new RenderPurity());
	    //RenderingRegistry.registerEntityRenderingHandler(FleshGolem.class, new RenderFleshGolem());
	    //RenderingRegistry.registerEntityRenderingHandler(EntityFleshProjectile.class, new RenderSnowball(Items.rotten_flesh));

    }

    /*@Override
    public void registerSound() {
        System.out.println("THERMAL TINKERER - WEIRD SCIENCE LEGACY DEBUG: REGISTER SOUND CALLED");
        MinecraftForge.EVENT_BUS.register(new EventSounds(ThermalTinkerer.sounds));
    }*/
}
