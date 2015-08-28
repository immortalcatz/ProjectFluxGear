package mortvana.melteddashboard.util.data;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class FluidContainerData {

	public ItemStack filledContainer, emptyContainer;
	public Fluid fluid;
	public Block block;
	public int metadata;

	public FluidContainerData(ItemStack filledContainer, ItemStack emptyContainer, Fluid fluid, Block block, int metadata) {
		this.filledContainer = filledContainer;
		this.emptyContainer = emptyContainer;
		this.fluid = fluid;
		this.block = block;
		this.metadata = metadata;
	}

	public ItemStack getFilledContainer() {
		return filledContainer;
	}

	public ItemStack getEmptyContainer() {
		return emptyContainer;
	}

	public Fluid getFluid() {
		return fluid;
	}

	public Block getBlock() {
		return block;
	}

	public int getMetadata() {
		return metadata;
	}

	public void setFilledContainer(ItemStack filledContainer) {
		this.filledContainer = filledContainer;
	}

	public void setEmptyContainer(ItemStack emptyContainer) {
		this.emptyContainer = emptyContainer;
	}

	public void setFluid(Fluid fluid) {
		this.fluid = fluid;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public void setMetadata(int metadata) {
		this.metadata = metadata;
	}
}
