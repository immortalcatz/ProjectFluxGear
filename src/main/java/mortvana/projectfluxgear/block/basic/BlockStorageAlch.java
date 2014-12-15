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

public class BlockStorageAlch extends BlockFluxGear {

	public BlockStorageAlch() {
		super(Material.iron, ProjectFluxGear.tabResources, EnumBlockType.STORAGE);
		setBlockName("projectfluxgear.storageAux");
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

	public static final String[] NAMES = {"nullmetal", "iocarbide", "cryocarbide", "pyrocarbide", "tenebride", "illuminide", "zythoferride", "crystalFlux", "lapiquartz", "rust", "whitePointStar", "voidInfernoStar", "sulfur", "saltpeter", "mithrilFlux", "mithrilTinker"};
	public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
	public static final int[] LIGHT = {0, 0, 4, 4, 0, 15, 7, 4, 2, 0, 15, 15, 0, 0, 7, 15};
	public static final float[] HARDNESS = {1, 5, 5, 5, 7, 7, 11, 5, 5, 0.6F, 8, 8, 5, 5, 8, 11};
	public static final float[] RESISTANCE = {1, 6, 7, 7, 10, 10, 42, 8, 8, 1, 42, 507, 5, 5, 32, 64};
	public static final int[] RARITY = {0, 0, 0, 0, 1, 1, 2, 1, 1, 0, 2, 3, 0, 0, 1, 2};
}
