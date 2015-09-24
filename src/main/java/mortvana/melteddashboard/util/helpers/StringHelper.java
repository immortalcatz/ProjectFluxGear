package mortvana.melteddashboard.util.helpers;

public class StringHelper {

	// TODO: This.
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
}
