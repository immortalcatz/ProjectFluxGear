package oldcode.projectfluxgear.util.wrapper;

import net.minecraft.block.Block;

public final class BlockWrapper {
	public Block block;
	public int metadata;

	public BlockWrapper(Block var1, int var2) {
		this.block = var1;
		this.metadata = var2;
	}

	public BlockWrapper set(Block var1, int var2) {
		if(var1 != null) {
			this.block = var1;
			this.metadata = var2;
		} else {
			this.block = null;
			this.metadata = 0;
		}

		return this;
	}

	public boolean isEqual(BlockWrapper var1) {
		return var1 != null && this.block == var1.block && this.metadata == var1.metadata;
	}

	public boolean equals(Object var1) {
		return !(var1 instanceof BlockWrapper) ? false : this.isEqual((BlockWrapper)var1);
	}

	public int hashCode() {
		return this.metadata | Block.getIdFromBlock(this.block) << 16;
	}
}
