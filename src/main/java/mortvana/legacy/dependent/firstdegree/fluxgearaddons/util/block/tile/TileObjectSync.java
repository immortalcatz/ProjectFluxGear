package mortvana.legacy.dependent.firstdegree.fluxgearaddons.util.block.tile;

import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.dependent.seconddegree.fluxgearaddons.network.ObjectPacket;
import mortvana.legacy.errored.core.ProjectFluxGear;

public abstract class TileObjectSync extends TileEntity {

	public TileObjectSync() {}

	public Object sendObject(byte dataType, int index, Object object) {
		return sendObject(dataType, index, object, new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, (double) xCoord, (double) yCoord, (double) zCoord, 64.0D));
	}

	public Object sendObject(byte dataType, int index, Object object, NetworkRegistry.TargetPoint point) {
		ProjectFluxGear.network.sendToAllAround(new ObjectPacket(this, dataType, index, object), point);
		return object;
	}

	@SideOnly(Side.CLIENT)
	public abstract void receiveObject(int par1, Object par2);
}
