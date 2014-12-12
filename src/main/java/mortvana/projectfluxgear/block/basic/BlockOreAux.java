package mortvana.projectfluxgear.block.basic;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.block.basic.itemblock.ItemBlockOreAux;
import mortvana.projectfluxgear.common.FluxGearContent;
import mortvana.projectfluxgear.common.ProjectFluxGear;

public class BlockOreAux extends Block {

	public BlockOreAux() {

		super(Material.rock);
		setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypeMetal).setCreativeTab(ProjectFluxGear.tab).setBlockName("projectfluxgear.oreAux");

		setHarvestLevel("pickaxe", 2, 0);
		setHarvestLevel("pickaxe", 2, 1);
		setHarvestLevel("pickaxe", 2, 2);
		setHarvestLevel("pickaxe", 2, 3);
		setHarvestLevel("pickaxe", 2, 4);
		setHarvestLevel("pickaxe", 3, 5);
		setHarvestLevel("pickaxe", 2, 6);
		setHarvestLevel("pickaxe", 2, 7);
		setHarvestLevel("pickaxe", 2, 8);
		setHarvestLevel("pickaxe", 1, 9);
		setHarvestLevel("pickaxe", 1, 10);
		setHarvestLevel("pickaxe", 2, 11);
		setHarvestLevel("pickaxe", 2, 12);
		setHarvestLevel("pickaxe", 3, 13);
		setHarvestLevel("pickaxe", 3, 14);
		setHarvestLevel("pickaxe", 3, 15);
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
	public int damageDropped(int i) {

		return i;
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		return TEXTURES[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {

		for (int i = 0; i < NAMES.length; i++) {
			TEXTURES[i] = ir.registerIcon("projectfluxgear:ore/ore" + StringHelper.titleCase(NAMES[i]));
		}
	}


	public boolean preInit() {

		GameRegistry.registerBlock(this, ItemBlockOreAux.class, "OreAux");

		FluxGearContent.oreCinnabar = new ItemStack(this, 1, 0);
		FluxGearContent.orePitchblende = new ItemStack(this, 1, 1);
		FluxGearContent.oreMonazite = new ItemStack(this, 1, 2);
		FluxGearContent.oreNiedermayrite = new ItemStack(this, 1, 3);
		FluxGearContent.oreGreenockite = new ItemStack(this, 1, 4);
		FluxGearContent.oreGaotaiite = new ItemStack(this, 1, 5);
		FluxGearContent.oreOsarsite = new ItemStack(this, 1, 6);
		FluxGearContent.oreZnamenskyite = new ItemStack(this, 1, 7);
		FluxGearContent.oreGallobeudanite = new ItemStack(this, 1, 8);
		FluxGearContent.oreTertahedrite = new ItemStack(this, 1, 9);
		FluxGearContent.oreTennantite = new ItemStack(this, 1, 10);
		FluxGearContent.oreSantafeite = new ItemStack(this, 1, 11);
		FluxGearContent.oreMagnetite = new ItemStack(this, 1, 12);
		FluxGearContent.oreDioptase = new ItemStack(this, 1, 13);
		FluxGearContent.orePyrope = new ItemStack(this, 1, 14);
		FluxGearContent.oreMyuvil = new ItemStack(this, 1, 15);
		return true;
	}

	public static final String[] NAMES = { "cinnabar", "pitchblende", "monazite", "niedermayrite", "greenockite", "gaotaiite", "osarsite", "znamenskyite", "gallobeudanite", "tetrahedrite", "tennantite", "santafeite", "magnetite", "dioptase", "pyrope", "myuvil"};
	public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
	public static final int[] LIGHT =  { 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 8, 4, 4};
	public static final int[] RARITY = { 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 2, 2, 1};
}
