package oldcode.projectfluxgear.util.network.packet;

import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

import oldcode.projectfluxgear.core.common.ProjectFluxGear;
import oldcode.projectfluxgear.util.block.metadata.TileEntityMetadata;

public enum CorePacketID implements IPacketID {
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
