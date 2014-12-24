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
			ProjectFluxGear.logger.error("Error getting/creating Project Flux Gear configuration directory: " + e.getMessage());
		}

		config = new Configuration(new File(configFolder, ("ProjectFluxGear-World.cfg")));
		config.load();

		config.addCustomCategoryComment(genL, "Enable and Disable which ores spawn.");
		config.addCustomCategoryComment(densL, "Configure the frequency of ore veins in a chunk.");
		config.addCustomCategoryComment(minL, "The lowest Y level an ore will spawn at.");
		config.addCustomCategoryComment(maxL, "The highest Y level an ore will spawn at.");
		config.addCustomCategoryComment(amntL, "How many ores are in a vein.");
		config.addCustomCategoryComment(chncL, "Configure the frequency a chunk will have ores. (1/x)");
		config.addCustomCategoryComment(miscGenL, "Configure miscellaneous world generation.");
		config.addCustomCategoryComment(pooresL, "Stuff relating to Poor Ores. 0 is enabled, 1 is disabled, 2 is automatic (Only with Railcraft)");
		config.addCustomCategoryComment(gravGenL, "Stuff relating to Gravel Ores. 0 is enabled, 1 is disabled, 2 is automatic (Only with Tinker's Construct)");
		config.addCustomCategoryComment(gravRareL, "Configure the frequency a chunk will have gravel ores. (1/x)");
		config.addCustomCategoryComment(gravSizeL, "How many gravel ores are in a vein.");
		config.addCustomCategoryComment(retroL, "Stuff relating to \"Retroactive World Generation\" (Retrogen).");

		doesGenerate();
		density();
		minY();
		maxY();
		amount();
		chance();
		pooresGen();
		gravelOresGen();
		gravelOresRarity();
		gravelOresSize();
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

	public static void minY() {

	}

	public static void maxY() {

	}

	public static void amount() {

	}

	//Not yet Used
	public static void chance () {

	}

	//TODO: Figure out how to Enum stuffs
	public static void pooresGen() {
		generatePoorChalcocite = config.get(pooresL, gen + poor + cu, 2).getInt(2);
		generatePoorCassiterite = config.get(pooresL, gen + poor + sn, 2).getInt(2);
		generatePoorGalena = config.get(pooresL, gen + poor + pb, 2).getInt(2);
		generatePoorAcanthite = config.get(pooresL, gen + poor + ag, 2).getInt(2);
		generatePoorGarnierite = config.get(pooresL, gen + poor + ni, 2).getInt(2);
		generatePoorSphalerite = config.get(pooresL, gen + poor + zn, 2).getInt(2);
		generatePoorBismuthinite = config.get(pooresL, gen + poor + bi, 2).getInt(2);
		generatePoorPyrolusite = config.get(pooresL, gen + poor + mn, 2).getInt(2);
		generatePoorBauxite = config.get(pooresL, gen + poor + al, 2).getInt(2);
		generatePoorCooperite = config.get(pooresL, gen + poor + pt, 2).getInt(2);
		generatePoorBraggite = config.get(pooresL, gen + poor + pd, 2).getInt(2);
		generatePoorMolybdenite = config.get(pooresL, gen + poor + mo, 2).getInt(2);
		generatePoorCobaltite = config.get(pooresL, gen + poor + co, 2).getInt(2);
		generatePoorWolframite = config.get(pooresL, gen + poor + w, 2).getInt(2);
		generatePoorIlmenite = config.get(pooresL, gen + poor + ti, 2).getInt(2);
		generatePoorChromite = config.get(pooresL, gen + poor + cr, 2).getInt(2);

		generatePoorCinnabar = config.get(pooresL, gen + poor + hg, 2).getInt(2);
		generatePoorPitchblende = config.get(pooresL, gen + poor + u, 2).getInt(2);
		generatePoorMonazite = config.get(pooresL, gen + poor + mnz, 2).getInt(2);
		generatePoorNiedermayrite = config.get(pooresL, gen + poor + nrd, 2).getInt(2);
		generatePoorGreenockite = config.get(pooresL, gen + poor + cad, 2).getInt(2);
		generatePoorGaotaiite = config.get(pooresL, gen + poor + tel, 2).getInt(2);
		generatePoorOsarsite = config.get(pooresL, gen + poor + os, 2).getInt(2);
		generatePoorGallobeudanite = config.get(pooresL, gen + poor + ga, 2).getInt(2);
		generatePoorZnamenskyite = config.get(pooresL, gen + poor + ind, 2).getInt(2);
		generatePoorTetrahedrite = config.get(pooresL, gen + poor + sbb, 2).getInt(2);
		generatePoorTennantite = config.get(pooresL, gen + poor + asb, 2).getInt(2);
		generatePoorSantafeite = config.get(pooresL, gen + poor + fe, 2).getInt(2);
		generatePoorMagnetite = config.get(pooresL, gen + poor + va, 2).getInt(2);
		generatePoorDioptase = config.get(pooresL, gen + poor + dts, 2).getInt(2);
		generatePoorPyrope = config.get(pooresL, gen + poor + prp, 2).getInt(2);
		generatePoorMyuvil = config.get(pooresL, gen + poor + myv, 2).getInt(2);
	}

	public static void gravelOresGen() {

		generateGravelChalcocite = config.get(gravGenL, gen + gravel + cu, 2).getInt(2);
		generateGravelCassiterite = config.get(gravGenL, gen + gravel + sn, 2).getInt(2);
		generateGravelGalena = config.get(gravGenL, gen + gravel + pb, 2).getInt(2);
		generateGravelAcanthite = config.get(gravGenL, gen + gravel + ag, 2).getInt(2);
		generateGravelGarnierite = config.get(gravGenL, gen + gravel + ni, 2).getInt(2);
		generateGravelSphalerite = config.get(gravGenL, gen + gravel + zn, 2).getInt(2);
		generateGravelBismuthinite = config.get(gravGenL, gen + gravel + bi, 2).getInt(2);
		generateGravelPyrolusite = config.get(gravGenL, gen + gravel + mn, 2).getInt(2);
		generateGravelBauxite = config.get(gravGenL, gen + gravel + al, 2).getInt(2);
		generateGravelCooperite = config.get(gravGenL, gen + gravel + pt, 2).getInt(2);
		generateGravelBraggite = config.get(gravGenL, gen + gravel + pd, 2).getInt(2);
		generateGravelMolybdenite = config.get(gravGenL, gen + gravel + mo, 2).getInt(2);
		generateGravelCobaltite = config.get(gravGenL, gen + gravel + co, 2).getInt(2);
		generateGravelWolframite = config.get(gravGenL, gen + gravel + w, 2).getInt(2);
		generateGravelIlmenite = config.get(gravGenL, gen + gravel + ti, 2).getInt(2);
		generateGravelChromite = config.get(gravGenL, gen + gravel + cr, 2).getInt(2);

		generateGravelCinnabar = config.get(gravGenL, gen + gravel + hg, 2).getInt(2);
		generateGravelPitchblende = config.get(gravGenL, gen + gravel + u, 2).getInt(2);
		generateGravelMonazite = config.get(gravGenL, gen + gravel + mnz, 2).getInt(2);
		generateGravelNiedermayrite = config.get(gravGenL, gen + gravel + nrd, 2).getInt(2);
		generateGravelGreenockite = config.get(gravGenL, gen + gravel + cad, 2).getInt(2);
		generateGravelGaotaiite = config.get(gravGenL, gen + gravel + tel, 2).getInt(2);
		generateGravelOsarsite = config.get(gravGenL, gen + gravel + os, 2).getInt(2);
		generateGravelGallobeudanite = config.get(gravGenL, gen + gravel + ga, 2).getInt(2);
		generateGravelZnamenskyite = config.get(gravGenL, gen + gravel + ind, 2).getInt(2);
		generateGravelTetrahedrite = config.get(gravGenL, gen + gravel + sbb, 2).getInt(2);
		generateGravelTennantite = config.get(gravGenL, gen + gravel + asb, 2).getInt(2);
		generateGravelSantafeite = config.get(gravGenL, gen + gravel + fe, 2).getInt(2);
		generateGravelMagnetite = config.get(gravGenL, gen + gravel + va, 2).getInt(2);
		generateGravelDioptase = config.get(gravGenL, gen + gravel + dts, 2).getInt(2);
		generateGravelPyrope = config.get(gravGenL, gen + gravel + prp, 2).getInt(2);
		generateGravelMyuvil = config.get(gravGenL, gen + gravel + myv, 2).getInt(2);
	}

	public static void gravelOresRarity() {
		//   50: As common as Aluminium
		//  100: As common as Copper/Tin
		//  400: As common as Iron
		//  900: As common as Gold
		// 2000: As common as Brobalt
		gravelChalcociteRarity = config.get(gravRareL, gravel + cu + rarity, 75).getInt(75);
		gravelCassiteriteRarity = config.get(gravRareL, gravel + sn + rarity, 75).getInt(75);
		gravelGalenaRarity = config.get(gravRareL, gravel + pb + rarity, 450).getInt(450);
		gravelAcanthiteRarity = config.get(gravRareL, gravel + ag + rarity, 475).getInt(475);
		gravelGarnieriteRarity = config.get(gravRareL, gravel + ni + rarity, 550).getInt(550);
		gravelSphaleriteRarity = config.get(gravRareL, gravel + zn + rarity, 225).getInt(225);
		gravelBismuthiniteRarity = config.get(gravRareL, gravel + bi + rarity, 325).getInt(325);
		gravelPyrolusiteRarity = config.get(gravRareL, gravel + mn + rarity, 275).getInt(275);
		gravelBauxiteRarity = config.get(gravRareL, gravel + al + rarity, 75).getInt(75);
		gravelCooperiteRarity = config.get(gravRareL, gravel + pt + rarity, 2350).getInt(2350);
		gravelBraggiteRarity = config.get(gravRareL, gravel + pd + rarity, 1325).getInt(1325);
		gravelMolybdeniteRarity = config.get(gravRareL, gravel + mo + rarity, 725).getInt(725);
		gravelCobaltiteRarity = config.get(gravRareL, gravel + co + rarity, 1725).getInt(1725);
		gravelWolframiteRarity = config.get(gravRareL, gravel + w + rarity, 1450).getInt(1450);
		gravelIlmeniteRarity = config.get(gravRareL, gravel + ti + rarity, 1475).getInt(1475);
		gravelChromiteRarity = config.get(gravRareL, gravel + cr + rarity, 1425).getInt(1425);

		gravelCinnabarRarity = config.get(gravRareL, gravel + hg + rarity, 825).getInt(825);
		gravelPitchblendeRarity = config.get(gravRareL, gravel + u + rarity, 1250).getInt(1250);
		gravelMonaziteRarity = config.get(gravRareL, gravel + mnz + rarity, 1325).getInt(1325);
		gravelNiedermayriteRarity = config.get(gravRareL, gravel + nrd + rarity, 1325).getInt(1325);
		gravelGreenockiteRarity = config.get(gravRareL, gravel + cad + rarity, 1625).getInt(1625);
		gravelGaotaiiteRarity = config.get(gravRareL, gravel + tel + rarity, 1725).getInt(1725);
		gravelOsarsiteRarity = config.get(gravRareL, gravel + os + rarity, 1675).getInt(1675);
		gravelGallobeudaniteRarity = config.get(gravRareL, gravel + ga + rarity, 675).getInt(675);
		gravelZnamenskyiteRarity = config.get(gravRareL, gravel + ind + rarity, 700).getInt(700);
		gravelTetrahedriteRarity = config.get(gravRareL, gravel + sbb + rarity, 125).getInt(125);
		gravelTennantiteRarity = config.get(gravRareL, gravel + asb + rarity, 125).getInt(125);
		gravelSantafeiteRarity = config.get(gravRareL, gravel + fe + rarity, 525).getInt(525);
		gravelMagnetiteRarity = config.get(gravRareL, gravel + va + rarity, 450).getInt(450);
		gravelDioptaseRarity = config.get(gravRareL, gravel + dts + rarity, 2700).getInt(2700);
		gravelPyropeRarity = config.get(gravRareL, gravel + prp + rarity, 2700).getInt(2700);
		gravelMyuvilRarity = config.get(gravRareL, gravel + myv + rarity, 2950).getInt(2950);
	}

	public static void gravelOresSize() {
		gravelChalcociteSize = config.get(gravSizeL, gravel + cu + size, 12).getInt(12);
		gravelCassiteriteSize = config.get(gravSizeL, gravel + sn + size, 12).getInt(12);
		gravelGalenaSize = config.get(gravSizeL, gravel + pb + size, 16).getInt(16);
		gravelAcanthiteSize = config.get(gravSizeL, gravel + ag + size, 16).getInt(16);
		gravelGarnieriteSize = config.get(gravSizeL, gravel + ni + size, 12).getInt(12);
		gravelSphaleriteSize = config.get(gravSizeL, gravel + zn + size, 12).getInt(12);
		gravelBismuthiniteSize = config.get(gravSizeL, gravel + bi + size, 12).getInt(12);
		gravelPyrolusiteSize = config.get(gravSizeL, gravel + mn + size, 12).getInt(12);
		gravelBauxiteSize = config.get(gravSizeL, gravel + al + size, 16).getInt(16);
		gravelCooperiteSize = config.get(gravSizeL, gravel + pt + size, 24).getInt(24);
		gravelBraggiteSize = config.get(gravSizeL, gravel + pd + size, 16).getInt(16);
		gravelMolybdeniteSize = config.get(gravSizeL, gravel + mo + size, 14).getInt(14);
		gravelCobaltiteSize = config.get(gravSizeL, gravel + co + size, 14).getInt(14);
		gravelWolframiteSize = config.get(gravSizeL, gravel + w + size, 17).getInt(17);
		gravelIlmeniteSize = config.get(gravSizeL, gravel + ti + size, 17).getInt(17);
		gravelChromiteSize = config.get(gravSizeL, gravel + cr + size, 17).getInt(17);

		gravelCinnabarSize = config.get(gravSizeL, gravel + hg + size, 14).getInt(14);
		gravelPitchblendeSize = config.get(gravSizeL, gravel + u + size, 18).getInt(18);
		gravelMonaziteSize = config.get(gravSizeL, gravel + mnz + size, 15).getInt(15);
		gravelNiedermayriteSize = config.get(gravSizeL, gravel + nrd + size, 15).getInt(15);
		gravelGreenockiteSize = config.get(gravSizeL, gravel + cad + size, 13).getInt(13);
		gravelGaotaiiteSize = config.get(gravSizeL, gravel + tel + size, 13).getInt(13);
		gravelOsarsiteSize = config.get(gravSizeL, gravel + os + size, 14).getInt(14);
		gravelGallobeudaniteSize = config.get(gravSizeL, gravel + ga + size, 12).getInt(12);
		gravelZnamenskyiteSize = config.get(gravSizeL, gravel + ind + size, 12).getInt(12);
		gravelTetrahedriteSize = config.get(gravSizeL, gravel + sbb + size, 10).getInt(10);
		gravelTennantiteSize = config.get(gravSizeL, gravel + asb + size, 10).getInt(10);
		gravelSantafeiteSize = config.get(gravSizeL, gravel + fe + size, 11).getInt(11);
		gravelMagnetiteSize = config.get(gravSizeL, gravel + va + size, 20).getInt(20);
		gravelDioptaseSize = config.get(gravSizeL, gravel + dts + size, 27).getInt(27);
		gravelPyropeSize = config.get(gravSizeL, gravel + prp + size, 27).getInt(27);
		gravelMyuvilSize = config.get(gravSizeL, gravel + myv + size, 32).getInt(32);
	}

	public static void otherGen() {
		generateIridium = config.get(miscGenL, gen + ir , true).getBoolean(true);
		generatePoorIridium = config.get(pooresL, gen + poor + ir, 2).getInt(2);
		spawnParaTrees = config.get(miscGenL, gen + "Pará Rubber Trees in the world", true).getBoolean(true);
		spawnGuayuleBushes = config.get(miscGenL, gen + "Guayule Latex Bushes in the world", true).getBoolean(true);
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
	public static String rarity = " Rarity";
	public static String size = " Ores per vein";

	public static String genL = "World Gen Enablers";
	public static String densL = "Ores Per Chunk";
	public static String minL = "";
	public static String maxL = "";
	public static String amntL = "";
	public static String chncL = "";
	public static String miscGenL = "Other Generation";
	public static String pooresL = "Poor Ore Enablers";
	public static String gravGenL = "Gravel Ore Enablers";
	public static String gravRareL = "Gravel Ore Rarities";
	public static String gravSizeL = "Gravel Ores per Vein";
	public static String retroL = "Retrogen";

	public static String poor = "Poor ";
	public static String gravel = "Gravel ";
	
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

	public static int generateGravelChalcocite;
	public static int generateGravelCassiterite;
	public static int generateGravelGalena;
	public static int generateGravelAcanthite;
	public static int generateGravelGarnierite;
	public static int generateGravelSphalerite;
	public static int generateGravelBismuthinite;
	public static int generateGravelPyrolusite;
	public static int generateGravelBauxite;
	public static int generateGravelCooperite;
	public static int generateGravelBraggite;
	public static int generateGravelMolybdenite;
	public static int generateGravelCobaltite;
	public static int generateGravelWolframite;
	public static int generateGravelIlmenite;
	public static int generateGravelChromite;

	public static int generateGravelCinnabar;
	public static int generateGravelPitchblende;
	public static int generateGravelMonazite;
	public static int generateGravelNiedermayrite;
	public static int generateGravelGreenockite;
	public static int generateGravelGaotaiite;
	public static int generateGravelOsarsite;
	public static int generateGravelGallobeudanite;
	public static int generateGravelZnamenskyite;
	public static int generateGravelTetrahedrite;
	public static int generateGravelTennantite;
	public static int generateGravelSantafeite;
	public static int generateGravelMagnetite;
	public static int generateGravelDioptase;
	public static int generateGravelPyrope;
	public static int generateGravelMyuvil;

	public static int gravelChalcociteRarity;
	public static int gravelCassiteriteRarity;
	public static int gravelGalenaRarity;
	public static int gravelAcanthiteRarity;
	public static int gravelGarnieriteRarity;
	public static int gravelSphaleriteRarity;
	public static int gravelBismuthiniteRarity;
	public static int gravelPyrolusiteRarity;
	public static int gravelBauxiteRarity;
	public static int gravelCooperiteRarity;
	public static int gravelBraggiteRarity;
	public static int gravelMolybdeniteRarity;
	public static int gravelCobaltiteRarity;
	public static int gravelWolframiteRarity;
	public static int gravelIlmeniteRarity;
	public static int gravelChromiteRarity;

	public static int gravelCinnabarRarity;
	public static int gravelPitchblendeRarity;
	public static int gravelMonaziteRarity;
	public static int gravelNiedermayriteRarity;
	public static int gravelGreenockiteRarity;
	public static int gravelGaotaiiteRarity;
	public static int gravelOsarsiteRarity;
	public static int gravelGallobeudaniteRarity;
	public static int gravelZnamenskyiteRarity;
	public static int gravelTetrahedriteRarity;
	public static int gravelTennantiteRarity;
	public static int gravelSantafeiteRarity;
	public static int gravelMagnetiteRarity;
	public static int gravelDioptaseRarity;
	public static int gravelPyropeRarity;
	public static int gravelMyuvilRarity;

	public static int gravelChalcociteSize;
	public static int gravelCassiteriteSize;
	public static int gravelGalenaSize;
	public static int gravelAcanthiteSize;
	public static int gravelGarnieriteSize;
	public static int gravelSphaleriteSize;
	public static int gravelBismuthiniteSize;
	public static int gravelPyrolusiteSize;
	public static int gravelBauxiteSize;
	public static int gravelCooperiteSize;
	public static int gravelBraggiteSize;
	public static int gravelMolybdeniteSize;
	public static int gravelCobaltiteSize;
	public static int gravelWolframiteSize;
	public static int gravelIlmeniteSize;
	public static int gravelChromiteSize;

	public static int gravelCinnabarSize;
	public static int gravelPitchblendeSize;
	public static int gravelMonaziteSize;
	public static int gravelNiedermayriteSize;
	public static int gravelGreenockiteSize;
	public static int gravelGaotaiiteSize;
	public static int gravelOsarsiteSize;
	public static int gravelGallobeudaniteSize;
	public static int gravelZnamenskyiteSize;
	public static int gravelTetrahedriteSize;
	public static int gravelTennantiteSize;
	public static int gravelSantafeiteSize;
	public static int gravelMagnetiteSize;
	public static int gravelDioptaseSize;
	public static int gravelPyropeSize;
	public static int gravelMyuvilSize;

	public static boolean generateIridium;
	public static int generatePoorIridium;
	public static boolean spawnParaTrees;
	public static boolean spawnGuayuleBushes;

	public static boolean enableRetrogen;
	public static boolean regenOres;
	public static boolean regenPoores;
	public static boolean regenParaTrees;
	public static boolean regenGuayuleBushes;
	public static String regenKey;
}
