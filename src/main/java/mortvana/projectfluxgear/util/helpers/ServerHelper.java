package mortvana.projectfluxgear.util.helpers;

import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public final class ServerHelper {

	private ServerHelper() { /* Singleton */ }

	public static final boolean isClientWorld(World world) {
		return world.isRemote;
	}

	public static final boolean isServerWorld(World world) {
		return !world.isRemote;
	}

	public static final boolean isSinglePlayerServer() {
		return FMLCommonHandler.instance().getMinecraftServerInstance() != null;
	}

	public static final boolean isMultiPlayerServer() {
		return FMLCommonHandler.instance().getMinecraftServerInstance() == null;
	}
}
