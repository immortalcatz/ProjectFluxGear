package mortvana.legacy.errored.core.util.helpers;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.input.Keyboard;

public final class StringHelper {

	public static boolean displayStackCount = false;

	public static int getSplitStringHeight(FontRenderer var0, String var1, int var2) {
		List var3 = var0.listFormattedStringToWidth(var1, var2);
		return var3.size() * var0.FONT_HEIGHT;
	}

	public static String camelCase(String var0) {
		return var0.substring(0, 1).toLowerCase() + var0.substring(1);
	}

	public static String titleCase(String var0) {
		return var0.substring(0, 1).toUpperCase() + var0.substring(1);
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

	public static String getRarity(int var0) {
		switch(var0) {
			case 2:
				return "\u00a7e";
			case 3:
				return "\u00a7b";
			default:
				return "\u00a77";
		}
	}

	public static String shiftForDetails() {
		return "\u00a77" + localize("info.fluxgear.tooltip.hold") + " " + "\u00a7e" + "\u00a7o" + localize("info.cofh" +
				".shift") + " " + "\u00a7r" + "\u00a77" + localize("info.cofh.forDetails") + "\u00a7r";
	}

	public static String tutorialTabAugment() {
		return localize("info.cofh.tutorial.tabAugment");
	}

	public static String tutorialTabConfiguration() {
		return localize("info.cofh.tutorial.tabConfiguration.0");
	}

	public static String tutorialTabOperation() {
		return localize("info.cofh.tutorial.tabConfiguration.1");
	}

	public static String tutorialTabRedstone() {
		return localize("info.cofh.tutorial.tabRedstone");
	}

	public static String tutorialTabSecurity() {
		return localize("info.cofh.tutorial.tabSecurity");
	}

	public static String tutorialTabFluxRequired() {
		return localize("info.cofh.tutorial.fluxRequired");
	}

	/** Useful Tooltips **/
	public static String shiftForMoreInfo = ORANGE + ITALIC + localize("info.pfg.tooltip.press") + " " + localize("info.pfg.key.shift") + " " + localize("info.pfg.tooltip.info");

	/** Enable Localizationizing **/
	public static String localize(String key) {
		return StatCollector.translateToLocal(key);
	}
}
