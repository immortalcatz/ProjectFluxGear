package mortvana.melteddashboard.module.classifiers;

import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.module.IFluxGearModule;

public interface IWeightedModule extends IFluxGearModule {
	public float getWeight(ItemStack itemstack);
}
