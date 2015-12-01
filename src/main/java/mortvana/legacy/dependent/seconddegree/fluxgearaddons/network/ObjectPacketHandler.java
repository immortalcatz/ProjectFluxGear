package mortvana.legacy.dependent.seconddegree.fluxgearaddons.network;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import mortvana.legacy.dependent.firstdegree.fluxgearaddons.util.block.tile.ContainerDataSync;
import mortvana.legacy.dependent.firstdegree.fluxgearaddons.util.block.tile.TileObjectSync;

public class ObjectPacketHandler implements IMessageHandler<ObjectPacket, IMessage> {

	public ObjectPacketHandler() {}

	public IMessage onMessage(ObjectPacket message, MessageContext ctx) {
		if (message.isContainerPacket) {
			ContainerDataSync container = Minecraft.getMinecraft().thePlayer.openContainer instanceof ContainerDataSync ? (ContainerDataSync) Minecraft.getMinecraft().thePlayer.openContainer : null;
			if (container == null) {
				return null;
			}

			container.receiveSyncData(message.index, message.object);
		} else {
			if (!(Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z) instanceof TileObjectSync)) {
				return null;
			}

			((TileObjectSync) Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z)).receiveObject(message.index, message.object);
		}
		return null;
	}
}