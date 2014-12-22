package mortvana.projectfluxgear.common.config;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

import mortvana.projectfluxgear.common.ProjectFluxGear;

public class FluxGearConfigWorld {

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

		config.addCustomCategoryComment(genL, "Enable and Disable which ores spawn.");
		config.addCustomCategoryComment(densL, "Configure the frequency of ore veins in a chunk.");
		config.addCustomCategoryComment(minL, "The lowest Y level an ore will spawn at.");
		config.addCustomCategoryComment(maxL, "The highest Y level an ore will spawn at.");
		config.addCustomCategoryComment(amntL, "How many ores are in a vein.");
		config.addCustomCategoryComment(chncL, "Configure the frequency a chunk will have ores.");
		config.addCustomCategoryComment(irGenL, "Configure miscellaneous world generation.");
		config.addCustomCategoryComment(pooresL, "Stuff relating to Poor Ores. 0 is enabled, 1 is disbled, 2 is automatic");
		config.addCustomCategoryComment(retroL, "Stuff relating to Retroactive World Generation (Retrogen).");

		doesGenerate();
		density();
		minY();
		maxY();
		amount();
		chance();
		pooresGen();
		otherGen();
		retrogenConfig();

		if (config.hasChanged())
			config.save();
	}

	public static void doesGenerate() {
		generateChalcocite = config.get(genL, gen + cu, true).getBoolean(true);
		generateCassiterite = config.get(genL, gen + sn, true).getBoolean(true);
		generateGalena = config.get(genL, gen + pb , true).getBoolean(true);
		generateAcanthite = config.get(genL, gen + ag, true).getBoolean(true);
		generateGarnierite = config.get(genL, gen + ni, true).getBoolean(true);
		generateSphalerite = config.get(genL, gen + zn, true).getBoolean(true);
		generateBismuthinite = config.get(genL, gen + bi, true).getBoolean(true);
		generatePyrolusite = config.get(genL, gen + mn, true).getBoolean(true);
		generateBauxite = config.get(genL, gen + al, true).getBoolean(true);
		generateCooperite = config.get(genL, gen + pt, true).getBoolean(true);
		generateBraggite = config.get(genL, gen + pd, true).getBoolean(true);
		generateMolybdenite = config.get(genL, gen + mo, true).getBoolean(true);
		generateCobaltite = config.get(genL, gen + co, true).getBoolean(true);
		generateWolframite = config.get(genL, gen + w, true).getBoolean(true);
		generateIlmenite = config.get(genL, gen + ti, true).getBoolean(true);
		generateChromite = config.get(genL, gen + cr, true).getBoolean(true);

		generateCinnabar = config.get(genL, gen + hg, true).getBoolean(true);
		generatePitchblende = config.get(genL, gen + u, true).getBoolean(true);
		generateMonazite = config.get(genL, gen + mnz, true).getBoolean(true);
		generateNiedermayrite = config.get(genL, gen + nrd, true).getBoolean(true);
		generateGreenockite = config.get(genL, gen + cad, true).getBoolean(true);
		generateGaotaiite = config.get(genL, gen + tel, true).getBoolean(true);
		generateOsarsite = config.get(genL, gen + os, true).getBoolean(true);
		generateGallobeudanite = config.get(genL, gen + ga, true).getBoolean(true);
		generateZnamenskyite = config.get(genL, gen + ind, true).getBoolean(true);
		generateTetrahedrite = config.get(genL, gen + sbb, true).getBoolean(true);
		generateTennantite = config.get(genL, gen + asb, true).getBoolean(true);
		generateSantafeite = config.get(genL, gen + fe, true).getBoolean(true);
		generateMagnetite = config.get(genL, gen + va, true).getBoolean(true);
		generateDioptase = config.get(genL, gen + dts, true).getBoolean(true);
		generatePyrope = config.get(genL, gen + prp, true).getBoolean(true);
		generateMyuvil = config.get(genL, gen + myv, true).getBoolean(true);
	}

	public static void density() {
		chalcociteDensity = config.get(densL, cu + dens, 10).getInt(10);
		cassiteriteDensity = config.get(densL, sn + dens, 10).getInt(10);
		galenaDensity = config.get(densL, pb + dens, 10).getInt(10);
		acanthiteDensity = config.get(densL, ag + dens, 10).getInt(10);
		garnieriteDensity = config.get(densL, ni + dens, 6).getInt(6);
		sphaleriteDensity = config.get(densL, zn + dens, 8).getInt(8);
		bismuthiniteDensity = config.get(densL, bi + dens, 7).getInt(7);
		pyrolusiteDensity = config.get(densL, mn + dens, 9).getInt(9);
		bauxiteDensity = config.get(densL, al + dens, 5).getInt(5);
		cooperiteDensity = config.get(densL, pt + dens, 2).getInt(2);
		braggiteDensity = config.get(densL, pd + dens, 4).getInt(4);
		molybdeniteDensity = config.get(densL, mo + dens, 6).getInt(6);
		cobaltiteDensity = config.get(densL, co + dens, 6).getInt(6);
		wolframiteDensity = config.get(densL, w + dens, 4).getInt(4);
		ilmeniteDensity = config.get(densL, ti + dens, 4).getInt(4);
		chromiteDensity = config.get(densL, cr + dens, 2).getInt(2);

		cinnabarDensity = config.get(densL, hg + dens, 7).getInt(7);
		pitchblendeDensity = config.get(densL, u + dens, 2).getInt(2);
		monaziteDensity = config.get(densL, mnz + dens, 8).getInt(8);
		niedermayriteDensity = config.get(densL, nrd + dens, 8).getInt(8);
		greenockiteDensity = config.get(densL, cad + dens, 6).getInt(6);
		gaotaiiteDensity = config.get(densL, tel + dens, 32).getInt(32);
		osarsiteDensity = config.get(densL, os + dens, 4).getInt(4);
		gallobeudaniteDensity = config.get(densL, ga + dens, 5).getInt(5);
		znamenskyiteDensity = config.get(densL, ind + dens, 5).getInt(5);
		tetrahedriteDensity = config.get(densL, sbb + dens, 6).getInt(6);
		tennantiteDensity = config.get(densL, asb + dens, 6).getInt(6);
		santafeiteDensity = config.get(densL, va + dens, 4).getInt(4);
		magnetiteDensity = config.get(densL, fe + dens, 8).getInt(8);
		dioptaseDensity = config.get(densL, dts + dens, 4).getInt(4);
		pyropeDensity = config.get(densL, prp + dens, 4).getInt(4);
		myuvilDensity = config.get(densL, myv + dens, 4).getInt(4);
	}

	public static void minY() {}

	public static void maxY() {}

	public static void amount() {}

	//Not yet Used
	public static void chance () {}

	public static void otherGen() {
		generateIridium = config.get(irGenL, gen + ir , true).getBoolean(true);
	}

	public static void pooresGen() {
		//TODO: Figure out how to Enum stuffs
		generatePoorChalcocite = config.get(genL, gen + poor + cu, 2).getInt(2);
		generatePoorCassiterite = config.get(genL, gen + poor + sn, 2).getInt(2);
		generatePoorGalena = config.get(genL, gen + poor + pb, 2).getInt(2);
		generatePoorAcanthite = config.get(genL, gen + poor + ag, 2).getInt(2);
		generatePoorGarnierite = config.get(genL, gen + poor + ni, 2).getInt(2);
		generatePoorSphalerite = config.get(genL, gen + poor + zn, 2).getInt(2);
		generatePoorBismuthinite = config.get(genL, gen + poor + bi, 2).getInt(2);
		generatePoorPyrolusite = config.get(genL, gen + poor + mn, 2).getInt(2);
		generatePoorBauxite = config.get(genL, gen + poor + al, 2).getInt(2);
		generatePoorCooperite = config.get(genL, gen + poor + pt, 2).getInt(2);
		generatePoorBraggite = config.get(genL, gen + poor + pd, 2).getInt(2);
		generatePoorMolybdenite = config.get(genL, gen + poor + mo, 2).getInt(2);
		generatePoorCobaltite = config.get(genL, gen + poor + co, 2).getInt(2);
		generatePoorWolframite = config.get(genL, gen + poor + w, 2).getInt(2);
		generatePoorIlmenite = config.get(genL, gen + poor + ti, 2).getInt(2);
		generatePoorChromite = config.get(genL, gen + poor + cr, 2).getInt(2);

		generatePoorCinnabar = config.get(genL, gen + poor + hg, 2).getInt(2);
		generatePoorPitchblende = config.get(genL, gen + poor + u, 2).getInt(2);
		generatePoorMonazite = config.get(genL, gen + poor + mnz, 2).getInt(2);
		generatePoorNiedermayrite = config.get(genL, gen + poor + nrd, 2).getInt(2);
		generatePoorGreenockite = config.get(genL, gen + poor + cad, 2).getInt(2);
		generatePoorGaotaiite = config.get(genL, gen + poor + tel, 2).getInt(2);
		generatePoorOsarsite = config.get(genL, gen + poor + os, 2).getInt(2);
		generatePoorGallobeudanite = config.get(genL, gen + poor + ga, 2).getInt(2);
		generatePoorZnamenskyite = config.get(genL, gen + poor + ind, 2).getInt(2);
		generatePoorTetrahedrite = config.get(genL, gen + poor + sbb, 2).getInt(2);
		generatePoorTennantite = config.get(genL, gen + poor + asb, 2).getInt(2);
		generatePoorSantafeite = config.get(genL, gen + poor + fe, 2).getInt(2);
		generatePoorMagnetite = config.get(genL, gen + poor + va, 2).getInt(2);
		generatePoorDioptase = config.get(genL, gen + poor + dts, 2).getInt(2);
		generatePoorPyrope = config.get(genL, gen + poor + prp, 2).getInt(2);
		generatePoorMyuvil = config.get(genL, gen + poor + myv, 2).getInt(2);

		generatePoorIridium = config.get(irGenL, gen + poor + ir, 2).getInt(2);
	}

	public static void retrogenConfig() {

	}
	
	public static Configuration getConfig() {
		return config;
	}
	
	public static String cu = "Chalcocite (Copper)";
	public static String sn = "Cassiterite (Tin)";
	public static String pb = "Galena (Lead)";
	public static String ag = "Acanthite (Silver)";
	public static String ni = "Garnierite (Nickel)";
	public static String zn = "Sphalerite (Zinc)";
	public static String bi = "Bismuthinite (Bismuth)";
	public static String mn = "Pyrolusite (Manganese)";
	public static String al = "Bauxite (Aluminium)";
	public static String pt = "Cooperite (Platinum)";
	public static String pd = "Braggite (Palladium)";
	public static String mo = "Molybdenite (Molybdenum)";
	public static String co = "Cobaltite (Cobalt)";
	public static String w = "Wolframite (Tungsten)";
	public static String ti = "Ilmenite (Titanium)";
	public static String cr = "Chromite (Chromium)";

	public static String hg = "Cinnabar (Mercury)";
	public static String u = "Pitchblende (Uranium)";
	public static String mnz = "Monazite";
	public static String nrd = "Nierdermayrite";
	public static String cad = "Greenockite (Cadmium)";
	public static String tel = "Gaotaiite (Tellurium)";
	public static String os = "Osarsite (Osmium)";
	public static String ga = "Gallobeudanite (Gallium)";
	public static String ind = "Znamenskyite (Indium)";
	public static String sbb = "Tetrahedrite (Animonial Bronze/Copper)";
	public static String asb = "Tennantite (Arsenical Bronze/Copper)";
	public static String va = "Santfeite (Vanadium)";
	public static String fe = "Magnetite";
	public static String dts = "Dioptase";
	public static String prp = "Pyrope";
	public static String myv = "Myuvil";

	public static String ir = "Iridium Clay Sands";

	public static String gen = "Generate ";
	public static String dens = " per Chunk";
	public static String miny = "";
	public static String maxy = "";
	public static String amnt = "";
	public static String chnc = "";

	public static String genL = "World Gen Enablers";
	public static String densL = "Ores Per Chunk";
	public static String minL = "";
	public static String maxL = "";
	public static String amntL = "";
	public static String chncL = "";
	public static String irGenL = "Other Generation";
	public static String pooresL = "Poor Ore Enablers";
	public static String retroL = "Retrogen";

	public static String poor = "Poor ";
	
	public static boolean generateChalcocite;
	public static int chalcociteDensity;
	public static int chalcociteAmount;
	public static int chalcociteMaxY;
	public static int chalcociteMinY;
	public static int chalcociteChance;

	public static boolean generateCassiterite;
	public static int cassiteriteDensity;
	public static int cassiteriteAmount;
	public static int cassiteriteMaxY;
	public static int cassiteriteMinY;
	public static int cassiteriteChance;

	public static boolean generateGalena;
	public static int galenaDensity;
	public static int galenaAmount;
	public static int galenaMaxY;
	public static int galenaMinY;
	public static int galenaChance;

	public static boolean generateAcanthite;
	public static int acanthiteDensity;
	public static int acanthiteAmount;
	public static int acanthiteMaxY;
	public static int acanthiteMinY;
	public static int acanthiteChance;

	public static boolean generateGarnierite;
	public static int garnieriteDensity;
	public static int garnieriteAmount;
	public static int garnieriteMaxY;
	public static int garnieriteMinY;
	public static int garnieriteChance;

	public static boolean generateSphalerite;
	public static int sphaleriteDensity;
	public static int sphaleriteAmount;
	public static int sphaleriteMaxY;
	public static int sphaleriteMinY;
	public static int sphaleriteChance;

	public static boolean generateBismuthinite;
	public static int bismuthiniteDensity;
	public static int bismuthiniteAmount;
	public static int bismuthiniteMaxY;
	public static int bismuthiniteMinY;
	public static int bismuthiniteChance;

	public static boolean generatePyrolusite;
	public static int pyrolusiteDensity;
	public static int pyrolusiteAmount;
	public static int pyrolusiteMaxY;
	public static int pyrolusiteMinY;
	public static int pyrolusiteChance;

	public static boolean generateBauxite;
	public static int bauxiteDensity;
	public static int bauxiteAmount;
	public static int bauxiteMaxY;
	public static int bauxiteMinY;
	public static int bauxiteChance;

	public static boolean generateCooperite;
	public static int cooperiteDensity;
	public static int cooperiteAmount;
	public static int cooperiteMaxY;
	public static int cooperiteMinY;
	public static int cooperiteChance;

	public static boolean generateBraggite;
	public static int braggiteDensity;
	public static int braggiteAmount;
	public static int braggiteMaxY;
	public static int braggiteMinY;
	public static int braggiteChance;

	public static boolean generateMolybdenite;
	public static int molybdeniteDensity;
	public static int molybdeniteAmount;
	public static int molybdeniteMaxY;
	public static int molybdeniteMinY;
	public static int molybdeniteChance;

	public static boolean generateCobaltite;
	public static int cobaltiteDensity;
	public static int cobaltiteAmount;
	public static int cobaltiteMaxY;
	public static int cobaltiteMinY;
	public static int cobaltiteChance;

	public static boolean generateWolframite;
	public static int wolframiteDensity;
	public static int wolframiteAmount;
	public static int wolframiteMaxY;
	public static int wolframiteMinY;
	public static int wolframiteChance;

	public static boolean generateIlmenite;
	public static int ilmeniteDensity;
	public static int ilmeniteAmount;
	public static int ilmeniteMaxY;
	public static int ilmeniteMinY;
	public static int ilmeniteChance;

	public static boolean generateChromite;
	public static int chromiteDensity;
	public static int chromiteAmount;
	public static int chromiteMaxY;
	public static int chromiteMinY;
	public static int chromiteChance;

	public static boolean generateCinnabar;
	public static int cinnabarDensity;
	public static int cinnabarAmount;
	public static int cinnabarMaxY;
	public static int cinnabarMinY;
	public static int cinnabarChance;

	public static boolean generatePitchblende;
	public static int pitchblendeDensity;
	public static int pitchblendeAmount;
	public static int pitchblendeMaxY;
	public static int pitchblendeMinY;
	public static int pitchblendeChance;

	public static boolean generateMonazite;
	public static int monaziteDensity;
	public static int monaziteAmount;
	public static int monaziteMaxY;
	public static int monaziteMinY;
	public static int monaziteChance;

	public static boolean generateNiedermayrite;
	public static int niedermayriteDensity;
	public static int niedermayriteAmount;
	public static int niedermayriteMaxY;
	public static int niedermayriteMinY;
	public static int niedermayriteChance;

	public static boolean generateGreenockite;
	public static int greenockiteDensity;
	public static int greenockiteAmount;
	public static int greenockiteMaxY;
	public static int greenockiteMinY;
	public static int greenockiteChance;

	public static boolean generateGaotaiite;
	public static int gaotaiiteDensity;
	public static int gaotaiiteAmount;
	public static int gaotaiiteMaxY;
	public static int gaotaiiteMinY;
	public static int gaotaiiteChance;

	public static boolean generateOsarsite;
	public static int osarsiteDensity;
	public static int osarsiteAmount;
	public static int osarsiteMaxY;
	public static int osarsiteMinY;
	public static int osarsiteChance;

	public static boolean generateGallobeudanite;
	public static int gallobeudaniteDensity;
	public static int gallobeudaniteAmount;
	public static int gallobeudaniteMaxY;
	public static int gallobeudaniteMinY;
	public static int gallobeudaniteChance;

	public static boolean generateZnamenskyite;
	public static int znamenskyiteDensity;
	public static int znamenskyiteAmount;
	public static int znamenskyiteMaxY;
	public static int znamenskyiteMinY;
	public static int znamenskyiteChance;

	public static boolean generateTetrahedrite;
	public static int tetrahedriteDensity;
	public static int tetrahedriteAmount;
	public static int tetrahedriteMaxY;
	public static int tetrahedriteMinY;
	public static int tetrahedriteChance;

	public static boolean generateTennantite;
	public static int tennantiteDensity;
	public static int tennantiteAmount;
	public static int tennantiteMaxY;
	public static int tennantiteMinY;
	public static int tennantiteChance;

	public static boolean generateSantafeite;
	public static int santafeiteDensity;
	public static int santafeiteAmount;
	public static int santafeiteMaxY;
	public static int santafeiteMinY;
	public static int santafeiteChance;

	public static boolean generateMagnetite;
	public static int magnetiteDensity;
	public static int magnetiteAmount;
	public static int magnetiteMaxY;
	public static int magnetiteMinY;
	public static int magnetiteChance;

	public static boolean generateDioptase;
	public static int dioptaseDensity;
	public static int dioptaseAmount;
	public static int dioptaseMaxY;
	public static int dioptaseMinY;
	public static int dioptaseChance;

	public static boolean generatePyrope;
	public static int pyropeDensity;
	public static int pyropeAmount;
	public static int pyropeMaxY;
	public static int pyropeMinY;
	public static int pyropeChance;

	public static boolean generateMyuvil;
	public static int myuvilDensity;
	public static int myuvilAmount;
	public static int myuvilMaxY;
	public static int myuvilMinY;
	public static int myuvilChance;

	public static int generatePoorChalcocite;
	public static int generatePoorCassiterite;
	public static int generatePoorGalena;
	public static int generatePoorAcanthite;
	public static int generatePoorGarnierite;
	public static int generatePoorSphalerite;
	public static int generatePoorBismuthinite;
	public static int generatePoorPyrolusite;
	public static int generatePoorBauxite;
	public static int generatePoorCooperite;
	public static int generatePoorBraggite;
	public static int generatePoorMolybdenite;
	public static int generatePoorCobaltite;
	public static int generatePoorWolframite;
	public static int generatePoorIlmenite;
	public static int generatePoorChromite;

	public static int generatePoorCinnabar;
	public static int generatePoorPitchblende;
	public static int generatePoorMonazite;
	public static int generatePoorNiedermayrite;
	public static int generatePoorGreenockite;
	public static int generatePoorGaotaiite;
	public static int generatePoorOsarsite;
	public static int generatePoorGallobeudanite;
	public static int generatePoorZnamenskyite;
	public static int generatePoorTetrahedrite;
	public static int generatePoorTennantite;
	public static int generatePoorSantafeite;
	public static int generatePoorMagnetite;
	public static int generatePoorDioptase;
	public static int generatePoorPyrope;
	public static int generatePoorMyuvil;
	public static int generatePoorIridium;

	public static boolean generateIridium;
	public static boolean spawnLatexTrees;
	public static boolean spawnLatexBushes;

	public static boolean enableRetrogen;
	public static boolean regenOres;
	public static boolean regenPoores;
	public static boolean regenLatexTrees;
	public static boolean regenLatexBushes;
	public static String regenKey;
}
