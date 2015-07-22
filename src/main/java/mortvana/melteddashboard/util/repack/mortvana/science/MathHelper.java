package mortvana.melteddashboard.util.repack.mortvana.science;

public class MathHelper {

	//public static String formatNumberAsString(double value) {
	//	return "";
	//}


	public static double clampDouble(double value, double min, double max) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		} else {
			return value;
		}
	}

	/* *=-=-=-=* Geometry *=-=-=-=* */
	public static double sumSquare2D(double x, double y) {
		return ((x * x) + (y * y));
	}

	public static double sumSquare3D(double x, double y, double z) {
		return ((x * x) + (y * y) + (z * z));
	}

	public static double getDistance2D(double x1, double y1, double x2, double y2) {
		return Math.sqrt(sumSquare2D(x1 - x2, y1 - y2));
	}

	public static double getDistance3D(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Math.sqrt(sumSquare3D(x1 - x2, y1 - y2, z1 - z2));
	}
}
