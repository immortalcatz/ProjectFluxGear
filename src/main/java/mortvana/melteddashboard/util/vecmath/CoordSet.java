package mortvana.melteddashboard.util.vecmath;

import net.minecraft.nbt.NBTTagCompound;

public class CoordSet {
	protected int x, z;
	protected short y;

	public CoordSet(int x, short y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Deprecated
	public CoordSet(int x, int y, int z) {
		this(x, (short) y, z);
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(short y) {
		this.y = y;
	}

	@Deprecated
	public void setY(int y) {
		setY((short) y);
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public short getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public CoordSet offsetCoordsBySide(int side) {
		switch (side) {
			case 0:
				y--;
				break;
			case 1:
				y++;
				break;
			case 2:
				z--;
				break;
			case 3:
				z++;
				break;
			case 4:
				x--;
				break;
			case 5:
				x++;
		}
		return this;
	}

	public static CoordSet readFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("x", 3) && nbt.hasKey("y", 2) && nbt.hasKey("z", 3)) {
			return new CoordSet(nbt.getInteger("x"), nbt.getShort("y"), nbt.getInteger("z"));
		} else {
			return null;
		}
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("x", x);
		nbt.setShort("y", y);
		nbt.setInteger("z", z);

		return nbt;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CoordSet) {
			CoordSet coords = (CoordSet) obj;
			return coords.getX() == x && coords.getY() == y && coords.getZ() == z;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.x + (y << 8) + (z << 16);
	}

	@Override
	public String toString() {
		return "[CoordSet: (" + x + ", " + y + ", " + z + ")]";
	}
}
