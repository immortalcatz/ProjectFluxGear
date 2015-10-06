package mortvana.legacy.clean.projectfluxgear.network;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public abstract class FluxGearMessage {

	public abstract void encodeTo(ByteBuf target);
	public abstract void decodeFrom(ByteBuf source);

	public abstract void handle();

	public int readVarInt(ByteBuf source) {
		int integer = 0;
		int size = 0;
		byte dashbyte; //Oh the dashboard melted, but we still have the radio~

		do {
			dashbyte = source.readByte();
			integer |= (dashbyte & 127) << size++ * 7;
			if (size > 5) {
				throw new RuntimeException("VarInt too large");
			}
		} while ((dashbyte & 128) == 128);

		return integer;
	}

	public void writeVarInt(ByteBuf target, int integer) {
		while ((integer & -128) != 0) {
			target.writeByte(integer & 127 | 128);
			integer >>>= 7;
		}
		target.writeByte(integer);
	}

	public String readString(ByteBuf source) {
		try {
			int length = source.readInt();
			int dataSize = readVarInt(source);
			if (dataSize > length * 4) {
				throw new IOException("The received encoded string buffer length is longer than the maximum allowed length (" + dataSize + " > " + length + ")");
			} else if (dataSize < 0) {
				throw new IOException("The received encoded string buffer length is negative (less than zero)... java.lang.ArithmeticException: \\ by RWTema");
			} else {
				String string = new String(source.readBytes(dataSize).array(), Charsets.UTF_8);
				if (string.length() > length) {
					throw new IOException("The received string length is longer than the maximum allowed length (" + dataSize + " > " + length + ")");
				} else {
					return string;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void writeString(ByteBuf target, String string) {
		try {
			byte[] data = string.getBytes(Charsets.UTF_8);
			if (data.length > 32767) {
				throw new IOException("String was to large to handle (it was " + string.length() + " bytes encoded, the maximum allowed is 32767 bytes)");
			} else {
				target.writeInt(string.length());
				writeVarInt(target, data.length);
				target.writeBytes(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NBTTagCompound readNBTTagCompound(ByteBuf source) {
		try {
			short dataSize = source.readShort();
			if (dataSize < 0) {
				return null;
			} else {
				byte[] data = new byte[dataSize];
				source.readBytes(data);
				return CompressedStreamTools.readCompressed(new ByteArrayInputStream(data));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void writeNBTTagCompound(ByteBuf target, NBTTagCompound nbt) {
		try {
			if (nbt == null) {
				target.writeShort(-1);
			} else {
				byte[] data = CompressedStreamTools.compress(nbt);
				target.writeShort((short) data.length);
				target.writeBytes(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ItemStack readItemStack(ByteBuf source) {
		ItemStack stack = null;
		short id = source.readShort();
		if (id >= 0) {
			stack = new ItemStack(Item.getItemById(id), source.readByte(), source.readShort());
			stack.stackTagCompound = readNBTTagCompound(source);
		}
		return stack;
	}

	public void writeItemStack(ByteBuf target, ItemStack stack) {
		if (stack == null) {
			target.writeShort(-1);
		} else {
			target.writeShort(Item.getIdFromItem(stack.getItem()));
			target.writeByte(stack.stackSize);
			target.writeShort(stack.getItemDamage());
			NBTTagCompound nbt = null;
			if (stack.getItem().isDamageable() || stack.getItem().getShareTag()) {
				nbt = stack.stackTagCompound;
			}
			writeNBTTagCompound(target, nbt);
		}
	}

	public FluidStack readFluidStack(ByteBuf source) {
		FluidStack fluid = null;
		int id = source.readShort();
		if (id > 0) {
			fluid = new FluidStack(id, source.readInt());
			fluid.tag = readNBTTagCompound(source);
		}
		return fluid;
	}

	public void writeFluidStack(ByteBuf target, FluidStack fluid) {
		if (fluid == null) {
			target.writeShort(-1);
		} else {
			target.writeShort(fluid.getFluidID());
			target.writeInt(fluid.amount);
			writeNBTTagCompound(target, fluid.tag);
		}
	}
}
