package mortvana.melteddashboard.util.helpers;

public class StringHelper {

	// TODO: This.
	public static String toTitleCase(String string) {
		return string;
	}

	// TODO: This.
	public static String stripPrefixes(String string) {
		return string;
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
