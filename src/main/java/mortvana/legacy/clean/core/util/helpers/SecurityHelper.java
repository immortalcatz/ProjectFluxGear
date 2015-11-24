package mortvana.legacy.clean.core.util.helpers;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import cofh.api.tileentity.ISecurable;

import mortvana.legacy.errored.core.util.helpers.StringHelper;

public class SecurityHelper {
	private SecurityHelper() {
	}

	public static NBTTagCompound setItemStackTagSecure(NBTTagCompound var0, ISecurable var1) {
		if(var1 == null) {
			return null;
		} else {
			if(var0 == null) {
				var0 = new NBTTagCompound();
			}

			var0.setBoolean("Secure", true);
			var0.setByte("Access", (byte)var1.getAccess().ordinal());
			var0.setString("Owner", var1.getOwnerName());
			return var0;
		}
	}

	public static void addOwnerInformation(ItemStack var0, List<String> var1) {
		if(isSecure(var0)) {
			if(!var0.stackTagCompound.hasKey("Owner")) {
				var1.add(StringHelper.localize("info.fluxgear.owner") + ": " + StringHelper.localize("info.fluxgear.none"));
			} else {
				var1.add(StringHelper.localize("info.fluxgear.owner") + ": " + var0.stackTagCompound.getString("Owner"));
			}
		}

	}

	public static void addAccessInformation(ItemStack var0, List<String> var1) {
		if(isSecure(var0)) {
			String var2 = "";
			switch(var0.stackTagCompound.getByte("Access")) {
				case 0:
					var2 = StringHelper.localize("info.fluxgear.accessPublic");
					break;
				case 1:
					var2 = StringHelper.localize("info.fluxgear.accessRestricted");
					break;
				case 2:
					var2 = StringHelper.localize("info.fluxgear.accessPrivate");
			}

			var1.add(StringHelper.localize("info.fluxgear.access") + ": " + var2);
		}

	}

	public static boolean isSecure(ItemStack var0) {
		return var0.stackTagCompound == null ? false : var0.stackTagCompound.hasKey("Secure");
	}

	public static ItemStack setSecure(ItemStack var0) {
		if(isSecure(var0)) {
			return var0;
		} else {
			if(var0.stackTagCompound == null) {
				var0.setTagCompound(new NBTTagCompound());
			}

			var0.stackTagCompound.setBoolean("Secure", true);
			var0.stackTagCompound.setByte("Access", (byte)0);
			return var0;
		}
	}

	public static boolean setAccess(ItemStack var0, ISecurable.AccessMode var1) {
		if(!isSecure(var0)) {
			return false;
		} else {
			var0.stackTagCompound.setByte("Access", (byte)var1.ordinal());
			return true;
		}
	}

	public static ISecurable.AccessMode getAccess(ItemStack var0) {
		return var0.stackTagCompound == null? ISecurable.AccessMode.PUBLIC: ISecurable.AccessMode.values()[var0.stackTagCompound.getByte("Access")];
	}

	public static boolean setOwnerName(ItemStack var0, String var1) {
		if(!isSecure(var0)) {
			return false;
		} else {
			var0.stackTagCompound.setString("Owner", var1);
			return true;
		}
	}

	public static String getOwnerName(ItemStack var0) {
		return var0.stackTagCompound == null?"[None]":var0.stackTagCompound.getString("Owner");
	}
}
