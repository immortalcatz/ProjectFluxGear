package mortvana.legacy.errored.apiology.common;

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.EnumSet;

import mortvana.melteddashboard.util.helpers.ChatHelper;

import mortvana.legacy.errored.core.common.config.BeeConfig;

public class TickHandlerVersion implements IScheduledTickHandler {

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

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if (sent) {
            return;
        }

        if (modIndex < modVersionInfo.size()) {
            VersionInfo anInfo = modVersionInfo.get(modIndex);

            if ((!BeeConfig.DisableUpdateNotification || anInfo.isCriticalUpdate()) && anInfo.isNewVersionAvailable()) {
                EntityPlayer player = (EntityPlayer) tickData[0];
                ChatHelper.addChatMessage(player, "[" + anInfo.modName + "] A new version is available: " + anInfo.getLatestVersion());
                ChatHelper.addChatMessage(player, anInfo.getVersionDescription()));
            }
            modIndex += 1;
        } else {
            sent = true;
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public EnumSet<TickType> ticks() {
        if (TickHandlerVersion.sent) {
            return EnumSet.noneOf(TickType.class);
        }
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return "mortvanabees.version";
    }

    @Override
    public int nextTickSpacing() {
	    return sent ? 72000 : 200;
    }
}