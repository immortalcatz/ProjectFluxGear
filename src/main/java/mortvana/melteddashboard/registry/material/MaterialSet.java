package mortvana.melteddashboard.registry.material;

import mortvana.melteddashboard.item.FluxGearItem;

public class MaterialSet {
	public FluxGearItem item;
	public int offset, itemIndex;
	public String prefix;

	public MaterialSet(FluxGearItem item, int offset, String prefix, int itemIndex) {
		this.item = item;
		this.offset = offset;
		this.prefix = prefix;
		this.itemIndex = itemIndex;
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

	public int getIndex() {
		return itemIndex;
	}
}
