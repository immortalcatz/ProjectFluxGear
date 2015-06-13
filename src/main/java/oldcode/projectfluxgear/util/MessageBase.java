package oldcode.projectfluxgear.util;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

import io.netty.buffer.ByteBuf;
import oldcode.projectfluxgear.core.ProjectFluxGear;
import oldcode.projectfluxgear.util.block.metadata.TileEntityMetadata;

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

	public void writeData(ByteBuf data) throws IOException {}

	public void readData(ByteBuf data) throws IOException {}

	public static class MessageCoords extends MessageBase {

		public int x;
		public int y;
		public int z;

		public MessageCoords(MessageCore message) {
			super(message);
		}

		public MessageCoords(int id, ChunkCoordinates coords) {
			this(id, coords.posX, coords.posY, coords.posZ);
		}

		public MessageCoords(int id, int posX, int posY, int posZ) {
			super(id);
			x = posX;
			y = posY;
			z = posZ;
		}

		public void writeData(ByteBuf data) throws IOException {
			data.writeInt(x);
			data.writeInt(y);
			data.writeInt(z);
		}

		public void readData(ByteBuf data) throws IOException {
			x = data.readInt();
			y = data.readInt();
			z = data.readInt();
		}

		public ChunkCoordinates getCoordinates() {
			return new ChunkCoordinates(x, y, z);
		}

		public TileEntity getTileEntity(World world) {
			return world.getTileEntity(x, y, z);
		}
	}

	public static class MessageMetadata extends MessageCoords {

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

	public static abstract interface IPacketID {
		public abstract int ordinal();

		public abstract void onMessage(MessageCore message, MessageContext context);
	}

	public static enum CorePacketID implements IPacketID {
		NetworkEntityUpdate,
		TileMetadata,
		CraftGUIAction,
		TileDescriptionSync;

		private CorePacketID() {}

		public void onMessage(MessageCore message, MessageContext context) {
			if (this == NetworkEntityUpdate) {
				//TODO
			} else if (this == TileMetadata) {
				MessageMetadata packet = new MessageMetadata(message);
				TileEntity tile = packet.getTileEntity(ProjectFluxGear.proxy.getWorld());
				if (tile instanceof TileEntityMetadata) {
					((TileEntityMetadata) tile).setTileMetadata(packet.meta, true);
				}
			} else if (this == CraftGUIAction && context.side == Side.CLIENT) {
				//TODO
			} else if (this == CraftGUIAction && context.side == Side.SERVER && context.netHandler instanceof NetHandlerPlayServer) {
				//TODO
			} else if (this == TileDescriptionSync && context.side == Side.CLIENT) {
				//TODO
			}
		}
	}

	public static abstract interface IFluxGearProxy {
		//Initialization
		public abstract void preInit();

		public abstract void init();

		public abstract void postInit();

		//Network
		public abstract void sendToAll(MessageBase message);

		public abstract void sendToPlayer(MessageBase message, EntityPlayer player);

		public abstract void sendToServer(MessageBase message);

		//World
		public abstract boolean isSimulating(World world);

		public abstract World getWorld();
	}

}
