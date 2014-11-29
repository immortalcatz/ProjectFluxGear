package mortvana.projectfluxgear.block;

import cofh.api.core.IInitializer;
import cofh.lib.util.helpers.ItemHelper;
import cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.block.itemblock.ItemBlockStorage;
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

public class BlockStorage extends Block implements IInitializer {

    public BlockStorage() {
        super(Material.iron);
        setHardness(5.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setCreativeTab(ProjectFluxGear.tab);
        setBlockName("thermaltinkerer.storage");
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

    /* IInitializer */
    @Override
    public boolean preInit() {

        GameRegistry.registerBlock(this, ItemBlockStorage.class, "Storage");

        blockZinc = new ItemStack(this, 1, 0);
        blockBismuth = new ItemStack(this, 1, 1);
        blockManganese = new ItemStack(this, 1, 2);
        blockPalladium = new ItemStack(this, 1, 3);
        blockMolybdenum = new ItemStack(this, 1, 4);
        blockCobalt = new ItemStack(this, 1, 5);
        blockTungsten = new ItemStack(this, 1, 6);
        blockAluminium = new ItemStack(this, 1, 7);
        blockChromium = new ItemStack(this, 1, 8);
        blockTitanium = new ItemStack(this, 1, 9);
        blockMagnetite = new ItemStack(this, 1, 10);
        blockDioptase = new ItemStack(this, 1, 11);
        blockPyrope = new ItemStack(this, 1, 12);
        blockMyuvil = new ItemStack(this, 1, 13);
        //blockArsenic = new ItemStack(this, 1, 14);
        //blockAntimony = new ItemStack(this, 1, 15);

        ItemHelper.registerWithHandlers("blockZinc", blockZinc);
        ItemHelper.registerWithHandlers("blockBismuth", blockBismuth);
        ItemHelper.registerWithHandlers("blockManganese", blockManganese);
        ItemHelper.registerWithHandlers("blockPalladium", blockPalladium);
        ItemHelper.registerWithHandlers("blockMolybdenum", blockMolybdenum);
        ItemHelper.registerWithHandlers("blockNaturalCobalt", blockCobalt);
        //if
	        OreDictionary.registerOre("blockCobalt", blockCobalt);
        //}
        ItemHelper.registerWithHandlers("blockTungsten", blockTungsten);
        ItemHelper.registerWithHandlers("blockAluminium", blockAluminium);
	    OreDictionary.registerOre("blockAluminum", blockAluminium);
        ItemHelper.registerWithHandlers("blockChromium", blockChromium);
        ItemHelper.registerWithHandlers("blockTitanium", blockTitanium);
        ItemHelper.registerWithHandlers("blockMagnetite", blockMagnetite);
        ItemHelper.registerWithHandlers("blockDioptase", blockDioptase);
        ItemHelper.registerWithHandlers("blockPyrope", blockPyrope);
        ItemHelper.registerWithHandlers("blockMyuvil", blockMyuvil);
        //ItemHelper.registerWithHandlers("blockArsenic", blockArsenic);
        //ItemHelper.registerWithHandlers("blockAntimony", blockAntimony);

        return true;
    }

    @Override
    public boolean initialize() {

        return true;
    }

    @Override
    public boolean postInit() {

        ItemHelper.addStorageRecipe(blockZinc, "ingotZinc");
        ItemHelper.addStorageRecipe(blockBismuth, "ingotBismuth");
        ItemHelper.addStorageRecipe(blockManganese, "ingotManganese");
        ItemHelper.addStorageRecipe(blockPalladium, "ingotPalladium");
        ItemHelper.addStorageRecipe(blockMolybdenum, "ingotMolybdenum");
        ItemHelper.addStorageRecipe(blockCobalt, "ingotNaturalCobalt");
        ItemHelper.addStorageRecipe(blockTungsten, "ingotTungsten");
        ItemHelper.addStorageRecipe(blockAluminium, "ingotAluminium");
        ItemHelper.addStorageRecipe(blockAluminium, "ingotAluminum"); // We Americans are so naive...
        ItemHelper.addStorageRecipe(blockChromium, "ingotChromium");
        ItemHelper.addStorageRecipe(blockTitanium, "ingotTitanium");
        ItemHelper.addStorageRecipe(blockMagnetite, "gemMagnetite");
        ItemHelper.addStorageRecipe(blockDioptase, "gemDioptase");
        ItemHelper.addStorageRecipe(blockPyrope, "gemPyrope");
        ItemHelper.addStorageRecipe(blockMyuvil, "dustMyuvil");
        //ItemHelper.addStorageRecipe(blockArsenic, "dustArsenic");
        //ItemHelper.addStorageRecipe(blockAntimony, "dustAntimony");

        return true;
    }

    public static final String[] NAMES = { "zinc", "bismuth", "manganese", "palladium", "molybdenum", "cobalt", "tungsten", "aluminium", "chromium", "titanium", "magnetite", "dioptase", "pyrope", "myuvil"/*, "arsenic", "antimony"*/ };
    public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
    public static final int[] LIGHT = { 0, 2, 0, 2, 0, 0, 0, 2, 0, 0, 0, 8, 4, 4, 0, 0};
    public static final float[] HARDNESS = { 5, 5, 5, 5, 6, 7, 10, 5, 7, 8, 5, 7, 7, 4, 3, 3 };
    public static final float[] RESISTANCE = { 6, 8, 6, 6, 7, 8, 25, 8, 10, 12, 8, 13, 13, 6, 5, 5};
    public static final int[] RARITY = { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 0, 0 };

    public static ItemStack blockZinc;
    public static ItemStack blockBismuth;
    public static ItemStack blockManganese;
    public static ItemStack blockPalladium;
    public static ItemStack blockMolybdenum;
    public static ItemStack blockCobalt;
    public static ItemStack blockTungsten;
    public static ItemStack blockAluminium;
    public static ItemStack blockChromium;
    public static ItemStack blockTitanium;
    public static ItemStack blockMagnetite;
    public static ItemStack blockDioptase;
    public static ItemStack blockPyrope;
    public static ItemStack blockMyuvil;
    public static ItemStack blockArsenic;
    public static ItemStack blockAntimony;

}
