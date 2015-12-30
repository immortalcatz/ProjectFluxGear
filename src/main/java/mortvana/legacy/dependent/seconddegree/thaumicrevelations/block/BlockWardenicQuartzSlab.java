package mortvana.legacy.dependent.seconddegree.thaumicrevelations.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.projectfluxgear.library.ContentLibrary;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.Random;

public class BlockWardenicQuartzSlab extends BlockSlab {

    public BlockWardenicQuartzSlab() {
        super(false, Material.rock);
        setUnlocalizedName("blockInfusedQuartzSlab");
        setCreativeTab(ContentLibrary.thaumicRevelationsTab);
        setStepSound(Block.soundTypeStone);
        setHardness(0.8F);
        setLightOpacity(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return FluxGearContent.blockInfusedQuartzNormal.getBlockTextureFromSide(par1);
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return Item.getItemFromBlock(FluxGearContent.blockInfusedQuartzSlab);
    }

    @Override
    public ItemStack createStackedBlock(int par1) {
        return new ItemStack(FluxGearContent.blockInfusedQuartzSlab);
    }

    @Override
    public String getFullSlabName(int var1) {
        return "tile.blockInfusedQuartzSlab";
    }
}