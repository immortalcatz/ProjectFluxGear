package mortvana.melteddashboard.util.random.weighted;

import net.minecraft.world.gen.feature.WorldGenerator;

public class WeightedWorldGenerator extends WeightedEntry {

	public final WorldGenerator WORLD_GENERATOR;

	public WeightedWorldGenerator(WorldGenerator worldGenerator) {
		super(100);
		WORLD_GENERATOR = worldGenerator;
	}

	public WeightedWorldGenerator(WorldGenerator worldGenerator, int weight) {
		super(weight);
		WORLD_GENERATOR = worldGenerator;
	}

	public WorldGenerator getWorldGenerator() {
		return WORLD_GENERATOR;
	}
}
