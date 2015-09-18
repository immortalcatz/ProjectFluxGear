package mortvana.projectfluxgear.thaumic.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

import mortvana.projectfluxgear.thaumic.common.ThaumicContent;

public class ExubituraGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int x = chunkX * 16 + random.nextInt(128);
		int z = chunkZ * 16 + random.nextInt(128);
		int y = world.getHeightValue(x, z);

		if (world.isAirBlock(x, y, z) && ThaumicContent.blockThaumicPlant.canBlockStay(world, x, y, z) && random.nextInt(1000) <= 10) {
			world.setBlock(x, y, z, ThaumicContent.blockThaumicPlant, 8, 2);
		}
	}
}