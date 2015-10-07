package mortvana.melteddashboard.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.util.helpers.StringHelper;

/**
 * A simple, yet advanced version of Block for use in many common situations.
 *
 * @author Mortvana
 */
public class FluxGearBlock extends Block {

	public String textureLocation;

	public IIcon[] icons;
	public String[] names;

	public boolean canSpawn = true;
	public boolean beaconBase = false;
	public boolean isColorized = false;
	public boolean[] metaSpawn;
	public boolean[] metaBeacon;
	public int[] colors;

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


	//public FluxGearBlock(Material material, CreativeTabs tab, EnumBlockType type)

	@Override
	public int damageDropped(int i) {
		return i;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		//for (int i = 0; i < names.length; i++) {
		//	icons[i] = iconRegister.registerIcon(textureLocation + StringHelper.toCamelCase(names[i]));
		//}
	}

}