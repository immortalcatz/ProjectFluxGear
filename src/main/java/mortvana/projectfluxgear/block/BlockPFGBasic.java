package mortvana.projectfluxgear.block;

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

import mortvana.fluxgearcore.block.AdvBlockFluxGear;
import mortvana.fluxgearcore.block.EnumBlockType;
import mortvana.fluxgearcore.block.FallingBlockFluxGear;
import mortvana.fluxgearcore.common.FluxGearCore;
import mortvana.fluxgearcore.util.helper.StringHelper;

import mortvana.projectfluxgear.common.ProjectFluxGear;

public class BlockPFGBasic {
	public BlockPFGBasic() {}

	public static final String TEXTURE_LOCATION_DEFAULT = "projectfluxgear:";
	public static final String TEXTURE_LOCATION_BLOCK = "projectfluxgear:storage/block";
	public static final String TEXTURE_LOCATION_ORE = "projectfluxgear:ore/ore";
	public static final String TEXTURE_LOCATION_POOR_ORE = "projectfluxgear:ore/orePoor";
	public static final String TEXTURE_LOCATION_GRAVEL_ORE = "projectfluxgear:ore/oreGravel";


	public static final String[] NAMES_ORES_MAIN = {"chalcocite", "cassiterite", "galena", "acanthite", "garnierite", "sphalerite", "bismuthinite", "pyrolusite", "bauxite", "cooperite", "braggite", "molybdenite", "cobaltite", "wolframite", "ilmenite", "chromite"};
	public static final int[] LIGHT_ORES_MAIN = {0, 0, 1, 4, 0, 0, 2, 0, 0, 4, 1, 2, 0, 0, 0, 0};
	public static final int[] RARITY_ORES_MAIN = {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 1, 1, 0};

	public static final String[] NAMES_ORES_AUX = {"cinnabar", "pitchblende", "monazite", "niedermayrite", "greenockite", "gaotaiite", "osarsite", "znamenskyite", "gallobeudanite", "tetrahedrite", "tennantite", "santafeite", "magnetite", "dioptase", "pyrope", "myuvil"};
	public static final int[] LIGHT_ORES_AUX = {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 8, 4, 4};
	public static final int[] RARITY_ORES_AUX = {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 2, 2, 1};


	//TODO: More Redstone Signal Stuff

	public static class BlockStorageMain extends AdvBlockFluxGear {

		public BlockStorageMain() {
			super(Material.iron, ProjectFluxGear.tabResources, EnumBlockType.STORAGE, NAMES, HARDNESS, RESISTANCE, LIGHT, TEXTURE_LOCATION_BLOCK);
			setBlockName("projectfluxgear.storageMain");
		}

		public static final String[] NAMES = {"copper", "tin", "lead", "silver", "nickel", "zinc", "bismuth", "manganese", "aluminium", "platinum", "palladium", "molybdenum", "cobalt", "tungsten", "titanium", "chromium"};
		public static final int[] LIGHT = {0, 0, 0, 4, 0, 0, 2, 0, 2, 4, 2, 0, 0, 0, 0, 0};
		public static final float[] HARDNESS = {5, 5, 4, 5, 7, 5, 5, 5, 5, 5, 5, 6, 7, 10, 8, 7};
		public static final float[] RESISTANCE = {6, 6, 12, 6, 6, 6, 8, 6, 8, 6, 6, 7, 8, 25, 12, 10};
		public static final int[] RARITY = {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 1, 1, 1};

	}

	public static class BlockStorageAux extends AdvBlockFluxGear {

		public BlockStorageAux() {
			super(Material.iron, ProjectFluxGear.tabResources, EnumBlockType.STORAGE, NAMES, HARDNESS, RESISTANCE, LIGHT, TEXTURE_LOCATION_BLOCK);
			setBlockName("projectfluxgear.storageAux");
		}

		public static final String[] NAMES = {"antimony", "arsenic", "neodymium", "tesseractium", "cadmium", "tellurium", "osmium", "iridium", "indium", "antimonialBronze", "arsenicalBronze", "vanadium", "unobtainium", "dioptase", "pyrope", "myuvil"};
		public static final int[] LIGHT = {0, 0, 0, 7, 0, 0, 1, 4, 0, 0, 0, 1, 15, 8, 4, 4};
		public static final float[] HARDNESS = {3, 3, 5, 8, 5, 5, 11, 13, 5, 6, 6, 7, 42, 7, 7, 4};
		public static final float[] RESISTANCE = {5, 5, 8, 42, 6, 6, 13, 16, 6, 8, 8, 9, 500, 13, 13, 6};
		public static final int[] RARITY = {0, 0, 0, 2, 0, 0, 1, 2, 0, 0, 0, 1, 3, 2, 2, 1};
	}

	public static class BlockAlloyMain extends AdvBlockFluxGear {

		public BlockAlloyMain() {
			super(Material.iron, ProjectFluxGear.tabResources, EnumBlockType.STORAGE, NAMES, HARDNESS, RESISTANCE, LIGHT, TEXTURE_LOCATION_BLOCK);
			setBlockName("projectfluxgear.alloyMain");
		}

		public static final String[] NAMES = {"bronze", "brass", "invar", "bismuthBronze", "cupronickel", "aluminiumBrass", "electrum", "dullRedsolder", "redsolder", "highCarbonSteel", "steel", "hsla", "stainlessSteel", "tungstenSteel", "electriplatinum", "mithril"};
		public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
		public static final int[] LIGHT = {0, 0, 0, 2, 0, 2, 4, 2, 2, 0, 0, 0, 0, 0, 4, 2};
		public static final float[] HARDNESS = {5, 5, 6, 5, 5, 5, 5, 5, 5, 7, 8, 8, 8, 13, 6, 5};
		public static final float[] RESISTANCE = {7, 6, 8, 8, 6, 6, 6, 6, 6, 12, 13, 13, 13, 42, 8, 10};
		public static final int[] RARITY = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
	}

