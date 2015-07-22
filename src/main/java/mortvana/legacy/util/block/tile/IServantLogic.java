package mortvana.legacy.util.block.tile;

import net.minecraft.world.World;

import mortvana.legacy.util.coordinates.CoordTuple;

public interface IServantLogic {
	public CoordTuple getMasterPosition ();

	/** The block should already have a valid master */
	public void notifyMasterOfChange ();

	/** Checks if this block can be tied to this master
	 *
	 * @param master
	 * @param world the world of master
	 * @param x xCoord of master
	 * @param y yCoord of master
	 * @param z zCoord of master
	 * @return whether the servant can be tied to this master
	 */
	public boolean setPotentialMaster (IMasterLogic master, World world, int x, int y, int z);

	/** Used to set and verify that this is the block's master
	 *
	 * @param master
	 * @param world
	 * @param x xCoord of master
	 * @param y yCoord of master
	 * @param z zCoord of master
	 * @return Is this block tied to this master?
	 */
	public boolean verifyMaster (IMasterLogic master, World world, int x, int y, int z);

	/** Exactly what it says on the tin
	 *
	 * @param master
	 * @param world
	 * @param x xCoord of master
	 * @param y yCoord of master
	 * @param z zCoord of master
	 */
	public void invalidateMaster (IMasterLogic master, World world, int x, int y, int z);
}
