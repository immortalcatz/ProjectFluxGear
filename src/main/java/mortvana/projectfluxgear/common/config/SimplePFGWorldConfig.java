package mortvana.projectfluxgear.common.config;

import java.io.File;
import java.io.IOException;

import mortvana.projectfluxgear.common.ProjectFluxGear;
import net.minecraftforge.common.config.Configuration;

public class SimplePFGWorldConfig {

	public static Configuration config;
	public static File configFolder;
	
	public static void loadConfiguration (File mainConfigFolder) {
		ProjectFluxGear.logger.info("Loading configuration from disk.");
		try {
			configFolder = new File(mainConfigFolder.getCanonicalPath(), "Mortvana");
			if (!configFolder.exists())
				configFolder.mkdirs();
		} catch (IOException e) {
			ProjectFluxGear.logger.error("Error getting/creating Thermal Tinkerer configuration directory: " + e.getMessage());
		}
		config = new Configuration(new File(configFolder, ("ProjectFluxGear-World.cfg")));
		config.load();
		
		doesGenerate();
		density();
		minY();
		maxY();
		amount();
		//chance();
		iridiumGen();

		
		config.save();
	}

	public static void doesGenerate() {
		generateChalcocite = config.get("WorldGen Enablers", "Generate Chalcocite (Copper)", true).getBoolean(true);
		generateCassiterite = config.get("WorldGen Enablers", "Generate Cassiterite (Tin)", true).getBoolean(true);
		generateGalena = config.get("WorldGen Enablers", "Generate Galena (Lead)", true).getBoolean(true);
		generateAcanthite = config.get("WorldGen Enablers", "Generate Acanthite (Silver)", true).getBoolean(true);
		generateGarnierite = config.get("WorldGen Enablers", "Generate Garnierite (Nickel)", true).getBoolean(true);
		generateSphalerite = config.get("WorldGen Enablers", "Generate Sphalerite (Zinc)", true).getBoolean(true);
		generateBismuthinite = config.get("WorldGen Enablers", "Generate Bismuthinite (Bismuth)", true).getBoolean(true);
		generatePyrolusite = config.get("WorldGen Enablers", "Generate Pyrolusite (Manganese)", true).getBoolean(true);
		generateBauxite = config.get("WorldGen Enablers", "Generate Bauxite (Aluminium)", true).getBoolean(true);
		generateCooperite = config.get("WorldGen Enablers", "Generate Cooperite (Platinum)", true).getBoolean(true);
		generateBraggite = config.get("WorldGen Enablers", "Generate Braggite (Palladium)", true).getBoolean(true);
		generateMolybdenite = config.get("WorldGen Enablers", "Generate Molybdenite (Molybdenum)", true).getBoolean(true);
		generateCobaltite = config.get("WorldGen Enablers", "Generate Cobaltite (Cobalt)", true).getBoolean(true);
		generateWolframite = config.get("WorldGen Enablers", "Generate Wolframite (Tungsten)", true).getBoolean(true);
		generateIlmenite = config.get("WorldGen Enablers", "Generate Ilmenite (Titanium)", true).getBoolean(true);
		generateChromite = config.get("WorldGen Enablers", "Generate Chromite (Chromium)", true).getBoolean(true);

		generateCinnabar = config.get("WorldGen Enablers", "Generate Cinnabar (Mercury)", true).getBoolean(true);
		generatePitchblende = config.get("WorldGen Enablers", "Generate Pitchblende (Uranium)", true).getBoolean(true);
		generateMonazite = config.get("WorldGen Enablers", "Generate Monazite", true).getBoolean(true);
		generateNiedermayrite = config.get("WorldGen Enablers", "Generate Nierdermayrite", true).getBoolean(true);
		generateGreenockite = config.get("WorldGen Enablers", "Generate Greenockite (Cadmium)", true).getBoolean(true);
		generateGaotaiite = config.get("WorldGen Enablers", "Generate Gaotaiite (Induim)", true).getBoolean(true);
		generateOsarsite = config.get("WorldGen Enablers", "Generate Osarsite (Osmium)", true).getBoolean(true);
		generateGallobeudanite = config.get("WorldGen Enablers", "Generate Gallobeudanite (Gallium)", true).getBoolean(true);
		generateZnamenskyite = config.get("WorldGen Enablers", "Generate Znamenskyite (Indium)", true).getBoolean(true);
		generateTetrahedrite = config.get("WorldGen Enablers", "Generate Tetrahedrite (Animonial Bronze/Copper)", true).getBoolean(true);
		generateTennantite = config.get("WorldGen Enablers", "Generate Tennantite (Arsenical Bronze/Copper)", true).getBoolean(true);
		generateSantafeite = config.get("WorldGen Enablers", "Generate Santfeite (Vanadium)", true).getBoolean(true);
		generateMagnetite = config.get("WorldGen Enablers", "Generate Magnetite", true).getBoolean(true);
		generateDioptase = config.get("WorldGen Enablers", "Generate Dioptase", true).getBoolean(true);
		generatePyrope = config.get("WorldGen Enablers", "Generate Pyrope", true).getBoolean(true);
		generateMyuvil = config.get("WorldGen Enablers", "Generate Myuvil", true).getBoolean(true);
	}

