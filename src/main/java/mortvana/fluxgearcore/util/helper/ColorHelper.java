package mortvana.fluxgearcore.util.helper;

public final class ColorHelper {
	//TODO: Switch to hexadecimal
	public static final int DYE_BLACK = 1644825;
	public static final int DYE_RED = 13388876;
	public static final int DYE_GREEN = 6717235;
	public static final int DYE_BROWN = 8349260;
	public static final int DYE_BLUE = 3368652;
	public static final int DYE_PURPLE = 11691749;
	public static final int DYE_CYAN = 5020082;
	public static final int DYE_LIGHT_GRAY = 10066329;
	public static final int DYE_GRAY = 5000268;
	public static final int DYE_PINK = 15905484;
	public static final int DYE_LIME = 8375321;
	public static final int DYE_YELLOW = 15066419;
	public static final int DYE_LIGHT_BLUE = 10072818;
	public static final int DYE_MAGENTA = 15040472;
	public static final int DYE_ORANGE = 15905331;
	public static final int DYE_WHITE = 16777215;
	public static final int[] DYE_COLORS = new int[] {1644825, 13388876, 6717235, 8349260, 3368652, 11691749, 5020082, 10066329, 5000268, 15905484, 8375321, 15066419, 10072818, 15040472, 15905331, 16777215};

	private ColorHelper() {
	}

	public static int getDyeColor(int var0) {
		return var0 >= 0 && var0 <= 15 ? DYE_COLORS[var0] : 16777215;
	}
}