package mortvana.melteddashboard.util.data;

import net.minecraftforge.common.util.ForgeDirection;

public class DirectionalInteger {

	public int[] values;

	public DirectionalInteger(int down, int up, int north, int south, int west, int east) {
		values = new int[] { down, up, north, south, west, east };
	}

	public int valueOnSide(ForgeDirection side) {
		return values[side.ordinal()];
	}
}
