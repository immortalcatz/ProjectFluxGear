package mortvana.projectfluxgear.world;

import net.minecraft.block.Block;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;

import mortvana.fluxgearcore.util.world.cjrepack.PoorOreGenerator;

import mortvana.projectfluxgear.common.FluxGearContent;

public class PoorOreGen {

	public PoorOreGen () {}

	public static EventType theEvent;
	public static final String[] NAMES = { "CHALCOCITE", "CASSITERITE", "GALENA", "ACANTHITE", "GARNIERITE", "SPHALERITE", "BISMUTHINITE", "PYROLUSITE", "BAUXITE", "COOPERITE", "BRAGGITE", "MOLYBDENITE", "COBALTITE", "WOLFRAMITE", "ILMENITE", "CHROMITE", "CINNABAR", "PITCHBLENDE", "MONAZITE", "NIEDERMAYRITE", "GREENOCKITE", "GAOTAIITE", "OSARSITE", "ZNAMENSKYITE", "GALLOBEUDANITE", "TETRAHEDRITE", "TENNANTITE", "SANTAFEITE", "MAGNETITE", "DIOPTASE", "PYROPE", "MYUVIL" };
	public static final int[] Y_LEVEL =  { 60, 50, 30, 25, 35, 55, 55, 45, 45, 15, 25, 40, 35, 15, 20, 20, 55, 20, 35, 25, 40, 30, 35, 30, 25, 50, 45, 20, 55, 10, 95, 10};
	public static final int[] Y_RANGE =  { 5, 4, 3, 2, 3, 4, 3, 4, 3, 1, 2, 3, 2, 2, 2, 2, 3, 4, 3, 2, 3, 2, 2, 3, 3, 4, 4, 3, 4, 2, 35, 2};
	public static final int[] DENSITY =  { 16, 12, 8, 7, 7, 9, 9, 9, 10, 2, 8, 6, 6, 2, 2, 2, 4, 12, 6, 6, 6, 4, 8, 8, 8, 7, 7, 9, 8, 4, 4, 2};
	public static final int[] SEED = { 682, 526, 261, 334, 706, 60, 282, 887, 712, 175, 803, 568, 767, 297, 740, 910, 720, 187, 633, 741, 934, 47, 218, 554, 505, 948, 499, 822, 291, 798, 274, 946 };

	public static final Block MAIN = FluxGearContent.blockPoorOreMain;
	public static final Block AUX = FluxGearContent.blockPoorOreAux;

	// I usually hate subclasses, but with 32 Poor Ores, it makes a lot sense.
	public static class PoorChalcociteGen extends PoorOreGenerator {
		public static final int ID = 0;
		public PoorChalcociteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorCassiteriteGen extends PoorOreGenerator {
		public static final int ID = 1;
		public PoorCassiteriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGalenaGen extends PoorOreGenerator {
		public static final int ID = 2;
		public PoorGalenaGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorAcanthiteGen extends PoorOreGenerator {
		public static final int ID = 3;
		public PoorAcanthiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGarnieriteGen extends PoorOreGenerator {
		public static final int ID = 4;
		public PoorGarnieriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorSphaleriteGen extends PoorOreGenerator {
		public static final int ID = 5;
		public PoorSphaleriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorBismuthiniteGen extends PoorOreGenerator {
		public static final int ID = 6;
		public PoorBismuthiniteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorPyrolusiteGen extends PoorOreGenerator {
		public static final int ID = 7;
		public PoorPyrolusiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorBauxiteGen extends PoorOreGenerator {
		public static final int ID = 8;
		public PoorBauxiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorCooperiteGen extends PoorOreGenerator {
		public static final int ID = 9;
		public PoorCooperiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorBraggriteGen extends PoorOreGenerator {
		public static final int ID = 10;
		public PoorBraggriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorMolybdeniteGen extends PoorOreGenerator {
		public static final int ID = 11;
		public PoorMolybdeniteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorCobaltiteGen extends PoorOreGenerator {
		public static final int ID = 12;
		public PoorCobaltiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorWolframiteGen extends PoorOreGenerator {
		public static final int ID = 13;
		public PoorWolframiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorIlmeniteGen extends PoorOreGenerator {
		public static final int ID = 14;
		public PoorIlmeniteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorChromiteGen extends PoorOreGenerator {
		public static final int ID = 15;
		public PoorChromiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], MAIN, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorCinnabarGen extends PoorOreGenerator {
		public static final int ID = 16;
		public PoorCinnabarGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorPitchblendeGen extends PoorOreGenerator {
		public static final int ID = 17;
		public PoorPitchblendeGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorMonaziteGen extends PoorOreGenerator {
		public static final int ID = 18;
		public PoorMonaziteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorNiedermayriteGen extends PoorOreGenerator {
		public static final int ID = 19;
		public PoorNiedermayriteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGreenockiteGen extends PoorOreGenerator {
		public static final int ID = 20;
		public PoorGreenockiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGaotaiiteGen extends PoorOreGenerator {
		public static final int ID = 21;
		public PoorGaotaiiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorOsarsiteGen extends PoorOreGenerator {
		public static final int ID = 22;
		public PoorOsarsiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorZnamenskyiteGen extends PoorOreGenerator {
		public static final int ID = 23;
		public PoorZnamenskyiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorGallobeudaniteGen extends PoorOreGenerator {
		public static final int ID = 24;
		public PoorGallobeudaniteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorTetrahediteGen extends PoorOreGenerator {
		public static final int ID = 25;
		public PoorTetrahediteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorTennantiteGen extends PoorOreGenerator {
		public static final int ID = 26;
		public PoorTennantiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorSantafeiteGen extends PoorOreGenerator {
		public static final int ID = 27;
		public PoorSantafeiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorMagnetiteGen extends PoorOreGenerator {
		public static final int ID = 28;
		public PoorMagnetiteGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}

	public static class PoorDioptaseGen extends PoorOreGenerator {
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

	public static class PoorMyuvilGen extends PoorOreGenerator {
		public static final int ID = 31;
		public PoorMyuvilGen() {
			super(theEvent, DENSITY[ID], Y_LEVEL[ID], Y_RANGE[ID], SEED[ID], AUX, ID % 16);
			theEvent = EnumHelper.addEnum(EventType.class, "PFG_POOR_" + NAMES[ID], new Class[0], new Object[0]);
		}
	}
}
