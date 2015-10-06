package mortvana.legacy.clean.crystalclimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCorruptor extends Block {
	public BlockCorruptor() {
		super(Material.rock);
		setTickRandomly(true);
		setHardness(1.0F);
	}

	public int tickRate() {
		return 5;
	}

	public int damageDropped(int meta) {
		return meta % 8;
	}
}
