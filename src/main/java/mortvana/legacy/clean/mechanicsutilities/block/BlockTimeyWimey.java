package mortvana.legacy.clean.mechanicsutilities.block;

import mortvana.melteddashboard.block.tile.ITileAcceleratorProvider;
import mortvana.melteddashboard.util.helpers.AccelerationHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.legacy.clean.mechanicsutilities.block.tile.TileTimeyWimey;

import java.util.Collection;

public class BlockTimeyWimey extends BlockTorch implements ITileEntityProvider, ITileAcceleratorProvider {

    public BlockTimeyWimey() {
        setUnlocalizedName("TimeyWimeyTorch");
        setLightLevel(1.0F);
        setCreativeTab(CreativeTabs.tabDecorations);
        setTextureName("FluxGearZee:timeyWimeyTorch");
        AccelerationHelper.registerAccelerator(this, TileTimeyWimey.class);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile != null && tile instanceof TileTimeyWimey) {
                ((TileTimeyWimey) tile).setActive(!world.isBlockIndirectlyGettingPowered(x, y, z));
            }
        }
        super.onBlockAdded(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile != null && tile instanceof TileTimeyWimey) {
                ((TileTimeyWimey) tile).setActive(!world.isBlockIndirectlyGettingPowered(x, y, z));
            }
        }
        super.onNeighborBlockChange(world, x, y, z, block);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(x, y, z);

            if (tile != null && tile instanceof TileTimeyWimey) {
                TileTimeyWimey torch = (TileTimeyWimey) tile;

                torch.changeMode(player.isSneaking());

                if (player.isSneaking()) {
                    player.addChatComponentMessage(new ChatComponentText("Changed speed: " + torch.getSpeedDescription()));
                } else {
                    player.addChatComponentMessage(new ChatComponentText("Changed mode: " + torch.getModeDescription()));
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileTimeyWimey();
    }

    @Override
    public void blacklistBlocks(Collection<Block> blocks) {
        for (Block block : blocks) {
            TileTimeyWimey.blacklistBlock(block);
        }
    }

    @Override
    public void blacklistTiles(Collection<Class<? extends TileEntity>> tiles) {
        for (Class<? extends TileEntity> tile : tiles) {
            TileTimeyWimey.blacklistTile(tile);
        }
    }

    @Override
    public boolean canSpeedUp(IBlockAccess world, int x, int y, int z) {
        return false;
    }
}