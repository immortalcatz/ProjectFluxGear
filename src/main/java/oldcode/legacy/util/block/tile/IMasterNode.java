package oldcode.legacy.util.block.tile;


public interface IMasterNode extends IMasterLogic, IServantLogic {
	public boolean isCurrentlyMaster ();

	public boolean isEquivalentMaster (IMasterLogic master);
}
