package mortvana.projectfluxgear.core.common;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.oredict.OreDictionary;

import mortvana.projectfluxgear.core.block.BlockMetaStorage;
import mortvana.projectfluxgear.core.block.BlockPlant;
import mortvana.projectfluxgear.core.block.ItemBlockMetaStorage;
import mortvana.projectfluxgear.core.block.ItemBlockPlant;
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

	public void loadOreDict() {
		OreDictionary.registerOre("magmaCream", Items.magma_cream);
		OreDictionary.registerOre("dustBlaze", Items.blaze_powder);
	}

	public static Block blockPlant;
	public static Block blockMetaStorage;

	public static ItemFluxGear itemMaterial;
}
