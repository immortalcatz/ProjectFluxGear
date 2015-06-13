package mortvana.projectfluxgear.util.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldHelper {

	public static World getWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
}
