package mortvana.projectfluxgear.core.common;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import mortvana.melteddashboard.block.metadata.FluxGearBlockExtendedMetadata;
import mortvana.melteddashboard.block.metadata.FluxGearItemBlockExtendedMetadata;
import mortvana.melteddashboard.block.metadata.TileEntityMetadata;
import mortvana.melteddashboard.inventory.FluxGearCreativeTab;
import mortvana.melteddashboard.item.FluxGearItem;
import mortvana.melteddashboard.registry.material.DynamicMaterialRegistry;
import mortvana.melteddashboard.registry.material.MaterialData;
import mortvana.melteddashboard.util.ConfigBase;
import mortvana.melteddashboard.util.IConfigInitialized;

public class FluxGearCoreContent implements IConfigInitialized {

	public static FluxGearCreativeTab dynMaterialTab = new FluxGearCreativeTab("PFG-DynamicMaterials", "fluxgear.dynMaterial", new ItemStack(Items.iron_ingot));
	public static FluxGearCreativeTab componentsTab = new FluxGearCreativeTab("PFG-Components", "fluxgear.components", new ItemStack(Items.potato));

	public void preInit(ConfigBase config) {
		preInitRegistry();

		generalItem = (FluxGearItem) new FluxGearItem("fluxgear", componentsTab).setUnlocalizedName("generalMaterial");
	}

	public void init(ConfigBase config) {
		loadVanillaOreDict();
		registerMaterials();
	}

	public void postInit(ConfigBase config) {
		dynMatReg.postInit();
	}

	public void preInitRegistry() {
		metaStorageBlock = new FluxGearBlockExtendedMetadata(Material.iron, dynMaterialTab, new TileEntityMetadata(), "fluxgear:block/", "fluxgear.dynamicStorageBlock");
		metaStorageBlock.setDefaultData(5.0F, 6.0F, 0, 0, true, false, 0, "pickaxe");
		GameRegistry.registerBlock(metaStorageBlock, FluxGearItemBlockExtendedMetadata.class, "dynamicStorageBlock", metaStorageBlock.blockNames, metaStorageBlock.itemRarity);

		registryIngot = (FluxGearItem) new FluxGearItem("fluxgear", dynMaterialTab).setRegistryItem(true).setUnlocalizedName("registryIngot");

		materialData = new MaterialData(metaStorageBlock, registryIngot);
		dynMatReg = new DynamicMaterialRegistry(materialData);
	}

	public void loadVanillaOreDict() {
		OreDictionary.registerOre("magmaCream", Items.magma_cream);
		OreDictionary.registerOre("dustBlaze", Items.blaze_powder);
	}

	public void registerMaterials() {
		dynMatReg.sortEntries();
	}

	public static DynamicMaterialRegistry dynMatReg;
	public static MaterialData materialData;
	public static FluxGearBlockExtendedMetadata metaStorageBlock;
	public static FluxGearItem registryIngot;
	public static FluxGearItem generalItem;
}
