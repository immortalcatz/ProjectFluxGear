package mortvana.legacy.clean.morttech.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileWoodmill extends TileEntity {

	public TileWoodmill() {}

	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound c = pkt.getNbtCompound();
		int power = c.getInteger("power");
	}
}
