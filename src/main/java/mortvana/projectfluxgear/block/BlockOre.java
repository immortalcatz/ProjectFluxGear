package mortvana.projectfluxgear.block;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.block.itemblock.ItemBlockOre;
import mortvana.projectfluxgear.common.FluxGearContent;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import mortvana.projectfluxgear.common.ProjectFluxGear;

public class BlockOre extends Block {

    public BlockOre() {

        super(Material.rock);
        setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypeMetal).setCreativeTab(ProjectFluxGear.tab).setBlockName("thermaltinkerer.ore");

        setHarvestLevel("pickaxe", 1, 0);
        setHarvestLevel("pickaxe", 1, 1);
        setHarvestLevel("pickaxe", 1, 2);
        setHarvestLevel("pickaxe", 2, 3);
        setHarvestLevel("pickaxe", 2, 4);
        setHarvestLevel("pickaxe", 2, 5);
        setHarvestLevel("pickaxe", 3, 6);
        setHarvestLevel("pickaxe", 1, 7);
        setHarvestLevel("pickaxe", 2, 8);
        setHarvestLevel("pickaxe", 2, 9);
        setHarvestLevel("pickaxe", 2, 10);
        setHarvestLevel("pickaxe", 3, 11);
        setHarvestLevel("pickaxe", 3, 12);
        setHarvestLevel("pickaxe", 3, 13);

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
            TEXTURES[i] = ir.registerIcon("thermaltinkerer:ore/ore" + StringHelper.titleCase(NAMES[i]));
        }
    }


    public boolean preInit() {

        GameRegistry.registerBlock(this, ItemBlockOre.class, "Ore");

        FluxGearContent.oreSphalerite = new ItemStack(this, 1, 0);
        FluxGearContent.oreBismuthinite = new ItemStack(this, 1, 1);
        FluxGearContent.orePyrolusite = new ItemStack(this, 1, 2);
        FluxGearContent.oreBraggite = new ItemStack(this, 1, 3);
        FluxGearContent.oreMolybdenite = new ItemStack(this, 1, 4);
        FluxGearContent.oreCobaltite = new ItemStack(this, 1, 5);
        FluxGearContent.oreWolframite = new ItemStack(this, 1, 6);
        FluxGearContent.oreBauxite = new ItemStack(this, 1, 7);
        FluxGearContent.oreChromite = new ItemStack(this, 1, 8);
        FluxGearContent.oreIlmenite = new ItemStack(this, 1, 9);
        FluxGearContent.oreMagnetite = new ItemStack(this, 1, 10);
        FluxGearContent.oreDioptase = new ItemStack(this, 1, 11);
        FluxGearContent.orePyrope = new ItemStack(this, 1, 12);
        FluxGearContent.oreMyuvil = new ItemStack(this, 1, 13);

        return true;
    }

    public static final String[] NAMES = { "sphalerite", "bismuthinite", "pyrolusite", "braggite", "molybdenite", "cobaltite", "wolframite", "bauxite", "chromite", "ilmenite", "magnetite", "dioptase", "pyrope", "myuvil" };
    public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
    public static final int[] LIGHT =  { 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 8, 4, 4 };
    public static final int[] RARITY = { 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 2, 2, 1 };
}
