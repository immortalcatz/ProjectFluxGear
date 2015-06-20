package oldcode.projectfluxgear;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;

import mortvana.projectfluxgear.util.helpers.LoadedHelper;

public class PoorOreGenerator {

	public PoorOreGenerator() {}

	public static void registerOres() {
		if (FluxGearConfigWorld.generatePoorChalcocite == 0 || (FluxGearConfigWorld.generatePoorChalcocite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorChalcociteGen());
		}
		if (FluxGearConfigWorld.generatePoorCassiterite == 0 || (FluxGearConfigWorld.generatePoorCassiterite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorCassiteriteGen());
		}
		if (FluxGearConfigWorld.generatePoorGalena == 0 || (FluxGearConfigWorld.generatePoorGalena == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorGalenaGen());
		}
		if (FluxGearConfigWorld.generatePoorAcanthite == 0 || (FluxGearConfigWorld.generatePoorAcanthite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorAcanthiteGen());
		}
		if (FluxGearConfigWorld.generatePoorGarnierite == 0 || (FluxGearConfigWorld.generatePoorGarnierite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorGarnieriteGen());
		}
		if (FluxGearConfigWorld.generatePoorSphalerite == 0 || (FluxGearConfigWorld.generatePoorSphalerite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorSphaleriteGen());
		}
		if (FluxGearConfigWorld.generatePoorBismuthinite == 0 || (FluxGearConfigWorld.generatePoorBismuthinite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorBismuthiniteGen());
		}
		if (FluxGearConfigWorld.generatePoorPyrolusite == 0 || (FluxGearConfigWorld.generatePoorPyrolusite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorPyrolusiteGen());
		}
		if (FluxGearConfigWorld.generatePoorBauxite == 0 || (FluxGearConfigWorld.generatePoorBauxite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorBauxiteGen());
		}
		if (FluxGearConfigWorld.generatePoorCooperite == 0 || (FluxGearConfigWorld.generatePoorCooperite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorCooperiteGen());
		}
		if (FluxGearConfigWorld.generatePoorBraggite == 0 || (FluxGearConfigWorld.generatePoorBraggite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorBraggriteGen());
		}
		if (FluxGearConfigWorld.generatePoorMolybdenite == 0 || (FluxGearConfigWorld.generatePoorMolybdenite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorMolybdeniteGen());
		}
		if (FluxGearConfigWorld.generatePoorCobaltite == 0 || (FluxGearConfigWorld.generatePoorCobaltite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorCobaltiteGen());
		}
		if (FluxGearConfigWorld.generatePoorWolframite == 0 || (FluxGearConfigWorld.generatePoorWolframite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorWolframiteGen());
		}
		if (FluxGearConfigWorld.generatePoorIlmenite == 0 || (FluxGearConfigWorld.generatePoorIlmenite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorIlmeniteGen());
		}
		if (FluxGearConfigWorld.generatePoorChromite == 0 || (FluxGearConfigWorld.generatePoorChromite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorChromiteGen());
		}
		if (FluxGearConfigWorld.generatePoorCinnabar == 0 || (FluxGearConfigWorld.generatePoorCinnabar == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorCinnabarGen());
		}
		if (FluxGearConfigWorld.generatePoorPitchblende == 0 || (FluxGearConfigWorld.generatePoorPitchblende == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorPitchblendeGen());
		}
		if (FluxGearConfigWorld.generatePoorMonazite == 0 || (FluxGearConfigWorld.generatePoorMonazite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorMonaziteGen());
		}
		if (FluxGearConfigWorld.generatePoorNiedermayrite == 0 || (FluxGearConfigWorld.generatePoorNiedermayrite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorNiedermayriteGen());
		}
		if (FluxGearConfigWorld.generatePoorGreenockite == 0 || (FluxGearConfigWorld.generatePoorGreenockite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))) {
			MinecraftForge.ORE_GEN_BUS.register(new PoorGreenockiteGen());
		}
		if (FluxGearConfigWorld.generatePoorGaotaiite == 0 || (FluxGearConfigWorld.generatePoorGaotaiite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorGaotaiiteGen());
		}
		if (FluxGearConfigWorld.generatePoorOsarsite == 0 || (FluxGearConfigWorld.generatePoorOsarsite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorOsarsiteGen());
		}
		if (FluxGearConfigWorld.generatePoorZnamenskyite == 0 || (FluxGearConfigWorld.generatePoorZnamenskyite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorZnamenskyiteGen());
		}
		if (FluxGearConfigWorld.generatePoorGallobeudanite == 0 || (FluxGearConfigWorld.generatePoorGallobeudanite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorGallobeudaniteGen());
		}
		if (FluxGearConfigWorld.generatePoorTetrahedrite == 0 || (FluxGearConfigWorld.generatePoorTetrahedrite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorTetrahediteGen());
		}
		if (FluxGearConfigWorld.generatePoorTennantite == 0 || (FluxGearConfigWorld.generatePoorTennantite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorTennantiteGen());
		}
		if (FluxGearConfigWorld.generatePoorSantafeite == 0 || (FluxGearConfigWorld.generatePoorSantafeite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorSantafeiteGen());
		}
		if (FluxGearConfigWorld.generatePoorMagnetite == 0 || (FluxGearConfigWorld.generatePoorMagnetite == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorMagnetiteGen());
		}
		if (FluxGearConfigWorld.generatePoorDioptase == 0 || (FluxGearConfigWorld.generatePoorDioptase == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorDioptaseGen());
		}
		//if (FluxGearConfigWorld.generatePoorPyrope == 0 || (FluxGearConfigWorld.generatePoorPyrope == 2 && FluxGearCore.isRailcraftLoaded || ProjectFluxGear.debugWorldGen)){
		//    MinecraftForge.ORE_GEN_BUS.register(new PoorPyropeGen());
		//}
		if (FluxGearConfigWorld.generatePoorMyuvil == 0 || (FluxGearConfigWorld.generatePoorMyuvil == 2 && (LoadedHelper.isRailcraftLoaded || ProjectFluxGear.debugWorldGen))){
			MinecraftForge.ORE_GEN_BUS.register(new PoorMyuvilGen());
		}
	}
	
	public static EventType theEvent; //"Is it true they feast on human flesh?" "Yes." *Cuts from 'The Quiz Broadcast'*
	public static final String[] NAMES = { "CHALCOCITE", "CASSITERITE", "GALENA", "ACANTHITE", "GARNIERITE", "SPHALERITE", "BISMUTHINITE", "PYROLUSITE", "BAUXITE", "COOPERITE", "BRAGGITE", "MOLYBDENITE", "COBALTITE", "WOLFRAMITE", "ILMENITE", "CHROMITE", "CINNABAR", "PITCHBLENDE", "MONAZITE", "NIEDERMAYRITE", "GREENOCKITE", "GAOTAIITE", "OSARSITE", "ZNAMENSKYITE", "GALLOBEUDANITE", "TETRAHEDRITE", "TENNANTITE", "SANTAFEITE", "MAGNETITE", "DIOPTASE", "PYROPE", "MYUVIL" };
	public static final int[] Y_LEVEL = { 60, 50, 30, 25, 35, 55, 45, 55, 45, 15, 25, 40, 35, 15, 20, 20, 55, 20, 35, 25, 40, 30, 35, 30, 25, 50, 45, 20, 55, 10, 95, 10 };
	public static final int[] Y_RANGE = { 5, 4, 3, 2, 3, 4, 3, 4, 3, 1, 2, 3, 2, 2, 2, 2, 3, 4, 3, 2, 3, 2, 2, 3, 3, 4, 4, 3, 4, 2, 35, 2 };
	public static final int[] DENSITY = { 16, 12, 8, 7, 7, 9, 9, 9, 10, 2, 8, 6, 6, 2, 2, 2, 6, 12, 6, 6, 6, 8, 8, 8, 8, 7, 7, 6, 8, 4, 4, 2 };
	public static final int[] SEED = { 682, 526, 261, 334, 706, 324, 282, 887, 712, 175, 803, 568, 767, 297, 740, 910, 720, 187, 633, 741, 934, 647, 218, 554, 505, 948, 499, 822, 291, 798, 274, 946 }; //THAT'S NUMBERWANG!

	public static final Block MAIN = FluxGearContent_.blockPoorOreMain;
	public static final Block AUX = FluxGearContent_.blockPoorOreAux;

	public static class PoorChalcociteGen extends PoorOreGenerator_ {
		public static final int ID = 0;
		public PoorChalcociteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorCassiteriteGen extends PoorOreGenerator_ {
		public static final int ID = 1;
		public PoorCassiteriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGalenaGen extends PoorOreGenerator_ {
		public static final int ID = 2;
		public PoorGalenaGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorAcanthiteGen extends PoorOreGenerator_ {
		public static final int ID = 3;
		public PoorAcanthiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGarnieriteGen extends PoorOreGenerator_ {
		public static final int ID = 4;
		public PoorGarnieriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorSphaleriteGen extends PoorOreGenerator_ {
		public static final int ID = 5;
		public PoorSphaleriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorBismuthiniteGen extends PoorOreGenerator_ {
		public static final int ID = 6;
		public PoorBismuthiniteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorPyrolusiteGen extends PoorOreGenerator_ {
		public static final int ID = 7;
		public PoorPyrolusiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorBauxiteGen extends PoorOreGenerator_ {
		public static final int ID = 8;
		public PoorBauxiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorCooperiteGen extends PoorOreGenerator_ {
		public static final int ID = 9;
		public PoorCooperiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorBraggriteGen extends PoorOreGenerator_ {
		public static final int ID = 10;
		public PoorBraggriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorMolybdeniteGen extends PoorOreGenerator_ {
		public static final int ID = 11;
		public PoorMolybdeniteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorCobaltiteGen extends PoorOreGenerator_ {
		public static final int ID = 12;
		public PoorCobaltiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorWolframiteGen extends PoorOreGenerator_ {
		public static final int ID = 13;
		public PoorWolframiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorIlmeniteGen extends PoorOreGenerator_ {
		public static final int ID = 14;
		public PoorIlmeniteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorChromiteGen extends PoorOreGenerator_ {
		public static final int ID = 15;
		public PoorChromiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorCinnabarGen extends PoorOreGenerator_ {
		public static final int ID = 16;
		public PoorCinnabarGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorPitchblendeGen extends PoorOreGenerator_ {
		public static final int ID = 17;
		public PoorPitchblendeGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorMonaziteGen extends PoorOreGenerator_ {
		public static final int ID = 18;
		public PoorMonaziteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorNiedermayriteGen extends PoorOreGenerator_ {
		public static final int ID = 19;
		public PoorNiedermayriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGreenockiteGen extends PoorOreGenerator_ {
		public static final int ID = 20;
		public PoorGreenockiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGaotaiiteGen extends PoorOreGenerator_ {
		public static final int ID = 21;
		public PoorGaotaiiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorOsarsiteGen extends PoorOreGenerator_ {
		public static final int ID = 22;
		public PoorOsarsiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorZnamenskyiteGen extends PoorOreGenerator_ {
		public static final int ID = 23;
		public PoorZnamenskyiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGallobeudaniteGen extends PoorOreGenerator_ {
		public static final int ID = 24;
		public PoorGallobeudaniteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorTetrahediteGen extends PoorOreGenerator_ {
		public static final int ID = 25;
		public PoorTetrahediteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorTennantiteGen extends PoorOreGenerator_ {
		public static final int ID = 26;
		public PoorTennantiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorSantafeiteGen extends PoorOreGenerator_ {
		public static final int ID = 27;
		public PoorSantafeiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorMagnetiteGen extends PoorOreGenerator_ {
		public static final int ID = 28;
		public PoorMagnetiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorDioptaseGen extends PoorOreGenerator_ {
		public static final int ID = 29;
		public PoorDioptaseGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorPyropeGen extends PoorNetherOreGenerator {
		public static final int ID = 30;
		public PoorPyropeGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorMyuvilGen extends PoorOreGenerator_ {
		public static final int ID = 31;
		public PoorMyuvilGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}
}
