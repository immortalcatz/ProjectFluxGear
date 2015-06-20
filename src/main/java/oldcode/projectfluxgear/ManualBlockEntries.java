package oldcode.projectfluxgear;

import net.minecraft.block.Block;

public class ManualBlockEntries {
	public ManualBlockEntries() {}

	public static class ItemBlockOreMain extends ItemBlockFluxGear {
		public ItemBlockOreMain(Block block) {
			super(block, unlocOre, PFGBlocks.NAMES_ORES_MAIN, PFGBlocks.RARITY_ORES_MAIN);
		}
	}

	public static class ItemBlockOreAux extends ItemBlockFluxGear {
		public ItemBlockOreAux(Block block) {
			super(block, unlocOre, PFGBlocks.NAMES_ORES_AUX, PFGBlocks.RARITY_ORES_AUX);
		}
	}

	public static class ItemBlockStorageMain extends ItemBlockFluxGear {
		public ItemBlockStorageMain(Block block) {
			super(block, unlocStore, PFGBlocks.NAMES_STORE_MAIN, PFGBlocks.RARITY_STORE_MAIN);
		}
	}

	public static class ItemBlockStorageAux extends ItemBlockFluxGear {
		public ItemBlockStorageAux(Block block) {
			super(block, unlocStore, PFGBlocks.NAMES_STORE_AUX, PFGBlocks.RARITY_STORE_AUX);
		}
	}

	public static class ItemBlockAlloyMain extends ItemBlockFluxGear {
		public ItemBlockAlloyMain(Block block) {
			super(block, unlocStore, PFGBlocks.NAMES_ALLOY_MAIN, PFGBlocks.RARITY_ALLOY_MAIN);
		}
	}

	public static class ItemBlockAlloyAux extends ItemBlockFluxGear {
		public ItemBlockAlloyAux(Block block) {
			super(block, unlocStore, PFGBlocks.NAMES_ALLOY_AUX, PFGBlocks.RARITY_ALLOY_AUX);
		}
	}

	public static class ItemBlockStorageAlch extends ItemBlockFluxGear {
		public ItemBlockStorageAlch(Block block) {
			super(block, unlocStore, PFGBlocks.NAMES_STORE_ALCH, PFGBlocks.RARITY_STORE_ALCH);
		}
	}

	public static class ItemBlockStorageAdv extends ItemBlockFluxGear {
		public ItemBlockStorageAdv(Block block) {
			super(block, unlocStore, PFGBlocks.NAMES_STORE_ADV, PFGBlocks.RARITY_STORE_ADV);
		}
	}

	public static class ItemBlockEarthen extends ItemBlockFluxGear {
		public ItemBlockEarthen(Block block) {
			super(block, unlocDefault, PFGBlocks.NAMES_EARTH, PFGBlocks.RARITY_EARTH);
		}
	}

	public static class ItemBlockGravelOreMain extends ItemBlockFluxGear {
		public ItemBlockGravelOreMain(Block block) {
			super(block, unlocGravel, PFGBlocks.NAMES_ORES_MAIN, PFGBlocks.RARITY_ORES_MAIN);
		}
	}

	public static class ItemBlockGravelOreAux extends ItemBlockFluxGear {
		public ItemBlockGravelOreAux(Block block) {
			super(block, unlocGravel, PFGBlocks.NAMES_ORES_AUX, PFGBlocks.RARITY_ORES_AUX);
		}
	}

	public static class ItemBlockPoorOreMain extends ItemBlockFluxGear {
		public ItemBlockPoorOreMain(Block block) {
			super(block, unlocPoore, PFGBlocks.NAMES_ORES_MAIN, PFGBlocks.RARITY_ORES_MAIN);
		}
	}

	public static class ItemBlockPoorOreAux extends ItemBlockFluxGear {
		public ItemBlockPoorOreAux(Block block) {
			super(block, unlocPoore, PFGBlocks.NAMES_ORES_AUX, PFGBlocks.RARITY_ORES_AUX);
		}
	}

	public static String unlocDefault = "tile.projectfluxgear.block.";
	public static String unlocOre = "tile.projectfluxgear.ore.";
	public static String unlocStore = "tile.projectfluxgear.storage.";
	public static String unlocGravel = "tile.projectfluxgear.oreGravel.";
	public static String unlocPoore = "tile.projectfluxgear.orePoor.";
}
