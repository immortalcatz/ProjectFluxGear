package mortvana.legacy.util.block.tile;

import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.common.FluxGearAddons;
import mortvana.legacy.network.ObjectPacket;

public abstract class TileObjectSync extends TileEntity {
	public TileObjectSync() {
	}

	public Object sendObject(byte dataType, int index, Object object) {
		return sendObject(dataType, index, object, new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, (double)xCoord, (double)yCoord, (double)zCoord, 64.0D));
	}

	public Object sendObject(byte dataType, int index, Object object, NetworkRegistry.TargetPoint point) {
		FluxGearAddons.network.sendToAllAround(new ObjectPacket(this, dataType, index, object), point);
		return object;
	}

	@SideOnly(Side.CLIENT)
	public abstract void receiveObject(int par1, Object par2);
}
