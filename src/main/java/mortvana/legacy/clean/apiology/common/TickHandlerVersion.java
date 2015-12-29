package mortvana.legacy.clean.apiology.common;

import java.util.ArrayList;

public class TickHandlerVersion {

    public static TickHandlerVersion instance = new TickHandlerVersion();

    private static ArrayList<VersionInfo> modVersionInfo = new ArrayList<VersionInfo>();
    private static boolean initialized;
    private static boolean sent;
    private static int modIndex = 0;

    public static boolean initialize() {
        if (initialized) {
            return false;
        }
        initialized = true;
        return true;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static boolean registerModVersionInfo(VersionInfo info) {
        if (modVersionInfo.contains(info)) {
            return false;
        }
        modVersionInfo.add(info);
        return true;
    }

    /*@SubscribeEvent
    public void tickStart(TickEvent event) {
        if (sent) {
            return;
        }

        if (modIndex < modVersionInfo.size()) {
            VersionInfo anInfo = modVersionInfo.get(modIndex);

            if ((!BeeConfig.DisableUpdateNotification || anInfo.isCriticalUpdate()) && anInfo.isNewVersionAvailable()) {
                EntityPlayer player = (EntityPlayer) tickData[0];
                ChatHelper.addChatMessage(player, "[" + anInfo.modName + "] A new version is available: " + anInfo.getLatestVersion());
                ChatHelper.addChatMessage(player, anInfo.getVersionDescription());
            }
            modIndex += 1;
        } else {
            sent = true;
        }
    }*/

    /*@Override
    public EnumSet<TickType> ticks() {
        if (TickHandlerVersion.sent) {
            return EnumSet.noneOf(TickType.class);
        }
        return EnumSet.of(TickType.PLAYER);
    }*/

    /*@Override
    public String getLabel() {
        return "mortvanabees.version";
    }*/

    /*@Override
    public int nextTickSpacing() {
	    return sent ? 72000 : 200;
    }*/
}