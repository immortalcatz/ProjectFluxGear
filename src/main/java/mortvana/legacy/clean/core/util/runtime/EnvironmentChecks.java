package mortvana.legacy.clean.core.util.runtime;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICrashCallable;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

import mortvana.legacy.clean.core.common.FluxGearConfig;
import mortvana.melteddashboard.common.MeltedDashboardCore;

/**
 * Environment Checks
 *
 * Checks the runtime environment is safe for use. If not, registers warnings and adds a suitable crash callable.
 *
 * Originally in Mantle
 */

public class EnvironmentChecks {

	private static List<String> incompatibilities = new ArrayList<String>();
	private static EnvironmentChecks instance = new EnvironmentChecks();

    // Usable by other mods to detect Optifine.
    public static boolean hasOptifine = false;

	private EnvironmentChecks() {/*Dem Singleton*/}

	/**
     * Checks for conflicting stuff in environment; adds callable to any crash logs if so.
     * Note: This code adds additional data to crashlogs. It does not trigger any crashes.
     */
	public static void verifyEnvironmentSanity () {

        List<String> modIds = new ArrayList<String>();

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT && FMLClientHandler.instance().hasOptifine() || Loader.isModLoaded("optifine")) {
            if (!FluxGearConfig.silenceEnvChecks) {
	            MeltedDashboardCore.logger.error("[Environment Checks] Optifine detected. This may cause issues due to base edits or ASM usage.");
            }
            hasOptifine = true;
            modIds.add("optifine");
        }

		try {
			Class clazz = Class.forName("org.bukkit.Bukkit");
			if (clazz != null) {
				if (!FluxGearConfig.silenceEnvChecks) {
					MeltedDashboardCore.logger.error("[Environment Checks] Bukkit implementation detected. This may cause issues. Bukkit implementations include Craftbukkit and Cauldron(MCPC+).");
				}
				modIds.add("bukkit");
			}
		}
		catch (Exception e) {
			// No Bukkit in environment.
		}

	    if (Loader.isModLoaded("gregtech")){
		    MeltedDashboardCore.logger.error("GREGORIOUS NERFBERG AHEAD!!!! TINKER FOR YOUR LIVES!!!!");
		    MeltedDashboardCore.logger.error("Project Flux Gear and GregTech are incompatible for the following reasons:");
		    MeltedDashboardCore.logger.error(modCompatDetails("GregTech", true));
		    modIds.add("gregtech");
		    incompatibilities.add("GregTech");
	    }



        if (modIds.size() == 0) {
            ICrashCallable callable = new CallableSuppConfig(MeltedDashboardCore.MOD_ID);
            FMLCommonHandler.instance().registerCrashCallable(callable);
        } else {
            ICrashCallable callable = new CallableUnsuppConfig(MeltedDashboardCore.MOD_ID, modIds);
            FMLCommonHandler.instance().registerCrashCallable(callable);
        }

	    if (incompatibilities.size() > 0 && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
		    MinecraftForge.EVENT_BUS.register(instance);
	    }
    }

	public static String modCompatDetails (String type, boolean consoleFormat) {
		String n = consoleFormat ? System.getProperty("line.separator") : "\n";
		if (type.equals("GregTech")) {
			return    "- GregTech is a meta-mod that changes how a lot of mods interact with the base game and with each other." + n
					+ "- The mod restructures the registration of various ores within the Ore Dictionary. This may alter or break the original design intention of various other mods." + n
					+ "- This mod alters various fundamental recipes from vanilla Minecraft, ruining balance of most other mods." + n
					+ "- Greg hacked into Forge ModLoader instead of making a Pull Request, damaging both his, and his mod's reputation." /*+ n
					+ "- Greg has the social skills of a 6-year-old, and insults other mods and their authors."*/;
		}
		return "";
	}
}