package mortvana.fluxgearcore.util.helper;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldHelper {

	public static Block getBlock(IBlockAccess world, int x, int y, int z) {
		return world.getBlock(x, y, z);
	}

	public static int getXOnSide(int x, ForgeDirection side) {
		return x + side.offsetX;
	}
	public static int getYOnSide(int y, ForgeDirection side) {
		return y + side.offsetY;
	}
	public static int getZOnSide(int z, ForgeDirection side) {
		return z + side.offsetZ;
	}
}
