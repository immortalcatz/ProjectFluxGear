package mortvana.projectfluxgear.core.common;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import mortvana.melteddashboard.block.metadata.FluxGearBlockExtendedMetadata;
import mortvana.melteddashboard.block.metadata.ItemBlockMetadata;
import mortvana.melteddashboard.block.metadata.TileEntityMetadata;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.registry.DynamicMaterialRegistry;

public class FluxGearCoreContent {

	public static DynamicMaterialRegistry dynMatReg;

	public static FluxGearCreativeTab dynMaterialTab = new FluxGearCreativeTab("PFG-DynamicMaterials", "fluxgear.dynMaterial", new ItemStack(Items.iron_ingot));



	public static void preInit() {
	}

	public static void init() {
		loadVanillaOreDict();
		initCoreBlocks();
		dynMatReg = new DynamicMaterialRegistry(metaStorageBlock);
		registerMaterials();
	}

	public static void postInit() {
		dynMatReg.postInit();
	}

	public static void loadVanillaOreDict() {
		OreDictionary.registerOre("magmaCream", Items.magma_cream);
		OreDictionary.registerOre("dustBlaze", Items.blaze_powder);
	}

	public static void initCoreBlocks() {
		GameRegistry.registerTileEntity(TileEntityMetadata.class, "TileMetadata");

		metaStorageBlock = new FluxGearBlockExtendedMetadata(Material.iron, dynMaterialTab, new TileEntityMetadata(), "fluxgear:block/", "DynamicStorageBlock");
		GameRegistry.registerBlock(metaStorageBlock, ItemBlockMetadata.class, "storageTest");
	}

	public static void registerMaterials() {

		dynMatReg.sortEntries();
	}

	public static FluxGearBlockExtendedMetadata metaStorageBlock;
}
