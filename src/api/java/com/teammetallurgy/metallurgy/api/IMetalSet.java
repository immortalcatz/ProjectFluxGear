package com.teammetallurgy.metallurgy.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public interface IMetalSet {

	public IMetalInfo getMetal(String metal);

	public String[] getMetalNames();

	public Block getDefaultOre();

	public Block getDefaultBlock();

	public Block getDefaultBricks();

	public ItemStack getOre(String metal);

	public ItemStack getBlock(String metal);

	public ItemStack getBrick(String metal);

	public ItemStack getDrop(String metal);

	public ItemStack getDust(String metal);

	public ItemStack getIngot(String metal);

	public ItemStack getNugget(String metal);

	public ItemArmor.ArmorMaterial getArmorMaterial(String metal);

	public ItemStack getHelmet(String metal);

	public ItemStack getChestplate(String metal);

	public ItemStack getLeggings(String metal);

	public ItemStack getBoots(String metal);

	public Item.ToolMaterial getToolMaterial(String metal);

	public ItemStack getSword(String metal);

	public ItemStack getPickaxe(String metal);

	public ItemStack getAxe(String metal);

	public ItemStack getShovel(String metal);

	public ItemStack getHoe(String metal);
}