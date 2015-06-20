package oldcode.projectfluxgear;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;

public final class ServerHelper {
	private ServerHelper() {
	}

	public static final boolean isClientWorld(World world) {
		return world.isRemote;
	}

	public static final boolean isServerWorld(World world) {
		return !world.isRemote;
	}

	public static final void sendItemUsePacket(ItemStack var0, EntityPlayer var1, World var2, int var3, int var4, int var5, int var6, float var7, float var8, float var9) {
		if(!isServerWorld(var2)) {
			NetHandlerPlayClient var10 = (NetHandlerPlayClient) FMLClientHandler.instance().getClientPlayHandler();
			var10.addToSendQueue(new C08PacketPlayerBlockPlacement(var3, var4, var5, var6, var1.inventory.getCurrentItem(), var7, var8, var9));
		}
	}
}