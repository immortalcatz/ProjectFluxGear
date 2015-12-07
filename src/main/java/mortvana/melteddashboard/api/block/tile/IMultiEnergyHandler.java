package mortvana.melteddashboard.api.block.tile;

import cofh.api.energy.IEnergyHandler;
import mortvana.melteddashboard.api.energy.rotational.IRotationalEnergyAcceptor;

public interface IMultiEnergyHandler extends IEnergyHandler, IRotationalEnergyAcceptor, IEUHandler {}
