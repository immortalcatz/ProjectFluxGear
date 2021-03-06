package mortvana.legacy.clean.weirdscience.block.tile;

import java.util.ArrayList;
import java.util.Random;

import mortvana.legacy.clean.weirdscience.util.block.IBlockMetaPower;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidStack;

import mortvana.legacy.clean.weirdscience.util.block.tile.TileEntitySolidFueled;
import mortvana.legacy.clean.weirdscience.util.ContentRegistry;
import mortvana.legacy.clean.weirdscience.util.fuel.ISolidFuelInfo;
import mortvana.legacy.clean.weirdscience.util.fuel.SolidFuelInfo;

/*
 * A tile entity is essentially a bit of extra behavior and information that is associated with a block in the world.
 * When looking at a block, the "Block" class is not instantiated on a per-block basis, instead, an instance of the 
 * Block class is used to do things to any block with that ID.
 * 
 * Without a Tile Entity, all of the information you can put into a block amounts to its ID and four bits of metadata.
 * With a Tile Entity, you can fit as much information as you want into a block, plus lots and lots of lag.
 * (so use them sparingly and don't build your walls out of engines)
 * (actually that would be cool and totally worth it, please build your walls out of engines)
 */
public class TileEntityGunpowderDynamo extends TileEntitySolidFueled implements ISidedInventory {

	// Static settings to apply to every instance of the Tile Entity.
	public static int rfPerTick;
	public static int quantityPerBurn; // Amount of fuel to attempt to consume at once.
	public static int ticksPerBurn; // Time between ticks where we burn fuel. To reduce lag.
	private static int staticEnergyCap;
	private static boolean setsFires = true;
	public static int radius = 4; 

	private static ArrayList<ISolidFuelInfo> staticFuelInfo = new ArrayList<ISolidFuelInfo>( 3);
	private static Random rand = new Random();
	public static ItemStack thermite = null;

	// Setting values to carry over between doConfig and DeferredInit
	public static int rfPerThermite = 0;
	public static boolean enableThermite = false;
	public static float thermiteFires = 0.0f;

	// Instance-specific values.
	private int ticksUntilBurn = 0;
	private ItemStack fuelStack = null;
	private boolean wasRunningLastBurn;

	// Make sure local values are synced up with static values:
	public TileEntityGunpowderDynamo() {
		super();
		fuelInfo.addAll(staticFuelInfo);
		energyCap = staticEnergyCap;
		transferRate = rfPerTick;
	}

	@Override
	public void receiveByproduct(ItemStack byproductStack) {
		// Do nothing. Not that kind of engine.
	}

	@Override
	public void receiveExhaust(FluidStack exhaustStack) {
		// Do nothing. Not that kind of engine.
	}

	/*
	 * Called automatically by our very own game registry and config systems.
	 * This method is called on a short-lived instance of the Tile Entity that
	 * will never be used in the world, for the purpose of doing configuration
	 * on static fields.
	 */
	public void doConfig(Configuration config, ContentRegistry cr) {
		// Get the energy cap which is universal for all TileEntityGunpowderEngines.
		// Then, we will set the specific energy cap of the instances from this value.
		staticEnergyCap = config.get("Blast Engine", "Capacity of internal energy buffer", 2000).getInt();
		setsFires = config.get("Blast Engine", "Sets fires", true).getBoolean(true);
		quantityPerBurn = 32; // Amount of dirt to attempt to consume at once.
		ticksPerBurn = 20; // Time between ticks where we burn dirt. To reduce lag.
		// Which is to say: Every X ticks, we will do the engine logic. Where X = ticksPerBurn
		rfPerTick = config.get("Blast Engine", "RF transfer rate", 20) .getInt();
		radius = rfPerTick = config.get("Blast Engine", "Burn radius", 4) .getInt(4);

		ticksUntilBurn = ticksPerBurn;

		boolean enableGunpowder = config.get("Blast Engine", "Enable gunpowder as fuel", true).getBoolean(true);
		if (enableGunpowder) {
			SolidFuelInfo gunpowderInfo = new SolidFuelInfo();
			gunpowderInfo.ourFuel = new ItemStack(Items.gunpowder);
			gunpowderInfo.energyPer = config.get("Blast Engine", "RF per Gunpowder", 80).getInt(80);
			/*
			 * Bit of a hack. SolidFuelInfo is very much specific to the Nitrate
			 * Engines I designed it for, and I haven't figured out how I should
			 * refactor it quite yet. For now, we'll use the "byproductMult"
			 * float variable to contain info about how much fire each different
			 * fuel will produce.
			 */
			gunpowderInfo.byproductMult = (float) config.get("Blast Engine", "Gunpowder fire factor", 4.0).getDouble(4.0d);
			staticFuelInfo.add(gunpowderInfo);
		}
		boolean enableBlazePowder = config.get("Blast Engine", "Enable blaze powder as fuel", true).getBoolean(true);
		if (enableBlazePowder) {
			SolidFuelInfo blazeInfo = new SolidFuelInfo();
			blazeInfo.ourFuel = new ItemStack(Items.blaze_powder);
			blazeInfo.energyPer = config.get("Blast Engine", "RF per Blaze Powder", 80).getInt(80);
			blazeInfo.byproductMult = (float) config.get("Blast Engine", "Blaze Powder fire factor", 8.0).getDouble(8.0d);
			staticFuelInfo.add(blazeInfo);
		}
		enableThermite = config.get("Blast Engine", "Enable Thermite as fuel", true).getBoolean(true);
		if (enableThermite) { 
			rfPerThermite = config.get("Blast Engine", "RF per Blaze Powder", 80).getInt(80);
			thermiteFires = (float) config.get("Blast Engine", "Blaze Powder fire factor", 8.0).getDouble(8.0d);
		}
	}

