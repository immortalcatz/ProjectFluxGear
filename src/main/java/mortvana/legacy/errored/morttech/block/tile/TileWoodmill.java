package mortvana.legacy.errored.morttech.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileWoodmill extends TileEntity {

	public TileWoodmill() {}

	//TODO: 1.7.10 Packets
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		NBTTagCompound c = pkt.data;
		int power = c.getInteger("power");
	}
}
