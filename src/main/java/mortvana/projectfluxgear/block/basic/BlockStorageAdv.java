package mortvana.projectfluxgear.block.basic;

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

import mortvana.fluxgearcore.block.BlockFluxGear;
import mortvana.fluxgearcore.block.EnumBlockType;
import mortvana.fluxgearcore.util.helper.StringHelper;

import mortvana.projectfluxgear.common.ProjectFluxGear;

public class BlockStorageAdv extends BlockFluxGear {

	public BlockStorageAdv() {
		super(Material.iron, ProjectFluxGear.tabResources, EnumBlockType.STORAGE);
		setBlockName("projectfluxgear.storageAdv");
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {

		for (int i = 0; i < NAMES.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		return LIGHT[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {

		return HARDNESS[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {

		return RESISTANCE[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		return TEXTURES[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {

		for (int i = 0; i < NAMES.length; i++) {
			TEXTURES[i] = ir.registerIcon("projectfluxgear:storage/block" + StringHelper.titleCase(NAMES[i]));
		}
	}

	public static final String[] NAMES = {"thorium", "uranium235", "uranium238", "magnetite", "neodymiumM", "ironM", "manganeseM", "cobaltM", "nickelM", "invarM", "highCarbonSteelM", "steel", "hsla", "amber", "nichrome", "polycarbide"};
	public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
	public static final int[] LIGHT = {2, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static final float[] HARDNESS = {5, 5, 5, 5, 5, 5, 5, 7, 7, 6, 7, 8, 8, 4, 2, 42};
	public static final float[] RESISTANCE = {5, 5, 5, 8, 8, 10, 6, 8, 6, 8, 12, 13, 13, 6, 11, 1000};
	public static final int[] RARITY = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 3, 3};
}