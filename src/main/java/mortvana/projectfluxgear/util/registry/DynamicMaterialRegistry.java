package mortvana.projectfluxgear.util.registry;

import java.util.*;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.oredict.OreDictionary;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.core.config.FluxGearCoreConfig;
import mortvana.projectfluxgear.util.block.metadata.FluxGearBlockExtendedMetadata;

public class DynamicMaterialRegistry {
	public ArrayList<MaterialEntry> entriesToSort = new ArrayList<MaterialEntry>();
	public TMap<Integer, MaterialEntry> materialMap = new THashMap<Integer, MaterialEntry>();
	public ArrayList<Integer> IDs = new ArrayList<Integer>();

	public FluxGearBlockExtendedMetadata block;
	final int WILD = Short.MAX_VALUE;

	private static boolean initialized = false;

	public DynamicMaterialRegistry(FluxGearBlockExtendedMetadata block) {
		this.block = block;
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
			int entryID = entry.materialID;
			String entryName = entry.materialName;

			if (!(materialMap.containsKey(entryID) && entryName.equals(""))) {
				EnumMaterialType entryType = entry.materialType;
				if (!(entryID < 0 || entryType != null)) {
					float entryBlockHardness = entry.materialBlockHardness;
					if (entryBlockHardness < 0.0F && entryBlockHardness != -1.0F) {
						entryBlockHardness = -1.0F;
					}
					float entryBlastResistance = entry.materialBlastResistance;
					if (entryBlastResistance < 0.0F && entryBlastResistance!= -1.0F) {
						entryBlastResistance = -1.0F;
					}
					int entryRarity = entry.materialRarity;
					if (entryRarity > 3 || entryRarity < 0) {
						entryRarity = 0;
					}
					int entryBlockLight = entry.materialBlockLight;
					if (entryBlockLight < 0 || entryBlockLight > 15) {
						entryBlockLight = 0;
					}
					int entryRedstoneSignal = entry.materialRedstoneSignal;
					if (entryRedstoneSignal < 0 || entryRedstoneSignal > 15) {
						entryRedstoneSignal = 0;
					}
					int entryHexColor = entry.materialHexColor;
					if (entryHexColor < -1 || entryHexColor > 0xFFFFFF) {
						entryHexColor = -1;
					}
					int entryMiningLevel = entry.materialMiningLevel;
					if (entryMiningLevel < -1) {
						entryMiningLevel = -1;
					}
					String[] oreEntries = entry.materialOreDict;
					String[] newOreEntries;
					if (FluxGearCoreConfig.registryOreDict) {

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
						for (int i = 0; i < newOreEntries.length; i++) {
							newOreEntries[i] = oreEntries[i];
						}
					} else {
						newOreEntries = oreEntries;
					}

					materialMap.put(entryID, new MaterialEntry(entryID, entryType, entryName, entry.materialTexture, newOreEntries, entryBlockHardness, entryBlastResistance, entryRarity, entryMiningLevel, entryBlockLight, entryRedstoneSignal, entryHexColor));
				}
			} else {
				ProjectFluxGear.logger.error("Someone registered a material \"" + entryName + "\" with the ID " + entryID + ", this ID is already used by the material \"" + materialMap.get(entry.materialID).materialName + "\"! Skipping this entry!");
			}
			entriesToSort.remove(entry);
		}

	}

	/**
	 *  Automatically called during postInit, DO NOT CALL THIS YOURSELF, and we have a safeguard for that (because I
	 *  know some idiot will try). Registers entries for associated types, so you don't have to!
	 */
	public void registerEntries() {
		if (!initialized) {
			for (Entry<Integer, MaterialEntry> materials : materialMap.entrySet()) {
				Integer id = materials.getKey();
				MaterialEntry entry = materials.getValue();
				if (id == entry.materialID) {
					if (entry.materialType.containsForm(EnumMaterialForm.BLOCK)) {
						block.setData(id, entry.materialTexture, entry.materialName);
						if (entry.materialBlockHardness != 1.0F) {
							block.setBlockHardness(id, entry.materialBlockHardness);
						}
						if (entry.materialBlastResistance != 1.0F) {
							block.setBlastResistance(id, entry.materialBlastResistance);
						}
						if (entry.materialRarity != 0) {
							block.setItemRarity(id, entry.materialRarity);
						}
						if (entry.materialBlockLight != 0) {
							block.setBlockLight(id, entry.materialBlockLight);
						}
						if (entry.materialRedstoneSignal != 0) {
							block.setRedstoneSignal(id, entry.materialRedstoneSignal);
						}
						if (entry.materialHexColor != -1) {
							block.setColorData(id, entry.materialHexColor);
						}
						if (entry.materialMiningLevel != -1 && entry.materialMiningLevel != block.harvestLevels.get(WILD)) {
							block.setMiningLevel(id, entry.materialMiningLevel);
						}
					}
				} else {
					ProjectFluxGear.logger.error("Someone messed with our registry, because " + id + " (Mapped ID) is not the same as " + entry.materialID + " (Expected ID)! Skipping this entry!");
				}

			}
			initialized = true;
		}
	}

	public void registerBlockWithHandlers() {
		for (Entry<Integer, MaterialEntry> materials : materialMap.entrySet()) {
			ItemStack itemstack = new ItemStack(block, 1, materials.getKey());
			for (String oreDictEntry : materials.getValue().materialOreDict) {
				OreDictionary.registerOre("block" + oreDictEntry, itemstack);
			}
			GameRegistry.registerCustomItemStack("block" + materials.getValue().materialName, itemstack);
			FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", itemstack);
		}
	}

	@Deprecated
	public void registerDynEMC(int ID, float EMC) {
		//TODO: Figure out EE3's API
	}

	public void postInit() {
		sortEntries();
		registerEntries();
		registerBlockWithHandlers();
	}

	public static class MaterialEntry {

		public static int materialID;
		public static EnumMaterialType materialType;
		public static String materialName;
		public static String materialTexture;
		public static String[] materialOreDict;
		public static float materialBlockHardness;
		public static float materialBlastResistance;
		public static int materialRarity;
		public static int materialMiningLevel;
		public static int materialBlockLight;
		public static int materialRedstoneSignal;
		public static int materialHexColor = -1;

		public MaterialEntry(int id, EnumMaterialType type, String name, String texture, String[] oreDict, float blockHardness, float blastResistance, int rarity, int miningLevel, int blockLight, int redstoneSignal) {
			materialID = id;
			materialType = type;
			materialName = name;
			materialTexture = texture;
			materialOreDict = oreDict;
			materialBlockHardness = blockHardness;
			materialBlastResistance = blastResistance;
			materialRarity = rarity;
			materialMiningLevel = miningLevel;
			materialBlockLight = blockLight;
			materialRedstoneSignal = redstoneSignal;
		}

		public MaterialEntry(int id, EnumMaterialType type, String name, String texture, String[] oreDict, float blockHardness, float blastResistance, int rarity, int miningLevel, int blockLight, int redstoneSignal, int hexColor) {
			this(id, type, name, texture, oreDict, blockHardness, blastResistance, rarity, miningLevel, blockLight, redstoneSignal);
			if (hexColor >= 0) {
				materialHexColor = hexColor;
			}
		}
	}

	public enum EnumMaterialType {
		MALLEABLE_METAL("MALLEABLE_METAL", EnumMaterialForm.BLOCK, EnumMaterialForm.INGOT, EnumMaterialForm.DUST, EnumMaterialForm.PLATE, EnumMaterialForm.NUTSNBOLTS, EnumMaterialForm.SHAFT, EnumMaterialForm.GEAR, EnumMaterialForm.FOIL, EnumMaterialForm.WASHER, EnumMaterialForm.BEARING),
		WORKABLE_METAL("WORKABLE_METAL"), //Steel, etc.
		INGOT_METAL("INGOT_METAL"), //Tellurium, etc.
		GEM("GEM"), //Dioptase, etc.
		DUST("DUST"), //Rust, etc.
		DUMMY("REMOVED"); //A dummy for future/undecided types;

		// TODO: More types

		public String internalName;
		public EnumMaterialForm[] associatedForms;
		EnumMaterialType(String name, EnumMaterialForm... forms) {
			//Don't register multiple things with the same name
			internalName = name;
			associatedForms = forms;
		}

		public EnumMaterialForm[] getAssociatedTypes(EnumMaterialType type) {
			return type.associatedForms;
		}

		public String getNameFromValue(EnumMaterialType type) {
			return type.internalName;
		}

		// TODO: Make this a thing
		//public EnumMaterialType getValueFromName(String name) {
		//}

		public boolean containsForm(EnumMaterialForm form) {
			for (EnumMaterialForm associatedForm : associatedForms) {
				if (associatedForm.equals(form)) {
					return true;
				}
			}
			return false;
		}
	}

	public enum EnumMaterialForm {
		BLOCK("BLOCK", EnumForms.BLOCK),
		INGOT("INGOT", EnumForms.INGOT, EnumForms.CHUNK, EnumForms.NUGGET, EnumForms.CAST_INGOT, EnumForms.INGOT_PILE, EnumForms.LARGE_INGOT),
		DUST("DUST", EnumForms.LARGE_DUST, EnumForms.DUST, EnumForms.HALF_DUST, EnumForms.THIRD_DUST, EnumForms.SMALL_DUST, EnumForms.FIFTH_DUST, EnumForms.SIXTH_DUST, EnumForms.EIGHTH_DUST, EnumForms.TINY_DUST, EnumForms.TENTH_DUST, EnumForms.SIXTEENTH_DUST, EnumForms.TWENTIETH_DUST, EnumForms.TWENTY_FIFTH_DUST, EnumForms.HUNDREDTH_DUST),
		GEM("GEM", EnumForms.INGOT, EnumForms.CHUNK, EnumForms.NUGGET),
		PLATE("PLATE", EnumForms.CASING, EnumForms.THIN_PLATE, EnumForms.PANEL, EnumForms.PLATE, EnumForms.DOUBLE_PLATE, EnumForms.THICK_PLATE, EnumForms.LARGE_PLATE, EnumForms.HEAVY_PLATE),
		NUTSNBOLTS("NUTSNBOLTS", EnumForms.NUT, EnumForms.BOLT, EnumForms.SCREW),
		SHAFT("SHAFT", EnumForms.ROD, EnumForms.SHAFT, EnumForms.LARGE_SHAFT),
		GEAR("GEAR", EnumForms.GEAR, EnumForms.COG),
		FOIL("FOIL", EnumForms.FOIL),
		WASHER("WASHER", EnumForms.WASHER, EnumForms.BUSHING),
		BEARING("BALL_BEARING", EnumForms.BALL_BEARINGS);

		public String name;
		public EnumForms[] subForms;
		EnumMaterialForm(String name, EnumForms... subForms) {
			this.name = name;
			this.subForms = subForms;
		}
	}

	public enum EnumForms {
		// Blocks
		BLOCK,
		// Ingots/Gems
		INGOT,
		CHUNK,
		NUGGET,
		// Ingots
		INGOT_PILE,
		LARGE_INGOT,
		CAST_INGOT,
		// Gems
		THIRD_GEM,
		QUARTER_GEM,
		SIXTH_GEM,
		EIGTH_GEM,
		GEM_SHARD,
		// Nuts 'n' Bolts
		BOLT,
		NUT,
		SCREW,
		// Dusts
		DUST,
		LARGE_DUST,
		HALF_DUST,
		THIRD_DUST,
		SMALL_DUST,
		FIFTH_DUST,
		SIXTH_DUST,
		EIGHTH_DUST,
		TINY_DUST,
		TENTH_DUST,
		SIXTEENTH_DUST,
		TWENTIETH_DUST,
		TWENTY_FIFTH_DUST,
		HUNDREDTH_DUST,
		// Shafts
		ROD,
		SHAFT,
		LARGE_SHAFT,
		// Gear
		GEAR,
		COG,
		// Foil
		FOIL,
		// Plating
		CASING,
		THIN_PLATE,
		PANEL,
		PLATE,
		DOUBLE_PLATE,
		THICK_PLATE,
		LARGE_PLATE,
		HEAVY_PLATE,
		// Washer
		WASHER,
		BUSHING,
		// Ball Bearings
		BALL_BEARINGS;

		// TODO: Add offset and Item registry here


		//public static final MaterialFormArray INGOTS = new MaterialFormArray(INGOT, INGOT_PILE, LARGE_INGOT, CAST_INGOT, CHUNK, NUGGET, BOLT, NUT);
		//public static final MaterialFormArray DUSTS = new MaterialFormArray(LARGE_DUST, DUST, HALF_DUST, THIRD_DUST, SMALL_DUST, FIFTH_DUST, SIXTH_DUST, EIGHTH_DUST);
		//public static final MaterialFormArray SHAFTS = new MaterialFormArray(ROD, SHAFT, LARGE_SHAFT, TINY_DUST, TENTH_DUST, TWENTIETH_DUST, TWENTY_FIFTH_DUST, HUNDREDTH_DUST);
		//public static final MaterialFormArray PLATES = new MaterialFormArray(CASING, THIN_PLATE, PANEL, PLATE, DOUBLE_PLATE, THICK_PLATE, LARGE_PLATE, HEAVY_PLATE);
		//public static final MaterialFormArray GEARS = new MaterialFormArray(GEAR, WASHER, FOIL, BALL_BEARINGS, SCREW, NULL, NULL, NULL);
		//public static final MaterialFormArray GEMS = new MaterialFormArray(THIRD_GEM, QUARTER_GEM, SIXTH_GEM, EIGHTH_GEM, GEM_SHARD, NULL, NULL, NULL);

	}
}

