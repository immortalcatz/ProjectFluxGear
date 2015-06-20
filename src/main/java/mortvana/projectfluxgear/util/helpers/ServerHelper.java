package mortvana.projectfluxgear.util.helpers;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 *  Contains various helper functions to assist with Server/Client status.
 */
public final class ServerHelper {

	private ServerHelper() { /* Singleton */ }

	public static final boolean isClientWorld(World world) {
		return world.isRemote;
	}

	public static final boolean isServerWorld(World world) {
		return !world.isRemote;
	}

	public static final boolean isSinglePlayerServer() {
		return FMLCommonHandler.instance().getMinecraftServerInstance() != null;
	}

	public static final boolean isMultiPlayerServer() {
		return FMLCommonHandler.instance().getMinecraftServerInstance() == null;
	}

	/**
	 *  This function claims to circumvent a miserable failing, and those aren't fun.
	 */
	public static final void sendItemUsePacket(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int hitSide, int hitX, int hitY, int hitZ) {
		if (isServerWorld(world)) {
			return;
		}

		NetHandlerPlayClient netClientHandler = (NetHandlerPlayClient) FMLClientHandler.instance().getClientPlayHandler();
		netClientHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(x, y, z, hitSide, player.inventory.getCurrentItem(), hitX, hitY, hitZ));
	}
}
