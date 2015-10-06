package mortvana.legacy.clean.projectfluxgear.network;

import java.util.EnumMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.network.FMLOutboundHandler.*;
import cpw.mods.fml.common.network.NetworkRegistry.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.google.common.collect.Maps;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *  The underscore is because IntelliJ refuses to see this file without it, because reasons...
 */
@Deprecated
public class FluxGearPacketHandler_ <T> {

	public String channel;
	public FMLIndexedMessageToMessageCodec<T> codec;
	public EnumMap<Side, FMLEmbeddedChannel> channels;
	public EnumMap<Side, SimpleChannelInboundHandler<T>> handlers;

	public FluxGearPacketHandler_(String channel, FMLIndexedMessageToMessageCodec<T> codec) {
		this.channel = channel;
		this.codec = codec;
		channels = NetworkRegistry.INSTANCE.newChannel(channel, codec);
		handlers = Maps.newEnumMap(Side.class);
	}

	@SideOnly(Side.CLIENT)
	public SimpleChannelInboundHandler<T> getClientHandler() {
		return handlers.get(Side.CLIENT);
	}

	public SimpleChannelInboundHandler<T> getServerHandler() {
		return handlers.get(Side.SERVER);
	}

	@SideOnly(Side.CLIENT)
	public void setClientHandler(SimpleChannelInboundHandler<T> handler) {
		FMLEmbeddedChannel channel = channels.get(Side.CLIENT);
		String codecName = channel.findChannelHandlerNameForType(codec.getClass());
		if (handlers.get(Side.CLIENT) != null ) {
			channel.pipeline().remove("FluxGearClientHandler");
		}
		channel.pipeline().addAfter(codecName, "FluxGearClientHandler", handler);
		handlers.put(Side.CLIENT, handler);
	}

	public void setServerHandler(SimpleChannelInboundHandler<T> handler) {
		FMLEmbeddedChannel channel = channels.get(Side.SERVER);
		String codecName = channel.findChannelHandlerNameForType(codec.getClass());
		if (handlers.get(Side.SERVER) != null) {
			channel.pipeline().remove("FluxGearServerHandler");
		}
		channel.pipeline().addAfter(codecName, "FluxGearServerHandler", handler);
		handlers.put(Side.SERVER, handler);
	}

	public Packet getPacketToClient(T message) {
		return channels.get(Side.SERVER).generatePacketFrom(message);
	}

	@SideOnly(Side.CLIENT)
	public Packet getPacketToServer(T message) {
		return channels.get(Side.CLIENT).generatePacketFrom(message);
	}

	public void sendToAllPlayers(FluxGearMessage message) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALL);
		channels.get(Side.SERVER).writeOutbound(message);
	}

	public void sendToAllPlayersInDimension(FluxGearMessage message, int dimensionID) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.DIMENSION);
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionID);
		channels.get(Side.SERVER).writeOutbound(message);
	}

	public void sendToPlayersAround(FluxGearMessage message, TargetPoint point) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.ALLAROUNDPOINT);
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
		channels.get(Side.SERVER).writeOutbound(message);
	}

	public void sendToPlayer(FluxGearMessage message, EntityPlayer player) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.PLAYER);
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		channels.get(Side.SERVER).generatePacketFrom(message);
	}

	public void sendToServer(FluxGearMessage message) {
		channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.TOSERVER);
		channels.get(Side.CLIENT).writeOutbound(message);
	}
}
