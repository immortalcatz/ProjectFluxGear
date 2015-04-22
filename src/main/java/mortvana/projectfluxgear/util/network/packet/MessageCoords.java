package mortvana.projectfluxgear.util.network.packet;

import java.io.IOException;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import io.netty.buffer.ByteBuf;

public class MessageCoords extends MessageBase {

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
