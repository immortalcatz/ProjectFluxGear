package mortvana.projectfluxgear.thaumic.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

import mortvana.projectfluxgear.to_refactor.common.FluxGearContent_;

public class ExubituraGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int X = chunkX * 16 + random.nextInt(128);
		int Z = chunkZ * 16 + random.nextInt(128);
		int Y = world.getHeightValue(X, Z);

		if (world.isAirBlock(X, Y, Z) && FluxGearContent_.blockPlant.canBlockStay(world, X, Y, Z) && random.nextInt(1000) <= 10) {

			world.setBlock(X, Y, Z, FluxGearContent_.blockPlant, 0, 2);

		}


	}

}
