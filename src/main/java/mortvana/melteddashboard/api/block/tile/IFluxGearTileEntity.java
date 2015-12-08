package mortvana.melteddashboard.api.block.tile;

import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

import mortvana.melteddashboard.block.metatile.IMetaTileEntityBase;

public interface IFluxGearTileEntity extends IMultiEnergyHandler, IFacadableAdv, ITileEntity, IMetaTileEntityBase, IInformative, IUpgradable, IWorkingTile, IUpdatable, IRotatable {}
