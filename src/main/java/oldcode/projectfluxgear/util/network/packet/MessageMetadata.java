package oldcode.projectfluxgear.util.network.packet;

import java.io.IOException;

import io.netty.buffer.ByteBuf;

public class MessageMetadata extends MessageCoords {

	public int meta;

	public MessageMetadata(int x, int y, int z, int meta) {
		super(CorePacketID.TileMetadata.ordinal(), x, y, z);
		this.meta = meta;
	}

	public MessageMetadata(MessageCore message) {
		super(message);
	}

	public void writeData(ByteBuf data) throws IOException {
		super.writeData(data);
		data.writeInt(meta);
	}

	public void readData(ByteBuf data) throws IOException {
		super.readData(data);
		meta = data.readInt();
	}
}
