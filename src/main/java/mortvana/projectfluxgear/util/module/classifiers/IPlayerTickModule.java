package mortvana.projectfluxgear.util.module.classifiers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IPlayerTickModule extends IFluxGearModule {
	public void onPlayerTickActive(EntityPlayer player, ItemStack itemstack);

	public void onPlayerTickInactive(EntityPlayer player, ItemStack itemstack);
}
