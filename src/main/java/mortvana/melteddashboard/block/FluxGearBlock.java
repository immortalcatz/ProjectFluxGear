package mortvana.melteddashboard.block;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.helpers.StringHelper;

/**
 *  A relatively easy to use, yet advanced version of Block for use in most situations.
 *  Contains automagic setting of meta-sensitive hardness, resistance, light emissions, redstone signals,
 *  beacon base-ness, mob spawnability, creative tabs, names, flammability, plant sustaining, and more!
 *
 *  @author Mortvana
 */
public class FluxGearBlock extends Block {

	public String textureLocation;
	public IIcon[] icons;
	public String[] textures;
	public String[] names;
	public float[] hardness;
	public float[] resistance;
	public int[] light;
	public int[] signal;
	public boolean canSpawn = true;
	public boolean beaconBase = false;
	public boolean isColorized = false;
	public boolean[] metaSpawn;
	public boolean[] metaBeacon;
	public int[] colors;
	public List<ItemStack>[] droppedBlocks;
	public int metaBlocks;
	public int[] harvestLevels;
	public String[] harvestTools;
	public MapColor[] mapColors;
	public IIcon[][] sidedIcons;
	public int mobilityFlag = 0;

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
	 * The simple way to initialize a block and add it to a creative tab, while also setting
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
	public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type) {
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
	public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type, String[] names, String [] textures, float[] hardness, float[] resistance, int[] light, String textureLocation) {
		this(material, tab, type);

		this.names = names;
		this.textures = textures;
		this.hardness = hardness;
		this.resistance = resistance;
		this.light = light;
		this.textureLocation = textureLocation;
	}


	/* Hardness Setters */
	public FluxGearBlock setDefaultHardness(float hardness) {
		super.setHardness(hardness);
		return this;
	}

	public FluxGearBlock setHardness(float hardness, float... values) {
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

	public FluxGearBlock setHardness(float hardness, Collection<Float> values) {
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

	public FluxGearBlock setHardness(Map<Integer, Float> values) {
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

	public FluxGearBlock setMetaHardness(int metadata, float hardness) {
		this.hardness[metadata] = hardness;
		return this;
	}


	/* Resistance Setters */
	public FluxGearBlock setDefaultResistance(float resistance) {
		super.setResistance(resistance);
		return this;
	}

	public FluxGearBlock setResistance(float resistance, float... values) {
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

	public FluxGearBlock setResistance(float resistance, Collection<Float> values) {
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

	public FluxGearBlock setResistance(Map<Integer, Float> values) {
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

	public FluxGearBlock setMetaResistance(int metadata, float resistance) {
		this.resistance[metadata] = resistance;
		return this;
	}

	/* Light Setters */

	/* Redstone Signal Setters */

	/* Mob Spawn Setters */

	/* Beacon Base Setters */

	/* Colorizer Setters */



	/* Getters */
	//TODO
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return hardness[world.getBlockMetadata(x, y, z)];
	}

	//TODO
	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		return resistance == null ? : resistance[world.getBlockMetadata(x, y, z)];
	}


	/* Redstone Getters */
	//TODO
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {}

	//TODO
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z) {}

	//TODO




	/* Color Getters */
	/*//TODO
	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {}

	//TODO
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1) {}

	//TODO
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {}*/

	/* Textures */
	//TODO
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		for (int i = 0; i < names.length; i++) {
			icons[i] = iconRegister.registerIcon(textureLocation + StringHelper.toCamelCase(names[i]));
		}
	}

	//TODO
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return sidedIcons[metadata][side];
	}

	/* Block Breaking */
	//TODO
	@Override
	public int damageDropped(int i) {
		return i;
	}

	/*//TODO Maybe?
	@Override
	public int quantityDropped(Random random) { }

	//TODO Maybe?
	public Item getItemDropped(int par1, Random random, int par3) {}

	//TODO
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune) {}

	//TODO
	public void dropBlockAsItem(World world, int x, int y, int z, ItemStack itemstack) {}

	//TODO
	public void dropXpOnBlockBreak(World world, int x, int y, int z, int amount) {}

	public boolean canReplace(World world, int x, int y, int z, int par5, ItemStack itemstack) {}

	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int par5) {}

	public boolean canPlaceBlockAt(World world, int x, int y, int z) {}

	public int quantityDroppedWithBonus(int par1, Random random) {}*/

	/* UNSORTED */

	/*public String getUnlocalizedName() {}

	public int getMobilityFlag() {
		return mobilityFlag;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {}

	public int getDamageValue(World world, int x, int y, int z) {}

	public void getSubBlocks(Item item, CreativeTabs tab, List list) {}

	public boolean canDropFromExplosion(Explosion explosion) {}

	@SideOnly(Side.CLIENT)
	public String getTextureName() {}*/

	/* Forge Hooked */

	/*public int getLightValue(IBlockAccess world, int x, int y, int z) {}

	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {}

	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {}

	public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {}

	public boolean isBurning(IBlockAccess world, int x, int y, int z) {}

	public boolean isAir(IBlockAccess world, int x, int y, int z) {}

	public boolean canHarvestBlock(IBlockAccess world, int x, int y, int z) {}

	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection side) {}

	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection side) {}

	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection side) {}

	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {}

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

	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {}

	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {}

	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {}

	public boolean canRenderInPass(int pass) {}

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

	public boolean getWeakChanges(IBlockAccess world, int x, int y, int z) {}*/

	//TODO: Harvest Extensions



	/* Enumerators */

	public static enum EnumBlockType {
		STORAGE,
		ORE,
		SOIL_ORE
	}
}