package mortvana.projectfluxgear.tech.util;

public class OreRegistration {


	public class OreCompound {
		public MetaOre[] ores;

		public OreCompound(MetaOre... ores) {
			this.ores = ores;
		}

		public MetaOre getMetaOre(int metadata) {
			return ores[metadata];
		}
	}

	public class MetaOre {}

	public interface IOreGen {}

	public class StandardGen implements IOreGen {
		public int yMin;
		public int yMax;
		public int density;

	}
}
