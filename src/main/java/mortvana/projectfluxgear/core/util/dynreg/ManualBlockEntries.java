package mortvana.projectfluxgear.core.util.dynreg;

import net.minecraft.block.Block;

import mortvana.projectfluxgear.util.block.AdvItemBlockFluxGear;

import mortvana.projectfluxgear.to_refactor.block.BlockPFGBasic;

public class ManualBlockEntries {
	public ManualBlockEntries() {}

	public static class ItemBlockOreMain extends AdvItemBlockFluxGear {
		public ItemBlockOreMain(Block block) {
			super(block, unlocOre, BlockPFGBasic.NAMES_ORES_MAIN, BlockPFGBasic.RARITY_ORES_MAIN);
		}
	}

	public static class ItemBlockOreAux extends AdvItemBlockFluxGear {
		public ItemBlockOreAux(Block block) {
			super(block, unlocOre, BlockPFGBasic.NAMES_ORES_AUX, BlockPFGBasic.RARITY_ORES_AUX);
		}
	}

	public static class ItemBlockStorageMain extends AdvItemBlockFluxGear {
		public ItemBlockStorageMain(Block block) {
			super(block, unlocStore, BlockPFGBasic.NAMES_STORE_MAIN, BlockPFGBasic.RARITY_STORE_MAIN);
		}
	}

	public static class ItemBlockStorageAux extends AdvItemBlockFluxGear {
		public ItemBlockStorageAux(Block block) {
			super(block, unlocStore, BlockPFGBasic.NAMES_STORE_AUX, BlockPFGBasic.RARITY_STORE_AUX);
		}
	}

	public static class ItemBlockAlloyMain extends AdvItemBlockFluxGear {
		public ItemBlockAlloyMain(Block block) {
			super(block, unlocStore, BlockPFGBasic.NAMES_ALLOY_MAIN, BlockPFGBasic.RARITY_ALLOY_MAIN);
		}
	}

	public static class ItemBlockAlloyAux extends AdvItemBlockFluxGear {
		public ItemBlockAlloyAux(Block block) {
			super(block, unlocStore, BlockPFGBasic.NAMES_ALLOY_AUX, BlockPFGBasic.RARITY_ALLOY_AUX);
		}
	}

	public static class ItemBlockStorageAlch extends AdvItemBlockFluxGear {
		public ItemBlockStorageAlch(Block block) {
			super(block, unlocStore, BlockPFGBasic.NAMES_STORE_ALCH, BlockPFGBasic.RARITY_STORE_ALCH);
		}
	}

	public static class ItemBlockStorageAdv extends AdvItemBlockFluxGear {
		public ItemBlockStorageAdv(Block block) {
			super(block, unlocStore, BlockPFGBasic.NAMES_STORE_ADV, BlockPFGBasic.RARITY_STORE_ADV);
		}
	}

	public static class ItemBlockEarthen extends AdvItemBlockFluxGear {
		public ItemBlockEarthen(Block block) {
			super(block, unlocDefault, BlockPFGBasic.NAMES_EARTH, BlockPFGBasic.RARITY_EARTH);
		}
	}

	public static class ItemBlockGravelOreMain extends AdvItemBlockFluxGear {
		public ItemBlockGravelOreMain(Block block) {
			super(block, unlocGravel, BlockPFGBasic.NAMES_ORES_MAIN, BlockPFGBasic.RARITY_ORES_MAIN);
		}
	}

	public static class ItemBlockGravelOreAux extends AdvItemBlockFluxGear {
		public ItemBlockGravelOreAux(Block block) {
			super(block, unlocGravel, BlockPFGBasic.NAMES_ORES_AUX, BlockPFGBasic.RARITY_ORES_AUX);
		}
	}

	public static class ItemBlockPoorOreMain extends AdvItemBlockFluxGear {
		public ItemBlockPoorOreMain(Block block) {
			super(block, unlocPoore, BlockPFGBasic.NAMES_ORES_MAIN, BlockPFGBasic.RARITY_ORES_MAIN);
		}
	}

	public static class ItemBlockPoorOreAux extends AdvItemBlockFluxGear {
		public ItemBlockPoorOreAux(Block block) {
			super(block, unlocPoore, BlockPFGBasic.NAMES_ORES_AUX, BlockPFGBasic.RARITY_ORES_AUX);
		}
	}

	public static String unlocDefault = "tile.projectfluxgear.block.";
	public static String unlocOre = "tile.projectfluxgear.ore.";
	public static String unlocStore = "tile.projectfluxgear.storage.";
	public static String unlocGravel = "tile.projectfluxgear.oreGravel.";
	public static String unlocPoore = "tile.projectfluxgear.orePoor.";
}
