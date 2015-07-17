package oldcode.legacy.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

import io.netty.buffer.ByteBuf;

public class ParticleGenPacket implements IMessage {
	public byte buttonId = 0;
	public short value = 0;
	public int tileX = 0;
	public int tileY = 0;
	public int tileZ = 0;

	public ParticleGenPacket() {
	}

	public ParticleGenPacket(byte buttonId, short value, int x, int y, int z) {
		this.buttonId = buttonId;
		this.value = value;
		tileX = x;
		tileY = y;
		tileZ = z;
	}

	public void toBytes(ByteBuf bytes) {
		bytes.writeByte(buttonId);
		bytes.writeShort(value);
		bytes.writeInt(tileX);
		bytes.writeInt(tileY);
		bytes.writeInt(tileZ);
	}

	public void fromBytes(ByteBuf bytes) {
		buttonId = bytes.readByte();
		value = bytes.readShort();
		tileX = bytes.readInt();
		tileY = bytes.readInt();
		tileZ = bytes.readInt();
	}
}
