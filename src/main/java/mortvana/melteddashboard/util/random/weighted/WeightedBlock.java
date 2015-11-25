package mortvana.melteddashboard.util.random.weighted;

import java.util.Collection;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class WeightedBlock extends WeightedEntry {

	public final Block BLOCK;
	public final int METADATA;

	public WeightedBlock(ItemStack block) {
		this(Block.getBlockFromItem(block.getItem()), block.getMetadata(), 100);
	}

	public WeightedBlock(ItemStack block, int weight) {
		this(Block.getBlockFromItem(block.getItem()), block.getMetadata(), weight);
	}

	public WeightedBlock(Block block) {
		this(block, 0, 100);
	}

	public WeightedBlock(Block block, int metadata) {
		this(block, metadata, 100);
	}

	public WeightedBlock(Block block, int metadata, int weight) {
		super(weight);
		BLOCK = block;
		METADATA = metadata;
	}

	public Block getBlock() {
		return BLOCK;
	}

	public int getMetadata() {
		return METADATA;
	}

	public ItemStack getBlockAsItemstack() {
		return new ItemStack(BLOCK, 1, METADATA);
	}

	public static boolean isEntry(ItemStack itemstack, Collection<WeightedBlock> entries) {
		return isEntry(Block.getBlockFromItem(itemstack.getItem()), itemstack.getMetadata(), entries);
	}

	public static boolean isEntry(Block block, int metadata, Collection<WeightedBlock> entries) {
		for (WeightedBlock entry : entries) {
			if (block.equals(entry.BLOCK) && (metadata == -1 || entry.METADATA == -1 || metadata == entry.METADATA)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEntry(ItemStack itemstack, WeightedBlock[] entries) {
		return isEntry(Block.getBlockFromItem(itemstack.getItem()), itemstack.getMetadata(), entries);
	}

	public static boolean isEntry(Block block, int metadata, WeightedBlock[] entries) {
		for (WeightedBlock entry : entries) {
			if (block.equals(entry.BLOCK) && (metadata == -1 || entry.METADATA == -1 || metadata == entry.METADATA)) {
				return true;
			}
		}
		return false;
	}
}
