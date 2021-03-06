package mortvana.legacy.clean.crystalclimate.block.tile;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class CrystalLogic extends TileEntity {
	boolean active;
	boolean growing;
	int crystalValue;
	int type = 0;
	Random random = new Random();

	@Override
	public void updateEntity() {
		if (worldObj.isRemote && random.nextInt(5) == 0) {//for (int i = 0; i < 8; i++)
			worldObj.spawnParticle("reddust", xCoord + random.nextFloat() * 3 - 1, yCoord + random.nextFloat(), zCoord + random.nextFloat() * 3 - 1, 0, 0, 0);
		}
	}

	public int getCrystalValue() {
		return crystalValue;
	}

	public void setCrystalValue(int value) {
		this.crystalValue = value;
	}

	public int getCrystalType() {
		return type;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean flag) {
		System.out.println("Activating");
		active = flag;
	}

	public boolean growing() {
		return growing;
	}

	public void setGrowth(boolean flag) {
		growing = flag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		readCustomNBT(tags);
	}

	@Override
	public void writeToNBT(NBTTagCompound tags) {
		super.writeToNBT(tags);
		writeCustomNBT(tags);
	}

	public void readCustomNBT(NBTTagCompound tags) {
		active = tags.getBoolean("Active");
		growing = tags.getBoolean("Growing");
		crystalValue = tags.getInteger("Value");
	}

	public void writeCustomNBT(NBTTagCompound tags) {
		tags.setBoolean("Active", active);
		tags.setBoolean("Growing", growing);
		tags.setInteger("Value", crystalValue);
	}

	/* Packets */
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeCustomNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
		readCustomNBT(packet.getNbtCompound());
		worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
	}
}
