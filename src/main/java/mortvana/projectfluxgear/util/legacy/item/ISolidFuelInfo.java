package mortvana.projectfluxgear.util.legacy.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
@Deprecated
//TODO: Merge with SolidFuelInfo
public interface ISolidFuelInfo {
	ItemStack getFuel();
	int getEnergyPer();
	ItemStack getByproduct();
	FluidStack getExhaust();
	float getByproductMult();
}
