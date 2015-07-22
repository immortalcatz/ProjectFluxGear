package mortvana.legacy.network;

import java.io.IOException;

import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

public class PacketCrank extends Packet {

	public int xPos;
	public int yPos;
	public int zPos;
	public int power;
	
	public PacketCrank() {
	}
	
	public PacketCrank(int x, int y, int z, int power) {
		xPos = x;
		yPos = y;
		zPos = z;
		this.power = power;
	}

	@Override
	public void readPacketData(PacketBuffer d) throws IOException {
		xPos = d.readInt();
		yPos = d.readInt();
		zPos = d.readInt();
		power = d.readInt();
	}

	@Override
	public void writePacketData(PacketBuffer d) throws IOException {
		d.writeInt(xPos);
		d.writeInt(yPos);
		d.writeInt(zPos);
		d.writeInt(power);
	}

	@Override
	public void processPacket(INetHandler n) {
		handlePowerSent(this);
	}

	public void unexpectedPacket(Packet par1Packet) {}
	
	public void handlePowerSent(PacketCrank p)
    {
        this.unexpectedPacket(p);
    }

	public int getPacketSize() {
		return 12;
	}
	
}
