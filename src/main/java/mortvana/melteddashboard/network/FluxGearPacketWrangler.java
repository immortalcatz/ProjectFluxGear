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
		INSTANCE.registerMessage(MessageTileBase.class, MessageTileBase.class, getNextDiscriminator(), Side.CLIENT);
	}

	//TODO: Fix This
	/*public static void registerMessage(Class <? extends IMessageHandler> messageClass, Side side) {
		INSTANCE.registerMessage(messageClass, (Class) messageClass, getNextDiscriminator(), side);
	}*/

	public static byte getNextDiscriminator() {
		return id++;
	}
}
/*Error:(24, 25) java: no suitable method found for registerMessage(java.lang.Class<capture#1 of ? extends cpw.mods.fml.common.network.simpleimpl.IMessageHandler>,java.lang.Class,byte,cpw.mods.fml.relauncher.Side)
		method cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper.<REQ,REPLY>registerMessage(cpw.mods.fml.common.network.simpleimpl.IMessageHandler<? super REQ,? extends REPLY>,java.lang.Class<REQ>,int,cpw.mods.fml.relauncher.Side) is not applicable
		(no instance(s) of type variable(s) REQ,REPLY exist so that argument type java.lang.Class<capture#1 of ? extends cpw.mods.fml.common.network.simpleimpl.IMessageHandler> conforms to formal parameter type cpw.mods.fml.common.network.simpleimpl.IMessageHandler<? super REQ,? extends REPLY>)
		method cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper.<REQ,REPLY>registerMessage(java.lang.Class<? extends cpw.mods.fml.common.network.simpleimpl.IMessageHandler<REQ,REPLY>>,java.lang.Class<REQ>,int,cpw.mods.fml.relauncher.Side) is not applicable
		(no instance(s) of type variable(s) REQ,REPLY exist so that argument type java.lang.Class<capture#1 of ? extends cpw.mods.fml.common.network.simpleimpl.IMessageHandler> conforms to formal parameter type java.lang.Class<? extends cpw.mods.fml.common.network.simpleimpl.IMessageHandler<REQ,REPLY>>)*/