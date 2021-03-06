package mortvana.melteddashboard.util.helpers;

public final class ColorHelper {
	private ColorHelper() {}

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

	public static final int[] DYE_COLORS = new int[] {0x191919, 0xCC4C4C, 0x667F33, 0x7F664C, 0x3366CC, 0xB266E5, 0x4C99B2, 0x999999, 0x4C4C4C, 0xF2B2CC, 0x7FCC19, 0xE5E533, 0x99B2F2, 0xE57FD8, 0xF2B233, 0xFFFFFF};

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

	public static final int[] MC_DYE_COLORS = new int[] {0x1E1B1B, 0xB3312C, 0x3B511A, 0x51301A, 0x253192, 0x7B2FBE, 0x287697, 0xABABAB, 0x434343, 0xD88198, 0x41CD34, 0xDECF2A, 0x6689D3, 0xC354CD, 0xEB8844, 0xF0F0F0};

	public static int getDyeColor(int meta) {
		if (meta >= 0 && meta <= 15) { //If meta is 0-15
			return DYE_COLORS[meta];
		} else {
			return COLOR_DYE_WHITE;
		}
	}

	public static int getWoolColor(int meta) {
		if (meta >= 0 && meta <= 15) { //If meta is 0-15
			return 15 - DYE_COLORS[meta];
		} else {
			return COLOR_DYE_WHITE;
		}
	}

	public static int getMinecraftDyeColor(int meta) {
		if (meta >= 0 && meta <= 15) { //If meta is 0-15
			return MC_DYE_COLORS[meta];
		} else {
			return COLOR_DYE_WHITE;
		}
	}

	public static int getMinecraftWoolColor(int meta) {
		if (meta >= 0 && meta <= 15) { //If meta is 0-15
			return 15 - MC_DYE_COLORS[meta];
		} else {
			return COLOR_DYE_WHITE;
		}
	}

	// Yes, this list is pre-localized to en_US and has no spaces. There are times when this is useful,
	// such as in a config file. Localization there is messy and not strictly required.
	public static final String[] TITLE_COLOR_NAMES = { "White", "Orange", "Magenta", "LightBlue", "Yellow", "Lime", "Pink", "Gray", "LightGray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };
	public static final String[] LOWER_PAINT_NAMES = { "white", "orange", "magenta", "lightblue", "yellow", "lime", "pink", "gray", "silver", "aqua", "purple", "blue", "brown", "green", "red", "black" };

}