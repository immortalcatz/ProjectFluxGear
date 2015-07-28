package mortvana.melteddashboard.intermod.baubles.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mortvana.melteddashboard.intermod.baubles.util.BaubleData;
import mortvana.melteddashboard.item.FluxGearItem;

public class FluxGearItemBauble extends FluxGearItem implements IBauble {

	public Map<Integer, BaubleData> baubleData = new HashMap<Integer, BaubleData>();

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return metaBaubleExists(itemstack) ? baubleData.get(itemstack.getItemDamage()).getType() : null;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if (metaBaubleExists(itemstack)){
			baubleData.get(itemstack.getItemDamage()).onWornTick(itemstack, player);
		}
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		if (metaBaubleExists(itemstack)){
			baubleData.get(itemstack.getItemDamage()).onEquipped(itemstack, player);
		}
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		if (metaBaubleExists(itemstack)){
			baubleData.get(itemstack.getItemDamage()).onUnequipped(itemstack, player);
		}
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		return !metaBaubleExists(itemstack) || baubleData.get(itemstack.getItemDamage()).canEquip();
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		return !metaBaubleExists(itemstack) || baubleData.get(itemstack.getItemDamage()).canUnequip();
	}

	public boolean metaBaubleExists(ItemStack bauble) {
		return metaBaubleExists(bauble.getItemDamage());
	}

	public boolean metaBaubleExists(int metadata) {
		return baubleData.containsKey(metadata);
	}

	public void addMetaBauble(int metadata, BaubleData data) {
		baubleData.put(metadata, data);
	}
}
