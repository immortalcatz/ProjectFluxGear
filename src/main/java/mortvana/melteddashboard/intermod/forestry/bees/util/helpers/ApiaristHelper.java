package mortvana.melteddashboard.intermod.forestry.bees.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import forestry.api.apiculture.IArmorApiarist;
import mortvana.melteddashboard.util.enums.EnumArmorType;

public class ApiaristHelper {
	public static int getNumberPiecesWorn(EntityPlayer player) {
		int count = 0;

		for (EnumArmorType type : EnumArmorType.values()) {
			if (wearingPiece(player, type)) {
				count++;
			}
		}

		return count;
	}

	public static boolean wearingPiece(EntityPlayer player, EnumArmorType type) {
		ItemStack armorItem = player.inventory.armorItemInSlot(type.ordinal());
		return armorItem != null && armorItem.getItem() instanceof IArmorApiarist;
	}

}
