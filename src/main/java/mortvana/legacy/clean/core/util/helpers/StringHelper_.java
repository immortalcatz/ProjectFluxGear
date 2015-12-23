package mortvana.legacy.clean.core.util.helpers;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import static mortvana.melteddashboard.util.helpers.StringHelper.*;

public final class StringHelper_ {

	public static boolean displayStackCount = false;

	public static int getSplitStringHeight(FontRenderer var0, String var1, int var2) {
		List var3 = var0.listFormattedStringToWidth(var1, var2);
		return var3.size() * var0.FONT_HEIGHT;
	}

	public static String getFluidName(FluidStack var0) {
		Fluid var1 = var0.getFluid();
		String var2 = "\u00a7r";
		if(var1.getRarity() == EnumRarity.uncommon) {
			var2 = var2 + "\u00a7e";
		} else if(var1.getRarity() == EnumRarity.rare) {
			var2 = var2 + "\u00a7b";
		} else if(var1.getRarity() == EnumRarity.epic) {
			var2 = var2 + "\u00a7d";
		}

		var2 = var2 + var1.getLocalizedName(var0) + "\u00a7r";
		return var2;
	}

	public static String getFluidName(FluidStack var0, String var1) {
		return var0 == null?var1:getFluidName(var0);
	}

	public static String getItemName(ItemStack var0) {
		String var1 = "\u00a7r";
		if(var0.getRarity() == EnumRarity.uncommon) {
			var1 = var1 + "\u00a7e";
		} else if(var0.getRarity() == EnumRarity.rare) {
			var1 = var1 + "\u00a7b";
		} else if(var0.getRarity() == EnumRarity.epic) {
			var1 = var1 + "\u00a7d";
		}

		var1 = var1 + var0.getDisplayName() + "\u00a7r";
		return var1;
	}

	public static String getScaledNumber(long var0) {
		return getScaledNumber(var0, 2);
	}

	public static String getScaledNumber(long var0, int var2) {
		String var3 = "";
		int var4 = 10 ^ var2;
		if(var0 > (long)(100000000 * var4)) {
			var3 = var3 + var0 / 1000000000L + "G";
		} else if(var0 > (long)(100000 * var4)) {
			var3 = var3 + var0 / 1000000L + "M";
		} else if(var0 > (long)(100 * var4)) {
			var3 = var3 + var0 / 1000L + "k";
		} else {
			var3 = var3 + var0;
		}

		return var3;
	}

	public static String getActivationText(String var0) {
		return "\u00a7b" + localize(var0) + "\u00a7r";
	}

	public static String getDeactivationText(String var0) {
		return "\u00a7e" + localize(var0) + "\u00a7r";
	}

	public static String getInfoText(String var0) {
		return "\u00a7a" + localize(var0) + "\u00a7r";
	}

	public static String getFlavorText(String var0) {
		return "\u00a7f\u00a7o" + localize(var0) + "\u00a7r";
	}

	public static String shiftForDetails() {
		return "\u00a77" + localize("info.fluxgear.tooltip.hold") + " " + "\u00a7e" + "\u00a7o" + localize("info.cofh" +
				".shift") + " " + "\u00a7r" + "\u00a77" + localize("info.cofh.forDetails") + "\u00a7r";
	}

	/** Useful Tooltips **/
	public static String shiftForMoreInfo = ORANGE + ITALIC + localize("info.pfg.tooltip.press") + " " +
			localize
			("info.pfg.key.shift") + " " + localize("info.pfg.tooltip.info");
}
