package oldcode.projectfluxgear;

import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class MiscHelper {
	// Fluids
	public static Fluid registerColoredFluid(String name, int color) {
		String fluidName = "molten." + name;
		Fluid fluid = new ColoredFluid(fluidName, color).setDensity(100).setViscosity(50).setTemperature(300).setLuminosity(15);

		if (!FluidRegistry.registerFluid(fluid)) {
			fluid = FluidRegistry.getFluid(fluidName);
		}

		FluidBlock block = new FluidBlock(fluid, Material.lava);
		block.setBlockName(fluidName);
		GameRegistry.registerBlock(block, ColoredFluidItemBlock.class, fluidName);

		return fluid;
	}
}
