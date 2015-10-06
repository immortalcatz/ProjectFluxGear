package mortvana.legacy.errored.fluxgearaddons.block;

import mortvana.legacy.errored.core.common.FluxGearContent;
import mortvana.melteddashboard.block.FluxGearBlock;
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

import mortvana.legacy.errored.fluxgearaddons.block.tileentity.TileEnergyStorageCore;
import mortvana.legacy.errored.core.common.ProjectFluxGear;
import mortvana.legacy.clean.fluxgearaddons.util.Utils;

public class BlockEnergyStorageCore extends FluxGearBlock {
	public BlockEnergyStorageCore() {
		super(Material.iron, FluxGearContent.tabMaterials);
		setHardness(10.0F).setResistance(20.0F).setBlockName("energyStorageCore");
		ModBlocks.register(this);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.field_149761_L = iconRegister.registerIcon(ProjectFluxGear.RESOURCESPREFIX + "energy_storage_core");
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEnergyStorageCore();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		TileEnergyStorageCore tile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEnergyStorageCore?(TileEnergyStorageCore)world.getTileEntity(x, y, z):null;
		if (tile == null) {
			ProjectFluxGear.logger.error("Missing Tile Entity (EnergyStorageCore)");
			return false;
		} else {
			if (!world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText("Tier:" + tile.getTier()));
				String BN = String.valueOf(tile.getEnergyStored());
				if(BN.substring(BN.length() - 2).contentEquals(".0")) {
					BN = BN.substring(0, BN.length() - 2);
				}

				player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("info.de.charge.txt") + ": " + Utils.formatNumber(tile.getEnergyStored()) + " / " + Utils.formatNumber(tile.getMaxEnergyStored()) + " [" + BN + " RF]"));
			}

			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		TileEnergyStorageCore tile = world.getTileEntity(x - ForgeDirection.getOrientation(side).offsetX, y - ForgeDirection.getOrientation(side).offsetY, z - ForgeDirection.getOrientation(side).offsetZ) != null && world.getTileEntity(x - ForgeDirection.getOrientation(side).offsetX, y - ForgeDirection.getOrientation(side).offsetY, z - ForgeDirection.getOrientation(side).offsetZ) instanceof TileEnergyStorageCore?(TileEnergyStorageCore)world.getTileEntity(x - ForgeDirection.getOrientation(side).offsetX, y - ForgeDirection.getOrientation(side).offsetY, z - ForgeDirection.getOrientation(side).offsetZ):null;
		if (tile == null) {
			ProjectFluxGear.logger.error("Missing Tile Entity at BlockEnergyStorageCore.shouldSideBeRendered");
			return true;
		} else {
			return !tile.isOnline() && super.shouldSideBeRendered(world, x, y, z, side);
		}
	}

	public void func_149695_a(World world, int x, int y, int z, Block p_149695_5_) {
		TileEnergyStorageCore thisTile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEnergyStorageCore?(TileEnergyStorageCore)world.getTileEntity(x, y, z):null;
		if(thisTile != null && thisTile.isOnline() && thisTile.getTier() == 0) {
			thisTile.isStructureStillValid(false);
		}

	}

	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		TileEnergyStorageCore thisTile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEnergyStorageCore?(TileEnergyStorageCore)world.getTileEntity(x, y, z):null;
		if(thisTile != null && thisTile.isOnline() && thisTile.getTier() == 0) {
			thisTile.deactivateStabilizers();
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
		TileEnergyStorageCore thisTile = world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEnergyStorageCore?(TileEnergyStorageCore)world.getTileEntity(x, y, z):null;
		return thisTile != null && thisTile.isOnline()? AxisAlignedBB.getBoundingBox((double) thisTile.field_145851_c + 0.5D, (double) thisTile.field_145848_d + 0.5D, (double) thisTile.field_145849_e + 0.5D, (double) thisTile.field_145851_c + 0.5D, (double) thisTile.field_145848_d + 0.5D, (double) thisTile.field_145849_e + 0.5D):super.func_149633_g(world, x, y, z);
	}
}
