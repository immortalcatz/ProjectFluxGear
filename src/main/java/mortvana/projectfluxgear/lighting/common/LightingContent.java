package mortvana.projectfluxgear.lighting.common;

import net.minecraft.item.ItemStack;

import mortvana.projectfluxgear.core.common.FluxGearCoreContent;

public class LightingContent {

	public static void preInit() {

	}

	public static void init() {
		rawFilament = FluxGearCoreContent.generalItem.addOreDictItem(17000, "unrifinedFilament", "partRawFilament");



	}

	public static void postInit() {

	}

	public static ItemStack rawFilament;
}
