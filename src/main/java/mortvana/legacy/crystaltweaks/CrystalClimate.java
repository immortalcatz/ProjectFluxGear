package mortvana.legacy.crystaltweaks;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import mortvana.legacy.crystaltweaks.util.TabCrystal;

@Mod(modid = "CrystalClimate", name = "CrystalClimate", version = "Byakuren")
public class CrystalClimate {

	@Instance("CrystalClimate")
	public static CrystalClimate instance;
	public static TabCrystal tab;
	@SidedProxy(clientSide = "crystal.util.CrystalProxyClient", serverSide = "crystal.util.CrystalProxy")
	public static CrystalProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		tab = new TabCrystal("crystalclimate");
		CrystalContent.CreateContent();
		CrystalRecipes.CreateRecipes();
	}
}
