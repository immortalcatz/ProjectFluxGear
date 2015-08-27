package mortvana.legacy.network;

import java.io.IOException;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import io.netty.buffer.ByteBuf;
import mortvana.legacy.util.block.metadata.BlockMetadata;
import mortvana.legacy.errored.core.common.ProjectFluxGear;

public class MessageBase {
	int id;
	public int x;
	public int y;
	public int z;
	public int meta;

	public MessageBase(int id, int posX, int posY, int posZ, int meta) {
		this.id = CorePacketID.TileMetadata.ordinal();
		x = posX;
		y = posY;
		z = posZ;
		this.meta = meta;
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

	public void writeData(ByteBuf data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
		data.writeInt(meta);
	}

	public void readData(ByteBuf data) throws IOException {
		x = data.readInt();
		y = data.readInt();
		z = data.readInt();
		meta = data.readInt();
	}

	public ChunkCoordinates getCoordinates() {
		return new ChunkCoordinates(x, y, z);
	}

	public TileEntity getTileEntity(World world) {
		return world.getTileEntity(x, y, z);
	}

	public static final class MessageCore implements IMessage {

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

	public enum CorePacketID {
		TileMetadata;

		private CorePacketID() {}

		public void onMessage(MessageCore message, MessageContext context) {
			if (this == TileMetadata) {
				MessageBase packet = new MessageBase(message);
				TileEntity tile = packet.getTileEntity(ProjectFluxGear.proxy.getWorld());
				if (tile instanceof BlockMetadata.TileEntityMetadata) {
					((BlockMetadata.TileEntityMetadata) tile).setTileMetadata(packet.meta, true);
				}
			}
		}
	}

}
