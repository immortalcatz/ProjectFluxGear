package mortvana.melteddashboard.block;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.data.*;
import mortvana.melteddashboard.util.helpers.StringHelper;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
//TODO: CLEAN UP AND IMPLEMENT!!!!
/**
 *  A relatively easy to use, yet advanced version of FluxGearBlock for use in many situations.
 *  Contains automagic setting of meta-sensitive hardness, resistance, light emissions, redstone signals,
 *  beacon base-ness, mob spawnability, creative tabs, names, flammability, plant sustaining, and more!
 *
 *  @author Mortvana
 */
public class AdvFluxGearBlock extends FluxGearBlock {

	public static final int WILD = Short.MAX_VALUE;

	public String textureLocation;
	public int metaBlocks;
	public TMap<Integer, SidedIIcon> icons = new THashMap<Integer, SidedIIcon>(20);
	public TMap<Integer, String> textures = new THashMap<Integer, String>(20);
	public TMap<Integer, String> names = new THashMap<Integer, String>(20);

	public TMap<Integer, Float> blockHardness = new THashMap<Integer, Float>(20);
	public TMap<Integer, Float> blastResistance = new THashMap<Integer, Float>(20);
	public TMap<Integer, Integer> blockLight = new THashMap<Integer, Integer>(20);
	public TMap<Integer, Integer> weakSignal = new THashMap<Integer, Integer>(20);
	public TMap<Integer, Integer> strongSignal = new THashMap<Integer, Integer>(20);
	public TMap<Integer, Boolean> mobSpawns = new THashMap<Integer, Boolean>(20);
	public TMap<Integer, Boolean> beaconBase = new THashMap<Integer, Boolean>(20);
	public TMap<Integer, Integer> colors = new THashMap<Integer, Integer>(20);
	public TMap<Integer, HarvestData> harvestLevels = new THashMap<Integer, HarvestData>(20);

	public int mobilityFlag = -1;
	public boolean dropsFromExplosion = true;

	public TMap<Integer, Boolean> climbable = new THashMap<Integer, Boolean>(20);
	public TMap<Integer, Boolean> normalCube = new THashMap<Integer, Boolean>(20);
	public TMap<Integer, DirectionalBoolean> sideSolid = new THashMap<Integer, DirectionalBoolean>(20);
	public TMap<Integer, Boolean> replaceable = new THashMap<Integer, Boolean>(20);

	public TMap<Integer, Boolean> burning = new THashMap<Integer, Boolean>(20);
	public TMap<Integer, DirectionalInteger> flammability = new THashMap<Integer, DirectionalInteger>(20);
	public TMap<Integer, DirectionalBoolean> flammable = new THashMap<Integer, DirectionalBoolean>(20);
	public TMap<Integer, DirectionalInteger> fireSpread = new THashMap<Integer, DirectionalInteger>(20);
	public TMap<Integer, DirectionalBoolean> fireSource = new THashMap<Integer, DirectionalBoolean>(20);
















	public TMap<Integer, Integer> droppedMeta = new THashMap<Integer, Integer>(20);
	public TMap<Integer, List<ItemStack>> droppedItems = new THashMap<Integer, List<ItemStack>>(20);






	public static boolean finalMaterial = true;
	public Boolean doesBlockGrass = null;


	public MapColor[] mapColors;
	public IIcon[][] sidedIcons;
	public String name;


	public List<Integer> renderInPasses = new ArrayList<Integer>(20);

	/**
	 *  Literally just a wrapper for a default block, stupidly simple!
	 *  @param material - The material of the block.
	 */
	public AdvFluxGearBlock(Material material) {
		super(material);
	}

	/**
	 * The really simple way to initialize a block and add it to a creative tab.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 */
	public AdvFluxGearBlock(Material material, CreativeTabs tab) {
		super(material);
		setCreativeTab(tab);
	}

