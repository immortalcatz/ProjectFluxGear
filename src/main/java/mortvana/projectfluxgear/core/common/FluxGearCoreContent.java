package mortvana.projectfluxgear.core.common;

import net.minecraft.init.Items;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import mortvana.projectfluxgear.util.block.metadata.TileEntityMetadata;

public class FluxGearCoreContent {

	public static void preInit() {
		GameRegistry.registerTileEntity(TileEntityMetadata.class, "TileMetadata");
	}

	public static void init() {
		loadVanillaOreDict();
	}

	public static void postInit() {

	}

	public static void loadVanillaOreDict() {
		OreDictionary.registerOre("magmaCream", Items.magma_cream);
		OreDictionary.registerOre("dustBlaze", Items.blaze_powder);
	}

}
