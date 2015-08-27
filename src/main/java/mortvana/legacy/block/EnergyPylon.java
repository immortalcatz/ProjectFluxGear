package mortvana.legacy.block;

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

import mortvana.legacy.block.tileentity.TileEnergyPylon;
import mortvana.legacy.errored.core.common.FluxGearContent;
import mortvana.legacy.errored.core.common.ProjectFluxGear;
import mortvana.melteddashboard.block.FluxGearBlock;

public class EnergyPylon extends FluxGearBlock {
	@SideOnly(Side.CLIENT)
	public IIcon icon_active_face;
	public IIcon icon_input;
	public IIcon icon_output;

	public EnergyPylon() {
		super(Material.iron, FluxGearContent.tabMaterials);
		setHardness(10.0F).setResistance(20.0F).setBlockName("energyPylon");
		ModBlocks.register(this);
	}

	public boolean hasTileEntity(int metadata) {
		return metadata == 1 || metadata == 2;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return metadata != 1 && metadata != 2 ? null : new TileEnergyPylon();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.icon_input = iconRegister.registerIcon(ProjectFluxGear.RESOURCESPREFIX + "energy_pylon_input");
		this.icon_output = iconRegister.registerIcon(ProjectFluxGear.RESOURCESPREFIX + "energy_pylon_output");
		this.icon_active_face = iconRegister.registerIcon(ProjectFluxGear.RESOURCESPREFIX + "energy_pylon_active_face");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta == 1 && side == 1?this.icon_active_face:(meta == 2 && side == 0?this.icon_active_face:this.icon_input);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 1 && side == 1) {
			return this.icon_active_face;
		} else if(meta == 2 && side == 0) {
			return this.icon_active_face;
		} else {
			TileEnergyPylon thisTile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEnergyPylon?(TileEnergyPylon)world.getTileEntity(x, y, z):null;
			return thisTile == null?this.icon_input:(!thisTile.reciveEnergy?this.icon_output:this.icon_input);
		}
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		int meta = world.getBlockMetadata(x, y, z);
		TileEnergyPylon thisTile;
		if(meta == 0) {
			if(world.getBlock(x, y + 1, z) == Blocks.glass) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 2);
				world.setBlock(x, y + 1, z, FluxGearContent.invisibleMultiblock, 2, 2);
			} else if(world.getBlock(x, y - 1, z) == Blocks.glass) {
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
				world.setBlock(x, y - 1, z, FluxGearContent.invisibleMultiblock, 2, 2);
			}
		} else {
			thisTile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEnergyPylon ? (TileEnergyPylon)world.getTileEntity(x, y, z) : null;
			if(thisTile == null || meta == 1 && !this.isGlass(world, x, y + 1, z) || meta == 2 && !this.isGlass(world, x, y - 1, z)) {
				world.setBlockMetadataWithNotify(x, y, z, 0, 2);
			}
		}

		thisTile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEnergyPylon ? (TileEnergyPylon)world.getTileEntity(x, y, z) : null;
		if(thisTile != null) {
			thisTile.onActivated();
		}

		if(world.getBlockMetadata(x, y, z) == 0 && world.getTileEntity(x, y, z) != null) {
			world.removeTileEntity(x, y, z);
		}

	}

	private boolean isGlass(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == FluxGearContent.invisibleMultiblock && world.getBlockMetadata(x, y, z) == 2;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 0) {
			return false;
		} else {
			TileEnergyPylon thisTile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEnergyPylon ? (TileEnergyPylon)world.getTileEntity(x, y, z) : null;
			if(thisTile != null) {
				if(!player.isSneaking()) {
					thisTile.onActivated();
				} else {
					thisTile.nextCore();
				}

				return true;
			} else {
				return false;
			}
		}
	}
}
