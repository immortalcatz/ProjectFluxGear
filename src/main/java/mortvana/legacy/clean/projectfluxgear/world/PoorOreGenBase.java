package mortvana.legacy.clean.projectfluxgear.world;

import net.minecraft.block.Block;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.terraingen.OreGenEvent;

public class PoorOreGenBase extends PoorOreGeneratorBase {

    public PoorOreGenBase(int id, Block block) {
        super(EnumHelper.addEnum(OreGenEvent.GenerateMinable.EventType.class, "PFG_POOR_" + NAMES[id], new Class[0], new Object[0]), DENSITY[id], Y_LEVEL[id], Y_RANGE[id], SEED[id], block, id % 16);
    }

    public static OreGenEvent.GenerateMinable.EventType theEvent; //"Is it true they feast on human flesh?" "Yes." *Cuts from 'The Quiz Broadcast'*
    public static final String[] NAMES = { "CHALCOCITE", "CASSITERITE", "GALENA", "ACANTHITE", "GARNIERITE", "SPHALERITE", "BISMUTHINITE", "PYROLUSITE", "BAUXITE", "COOPERITE", "BRAGGITE", "MOLYBDENITE", "COBALTITE", "WOLFRAMITE", "ILMENITE", "CHROMITE", "CINNABAR", "PITCHBLENDE", "MONAZITE", "NIEDERMAYRITE", "GREENOCKITE", "GAOTAIITE", "OSARSITE", "ZNAMENSKYITE", "GALLOBEUDANITE", "TETRAHEDRITE", "TENNANTITE", "SANTAFEITE", "MAGNETITE", "DIOPTASE", "PYROPE", "MYUVIL" };
    public static final int[] Y_LEVEL = { 60, 50, 30, 25, 35, 55, 45, 55, 45, 15, 25, 40, 35, 15, 20, 20, 55, 20, 35, 25, 40, 30, 35, 30, 25, 50, 45, 20, 55, 10, 95, 10 };
    public static final int[] Y_RANGE = { 5, 4, 3, 2, 3, 4, 3, 4, 3, 1, 2, 3, 2, 2, 2, 2, 3, 4, 3, 2, 3, 2, 2, 3, 3, 4, 4, 3, 4, 2, 35, 2 };
    public static final int[] DENSITY = { 16, 12, 8, 7, 7, 9, 9, 9, 10, 2, 8, 6, 6, 2, 2, 2, 6, 12, 6, 6, 6, 8, 8, 8, 8, 7, 7, 6, 8, 4, 4, 2 };
    public static final int[] SEED = { 682, 526, 261, 334, 706, 324, 282, 887, 712, 175, 803, 568, 767, 297, 740, 910, 720, 187, 633, 741, 934, 647, 218, 554, 505, 948, 499, 822, 291, 798, 274, 946 }; //THAT'S NUMBERWANG!

}