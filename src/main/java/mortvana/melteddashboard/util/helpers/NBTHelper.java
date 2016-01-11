package mortvana.melteddashboard.util.helpers;

import net.minecraft.item.ItemStack;

@Deprecated
public class NBTHelper {

	public static boolean stackHasBooleanKey(ItemStack stack, String key) {
		return stack.hasTagCompound() && stack.getTagCompound().hasKey(key) && stack.getTagCompound().getBoolean(key);
	}

}
