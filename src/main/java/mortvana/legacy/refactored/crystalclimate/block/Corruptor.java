package mortvana.legacy.refactored.crystalclimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Corruptor extends Block {
	public Corruptor() {
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
