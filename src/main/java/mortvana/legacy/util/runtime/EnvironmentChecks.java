package mortvana.legacy.util.runtime;


import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICrashCallable;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

import mortvana.legacy.common.config.FluxGearConfig;
import mortvana.legacy.common.MortTech;

/**
 * Environment Checks
 *
 * Checks the runtime environment is safe for use. If not, registers warnings and adds a suitable crash callable.
 *
 * Originally in Mantle
 */

public class EnvironmentChecks {

    private EnvironmentChecks() {/*Dem Singleton*/}

    // Usable by other mods to detect Optifine.
    public static boolean hasOptifine = false;

    /**
     * Checks for conflicting stuff in environment; adds callable to any crash logs if so.
     * Note: This code adds additional data to crashlogs. It does not trigger any crashes.
     */

    private static List<String> incompatibilities = new ArrayList<String>();
	private static EnvironmentChecks instance = new EnvironmentChecks();

    public static void verifyEnvironmentSanity () {

        List<String> modIds = new ArrayList<String>();

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT && FMLClientHandler.instance().hasOptifine() || Loader.isModLoaded("optifine")) {
            if (!FluxGearConfig.silenceEnvChecks)
                FluxGearCore.log.error("[Environment Checks] Optifine detected. This may cause issues due to base edits or ASM usage.");
            hasOptifine = true;
            modIds.add("optifine");
        }

	    if (Loader.isModLoaded("gregtech_addon")){
		    MortTech.logger.error("GREGORIOUS NERFBERG AHEAD!!!! TINKER FOR YOUR LIVES!!!!");
		    MortTech.logger.error("MortTech and GregTech are incompatible for the following reasons:");
		    MortTech.logger.error(modCompatDetails("GregTech", true));
		    modIds.add("gregtech_addon");
		    incompatibilities.add("GregTech");
	    }

        try {
            Class cl = Class.forName("org.bukkit.Bukkit");
            if (cl != null)
            {
                if (!FluxGearConfig.silenceEnvChecks)
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

	    if (incompatibilities.size() > 0 && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
		    MinecraftForge.EVENT_BUS.register(instance);
	    }
    }

	public static String modCompatDetails (String type, boolean consoleFormat) {
		String n = consoleFormat ? System.getProperty("line.separator") : "\n";
		if (type.equals("GregTech"))
		{
			return    "- GregTech is a meta-mod that changes how a lot of mods interact with the base game and with each other." + n
					+ "- The mod restructures the registration of various ores within the Ore Dictionary. This may alter or break the original design intention of various other mods." + n
					+ "- This mod alters various fundamental recipes from vanilla Minecraft, ruining balance of all other mods." + n
					+ "- Greg hacks into Forge ModLoader instead of making a Pull Request, damaging both his, and his mod's reputation." + n
					+ "- Greg has the social skills of a 6-year-old, and insults other mods and their authors.";
		}
		return "";
	}
}