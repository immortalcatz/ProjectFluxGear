package mortvana.legacy.dependent.seconddegree.fluxgearaddons.block;

import java.util.Random;

import mortvana.legacy.LegacyHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.block.FluxGearBlock;
import mortvana.melteddashboard.common.MeltedDashboardCore;

import mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile.TileEnergyPylon;
import mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile.TileEnergyStorageCore;
import mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile.TileInvisibleMultiblock;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.legacy.clean.fluxgearaddons.util.Utils;

public class BlockInvisibleMultiblock extends FluxGearBlock {

	public BlockInvisibleMultiblock() {
		super(Material.iron);
		setHardness(10.0F).setResistance(2000.0F).setUnlocalizedName("invisibleMultiblock");
		LegacyHelper.register(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(LegacyHelper.RESOURCE_PREFIX + "polycarbide_block_0");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return metadata == 0 || metadata == 1;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return metadata != 0 && metadata != 1 ? null : new TileInvisibleMultiblock();
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int var2) {
		return metadata == 0 ? Item.getItemFromBlock(LegacyHelper.blockPolycarbide) : (metadata == 1 ? Item.getItemFromBlock(Blocks.redstone_block) : null);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,  float hitY, float hitZ) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta != 0 && meta != 1) {
			if (meta != 2) {
				return false;
			} else {
				TileEnergyPylon pylon = BlockEnergyPylon.isPylon(world, x, y + 1, z);
				if (pylon == null) {
					return false;
				} else {
					pylon.receiveEnergy = !pylon.receiveEnergy;
					world.markBlockForUpdate(pylon.xCoord, pylon.yCoord, pylon.zCoord);
					pylon.onActivated();
					return true;
				}
			}
		} else {
			TileInvisibleMultiblock tile = isTile(world, x, y, z);
			if (tile == null) {
				MeltedDashboardCore.logger.error("Missing Tile Entity (TileInvisibleMultiblock)");
				return false;
			} else {
				TileEnergyStorageCore master = tile.getMaster();
				if (master == null) {
					onNeighborBlockChange(world, x, y, z, this);
					return false;
				} else {
					if (!world.isRemote) {
						world.markBlockForUpdate(master.xCoord, master.yCoord, master.zCoord);
						player.addChatComponentMessage(new ChatComponentText("Tier:" + master.getTier()));
						String flux = String.valueOf(master.getEnergyStored());
						if (flux.substring(flux.length() - 2).contentEquals(".0")) {
							flux = flux.substring(0, flux.length() - 2);
						}

						player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("info.de.charge.txt") + ": " + Utils.formatNumber(master.getEnergyStored()) + " / " + Utils.formatNumber(master.getMaxEnergyStored()) + " [" + flux + " RF]"));
					}

					return true;
				}
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_) {
		int metadata = world.getBlockMetadata(x, y, z);
		if (metadata != 0 && metadata != 1) {
			if (metadata == 2 && world.getBlock(x, y + 1, z) != FluxGearContent.energyPylon && world.getBlock(x, y - 1, z) != FluxGearContent.energyPylon) {
				world.setBlock(x, y, z, Blocks.glass);
			}
		} else {
			TileInvisibleMultiblock tile = isTile(world, x, y, z);
			if (tile == null) {
				MeltedDashboardCore.logger.error("Missing Tile Entity (TileInvisibleMultiblock)");
				revert(world, x, y, z);
				return;
			}

			TileEnergyStorageCore master = tile.getMaster();
			if (master == null) {
				MeltedDashboardCore.logger.error("Master = null reverting!");
				revert(world, x, y, z);
				return;
			}

			if (master.isOnline()) {
				master.isStructureStillValid(tile.getMaster().getTier() == 1);
			}

			if (!master.isOnline()) {
				revert(world, x, y, z);
			}
		}

	}

	public void revert(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0) {
			world.setBlock(x, y, z, LegacyHelper.blockPolycarbide);
		} else if (meta == 1) {
			world.setBlock(x, y, z, Blocks.redstone_block);
		}

	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileInvisibleMultiblock tile = isTile(world, x, y, z);
		if (tile != null && tile.getMaster() != null && tile.getMaster().isOnline()) {
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
			tile.getMaster().isStructureStillValid(tile.getMaster().getTier() == 1);
		}

		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta != 0 && meta != 1) {
			return meta == 2 ? AxisAlignedBB.getBoundingBox((double) x + 0.49D, (double) y + 0.49D, (double) z + 0.49D, (double) x + 0.51D, (double) y + 0.51D, (double) z + 0.51D) : super.getSelectedBoundingBoxFromPool(world, x, y, z);
		} else {
			TileEntity protoTile = world.getTileEntity(x, y, z);
			TileInvisibleMultiblock tile = protoTile != null && protoTile instanceof TileInvisibleMultiblock ? (TileInvisibleMultiblock) protoTile : null;
			return tile != null && tile.getMaster() != null ? AxisAlignedBB.getBoundingBox((double) tile.getMaster().xCoord, (double) tile.getMaster().yCoord, (double) tile.getMaster().zCoord, (double) tile.getMaster().xCoord + 0.5D, (double) tile.getMaster().yCoord + 0.5D, (double) tile.getMaster().zCoord + 0.5D) : super.getSelectedBoundingBoxFromPool(world, x, y, z);
		}
	}

	@Override
	@Deprecated
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int metadata = world.getBlockMetadata(x, y, z);
		return metadata == 0 ? new ItemStack(LegacyHelper.blockPolycarbide) : (metadata == 1 ? new ItemStack(Blocks.redstone_block) : new ItemStack(Blocks.glass));
	}

	public static TileInvisibleMultiblock isTile(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null && tile instanceof TileInvisibleMultiblock ? (TileInvisibleMultiblock) tile : null;
	}
}
