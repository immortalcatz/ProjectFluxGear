package mortvana.projectfluxgear.tech.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.IFuelHandler;

import mortvana.melteddashboard.block.FluxGearBlock;
import mortvana.melteddashboard.registry.material.EnumMaterialType;
import mortvana.melteddashboard.registry.material.MaterialEntry;
import mortvana.melteddashboard.util.ColorLibrary;
import mortvana.melteddashboard.util.ConfigBase;
import mortvana.melteddashboard.util.IConfigInitialized;
import mortvana.projectfluxgear.core.common.FluxGearCoreContent;

import static mortvana.projectfluxgear.core.common.FluxGearCoreContent.generalItem;

public class FluxGearTech implements IConfigInitialized, IFuelHandler {

	@Override
	public void preInit(ConfigBase config) {}

	@Override
	public void init(ConfigBase config) {
		for (MaterialEntry material : materials) {
			FluxGearCoreContent.dynMatReg.addMaterial(material);
		}
		rawFilament = generalItem.addOreDictItem(17000, "unrifinedFilament", "partRawFilament");
	}

	@Override
	public void postInit(ConfigBase config) {

	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		return 0;
	}




	public static void registerMaterials() {}

	public static Block blockPrimaryOre = new FluxGearBlock(Material.iron);
	public static Block blockSecondaryOre;
	public static Block blockTertiaryOre;
	public static Block blockPrimaryPoorOre;
	public static Block blockSecondaryPoorOre;
	public static Block blockTertiaryPoorOre;
	public static Block blockPrimaryGravelOre;
	public static Block blockSecondaryGravelOre;
	public static Block blockTertiaryGravelOre;

	public static ItemStack rawFilament;

	public static MaterialEntry copper = new MaterialEntry(0, EnumMaterialType.MALLEABLE_METAL, "copper", "copper", new String[] {"Copper"}, 5.0F, 6.0F, 0, 1, 0, 0, ColorLibrary.COLOR_MATERIAL_COPPER);
	public static MaterialEntry tin = new MaterialEntry(1, EnumMaterialType.INGOT_METAL, "tin", "tin", new String[] {"Tin"}, 5.0F, 6.0F, 0, 1, 0, 0, ColorLibrary.COLOR_MATERIAL_TIN);
	public static MaterialEntry lead = new MaterialEntry(2, EnumMaterialType.INGOT_METAL, "lead", "lead", new String[] {"Lead"}, 4.0F, 12.0F, 0, 2, 0, 0, ColorLibrary.COLOR_MATERIAL_LEAD);
	public static MaterialEntry silver = new MaterialEntry(3, EnumMaterialType.INGOT_METAL, "silver", "silver", new String[] {"Silver"}, 5.0F, 6.0F, 0, 2, 4, 0, ColorLibrary.COLOR_MATERIAL_SILVER);
	public static MaterialEntry nickel = new MaterialEntry(4, EnumMaterialType.INGOT_METAL, "nickel", "nickel", new String[] {"Nickel"}, 7.0F, 6.0F, 0, 2, 0, 0, ColorLibrary.COLOR_MATERIAL_NICKEL);
	//public static MaterialEntry zinc = new MaterialEntry(5, EnumMaterialType.INGOT_METAL, "zinc", "zinc", new String[] {"Zinc"}, 5.0F, 6.0F, 0, 1 , 0, 0/*, ColorHelper.COLOR_MATERIAL_*/);
	//public static MaterialEntry bismuth = new MaterialEntry(6, EnumMaterialType.INGOT_METAL, "bismuth", "bismuth", new String[] {"Bismuth"}, 5.0F, 8.0F, 0, 1, 2, 0/*, ColorHelper.COLOR_MATERIAL_*/);
	//public static MaterialEntry manganese = new MaterialEntry(7, EnumMaterialType.INGOT_METAL, "manganese", "manganese", new String[] {"Manganese"}, 5.0F, 6.0F, 0, 1, 0, 0/*, ColorHelper.COLOR_MATERIAL_*/);
	//public static MaterialEntry aluminium = new MaterialEntry(8, EnumMaterialType.INGOT_METAL, "aluminium", "aluminium", new String[] {"Aluminium", "Aluminum", FluxGearCoreConfig.naturalAl ? "NaturalAluminum" : ""}, 5.0F, 8.0F, 0, 1, 2, 0/*, ColorHelper.COLOR_MATERIAL_*/);
	public static MaterialEntry platinum = new MaterialEntry(9, EnumMaterialType.INGOT_METAL, "platinum", "platinum", new String[] {"Platinum"}, 5.0F, 6.0F, 2, 2, 4, 0, ColorLibrary.COLOR_MATERIAL_PLATINUM);
	//public static MaterialEntry palladium = new MaterialEntry(10, EnumMaterialType.INGOT_METAL, "palladium", "palladium", new String[] {"Palladium"}, 5.0F, 6.0F, 1, 2, 2, 0/*, ColorHelper.COLOR_MATERIAL_*/);
	//public static MaterialEntry molybdenum = new MaterialEntry(11, EnumMaterialType.INGOT_METAL, "molybdenum", "molybdenum", new String[] {"Molybdenum"}, 6.0F, 7.0F, 0, 2, 0, 0/*, ColorHelper.COLOR_MATERIAL_*/);
	//public static MaterialEntry cobalt = new MaterialEntry(12, EnumMaterialType.INGOT_METAL, "cobalt", "cobalt", new String[] {"NaturalCobalt", FluxGearCoreConfig.coAssimilation ? "Cobalt" : ""}, 7.0F, 8.0F, 1, 2, 0, 0/*, ColorHelper.COLOR_MATERIAL_*/);
	//public static MaterialEntry tungsten = new MaterialEntry(13, EnumMaterialType.INGOT_METAL, "tungsten", "tungsten", new String[] {"Tungsten"}, 10.0F, 25.0F, 1, 3, 0, 0/*, ColorHelper.COLOR_MATERIAL_*/);
	//public static MaterialEntry titanium = new MaterialEntry(14, EnumMaterialType.INGOT_METAL, "titanium", "titanium", new String[] {"Titanium"}, 8.0F, 12.0F, 1, 2, 0, 0/*, ColorHelper.COLOR_MATERIAL_*/);
	//public static MaterialEntry chromium = new MaterialEntry(15, EnumMaterialType.INGOT_METAL, "chromium", "chromium", new String[] {"Chromium", "Chrome"}, 7.0F, 10.0F, 1, 2, 0, 0/*, ColorHelper.COLOR_MATERIAL_*/);

	public static MaterialEntry[] materials = new MaterialEntry[] { copper, tin, lead, silver, nickel, platinum };


}
