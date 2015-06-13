package oldcode.projectfluxgear.util.helper;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HolidayHelper {
	static Calendar curTime = Calendar.getInstance();
	static Calendar holidayStart = Calendar.getInstance();
	static Calendar holidayEnd = Calendar.getInstance();

	private HolidayHelper() {
	}

	//TODO: Arbor Day, Douglas Adams' Birthday


	public static boolean isNewYear() {
		setDate(holidayStart, 11, 31, false);
		setDate(holidayEnd, 0, 2, true);
		holidayEnd.set(1, 2);
		curTime = Calendar.getInstance();
		return curTime.after(holidayStart) && curTime.before(holidayEnd);
	}

	public static boolean isValentinesDay() {
		setDate(holidayStart, 1, 13, false);
		setDate(holidayEnd, 1, 15, true);
		curTime = Calendar.getInstance();
		return curTime.after(holidayStart) && curTime.before(holidayEnd);
	}

	public static boolean isStPatricksDay() {
		setDate(holidayStart, 2, 16, false);
		setDate(holidayEnd, 2, 18, true);
		curTime = Calendar.getInstance();
		return curTime.after(holidayStart) && curTime.before(holidayEnd);
	}

	public static boolean isAprilFools() {
		setDate(holidayStart, 2, 31, false);
		setDate(holidayEnd, 3, 2, true);
		curTime = Calendar.getInstance();
		return curTime.after(holidayStart) && curTime.before(holidayEnd);
	}

	public static boolean isEarthDay() {
		setDate(holidayStart, 3, 21, false);
		setDate(holidayEnd, 3, 23, true);
		curTime = Calendar.getInstance();
		return curTime.after(holidayStart) && curTime.before(holidayEnd);
	}

	public static boolean isEaster() {
		int var1 = Calendar.getInstance().get(1);
		if(var1 <= 1582) {
			return false;
		} else {
			int var2 = var1 % 19 + 1;
			int var3 = var1 / 100 + 1;
			int var4 = 3 * var3 / 4 - 12;
			int var5 = (8 * var3 + 5) / 25 - 5;
			int var6 = 5 * var1 / 4 - var4 - 10;
			int var7 = (11 * var2 + 20 + var5 - var4) % 30;
			if(var7 == 25 && var2 > 11 || var7 == 24) {
				++var7;
			}

			int var8 = 44 - var7;
			var8 += 30 * (var8 < 21?1:0);
			var8 += 7 - (var6 + var8) % 7;
			GregorianCalendar var0;
			if(var8 > 31) {
				var0 = new GregorianCalendar(var1, 3, var8 - 31);
			} else {
				var0 = new GregorianCalendar(var1, 2, var8);
			}

			setDate(holidayStart, var0.get(2), var0.get(5) - 1, false);
			setDate(holidayEnd, var0.get(2), var0.get(5) + 1, true);
			curTime = Calendar.getInstance();
			return curTime.after(holidayStart) && curTime.before(holidayEnd);
		}
	}

	public static boolean isUSIndependenceDay() {
		setDate(holidayStart, 6, 3, false);
		setDate(holidayEnd, 6, 5, true);
		curTime = Calendar.getInstance();
		return curTime.after(holidayStart) && curTime.before(holidayEnd);
	}

	public static boolean isHalloween() {
		setDate(holidayStart, 9, 30, false);
		setDate(holidayEnd, 10, 1, true);
		curTime = Calendar.getInstance();
		return curTime.after(holidayStart) && curTime.before(holidayEnd);
	}

	public static boolean isThanksgiving() {
		return false;
	}

	public static boolean isHanukkah() {
		return false;
	}

	public static boolean isChristmas() {
		setDate(holidayStart, 11, 24, false);
		setDate(holidayEnd, 11, 26, true);
		curTime = Calendar.getInstance();
		return curTime.after(holidayStart) && curTime.before(holidayEnd);
	}

	static void setDate(Calendar var0, int var1, int var2, boolean var3) {
		var0.clear();
		var0.set(1, Calendar.getInstance().get(1));
		var0.set(2, var1);
		var0.set(5, var2);
		if(var3) {
			var0.set(11, 23);
			var0.set(12, 59);
			var0.set(13, 59);
			var0.set(14, 999);
		} else {
			var0.set(11, 0);
			var0.set(12, 0);
			var0.set(13, 0);
			var0.set(14, 0);
		}

	}
}