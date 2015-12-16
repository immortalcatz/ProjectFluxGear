package mortvana.legacy.clean.morttech.block.tile;

import cpw.mods.fml.common.registry.GameRegistry;
import mantle.blocks.BlockUtils;
import mantle.blocks.abstracts.InventoryLogic;
import mantle.blocks.iface.IActiveLogic;
import mantle.blocks.iface.IFacingLogic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.legacy.clean.morttech.inventory.ContainerWoodmill;
import mortvana.legacy.clean.morttech.recipes.WoodmillRecipes;

public class WoodmillLogic extends InventoryLogic implements IActiveLogic, IFacingLogic {

    boolean active;
    public int power;
    public int powerGauge;
    public int progress;
    public int powerScale = 200;
    byte direction;

    public WoodmillLogic() {
        super(3);
        active = false;
    }

    @Override
    public String getDefaultName() {
        return "machine.woodmill";
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public void setActive(boolean flag) {
        active = flag;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    //Power Gauge

    public int gaugeProgressScaled(int scale) {
        return (progress * scale) / powerScale;
    }

    public int gaugePowerScaled(int scale) {
        if (powerGauge == 0) {
            powerGauge = power;
            if (powerGauge == 0) {
                powerGauge = powerScale;
            }
        }
        return (power * scale) / powerGauge;
    }

    //Item Processing
    @Override
    public void updateEntity() {
        boolean working = isWorking();
        boolean updateInventory = false;
        if (power <= 0 && canCut()) {
            power = powerGauge = (int) (getItemPowerTime(inventory[1]));
            if (power > 0) {
                if (inventory[1].getItem().hasContainerItem()) { // Fuel Slot
                    inventory[1] = new ItemStack(inventory[1].getItem().getContainerItem());
                } else {
                    inventory[1].stackSize--;
                } if (inventory[1].stackSize <= 0) {
                    inventory[1] = null;
                }
                updateInventory = true;
            }
            if (isWorking() && canCut()) {
                progress++;
                if (progress >= powerScale) {
                progress = 0;
                cutItems();
                updateInventory = true;
                }
            } else {
                progress = 0;
            }
            if (power > 0) {
                power--;
            }
            if (working != isWorking()) {
                setActive(isWorking());
                updateInventory = true;
            }
            if (updateInventory) {
                markDirty();
            }
        }
    }

    public void cutItems() {
        if (canCut()) {
            ItemStack itemstack = WoodmillRecipes.cutting().getCuttingResult(this.inventory[0]);

            if (this.inventory[2] == null) {
                this.inventory[2] = itemstack.copy();
            } else if (this.inventory[2].isItemEqual(itemstack)) {
                inventory[2].stackSize += itemstack.stackSize;
            }

            --inventory[0].stackSize;

            if (inventory[0].stackSize <= 0) {
                inventory[0] = null;
            }
        }

    }

    public boolean canCut() {
        if (inventory[0] == null) {
            return false; // Nothing here!
        } else {
            ItemStack itemstack = WoodmillRecipes.cutting().getCuttingResult(this.inventory[0]);
            if (itemstack == null) {
                return false;
            }
            if (this.inventory[2] == null) {
                return true;
            }
            if (!this.inventory[2].isItemEqual(itemstack)) {
                return false;
            }
            int result = inventory[2].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    public boolean isWorking()
    {
        return power > 0;
    }

    //TODO: Get the result >.>
    public ItemStack getResultFor(ItemStack stack) {
        ItemStack result = WoodmillRecipes.cutting().getCuttingResult(stack);
        if (result != null) {
            return result.copy();
        }
        return null;
    }

    public static int getItemPowerTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        } else {
            Item item = stack.getItem();

            if (stack.getItem() instanceof ItemBlock && BlockUtils.getBlockFromItem(item) != null) {
                Block block = BlockUtils.getBlockFromItem(item);

                if (block == Blocks.wooden_slab) {
                    return 150;
                }

                if (block == Blocks.log) {
                    return 1200;
                }

                if (block.getMaterial() == Material.wood) {
                    return 300;
                }

                if (block == Blocks.coal_block) {
                    return 16000;
                }
            }

            if ((item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) || (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) || (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD"))) {
                return 200;
            }
            if ((item == Items.stick) || (item == new ItemStack(Blocks.sapling).getItem())) {
                return 100;
            }
            if (item == Items.coal) {
                return 1600;
            }
            if (item == Items.lava_bucket) {
                return 20000;
            }
            if (item == Items.blaze_rod) {
                return 2400;
            }
            return GameRegistry.getFuelValue(stack);
        }
    }

    /* NBT */
    @Override
    public void readFromNBT(NBTTagCompound tags) {
        super.readFromNBT(tags);
        active = tags.getBoolean("Active");
        power = tags.getInteger("Power");
        powerGauge = tags.getInteger("PowerGauge");
        readNetworkNBT(tags);
    }

    @Override
    public void writeToNBT(NBTTagCompound tags) {
        super.writeToNBT(tags);
        tags.setBoolean("Active", active);
        tags.setInteger("Power", power);
        tags.setInteger("PowerGauge", powerGauge);
        writeNetworkNBT(tags);
    }

    public void readNetworkNBT(NBTTagCompound tags) {
        direction = tags.getByte("Direction");
    }

    public void writeNetworkNBT(NBTTagCompound tags) {
        tags.setByte("Direction", direction);
    }

    /* Packets */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeNetworkNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
	    //power = packet.getNbtCompound().getInteger("power");
	    readNetworkNBT(packet.getNbtCompound());
        worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
    }

    @Override
    public Container getGuiContainer(InventoryPlayer inventoryplayer, World world, int x, int y, int z) {
        return new ContainerWoodmill(inventoryplayer, this);
    }

    @Override
    public byte getRenderDirection() {
        return direction;
    }

    @Override
    public ForgeDirection getForgeDirection() {
        return ForgeDirection.VALID_DIRECTIONS[direction];
    }

    @Override
    @Deprecated
    public void setDirection(int side) {
        // Nope!
    }

    @Override
    @Deprecated
    public void setDirection(float yaw, float pitch, EntityLivingBase player) {
        int facing = MathHelper.floor_double((double) (yaw / 360) + 0.5D) & 3;
        switch (facing) {
            case 0:
                direction = 2;
                break;
            case 1:
                direction = 5;
                break;
            case 2:
                direction = 3;
                break;
            case 3:
                direction = 4;
                break;
        }
    }

    @Override
    public String getInventoryName() {
        return getDefaultName();
    }

    @Override
    @Deprecated
    public boolean hasCustomInventoryName() {
        return false;
    }

	@Override
	public boolean isCustomInventoryName() {
		return false;
	}

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {}
}