package mortvana.melteddashboard.util.random.weighted;

import net.minecraftforge.fluids.FluidStack;

public class WeightedFluidStack extends WeightedEntry {

	public final FluidStack FLUIDSTACK;

	public WeightedFluidStack(FluidStack fluidstack) {
		super(100);
		this.FLUIDSTACK = fluidstack;
	}

	public WeightedFluidStack(FluidStack fluidstack, int weight) {
		super(weight);
		this.FLUIDSTACK = fluidstack;
	}

	public FluidStack getFluidStack() {
		return FLUIDSTACK;
	}
}