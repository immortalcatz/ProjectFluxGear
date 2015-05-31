package oldcode.projectfluxgear.util.helper.cofh;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.input.Keyboard;

public final class StringHelper {
	public static final String BLACK = "\u00a70";
	public static final String BLUE = "\u00a71";
	public static final String GREEN = "\u00a72";
	public static final String TEAL = "\u00a73";
	public static final String RED = "\u00a74";
	public static final String PURPLE = "\u00a75";
	public static final String ORANGE = "\u00a76";
	public static final String LIGHT_GRAY = "\u00a77";
	public static final String GRAY = "\u00a78";
	public static final String LIGHT_BLUE = "\u00a79";
	public static final String BRIGHT_GREEN = "\u00a7a";
	public static final String BRIGHT_BLUE = "\u00a7b";
	public static final String LIGHT_RED = "\u00a7c";
	public static final String PINK = "\u00a7d";
	public static final String YELLOW = "\u00a7e";
	public static final String WHITE = "\u00a7f";
	public static final String OBFUSCATED = "\u00a7k";
	public static final String BOLD = "\u00a7l";
	public static final String STRIKETHROUGH = "\u00a7m";
	public static final String UNDERLINE = "\u00a7n";
	public static final String ITALIC = "\u00a7o";
	public static final String END = "\u00a7r";
	public static final String[] ROMAN_NUMERAL = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
	public static boolean displayShiftForDetail = true;
	public static boolean displayStackCount = false;

	private StringHelper() {
	}

	public static boolean isAltKeyDown() {
		return Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
	}

	public static boolean isControlKeyDown() {
		return Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
	}

	public static boolean isShiftKeyDown() {
		return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
	}

	public static int getSplitStringHeight(FontRenderer var0, String var1, int var2) {
		List var3 = var0.listFormattedStringToWidth(var1, var2);
		return var3.size() * var0.FONT_HEIGHT;
	}

	public static String camelCase(String var0) {
		return var0.substring(0, 1).toLowerCase() + var0.substring(1);
	}

	public static String titleCase(String var0) {
		return var0.substring(0, 1).toUpperCase() + var0.substring(1);
	}

	public static String localize(String var0) {
		return StatCollector.translateToLocal(var0);
	}

	public static String getFluidName(FluidStack var0) {
		Fluid var1 = var0.getFluid();
		String var2 = "\u00a7r";
		if(var1.getRarity() == EnumRarity.uncommon) {
			var2 = var2 + "\u00a7e";
		} else if(var1.getRarity() == EnumRarity.rare) {
			var2 = var2 + "\u00a7b";
		} else if(var1.getRarity() == EnumRarity.epic) {
			var2 = var2 + "\u00a7d";
		}

		var2 = var2 + var1.getLocalizedName(var0) + "\u00a7r";
		return var2;
	}

	public static String getFluidName(FluidStack var0, String var1) {
		return var0 == null?var1:getFluidName(var0);
	}

	public static String getItemName(ItemStack var0) {
		String var1 = "\u00a7r";
		if(var0.getRarity() == EnumRarity.uncommon) {
			var1 = var1 + "\u00a7e";
		} else if(var0.getRarity() == EnumRarity.rare) {
			var1 = var1 + "\u00a7b";
		} else if(var0.getRarity() == EnumRarity.epic) {
			var1 = var1 + "\u00a7d";
		}

		var1 = var1 + var0.getDisplayName() + "\u00a7r";
		return var1;
	}

	public static String getScaledNumber(long var0) {
		return getScaledNumber(var0, 2);
	}

	public static String getScaledNumber(long var0, int var2) {
		String var3 = "";
		int var4 = 10 ^ var2;
		if(var0 > (long)(100000000 * var4)) {
			var3 = var3 + var0 / 1000000000L + "G";
		} else if(var0 > (long)(100000 * var4)) {
			var3 = var3 + var0 / 1000000L + "M";
		} else if(var0 > (long)(100 * var4)) {
			var3 = var3 + var0 / 1000L + "k";
		} else {
			var3 = var3 + var0;
		}

		return var3;
	}

	public static String getActivationText(String var0) {
		return "\u00a7b" + localize(var0) + "\u00a7r";
	}

	public static String getDeactivationText(String var0) {
		return "\u00a7e" + localize(var0) + "\u00a7r";
	}

	public static String getInfoText(String var0) {
		return "\u00a7a" + localize(var0) + "\u00a7r";
	}

	public static String getFlavorText(String var0) {
		return "\u00a7f\u00a7o" + localize(var0) + "\u00a7r";
	}

	public static String getRarity(int var0) {
		switch(var0) {
			case 2:
				return "\u00a7e";
			case 3:
				return "\u00a7b";
			default:
				return "\u00a77";
		}
	}

	public static String shiftForDetails() {
		return "\u00a77" + localize("info.cofh.hold") + " " + "\u00a7e" + "\u00a7o" + localize("info.cofh.shift") + " " + "\u00a7r" + "\u00a77" + localize("info.cofh.forDetails") + "\u00a7r";
	}

	public static String tutorialTabAugment() {
		return localize("info.cofh.tutorial.tabAugment");
	}

	public static String tutorialTabConfiguration() {
		return localize("info.cofh.tutorial.tabConfiguration.0");
	}

	public static String tutorialTabOperation() {
		return localize("info.cofh.tutorial.tabConfiguration.1");
	}

	public static String tutorialTabRedstone() {
		return localize("info.cofh.tutorial.tabRedstone");
	}

	public static String tutorialTabSecurity() {
		return localize("info.cofh.tutorial.tabSecurity");
	}

	public static String tutorialTabFluxRequired() {
		return localize("info.cofh.tutorial.fluxRequired");
	}
}
