package oldcode.legacy.util.block.tile;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import oldcode.legacy.common.FluxGearAddons;
import oldcode.legacy.network.ObjectPacket;

public abstract class ContainerDataSync extends Container {
	public ContainerDataSync() {
	}

	public Object sendObject(TileObjectSync tile, short dataType, int index, Object object) {
		Iterator i$ = crafters.iterator();

		while(i$.hasNext()) {
			Object p = i$.next();
			FluxGearAddons.network.sendTo(new ObjectPacket(tile, (byte)2, index, object), (EntityPlayerMP)p);
		}

		return object;
	}

	@SideOnly(Side.CLIENT)
	public abstract void receiveSyncData(int par1, Object par2);
}