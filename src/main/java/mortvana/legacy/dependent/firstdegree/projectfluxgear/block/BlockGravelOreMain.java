package mortvana.legacy.dependent.firstdegree.projectfluxgear.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.clean.core.util.block.FallingBlockFluxGear;
import mortvana.legacy.errored.projectfluxgear.BlockInformation;

import mortvana.melteddashboard.util.enums.EnumBlockType;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;

import mortvana.melteddashboard.util.helpers.StringHelper;

import static mortvana.projectfluxgear.library.FluxGearLibrary.*;

//TODO: AdvFallingBlockFluxGear
public class BlockGravelOreMain extends FallingBlockFluxGear {

    public BlockGravelOreMain() {
        super(BlockInformation.materialSoilOre, FluxGearContent.tabWorld, EnumBlockType.SOIL_ORE);
        setUnlocalizedName("fluxgear.gravelOrePrimary");
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < NAMES_ORE_PRIMARY.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return LIGHT_ORES_PRIMARY[world.getBlockMetadata(x, y, z)];
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return TEXTURES[metadata];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        for (int i = 0; i < NAMES_ORE_PRIMARY.length; i++) {
            TEXTURES[i] = ir.registerIcon(TEX_LOC_GRAVEL_ORE + StringHelper.titleCase(NAMES_ORE_PRIMARY[i]));
        }
    }

    public static final IIcon[] TEXTURES = new IIcon[NAMES_ORE_PRIMARY.length];
}