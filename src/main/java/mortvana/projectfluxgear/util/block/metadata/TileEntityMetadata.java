package mortvana.projectfluxgear.util.block.metadata;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.util.network.FluxGearMessageTile.FluxGearMessageMetadata;

public class TileEntityMetadata extends TileEntity {

	/** Use setTileMetadata for editing this, as it logs if it will likely derp. */
	public int metadata = 0;
	public int oldMetadata = -1;

	public TileEntityMetadata() { /* Singleton */ }

	public TileEntityMetadata(byte metadata) {
		setTileMetadata(metadata);
	}

	public TileEntityMetadata(Byte metadata) {
		setTileMetadata(metadata);
	}

	public boolean hasDropped = false;

	public boolean canUpdate() {
		return false;
	}

	public Packet getDescriptionPacket() {
		if (metadata == oldMetadata) {
			return null;
		}
		oldMetadata = metadata;
		return ProjectFluxGear.packetHandler.getPacketToClient(new FluxGearMessageMetadata(xCoord, yCoord, zCoord, metadata));
	}

	public int getTileMetadata() {
		return metadata;
	}

	public void setTileMetadata(int metadata) {
		if (metadata >= 65536 ) {
			ProjectFluxGear.logger.error(getBlockType().getUnlocalizedName() + " (" + getBlockType() + ") is using a metadata of 65536 or greater (>= 65536), this will derp hard, and likely crash, report this to me, slapping me a squid or trout is optional");
		}
		this.metadata = metadata;
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		metadata = nbt.getInteger("Metadata");
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Metadata", metadata);
	}
}
