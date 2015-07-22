package mortvana.melteddashboard.module.classifiers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.module.IFluxGearModule;

public interface IPlayerTickModule extends IFluxGearModule {
	public void onPlayerTickActive(EntityPlayer player, ItemStack itemstack);

	public void onPlayerTickInactive(EntityPlayer player, ItemStack itemstack);
}
