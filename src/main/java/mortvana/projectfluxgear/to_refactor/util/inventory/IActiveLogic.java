package mortvana.projectfluxgear.to_refactor.util.inventory;

/**
 * Marks blocks that can be active and inactive.
 */

public interface IActiveLogic {

	public boolean getActive();

	public void setActive (boolean flag);
}