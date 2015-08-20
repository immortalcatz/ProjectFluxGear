package mortvana.melteddashboard.registry.material;

import java.util.*;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.oredict.OreDictionary;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.melteddashboard.common.MeltedDashboardCore;
import mortvana.melteddashboard.common.MeltedDashboardConfig;
import mortvana.melteddashboard.block.metadata.FluxGearBlockExtendedMetadata;
import mortvana.melteddashboard.item.FluxGearItem;
import mortvana.melteddashboard.util.helpers.StringHelper;

public class DynamicMaterialRegistry {
	public ArrayList<MaterialEntry> entriesToSort = new ArrayList<MaterialEntry>();
	public TMap<Integer, MaterialEntry> materialMap = new THashMap<Integer, MaterialEntry>(2000);

	public MaterialData data;
	public FluxGearBlockExtendedMetadata block;
	public FluxGearItem[] items;

	final int WILD = Short.MAX_VALUE;

	private static boolean initialized = false;

	public DynamicMaterialRegistry(MaterialData data) {
		this.data = data;
		block = data.block;
		items = data.items;
	}

	public void addMaterial(MaterialEntry entry) {
		entriesToSort.add(entry);
	}

	/**
	 * Sorts entries after the have been initialized, call after you register your blocks in *INIT*
	 * This gets automatically called once during postInit,
	 * in case some dummy (we all make mistakes) forgets to call it during init.
	 */
	public void sortEntries() {
		for (MaterialEntry entry : entriesToSort) {
			if (entry != null) {
				int entryID = entry.getMaterialID();
				String entryName = entry.getMaterialName();

				if (!(materialMap.containsKey(entryID) && entryName.equals(""))) {
					EnumMaterialType entryType = entry.getMaterialType();
					if (entryID > -1 && entryID < 2000 && entryType != null) {
						float entryBlockHardness = entry.getMaterialBlockHardness();
						if (entryBlockHardness < 0.0F && entryBlockHardness != -1.0F) {
							entryBlockHardness = -1.0F;
						}
						float entryBlastResistance = entry.getMaterialBlastResistance();
						if (entryBlastResistance < 0.0F && entryBlastResistance!= -1.0F) {
							entryBlastResistance = -1.0F;
						}
						int entryRarity = entry.getMaterialRarity();
						if (entryRarity > 3 || entryRarity < 0) {
							entryRarity = 0;
						}
						int entryBlockLight = entry.getMaterialBlockLight();
						if (entryBlockLight < 0 || entryBlockLight > 15) {
							entryBlockLight = 0;
						}
						int entryRedstoneSignal = entry.getMaterialRedstoneSignal();
						if (entryRedstoneSignal < 0 || entryRedstoneSignal > 15) {
							entryRedstoneSignal = 0;
						}
						int entryHexColor = entry.getMaterialHexColor();
						if (entryHexColor < -1 || entryHexColor > 0xFFFFFF) {
							entryHexColor = -1;
						}
						int entryMiningLevel = entry.getMaterialMiningLevel();
						if (entryMiningLevel < -1) {
							entryMiningLevel = -1;
						}
						String[] oreEntries = entry.getMaterialOreDict();
						String[] newOreEntries;
						if (MeltedDashboardConfig.registryOreDict) {

							int q = 0;
							for (int i = 0; i < oreEntries.length; i++) {
								String oreDictEntry = oreEntries[i];
								if (oreDictEntry.equals("")) {
									q++;
								} else if (oreDictEntry.startsWith("block")) {
									oreEntries[i-q] = oreDictEntry.replaceFirst("block", "");
								} else if (oreDictEntry.startsWith("ore")) {
									oreEntries[i-q] = oreDictEntry.replaceFirst("ore", "");
								} else if (oreDictEntry.startsWith("ingot")) {
									oreEntries[i-q] = oreDictEntry.replaceFirst("ingot", "");
								} else if (oreDictEntry.startsWith("dust")) {
									oreEntries[i-q] = oreDictEntry.replaceFirst("dust", "");
								} else if (oreDictEntry.startsWith("nugget")) {
									oreEntries[i-q] = oreDictEntry.replaceFirst("nugget", "");
								} else if (oreDictEntry.startsWith("log")) {
									oreEntries[i-q] = oreDictEntry.replaceFirst("log", "");
								} else if (oreDictEntry.startsWith("item")) {
									oreEntries[i-q] = oreDictEntry.replaceFirst("item", "");
								} else if (oreDictEntry.startsWith("gem")) {
									oreEntries[i-q] = oreDictEntry.replaceFirst("gem", "");
								} else if (oreDictEntry.startsWith("crystal")) {
									oreEntries[i-q] = oreDictEntry.replaceFirst("crystal", "");
								}
							}
							newOreEntries = new String[oreEntries.length - q];
							System.arraycopy(oreEntries, 0, newOreEntries, 0, newOreEntries.length);
						} else {
							newOreEntries = oreEntries;
						}

						materialMap.put(entryID, new MaterialEntry(entryID, entryType, entryName, entry.getMaterialTexture(), newOreEntries, entryBlockHardness, entryBlastResistance, entryRarity, entryMiningLevel, entryBlockLight, entryRedstoneSignal, entryHexColor));
					} else if (entryID < 0 || entryID > 1999) {
						MeltedDashboardCore.logger.error("Someone registered a material \"" + entryName + "\" with the ID " + entryID + ", the ID must be between 0 and 1999 inclusive, skipping this entry!");
					} else if (entryType == null) {
						MeltedDashboardCore.logger.error("Someone registered a material \"" + entryName + "\" with a null MaterialType, skipping this entry!");
					}
				} else if (materialMap.containsKey(entryID)) {
					MeltedDashboardCore.logger.error("Someone registered a material \"" + entryName + "\" with the ID " + entryID + ", this ID is already used by the material \"" + materialMap.get(entry.getMaterialID()).getMaterialName() + "\"! Skipping this entry!");
				} else if (entryName.equals("")) {
					MeltedDashboardCore.logger.error("Someone registered a nameless material with the ID " + entryID + ", skipping this entry!");
				}
			} else {
				MeltedDashboardCore.logger.error("Some genius registered a null material... Yeah, don't do that. It's generally not a good idea.");
			}
		}
		entriesToSort.clear();
	}

