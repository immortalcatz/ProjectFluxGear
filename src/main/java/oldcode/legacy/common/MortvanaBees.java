package oldcode.legacy.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import forestry.api.apiculture.IBeeRoot;
import oldcode.legacy.common.config.BeeConfig;
import oldcode.legacy.util.BranchBees;
import oldcode.legacy.util.LocalizationManager;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.logging.Logger;

@Mod(
        modid = "mortvanabees",
        name = "Mortvana's Bees",
        acceptedMinecraftVersions = VersionInfo.MCVersion,
        version = VersionInfo.Version,
        dependencies = VersionInfo.Depends)
public class MortvanaBees {

    // Shared mod logger
    public static final Logger logger = Logger.getLogger("MortvanaBees");

    @Mod.Instance("MortvanaBees")
    public static MortvanaBees instance;

    @SidedProxy(clientSide = "mortvana.bees.client.ClientProxy", serverSide = "mortvana.bees.common.CommonProxy")
    public static CommonProxy proxy;

    public static IBeeRoot beeRoot;



    public MortvanaBees(){
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        File config = new File(event.getModConfigurationDirectory() + "/Mortvana/Bees.cfg");
        BeeConfig.init(new Configuration(config));
        BeeConfig.setConfigFolderBase(event.getModConfigurationDirectory());

        // Compatibility Helpers
        //ForestryHelper.preInit();
        //BigReactorsHelper();

        LocalizationManager.setupLocalizationInfo();

        content = new BeeContent();
        content.oreRegistry();

    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent event){
        // Compatibility Helpers
        //ForestryHelper.init();
        //BigReactorsHelper.init();
    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event){
        proxy.registerRenderer();
        BeeManager.ititializeBees();
        VersionInfo.doVersionCheck();
        // get bee root
        //beeRoot = (IBeeRoot) AlleleManager.alleleRegistry.getSpeciesRoot("rootBees");

        // init bee branches
        //branchTechnostatic = new BranchBees();
        //AlleleManager.alleleRegistry.getClassification("family.apidae").addMemberGroup(branchTechnostatic);
    }

    public static BeeContent content;
}