	public AdvFluxGearBlock(Material material, CreativeTabs tab, int metaBlocks) {
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
	public AdvFluxGearBlock(Material material, CreativeTabs tab, float hardness, float resistance) {
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
	public AdvFluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type) {
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
	}

	/**
	 * The slightly more complex way to initialize a block.
	 * Allows for more customization
	 * @param material The material of the block.
	 * @param tab The creative tab the block is under.
	 * @param spawn Can mobs spawn on top of it.
	 * @param base Is it usable as a beacon base.
	 * @param colorized Set to true if you are using a simple colorizer.
	 */
	public AdvFluxGearBlock(Material material, CreativeTabs tab, boolean spawn, boolean base, boolean colorized) {
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
	public AdvFluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type, String[] names,  String [] textures, float[] hardness, float[] resistance, int[] light, String textureLocation) {
		this(material, tab, type);

		this.names = names;
		this.textures = textures;
		this.hardness = hardness;
		this.resistance = resistance;
		this.light = light;
		this.textureLocation = textureLocation;
	}

	//public FluxGearBlock(Material material, CreativeTabs tab, int metaBlocks, float blockHardness, float blockResistance,
	//                     List<Boolean> canSpawn, List<Boolean> beaconBase, List<String> names, List<Float> hardness, List<Float> resistance,
	//                     List<Integer> light, List<Integer> signal, String textureLocation) {
	//	super(material);

	//public void setupArrays(int length) {
	//	hardness = new float[length];
	//	resistance = new float[length];
	//}

	public AdvFluxGearBlock init() {
		blockHardness.putIfAbsent(WILD, 3.0F);
		blastResistance.putIfAbsent(WILD, 5.0F);
		droppedMeta.putIfAbsent(WILD, -1);
		if (mobilityFlag == -1) {
			mobilityFlag = super.getMobilityFlag();
		}

		return this;
	}

	/* Hardness Setters */
	public AdvFluxGearBlock setDefaultHardness(float hardness) {
		super.setHardness(hardness);
		return this;
	}

	public AdvFluxGearBlock setHardness(float hardness, float... values) {
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

	public AdvFluxGearBlock setHardness(float hardness, Collection<Float> values) {
		super.setHardness(hardness);
		Iterator iburning = values.iterator();
		for (int i = 0; i < metaBlocks; i++) {
			if (iburning.hasNext()) {
				this.hardness[i] = (Float) iburning.next();
			} else {
				this.hardness[i] = hardness;
			}
		}
		return this;
	}

	public AdvFluxGearBlock setHardness(Map<Integer, Float> values) {
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

	public AdvFluxGearBlock setMetaHardness(int metadata, float hardness) {
		this.hardness[metadata] = hardness;
		return this;
	}


	/* Resistance Setters */
	public AdvFluxGearBlock setDefaultResistance(float resistance) {
		super.setResistance(resistance);
		return this;
	}

	public AdvFluxGearBlock setResistance(float resistance, float... values) {
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

	public AdvFluxGearBlock setResistance(float resistance, Collection<Float> values) {
		super.setHardness(resistance);
		Iterator iburning = values.iterator();
		for (int i = 0; i < metaBlocks; i++) {
			if (iburning.hasNext()) {
				this.resistance[i] = (Float) iburning.next();
			} else {
				this.resistance[i] = resistance;
			}
		}
		return this;
	}

	public AdvFluxGearBlock setResistance(Map<Integer, Float> values) {
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

	public AdvFluxGearBlock setMetaResistance(int metadata, float resistance) {
		this.resistance[metadata] = resistance;
		return this;
	}

	/* Light Setters */

	/* Redstone Signal Setters */

	/* Mob Spawn Setters */

	/* Beacon Base Setters */

	/* Colorizer Setters */








	/*public FluxGearBlock setMaterial(Material material) {
		if (finalMaterial) {
			definalizeMaterial();
		}

		blockMaterial = material;
		canBlockGrass = getCanBlockGrass();
		return this;
	}


	public void definalizeMaterial() {
		//Deep dark java reflection voodoo. A possible security exception, but done for the greater good.
		Field material;
		try {
			//Assign field.
			material = Block.class.getField("blockMaterial");
			material.setAccessible(true);

			//Modify field.
			Field modifiers = Field.class.getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(material, material.getModifiers() & ~Modifier.FINAL);

			finalMaterial = false;
		} catch (Exception e) {
			MeltedDashboardCore.logger.error(e.getMessage());
			e.printStackTrace();
		}
	}*/













	/* Standard Meta-Sensitive Getters */
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (blockHardness.containsKey(meta)) {
			return blockHardness.get(meta);
		} else if (blockHardness.containsKey(WILD)) {
			return blockHardness.get(WILD);
		} else {
			return super.getBlockHardness(world, x, y, z);
		}
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		int meta = world.getBlockMetadata(x, y, z);
		if (blastResistance.containsKey(meta)) {
			return blastResistance.get(meta);
		} else if (blastResistance.containsKey(WILD)) {
			return blastResistance.get(WILD);
		} else {
			return super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (blockLight.containsKey(meta)) {
			return blockLight.get(meta);
		} else if (blockLight.containsKey(WILD)) {
			return blockLight.get(WILD);
		} else {
			return super.getLightValue(world, x, y, z);
		}
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		if (weakSignal.containsKey(meta)) {
			return weakSignal.get(meta);
		} else if (weakSignal.containsKey(WILD)) {
			return weakSignal.get(WILD);
		} else {
			return super.isProvidingWeakPower(world, x, y, z, side);
		}
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		if (strongSignal.containsKey(meta)) {
			return strongSignal.get(meta);
		} else if (strongSignal.containsKey(WILD)) {
			return strongSignal.get(WILD);
		} else {
			return super.isProvidingStrongPower(world, x, y, z, side);
		}
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (mobSpawns.containsKey(meta)) {
			return mobSpawns.get(meta);
		} else if (mobSpawns.containsKey(WILD)) {
			return mobSpawns.get(WILD);
		} else {
			return super.canCreatureSpawn(type, world, x, y, z);
		}
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
		int meta = world.getBlockMetadata(x, y, z);
		if (beaconBase.containsKey(meta)) {
			return beaconBase.get(meta);
		} else if (beaconBase.containsKey(WILD)) {
			return beaconBase.get(WILD);
		} else {
			return super.isBeaconBase(world, x, y, z, beaconX, beaconY, beaconZ);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (colors.containsKey(meta)) {
			return colors.get(meta);
		} else if (colors.containsKey(WILD)) {
			return colors.get(WILD);
		} else {
			return super.colorMultiplier(world, x, y, z);
		}
	}


	/* Meta-Insensitive Getters */
	@Override
	public int getMobilityFlag() {
		return mobilityFlag;
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return dropsFromExplosion;
	}

	public boolean blocksGrass() {
		return doesBlockGrass == null ? blockMaterial.getCanBlockGrass() : doesBlockGrass;
	}

	/* Obscure Meta-Sensitive Getters */
	@Override
	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
		int meta = world.getBlockMetadata(x, y, z);
		if (climbable.containsKey(meta)) {
			return climbable.get(meta);
		} else if (climbable.containsKey(WILD)) {
			return climbable.get(WILD);
		} else {
			return super.isLadder(world, x, y, z, entity);
		}
	}

	@Override
	public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (normalCube.containsKey(meta)) {
			return normalCube.get(meta);
		} else if (normalCube.containsKey(WILD)) {
			return normalCube.get(WILD);
		} else {
			return super.isNormalCube(world, x, y, z);
		}
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		int meta = world.getBlockMetadata(x, y, z);
		if (sideSolid.containsKey(meta)) {
			return sideSolid.get(meta).solidOnSide(side);
		} else if (sideSolid.containsKey(WILD)) {
			return sideSolid.get(WILD).solidOnSide(side);
		} else {
			return isNormalCube(world, x, y, z);
		}
	}

	@Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (replaceable.containsKey(meta)) {
			return replaceable.get(meta);
		} else if (replaceable.containsKey(WILD)) {
			return replaceable.get(WILD);
		} else {
			return super.isReplaceable(world, x, y, z);
		}
	}



	/* Combustion Getters */
	@Override
	public boolean isBurning(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (burning.containsKey(meta)) {
			return burning.get(meta);
		} else if (burning.containsKey(WILD)) {
			return burning.get(WILD);
		} else {
			return super.isBurning(world, x, y, z);
		}
	}

	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection side) {}

	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection side) {}

	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection side) {}

	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {}




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








