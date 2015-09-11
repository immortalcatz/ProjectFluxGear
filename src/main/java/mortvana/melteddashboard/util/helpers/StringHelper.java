package mortvana.melteddashboard.util.helpers;

@Deprecated
public class StringHelper {

	// TODO: This.
	public static String toTitleCase(String string) {
		return string;
	}

	// TODO: This.
	public static String stripPrefixes(String string) {
		return string;
	}

	public static String generalizeOreDictSting(String string) {
		return '\u1F18' + toTitleCase(stripPrefixes(string));
	}

	public static String createOreDictString(String prefix, String name) {
		return prefix + name.replaceFirst("\u1F18", "");
	}

	public static String createSimpleOreDictString(String prefix, String name) {
		return prefix + toTitleCase(stripPrefixes(name));
	}
}
