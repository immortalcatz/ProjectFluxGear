package mortvana.fluxgearcore.common;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

public class FGCContent {
	public static void registerOreDict() {
		OreDictionary.registerOre("magmaCream", Items.magma_cream);
		OreDictionary.registerOre("dustBlaze", Items.blaze_powder);
	}
}
