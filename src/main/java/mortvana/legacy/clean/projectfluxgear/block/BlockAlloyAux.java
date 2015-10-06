package mortvana.legacy.clean.projectfluxgear.block;

import mortvana.legacy.errored.core.block.BlockFluxGear;
import mortvana.legacy.errored.core.common.FluxGearContent;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockAlloyAux extends BlockFluxGear {

    public BlockAlloyAux() {
        super(Material.iron, FluxGearContent.tabMaterials, EnumBlockType.STORAGE, NAMES_ALLOY_AUX, TEXTURES_FULL, HARDNESS_ALLOY_AUX, RESISTANCE_ALLOY_AUX, LIGHT_ALLOY_AUX, TEXTURE_LOCATION_BLOCK);
        setBlockName("mortvana.projectfluxgear.alloyAux");
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

//BlockStorageRandom
//BlockPFGGlass