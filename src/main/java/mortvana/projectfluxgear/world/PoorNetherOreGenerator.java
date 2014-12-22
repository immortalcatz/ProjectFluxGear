package mortvana.projectfluxgear.world;

import net.minecraft.block.Block;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;

import mortvana.fluxgearcore.util.world.cjrepack.PoorOreGenerator;

public abstract class PoorNetherOreGenerator extends PoorOreGenerator {
	public PoorNetherOreGenerator(EventType eventType, int density, int yLevel, int yRange, int noiseSeed, Block ore, int metadata) {
		super(eventType, density, yLevel, yRange, noiseSeed, ore, metadata);
	}

	public PoorNetherOreGenerator(EventType eventType, double scale, double denseArea, double fringeArea, int density, int yLevel, int yRange, int noiseSeed, Block ore, int metadata) {
		super(eventType, scale, denseArea, fringeArea, density, yLevel, yRange, noiseSeed, ore, metadata);
	}
}
