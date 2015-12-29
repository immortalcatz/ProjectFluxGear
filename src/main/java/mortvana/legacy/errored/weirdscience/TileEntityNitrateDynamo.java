package mortvana.legacy.errored.weirdscience;

import java.util.ArrayList;
import java.util.Random;

import mortvana.legacy.clean.weirdscience.util.block.IBlockMetaPower;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

import cofh.api.energy.IEnergyHandler;

import mortvana.legacy.clean.weirdscience.util.block.tile.TileEntitySolidFueled;
import mortvana.legacy.clean.weirdscience.util.ContentRegistry;
import mortvana.legacy.clean.weirdscience.util.fuel.ISolidFuelInfo;
import mortvana.legacy.clean.weirdscience.util.fuel.SolidFuelInfo;

public class TileEntityNitrateDynamo extends TileEntitySolidFueled implements IEnergyHandler, ISidedInventory, IFluidHandler, IFluidTank {
	private static final int[] accessibleSlots = new int[] { 0, 1 };

	// The ItemStacks that hold the items currently being used in the furnace
	private ItemStack[] engineItemStacks = new ItemStack[2];

	private final Random itemDropRand = new Random(); // Randomize item drop direction.

	public static int rfPerTick;
	public static int rfPerDirt;
	public static int quantityPerBurn; // Amount of dirt to attempt to consume at once.
	public static int ticksPerBurn; // Time between ticks where we burn dirt. To reduce lag.
	// private static int energy;
	private static int staticEnergyCap;
	public static float explosionStrength = 4.0F;
	protected static int wasteCapacity;
	protected static int ticksPerExhaust; // How long until we try to spawn smog?
	protected static int exhaustPerDirt;
	protected static int exhaustPerGrass;
	protected static int exhaustPerMycelium;

	public static Fluid waste = null;
	private static ArrayList<ISolidFuelInfo> staticFuelInfo = new ArrayList<ISolidFuelInfo>(3);

	// Fuel config.
	static boolean enableDirt = true;
	static SolidFuelInfo dirtFuel;
	static boolean enableGrassyDirt = true;
	static SolidFuelInfo grassyDirtFuel;
	static boolean enableMycelium = true;
	static SolidFuelInfo myceliumFuel;

	// Burning Natura nether dirts / sands / etc.
	static boolean enableNaturaCompat = true;
	static boolean enableTaintedSoil = true;
	static boolean enableHeatSand = true;
	static SolidFuelInfo taintedSoilFuel;
	static SolidFuelInfo heatSandFuel;
	protected static int exhaustPerHeatSand;
	protected static int exhaustPerTaintedSoil;

	private int ticksUntilBurn = ticksPerBurn;
	private boolean wasRunningLastBurn = false;

	public TileEntityNitrateDynamo() {
		super();
		setEnergyCapacity(staticEnergyCap);
		setEnergyTransferRate(rfPerTick);
		ticksUntilBurn = ticksPerBurn;
		energy = 0;
		// Tell our instance everything it needs to know.
		fuelInfo.addAll(staticFuelInfo);
	}

	// Adds a fuel.
	@Override
	public void addSolidFuelInfo(ISolidFuelInfo isf) {
		if (isf != null) {
			staticFuelInfo.add(isf);
		}
	}

	protected FluidStack fluidTank;

	public static void setWaste(Fluid b) {
		waste = b;
	}

	public void setWasteCapacity(int amt) {
		wasteCapacity = amt;
	}

	public void setTicksPerExhaust(int amt) {
		ticksPerExhaust = amt;
	}

	@Override
	public int getSizeInventory() {
		return this.engineItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotID) {
		return this.engineItemStacks[slotID];
	}

