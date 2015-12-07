package mortvana.projectfluxgear.thaumic.item.armor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.projectfluxgear.api.item.armor.IMagitechHelmet;

import thaumcraft.api.aspects.Aspect;

public class ItemTinkersGoggles extends ItemMagitechArmor implements IMagitechHelmet {

	@Override
	public boolean showIngameHUD(World world, ItemStack stack, EntityPlayer player) {
		return false;
	}

	@Override
	public void addAncientWill(ItemStack stack, int will) {

	}

	@Override
	public boolean hasAncientWill(ItemStack stack, int will) {
		return false;
	}

	@Override
	public boolean canSeePollination(EntityPlayer player, ItemStack stack, boolean b) {
		return false;
	}

	@Override
	public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
		return false;
	}

	@Override
	public float getPixieChance(ItemStack stack) {
		return 0;
	}

	@Override
	public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
		return false;
	}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return 0;
	}
}
