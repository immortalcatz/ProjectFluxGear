package mortvana.legacy.dependent.seconddegree.thaumicrevelations.item;

import java.util.List;

import mortvana.projectfluxgear.library.ContentLibrary;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import mortvana.melteddashboard.util.enums.EnumArmorType;
import mortvana.melteddashboard.util.helpers.StringHelper;

import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;

import mortvana.legacy.clean.core.util.item.ItemArmorFluxGear;
import mortvana.projectfluxgear.api.item.armor.IWardenicArmor;
import mortvana.legacy.clean.thaumicrevelations.util.WardenicChargeHelper;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;

public class ItemWardenicArmor extends ItemArmorFluxGear implements ISpecialArmor, IVisDiscountGear, IWardenicArmor {

	public ItemWardenicArmor(EnumArmorType type, String name, String sheet, String icon) {
		super(FluxGearContent.materialWarden, 3, type, name, sheet, icon);
		setCreativeTab(ContentLibrary.thaumicRevelationsTab);
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return 50;
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(EnumChatFormatting.AQUA + StringHelper.localize("tooltip.wardenic.charge") + ": " + (stack.getMaxDurability() - stack.getMetadata()) + "/" + stack.getMaxDurability());
		list.add(EnumChatFormatting.GOLD + StringHelper.localize("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(stack).getQuote());
		super.addInformation(stack, player, list, par4);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		WardenicChargeHelper.getUpgrade(itemStack).onTick(world, player, itemStack);
		super.onArmorTick(world, player, itemStack);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if (armor.getMetadata() != armor.getMaxDurability()) {
			return new ArmorProperties(0, getArmorMaterial().getDamageReductionAmount(slot) / 25D, 20);
		} else {
			return new ArmorProperties(0, 0, 0);
		}
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return getArmorMaterial().getDamageReductionAmount(slot);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return 5;
	}

}