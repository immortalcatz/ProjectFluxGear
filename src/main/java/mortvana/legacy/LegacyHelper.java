package mortvana.legacy;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class LegacyHelper {
	public static void register(Block block, Class<? extends ItemBlock> item) {
		String name = String.format("tile.%s%s", "ProjectFluxGear".toLowerCase() + ":", block.unlocalizedName.substring(block.unlocalizedName.indexOf(".") + 1)).substring(String.format("tile.%s%s", "ProjectFluxGear".toLowerCase() + ":", block.unlocalizedName.substring(block.unlocalizedName.indexOf(".") + 1)).indexOf(".") + 1);
		GameRegistry.registerBlock(block, item, name.substring(name.indexOf(":") + 1));
	}

	public static void register(Block block) {
		String name = String.format("tile.%s%s", "ProjectFluxGear".toLowerCase() + ":", block.unlocalizedName.substring(block.unlocalizedName.indexOf(".") + 1)).substring(String.format("tile.%s%s", "ProjectFluxGear".toLowerCase() + ":", block.unlocalizedName.substring(block.unlocalizedName.indexOf(".") + 1)).indexOf(".") + 1);
		GameRegistry.registerBlock(block, name.substring(name.indexOf(":") + 1));
	}

	public static final String RESOURCE_PREFIX = "projectfluxgear:";

	public static Block blockPolycarbide;
}
