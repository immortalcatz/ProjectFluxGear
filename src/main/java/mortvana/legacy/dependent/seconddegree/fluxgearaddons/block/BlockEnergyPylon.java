package mortvana.legacy.dependent.seconddegree.fluxgearaddons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.LegacyHelper;
import mortvana.legacy.dependent.seconddegree.fluxgearaddons.block.tile.TileEnergyPylon;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import mortvana.melteddashboard.block.FluxGearBlock;

public class BlockEnergyPylon extends FluxGearBlock {
	@SideOnly(Side.CLIENT)
	public IIcon icon_active_face;
	public IIcon icon_input;
	public IIcon icon_output;

	public BlockEnergyPylon() {
		super(Material.iron, FluxGearContent.tabMaterials, 10.0F, 20.0F);
		setUnlocalizedName("energyPylon");
		LegacyHelper.register(this);
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return metadata == 1 || metadata == 2;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return metadata == 1 || metadata == 2 ? new TileEnergyPylon() : null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icon_input = iconRegister.registerIcon(LegacyHelper.RESOURCE_PREFIX + "energy_pylon_input");
		icon_output = iconRegister.registerIcon(LegacyHelper.RESOURCE_PREFIX + "energy_pylon_output");
		icon_active_face = iconRegister.registerIcon(LegacyHelper.RESOURCE_PREFIX + "energy_pylon_active_face");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta == 1 && side == 1 ? icon_active_face : (meta == 2 && side == 0 ? icon_active_face : icon_input);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 1 && side == 1) {
			return icon_active_face;
		} else if (meta == 2 && side == 0) {
			return icon_active_face;
		} else {
			TileEnergyPylon tile = isPylon(world, x, y, z);
			return tile == null ? icon_input : (!tile.receiveEnergy ? icon_output : icon_input);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		int meta = world.getBlockMetadata(x, y, z);
		TileEnergyPylon tile = isPylon(world, x, y, z);
		if (meta == 0) {
			if (world.getBlock(x, y + 1, z) == Blocks.glass) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 2);
				world.setBlock(x, y + 1, z, FluxGearContent.invisibleMultiblock, 2, 2);
			} else if (world.getBlock(x, y - 1, z) == Blocks.glass) {
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
				world.setBlock(x, y - 1, z, FluxGearContent.invisibleMultiblock, 2, 2);
			}
		} else {
			if (tile == null || meta == 1 && !isGlass(world, x, y + 1, z) || meta == 2 && !isGlass(world, x, y - 1, z)) {
				world.setBlockMetadataWithNotify(x, y, z, 0, 2);
			}
		}

		if (tile != null) {
			tile.onActivated();
		}

		if (world.getBlockMetadata(x, y, z) == 0 && world.getTileEntity(x, y, z) != null) {
			world.removeTileEntity(x, y, z);
		}

	}

	public boolean isGlass(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == FluxGearContent.invisibleMultiblock && world.getBlockMetadata(x, y, z) == 2;
	}

	public static TileEnergyPylon isPylon(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null && tile instanceof TileEnergyPylon ? (TileEnergyPylon) tile : null;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 0) {
			return false;
		} else {
			TileEnergyPylon tile = isPylon(world, x, y, z);
			if (tile != null) {
				if (!player.isSneaking()) {
					tile.onActivated();
				} else {
					tile.nextCore();
				}
				return true;
			} else {
				return false;
			}
		}
	}
}
