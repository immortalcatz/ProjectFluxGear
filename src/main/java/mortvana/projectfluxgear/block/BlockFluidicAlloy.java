package mortvana.projectfluxgear.block;

import cofh.api.core.IInitializer;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

public class BlockFluidicAlloy extends Block implements IInitializer {

    public BlockFluidicAlloy() {
        super(Material.iron);
        setHardness(5.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setCreativeTab(ProjectFluxGear.tab);
        setBlockName("thermaltinkerer.fluidicalloy");
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

        return world.getBlockMetadata(x, y, z) == 3 ? 15 : 0;
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
            TEXTURES[i] = ir.registerIcon("thermaltinkerer:storage/Block_" + StringHelper.titleCase(NAMES[i]));
        }
    }

    /* IInitializer */
    @Override
    public boolean preInit() {

        /*blockTungstenBlazing = new ItemStack(this, 1, 0);
        blockPlatinumGelid = new ItemStack(this, 1, 1);
        blockSilverLuminous = new ItemStack(this, 1, 2);
        blockElectrumFlux = new ItemStack(this, 1, 3);
        blockMolybdenumResonant = new ItemStack(this, 1, 4);
        blockChromiumCarbide = new ItemStack(this, 1, 5);
        blockCarbonite = new ItemStack(this, 1, 6);
        blockPyrum = new ItemStack(this, 1, 7);
        blockGelinium = new ItemStack(this, 1, 8);
        blockDullRedsolder = new ItemStack(this, 1, 9);
        blockRedsolder = new ItemStack(this, 1, 10);
        blockIridium = new ItemStack(this, 1, 11);
        blockSulfur = new ItemStack(this, 1, 12);
        blockSaltpeter = new ItemStack(this, 1, 13);
        blockRust = new ItemStack(this, 1, 14);
        blockColdfireBismuthBronze = new ItemStack(this, 1, 15);

        ItemHelper.registerWithHandlers("block", blockTungstenBlazing);
        ItemHelper.registerWithHandlers("block", blockBismuthBronzeGelid);
        ItemHelper.registerWithHandlers("block", blockSilverLuminous);
        ItemHelper.registerWithHandlers("block", blockElectrumFlux);
        ItemHelper.registerWithHandlers("block", blockMolybdenumResonant);
        ItemHelper.registerWithHandlers("block", blockChromiumCarbide);
        ItemHelper.registerWithHandlers("block", blockCarbonite);
        ItemHelper.registerWithHandlers("block", blockPyrum);
        ItemHelper.registerWithHandlers("block", blockGelinium);
        ItemHelper.registerWithHandlers("block", blockRust);
        ItemHelper.registerWithHandlers("block", blockApatite);
        ItemHelper.registerWithHandlers("block", blockIridium);
        ItemHelper.registerWithHandlers("block", blockSulfur);
        ItemHelper.registerWithHandlers("block", blockSaltpeter);*/

        return true;
    }

    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean postInit() {

        /*ItemHelper.addStorageRecipe(blockTungstenBlazing, "ingotTungstenBlazing");
        ItemHelper.addStorageRecipe(blockBismuthBronzeGelid, "ingotBismuthBronzeGelid");
        ItemHelper.addStorageRecipe(blockSilverLuminous, "ingotSilverLuminous");
        ItemHelper.addStorageRecipe(blockElectrumFlux, "ingotElectrumFlux");
        ItemHelper.addStorageRecipe(blockMolybdenumResonant, "ingotMolybdenumResonant");
        ItemHelper.addStorageRecipe(blockChromiumCarbide, "ingotChromiumCarbide");
        ItemHelper.addStorageRecipe(blockCarbonite, "ingotCarbonite");
        ItemHelper.addStorageRecipe(blockPyrum, "ingotPyrum");
        ItemHelper.addStorageRecipe(blockGelinium, "ingotGelinium");
        ItemHelper.addStorageRecipe(blockRust, "ingotRust");
        ItemHelper.addStorageRecipe(blockApatite, "ingotApatite");
        ItemHelper.addStorageRecipe(blockIridium, "ingotIridium");
        ItemHelper.addStorageRecipe(blockSulfur, "dustSulfur");
        ItemHelper.addStorageRecipe(blockSulfur, "dustSulphur");
        ItemHelper.addStorageRecipe(blockSaltpeter, "dustSaltpeter");
        ItemHelper.addStorageRecipe(blockSaltpeter, "dustSaltpetre");*/

        return true;
    }

    public static final String[] NAMES = {};
    public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
    public static final int[] LIGHT = {};
    public static final float[] HARDNESS = {};
    public static final float[] RESISTANCE = {};
    public static final int[] RARITY = {};

    public static ItemStack blockTungstenBlazing;
    public static ItemStack blockBismuthBronzeGelid;
    public static ItemStack blockSilverLuminous;
    public static ItemStack blockElectrumFlux;
    public static ItemStack blockMolybdenumResonant;
    public static ItemStack blockChromiumCarbide;
    public static ItemStack blockCarbonite;
    public static ItemStack blockPyrum;
    public static ItemStack blockGelinium;
    public static ItemStack blockRust;
    public static ItemStack blockApatite;
    public static ItemStack blockIridium;
    public static ItemStack blockSulfur;
    public static ItemStack blockSaltpeter;
}