	/**
	 *  Automatically called during postInit, DO NOT CALL THIS YOURSELF (unless it is your own registry).
	 *  Registers entries for associated types, so you don't have to!
	 */
	public void registerEntries() {
		for (Entry<Integer, MaterialEntry> materials : materialMap.entrySet()) {
			Integer id = materials.getKey();
			MaterialEntry entry = materials.getValue();
			if (id == entry.getMaterialID()) {
				delegateRegistration(id, entry);
				registerCrafting(id, entry);
			} else {
				MeltedDashboardCore.logger.error("Someone messed with our registry, because " + id + " (Mapped ID) is not the same as " + entry.getMaterialID() + " (Expected ID)! Skipping this entry!");
			}
		}
	}

	public void delegateRegistration(int id, MaterialEntry entry) {
		EnumMaterialType type = entry.getMaterialType();
		if (type.containsForm(EnumMaterialForm.BLOCK)) {
			registerBlocks(id, entry);
		}
		if (type.containsForm(EnumMaterialForm.INGOT)) {
			registerItem(id, entry, data.INGOT);
			registerItem(id, entry, data.CHUNK);
			registerItem(id, entry, data.NUGGET);
			registerItem(id, entry, data.CAST_INGOT);
			registerItem(id, entry, data.INGOT_PILE);
			registerItem(id, entry, data.LARGE_INGOT);
		}
		if (type.containsForm(EnumMaterialForm.GEAR)) {
			registerItem(id, entry, data.GEAR);
			registerItem(id, entry, data.COG);
		}
	}

	public void registerBlocks(int id, MaterialEntry entry) {
		block.setData(id, entry.getMaterialTexture(), /*"block" +*/ StringHelper.toTitleCase(entry.getMaterialName()));
		if (entry.getMaterialBlockHardness() != -1.0F) {
			block.setBlockHardness(id, entry.getMaterialBlockHardness());
		}
		if (entry.getMaterialBlastResistance() != -1.0F) {
			block.setBlastResistance(id, entry.getMaterialBlastResistance());
		}
		if (entry.getMaterialRarity() != 0) {
			block.setItemRarity(id, entry.getMaterialRarity());
		}
		if (entry.getMaterialBlockLight() != 0) {
			block.setBlockLight(id, entry.getMaterialBlockLight());
		}
		if (entry.getMaterialRedstoneSignal() != 0) {
			block.setRedstoneSignal(id, entry.getMaterialRedstoneSignal());
		}
		if (entry.getMaterialHexColor() != -1) {
			block.setColorData(id, entry.getMaterialHexColor());
		}
		if (entry.getMaterialMiningLevel() != -1 && entry.getMaterialMiningLevel() != block.harvestLevels.get(WILD)) {
			block.setMiningLevel(id, entry.getMaterialMiningLevel());
		}
		ItemStack itemstack = new ItemStack(block, 1, id);
		for (String oreDictEntry : entry.getMaterialOreDict()) {
			OreDictionary.registerOre("block" + oreDictEntry, itemstack);
		}
		GameRegistry.registerCustomItemStack("block" + StringHelper.toTitleCase(entry.getMaterialName()), itemstack);
		FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", itemstack);
	}

	public void registerItem(int id, MaterialEntry entry, MaterialSet data) {
		items[data.getIndex()].addColorizedOreDictItem(data.getOffset() + id, data.getPrefix() + StringHelper.toTitleCase(entry.getMaterialName()), entry.getMaterialRarity(), data.getPrefix(), entry.getMaterialTexture(), entry.getMaterialHexColor(), entry.getMaterialOreDict());
	}

	public void registerCrafting(int id, MaterialEntry entry) {
		String[] oredict = entry.getMaterialOreDict();
		EnumMaterialType type = entry.getMaterialType();
		if (type.containsForm(EnumMaterialForm.BLOCK) && type.containsForm(EnumMaterialForm.INGOT)) {

		}
	}

	@Deprecated
	public void registerDynEMC(int ID, float EMC) {
		//TODO: Figure out EE3's API
	}

	public void postInit() {
		sortEntries();
		registerEntries();
	}
}

