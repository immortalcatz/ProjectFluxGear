package mortvana.melteddashboard.block;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import gnu.trove.map.TMap;
import mortvana.melteddashboard.common.MeltedDashboardCore;

/**
 *  An easy to use, yet advanced version of Block for use in many situations.
 *  Contains automagic setting of meta-sensitive hardness, resistance, light emissions, redstone signals,
 *  beacon base-ness, mob spawnability, creative tabs, and names!
 *
 *  @author Mortvana
 */
public class FluxGearComplexBlock extends Block {

	public int metaBlocks;
	public float[] hardness;
	public float[] resistance;
	/*public TMap<Integer, TMap<String, Integer>> miningLevels;
	public String name;
	public TMap<Integer, Boolean> canSpawn;
	public TMap<Integer, Boolean> beaconBase;
	public TMap<Integer, String> names;
	public TMap<Integer, Float> hardness;
	public TMap<Integer, Float> resistance;
	public TMap<Integer, Integer> light;
	public TMap<Integer, TMap<Integer, Boolean>> signal;
	public String textureLocation;
	public TMap<Integer, List<ItemStack>> droppedBlocks;*/

	public FluxGearComplexBlock(Material material, CreativeTabs tab, int metaBlocks) {
		super(material);
		this.metaBlocks = metaBlocks;
		setCreativeTab(tab);
		setupArrays(metaBlocks);
		//internalName = ;
	}

	public void setupArrays(int length) {
		hardness = new float[length];
		resistance = new float[length];
	}

	public FluxGearComplexBlock setDefaultHardness(float hardness) {
		super.setHardness(hardness);
		return this;
	}

	public FluxGearComplexBlock setHardness(float hardness, float... values) {
		super.setHardness(hardness);
		if (values.length == metaBlocks) {
			for (int i = 0; i < metaBlocks; i++) {
				if (values[i] != -1) {
					this.hardness[i] = values[i];
				} else {
					this.hardness[i] = hardness;
				}
			}
		} else {
			MeltedDashboardCore.logger.warn("Someone set the hardness of a block with the unlocalized name \"" + getUnlocalizedName() + "\" wrong, report this to the author.");
		}
		return this;
	}

	public FluxGearComplexBlock setHardness(float hardness, Collection<Float> values) {
		super.setHardness(hardness);
		Iterator i$ = values.iterator();
		for (int i = 0; i < metaBlocks; i++) {
			if (i$.hasNext()) {
				this.hardness[i] = (Float) i$.next();
			} else {
				this.hardness[i] = hardness;
			}
		}
		return this;
	}

	public FluxGearComplexBlock setHardness(Map<Integer, Float> values) {
		for (int i = 0; i < metaBlocks; i++) {
			if (values.containsKey(i)) {
				hardness[i] = values.get(i);
			} else if (values.containsKey(MeltedDashboardCore.WILDCARD)) {
				hardness[i] = values.get(MeltedDashboardCore.WILDCARD);
			} else {
				hardness[i] = blockHardness;
			}
		}
		return this;
	}

	public FluxGearComplexBlock setMetaHardness(int metadata, float hardness) {
		this.hardness[metadata] = hardness;
		return this;
	}

	//@Override
	//public float

	public FluxGearComplexBlock setDefaultResistance(float resistance) {
		super.setResistance(resistance);
		return this;
	}

	public FluxGearComplexBlock setResistance(float resistance, float... values) {
		super.setHardness(resistance);
		if (values.length == metaBlocks) {
			for (int i = 0; i < metaBlocks; i++) {
				if (values[i] != -1) {
					this.resistance[i] = values[i];
				} else {
					this.resistance[i] = resistance;
				}
			}
		} else {
			MeltedDashboardCore.logger.warn("Someone set the resistance of a block with the unlocalized name \"" + getUnlocalizedName() + "\" wrong, report this to the author.");
		}
		return this;
	}

	public FluxGearComplexBlock setResistance(float resistance, Collection<Float> values) {
		super.setHardness(resistance);
		Iterator i$ = values.iterator();
		for (int i = 0; i < metaBlocks; i++) {
			if (i$.hasNext()) {
				this.resistance[i] = (Float) i$.next();
			} else {
				this.resistance[i] = resistance;
			}
		}
		return this;
	}

	public FluxGearComplexBlock setResistance(Map<Integer, Float> values) {
		for (int i = 0; i < metaBlocks; i++) {
			if (values.containsKey(i)) {
				resistance[i] = values.get(i);
			} else if (values.containsKey(MeltedDashboardCore.WILDCARD)) {
				resistance[i] = values.get(MeltedDashboardCore.WILDCARD);
			} else {
				resistance[i] = blockResistance;
			}
		}
		return this;
	}

	public FluxGearComplexBlock setMetaResistance(int metadata, float resistance) {
		this.resistance[metadata] = resistance;
		return this;
	}






























	public FluxGearComplexBlock setResistance() {

		return this;
	}

	//public FluxGearBlock















	/**
	 *  Literally just a wrapper for a default block, stupidly simple!
	 *  @param material - The material of the block.
	 */
	//public FluxGearBlock(Material material) {
	//	this(material, null, -1, -1);
	//}

	/**
	 * The really simple way to initialize a block and add it to a creative tab.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 */
	//public FluxGearBlock(Material material, CreativeTabs tab) {
	//	this(material, tab, -1, -1);
	//}

	/**
	 * The simple way to initialize a block and add it to a creative tab, while also setting
	 * hardness and blast resistance.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 * @param hardness - The hardness of the block (how long it takes to mine).
	 * @param resistance - The blast resistance of the block (how resistant it is to explosions).
	 */
	//public FluxGearBlock(Material material, CreativeTabs tab, float hardness, float resistance) {
	//	super(material);
	//	setCreativeTab(tab);
	//	setHardness(hardness);
	//	setResistance(resistance);
	//}

	//public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type)

	//public FluxGearBlock(Material material, CreativeTabs tab, int metaBlocks, float blockHardness, float blockResistance,
	//                     List<Boolean> canSpawn, List<Boolean> beaconBase, List<String> names, List<Float> hardness, List<Float> resistance,
	//                     List<Integer> light, List<Integer> signal, String textureLocation) {
	//	super(material);

	//}

	@Override
	public int damageDropped(int i) {
		return i;
	}

}


