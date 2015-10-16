package mortvana.melteddashboard.util.data;

import net.minecraftforge.common.util.ForgeDirection;

public class DirectionalBoolean {

	public boolean[] values;

	public DirectionalBoolean(boolean down, boolean up, boolean north, boolean south, boolean west, boolean east) {
		values = new boolean[] { down, up, north, south, west, east };
	}

	public boolean valueOnSide(ForgeDirection side) {
		return values[side.ordinal()];
	}
}
