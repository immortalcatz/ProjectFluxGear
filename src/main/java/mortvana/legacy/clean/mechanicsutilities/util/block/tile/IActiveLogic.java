package mortvana.legacy.clean.mechanicsutilities.util.block.tile;

/**
 * Marks blocks that can be active and inactive.
 */

public interface IActiveLogic {

	public boolean getActive();

	public void setActive (boolean flag);
}
