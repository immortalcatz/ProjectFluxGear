package mortvana.melteddashboard.api.block.tile;

public interface ITemperatureHandler {

	public boolean getTemperature();

	public void setTemperature(int temperature);

	public void modifyTemperature(int offset);
}
