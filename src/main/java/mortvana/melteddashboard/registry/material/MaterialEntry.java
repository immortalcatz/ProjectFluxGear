package mortvana.melteddashboard.registry.material;

import mortvana.melteddashboard.util.helpers.StringHelper;

public class MaterialEntry {
	public int materialID;
	public EnumMaterialType materialType;
	public String materialName;
	public String materialTexture;
	public String[] materialOreDict;
	public float materialBlockHardness;
	public float materialBlastResistance;
	public byte materialRarity;
	public int materialMiningLevel;
	public byte materialBlockLight;
	public byte materialRedstoneSignal;
	public int materialHexColor = -1;

	public MaterialEntry(int id, EnumMaterialType type, String name, String texture, String[] oreDict, float blockHardness, float blastResistance, byte rarity, int miningLevel, byte blockLight, byte redstoneSignal) {
		materialID = id;
		materialType = type;
		materialName = name;
		materialTexture = texture;
		materialOreDict = StringHelper.generalizeOreDictStingArray(oreDict);
		materialBlockHardness = blockHardness;
		materialBlastResistance = blastResistance;
		materialRarity = rarity;
		materialMiningLevel = miningLevel;
		materialBlockLight = blockLight;
		materialRedstoneSignal = redstoneSignal;
	}

	public MaterialEntry(int id, EnumMaterialType type, String name, String texture, String[] oreDict, float blockHardness, float blastResistance, byte rarity, int miningLevel, byte blockLight, byte redstoneSignal, int hexColor) {
		this(id, type, name, texture, oreDict, blockHardness, blastResistance, rarity, miningLevel, blockLight, redstoneSignal);
		if (hexColor >= 0) {
			materialHexColor = hexColor;
		}
	}

	public MaterialEntry(int id, EnumMaterialType type, String name, String texture, String[] oreDict, float blockHardness, float blastResistance, int rarity, int miningLevel, int blockLight, int redstoneSignal) {
		this(id, type, name, texture, oreDict, blockHardness, blastResistance, (byte) rarity, miningLevel, (byte) blockLight, (byte) redstoneSignal);
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

	public byte getMaterialRarity() {
		return materialRarity;
	}

	public int getMaterialMiningLevel() {
		return materialMiningLevel;
	}

	public byte getMaterialBlockLight() {
		return materialBlockLight;
	}

	public byte getMaterialRedstoneSignal() {
		return materialRedstoneSignal;
	}

	public int getMaterialHexColor() {
		return materialHexColor;
	}
}
