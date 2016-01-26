package mortvana.melteddashboard.util.data;

import mortvana.melteddashboard.block.tile.ITileAcceleratorProvider;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class AcceleratorData {

    private ITileAcceleratorProvider block;
    private Class<? extends TileEntity> tile;

    public AcceleratorData(ITileAcceleratorProvider block, Class<? extends TileEntity> tile) {
        this.block = block instanceof Block ? block : null;
        this.tile = tile;
    }

    public ITileAcceleratorProvider getBlock() {
        return block;
    }

    public Class<? extends TileEntity> getTile() {
        return tile;
    }

    public void setBlock(ITileAcceleratorProvider block) {
        if (block instanceof Block) {
            this.block = block;
        }
    }

    public void setTile(Class<? extends TileEntity> tile) {
        this.tile = tile;
    }
}
