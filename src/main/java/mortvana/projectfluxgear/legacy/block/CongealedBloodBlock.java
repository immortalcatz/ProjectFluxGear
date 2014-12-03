package mortvana.projectfluxgear.legacy.block;

import java.util.ArrayList;
import java.util.Arrays;

import mortvana.projectfluxgear.common.ProjectFluxGear;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.config.Configuration;
import mortvana.fluxgearcore.legacy.block.BlockBase;
import mortvana.fluxgearcore.legacy.util.ISoundProvider;

public class CongealedBloodBlock extends Block implements ISoundProvider {
	private static String unlocalizedName = "congealedBloodBlock";
	
	private static final String stepSoundName = ProjectFluxGear.modID + ":step." + unlocalizedName;
	private static final String stepSoundType = "wav";
	private static final String placeSoundName = ProjectFluxGear.modID + ":place." + unlocalizedName;
	private static final String placeSoundType = "wav";
	private static final int stepSoundCount = 2;
	private static final String stepSounds[];
	private static final int placeSoundCount = 1;
	private static final String placeSounds[];
	
	public static final ArrayList<String> sounds;
	
	static {
		stepSounds = new String[stepSoundCount];
		for(int i = 0; i < stepSoundCount; i++) {
			stepSounds[i] = ProjectFluxGear.modID + ":step/" + unlocalizedName + (i + 1) + "." + stepSoundType;
			System.out.println("PROJECT FLUX GEAR - WEIRD SCIENCE LEGACY DEBUG OUTPUT: " + stepSounds[i]);
		}
		placeSounds = new String[placeSoundCount];
		for(int i = 0; i < placeSoundCount; i++) {
			placeSounds[i] = ProjectFluxGear.modID + ":place/" + unlocalizedName + (i + 1) + "." + placeSoundType;
		}
		
		sounds = new ArrayList<String>();
		sounds.addAll(Arrays.asList(stepSounds));
		sounds.addAll(Arrays.asList(placeSounds));
	}
	
	public CongealedBloodBlock(Material blockMaterial) {
		super(Material.ground);

		setCreativeTab(ProjectFluxGear.tab);
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