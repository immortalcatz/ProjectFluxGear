package oldcode.projectfluxgear.thaumic.util;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import mortvana.projectfluxgear.thaumic.common.ThaumicContent;
import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;
import oldcode.projectfluxgear.util.item.armor.EnumArmorType;
import oldcode.projectfluxgear.util.item.armor.ItemArmorFluxGear;
import oldcode.projectfluxgear.thaumic.util.wardenic.WardenicChargeHelper;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;

public class ItemWardenArmor extends ItemArmorFluxGear implements ISpecialArmor, IVisDiscountGear {

	public ItemWardenArmor(EnumArmorType type, String name, String sheet, String icon) {
		super(ThaumicContent.materialWarden, 3, type, name, sheet, icon);
		setCreativeTab(ThaumicRevelations.thaumicTab);
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
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal("tooltip.wardenic.charge") + ": " + (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage()) + "/" + par1ItemStack.getMaxDamage());
		par3List.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(par1ItemStack).getQuote());
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		WardenicChargeHelper.getUpgrade(itemStack).onTick(world, player, itemStack);
		super.onArmorTick(world, player, itemStack);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if (armor.getItemDamage() != armor.getMaxDamage()) {
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
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

	}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return 5;
	}

}
