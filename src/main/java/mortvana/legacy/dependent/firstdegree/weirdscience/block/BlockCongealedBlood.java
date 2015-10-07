package mortvana.legacy.dependent.firstdegree.weirdscience.block;

import mortvana.legacy.errored.core.common.FluxGearContent;
import mortvana.legacy.clean.weirdscience.util.sound.ISoundProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class BlockCongealedBlood extends Block implements ISoundProvider {
    private static String unlocalizedName = "congealedBloodBlock";

    private static final String stepSounds[];
    private static final String placeSounds[];

    public static final ArrayList<String> sounds;

    static {
        stepSounds = new String[2];
        for(int i = 0; i < 2; i++) {
            stepSounds[i] = "mortvana.projectfluxgear:step/congealedBloodBlock" + (i + 1) + ".wav";
            System.out.println("PROJECT FLUX GEAR - WEIRD SCIENCE LEGACY DEBUG OUTPUT: " + stepSounds[i]);
        }
        placeSounds = new String[1];
        for(int i = 0; i < 1; i++) {
            placeSounds[i] = "mortvana.projectfluxgear:place/congealedBloodBlock" + (i + 1) + ".wav";
        }

        sounds = new ArrayList<String>();
        sounds.addAll(Arrays.asList(stepSounds));
        sounds.addAll(Arrays.asList(placeSounds));
    }

    public BlockCongealedBlood(Material blockMaterial) {
        super(Material.ground);

        setCreativeTab(FluxGearContent.tabMaterials);
        setBlockName("congealedBloodBlock");
        setHardness(1); //TODO: Proper value for this.
        setResistance(1);
        setBlockTextureName("gui:congealedBloodBlock");
        //setStepSound((StepSound) new WeirdStepSound(placeSoundName, placeSoundName, stepSoundName, 1.0f, 1.0f));
    }

    @Override
    public int getHarvestLevel(int subBlockMeta) {
        return 0;
    }

    public String getHarvestType(int subBlockMeta) {
        return "shovel";
    }

    @Override
    public ArrayList<String> getSounds() {
        return sounds;
    }

}