package oldcode.projectfluxgear;

public interface IFluxGearWrench {
	public boolean isPFGWrench();
	public void onLeftWhack();
	public void onRightWhack();
	public void onSneakLeftWhack();
	public void onSneakRightWhack();
	public void hideFacades();
}
