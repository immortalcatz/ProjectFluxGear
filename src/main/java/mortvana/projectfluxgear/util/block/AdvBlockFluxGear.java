package mortvana.projectfluxgear.util.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.util.helper.StringHelper;

/**
 * An advanced version of BlockFluxGear using metadata arrays to initialize common things internally.
 *
 * @author Mortvana
 */
public class AdvBlockFluxGear extends BlockFluxGear {

	/**
	 * The only way, currently, to initialize a block with metadata things and automatic texture registration.
	 * Uses common for a type defaults.
	 *
	 * @param material The material of the block.
	 * @param tab      The creative tab the block is under.
	 * @param type     Use a default type for ease of use.
	 */
	public AdvBlockFluxGear(Material material, CreativeTabs tab, EnumBlockType type, String[] names, IIcon[] textures, float[] hardness, float[] resistance, int[] light, String textureLocation) {
		super(material, tab, type);

		this.names = names;
		this.textures = textures;
		this.hardness = hardness;
		this.resistance = resistance;
		blockLight = light;
		this.textureLocation = textureLocation;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {

		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		return blockLight[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {

		return hardness[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {

		return resistance[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		return textures[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {

		for (int i = 0; i < names.length; i++) {
			textures[i] = ir.registerIcon(textureLocation + StringHelper.titleCase(names[i]));
		}
	}

	public String[] names;
	public IIcon[] textures;
	public float[] hardness;
	public float[] resistance;
	public int[] blockLight;
	public String textureLocation;
}