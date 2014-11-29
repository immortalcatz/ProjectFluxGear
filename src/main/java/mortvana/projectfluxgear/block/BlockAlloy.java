package mortvana.projectfluxgear.block;

import cofh.api.core.IInitializer;
import cofh.lib.util.helpers.ItemHelper;
import cofh.lib.util.helpers.StringHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.block.itemblock.ItemBlockAlloy;
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

public class BlockAlloy extends Block implements IInitializer {

    public BlockAlloy() {
        super(Material.iron);
        setHardness(5.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setCreativeTab(ProjectFluxGear.tab);
        setBlockName("thermaltinkerer.alloy");
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

	    GameRegistry.registerBlock(this, ItemBlockAlloy.class, "Alloy");

        blockBrass = new ItemStack(this, 1, 0);
        blockBismuthBronze = new ItemStack(this, 1, 1);
        blockCupronickel = new ItemStack(this, 1, 2);
        blockAluminiumBrass = new ItemStack(this, 1, 3);
        blockMithrilBronze = new ItemStack(this, 1, 4);
        blockElectriplatinum = new ItemStack(this, 1, 5);
        blockSteel = new ItemStack(this, 1, 6);
        blockTungstenSteel = new ItemStack(this, 1, 7);
        blockStainlessSteel = new ItemStack(this, 1, 8);
        /*block = new ItemStack(this, 1, );
        block = new ItemStack(this, 1, );
        block = new ItemStack(this, 1, );
        block = new ItemStack(this, 1, );
        block = new ItemStack(this, 1, );
        block = new ItemStack(this, 1, );
        block = new ItemStack(this, 1, );*/

        ItemHelper.registerWithHandlers("blockBrass", blockBrass);
        ItemHelper.registerWithHandlers("blockBismuthBronze", blockBismuthBronze);
        ItemHelper.registerWithHandlers("blockCupronickel", blockCupronickel);
        ItemHelper.registerWithHandlers("blockAluminiumBrass", blockAluminiumBrass);
        ItemHelper.registerWithHandlers("blockMithrilBronze", blockMithrilBronze);
        ItemHelper.registerWithHandlers("blockElectriplatinum", blockElectriplatinum);
        ItemHelper.registerWithHandlers("blockSteel", blockSteel);
        ItemHelper.registerWithHandlers("blockTungstenSteel", blockTungstenSteel);
        ItemHelper.registerWithHandlers("blockStainlessSteel", blockStainlessSteel);
        /*ItemHelper.registerWithHandlers("block", block);
        ItemHelper.registerWithHandlers("block", block);
        ItemHelper.registerWithHandlers("block", block);
        ItemHelper.registerWithHandlers("block", block);
        ItemHelper.registerWithHandlers("block", block);
        ItemHelper.registerWithHandlers("block", block);
        ItemHelper.registerWithHandlers("block", block);*/

        return true;
    }

    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean postInit() {

        ItemHelper.addStorageRecipe(blockBrass, "ingotBrass");
        ItemHelper.addStorageRecipe(blockBismuthBronze, "ingotBismuthBronze");
        ItemHelper.addStorageRecipe(blockCupronickel, "ingotCupronickel");
        ItemHelper.addStorageRecipe(blockAluminiumBrass, "ingotAluminiumBrass");
        ItemHelper.addStorageRecipe(blockAluminiumBrass, "ingotAluminumBrass");
        ItemHelper.addStorageRecipe(blockMithrilBronze, "ingotMithrilBronze");
        ItemHelper.addStorageRecipe(blockElectriplatinum, "ingotElectrplatinum");
        ItemHelper.addStorageRecipe(blockSteel, "ingotSteel");
        ItemHelper.addStorageRecipe(blockTungstenSteel, "ingotTungstenSteel");
        ItemHelper.addStorageRecipe(blockStainlessSteel, "ingotStainlessSteel");
        /*ItemHelper.addStorageRecipe(blockTecnomancy, "ingot");
        ItemHelper.addStorageRecipe(blockResonantTechnomancy, "ingot");
        // Amber is a 2x2 recipe
        ItemHelper.addStorageRecipe(blockCrystalFlux, "ingot");
        // Lapiquartz is a resource recipe
        ItemHelper.addStorageRecipe(blockWhitePointStar, "ingot");
        ItemHelper.addStorageRecipe(blockVoidInfernoStar, "ingot");*/

        return true;
    }

    public static final String[] NAMES = { "brass", "bismuthBronze", "cupronickel", "aluminiumBrass", "mithrilBronze", "electriplatinum", "steel", "tungstenSteel", "stainlessSteel" };
    public static final IIcon[] TEXTURES = new IIcon[NAMES.length];
    public static final int[] LIGHT = { 0, 2, 0, 2, 2, 4, 0, 0, 0};
    public static final float[] HARDNESS = { 5, 5, 5, 5, 5, 6, 8, 13, 8 };
    public static final float[] RESISTANCE = { 6, 8, 6, 6, 10, 8, 13, 42, 13};
    public static final int[] RARITY = { 0, 0, 0, 1, 1, 0, 1, 1, 1};

    public static ItemStack blockBrass;
    public static ItemStack blockBismuthBronze;
    public static ItemStack blockCupronickel;
    public static ItemStack blockAluminiumBrass;
    public static ItemStack blockMithrilBronze;
    public static ItemStack blockElectriplatinum;
    public static ItemStack blockSteel;
    public static ItemStack blockTungstenSteel;
    public static ItemStack blockStainlessSteel;
    public static ItemStack blockTecnomancy;
    public static ItemStack blockResonantTechnomancy;
    public static ItemStack blockAmber;
    public static ItemStack blockCrystalFlux;
    public static ItemStack blockLapiquartz;
    public static ItemStack blockWhitePointStar;
    public static ItemStack blockVoidInfernoStar;
}
