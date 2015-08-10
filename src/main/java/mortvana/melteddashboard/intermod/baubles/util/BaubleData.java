package mortvana.melteddashboard.intermod.baubles.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import baubles.api.BaubleType;

public abstract class BaubleData {

	public final BaubleType type;
	public final boolean canEquip, canUnequip;

	public BaubleData(BaubleType type) {
		this(type, true, true);
	}

	public BaubleData(BaubleType type, boolean canEquip, boolean canUnequip) {
		this.type = type;
		this.canEquip = canEquip;
		this.canUnequip = canUnequip;
	}

	public BaubleType getType() {
		return type;
	}

	public boolean canEquip() {
		return canEquip;
	}

	public boolean canUnequip() {
		return canUnequip;
	}

	public abstract void onWornTick(ItemStack itemstack, EntityLivingBase player);

	public abstract void onEquipped(ItemStack itemstack, EntityLivingBase player);

	public abstract void onUnequipped(ItemStack itemstack, EntityLivingBase player);
}
