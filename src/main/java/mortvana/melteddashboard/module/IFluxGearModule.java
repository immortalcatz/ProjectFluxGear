package mortvana.melteddashboard.module;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public interface IFluxGearModule {
	public IIcon getModuleIcon();
	public IIcon getIcon(ItemStack itemstack);
}
