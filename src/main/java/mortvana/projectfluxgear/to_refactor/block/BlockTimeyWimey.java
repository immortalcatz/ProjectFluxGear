package mortvana.projectfluxgear.to_refactor.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import mortvana.fluxgearzee.util.ServerHelper;
import mortvana.projectfluxgear.to_refactor.block.tile.TileTimeyWimey;

public class BlockTimeyWimey extends BlockTorch implements ITileEntityProvider{
	public BlockTimeyWimey() {
		setBlockName("TimeyWimeyTorch").setLightLevel(1.0F).setCreativeTab(CreativeTabs.tabDecorations).setBlockTextureName("FluxGearZee:timeyWimeyTorch");
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (ServerHelper.isServerWorld(world)) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileTimeyWimey) {
				((TileTimeyWimey) tile).setActive(!world.isBlockIndirectlyGettingPowered(x, y, z));
			}
		}
		super.onBlockAdded(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (ServerHelper.isServerWorld(world)) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileTimeyWimey) {
				((TileTimeyWimey) tile).setActive(!world.isBlockIndirectlyGettingPowered(x, y, z));
			}
		}
		super.onNeighborBlockChange(world, x, y, z, block);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) {
		if (ServerHelper.isServerWorld(world)) {
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

}
