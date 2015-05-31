package oldcode.projectfluxgear.util.network.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import mortvana.projectfluxgear.util.network.packet.MessageBase;

public abstract interface IFluxGearProxy {
	//Initialization
	public abstract void preInit();

	public abstract void init();

	public abstract void postInit();

	//Network
	public abstract void sendToAll(MessageBase message);

	public abstract void sendToPlayer(MessageBase message, EntityPlayer player);

	public abstract void sendToServer(MessageBase message);

	//World
	public abstract boolean isSimulating(World world);

	public abstract World getWorld();
}
