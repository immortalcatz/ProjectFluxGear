package mortvana.melteddashboard.util.helpers;

import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

public class StringHelper {

	/* Color Coding */
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

	/* Text formatting */
	public static final String OBFUSCATED = "\u00a7k";
	public static final String BOLD = "\u00a7l";
	public static final String STRIKETHROUGH = "\u00a7m";
	public static final String UNDERLINE = "\u00a7n";
	public static final String ITALIC = "\u00a7o";
	public static final String END = "\u00a7r";

	public static final String[] ROMAN_NUMERAL = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
	public static boolean displayShiftForDetails = true;

	public static final String TOOLTIP = "info.fluxgear.tooltip.";
	public static final String TUTORIAL = "info.fluxgear.tutorial";

	private StringHelper() {}

	/** Keyboard Helpers **/
	public static boolean isAltKeyDown() {
		return Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
	}

	public static boolean isControlKeyDown() {
		return Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
	}

	public static boolean isShiftKeyDown() {
		return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
	}

	/** Localizationizing **/
	public static String localize(String string) {
		return StatCollector.translateToLocal(string);
	}

	/** Casing **/
	public static String camelCase(String string) {
		return string.substring(0, 1).toLowerCase() + string.substring(1);
	}

	public static String titleCase(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}



	// TODO: This.
	@Deprecated
	public static String toTitleCase(String string) {
		return string;
	}

	// TODO: This.
	public static String stripPrefixes(String string) {
		String oreDict = string;
		if (oreDict.startsWith("block")) {
			oreDict = oreDict.replaceFirst("block", "");
		} else if (oreDict.startsWith("ore")) {
			oreDict = oreDict.replaceFirst("ore", "");
		} else if (oreDict.startsWith("ingot")) {
			oreDict = oreDict.replaceFirst("ingot", "");
		} else if (oreDict.startsWith("dust")) {
			oreDict = oreDict.replaceFirst("dust", "");
		} else if (oreDict.startsWith("nugget")) {
			oreDict = oreDict.replaceFirst("nugget", "");
		} else if (oreDict.startsWith("log")) {
			oreDict = oreDict.replaceFirst("log", "");
		} else if (oreDict.startsWith("item")) {
			oreDict = oreDict.replaceFirst("item", "");
		} else if (oreDict.startsWith("gem")) {
			oreDict = oreDict.replaceFirst("gem", "");
		} else if (oreDict.startsWith("crystal")) {
			oreDict = oreDict.replaceFirst("crystal", "");
		}

		return oreDict;
	}

	public static String generalizeOreDictSting(String name) {
		return '\u1F18' + toTitleCase(stripPrefixes(name));
	}

	public static String[] generalizeOreDictStingArray(String... names) {
		String[] newNames = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			newNames[i] = generalizeOreDictSting(names[i]);
		}
		return newNames;
	}

	public static String createOreDictString(String prefix, String name) {
		return prefix + name.replaceAll("\u1F18", "");
	}

	public static String[] createOreDictStringArray(String prefix, String... names) {
		String[] newNames = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			newNames[i] = createOreDictString(prefix, names[i]);
		}
		return newNames;
	}

	public static String createSimpleOreDictString(String prefix, String name) {
		return prefix + toTitleCase(stripPrefixes(name));
	}

	public static String getRarity(int rarity) {
		switch (rarity) {
			case 2:
				return "\u00a7e";
			case 3:
				return "\u00a7b";
			default:
				return "\u00a77";
		}
	}


	/** Tooltips **/
	public static String holdShiftForDetails() {
		return LIGHT_GRAY + localize(TOOLTIP + "hold") + " " + TEAL + ITALIC + localize(TOOLTIP + "shift") + " " + END + LIGHT_GRAY + localize(TOOLTIP + "details") + END;
	}

	public static String visDiscount(int discount) {
		return PURPLE + localize("tc.visdicount" + ": " + discount + "%");
	}

	/** Tutorial Tabs **/
	public static String tutorialTabAugment() {
		return localize(TUTORIAL + "tabAugment");
	}

	public static String tutorialTabConfiguration() {
		return localize(TUTORIAL + "tabConfiguration");
	}

	public static String tutorialTabOperation() {
		return localize(TUTORIAL + "tabOperation");
	}

	public static String tutorialTabRedstone() {
		return localize(TUTORIAL + "tabRedstone");
	}

	public static String tutorialTabSecurity() {
		return localize(TUTORIAL + "tabSecurity");
	}

	public static String tutorialTabFluxRequired() {
		return localize(TUTORIAL + "fluxRequired");
	}
}
