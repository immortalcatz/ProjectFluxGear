package oldcode.morttech;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import oldcode.morttech.inventory.WoodmillContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

//Common side of the proxy. Provides functions which
//the ClientProxy and ServerProxy will override if the behaviour is different for client and
//server, and keeps some common behaviour.
public class CommonProxy implements IGuiHandler {

    public static final int woodmillGui = 1;

    @Override
    public Object getServerGuiElement (int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == woodmillGui) {
            return null;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    /* Registers any rendering code. Does nothing server-side */
    public void registerRenderer() {
	    // Nothing here as the server doesn't render graphics or entities!
	}

    public void registerTickHandler (){

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

	public void registerRenderers() {

	}
}