	public static class BlockAlloyAux extends AdvBlockFluxGear {

		public BlockAlloyAux() {
			super(Material.iron, ProjectFluxGear.tabResources, EnumBlockType.STORAGE, NAMES, HARDNESS, RESISTANCE, LIGHT, TEXTURE_LOCATION_BLOCK);
			setBlockName("projectfluxgear.alloyAux");
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

		public static final String[] NAMES = {"technomancy", "resonantTechnomancy", "tungstenBlazing", "platinumGelid", "silverLuminous", "electrumFlux", "molybdenumResonant", "chromiumCarbide", "bismuthBronzeColdfire", "pyrum", "gelinum", "lumium", "signalum", "enderium", "carbonite", "therminate"};
		public static final int[] LIGHT = {4, 8, 12, 4, 15, 7, 4, 2, 15, 12, 4, 15, 7, 4, 2, 15};
		public static final float[] HARDNESS = {7, 11, 13, 8, 8, 8, 13, 11, 16, 11, 6, 5, 5, 40, 7, 13};
		public static final float[] RESISTANCE = {16, 128, 135, 85, 85, 85, 192, 192, 256, 35, 11, 9, 9, 120, 64, 128};
		public static final int[] RARITY = {1, 2, 2, 2, 2, 2, 3, 2, 3, 1, 1, 1, 1, 2, 1, 2};
	}

	public static class BlockStorageAlch extends AdvBlockFluxGear {

		public BlockStorageAlch() {
			super(Material.iron, ProjectFluxGear.tabResources, EnumBlockType.STORAGE, NAMES, HARDNESS, RESISTANCE, LIGHT, TEXTURE_LOCATION_BLOCK);
			setBlockName("projectfluxgear.storageAux");
		}

		public static final String[] NAMES = {"nullmetal", "iocarbide", "cryocarbide", "pyrocarbide", "tenebride", "illuminide", "zythoferride", "crystalFlux", "lapiquartz", "rust", "whitePointStar", "voidInfernoStar", "sulfur", "saltpeter", "mithrilFlux", "mithrilTinker"};
		public static final int[] LIGHT = {0, 0, 4, 4, 0, 15, 7, 4, 2, 0, 15, 15, 0, 0, 7, 15};
		public static final float[] HARDNESS = {1, 5, 5, 5, 7, 7, 11, 5, 5, 0.6F, 8, 8, 5, 5, 8, 11};
		public static final float[] RESISTANCE = {1, 6, 7, 7, 10, 10, 42, 8, 8, 1, 42, 507, 5, 5, 32, 64};
		public static final int[] RARITY = {0, 0, 0, 0, 1, 1, 2, 1, 1, 0, 2, 3, 0, 0, 1, 2};
	}

	public static class BlockStorageAdv extends AdvBlockFluxGear {

		public BlockStorageAdv() {
			super(Material.iron, ProjectFluxGear.tabResources, EnumBlockType.STORAGE, NAMES, HARDNESS, RESISTANCE, LIGHT, TEXTURE_LOCATION_BLOCK);
			setBlockName("projectfluxgear.storageAdv");
		}

		public static final String[] NAMES = {"thorium", "uranium235", "uranium238", "magnetite", "neodymiumM", "ironM", "manganeseM", "cobaltM", "nickelM", "invarM", "highCarbonSteelM", "steel", "hsla", "amber", "nichrome", "polycarbide"};
		public static final int[] LIGHT = {2, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		public static final float[] HARDNESS = {5, 5, 5, 5, 5, 5, 5, 7, 7, 6, 7, 8, 8, 4, 2, 42};
		public static final float[] RESISTANCE = {5, 5, 5, 8, 8, 10, 6, 8, 6, 8, 12, 13, 13, 6, 11, 1000};
		public static final int[] RARITY = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 3, 3};
	}

	//BlockStorageRandom



	//TODO: AdvFallingBlockFluxGear
	public static class BlockGravelOreMain extends FallingBlockFluxGear {

		public BlockGravelOreMain() {
			super(FluxGearCore.soilOre, ProjectFluxGear.tabOres, EnumBlockType.SOIL_ORE);
			setBlockName("projectfluxgear.gravelOreMain");
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
			super(FluxGearCore.soilOre, ProjectFluxGear.tabOres, EnumBlockType.SOIL_ORE);
			setBlockName("projectfluxgear.gravelOreAux");
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

	public static class BlockEarthen extends AdvBlockFluxGear {
		public BlockEarthen() {
			super(FluxGearCore.soilOre, ProjectFluxGear.tabResources, EnumBlockType.SOIL_ORE, NAMES, HARDNESS, RESISTANCE, LIGHT, TEXTURE_LOCATION_DEFAULT);
			setBlockName("projectfluxgear.earthen");
		}

		public static final String[] NAMES = {"clayIridium", "clayIridiumPoor", "aluminosilicateSludge"};
		public static final int[] RARITY = { 2, 1, 0 };
		public static final float[] HARDNESS = {5, 5, 0.3F};
		public static final float[] RESISTANCE = { 5, 5, 5 };
		public static final int[] LIGHT = { 0, 0, 0 };
	}

	//BlockPFGGlass
}