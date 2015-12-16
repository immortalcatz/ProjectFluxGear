package mortvana.legacy.dependent.firstdegree.core.client.gui;

import java.util.HashMap;

import mortvana.legacy.clean.thaumicrevelations.inventory.ContainerHammer;
import mortvana.legacy.clean.morttech.block.tile.WoodmillLogic;
import mortvana.legacy.clean.morttech.client.gui.GuiWoodmill;
import mortvana.legacy.clean.thaumicrevelations.client.gui.GuiWaslieHammer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;

import mortvana.legacy.clean.morttech.inventory.ContainerWoodmill;

public class FluxGearGUIHandler implements IGuiHandler {

	public static int woodmill = 1;

	public static void init() {

		NetworkRegistry.INSTANCE.registerGuiHandler(ProjectFluxGear.instance, new FluxGearGUIHandler());

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {
			case 0:
				return new ContainerHammer(player);
		}

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof WoodmillLogic) {
			return new ContainerWoodmill(player.inventory, (WoodmillLogic) tileEntity);
		}
		IGuiHandler handler = serverGuiHandlers.get(ID);

		if (handler != null)
			return handler.getServerGuiElement(ID, player, world, x, y, z);

		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {
			case 0:
				return new GuiWaslieHammer(player);
		}

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof WoodmillLogic) {
			return new GuiWoodmill(player.inventory, (WoodmillLogic) tileEntity);
		}

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
}
