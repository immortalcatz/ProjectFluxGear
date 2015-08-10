package com.bioxx.tfc.api.Interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.bioxx.tfc.api.Enums.EnumFoodGroup;

public interface IFood
{
	public EnumFoodGroup getFoodGroup();
	public int getFoodID();
	public float getDecayRate(ItemStack is);
	public float getFoodWeight(ItemStack is);
	public float getFoodMaxWeight(ItemStack is);
	public float getFoodDecay(ItemStack is);
	/**
	 * @return Returns an ItemStack that will replace the current ItemStack when the food has reached maximum decay.
	 * Normally returns null.
	 */
	public ItemStack onDecayed(ItemStack is, World world, int i, int j, int k);
	/**
	 * @return Is this food edible as is.
	 */
	public boolean isEdible(ItemStack is);
	/**
	 * @return Is this item usable in meals
	 */
	public boolean isUsable(ItemStack is);

	public int getTasteSweet(ItemStack is);
	public int getTasteSour(ItemStack is);
	public int getTasteSalty(ItemStack is);
	public int getTasteBitter(ItemStack is);
	public int getTasteSavory(ItemStack is);
	public boolean renderDecay();
	public boolean renderWeight();
}