	public static void density() {
		chalcociteDensity = config.get("Ores Per Chunk", "Chalcocite (Copper) per Chunk", 10).getInt(10);
		cassiteriteDensity = config.get("Ores Per Chunk", "Cassiterite (Tin) per Chunk", 8).getInt(8);

	}

	public static void minY() {

	}

	public static void maxY() {

	}

	public static void amount() {

	}

	//Not yet Used
	public static void chance () {}

	public static void iridiumGen() {

	}
	
	// Poor Ore Config Here
	
	public static void entwining() {}
	
	
	public static Configuration getConfig() {
		return config;
	}
	
	public static boolean generateChalcocite;
	public static int chalcociteDensity;
	public static int chalcociteAmount;
	public static int chalcociteMaxY;
	public static int chalcociteMinY;

	public static boolean generateCassiterite;
	public static int cassiteriteDensity;
	public static int cassiteriteAmount;
	public static int cassiteriteMaxY;
	public static int cassiteriteMinY;

	public static boolean generateGalena;
	public static int galenaDensity;
	public static int galenaAmount;
	public static int galenaMaxY;
	public static int galenaMinY;

	public static boolean generateAcanthite;
	public static int acanthiteDensity;
	public static int acanthiteAmount;
	public static int acanthiteMaxY;
	public static int acanthiteMinY;

	public static boolean generateGarnierite;
	public static int garnieriteDensity;
	public static int garnieriteAmount;
	public static int garnieriteMaxY;
	public static int garnieriteMinY;

	public static boolean generateSphalerite;
	public static int sphaleriteDensity;
	public static int sphaleriteAmount;
	public static int sphaleriteMaxY;
	public static int sphaleriteMinY;

	public static boolean generateBismuthinite;
	public static int bismuthiniteDensity;
	public static int bismuthiniteAmount;
	public static int bismuthiniteMaxY;
	public static int bismuthiniteMinY;

	public static boolean generatePyrolusite;
	public static int pyrolusiteDensity;
	public static int pyrolusiteAmount;
	public static int pyrolusiteMaxY;
	public static int pyrolusiteMinY;

	public static boolean generateBauxite;
	public static int bauxiteDensity;
	public static int bauxiteAmount;
	public static int bauxiteMaxY;
	public static int bauxiteMinY;

	public static boolean generateCooperite;
	public static int cooperiteDensity;
	public static int cooperiteAmount;
	public static int cooperiteMaxY;
	public static int cooperiteMinY;

	public static boolean generateBraggite;
	public static int braggiteDensity;
	public static int braggiteAmount;
	public static int braggiteMaxY;
	public static int braggiteMinY;

