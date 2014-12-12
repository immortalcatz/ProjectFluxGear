package mortvana.projectfluxgear.block.basic;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.block.basic.itemblock.ItemBlockAlloyMain;
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

public class BlockAlloyMain extends Block {

    public BlockAlloyMain() {
        super(Material.iron);
        setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setCreativeTab(ProjectFluxGear.tab).setBlockName("projectfluxgear.alloy");
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
            TEXTURES[i] = ir.registerIcon("projectfluxgear:storage/block" + StringHelper.titleCase(NAMES[i]));
        }
    }

    public boolean preInit() {

	    GameRegistry.registerBlock(this, ItemBlockAlloyMain.class, "Alloy");

        FluxGearContent.blockBrass = new ItemStack(this, 1, 0);
        FluxGearContent.blockBismuthBronze = new ItemStack(this, 1, 1);
        FluxGearContent.blockCupronickel = new ItemStack(this, 1, 2);
        FluxGearContent.blockAluminiumBrass = new ItemStack(this, 1, 3);
        FluxGearContent.blockMithril = new ItemStack(this, 1, 4);
        FluxGearContent.blockElectriplatinum = new ItemStack(this, 1, 5);
        FluxGearContent.blockSteel = new ItemStack(this, 1, 6);
        FluxGearContent.blockTungstenSteel = new ItemStack(this, 1, 7);
        FluxGearContent.blockStainlessSteel = new ItemStack(this, 1, 8);
        FluxGearContent.blockTechnomancy = new ItemStack(this, 1, 9);
        FluxGearContent.blockResonantTechnomancy = new ItemStack(this, 1, 10);
        FluxGearContent.blockAmber = new ItemStack(this, 1, 11);
        FluxGearContent.blockCrystalFlux = new ItemStack(this, 1, 12);
        FluxGearContent.blockLapiquartz = new ItemStack(this, 1, 13);
        FluxGearContent.blockWhitePointStar = new ItemStack(this, 1, 14);
        FluxGearContent.blockVoidInfernoStar = new ItemStack(this, 1, 15);

        return true;
    }

    public static final String[] NAMES = { "brass", "bismuthBronze", "cupronickel", "aluminiumBrass", "mithril", "electriplatinum", "steel", "tungstenSteel", "stainlessSteel", "technomancy", "resonantTechnomancy", "amber", "crystalFlux", "lapiquartz", "whitePointStar", "voidInfernoStar" };
    public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
    public static final int[] LIGHT = { 0, 2, 0, 2, 2, 4, 0, 0, 0, 4, 8, 0, 4, 2, 15, 15 };
    public static final float[] HARDNESS = { 5, 5, 5, 5, 5, 6, 8, 13, 8, 7, 11, 4, 5, 5, 8, 8 };
    public static final float[] RESISTANCE = { 6, 8, 6, 6, 10, 8, 13, 42, 13, 8, 10, 6, 8, 8, 42, 507 };
    public static final int[] RARITY = { 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 2, 0, 1, 1, 2, 3 };


}
