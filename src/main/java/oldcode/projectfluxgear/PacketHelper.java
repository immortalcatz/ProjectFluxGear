package oldcode.projectfluxgear;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketHelper {
	public static void dispatchTEToNearbyPlayers(TileEntity tile) {
		World world = tile.getWorldObj();
		List players = world.playerEntities;
		for (Object player : players) {
			if (player instanceof EntityPlayerMP) {
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				if (MathHelper.pointDistancePlane(playerMP.posX, playerMP.posZ, tile.xCoord + 0.5, tile.zCoord + 0.5) < 64) {
					((EntityPlayerMP) player).playerNetServerHandler.sendPacket(tile.getDescriptionPacket());
				}
			}
		}
	}

	public static void dispatchTEToNearbyPlayers(World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile != null) {
			dispatchTEToNearbyPlayers(tile);
		}
	}
}
