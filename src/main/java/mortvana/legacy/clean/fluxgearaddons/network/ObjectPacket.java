package mortvana.legacy.clean.fluxgearaddons.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import mortvana.legacy.clean.fluxgearaddons.util.block.tile.TileObjectSync;

public class ObjectPacket implements IMessage {
	public static final byte BYTE = 0;
	public static final byte SHORT = 1;
	public static final byte INT = 2;
	public static final byte LONG = 3;
	public static final byte FLOAT = 4;
	public static final byte DOUBLE = 5;
	public static final byte BOOLEAN = 6;
	public static final byte CHAR = 7;
	public static final byte STRING = 8;
	public int x;
	public int y;
	public int z;
	public short index;
	public short dataType = -1;
	public Object object;
	public boolean isContainerPacket;

	public ObjectPacket() {}

	public ObjectPacket(TileObjectSync tile, byte dataType, int index, Object object) {
		isContainerPacket = tile == null;
		if(!isContainerPacket) {
			x = tile.xCoord;
			y = tile.yCoord;
			z = tile.zCoord;
		}

		this.dataType = (short)dataType;
		this.object = object;
		this.index = (short)index;
	}

	public void toBytes(ByteBuf bytes) {
		bytes.writeBoolean(isContainerPacket);
		if(!isContainerPacket) {
			bytes.writeInt(x);
			bytes.writeInt(y);
			bytes.writeInt(z);
		}

		bytes.writeByte(dataType);
		bytes.writeShort(index);
		switch(dataType) {
			case 0:
				bytes.writeByte(((Byte)object));
				break;
			case 1:
				bytes.writeShort(((Short)object));
				break;
			case 2:
				bytes.writeInt(((Integer)object));
				break;
			case 3:
				bytes.writeLong(((Long)object));
				break;
			case 4:
				bytes.writeFloat(((Float)object));
				break;
			case 5:
				bytes.writeDouble(((Double)object));
				break;
			case 6:
				bytes.writeBoolean(((Boolean)object));
				break;
			case 7:
				bytes.writeChar(((Character)object));
				break;
			case 8:
				ByteBufUtils.writeUTF8String(bytes, (String)object);
		}

	}

	public void fromBytes(ByteBuf bytes) {
		isContainerPacket = bytes.readBoolean();
		if(!isContainerPacket) {
			x = bytes.readInt();
			y = bytes.readInt();
			z = bytes.readInt();
		}

		dataType = (short)bytes.readByte();
		index = bytes.readShort();
		switch(dataType) {
			case 0:
				object = bytes.readByte();
				break;
			case 1:
				object = bytes.readShort();
				break;
			case 2:
				object = bytes.readInt();
				break;
			case 3:
				object = bytes.readLong();
				break;
			case 4:
				object = bytes.readFloat();
				break;
			case 5:
				object = bytes.readDouble();
				break;
			case 6:
				object = bytes.readBoolean();
				break;
			case 7:
				object = bytes.readChar();
				break;
			case 8:
				object = ByteBufUtils.readUTF8String(bytes);
		}

	}
}
