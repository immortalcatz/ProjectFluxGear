package oldcode.projectfluxgear;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.common.MinecraftForge;

import mortvana.projectfluxgear.thaumic.client.render.RenderPurity;

public class ClientProxy extends CommonProxy {

	public static int renderPass;
	public static int dualPassCubeID;

	public static final TRParticle PARTICLE_HANDLER = new TRParticle();

    @Override
    public void registerRenderers() {
	    dualPassCubeID = RenderingRegistry.getNextAvailableRenderId();
	    RenderingRegistry.registerBlockHandler(new DualPassCubeRenderer());

	    RenderingRegistry.registerEntityRenderingHandler(ThaumicContent.EntityPurity.class, new RenderPurity());
	    RenderingRegistry.registerEntityRenderingHandler(FleshGolem.class, new RenderFleshGolem());
	    RenderingRegistry.registerEntityRenderingHandler(EntityFleshProjectile.class, new RenderSnowball(Items.rotten_flesh));

    }

    @Override
    public void registerSound() {
        System.out.println("THERMAL TINKERER - WEIRD SCIENCE LEGACY DEBUG: REGISTER SOUND CALLED");
        MinecraftForge.EVENT_BUS.register(new EventSounds(ThermalTinkerer.sounds));
    }
}
