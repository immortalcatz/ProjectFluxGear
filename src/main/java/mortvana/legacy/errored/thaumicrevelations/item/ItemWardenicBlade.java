package mortvana.legacy.errored.thaumicrevelations.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.melteddashboard.util.FluxGearDamageSources;
import mortvana.melteddashboard.util.helpers.StringHelper;

import mortvana.projectfluxgear.thaumic.common.ThaumicRevelations;

import mortvana.projectfluxgear.api.item.IWardenicEquipment;
import mortvana.legacy.clean.thaumicrevelations.util.WardenicChargeHelper;

public class ItemWardenicBlade extends Item implements IWardenicEquipment {

	public ItemWardenicBlade() {
		super();
		setUnlocalizedName("itemWardenicBlade");
		setCreativeTab(ThaumicRevelations.thaumicRevelationsTab);
		setMaxStackSize(1);

		setFull3D();
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
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add(EnumChatFormatting.AQUA + StringHelper.localize("tooltip.wardenic.charge") + ": " + (stack.getMaxDurability() - stack.getMetadata()) + "/" + stack.getMaxDurability());
		list.add(EnumChatFormatting.GOLD + StringHelper.localize("tooltip.wardenic.upgrade") + ": " + WardenicChargeHelper.getUpgrade(stack).getQuote());

		super.addInformation(stack, player, list, par4);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (stack.getMetadata() != stack.getMaxDurability()) {
			DamageSource damageSource = new FluxGearDamageSources("warden", player); //TODO: DamageSource generation?
			entity.attackEntityFrom(damageSource, 5);
			WardenicChargeHelper.getUpgrade(stack).onAttack(stack, player, entity);
			stack.setMetadata(stack.getMetadata() + 1);
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("trevelations:wardensword");
	}

}
