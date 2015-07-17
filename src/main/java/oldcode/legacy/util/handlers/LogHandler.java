package oldcode.legacy.util.handlers;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHandler {

    public static final Logger logger = LogManager.getLogger("MortTech");

    public static void log(Level level, String message) {
        FMLLog.getLogger().log(level, "[MortTech] " + message);
    }
}