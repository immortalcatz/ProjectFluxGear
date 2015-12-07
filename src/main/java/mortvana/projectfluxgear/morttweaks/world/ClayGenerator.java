package mortvana.projectfluxgear.morttweaks.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

import static mortvana.melteddashboard.util.repack.mortvana.science.math.MathHelper.diffRand;
import static mortvana.projectfluxgear.core.config.MortTweaksConfig.*;

public class ClayGenerator implements IWorldGenerator {

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		//TODO: List of Dimensions to generate in
		if (world.provider.dimensionId == 0) {
			for (int i = 0; i < clayVeinQuantity; i++) {
				//TODO: WorldGenHelper.fuzzChunk()
				int x = chunkX * 16 + random.nextInt(16);
				int z = chunkZ * 16 + random.nextInt(16);
				new WorldGenMinable(Blocks.clay, diffRand(clayVeinMinSize, clayVeinMaxSize)).generate(world, random, x, diffRand(claySpawnMinY, claySpawnMaxY), z);
			}
		}
	}
}
