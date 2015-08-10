package mortvana.melteddashboard.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.network.message.*;

public class FluxGearPacketWrangler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MeltedDashboardCore.MOD_ID);

	public static byte id = 0;

	public static void initialize() {
		//INSTANCE.registerMessage(messageHandler, messageType, id, side);
		registerMessage();

	}

	public static void registerMessage(Class <? extends IMessageHandler> messageClass, Side side) {
		INSTANCE.registerMessage(messageClass, (Class) messageClass, getNextDiscriminator(), side);
	}

	public static byte getNextDiscriminator() {
		byte discriminator = id;
		id++;
		return discriminator;
	}
}
