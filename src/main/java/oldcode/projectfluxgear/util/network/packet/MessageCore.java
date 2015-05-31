package oldcode.projectfluxgear.util.network.packet;

import java.io.IOException;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

import io.netty.buffer.ByteBuf;

public final class MessageCore implements IMessage {

	public int id;
	MessageBase message;
	ByteBuf data;

	public MessageCore() {}

	public MessageCore(int id, MessageBase message) {
		this.id = id;
		this.message = message;
	}

	public void toBytes(ByteBuf buffer) {
		buffer.writeByte(id);
		try {
			message.writeData(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fromBytes(ByteBuf buffer) {
		id = buffer.readByte();
		data = buffer;
	}
}
