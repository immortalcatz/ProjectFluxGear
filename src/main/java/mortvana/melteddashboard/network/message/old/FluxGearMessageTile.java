package mortvana.melteddashboard.network.message.old;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import io.netty.buffer.ByteBuf;
import mortvana.melteddashboard.network.message.old.FluxGearMessage;

public abstract class FluxGearMessageTile extends FluxGearMessage {

	public int x, y, z;

	public FluxGearMessageTile() { /* Singleton */ }

	public FluxGearMessageTile(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void encodeTo(ByteBuf target) {
		target.writeInt(x);
		target.writeShort(y);
		target.writeInt(z);
	}

	@Override
	public void decodeFrom(ByteBuf source) {
		x = source.readInt();
		y = source.readShort();
		z = source.readInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public abstract void handle();
}
