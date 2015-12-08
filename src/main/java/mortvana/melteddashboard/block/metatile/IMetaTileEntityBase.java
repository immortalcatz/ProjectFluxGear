package mortvana.melteddashboard.block.metatile;

import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

import mortvana.melteddashboard.api.block.tile.ITemperatureHandler;

public interface IMetaTileEntityBase extends ITemperatureHandler, IFluidTank, IFluidHandler {

}
