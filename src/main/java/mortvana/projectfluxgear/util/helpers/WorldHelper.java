package mortvana.projectfluxgear.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldHelper {

	public static World getWorld() {
		return Minecraft.getMinecraft().theWorld;
	}

	public static EntityPlayer getPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	public static Block getBlock(IBlockAccess world, int x, int y, int z) {
		return world.getBlock(x, y, z);
	}

	public static int getXOnSide(int x, ForgeDirection side) {
		return x + side.offsetX;
	}

	public static int getYOnSide(int y, ForgeDirection side) {
		return y + side.offsetY;
	}

	public static int getZOnSide(int z, ForgeDirection side) {
		return z + side.offsetZ;
	}
}