	/* Block Placing */










	/* Textures */



	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (icons.containsKey(metadata)) {
			return icons.get(metadata).iconOnSide(side);
		} else if (icons.containsKey(WILD)) {
			return icons.get(WILD).iconOnSide(side);
		} else {
			return blockIcon;
		}
	}

	@Override
	public boolean canRenderInPass(int pass) {
		return renderInPasses.contains(pass);
	}















	//TODO





	/* Textures */
	//TODO
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		for (int i = 0; i < names.length; i++) {
			icons.put(i) = iconRegister.registerIcon(textureLocation + StringHelper.toCamelCase(names[i]));
		}
	}

	//TODO Maybe?
	@Override
	public int quantityDropped(Random random) {}

	//TODO Maybe?
	public Item getItemDropped(int par1, Random random, int par3) {}

	public String getUnlocalizedName() {}

	public int quantityDroppedWithBonus(int par1, Random random) {}

	public void getSubBlocks(Item item, CreativeTabs tab, List list) {}

	@SideOnly(Side.CLIENT)
	public String getTextureName() {}









	/* UNSORTED */

	/*//TODO
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune) {}

	//TODO
	public void dropBlockAsItem(World world, int x, int y, int z, ItemStack itemstack) {}

	//TODO
	public void dropXpOnBlockBreak(World world, int x, int y, int z, int amount) {}

	public boolean canReplace(World world, int x, int y, int z, int par5, ItemStack itemstack) {}

	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int par5) {}

	public boolean canPlaceBlockAt(World world, int x, int y, int z) {}

	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {}

	public int getDamageValue(World world, int x, int y, int z) {}
*/

	/* Forge Hooked */



