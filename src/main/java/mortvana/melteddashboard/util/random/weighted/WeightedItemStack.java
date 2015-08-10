package mortvana.melteddashboard.util.random.weighted;

import net.minecraft.item.ItemStack;

public class WeightedItemStack extends WeightedEntry {

	public ItemStack ITEMSTACK;

	public WeightedItemStack(ItemStack itemstack) {
		super(100);
		this.ITEMSTACK = itemstack;
	}

	public WeightedItemStack(ItemStack itemstack, int weight) {
		super(weight);
		this.ITEMSTACK = itemstack;
	}

	public ItemStack getItemStack() {
		return ITEMSTACK;
	}
}