	public static boolean generateMolybdenite;
	public static int molybdeniteDensity;
	public static int molybdeniteAmount;
	public static int molybdeniteMaxY;
	public static int molybdeniteMinY;

	public static boolean generateCobaltite;
	public static int cobaltiteDensity;
	public static int cobaltiteAmount;
	public static int cobaltiteMaxY;
	public static int cobaltiteMinY;

	public static boolean generateWolframite;
	public static int wolframiteDensity;
	public static int wolframiteAmount;
	public static int wolframiteMaxY;
	public static int wolframiteMinY;

	public static boolean generateIlmenite;
	public static int ilmeniteDensity;
	public static int ilmeniteAmount;
	public static int ilmeniteMaxY;
	public static int ilmeniteMinY;

	public static boolean generateChromite;
	public static int chromiteDensity;
	public static int chromiteAmount;
	public static int chromiteMaxY;
	public static int chromiteMinY;

	public static boolean generateCinnabar;
	public static int cinnabarDensity;
	public static int cinnabarChance;
	public static int cinnabarMaxY;
	public static int cinnabarMinY;

	public static boolean generatePitchblende;
	public static int pitchblendeDensity;
	public static int pitchblendeChance;
	public static int pitchblendeMaxY;
	public static int pitchblendeMinY;

	public static boolean generateMonazite;
	public static int monaziteDensity;
	public static int monaziteChance;
	public static int monaziteMaxY;
	public static int monaziteMinY;

	public static boolean generateNiedermayrite;
	public static int niedermayriteDensity;
	public static int niedermayriteChance;
	public static int niedermayriteMaxY;
	public static int niedermayriteMinY;

	public static boolean generateGreenockite;
	public static int greenockiteDensity;
	public static int greenockiteChance;
	public static int greenockiteMaxY;
	public static int greenockiteMinY;

	public static boolean generateGaotaiite;
	public static int gaotaiiteDensity;
	public static int gaotaiiteChance;
	public static int gaotaiiteMaxY;
	public static int gaotaiiteMinY;

	public static boolean generateOsarsite;
	public static int osarsiteDensity;
	public static int osarsiteChance;
	public static int osarsiteMaxY;
	public static int osarsiteMinY;

	public static boolean generateGallobeudanite;
	public static int gallobeudaniteDensity;
	public static int gallobeudaniteChance;
	public static int gallobeudaniteMaxY;
	public static int gallobeudaniteMinY;

	public static boolean generateZnamenskyite;
	public static int znamenskyiteDensity;
	public static int znamenskyiteChance;
	public static int znamenskyiteMaxY;
	public static int znamenskyiteMinY;

	public static boolean generateTetrahedrite;
	public static int tetrahedriteDensity;
	public static int tetrahedriteChance;
	public static int tetrahedriteMaxY;
	public static int tetrahedriteMinY;

	public static boolean generateTennantite;
	public static int tennantiteDensity;
	public static int tennantiteChance;
	public static int tennantiteMaxY;
	public static int tennantiteMinY;

	public static boolean generateSantafeite;
	public static int santafeiteDensity;
	public static int santafeiteChance;
	public static int santafeiteMaxY;
	public static int santafeiteMinY;

	public static boolean generateMagnetite;
	public static int magnetiteDensity;
	public static int magnetiteChance;
	public static int magnetiteMaxY;
	public static int magnetiteMinY;

	public static boolean generateDioptase;
	public static int dioptaseDensity;
	public static int dioptaseChance;
	public static int dioptaseMaxY;
	public static int dioptaseMinY;

	public static boolean generatePyrope;
	public static int pyropeDensity;
	public static int pyropeChance;
	public static int pyropeMaxY;
	public static int pyropeMinY;

	public static boolean generateMyuvil;
	public static int myuvilDensity;
	public static int myuvilChance;
	public static int myuvilMaxY;
	public static int myuvilMinY;
}
