package mortvana.projectfluxgear.weirdScienceLegacy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

import mortvana.projectfluxgear.util.legacy.ContentRegistry;
import mortvana.projectfluxgear.util.legacy.block.BlockMetaTank;
import mortvana.projectfluxgear.util.legacy.block.tile.TileEntityGenerator;

public class TileEntityBloodDynamo extends TileEntityGenerator implements IFluidHandler {
	
	//Static values
	protected static int tankCap;
	protected static float rfPerMB;
	protected static int mbPerBurn;
	protected static int ticksPerBurn;
	protected static int rfPerTickStatic;
	protected static int energyCapStatic;

	private int fuelFluidID;
	
	//Instance-specific values.
	protected int ticksUntilBurn;
	protected FluidStack tank = null;

	public TileEntityBloodDynamo() {
		super();
		this.setEnergyCapacity(energyCapStatic);
		this.setEnergyTransferRate(rfPerTickStatic);
		ticksUntilBurn = ticksPerBurn;

		energy = 0;

        this.energyCap = energyCapStatic;
	}
	

	//NBT stuff
	@Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        //Read how far we are from doing another engine tick.
        this.ticksUntilBurn = nbt.getShort("BurnTime");

        //Read the internal fluid tank for smog storage
        if (!nbt.hasKey("Empty")) {
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);

            if (fluid != null) {
            	tank = fluid;
            }
        }
        this.energyCap = energyCapStatic;
    }

	@Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        //Write time until next engine burn tick.
        nbt.setShort("BurnTime", (short)this.ticksUntilBurn);
        //Write our internal fluid tank (which stores smog)
        if (tank != null) {
        	tank.writeToNBT(nbt);
        } else {
        	nbt.setString("Empty", "");
        }
    }

	public void doConfig(Configuration config, ContentRegistry cr) {
		rfPerMB = (float)config.get("Hemoionic Dynamo", "RF generated per MB of fuel", 0.1f).getDouble(0.1d);
		rfPerTickStatic = config.get("Hemoionic Dynamo", "RF transfer rate", 20).getInt();
		energyCapStatic = config.get("Hemoionic Dynamo", "Capacity of internal energy buffer", 4000).getInt();
		tankCap = config.get("Hemoionic Dynamo", "Internal fuel tank capacity", 4000).getInt();
		mbPerBurn = 400; //Amount of fuel to attempt to consume at once.
	    ticksPerBurn = 20; //Time between ticks where we burn fuel. To reduce lag.
		ticksUntilBurn = ticksPerBurn;
		energyCap = energyCapStatic;
	}

	public void DeferredInit(ContentRegistry cr) {
		fuelFluidID = FluidRegistry.getFluidID("blood");
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource.getFluid().getName() == "blood") {
			int ourValue = 0;
			if(tank != null) {
				ourValue = tank.amount;
			}
			int resultValue = ourValue + resource.amount;
			if(resultValue > tankCap) {
				resultValue = tankCap;
			}
			if(doFill) {
				if (tank != null) {
					tank.amount = resultValue;
				}
				else {
					tank = resource.copy();
				}
				this.updateTank();
			}
			return resultValue - ourValue;
		} else {
			return 0;
		}
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if(resource.fluidID == fuelFluidID) {
			return drain(from, resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		FluidStack returnVal = null;
		if(tank != null) {
			returnVal = new FluidStack(fuelFluidID, Math.min(tank.amount, maxDrain));
			if(doDrain) {
				if(maxDrain >= tank.amount) { 
					tank = null;
					updateTank();
				} else {
					tank.amount -= maxDrain;
					updateTank();
				}
			}
		}
		return returnVal;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return (fluid.getID() == fuelFluidID);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return (fluid.getID() == fuelFluidID);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{new FluidTankInfo(tank, tankCap)};
	}

	//ENTITY UPDATE:
	@Override
	public void updateEntity() { //The meat of our block.
		super.updateEntity();
		//Clientside is for suckers.
		if(!worldObj.isRemote) {
			//Burn logic: Are we still waiting to burn fuel?
			boolean flagHasPower = energy > 0;
	        if (this.ticksUntilBurn > 0) {
	        	--this.ticksUntilBurn;
	        } else {
	        	//Do we have fuel?
				if ((this.tank != null) && (this.tank.amount >= 1) && (energy < energyCapStatic)) {
					//Bugs are hard and Tile Entities are eccentric.
		            int toBurn = Math.min(mbPerBurn, this.tank.amount); //Either eat mbPerBurn fuel or the entire stack.
		            drain(ForgeDirection.UP, toBurn, true);
		            	
		            energy += (int)(((float)toBurn)*rfPerMB);
		            if(energy > energyCapStatic) {
		            	energy = energyCapStatic;
		            }
		        	flagHasPower = true;
					//updateTank()
						
			        ticksUntilBurn = ticksPerBurn; //Reset the timer, but only if we did anything.
				}
	        }
	        if(flagHasPower) {
	    		//And now, attempt to charge surrounding blocks.
	            this.powerAdjacent();
	        }
		}
    }

	public boolean isEnabled() {
		return true;
	}
	
	public void updateTank() { 
		if((!worldObj.isRemote) && (worldObj.getBlock(xCoord, yCoord, zCoord) instanceof BlockMetaTank)) {
			BlockMetaTank bmt = (BlockMetaTank)(worldObj.getBlock(xCoord, yCoord, zCoord));
			if(tank == null) {
				bmt.setMetaByFillPercent(worldObj, xCoord, yCoord, zCoord, 0);
			} else {
				bmt.setMetaByFillPercent(worldObj, xCoord, yCoord, zCoord, (this.tank.amount*100)/this.tankCap);
			}
		}
	}
}
