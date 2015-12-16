package mortvana.melteddashboard.block;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;

import mortvana.melteddashboard.util.data.*;
import mortvana.melteddashboard.util.helpers.StringHelper;
//TODO: CLEAN UP!
/**
 *  A relatively easy to use, yet advanced version of FluxGearBlock for use in many situations.
 *  Contains automagic setting of meta-sensitive hardness, resistance, light emissions, redstone signals,
 *  beacon base-ness, mob spawnability, creative tabs, names, flammability, plant sustaining, and more!
 *
 *  @author Mortvana
 */
public class FluxGearBlock extends Block {

	public static final int WILD = Short.MAX_VALUE;

	public String textureLocation;
	public int metaBlocks;
	public IIcon[] icons;
	public String[] textures;
	public String[] names;
	
	public float[] blockHardness;
	public float[] blastResistance;
	public int[] blockLight;
	public int[] signal;
	public boolean[] mobSpawns;
	public boolean[] beaconBase;
	public int[] colors;
	public TMap<Integer, HarvestData> harvestLevels = new THashMap<Integer, HarvestData>(20);

	public TMap<Integer, Integer> droppedMeta = new THashMap<Integer, Integer>(20);
	public TMap<Integer, List<ItemStack>> droppedItems = new THashMap<Integer, List<ItemStack>>(20);

	public String name;

	public List<Integer> renderInPasses = new ArrayList<Integer>(20);

	/**
	 *  Literally just a wrapper for a default block, stupidly simple!
	 *  @param material - The material of the block.
	 */
	public FluxGearBlock(Material material) {
		super(material);
	}

	/**
	 * The really simple way to initialize a block and add it to a creative tab.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 */
	public FluxGearBlock(Material material, CreativeTabs tab) {
		super(material);
		setCreativeTab(tab);
	}

	public FluxGearBlock(Material material, CreativeTabs tab, int metaBlocks) {
		super(material);
		this.metaBlocks = metaBlocks;
		setCreativeTab(tab);
		//internalName = ;
	}

	/**
	 * The simple way to initialize a block and add it to a creative tab, while also setting non-meta-sensitive
	 * hardness and blast resistance.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 * @param hardness - The hardness of the block (how long it takes to mine).
	 * @param resistance - The blast resistance of the block (how resistant it is to explosions).
	 */
	public FluxGearBlock(Material material, CreativeTabs tab, float hardness, float resistance) {
		super(material);
		setCreativeTab(tab);
		setHardness(hardness);
		setResistance(resistance);
	}

	/**
	 * The simple way to initialize a block.
	 * Uses common for a type defaults.
	 * @param material The material of the block.
	 * @param tab The creative tab the block is under.
	 * @param type Use a default type for ease of use.
	 */
	/*public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type) {
		super(material);
		setCreativeTab(tab);

		if (type == EnumBlockType.STORAGE) {
			canSpawn = false;
			beaconBase = true;
			isColorized = false;
			setHardness(5.0F);
			setResistance(10.0F);
			setStepSound(soundTypeMetal);
		} else if (type == EnumBlockType.ORE) {
			canSpawn = true;
			beaconBase = false;
			isColorized = false;
			setHardness(3.0F);
			setResistance(5.0F);
			setStepSound(soundTypeMetal);
		} else if (type == EnumBlockType.SOIL_ORE) {
			canSpawn = true;
			beaconBase = false;
			isColorized = false;
			setHardness(3.0F);
			setResistance(5.0F);
			setStepSound(soundTypeGravel);
		} else {
			canSpawn = true;
			beaconBase = false;
			isColorized = false;
			MeltedDashboardCore.logger.warn("Someone registered a block wrong in using Melted Dashboard Core... Using defaults...");
		}
	}*/

	/**
	 * The slightly more complex way to initialize a block.
	 * Allows for more customization
	 * @param material The material of the block.
	 * @param tab The creative tab the block is under.
	 * @param spawn Can mobs spawn on top of it.
	 * @param base Is it usable as a beacon base.
	 * @param colorized Set to true if you are using a simple colorizer.
	 */
	public FluxGearBlock(Material material, CreativeTabs tab, boolean spawn, boolean base, boolean colorized) {
		super(material);
		setCreativeTab(tab);

		canSpawn = spawn;
		beaconBase = base;
		isColorized = colorized;
	}

	/**
	 * The only way, currently, to initialize a block with metadata things and automatic texture registration.
	 * Uses common for a type defaults.
	 *
	 * @param material The material of the block.
	 * @param tab      The creative tab the block is under.
	 * @param type     Use a default type for ease of use.
	 */
	/*public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type, String[] names,
	                      String [] textures, float[] hardness, float[] resistance, int[] light, String textureLocation) {
		this(material, tab, type);

		this.names = names;
		this.textures = textures;
		this.hardness = hardness;
		this.resistance = resistance;
		this.light = light;
		this.textureLocation = textureLocation;
	}*/

	//public FluxGearBlock(Material material, CreativeTabs tab, int metaBlocks, float blockHardness, float blockResistance,
	//                     List<Boolean> canSpawn, List<Boolean> beaconBase, List<String> names, List<Float> hardness, List<Float> resistance,
	//                     List<Integer> light, List<Integer> signal, String textureLocation) {
	//	super(material);

	//public void setupArrays(int length) {
	//	hardness = new float[length];
	//	resistance = new float[length];
	//}



	/* Standard Meta-Sensitive Getters */
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > blockHardness.length ? blockHardness[meta] : blockHardness[0];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > blastResistance.length ? blastResistance[meta] : blastResistance[0];
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > blockLight.length ? blockLight[meta] : 0;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > signal.length && signal[meta] > 16 ? signal[meta] : 0;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > signal.length && signal[meta] < 16 ? signal[meta] % 16 : 0;
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > mobSpawns.length ? mobSpawns[meta] : mobSpawns[0];
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > beaconBase.length ? beaconBase[meta] : beaconBase[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > colors.length ? colors[meta] : 0xFFFFFF;
	}





	/* Block Breaking */
	@Override
	public int damageDropped(int metadata) {
		if (droppedItems.containsKey(metadata) && droppedItems.get(metadata).size() == 1 && droppedItems.get(metadata).get(0) != null) {
			return droppedItems.get(metadata).get(0).getMetadata();
		} else if (droppedMeta.containsKey(metadata)) {
			return droppedMeta.get(metadata);
		} else if (droppedMeta.containsKey(WILD)) {
			if (droppedMeta.get(WILD) == -1 || droppedMeta.get(WILD) == WILD) {
				return metadata;
			} else {
				return droppedMeta.get(WILD);
			}
		} else {
			return super.damageDropped(metadata);
		}
	}

	/* Textures */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return metadata > icons.length ? icons[metadata] : blockIcon;
	}

	@Override
	public boolean canRenderInPass(int pass) {
		return renderInPasses.contains(pass);
	}

	//TODO
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		for (int i = 0; i < names.length; i++) {
			icons[i] = iconRegister.registerIcon(textureLocation + StringHelper.camelCase(names[i]));
		}
	}

	public String getUnlocalizedName() {}

	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

}