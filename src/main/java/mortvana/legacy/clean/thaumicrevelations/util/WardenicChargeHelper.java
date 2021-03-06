package mortvana.legacy.clean.thaumicrevelations.util;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import mortvana.projectfluxgear.library.FluxGearLibrary;
import mortvana.projectfluxgear.thaumic.augments.ThaumicAugmentBase;

public class WardenicChargeHelper {

	public static HashMap<String, ThaumicAugmentBase> upgrades = new HashMap<String, ThaumicAugmentBase>();

	public static void addUpgrade(ThaumicAugmentBase upgrade) {
		addUpgrade(upgrade.aspect.getName(), upgrade);
	}

	public static void addUpgrade(String key, ThaumicAugmentBase upgrade) {
		upgrades.put(key, upgrade);
	}

	public static ThaumicAugmentBase getUpgrade(ItemStack stack) {
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("upgrade")) {
			return upgrades.get(stack.stackTagCompound.getString("upgrade"));
		} else {
			return upgrades.get(FluxGearLibrary.WARDEN.getName());
		}
	}

	public static void setUpgradeOnStack(ItemStack stack, String key) {
		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.stackTagCompound.setString("upgrade", key);
	}
}
