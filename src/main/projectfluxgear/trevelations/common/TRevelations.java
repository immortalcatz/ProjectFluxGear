package mortvana.projectfluxgear.trevelations.common;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import mortvana.projectfluxgear.trevelations.client.gui.GuiHandler;
import mortvana.projectfluxgear.trevelations.crafting.ModRecipes;
import mortvana.projectfluxgear.trevelations.research.ModResearch;
import mortvana.projectfluxgear.trevelations.util.TabTRevelations;
import mortvana.projectfluxgear.trevelations.util.wardenic.WardenicChargeEvents;
import mortvana.projectfluxgear.trevelations.util.wardenic.WardenicUpgrades;
import mortvana.projectfluxgear.trevelations.world.ModGen;

@Mod(modid = TRevelations.ModID, name = "Thaumic Revelations", version = "v0.1.0.0", dependencies = "required-after:Thaumcraft")
public class TRevelations {

	public static final String ModID = "trevelations";
	public static CreativeTabs tabTRevelations = new TabTRevelations(TRevelations.ModID);

	@Instance(ModID)
	public static TRevelations instance;

	@SidedProxy(serverSide = "mortvana.trevelations.common.CommonProxy", clientSide = "mortvana.trevelations.client.ClientProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		proxy.initRenderers();
		GuiHandler.init();

		WardenicChargeEvents.init();
		WardenicUpgrades.init();

		ModContent.itemInit();
		ModContent.blockInit();
		ModContent.entityInit();
		ModGen.init();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		ModRecipes.init();
		ModResearch.init();

	}

}
