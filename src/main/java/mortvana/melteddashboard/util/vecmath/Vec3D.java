package mortvana.melteddashboard.util.vecmath;

public class Vec3D {

	public double x, y, z;

	public Vec3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		checkZeros();
	}

	public Vec3D setComponents(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		checkZeros();
		return this;
	}

















	public void checkZeros() {
		if (x == -0.0D) {
			x = 0.0D;
		}

	}

}
