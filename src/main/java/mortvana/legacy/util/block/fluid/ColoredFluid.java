package mortvana.legacy.util.block.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class ColoredFluid extends Fluid {
	public final int color;

	public ColoredFluid(String fluidName, int color) {
		super(fluidName);
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public String getLocalizedName(FluidStack fluid) {
		return this.getBlock().getLocalizedName();
	}
}
