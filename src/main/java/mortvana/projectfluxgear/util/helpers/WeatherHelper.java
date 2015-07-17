package mortvana.projectfluxgear.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import mortvana.projectfluxgear.util.enums.EnumDivisorMode;

public class WeatherHelper {

	/* *=-=-=-=* Conditions and Calculations *=-=-=-=* */
	public static boolean canSeeSky(World world, int x, int y, int z) {
		return world.canBlockSeeTheSky(x, y, z);
	}

	/*public static int getSunLight(World world, int x, int y, int z) {
		if (canSeeSky(world, x, y, z)) {
			return 15;
		} else {
			Block block = world.getBlock(x, y, z);
			int blockLight = block.getLightValue(world, x, y, z);

		}
	}*/


	/* *=-=-=-=* Power Generation *=-=-=-=* */
	//public static double getPowerOutput(EnumDivisorMode mode, int flux, int multiplier, World world) {
	//
	//}

}
