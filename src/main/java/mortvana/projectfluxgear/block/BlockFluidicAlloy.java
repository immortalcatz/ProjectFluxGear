package mortvana.projectfluxgear.block;

import cofh.api.core.IInitializer;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.fluxgearcore.util.helper.ItemHelper;
import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.block.itemblock.ItemBlockFluidicAlloy;
import mortvana.projectfluxgear.common.FluxGearContent;
import mortvana.projectfluxgear.common.ProjectFluxGear;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockFluidicAlloy extends Block {

    public BlockFluidicAlloy() {
        super(Material.iron);
        setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setCreativeTab(ProjectFluxGear.tab).setBlockName("thermaltinkerer.fluidicalloy");
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
    public int damageDropped(int i) {

        return i;
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {

        return false;
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {

        return true;
    }

    @Override
    public IIcon getIcon(int side, int metadata) {

        return TEXTURES[metadata];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {

        for (int i = 0; i < NAMES.length; i++) {
            TEXTURES[i] = ir.registerIcon("thermaltinkerer:storage/block" + StringHelper.titleCase(NAMES[i]));
        }
    }

    public boolean preInit() {

        GameRegistry.registerBlock(this, ItemBlockFluidicAlloy.class, "FluidicAlloy");

        FluxGearContent.blockTungstenBlazing = new ItemStack(this, 1, 0);
        FluxGearContent.blockPlatinumGelid = new ItemStack(this, 1, 1);
        FluxGearContent.blockSilverLuminous = new ItemStack(this, 1, 2);
        FluxGearContent.blockElectrumFlux = new ItemStack(this, 1, 3);
        FluxGearContent.blockMolybdenumResonant = new ItemStack(this, 1, 4);
        FluxGearContent.blockChromiumCarbide = new ItemStack(this, 1, 5);
        FluxGearContent.blockCarbonite = new ItemStack(this, 1, 6);
        FluxGearContent.blockPyrum = new ItemStack(this, 1, 7);
        FluxGearContent.blockGelinium = new ItemStack(this, 1, 8);
        FluxGearContent.blockDullRedsolder = new ItemStack(this, 1, 9);
        FluxGearContent.blockRedsolder = new ItemStack(this, 1, 10);
        FluxGearContent.blockIridium = new ItemStack(this, 1, 11);
        FluxGearContent.blockSulfur = new ItemStack(this, 1, 12);
        FluxGearContent.blockSaltpeter = new ItemStack(this, 1, 13);
        FluxGearContent.blockRust = new ItemStack(this, 1, 14);
        FluxGearContent.blockColdfireBismuthBronze = new ItemStack(this, 1, 15);


        return true;
    }

    public static final String[] NAMES = {};
    public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
    public static final int[] LIGHT = {};
    public static final float[] HARDNESS = {};
    public static final float[] RESISTANCE = {};
    public static final int[] RARITY = {};


}
