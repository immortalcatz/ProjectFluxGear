package mortvana.legacy.dependent.firstdegree.projectfluxgear.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.clean.core.util.block.FallingBlockFluxGear;
import mortvana.legacy.errored.projectfluxgear.BlockInformation;

import mortvana.melteddashboard.util.enums.EnumBlockType;
import mortvana.projectfluxgear.core.data.ItemBlockInformation;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;

import mortvana.melteddashboard.util.helpers.StringHelper;

//TODO: AdvFallingBlockFluxGear
public class BlockGravelOreAux extends FallingBlockFluxGear {

    public BlockGravelOreAux() {
        super(BlockInformation.materialSoilOre, FluxGearContent.tabWorld, EnumBlockType.SOIL_ORE);
        setUnlocalizedName("mortvana.projectfluxgear.gravelOreAux");
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < ItemBlockInformation.BLOCK_NAMES_ORE_SECONDARY.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return BlockInformation.LIGHT_ORES_AUX[world.getBlockMetadata(x, y, z)];
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return TEXTURES[metadata];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        for (int i = 0; i < ItemBlockInformation.BLOCK_NAMES_ORE_SECONDARY.length; i++) {
            TEXTURES[i] = ir.registerIcon(BlockInformation.TEXTURE_LOCATION_GRAVEL_ORE + StringHelper.titleCase(ItemBlockInformation.BLOCK_NAMES_ORE_SECONDARY[i]));
        }
    }

    public static final IIcon[] TEXTURES = new IIcon[ItemBlockInformation.BLOCK_NAMES_ORE_SECONDARY.length];
}
