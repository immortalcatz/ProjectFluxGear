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

public class BlockAlloyAux extends BlockFluxGear {

	public BlockAlloyAux() {
		super(Material.iron, ProjectFluxGear.tabResources, EnumBlockType.STORAGE);
		setBlockName("projectfluxgear.alloyAux");
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
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {

		if (world.getBlockMetadata(x, y, z) == 5) {
			return 7;
		} else
			if (world.getBlockMetadata(x, y, z) == 12) {
				return 15;
			} else {
				return 0;
			}
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

	public static final String[] NAMES = {"technomancy", "resonantTechnomancy", "tungstenBlazing", "platinumGelid", "silverLuminous", "electrumFlux", "molybdenumResonant", "chromiumCarbide", "bismuthBronzeColdfire", "pyrum", "gelinum", "lumium", "signalum", "enderium", "carbonite", "therminate"};
	public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
	public static final int[] LIGHT = {4, 8, 12, 4, 15, 7, 4, 2, 15, 12, 4, 15, 7, 4, 2, 15};
	public static final float[] HARDNESS = {7, 11, 13, 8, 8, 8, 13, 11, 16, 11, 6, 5, 5, 40, 7, 13};
	public static final float[] RESISTANCE = {16, 128, 135, 85, 85, 85, 192, 192, 256, 35, 11, 9, 9, 120, 64, 128};
	public static final int[] RARITY = {1, 2, 2, 2, 2, 2, 3, 2, 3, 1, 1, 1, 1, 2, 1, 2};
}
