package mortvana.projectfluxgear.modules.mechutil;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import mortvana.fluxgearcore.pulsar.pulse.Handler;
import mortvana.fluxgearcore.pulsar.pulse.Pulse;
import mortvana.projectfluxgear.common.ProjectFluxGear;

@ObjectHolder(ProjectFluxGear.modID)
@Pulse(id = "Mechanic's Utilities", description = "Random Stuff thrown together for utility")
public class MechanicsUtils {

    @Handler
    public void preInit (FMLPreInitializationEvent event) {

    }
}
