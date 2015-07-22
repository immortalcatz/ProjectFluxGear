package mortvana.legacy.util;

public class Utils {
	public Utils() {
	}

	public static String formatNumber(double value) {
		if (value < 1.0e3D) {
			return String.valueOf(value);
		} else if (value < 1.0e6D) {
			return (double)Math.round(value) / 1.0e3D + "K";
		} else if (value < 1.0e9D) {
			return(double)Math.round(value) / 1.0e6D + "M";
		} else if (value < 1.0e12D) {
			return (double)Math.round(value) / 1.0e9D + "B";
		} else if (value < 1.0e15D) {
			return (double)Math.round(value) / 1.0e12D + "T";
		}
		return String.valueOf(value);
	}
}