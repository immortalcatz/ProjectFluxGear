package mortvana.projectfluxgear.block.basic;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.block.basic.itemblock.ItemBlockOreMain;
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

public class BlockOreMain extends Block {

    public BlockOreMain() {

        super(Material.rock);
        setHardness(3.0F).setResistance(5.0F).setStepSound(soundTypeMetal).setCreativeTab(ProjectFluxGear.tab).setBlockName("projectfluxgear.oreMain");

        setHarvestLevel("pickaxe", 1, 0);
        setHarvestLevel("pickaxe", 1, 1);
        setHarvestLevel("pickaxe", 2, 2);
        setHarvestLevel("pickaxe", 2, 3);
        setHarvestLevel("pickaxe", 2, 4);
        setHarvestLevel("pickaxe", 1, 5);
        setHarvestLevel("pickaxe", 1, 6);
        setHarvestLevel("pickaxe", 1, 7);
        setHarvestLevel("pickaxe", 1, 8);
        setHarvestLevel("pickaxe", 2, 9);
        setHarvestLevel("pickaxe", 2, 10);
        setHarvestLevel("pickaxe", 2, 11);
        setHarvestLevel("pickaxe", 2, 12);
        setHarvestLevel("pickaxe", 3, 13);
        setHarvestLevel("pickaxe", 2, 14);
        setHarvestLevel("pickaxe", 2, 15);
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

        GameRegistry.registerBlock(this, ItemBlockOreMain.class, "OreMain");

        FluxGearContent.oreChalcocite = new ItemStack(this, 1, 0);
        FluxGearContent.oreCassiterite = new ItemStack(this, 1, 1);
        FluxGearContent.oreGalena = new ItemStack(this, 1, 2);
        FluxGearContent.oreAcanthite = new ItemStack(this, 1, 3);
        FluxGearContent.oreGarnierite = new ItemStack(this, 1, 4);
        FluxGearContent.oreSphalerite = new ItemStack(this, 1, 5);
        FluxGearContent.oreBismuthinite = new ItemStack(this, 1, 6);
        FluxGearContent.orePyrolusite = new ItemStack(this, 1, 7);
        FluxGearContent.oreBauxite = new ItemStack(this, 1, 8);
        FluxGearContent.oreCooperite = new ItemStack(this, 1, 9);
        FluxGearContent.oreBraggite = new ItemStack(this, 1, 10);
        FluxGearContent.oreMolybdenite = new ItemStack(this, 1, 11);
        FluxGearContent.oreCobaltite = new ItemStack(this, 1, 12);
        FluxGearContent.oreWolframite = new ItemStack(this, 1, 13);
        FluxGearContent.oreIlmenite = new ItemStack(this, 1, 14);
        FluxGearContent.oreChromite = new ItemStack(this, 1, 15);
        return true;
    }

    public static final String[] NAMES = { "chalcocite", "cassiterite", "galena", "acanthite", "garnierite", "sphalerite", "bismuthinite", "pyrolusite", "bauxite", "cooperite", "braggite", "molybdenite", "cobaltite", "wolframite", "ilmenite", "chromite"};
    public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
    public static final int[] LIGHT =  { 0, 0, 1, 4, 0, 0, 2, 0, 0, 2, 1, 2, 0, 0, 0, 0};
    public static final int[] RARITY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 1, 1, 0};
}
