package mortvana.melteddashboard.api.block.tile;

import net.minecraft.world.World;

public interface ITileEntity {

	public World getWorld();

	public int getXCoord();

	public short getYCoord();

	public int getZCoord();
}
