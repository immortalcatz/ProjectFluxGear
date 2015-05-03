package mortvana.projectfluxgear.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

/**
 * An advanced version of Block for use is many common situations.
 *
 * @author Mortvana
 */
public class BlockFluxGear extends Block {

	/**
	 * The really simple way to initialize a block.
	 * Uses common defaults.
	 * @param material The material of the block.
	 * @param tab The creative tab the block is under.
	 */
	public BlockFluxGear(Material material, CreativeTabs tab) {
		super(material);
		setCreativeTab(tab);

		canSpawn = true;
		beaconBase = false;
		isColorized = false;
	}

	/**
	 * The simple way to initialize a block.
	 * Uses common for a type defaults.
	 * @param material The material of the block.
	 * @param tab The creative tab the block is under.
	 * @param type Use a default type for ease of use.
	 */
	public BlockFluxGear(Material material, CreativeTabs tab, EnumBlockType type) {
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
			ProjectFluxGear.logger.warn(wrong);
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
	public BlockFluxGear(Material material, CreativeTabs tab, boolean spawn, boolean base, boolean colorized) {
		super(material);
		setCreativeTab(tab);

		canSpawn = spawn;
		beaconBase = base;
		isColorized = colorized;
	}

	public String[] names;
	public IIcon[] textures;

	/*public BlockFluxGear setRenderType(int renderType) {

	}*/

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return canSpawn;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {

		return beaconBase;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return isColorized ? 1 : 0;
	}

	//TODO: Move Icon, Light, Hardness, and Blast Resistance to here
	/*@Override
	public IIcon getIcon(int side, int metadata) {

	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {

	}*/

	@Override
	public int damageDropped(int i) {
		return i;
	}

	public boolean isColorized;
	public boolean canSpawn;
	public boolean beaconBase;
	public static String wrong = "Mortvana registered a block wrong in Project Flux Gear... Derp";
}