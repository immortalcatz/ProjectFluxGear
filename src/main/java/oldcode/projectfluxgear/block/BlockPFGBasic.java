package oldcode.projectfluxgear.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import oldcode.projectfluxgear.util.block.AdvBlockFluxGear;
import oldcode.projectfluxgear.util.block.EnumBlockType;
import oldcode.projectfluxgear.util.block.FallingBlockFluxGear;
import mortvana.dashboard.common.MeltedDashboardLib;
import oldcode.projectfluxgear.util.helper.StringHelper;

import oldcode.projectfluxgear.core.ProjectFluxGear;

public class BlockPFGBasic {
	public BlockPFGBasic() {}

	//TODO: More Redstone Signal Stuff





	public static class BlockStorageMain = new AdvBlockFluxGear(Material.iron, ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_MAIN, TEXTURES_FULL, HARDNESS_STORE_MAIN, RESISTANCE_STORE_MAIN, LIGHT_STORE_MAIN, TEXTURE_LOCATION_BLOCK);
			setBlockName("mortvana.projectfluxgear.storageMain");
		}
	}

	public static class BlockStorageAux = new AdvBlockFluxGear(Material.iron, ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_AUX, TEXTURES_FULL, HARDNESS_STORE_AUX, RESISTANCE_STORE_AUX, LIGHT_STORE_AUX, TEXTURE_LOCATION_BLOCK);
			setBlockName("mortvana.projectfluxgear.storageAux");
		}
	}

	public static class BlockAlloyMain = new AdvBlockFluxGear((Material.iron, ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_ALLOY_MAIN, TEXTURES_FULL, HARDNESS_ALLOY_MAIN, RESISTANCE_ALLOY_MAIN, LIGHT_ALLOY_MAIN, TEXTURE_LOCATION_BLOCK);
			setBlockName("mortvana.projectfluxgear.alloyMain");
		}
	}

	public static class BlockAlloyAux extends AdvBlockFluxGear {

		public BlockAlloyAux() {
			super(Material.iron, ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_ALLOY_AUX, TEXTURES_FULL, HARDNESS_ALLOY_AUX, RESISTANCE_ALLOY_AUX, LIGHT_ALLOY_AUX, TEXTURE_LOCATION_BLOCK);
			setBlockName("mortvana.projectfluxgear.alloyAux");
		}

		@Override
		public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {

			if (world.getBlockMetadata(x, y, z) == 5) {
				return 7;
			} else if (world.getBlockMetadata(x, y, z) == 12) {
				return 15;
			} else {
				return 0;
			}
		}

	}

	public static class BlockStorageAlch extends AdvBlockFluxGear {
		public BlockStorageAlch() {
			super(Material.iron, ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_ALCH, TEXTURES_FULL, HARDNESS_STORE_ALCH, RESISTANCE_STORE_ALCH, LIGHT_STORE_ALCH, TEXTURE_LOCATION_BLOCK);
			setBlockName("mortvana.projectfluxgear.storageAux");
		}
	}

	public static class BlockStorageAdv extends AdvBlockFluxGear {
		public BlockStorageAdv() {
			super(Material.iron, ProjectFluxGear.tabMaterials, EnumBlockType.STORAGE, NAMES_STORE_ADV, TEXTURES_FULL, HARDNESS_STORE_ADV, RESISTANCE_STORE_ADV, LIGHT_STORE_ADV, TEXTURE_LOCATION_BLOCK);
			setBlockName("mortvana.projectfluxgear.storageAdv");
		}
	}

	//BlockStorageRandom

	public static class BlockEarthen extends AdvBlockFluxGear {
		public BlockEarthen() {
			super(MeltedDashboardLib.soilOre, ProjectFluxGear.tabMaterials, EnumBlockType.SOIL_ORE, NAMES_EARTH, TEXTURES_EARTH, HARDNESS_EARTH, RESISTANCE_EARTH, LIGHT_EARTH, TEXTURE_LOCATION_DEFAULT);
			setBlockName("mortvana.projectfluxgear.earthen");
		}
	}

	public static class BlockPoorOreMain extends AdvBlockFluxGear {
		public BlockPoorOreMain() {
			super(Material.rock, ProjectFluxGear.tabWorld, EnumBlockType.ORE, NAMES_ORES_MAIN, TEXTURES_FULL, HARDNESS_ORES, RESISTANCE_ORES, LIGHT_ORES_MAIN, TEXTURE_LOCATION_POOR_ORE);
			setBlockName("mortvana.projectfluxgear.poorOreMain");
		}
	}

	public static class BlockPoorOreAux extends AdvBlockFluxGear {
		public BlockPoorOreAux() {
			super(Material.rock, ProjectFluxGear.tabWorld, EnumBlockType.ORE, NAMES_ORES_AUX, TEXTURES_FULL, HARDNESS_ORES, RESISTANCE_ORES, LIGHT_ORES_AUX, TEXTURE_LOCATION_POOR_ORE);
			setBlockName("mortvana.projectfluxgear.poorOreAux");
		}
	}

	//TODO: AdvFallingBlockFluxGear
	public static class BlockGravelOreMain extends FallingBlockFluxGear {

		public BlockGravelOreMain() {
			super(MeltedDashboardLib.soilOre, ProjectFluxGear.tabWorld, EnumBlockType.SOIL_ORE);
			setBlockName("mortvana.projectfluxgear.gravelOreMain");
		}

		@Override
		public void getSubBlocks(Item item, CreativeTabs tab, List list) {
			for (int i = 0; i < NAMES_ORES_MAIN.length; i++) {
				list.add(new ItemStack(item, 1, i));
			}
		}

		@Override
		public int getLightValue(IBlockAccess world, int x, int y, int z) {

			return LIGHT_ORES_MAIN[world.getBlockMetadata(x, y, z)];
		}

		@Override
		public IIcon getIcon(int side, int metadata) {

			return TEXTURES[metadata];
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister ir) {

			for (int i = 0; i < NAMES_ORES_MAIN.length; i++) {
				TEXTURES[i] = ir.registerIcon(TEXTURE_LOCATION_GRAVEL_ORE + StringHelper.titleCase(NAMES_ORES_MAIN[i]));
			}
		}

		public static final IIcon[] TEXTURES = new IIcon[NAMES_ORES_MAIN.length];
	}

	public static class BlockGravelOreAux extends FallingBlockFluxGear {

		public BlockGravelOreAux() {
			super(MeltedDashboardLib.soilOre, ProjectFluxGear.tabWorld, EnumBlockType.SOIL_ORE);
			setBlockName("mortvana.projectfluxgear.gravelOreAux");
		}

		@Override
		public void getSubBlocks(Item item, CreativeTabs tab, List list) {

			for (int i = 0; i < NAMES_ORES_AUX.length; i++) {
				list.add(new ItemStack(item, 1, i));
			}
		}

		@Override
		public int getLightValue(IBlockAccess world, int x, int y, int z) {

			return LIGHT_ORES_AUX[world.getBlockMetadata(x, y, z)];
		}

		@Override
		public IIcon getIcon(int side, int metadata) {

			return TEXTURES[metadata];
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister ir) {

			for (int i = 0; i < NAMES_ORES_AUX.length; i++) {
				TEXTURES[i] = ir.registerIcon(TEXTURE_LOCATION_GRAVEL_ORE + StringHelper.titleCase(NAMES_ORES_AUX[i]));
			}
		}

		public static final IIcon[] TEXTURES = new IIcon[NAMES_ORES_AUX.length];
	}

	//BlockPFGGlass






}
