package mortvana.legacy.clean.weirdscience.util.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyHandler;
import mortvana.melteddashboard.block.tile.FluxGearTileEntity;

@Deprecated
//TODO: I saw a junkie eat a tuba
public class TileEntityGenerator extends FluxGearTileEntity implements IEnergyHandler {

	protected int energy;
	protected int energyCap;
	protected int transferRate = 80;
	
	//Check to see if we can push power into adjacent tile entities.
	public void powerAdjacent() {
		IEnergyHandler ehandler;
		for (int i = 0; i < 6; i++) {
			ehandler = adjRFTiles[i];
			if (ehandler != null) {
				ehandler.receiveEnergy(ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[i]], Math.min(energy, transferRate), false);
				energy -= Math.min(energy, transferRate);
				if (energy == 0) {
					//Don't bother trying to output energy if we're out of it.
					break;
				}
			}
		}
	}
	
	//Argument is capacity.
	public TileEntityGenerator() {
		super();
		energy = 0;
	}

	@Override
	public String getName() {
		return "Legacy";
	}

	@Override
	public int getType() {
		return -1;
	}

	public void setEnergyTransferRate(int tr) {
		transferRate = tr;
	}

	public int getEnergyTransferRate() {
		return transferRate;
	}

	public void setEnergyCapacity(int cap) {
		energyCap = cap;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		// This isn't a battery.
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		if (!simulate) {
			if (energy < maxExtract) {
				maxExtract = energy;
				energy = 0;
				return maxExtract;
			} else {
				energy -= maxExtract;
				return maxExtract;
			}
		} else {
			if (energy < maxExtract) {
				return energy;
			} else {
				return maxExtract;
			}
		}
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return energy;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return energyCap;
	}

	@Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        //Write energy
        nbt.setInteger("Energy", energy);
    }

	@Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        //Write energy
        energy = nbt.getInteger("Energy");
    }

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

}
