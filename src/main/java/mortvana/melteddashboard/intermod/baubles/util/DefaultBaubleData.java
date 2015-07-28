package mortvana.melteddashboard.intermod.baubles.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public final class DefaultBaubleData extends BaubleData {

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}
}
