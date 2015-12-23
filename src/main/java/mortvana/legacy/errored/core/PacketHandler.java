package mortvana.legacy.errored.core;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.DimensionManager;

import mortvana.melteddashboard.util.repack.mortvana.science.math.MathHelper;

import mortvana.legacy.errored.morttweaks.common.MortTweaks;

//TODO: 1.7.10 Version...
public class PacketHandler implements IPacketHandler {
	private static Random random = MathHelper.RANDOM;

	@Override
	public void onPacketData(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if (packet.channel.equals("MortTweaks")) {
			side == Side.SERVER ? handleServerPacket(packet, (EntityPlayerMP) player) : handleClientPacket(packet, (EntityPlayerSP) player);
		}
	}

	void handleClientPacket(Packet250CustomPayload packet, EntityPlayerSP player) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

		byte packetID;

		try {
			packetID = inputStream.readByte();

			switch (packetID) {
				case 1:
					MortTweaks.overrideFoodStats(player);
					break;
				case 2:
					int dimension = inputStream.readInt();
					World world = DimensionManager.getWorld(dimension);
					int x = inputStream.readInt();
					int y = inputStream.readInt();
					int z = inputStream.readInt();

					for (int i = 0; i < 16; i++) {
						world.spawnParticle("smoke", x + random.nextFloat(), y + 1.0f + random.nextFloat() * 0.5f, z + random.nextFloat(), 0, 0, 0);
					}
					break;
			}
		} catch (Exception e) {
			System.out.println("Failed at reading client packet for MortTweaks.");
			e.printStackTrace();
		}
	}

	void handleServerPacket(Packet250CustomPayload packet, EntityPlayerMP player) {
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

		byte packetID;

		try {
			packetID = inputStream.readByte();

			switch (packetID) {
				case 1:
					break;
				case 2:

					break;
			}
		} catch (IOException e) {
			System.out.println("Failed at reading server packet for MortTweaks.");
			e.printStackTrace();
		}
	}

	Entity getEntity(World world, int id) {
		for (Object o : world.loadedEntityList) {
			if (((Entity) o).getEntityId() == id)
				return (Entity) o;
		}
		return null;
	}
}
