package oldcode.projectfluxgear.util.block;


public interface IMasterNode extends IMasterLogic, IServantLogic {
	public boolean isCurrentlyMaster ();

	public boolean isEquivalentMaster (IMasterLogic master);
}
