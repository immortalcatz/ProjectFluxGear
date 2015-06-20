package oldcode.projectfluxgear;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;

public class CommonProxy implements IGuiHandler, MessageBase.IFluxGearProxy {

	public void registerEntities() {
    }

    public void registerRenderers() {
    }

    /* Weird Science Artifacts */
    public void registerSound() {
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerIcons(TextureStitchEvent.Pre event) {

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void initializeIcons(TextureStitchEvent.Post event) {

    }

    /* IGuiHandler */

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IGuiHandler handler = serverGuiHandlers.get(ID);
        if (handler != null)
            return handler.getServerGuiElement(ID, player, world, x, y, z);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        IGuiHandler handler = clientGuiHandlers.get(ID);
        if (handler != null)
            return handler.getClientGuiElement(ID, player, world, x, y, z);
        return null;
    }

    public static HashMap<Integer, IGuiHandler> serverGuiHandlers = new HashMap<Integer, IGuiHandler>();
    public static HashMap<Integer, IGuiHandler> clientGuiHandlers = new HashMap<Integer, IGuiHandler>();

    public static void registerServerGuiHandler (int gui, IGuiHandler handler) {
        serverGuiHandlers.put(gui, handler);
    }

    public static void registerClientGuiHandler (int gui, IGuiHandler handler) {
        clientGuiHandlers.put(gui, handler);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void preInit() {	}

	public void init() {}

	public void postInit() {}

	public void sendToAll(MessageBase message) {

	}

	public void sendToPlayer(MessageBase message, EntityPlayer player) {

	}

	public void sendToServer(MessageBase message) {

	}

	public boolean isSimulating(World world) {
		return true;
	}

	public World getWorld() {
		return null;
	}
}
