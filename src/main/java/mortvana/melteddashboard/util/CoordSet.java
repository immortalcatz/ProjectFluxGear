package mortvana.melteddashboard.util;

public class CoordSet {
	public int x, y, z;

	public CoordSet(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
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

}
