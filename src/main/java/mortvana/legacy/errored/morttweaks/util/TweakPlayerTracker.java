package mortvana.legacy.errored.morttweaks.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import mortvana.legacy.errored.morttweaks.common.MortTweaks;

public class TweakPlayerTracker implements IPlayerTracker {

	//IPlayerTracker

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		resetFood(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		resetFood(player);
	}

	private void resetFood(EntityPlayer player) {
		if (MortTweaks.disableHunger || MortTweaks.tweakHunger) {
			MortTweaks.overrideFoodStats(player);

			ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
			DataOutputStream outputStream = new DataOutputStream(bos);
			try {
				outputStream.writeByte(1);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = "MortTweaks";
			packet.data = bos.toByteArray();
			packet.length = bos.size();

			PacketDispatcher.sendPacketToPlayer(packet, (Player) player);
		}
	}
}
