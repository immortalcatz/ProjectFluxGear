package mortvana.melteddashboard.util.helpers;

import net.minecraft.item.ItemStack;

@Deprecated
public class SecurityHelper {

	private SecurityHelper() {}

	public static boolean isSecure(ItemStack stack) {
		return NBTHelper.stackHasBooleanKey(stack, "Secure");
	}
}
