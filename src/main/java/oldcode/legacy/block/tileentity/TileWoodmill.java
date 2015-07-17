package oldcode.legacy.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileWoodmill extends TileEntity {

	public TileWoodmill() {
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		NBTTagCompound c = pkt.data;
		
		int power = c.getInteger("power");
		
		System.out.println();
	}
}
