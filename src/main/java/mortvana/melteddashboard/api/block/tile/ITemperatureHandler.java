package mortvana.melteddashboard.api.block.tile;

public interface ITemperatureHandler {

	public int getTemperature();

	public void setTemperature(int temperature);

	public void modifyTemperature(int offset);
}