	// All mods have added all items. Do cool init stuff now.
	public void DeferredInit(ContentRegistry cr) {
		if (enableThermite) {
			SolidFuelInfo thermInfo = new SolidFuelInfo();
			thermInfo.energyPer = rfPerThermite;
			thermInfo.byproductMult = thermiteFires;
			if (thermite != null) {
				thermInfo.ourFuel = thermite;
				staticFuelInfo.add(thermInfo);
			}
		}
	}

	// A bunch of ugly boilerplate I'd abstract away in a second if Java had
	// multiple inheritance follows.

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slotID) {
		if (slotID == 0) {
			return fuelStack;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int slotID, int amount) {
		// Sanity check
		if (slotID != 0) {
			return null;
		}
		// Attempts to remove amount from slot # slotID. Returns a usable
		// itemstack taken out of the slot: Split or just yanked.
		if (fuelStack != null) {
			ItemStack itemstack;

			if (fuelStack.stackSize <= amount) {
				itemstack = fuelStack;
				fuelStack = null;
				return itemstack;
			} else {
				// Creates a new item stack split from ours, of size amount
				itemstack = fuelStack.splitStack(amount);
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotID) {
		// Sanity check
		if (slotID != 0) {
			return null;
		}
		// Used to get items when block is broken.
		// (I think)
		if (fuelStack != null) {
			ItemStack itemstack = fuelStack;
			fuelStack = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotID, ItemStack itemstack) {
		// Sanity check
		if (slotID != 0) {
			return;
		}

		fuelStack = itemstack;
		if (fuelStack != null
				&& fuelStack.stackSize > this.getInventoryStackLimit()) {
			fuelStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// Sanity checks! Is the player is sufficiently close, or too far away?
		return player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 16.0D;
	}

	public void openChest() {}

	public void closeChest() {}

	public boolean isItemFuel(Item item) {
		// Uses the canBurn functionality from our parent class, TileEntitySolidFueled
		return canBurn(new ItemStack(item)) != null;
	}

	@Override
	public boolean isItemValidForSlot(int slotID, ItemStack itemstack) {
		return isItemFuel(itemstack.getItem()) && (slotID == 0);
	}

	@Override
	public int[] getSlotsForFace(int side) {
		// Slot 0 is accessible from all sides.
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack itemstack, int direction) {
		return isItemValidForSlot(slotID, itemstack);
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack itemstack, int direction) {
		// We can extract from and insert to the same slot. This is fine because there is a single slot
		// for the machine. In a machine with input and result slots, consider only allowing extraction
		// from result slots and insertion to input slots, to allow reasonable automation.
		return slotID == 0;
	}

	// NBT stuff: Minecraft uses this data structure for serializing many things to file and to network.
	// Our parent class handles energy just fine, but here, we must implement saving/loading of fuel.
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		// Read the item stack as a sub-tag of nbt
		NBTTagCompound nbttagcompound1 = nbt.getCompoundTag("Fuel");
		if (nbttagcompound1 != null) {
			fuelStack = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
		// Simple behavior for performance reasons: If there's fuel in the slot, assume the engine was running,
		// otherwise, assume it was not.
		wasRunningLastBurn = fuelStack != null;

		// Read how far we are from doing another engine tick.
		ticksUntilBurn = nbt.getShort("BurnTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		// Write time until next engine burn tick.
		nbt.setShort("BurnTime", (short) ticksUntilBurn);
		// Write item stacks.
		NBTTagCompound fuelSubTag = new NBTTagCompound();
		if (fuelStack != null) {
			fuelStack.writeToNBT(fuelSubTag);
		}
		nbt.setTag("Fuel", fuelSubTag);
	}

	// The meat of our engine logic.
	// ENTITY UPDATE:
	@Override
	public void updateEntity() { // The meat of our block.
		super.updateEntity();
		boolean flagInvChanged = false;
		//worldObj.isRemote is a boolean set to true on gui. We need to run this code only on server-side,
		//because Minecraft is very naive about sidedness. It does none of it for you.
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
				// Make sure we have fuel, somewhere to put waste products, and energy storage capacity.
				if (fuelStack != null) {
					if (fuelStack.stackSize >= 1) {
						// Do the burny thing.
						int deltaItems = doBurn(fuelStack, quantityPerBurn);
						
						setFires(canBurn(fuelStack), deltaItems);
						//Delta, here, is the mathematical meaning of the word (change in thing)
						fuelStack.stackSize -= deltaItems;
						flagHasPower = (deltaItems != 0);
						flagInvChanged = flagHasPower;
						if (fuelStack.stackSize <= 0) {
							fuelStack = null;
						} //Update our block as to the current running state of the engine.
						if (deltaItems != 0) {
							TurnBlockOn();
						} else {
							TurnBlockOff();
						}
					} else {
						TurnBlockOff();
					}
					ticksUntilBurn = ticksPerBurn; // Reset the timer, but only if we did anything.
				} else {
					TurnBlockOff();
				}
			}
			// And now, attempt to charge surrounding blocks.
			if (flagHasPower) {
				powerAdjacent();
			}
		}
		//This is really important: Here, inventory info is synced. You get desync if you comment this out.
		if (flagInvChanged) {
			markDirty();
		}
	}
	
	//Yessssss.
	//Attempt to ignite surrounding blocks.
	private void setFires(ISolidFuelInfo fuel, int quantityBurned) {
		//Is this behavior enabled via config?
		if (setsFires && quantityBurned > 0) {
			//Chance per block, a percentage.
			//Magic number 2.0f here. Refactor how?
			int cpb = (int) ((2.0f * fuel.getByproductMult()) * (float) quantityBurned);
			ChunkCoordinates coordCheck = new ChunkCoordinates(0, 0, 0);
			ChunkCoordinates coordOurs = new ChunkCoordinates(xCoord, yCoord, zCoord);
			//Iterate through surrounding blocks within the bounding box, attempting to set them on fire.
			for (int x = -radius; x <= radius; x++) {
				for (int y = -radius; y <= radius; y++) {
					for (int z = -radius; z <= radius; z++) {
						coordCheck.set(x+xCoord, y+yCoord, z+zCoord);
						//Check to see if this block is ACTUALLY within the radius.
						//Check to see if the block under this one is not air
						// Check to see if THIS block is air
						// Roll the dice. Adjust to scale off with distance.
						int distanceSquared = (int) coordCheck.getDistanceSquaredToChunkCoordinates(coordOurs);
						if((distanceSquared <= (radius * radius)) && (worldObj.getBlock(x+xCoord, (y+yCoord)-1, z+zCoord) != Blocks.air) && (worldObj.getBlock(x+xCoord, y+yCoord, z+zCoord) == Blocks.air) && (rand.nextInt(100) <= (cpb - (distanceSquared * 2)))) {
							//Set it to fire.
							worldObj.setBlock(x + xCoord, y + yCoord, z + zCoord, Blocks.fire);
						}
					}
				}
			}
		}
	}

	//Do metadata things to our block, changing its textures based on if the engine is on or off.
	private void TurnBlockOff() {
		if (wasRunningLastBurn) {
			Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
			if (block instanceof IBlockMetaPower) {
				((IBlockMetaPower) block).receivePowerOff(worldObj, xCoord, yCoord, zCoord);
			}
		}
		wasRunningLastBurn = false;
	}

	//Do metadata things to our block, changing its textures based on if the engine is on or off.
	private void TurnBlockOn() {
		if (!wasRunningLastBurn) {
			Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
			if (block instanceof IBlockMetaPower) {
				((IBlockMetaPower) block).receivePowerOn(worldObj, xCoord, yCoord, zCoord);
			}
		}
		wasRunningLastBurn = true;
	}

	@Override
	public String getInventoryName() {
		return "engineGunpowder";
	}

	@Override
	public boolean isCustomInventoryName() {
		return false;
	}

}