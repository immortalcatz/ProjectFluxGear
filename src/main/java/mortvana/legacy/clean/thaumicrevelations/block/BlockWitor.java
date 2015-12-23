package mortvana.legacy.clean.thaumicrevelations.block;

import mortvana.legacy.clean.thaumicrevelations.block.tile.TileWitor;
import mortvana.projectfluxgear.library.ContentLibrary;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import thaumcraft.common.config.Config;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWitor extends BlockContainer {

    public BlockWitor() {

        super(Config.airyMaterial);
        setUnlocalizedName("blockWitor");
        setStepSound(Block.soundTypeCloth);
        setCreativeTab(ContentLibrary.thaumicRevelationsTab);
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
