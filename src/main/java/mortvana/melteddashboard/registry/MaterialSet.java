package mortvana.melteddashboard.registry;

import mortvana.melteddashboard.item.FluxGearItem;

public class MaterialSet {
	public FluxGearItem item;
	public int offset;
	public String prefix;

	public MaterialSet(FluxGearItem item, int offset, String prefix) {
		this.item = item;
		this.offset = offset;
		this.prefix = prefix;
	}

	public FluxGearItem getItem() {
		return item;
	}

	public int getOffset() {
		return offset;
	}

	public String getPrefix() {
		return prefix;
	}
}
