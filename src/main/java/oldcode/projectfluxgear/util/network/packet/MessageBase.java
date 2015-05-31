package oldcode.projectfluxgear.util.network.packet;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class MessageBase {
	int id;

	public MessageBase(int id) {
		this.id = id;
	}

	public MessageBase(MessageCore message) {
		try {
			readData(message.data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MessageCore getMessage() {
		return new MessageCore(id, this);
	}

	protected NBTTagCompound readNBTTagCompound(ByteBuf data) throws IOException {
		short length = data.readShort();
		if (length < 0) {
			return null;
		}
		byte[] compressed = new byte[length];
		return CompressedStreamTools.readCompressed(new ByteArrayInputStream(compressed));
	}

	protected void writeNBTTagCompound(NBTTagCompound nbt, ByteBuf data) throws IOException {
		if (nbt == null) {
			data.writeShort(-1);
		} else {
			byte[] compressed = CompressedStreamTools.compress(nbt);
			data.writeShort((short) compressed.length);
			data.writeBytes(compressed);
		}
	}

	public void writeData(ByteBuf data) throws IOException {}

	public void readData(ByteBuf data) throws IOException {}
}
