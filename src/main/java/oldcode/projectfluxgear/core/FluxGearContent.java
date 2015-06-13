package oldcode.projectfluxgear.core;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

import oldcode.projectfluxgear.util.item.ItemFluxGear;

public class FluxGearContent {
	public static void loadBlocks() {
		blockPlant = new BlockPlant();
		GameRegistry.registerBlock(blockPlant, ItemBlockPlant.class, "plant");

	}

	public static void loadItems() {
		itemMaterial = (ItemFluxGear) new ItemFluxGear("fluxgear").setUnlocalizedName("material").setCreativeTab(ProjectFluxGear.tabMaterials);
	}

	public static Block blockPlant;

	public static ItemFluxGear itemMaterial;
}
