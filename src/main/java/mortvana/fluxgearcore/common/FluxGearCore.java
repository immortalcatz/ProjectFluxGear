package mortvana.fluxgearcore.common;

import java.io.File;
import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import mortvana.fluxgearcore.block.material.MaterialSoilOre;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import mortvana.fluxgearcore.util.environment.EnvironmentChecks;

@Mod(modid = "FluxGearCore", name = "Flux Gear Core", version = "v0.0.1.0", acceptedMinecraftVersions = "1.7.10")
public class FluxGearCore {

    // Here's a way to help avoid frustration:
    // Imagine Mojang only hires dyslexic and 'slow' 10-year-olds that write code only when a bird is actively trying to eat their left eye.
    // It's a reminder that no matter where you look, you're going to find something and exclaim, "What fucking moron wrote this piece of shit!?"
    //     -Skyboy026, November 9th, 2014

    @Instance("FluxGearCore")
    public static FluxGearCore instance;

    @SidedProxy(clientSide = "mortvana.fluxgearcore.client.ClientProxy", serverSide = "mortvana.fluxgearcore.common.Proxy")
    public static Proxy proxy;

    public static Material soilOre = new MaterialSoilOre();

    public FluxGearCore() {
        //EnvironmentChecks.verifyEnvironmentSanity();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
	    configDir = event.getModConfigurationDirectory();
	    FluxGearCoreConfig.loadConfiguration(event.getModConfigurationDirectory());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        FGCContent.registerOreDict();
    }

    /* Utility Code, any public utility goes here*/

    /*General*/
    public static File configDir = null;
    public static Logger log = LogManager.getLogger("FluxGearCore");
    public static Random random = new Random();

    /* Global Constants */
    public static final String DEFAULT_OWNER = "[None]";
    public static final int TIME_CONSTANT = 32;
    public static final int TIME_CONSTANT_HALF = TIME_CONSTANT / 2;
    public static final int TIME_CONSTANT_QUARTER = TIME_CONSTANT / 4;
    public static final int TIME_CONSTANT_EIGHTH = TIME_CONSTANT / 8;
    public static final int RF_PER_EU = 4;

    /* Network */
    public static final int NETWORK_UPDATE_RANGE = 192;

    /* Audio */
    public static float soundVolume = 1.0f;

    /* Options */
    public static boolean enableUpdateNotice = true;
    public static boolean enableDismantleLogging = false;
    public static boolean enableOpSecureAccess = false;
    public static boolean enableOpSecureAccessWarning = true;

    /* Loaded Mod Checker */
    public static boolean isArsMagicaLoaded = Loader.isModLoaded("arsmagica2");
    public static boolean isDartcraftLoaded = Loader.isModLoaded("Dartcraft");
    public static boolean isExtraTiCLoaded = Loader.isModLoaded("ExtraTiC");
    public static boolean isForestryLoaded = Loader.isModLoaded("Forestry");
    public static boolean isGrowthcraftLoaded = Loader.isModLoaded("Growthcraft");
    public static boolean isMaricultureLoaded = Loader.isModLoaded("Mariculture");
    public static boolean isMetallurgyLoaded = Loader.isModLoaded("Metallurgy");
    public static boolean isMFRLoaded = Loader.isModLoaded("MineFactoryReloaded");
    public static boolean isNaturaLoaded = Loader.isModLoaded("Natura");
    public static boolean isNetherOresLoaded = Loader.isModLoaded("NetherOres");
    public static boolean isTinkersLoaded = Loader.isModLoaded("TConstruct");
    public static boolean isThaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
    public static boolean isThermalExpansionLoaded = Loader.isModLoaded("ThermalExpansion");
    public static boolean isMekanismLoaded = Loader.isModLoaded("Mekanism");
    public static boolean isBloodMagicLoaded = Loader.isModLoaded("AWWayofTime");
    public static boolean isTSteelworkLoaded = Loader.isModLoaded("TSteelworks");
    public static boolean isBigReactorsLoaded = Loader.isModLoaded("BigReactors");
    public static boolean isRedstoneArsenalLoaded = Loader.isModLoaded("RedstoneArsenal");
    public static boolean isThermalFoundationLoaded = Loader.isModLoaded("ThermalFoundation");
    public static boolean isExtraBeesLoaded = Loader.isModLoaded("ExtraBees");
    public static boolean isRotaryCraftLoaded = Loader.isModLoaded("RotaryCraft");
    public static boolean isMagicBeesLoaded = Loader.isModLoaded("MagicBees");
    public static boolean isJABBALoaded = Loader.isModLoaded("JABBA");
    public static boolean isProjectRedLoaded = Loader.isModLoaded("ProjRed|Core");
    public static boolean isMagicalCropsLoaded = Loader.isModLoaded("magicalcrops");
    public static boolean isOpenBlocksLoaded = Loader.isModLoaded("OpenBlocks");
    public static boolean isExtraUtilitiesLoaded = Loader.isModLoaded("ExtraUtilities");
    public static boolean isAppliedEnergisticsLoaded = Loader.isModLoaded("appliedenergistics2");
    public static boolean isStevesFactoryLoaded = Loader.isModLoaded("StevesFactoryManager");
    public static boolean isArtificeLoaded = Loader.isModLoaded("Artifice");
    public static boolean isLaserCraftLoaded = Loader.isModLoaded("LaserCraft");
    public static boolean isRailcraftLoaded = Loader.isModLoaded("Railcraft");

    //Planned Stuff
    public static boolean isDragonAPILoaded = Loader.isModLoaded("DragonAPI");
    public static boolean isReactorCraftLoaded = Loader.isModLoaded("ReactorCraft");
    public static boolean isWitcheryLoaded = Loader.isModLoaded("witchery");
    public static boolean isMPSLoaded = Loader.isModLoaded("powersuits");
    public static boolean isFSPLoaded = Loader.isModLoaded("Steamcraft");
    public static boolean isPneumatiCraftLoaded = Loader.isModLoaded("PneumaticCraft");
    public static boolean isOodsModLoaded = Loader.isModLoaded("oodmod");

    //Modules
    public static boolean isGCApples = Loader.isModLoaded("Growthcraft|Apples");
    public static boolean isGCBamboo = Loader.isModLoaded("Growthcraft|Bamboo");
    public static boolean isGCBees = Loader.isModLoaded("Growthcraft|Bees");
    public static boolean isGCGrapes = Loader.isModLoaded("Growthcraft|Grapes");
    public static boolean isGCHops = Loader.isModLoaded("Growthcraft|Hops");
    public static boolean isGCRice = Loader.isModLoaded("Growthcraft|Rice");
    public static boolean isProjectRedExploration = Loader.isModLoaded("ProjRed|Exploration");

    /* OreDict Helper */
    public static boolean isSteelRegistered = !OreDictionary.getOres("ingotSteel").isEmpty();
}