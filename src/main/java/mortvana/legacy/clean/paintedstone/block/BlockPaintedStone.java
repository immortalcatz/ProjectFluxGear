package mortvana.legacy.clean.paintedstone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.legacy.errored.core.common.ProjectFluxGear;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;
import java.util.Random;

public class BlockPaintedStone extends Block {
    public final String textureName;
    public final String localName;
    public IIcon[] icons;
    Block dropBlock;

    public BlockPaintedStone(Material material, float hardness, String texture, String name) {
        super(material);
        setHardness(hardness).setCreativeTab(CreativeTabs.tabDecorations);
        textureName = texture;
        localName = name;
        dropBlock = this;
    }

    public BlockPaintedStone(Material material, float hardness, String texture, String name, Block dropBlock) {
        this(material, hardness, texture, name);
        this.dropBlock = dropBlock;
    }

    public BlockPaintedStone(String texture, String name) {
        this(Material.rock, 1.5F, texture, name);
    }

    public String getUnlocalizedName() {
        return "tile." + localName;
    }

    public int damageDropped(int meta) {
        return meta;
    }

    public Block blockDropped(int par1, Random par2Random, int par3) {
        return dropBlock;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons = new IIcon[16];

        for(int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.registerIcon("mortvana.mechstoneworks:" + textureName + "_" + ProjectFluxGear.colorNames[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return meta < icons.length ? icons[meta] : icons[0];
    }

    public void getSubBlocks(Item block, CreativeTabs tab, List list) {
        for(int iter = 0; iter < this.icons.length; ++iter) {
            list.add(new ItemStack(block, 1, iter));
        }

    }
}