/*	public boolean isAir(IBlockAccess world, int x, int y, int z) {}

	public boolean canHarvestBlock(IBlockAccess world, int x, int y, int z) {}



	public boolean hasTileEntity(int metadata) {}

	public int quantityDropped(int metadata, int fortune, Random random) {}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {}

	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {}

	public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {}

	public int getBedDirection(IBlockAccess world, int x, int y, int z) {}

	public boolean isBedFoot(IBlockAccess world, int x, int y, int z) {}

	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {}

	public boolean isLeaves(IBlockAccess world, int x, int y, int z) {}

	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {}

	public boolean isWood(IBlockAccess world, int x, int y, int z) {}

	public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {}

	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {}

	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {}

	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {}

	public boolean isFoliage(IBlockAccess world, int x, int y, int z) {}

	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection side, IPlantable plantable) {}

	public boolean isFertile(World world, int x, int y, int z) {}

	public int getLightOpacity(IBlockAccess world, int x, int y, int z) {}

	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {}

	public float getEnchantPowerBonus(World world, int x, int y, int z) {}

	public boolean recolourBlock(World world, int x, int y, int z, ForgeDirection side, int color) {}

	public int getExpDrop(IBlockAccess world, int metadata, int fortune) {}

	public boolean shouldCheckWeakPower(IBlockAccess world, int x, int y, int z, int side) {}

	public boolean getWeakChanges(IBlockAccess world, int x, int y, int z) {}

	//TODO: Harvest Extensions*/



	/* Enumerators */

	public enum EnumBlockType {
		STORAGE,
		ORE,
		SOIL_ORE
	}
}