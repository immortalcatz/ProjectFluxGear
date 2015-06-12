package mortvana.projectfluxgear.core.common;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

public class FluxGearCoreContent {

	public static void preInit() {

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