	@Override
	public ItemStack decrStackSize(int slotID, int amount) {
		// Attempts to remove amount from slot # slotID. Returns a usable
		// itemstack taken out of the slot: Split or just yanked.
		if (this.engineItemStacks[slotID] != null) {
			ItemStack itemstack;

			if (this.engineItemStacks[slotID].stackSize <= amount) {
				itemstack = this.engineItemStacks[slotID];
				this.engineItemStacks[slotID] = null;
				return itemstack;
			} else {
				itemstack = this.engineItemStacks[slotID].splitStack(amount);

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotID) {
		// Used to get items when block is broken.
		// (I think)
		if (engineItemStacks[slotID] != null) {
			ItemStack itemstack = engineItemStacks[slotID];
			engineItemStacks[slotID] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotID, ItemStack itemstack) {
		engineItemStacks[slotID] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "Nitrate Engine";
	}

	@Override
	public boolean isCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// Sanity checks! Is the player is sufficiently close, or too far away.
		return player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 16.0D;
	}

	public boolean isItemFuel(Item item) {
		return canBurn(new ItemStack(item)) != null;
	}

	public boolean isItemWaste(Item item) {
		for (ISolidFuelInfo fuel : staticFuelInfo) {
			if (fuel.getByproduct().getItem().getUnlocalizedName().contentEquals(item.getUnlocalizedName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int slotID, ItemStack itemstack) {
		// Only allow inserting into the input slot, and only allow fuel to be inserted.
		return isItemFuel(itemstack.getItem()) && (slotID == 0);
	}

	@Override
	public int[] getSlotsForFace(int side) {
		return accessibleSlots;
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack itemstack, int direction) {
		return isItemValidForSlot(slotID, itemstack);
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack itemstack, int direction) {
		// Only allow removing from the output slot. Causes hoppers and item pipes to act clever.
		return slotID == 1;
	}

	// NBT stuff
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		// Read the item stacks.
		NBTTagList nbttaglist = nbt.getTagList("Items", 0);
		engineItemStacks = new ItemStack[getSizeInventory()];
		NBTTagCompound nbttagcompound;
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			nbttagcompound = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound.getByte("Slot");

			if (b0 >= 0 && b0 < engineItemStacks.length) {
				engineItemStacks[b0] = ItemStack
						.loadItemStackFromNBT(nbttagcompound);
			}
		}
		// Simple behavior for performance reasons: If there's fuel in the slot, assume the engine was running,
		// otherwise, assume it was not.
		wasRunningLastBurn = engineItemStacks[0] != null;
		// Read how far we are from doing another engine tick.
		ticksUntilBurn = nbt.getShort("BurnTime");

		// Read the internal fluid tank for smog storage
		if (!nbt.hasKey("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);

			if (fluid != null) {
				fluidTank = fluid;
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		// Write time until next engine burn tick.
		nbt.setShort("BurnTime", (short) ticksUntilBurn);
		// Write energy
		// Write item stacks.
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < engineItemStacks.length; ++i) {
			if (this.engineItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				engineItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbt.setTag("Items", nbttaglist);
		// Write our internal fluid tank (which stores smog)
		if (fluidTank != null) {
			fluidTank.writeToNBT(nbt);
		} else {
			nbt.setString("Empty", "");
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		// This is not a fluid tank.
		return 0;
	}

	// FLUID CODE:
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource.isFluidEqual(fluidTank)) {
			return drain(from, resource.amount, doDrain);
		} else {
			return null;
		}
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		// This is still not a fluid tank.
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { getInfo() };
	}

	@Override
	public FluidStack getFluid() {
		return fluidTank;
	}

	@Override
	public int getFluidAmount() {
		return fluidTank.amount;
	}

	@Override
	public int getCapacity() {
		return wasteCapacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluidTank == null) {
			return null;
		}

		int drained = maxDrain;
		if (fluidTank.amount < drained) {
			drained = fluidTank.amount;
		}

		FluidStack stack = new FluidStack(fluidTank, drained);
		if (doDrain) {
			fluidTank.amount -= drained;
			if (fluidTank.amount <= 0) {
				fluidTank = null;
			}
			FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluidTank, worldObj, xCoord, yCoord, zCoord, this));
		}
		return stack;
	}

	// ENERGY CODE:

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
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return energy;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return staticEnergyCap;
	}

	// ENTITY UPDATE:
	@Override
	public void updateEntity(){ // The meat of our block.
		super.updateEntity();
		boolean flagInvChanged = false;
		if (!worldObj.isRemote) {
			// Burn logic:
			// Are we still waiting to burn fuel?
			boolean flagHasPower = energy > 0;
			int smogProduced = 0;
			if (ticksUntilBurn > 0) {
				ticksUntilBurn--;
			} else {
				// If we are not waiting, update the entity.
				int toBurn = 0;
				// Make sure we have fuel, somewhere to put waste products, and
				// energy storage capacity.

				if (engineItemStacks[0] != null) {
					if (engineItemStacks[0].stackSize >= 1) {
						// Do the burny thing.
						int deltaItems = doBurn(engineItemStacks[0], quantityPerBurn);
						engineItemStacks[0].stackSize -= deltaItems;
						flagHasPower = (deltaItems != 0);
						flagInvChanged = flagHasPower;
						if (engineItemStacks[0].stackSize <= 0) {
							engineItemStacks[0] = null;
						}
						if (deltaItems != 0) {
							turnBlockOn();
						} else {
							turnBlockOff();
						}
					} else {
						turnBlockOff();
					}
					ticksUntilBurn = ticksPerBurn; // Reset the timer, but only if we did anything.
				} else {
					turnBlockOff();
				}
			}
			// And now, attempt to charge surrounding blocks.
			if (flagHasPower) {
				powerAdjacent();
			}
			// Attempt to dump tank into surrounding Forge fluid handlers.
			if (fluidTank != null) {
				ForgeDirection dir;
				IFluidHandler adjFluidHandler;
				for (int i = 0; i < 6; ++i) {
					dir = ForgeDirection.VALID_DIRECTIONS[i];
					adjFluidHandler = adjFluidTiles[i];
					if (adjFluidHandler != null) {
						FluidStack toDrain = new FluidStack(fluidTank.getFluid(), fluidTank.amount);
						drain(adjFluidHandler.fill(dir.getOpposite(), toDrain, true), true);
						if (fluidTank == null) {
							break;
						}
					}
				}
			}
		}
		if (flagInvChanged) {
			markDirty();
		}
	}

	private void turnBlockOff() {
		if (wasRunningLastBurn) {
			Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
			if (block instanceof IBlockMetaPower) {
				((IBlockMetaPower) block).receivePowerOff(worldObj, xCoord, yCoord, zCoord);
			}
		}
		wasRunningLastBurn = false;
	}

	private void turnBlockOn() {
		if (wasRunningLastBurn) {
			Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
			if (block instanceof IBlockMetaPower) {
				((IBlockMetaPower) block).receivePowerOn(worldObj, xCoord, yCoord, zCoord);
			}
		}
		wasRunningLastBurn = true;
	}

	@Override
	public void receiveByproduct(ItemStack byproductStack) {
		if (byproductStack == null) {
			// If we're just receiving a null value, do nothing with it.
		} else if (engineItemStacks[1] == null) { // Make sure we have a sand stack to add to.
			engineItemStacks[1] = byproductStack;
		} else {
			// Only do the simple behavior if we've got two item stacks of the same type and added items will fit.
			if ((engineItemStacks[1].isItemEqual(byproductStack)) && ((engineItemStacks[1].stackSize + byproductStack.stackSize) > this.getInventoryStackLimit())) {
			} else {
				// ...Otherwise, remove everything from that slot and put our byproduct stack in there.
				ejectWaste();
				engineItemStacks[1] = byproductStack.copy();
			}
		}
	}

	public void ejectWaste() {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) { // prevent ghost item stupidity
			float xr = itemDropRand.nextFloat() * 0.8F + 0.1F;
			float yr = itemDropRand.nextFloat() * 0.8F + 0.1F;
			float zr = itemDropRand.nextFloat() * 0.8F + 0.1F;
			EntityItem entityItem = new EntityItem(worldObj, (double) ((float) xCoord + xr), (double) ((float) yCoord + yr), (double) ((float) zCoord + zr), engineItemStacks[1].copy());
			worldObj.spawnEntityInWorld(entityItem);
			engineItemStacks[1] = null;
		}
	}

	// else {
	// engineItemStacks[1].stackSize += toBurn;
	// }

	//TODO: Where did our Smog go?
	/*
	 * Emit deadly smog. This particular implementation of recieveExhaust() is
	 * only smart enough to operate with fluids whose block extends
	 * BlockGasBase.
	 */
	@Override
	public void receiveExhaust(FluidStack exhaustStack) {
		if ((exhaustStack == null) || (exhaustStack.amount == 0)) {
			// If we're just receiving a null value, do nothing with it.
			return;
		} else {
			int smogProduced = exhaustStack.amount;
			if (fluidTank == null) {
				fluidTank = exhaustStack.copy();
				fluidTank.amount = 0;
			}
			// Stash some smog in the fluid tank.
			if (fluidTank.amount < wasteCapacity) {
				int amountToStore = Math.min(
						(wasteCapacity - fluidTank.amount), smogProduced);
				fluidTank.amount += amountToStore;
				smogProduced -= amountToStore;
			}
			// Is there still smog left over? If so, we could not fit it into
			// the tank. Exhaust into the adjacent air.
			if (smogProduced > 0) {
				// TODO: Redo gas code.
				smogProduced = 0;

				Fluid fluid = exhaustStack.getFluid();
				if (fluid instanceof BlockGasBase) {
					BlockGasBase ourWaste = (BlockGasBase) fluid;
					for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
						if (dir != ForgeDirection.UP) {
							smogProduced = ourWaste.pushIntoBlock(worldObj, xCoord + dir.offsetX, yCoord  + dir.offsetY, zCoord + dir.offsetZ, smogProduced);
							if(smogProduced <= 0) {
								break;
							}
						}
					}
				}
			}
			// If this isn't a fluid we can handle sanely...
			else {
				// Don't punish the player for it.
				smogProduced = 0;
			}
		}
		// Is there STILL smog left over? If so, explode violently.
		if (smogProduced > 0) {
			//prevent general stupidity
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
				worldObj.createExplosion(null, xCoord, yCoord, zCoord, explosionStrength, true);
			}
		}
	}

	public void doConfig(Configuration config, ContentRegistry cr) {
		staticEnergyCap = config.get("Nitrate Engine", "Capacity of internal buffer", 2000).getInt();
		quantityPerBurn = 32; // Amount of dirt to attempt to consume at once.
		ticksPerBurn = 20; // Time between ticks where we burn dirt. To reduce lag.
		rfPerTick = config.get("Nitrate Engine", "RF transfer rate", 20).getInt();
		enableNaturaCompat = config.get("Nitrate Engine", "Enable Natura fuels", true).getBoolean(true);
		enableDirt = config.get("Nitrate Engine", "Enable dirt as fuel", true).getBoolean(true);
		enableGrassyDirt = config.get("Nitrate Engine", "Enable grassy dirt as fuel", true).getBoolean(true);
		enableMycelium = config.get("Nitrate Engine", "Enable mycelium as fuel", true).getBoolean(true);

		if (enableDirt) {
			dirtFuel = new SolidFuelInfo();
			dirtFuel.energyPer = config.get("Nitrate Engine", "RF generated per dirt", 2).getInt();
			exhaustPerDirt = config.get("Nitrate Engine", "Smog produced per dirt burned in milibuckets", 16).getInt();
		}
		if (enableGrassyDirt) {
			grassyDirtFuel = new SolidFuelInfo();
			grassyDirtFuel.energyPer = config.get("Nitrate Engine", "RF generated per grassy dirt", 3).getInt();
			exhaustPerGrass = config.get("Nitrate Engine", "Smog produced per grassy dirt burned in milibuckets", 16).getInt();
		}
		if (enableMycelium) {
			myceliumFuel = new SolidFuelInfo();
			myceliumFuel.energyPer = config.get("Nitrate Engine", "RF generated per mycelium", 5).getInt();
			exhaustPerMycelium = config.get("Nitrate Engine", "Smog produced per mycelium burned in milibuckets", 16).getInt();
		}
		if (enableNaturaCompat) {
			enableTaintedSoil = config.get("Nitrate Engine", "Enable tainted soil as fuel", true).getBoolean(true);
			enableHeatSand = config.get("Nitrate Engine", "Enable heat sand as fuel", true).getBoolean(true);
			if (enableTaintedSoil) {
				taintedSoilFuel = new SolidFuelInfo();
				taintedSoilFuel.energyPer = config.get("Nitrate Engine", "RF generated per Tainted Soil", 4).getInt();
				exhaustPerTaintedSoil = config.get("Nitrate Engine", "Smog produced per Tainted Soil burned in milibuckets", 32).getInt();
			}
			if (enableHeatSand) {
				heatSandFuel = new SolidFuelInfo();
				heatSandFuel.energyPer = config.get("Nitrate Engine", "RF generated per heat sand", 16).getInt();
				exhaustPerHeatSand = config.get("Nitrate Engine", "waste produced per heat sand burned in milibuckets", 2).getInt();
			}
		}
		ticksUntilBurn = ticksPerBurn;
		wasteCapacity = config.get("Nitrate Engine", "Internal smog tank capacity", (exhaustPerDirt * quantityPerBurn) * 2).getInt();
	}

	public void DeferredInit(ContentRegistry cr) {
		if (dirtFuel != null) {
			dirtFuel.ourFuel = new ItemStack(Blocks.dirt);
			dirtFuel.byproduct = new ItemStack(Blocks.sand);
			dirtFuel.exhaust = new FluidStack(waste, exhaustPerDirt);
			staticFuelInfo.add(dirtFuel);
		}
		if (grassyDirtFuel != null) {
			grassyDirtFuel.ourFuel = new ItemStack(Blocks.grass);
			grassyDirtFuel.byproduct = new ItemStack(Blocks.sand);
			grassyDirtFuel.exhaust = new FluidStack(waste, exhaustPerDirt);
			staticFuelInfo.add(grassyDirtFuel);
		}
		if (myceliumFuel != null) {
			myceliumFuel.ourFuel = new ItemStack(Blocks.mycelium);
			myceliumFuel.byproduct = new ItemStack(Blocks.sand);
			myceliumFuel.exhaust = new FluidStack(waste, exhaustPerDirt / 2);
			staticFuelInfo.add(myceliumFuel);
		}

		if (enableNaturaCompat) {
			Block tsBlock = null;
			Block hsBlock = null;
			if (GameRegistry.findBlock("Natura", "soil.tainted") != null) {
				tsBlock = GameRegistry.findBlock("Natura", "soil.tainted");
			}
			if (GameRegistry.findBlock("Natura", "heatsand") != null) {
				hsBlock = GameRegistry.findBlock("Natura", "heatsand");
			}
			if (taintedSoilFuel != null && tsBlock != null) {
				System.out.println("yoyoyo");
				taintedSoilFuel.ourFuel = new ItemStack(tsBlock);
				taintedSoilFuel.byproduct = new ItemStack(Blocks.soul_sand);
				taintedSoilFuel.exhaust = new FluidStack(waste, exhaustPerTaintedSoil);
				staticFuelInfo.add(taintedSoilFuel);
			}
			if (heatSandFuel != null && hsBlock != null) {
				heatSandFuel.ourFuel = new ItemStack(Item.getItemFromBlock(hsBlock));
				heatSandFuel.byproduct = new ItemStack(Item.getItemFromBlock(Blocks.soul_sand));
				heatSandFuel.exhaust = new FluidStack(waste, exhaustPerHeatSand);
				heatSandFuel.byproductMult = 0.5f;
				staticFuelInfo.add(heatSandFuel);
			}
		}
	}

	@Override
	public void closeChest() {}

	@Override
	public void openChest() {}

}
