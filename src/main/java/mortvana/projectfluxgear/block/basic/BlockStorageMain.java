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
import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.common.ProjectFluxGear;

public class BlockStorageMain extends BlockFluxGear {

	public BlockStorageMain() {
		super(Material.iron, ProjectFluxGear.tab, "BLOCK");
		setBlockName("projectfluxgear.storageMain");
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

	public static final String[] NAMES = {"copper", "tin", "lead", "silver", "nickel", "zinc", "bismuth", "manganese", "aluminium", "platinum", "palladium", "molybdenum", "cobalt", "tungsten", "titanium", "chromium"};
	public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
	public static final int[] LIGHT = {0, 0, 0, 4, 0, 0, 2, 0, 2, 4, 2, 0, 0, 0, 0, 0};
	public static final float[] HARDNESS = {5, 5, 4, 5, 7, 5, 5, 5, 5, 5, 5, 6, 7, 10, 8, 7};
	public static final float[] RESISTANCE = {6, 6, 12, 6, 6, 6, 8, 6, 8, 6, 6, 7, 8, 25, 12, 10};
	public static final int[] RARITY = {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 1, 1, 1};

}
