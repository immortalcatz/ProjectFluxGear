package mortvana.melteddashboard.registry;

import mortvana.melteddashboard.block.metadata.FluxGearBlockExtendedMetadata;
import mortvana.melteddashboard.item.FluxGearItem;

public class MaterialData {

	public FluxGearBlockExtendedMetadata block;
	public FluxGearItem ingot;

	public MaterialData(FluxGearBlockExtendedMetadata block, FluxGearItem ingot) {
		this.block = block;
		this.ingot = ingot;
	}

	public final MaterialSet INGOT = new MaterialSet(ingot, 0, "ingot");
	public final MaterialSet CHUNK = new MaterialSet(ingot, 2000, "chunk");
	public final MaterialSet NUGGET = new MaterialSet(ingot, 4000, "nugget");
	public final MaterialSet CAST_INGOT = new MaterialSet(ingot, 6000, "ingotCast");
	public final MaterialSet INGOT_PILE = new MaterialSet(ingot, 8000, "ingotPile");
	public final MaterialSet LARGE_INGOT = new MaterialSet(ingot, 10000, "ingotLarge");
	public final MaterialSet GEAR = new MaterialSet(ingot, 12000, "gear");
	public final MaterialSet COG = new MaterialSet(ingot, 14000, "cog");

	public static MaterialSet DUST;

}
