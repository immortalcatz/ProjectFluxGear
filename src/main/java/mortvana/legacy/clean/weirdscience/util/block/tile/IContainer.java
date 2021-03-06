package mortvana.legacy.clean.weirdscience.util.block.tile;


import mortvana.legacy.clean.weirdscience.util.slot.SlotData;

/**
 * An ISidedInventory with SlotData for its slots.
 */

@Deprecated
public interface IContainer {
	/*
	 * Gets a reference to an array of the slots available.
	 * No guarantees are made that this is the actual base
	 * container, treat it as immutable!
	 */
	SlotData[] getSlotData();
	
	/*
	 * Gets a slot by index.
	 */
	SlotData getSlotDatum(int index);
	
	/*
	 * Gets the index of a slot on this container by identifier.
	 */
	int getSlotDataIndex(String name);
}
