package mortvana.projectfluxgear.util.block.structure;


public interface IMasterNode extends IMasterLogic, IServantLogic {
	public boolean isCurrentlyMaster ();

	public boolean isEquivalentMaster (IMasterLogic master);
}
