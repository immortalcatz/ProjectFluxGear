package oldcode.projectfluxgear.core;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.oredict.OreDictionary;

import mortvana.projectfluxgear.util.item.ItemFluxGear;

public class FluxGearContent {
	public static void loadBlocks() {
		blockPlant = new BlockPlant();
		GameRegistry.registerBlock(blockPlant, ItemBlockPlant.class, "plant");

		blockMetaStorage = new BlockMetaStorage();
		GameRegistry.registerBlock(blockMetaStorage, ItemBlockMetaStorage.class, "storage");
	}

	public static void loadItems() {
		itemMaterial = (ItemFluxGear) new ItemFluxGear("fluxgear").setUnlocalizedName("material").setCreativeTab(ProjectFluxGear.tabMaterials);
	}

	public static Block blockPlant;
	public static Block blockMetaStorage;

	public static ItemFluxGear itemMaterial;
}
