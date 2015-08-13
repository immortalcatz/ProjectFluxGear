package mortvana.melteddashboard.registry.material;

import mortvana.melteddashboard.block.metadata.FluxGearBlockExtendedMetadata;
import mortvana.melteddashboard.item.FluxGearItem;

public class MaterialData {

	public FluxGearBlockExtendedMetadata block;
	public FluxGearItem ingot;

	public FluxGearItem[] items;

	public MaterialData(FluxGearBlockExtendedMetadata block, FluxGearItem ingot) {
		this.block = block;
		this.ingot = ingot;

		items = new FluxGearItem[] { ingot };
	}

	public final MaterialSet INGOT = new MaterialSet(ingot, 0, "ingot", 0);
	public final MaterialSet CHUNK = new MaterialSet(ingot, 2000, "chunk", 0);
	public final MaterialSet NUGGET = new MaterialSet(ingot, 4000, "nugget", 0);
	public final MaterialSet CAST_INGOT = new MaterialSet(ingot, 6000, "ingotCast", 0);
	public final MaterialSet INGOT_PILE = new MaterialSet(ingot, 8000, "ingotPile", 0);
	public final MaterialSet LARGE_INGOT = new MaterialSet(ingot, 10000, "ingotLarge", 0);
	public final MaterialSet GEAR = new MaterialSet(ingot, 12000, "gear", 0);
	public final MaterialSet COG = new MaterialSet(ingot, 14000, "cog", 0);

	public static MaterialSet DUST;

}
