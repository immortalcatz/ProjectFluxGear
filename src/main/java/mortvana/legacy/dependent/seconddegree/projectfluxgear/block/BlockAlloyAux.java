package mortvana.legacy.dependent.seconddegree.projectfluxgear.block;

import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;

import mortvana.legacy.dependent.seconddegree.projectfluxgear.util.BlockInformation;
import mortvana.melteddashboard.block.FluxGearBlock;
import mortvana.melteddashboard.util.enums.EnumBlockType;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

import mortvana.projectfluxgear.library.FluxGearLibrary;

public class BlockAlloyAux extends FluxGearBlock {

    public BlockAlloyAux() {
        //TODO: Fix String/IIcon Situation...
        super(Material.iron, FluxGearContent.tabMaterials, EnumBlockType.STORAGE, BlockInformation.NAMES_ALLOY_AUX, BlockInformation.HARDNESS_ALLOY_AUX, BlockInformation.RESISTANCE_ALLOY_AUX, BlockInformation.LIGHT_ALLOY_AUX, FluxGearLibrary.TEX_LOC_BLOCK);
        setUnlocalizedName("mortvana.projectfluxgear.alloyAux");
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {

        if (world.getBlockMetadata(x, y, z) == 5) {
            return 7;
        } else if (world.getBlockMetadata(x, y, z) == 12) {
            return 15;
        } else {
            return 0;
        }
    }

}

//TODO: BlockStorageRandom
//TODO: BlockPFGGlass