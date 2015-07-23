package mortvana.melteddashboard.registry;

import mortvana.melteddashboard.block.metadata.FluxGearBlockExtendedMetadata;
import mortvana.melteddashboard.item.FluxGearItem;

public interface IMaterialRegistryItems {
	public FluxGearBlockExtendedMetadata getBlock();
	public FluxGearItem getIngot();
}
