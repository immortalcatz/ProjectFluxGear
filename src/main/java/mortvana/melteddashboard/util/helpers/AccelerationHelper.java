package mortvana.melteddashboard.util.helpers;

import mortvana.melteddashboard.block.tile.ITileAcceleratorProvider;
import mortvana.melteddashboard.util.data.AcceleratorData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class AccelerationHelper {

    private static List<AcceleratorData> accelerators = new ArrayList<AcceleratorData>();

    private static List<Block> blocks = new ArrayList<Block>();
    private static List<Class<? extends TileEntity>> tiles = new ArrayList<Class<? extends TileEntity>>();

    public static void initAccelerators() {
        ITileAcceleratorProvider block;
        for (AcceleratorData data : accelerators) {
            block = data.getBlock();
            block.blacklistBlocks(blocks);
            block.blacklistTiles(tiles);
        }
    }

    public static void blacklistBlock(Block block) {
        blocks.add(block);
    }

    public static void blacklistTile(Class<? extends TileEntity> tile) {
        tiles.add(tile);
    }

    public static boolean registerAccelerator(ITileAcceleratorProvider block, Class<? extends TileEntity> tile) {
        if (block instanceof Block) {
            accelerators.add(new AcceleratorData(block, tile));
            return true;
        } else {
            return false;
        }
    }

    static {
        blocks.add(Blocks.air);
        blocks.add(Blocks.water);
        blocks.add(Blocks.flowing_water);
        blocks.add(Blocks.lava);
        blocks.add(Blocks.flowing_lava);

        /*TileTimeyWimey.blacklistBlock(FluxGearContent.timeyWimeyTorch);
        TileTimeyWimey.blacklistTile(TileTimeyWimey.class);*/
    }
}
