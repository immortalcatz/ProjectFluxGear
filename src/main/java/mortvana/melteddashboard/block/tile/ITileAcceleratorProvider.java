package mortvana.melteddashboard.block.tile;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import java.util.Collection;

public interface ITileAcceleratorProvider {

    void blacklistBlocks(Collection<Block> blocks);

    void blacklistTiles(Collection<Class<? extends TileEntity>> tiles);

    boolean canSpeedUp(IBlockAccess world, int x, int y, int z);
}
