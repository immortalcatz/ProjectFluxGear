package mortvana.legacy.errored.thaumicrevelations.block;

import mortvana.legacy.errored.thaumicrevelations.block.tileentity.TileWitor;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWitor extends BlockContainer {

    public BlockWitor() {

        super(Config.airyMaterial);
        setBlockName("blockWitor");
        setStepSound(Block.soundTypeCloth);
        setCreativeTab(ThaumicRevelations.thaumicRevelationsTab);
        setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);

    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public int getLightValue() {
        return 15;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileWitor();
    }


}
