package mortvana.projectfluxgear.util.block.ores;

public class OrePair {
	public static BasicOreEntry[] ores;
	public static OreBlockEntry block;

	public OrePair (OreBlockEntry block, BasicOreEntry[] ores) {
		this.block = block;
		this.ores = ores;
	}
}
