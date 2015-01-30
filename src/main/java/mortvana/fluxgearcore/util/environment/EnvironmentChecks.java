package mortvana.fluxgearcore.util.environment;


/**
 * Environment Checks
 *
 * Checks the runtime environment is safe for use. If not, registers warnings and adds a suitable crash callable.
 *
 * Originally in Mantle
 */

/*public class EnvironmentChecks {

    private EnvironmentChecks()
    {
    } // Singleton*/

    // Usable by other mods to detect Optifine.
    //public static boolean hasOptifine = false;

    /**
     * Checks for conflicting stuff in environment; adds callable to any crash logs if so.
     * Note: This code adds additional data to crashlogs. It does not trigger any crashes.
     */

    /*public static void verifyEnvironmentSanity () {

        List<String> modIds = new ArrayList<String>();

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT && FMLClientHandler.instance().hasOptifine() || Loader.isModLoaded("optifine")) {
            if (!Config.silenceEnvChecks)
                FluxGearCore.log.error("[Environment Checks] Optifine detected. This may cause issues due to base edits or ASM usage.");
            hasOptifine = true;
            modIds.add("optifine");
        }

        try {
            Class cl = Class.forName("org.bukkit.Bukkit");
            if (cl != null)
            {
                if (!Config.silenceEnvChecks)
                    FluxGearCore.log.error("[Environment Checks] Bukkit implementation detected. This may cause issues. Bukkit implementations include Craftbukkit and Cauldron(MCPC+).");
                modIds.add("bukkit");
            }
        }
        catch (Exception ex) {
            // No Bukkit in environment.
        }

        if (modIds.size() == 0) {
            ICrashCallable callable = new CallableSuppConfig(modId);
            FMLCommonHandler.instance().registerCrashCallable(callable);
        }
        else {
            ICrashCallable callable = new CallableUnsuppConfig(modId, modIds);
            FMLCommonHandler.instance().registerCrashCallable(callable);
        }
    }
}*/