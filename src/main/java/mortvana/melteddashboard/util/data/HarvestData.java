package mortvana.melteddashboard.util.data;

public class HarvestData {
	public int miningLevel = -1;
	public String toolType;

	public HarvestData(int miningLevel, String toolType) {
		this.miningLevel = miningLevel;
		this.toolType = toolType;
	}

	public HarvestData(int miningLevel) {
		this.miningLevel = miningLevel;
	}

	public HarvestData(String toolType) {
		this.toolType = toolType;
	}

	public HarvestData() {}

	public int getMiningLevel() {
		return miningLevel;
	}

	public String getToolType() {
		return toolType;
	}

	public HarvestData setMiningLevel(int miningLevel) {
		this.miningLevel = miningLevel;
		return this;
	}

	public HarvestData setToolType(String toolType) {
		this.toolType = toolType;
		return this;
	}
}
