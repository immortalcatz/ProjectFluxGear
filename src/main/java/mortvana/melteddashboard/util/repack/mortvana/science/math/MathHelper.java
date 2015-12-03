package mortvana.melteddashboard.util.repack.mortvana.science.math;

import java.util.Random;

public class MathHelper {

	//public static String formatNumberAsString(double value) {
	//	return "";
	//}

	/* *=-=-=-=* Constants *=-=-=-=* */
	public static final double PHI = 1.618034;
	public static final Random RANDOM = new Random();
	public static final double[] SINE_TABLE = new double[65536];

	static {
		for (int i = 0; i < 65536; i++) {
			SINE_TABLE[i] = Math.sin(i / (double) 65536 * 2 * Math.PI);
		}
		SINE_TABLE[0] = 0;
		SINE_TABLE[16384] = 1;
		SINE_TABLE[32768] = 0;
		SINE_TABLE[49152] = 0;
	}

	/* *=-=-=-=* Clamping *=-=-=-=* */
	public static int clampInt(int value, int min, int max) {
		return value < min ? min : (value > max ? max : value);
	}

	public static float clampFloat(float value, float min, float max) {
		return value < min ? min : (value > max ? max : value);
	}

	public static double clampDouble(double value, double min, double max) {
		return value < min ? min : (value > max ? max : value);
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

	/* *=-=-=-=* Trigonometry *=-=-=-=* */

	public static double sin(double value) {
		return SINE_TABLE[(int) ((float) value * 10430.378F) & 65535];
	}

	public static double cos(double value) {
		return SINE_TABLE[(int) ((float) value * 10430.378F + 16384.0F) & 65535];
	}

	/* *=-=-=-=* Unchecked Comparison *=-=-=-=* */

	/* *=-=-=-=* Other *=-=-=-=* */

	public static double offsetIntToFloat(int value) {
		return (double)((float) value + 0.5F);
	}

	public static int diffRand(int min, int max) {
		return min != max ? RANDOM.nextInt(max - min) + min : min;
	}
}
