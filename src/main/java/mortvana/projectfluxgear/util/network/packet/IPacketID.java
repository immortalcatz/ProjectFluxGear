package mortvana.projectfluxgear.util.network.packet;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public abstract interface IPacketID {
	public abstract int ordinal();

	public abstract void onMessage(MessageCore message, MessageContext context);
}
