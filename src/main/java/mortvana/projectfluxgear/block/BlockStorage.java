package mortvana.projectfluxgear.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.block.itemblock.ItemBlockStorage;
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

import net.minecraftforge.oredict.OreDictionary;

public class BlockStorage extends Block {

    public BlockStorage() {
        super(Material.iron);
        setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setCreativeTab(ProjectFluxGear.tab).setBlockName("thermaltinkerer.storage");
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

        GameRegistry.registerBlock(this, ItemBlockStorage.class, "Storage");

        FluxGearContent.blockZinc = new ItemStack(this, 1, 0);
        FluxGearContent.blockBismuth = new ItemStack(this, 1, 1);
        FluxGearContent.blockManganese = new ItemStack(this, 1, 2);
        FluxGearContent.blockPalladium = new ItemStack(this, 1, 3);
        FluxGearContent.blockMolybdenum = new ItemStack(this, 1, 4);
        FluxGearContent.blockCobalt = new ItemStack(this, 1, 5);
        FluxGearContent.blockTungsten = new ItemStack(this, 1, 6);
        FluxGearContent.blockAluminium = new ItemStack(this, 1, 7);
        FluxGearContent.blockChromium = new ItemStack(this, 1, 8);
        FluxGearContent.blockTitanium = new ItemStack(this, 1, 9);
        FluxGearContent.blockMagnetite = new ItemStack(this, 1, 10);
        FluxGearContent.blockDioptase = new ItemStack(this, 1, 11);
        FluxGearContent.blockPyrope = new ItemStack(this, 1, 12);
        FluxGearContent.blockMyuvil = new ItemStack(this, 1, 13);
        //FluxGearContent.blockArsenic = new ItemStack(this, 1, 14);
        //FluxGearContent.blockAntimony = new ItemStack(this, 1, 15);

        return true;
    }

    public static final String[] NAMES = { "zinc", "bismuth", "manganese", "palladium", "molybdenum", "cobalt", "tungsten", "aluminium", "chromium", "titanium", "magnetite", "dioptase", "pyrope", "myuvil"/*, "arsenic", "antimony"*/ };
    public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
    public static final int[] LIGHT = { 0, 2, 0, 2, 0, 0, 0, 2, 0, 0, 0, 8, 4, 4, 0, 0};
    public static final float[] HARDNESS = { 5, 5, 5, 5, 6, 7, 10, 5, 7, 8, 5, 7, 7, 4, 3, 3 };
    public static final float[] RESISTANCE = { 6, 8, 6, 6, 7, 8, 25, 8, 10, 12, 8, 13, 13, 6, 5, 5};
    public static final int[] RARITY = { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 0, 0 };

}
