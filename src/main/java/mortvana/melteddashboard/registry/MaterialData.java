package mortvana.melteddashboard.registry;

import mortvana.melteddashboard.block.metadata.FluxGearBlockExtendedMetadata;
import mortvana.melteddashboard.item.FluxGearItem;

public class MaterialData {

	public IMaterialRegistryItems registry;

	public MaterialData(IMaterialRegistryItems registryItems) {
		registry = registryItems;
	}

	public final FluxGearBlockExtendedMetadata BASE_BLOCK = registry.getBlock();
	public final FluxGearItem BASE_INGOT = registry.getIngot();

	public final MaterialSet INGOT = new MaterialSet(BASE_INGOT, 0, "ingot");
	public final MaterialSet CHUNK = new MaterialSet(BASE_INGOT, 2000, "chunk");
	public final MaterialSet NUGGET = new MaterialSet(BASE_INGOT, 4000, "nugget");
	public final MaterialSet CAST_INGOT = new MaterialSet(BASE_INGOT, 6000, "ingotCast");
	public final MaterialSet INGOT_PILE = new MaterialSet(BASE_INGOT, 8000, "ingotPile");
	public final MaterialSet LARGE_INGOT = new MaterialSet(BASE_INGOT, 10000, "ingotLarge");
	public final MaterialSet GEAR = new MaterialSet(BASE_INGOT, 12000, "gear");
	public final MaterialSet COG = new MaterialSet(BASE_INGOT, 14000, "cog");



	public static MaterialSet DUST;
}
