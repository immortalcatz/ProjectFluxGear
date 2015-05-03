package mortvana.projectfluxgear.util.block.ores;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class OreBlockEntry {
	public static Block block;
	public static Class<? extends ItemBlock> itemblock;
	public static String name;
	public static String toolType;

	public OreBlockEntry(Block block, Class<? extends ItemBlock> itemblock, String name, String toolType) {
		this.block = block;
		this.itemblock = itemblock;
		this.name = name;
		this.toolType = toolType;
	}
}
