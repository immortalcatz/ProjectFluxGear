package mortvana.legacy.clean.projectfluxgear.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mortvana.legacy.errored.core.common.FluxGearContent;
import mortvana.legacy.clean.core.util.block.FallingBlockFluxGear;
import mortvana.legacy.clean.core.util.helpers.StringHelper;
import mortvana.projectfluxgear.core.data.ItemBlockInformation;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;

import static mortvana.legacy.errored.core.block.BlockFluxGear.*;

//TODO: AdvFallingBlockFluxGear
public class BlockGravelOreMain extends FallingBlockFluxGear {

    public BlockGravelOreMain() {
        super(materialSoilOre, FluxGearContent.tabWorld, EnumBlockType.SOIL_ORE);
        setBlockName("mortvana.projectfluxgear.gravelOreMain");
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < ItemBlockInformation.BLOCK_NAMES_ORE_PRIMARY.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        return LIGHT_ORES_MAIN[world.getBlockMetadata(x, y, z)];
    }

    @Override
    public IIcon getIcon(int side, int metadata) {

        return TEXTURES[metadata];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {

        for (int i = 0; i < ItemBlockInformation.BLOCK_NAMES_ORE_PRIMARY.length; i++) {
            TEXTURES[i] = ir.registerIcon(TEXTURE_LOCATION_GRAVEL_ORE + StringHelper.titleCase(ItemBlockInformation.BLOCK_NAMES_ORE_PRIMARY[i]));
        }
    }

    public static final IIcon[] TEXTURES = new IIcon[ItemBlockInformation.BLOCK_NAMES_ORE_PRIMARY.length];
}