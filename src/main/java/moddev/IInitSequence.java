package moddev;

public abstract interface IInitSequence {
	public abstract void preInit();
	public abstract void init();
	public abstract void postInit();
}
