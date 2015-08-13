package mortvana.melteddashboard.network.message;

import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import io.netty.buffer.ByteBuf;

public abstract class MessageTileCoords extends FluxGearMessageBase {

	public int x, y, z;

	public MessageTileCoords() { /* Singleton */ }

	public MessageTileCoords(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageTileCoords(TileEntity tile) {
		x = tile.xCoord;
		y = tile.yCoord;
		z = tile.zCoord;
	}

	@Override
	public void toBytes(ByteBuf target) {
		target.writeInt(x);
		target.writeShort(y);
		target.writeInt(z);
	}

	@Override
	public void fromBytes(ByteBuf source) {
		x = source.readInt();
		y = source.readShort();
		z = source.readInt();
	}
}
