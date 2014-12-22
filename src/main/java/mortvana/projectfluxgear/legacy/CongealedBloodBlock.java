package mortvana.projectfluxgear.legacy;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import mortvana.fluxgearcore.legacy.util.ISoundProvider;

import mortvana.projectfluxgear.common.ProjectFluxGear;

public class CongealedBloodBlock extends Block implements ISoundProvider {
	private static String unlocalizedName = "congealedBloodBlock";

	private static final String stepSounds[];
	private static final String placeSounds[];
	
	public static final ArrayList<String> sounds;
	
	static {
		stepSounds = new String[2];
		for(int i = 0; i < 2; i++) {
			stepSounds[i] = "projectfluxgear:step/congealedBloodBlock" + (i + 1) + ".wav";
			System.out.println("PROJECT FLUX GEAR - WEIRD SCIENCE LEGACY DEBUG OUTPUT: " + stepSounds[i]);
		}
		placeSounds = new String[1];
		for(int i = 0; i < 1; i++) {
			placeSounds[i] = "projectfluxgear:place/congealedBloodBlock" + (i + 1) + ".wav";
		}
		
		sounds = new ArrayList<String>();
		sounds.addAll(Arrays.asList(stepSounds));
		sounds.addAll(Arrays.asList(placeSounds));
	}
	
	public CongealedBloodBlock(Material blockMaterial) {
		super(Material.ground);

		setCreativeTab(ProjectFluxGear.tabResources);
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