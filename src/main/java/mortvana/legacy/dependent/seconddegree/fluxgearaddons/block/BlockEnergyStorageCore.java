package mortvana.legacy.dependent.seconddegree.fluxgearaddons.block;

import mortvana.legacy.LegacyHelper;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.melteddashboard.block.FluxGearBlock;
import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.util.helpers.StringHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.ForgeDirection;

import mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile.TileEnergyStorageCore;
public class BlockEnergyStorageCore extends FluxGearBlock {
	public BlockEnergyStorageCore() {
		super(Material.iron, FluxGearContent.tabMaterials, 10.0F, 20.0F);
		setUnlocalizedName("energyStorageCore");
		LegacyHelper.register(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(LegacyHelper.RESOURCE_PREFIX + "energy_storage_core");
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEnergyStorageCore();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEnergyStorageCore tile = isCore(world, x, y, z);
		if (tile == null) {
			MeltedDashboardCore.logger.error("Missing Tile Entity (EnergyStorageCore)");
			return false;
		} else {
			if (!world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText("Tier:" + tile.getTier()));
				String flux = String.valueOf(tile.getEnergyStored());
				if (flux.substring(flux.length() - 2).contentEquals(".0")) {
					flux = flux.substring(0, flux.length() - 2);
				}

				player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("info.de.charge.txt") + ": " + StringHelper.formatNumber(tile.getEnergyStored()) + " / " + StringHelper.formatNumber(tile.getMaxEnergyStored()) + " [" + flux + " RF]"));
			}

			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		ForgeDirection dir = ForgeDirection.getOrientation(side);
		TileEnergyStorageCore tile = isCore(world, x - dir.offsetX, y - dir.offsetY, z - dir.offsetZ);
		if (tile == null) {
			MeltedDashboardCore.logger.error("Missing Tile Entity at BlockEnergyStorageCore.shouldSideBeRendered");
			return true;
		} else {
			return !tile.isOnline() && super.shouldSideBeRendered(world, x, y, z, side);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		TileEnergyStorageCore tile = isCore(world, x, y, z);
		if (tile != null && tile.isOnline() && tile.getTier() == 0) {
			tile.isStructureStillValid(false);
		}

	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		TileEnergyStorageCore tile = isCore(world, x, y, z);
		if (tile != null && tile.isOnline() && tile.getTier() == 0) {
			tile.deactivateStabilizers();
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		TileEnergyStorageCore tile = isCore(world, x, y, z);
		return tile != null && tile.isOnline() ? AxisAlignedBB.getBoundingBox((double) tile.xCoord + 0.5D, (double) tile.yCoord + 0.5D, (double) tile.zCoord + 0.5D, (double) tile.xCoord + 0.5D, (double) tile.yCoord + 0.5D, (double) tile.zCoord + 0.5D) : super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	public static TileEnergyStorageCore isCore(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null && tile instanceof TileEnergyStorageCore ? (TileEnergyStorageCore) tile : null;
	}
}
