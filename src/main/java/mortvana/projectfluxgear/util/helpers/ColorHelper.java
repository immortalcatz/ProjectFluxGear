package mortvana.projectfluxgear.util.helpers;

public final class ColorHelper {
	public static final int COLOR_DYE_BLACK = 0x191919;
	public static final int COLOR_DYE_RED = 0xCC4C4C;
	public static final int COLOR_DYE_GREEN = 0x667F33;
	public static final int COLOR_DYE_BROWN = 0x7F664C;
	public static final int COLOR_DYE_BLUE = 0x3366CC;
	public static final int COLOR_DYE_PURPLE = 0xB266E5;
	public static final int COLOR_DYE_CYAN = 0x4C99B2;
	public static final int COLOR_DYE_LIGHT_GRAY = 0x999999;
	public static final int COLOR_DYE_GRAY = 0x4C4C4C;
	public static final int COLOR_DYE_PINK = 0xF2B2CC;
	public static final int COLOR_DYE_LIME = 0x7FCC19;
	public static final int COLOR_DYE_YELLOW = 0xE5E533;
	public static final int COLOR_DYE_LIGHT_BLUE = 0x99B2F2;
	public static final int COLOR_DYE_MAGENTA = 0xE57FD8;
	public static final int COLOR_DYE_ORANGE = 0xF2B233;
	public static final int COLOR_DYE_WHITE = 0xFFFFFF;

	public static final int[] DYE_COLORS = new int[] { 0x191919, 0xCC4C4C, 0x667F33, 0x7F664C, 0x3366CC, 0xB266E5, 0x4C99B2, 0x999999, 0x4C4C4C, 0xF2B2CC, 0x7FCC19, 0xE5E533, 0x99B2F2, 0xE57FD8, 0xF2B233, 0xFFFFFF };

	public static final int COLOR_MC_DYE_BLACK = 0x1E1B1B;
	public static final int COLOR_MC_DYE_RED = 0xB3312C;
	public static final int COLOR_MC_DYE_GREEN = 0x3B511A;
	public static final int COLOR_MC_DYE_BROWN = 0x51301A;
	public static final int COLOR_MC_DYE_BLUE = 0x253192;
	public static final int COLOR_MC_DYE_PURPLE = 0x7B2FBE;
	public static final int COLOR_MC_DYE_CYAN = 0x287697;
	public static final int COLOR_MC_DYE_LIGHT_GRAY = 0xABABAB;
	public static final int COLOR_MC_DYE_GRAY = 0x434343;
	public static final int COLOR_MC_DYE_PINK = 0xD88198;
	public static final int COLOR_MC_DYE_LIME = 0x41CD34;
	public static final int COLOR_MC_DYE_YELLOW = 0xDECF2A;
	public static final int COLOR_MC_DYE_LIGHT_BLUE = 0x6689D3;
	public static final int COLOR_MC_DYE_MAGENTA = 0xC354CD;
	public static final int COLOR_MC_DYE_ORANGE = 0xEB8844;
	public static final int COLOR_MC_DYE_WHITE = 0xF0F0F0;

	public static final int[] MC_DYE_COLORS = new int[] { 0x1E1B1B, 0xB3312C, 0x3B511A, 0x51301A, 0x253192, 0x7B2FBE, 0x287697, 0xABABAB, 0x434343, 0xD88198, 0x41CD34, 0xDECF2A, 0x6689D3, 0xC354CD, 0xEB8844, 0xF0F0F0 };

	private ColorHelper() { /* Singleton */ }

	public static int getDyeColor (int meta) {
		if (meta >= 0 && meta <= 15) { //If meta is 0-15
			return DYE_COLORS[meta];
		} else {
			return COLOR_DYE_WHITE;
		}
	}

	public static int getWoolColor (int meta) {
		if (meta >= 0 && meta <= 15) { //If meta is 0-15
			return 15 - DYE_COLORS[meta];
		} else {
			return COLOR_DYE_WHITE;
		}
	}

	public static int getMinecraftDyeColor (int meta) {
		if (meta >= 0 && meta <= 15) { //If meta is 0-15
			return MC_DYE_COLORS[meta];
		} else {
			return COLOR_DYE_WHITE;
		}
	}

	public static int getMinecraftWoolColor (int meta) {
		if (meta >= 0 && meta <= 15) { //If meta is 0-15
			return 15 - MC_DYE_COLORS[meta];
		} else {
			return COLOR_DYE_WHITE;
		}
	}

	public static final int COLOR_MATERIAL_COPPER = 0xC95926;
	public static final int COLOR_MATERIAL_TIN = 0x99AFAF;
	public static final int COLOR_MATERIAL_LEAD = 0x544960;
	public static final int COLOR_MATERIAL_SILVER = 0xB1D3D3;
	public static final int COLOR_MATERIAL_NICKEL = 0xCEC59A;
	//
	public static final int COLOR_MATERIAL_PLATINUM = 0x72D7D8;
	//
	public static final int COLOR_MATERIAL_MOLYBDENUM = 0x808080;
	//
	public static final int COLOR_MATERIAL_ARSENIC = 0x968866;
	public static final int COLOR_MATERIAL_ANTIMONY = 0x707061;
	public static final int COLOR_MATERIAL_NEODYMIUM = 0x7A5D70;
	public static final int COLOR_MATERIAL_TESSERACTIUM = 0x097261;
	public static final int COLOR_MATERIAL_CADMIUM = 0x9BCCCC;
	public static final int COLOR_MATERIAL_BRONZE= 0xBF813F;

}
