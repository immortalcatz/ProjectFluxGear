package mortvana.legacy.errored.apiology;

import cpw.mods.fml.common.registry.LanguageRegistry;
import mortvana.legacy.clean.core.common.CommonProxy;

public class LocalizationManager {
    private enum Locale {
        de_DE("de_DE"),
        en_US("en_US"),
        ru_RU("ru_RU"),
        zh_CN("zh_CN");

        public String locale;

        private Locale(String loc)
        {
            this.locale = loc;
        }
    }

    public static void setupLocalizationInfo() {
        for (Locale l : Locale.values()) {
            LanguageRegistry.instance().loadLocalization(CommonProxy.MORTVANABEES_LOCDIR + l.locale + ".xml", l.locale, true);
        }
    }

    public static String getLocalizedString(String key) {
        String result = LanguageRegistry.instance().getStringLocalization(key);
        if (result.isEmpty()) {
            result = LanguageRegistry.instance().getStringLocalization(key, "en_US");
        }
        return result;
    }

    public static String getLocalizedString(String key, Object ...objects) {
        String result = LanguageRegistry.instance().getStringLocalization(key);
        if (result.isEmpty()) {
            result = LanguageRegistry.instance().getStringLocalization(key, "en_US");
        }
        return String.format(result, objects);
    }
}
