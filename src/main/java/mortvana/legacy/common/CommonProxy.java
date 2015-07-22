package mortvana.legacy.common;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;

//Common side of the proxy. Provides functions which
//the ClientProxy and ServerProxy will override if the behaviour is different for client and
//server, and keeps some common behaviour.
public class CommonProxy implements IGuiHandler {

	public void registerEntities() {
    }

    public void registerRenderers() {
    }

    /* Weird Science Artifacts */
    public void registerSound() {
    }


    /* IGuiHandler */

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	    if (ID == woodmillGui) {
		    return null;
	    }
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

	public static final int woodmillGui = 1;

	/* Registers any rendering code. Does nothing server-side */
	public void registerRenderer() {
		// Nothing here as the server doesn't render graphics or entities!
	}

	public void registerTickHandler () {
	}

	public void readManuals () {
	}

	public void registerKeys () {
	}

	public void spawnParticle (String slimeParticle, double xPos, double yPos, double zPos, double velX, double velY, double velZ) {
	}

	public boolean isSimulating()
	{
		return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

	public boolean isRendering()
	{
		return !isSimulating();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static final String DOMAIN = "mortvanabees";
	public static final String TEXTURE = "textures/";
	public static final String GUI_TEXTURE = TEXTURE + "gui/";
	public static final String ITEM_TEXTURE = TEXTURE + "items/";
	public static final String MODEL = "model/";
	public static final String LANGS = "lang/";
	public static String FORESTRY_GFX_ITEMS;
	public static String FORESTRY_GFX_BEEEFFECTS;
}
