package mortvana.projectfluxgear.util.block.metadata;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.util.network.packet.MessageMetadata;

public class TileEntityMetadata extends TileEntity {

	protected int meta;

	public TileEntityMetadata() {}

	public boolean receiveClientEvent(int par1, int par2) {
		if (par1 == 42) {
			meta = par2;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		return true;
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		meta = nbt.getInteger("meta");
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("meta", meta);
	}

	public boolean canUpdate() {
		return false;
	}

	public int getTileMetadata() {
		return meta;
	}

	public void setTileMetadata(int meta, boolean notify) {
		if (this.meta != meta) {
			this.meta = meta;
			if (notify) {
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}
	}

	public Packet getDescriptionPacket() {
		return ProjectFluxGear.instance.getNetworkWrapper().getPacketFrom(new MessageMetadata(xCoord, yCoord, zCoord, meta).getMessage());
	}

	public static TileEntityMetadata getTile(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!(tile instanceof TileEntityMetadata)) {
			return null;
		}
		return (TileEntityMetadata) tile;
	}

	public static ItemStack getItemStack(Block block, int meta) {
		ItemStack itemstack = new ItemStack(block, 1, 0);
		setItemMetadata(itemstack, meta);
		return itemstack;
	}

	public static void setItemMetadata(ItemStack itemstack, int meta) {
		itemstack.setItemDamage(meta < 16387 ? meta : 16387);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("meta", meta);
		itemstack.setTagCompound(tag);
	}

	public static int getItemMetadata(ItemStack itemstack) {
		if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("meta")) {
			return itemstack.getTagCompound().getInteger("meta");
		}
		return itemstack.getItemDamage();
	}

	public static int getTileMetadata(IBlockAccess world, int x, int y, int z) {
		TileEntityMetadata tile = getTile(world, x, y, z);
		return tile == null ? 0 : tile.getTileMetadata();
	}

	boolean droppedBlock = false;

	public boolean hasDroppedBlock() {
		return droppedBlock;
	}

	public void dropBlock() {
		droppedBlock = true;
	}
}
