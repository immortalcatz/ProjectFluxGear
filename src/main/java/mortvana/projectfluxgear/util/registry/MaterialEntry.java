package mortvana.projectfluxgear.util.registry;

public class MaterialEntry {
	public static int materialID;
	public static EnumMaterialType materialType;
	public static String materialName;
	public static String materialTexture;
	public static String[] materialOreDict;
	public static float materialBlockHardness;
	public static float materialBlastResistance;
	public static int materialRarity;
	public static int materialMiningLevel;
	public static int materialBlockLight;
	public static int materialRedstoneSignal;
	public static int materialHexColor = -1;

	public MaterialEntry(int id, EnumMaterialType type, String name, String texture, String[] oreDict, float blockHardness, float blastResistance, int rarity, int miningLevel, int blockLight, int redstoneSignal) {
		materialID = id;
		materialType = type;
		materialName = name;
		materialTexture = texture;
		materialOreDict = oreDict;
		materialBlockHardness = blockHardness;
		materialBlastResistance = blastResistance;
		materialRarity = rarity;
		materialMiningLevel = miningLevel;
		materialBlockLight = blockLight;
		materialRedstoneSignal = redstoneSignal;
	}

	public MaterialEntry(int id, EnumMaterialType type, String name, String texture, String[] oreDict, float blockHardness, float blastResistance, int rarity, int miningLevel, int blockLight, int redstoneSignal, int hexColor) {
		this(id, type, name, texture, oreDict, blockHardness, blastResistance, rarity, miningLevel, blockLight, redstoneSignal);
		if (hexColor >= 0) {
			materialHexColor = hexColor;
		}
	}

	public int getMaterialID() {
		return materialID;
	}

	public EnumMaterialType getMaterialType() {
		return materialType;
	}

	public String getMaterialName() {
		return materialName;
	}

	public String getMaterialTexture() {
		return materialTexture;
	}

	public String[] getMaterialOreDict() {
		return materialOreDict;
	}

	public float getMaterialBlockHardness() {
		return materialBlockHardness;
	}

	public float getMaterialBlastResistance()  {
		return materialBlastResistance;
	}

	public int getMaterialRarity() {
		return materialRarity;
	}

	public int getMaterialMiningLevel() {
		return materialMiningLevel;
	}

	public int getMaterialBlockLight() {
		return materialBlockLight;
	}

	public int getMaterialRedstoneSignal() {
		return materialRedstoneSignal;
	}

	public int getMaterialHexColor() {
		return materialHexColor;
	}
}
