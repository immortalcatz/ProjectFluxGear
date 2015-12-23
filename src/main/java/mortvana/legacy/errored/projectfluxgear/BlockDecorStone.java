package mortvana.legacy.errored.projectfluxgear;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.legacy.errored.core.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockDecorStone extends Block {
    public String[] rockNames = BlockInformation.rockNames;
    public IIcon[] icons;
    public float[] hardness;
    public float[] resistance;
    public int[] blockLight;
    Block dropBlock;

    public String overlayType;
    @SideOnly(Side.CLIENT)
    public IIcon overlayTexture;

    public BlockDecorStone(Material material, CreativeTabs tab, float[] hardness, float[] resistance, int[] light, String overlayType, SoundType sound) {
        super(material);
        setCreativeTab(tab);
        setStepSound(sound);
        this.hardness = hardness;
        this.resistance = resistance;
        blockLight = light;
        dropBlock = this;
        this.overlayType = overlayType;

    }

    public BlockDecorStone(Material material, CreativeTabs tab, float[] hardness, float[] resistance, int[] light, String overlayType, SoundType sound, Block dropBlock) {
        this(material, tab, hardness, resistance, light, overlayType, sound);
        this.dropBlock = dropBlock;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < rockNames.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return blockLight[world.getBlockMetadata(x, y, z)];
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        return hardness[world.getBlockMetadata(x, y, z)];
    }

    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return resistance[world.getBlockMetadata(x, y, z)];
    }

    public Block blockDropped(int par1, Random random, int par3) {
        return dropBlock;
    }


    /* Convoluted Overlay Renderage Code */
    @Override
    public boolean renderAsNormalBlock() {
        // We don't render as a normal block, we are special and use a custom renderer
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        // Would return false if it was transparent, but it is just for overlays this time
        return true;
    }

    @Override
    public int getRenderType() {
        // We are a 1*1*1 cube, we just have TWO render passes
        return ClientProxy.dualPassCubeID;
    }

    @Override
    public boolean canRenderInPass(int pass) {
        // Set the static variable in our Client Proxy
        ClientProxy.renderPass = pass;
        // The block utilizes both passes, so it is always true
        return true;
    }

    @Override
    public int getRenderBlockPass() {
        // 0 for Solids; 1 for Alpha
        // This renders a darkening overlay, so it is alpha
        return 1;
    }

    public String getUnlocalizedName() {
        return "tile." + localName; //TODO: Fix this
    }

    public int damageDropped(int meta) {
        return meta;
    }

    public IIcon getOverlayTexture() {
        return overlayTexture;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons = new IIcon[rockNames.length];
        for (int i = 0; i < icons.length; ++i) {
            icons[i] = iconRegister.registerIcon("mechanicsstoneworks:rocks/" + rockNames[i]);
        }
        overlayTexture = iconRegister.registerIcon("mechanicsstoneworks:overlays/" + overlayType);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return meta < icons.length ? icons[meta] : icons[0];
    }

}