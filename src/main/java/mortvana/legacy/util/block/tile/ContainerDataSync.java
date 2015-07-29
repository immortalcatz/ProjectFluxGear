package mortvana.legacy.util.block.tile;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.common.ProjectFluxGear;
import mortvana.legacy.network.ObjectPacket;

public abstract class ContainerDataSync extends Container {
	public ContainerDataSync() {
	}

	public Object sendObject(TileObjectSync tile, short dataType, int index, Object object) {

		for (Object player : crafters) {
			ProjectFluxGear.network.sendTo(new ObjectPacket(tile, (byte) 2, index, object), (EntityPlayerMP) player);
		}

		return object;
	}

	@SideOnly(Side.CLIENT)
	public abstract void receiveSyncData(int par1, Object par2);
}