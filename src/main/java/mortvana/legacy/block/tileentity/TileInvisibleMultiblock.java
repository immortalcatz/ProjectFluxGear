package mortvana.legacy.block.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import mortvana.legacy.util.helpers.MultiblockHelper.TileLocation;
import mortvana.projectfluxgear.core.common.ProjectFluxGear;

public class TileInvisibleMultiblock extends TileEntity {
	public TileLocation master = new TileLocation();

	public TileInvisibleMultiblock() {
	}

	public boolean isMasterOnline() {
		TileEnergyStorageCore tile = this.field_145850_b.getTileEntity(this.master.getXCoord(), this.master.getYCoord(), this.master.getZCoord()) != null && this.field_145850_b.getTileEntity(this.master.getXCoord(), this.master.getYCoord(), this.master.getZCoord()) instanceof TileEnergyStorageCore?(TileEnergyStorageCore)this.field_145850_b.getTileEntity(this.master.getXCoord(), this.master.getYCoord(), this.master.getZCoord()):null;
		return tile == null?false:tile.online;
	}

	public TileEnergyStorageCore getMaster() {
		if(this.master == null) {
			return null;
		} else {
			TileEnergyStorageCore tile = this.field_145850_b.getTileEntity(this.master.getXCoord(), this.master.getYCoord(), this.master.getZCoord()) != null && this.field_145850_b.getTileEntity(this.master.getXCoord(), this.master.getYCoord(), this.master.getZCoord()) instanceof TileEnergyStorageCore?(TileEnergyStorageCore)this.field_145850_b.getTileEntity(this.master.getXCoord(), this.master.getYCoord(), this.master.getZCoord()):null;
			return tile;
		}
	}

	public void func_145841_b(NBTTagCompound compound) {
		super.writeToNBT(compound);
		this.master.writeToNBT(compound, "Key");
	}

	public void func_145839_a(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.master.readFromNBT(compound, "Key");
	}

	public Packet func_145844_m() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.func_145841_b(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbttagcompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.func_145839_a(pkt.func_148857_g());
	}

	public void isStructureStillValid() {
		if(this.getMaster() == null) {
			ProjectFluxGear.logger.error("{Tile} Master = null reverting!");
			this.revert();
		} else {
			if(!this.getMaster().isOnline()) {
				this.revert();
			}

		}
	}

	private void revert() {
		int meta = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e);
		if(meta == 0) {
			this.field_145850_b.setBlock(this.field_145851_c, this.field_145848_d, this.field_145849_e, ModBlocks.draconiumBlock);
		} else if(meta == 1) {
			this.field_145850_b.setBlock(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.redstone_block);
		}

	}
}
