package mortvana.melteddashboard.client.particle;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;


import mortvana.melteddashboard.util.vecmath.Vec3D;

import static mortvana.melteddashboard.util.helpers.science.MathHelper.*;

public abstract class ParticleBase {

	public static final float BASE_GRAVITY = 0.04F;
	protected static final ResourceLocation MC_PARTICLES = new ResourceLocation("textures/particle/particles.png");
	protected static final ResourceLocation MC_BLOCKS = TextureMap.locationBlocksTexture;
	protected static final ResourceLocation MC_ITEMS = TextureMap.locationItemsTexture;
	public static double interpPosX, interpPosY, interpPosZ;
	// Rotations
	public static float rX;     // The X component of the particle's yaw rotation.
	public static float rZ;     // The Z component of the entity's yaw rotation.
	public static float rYZ;    // The Y component (scaled along the Z axis) of the entity's pitch rotation.
	public static float rXY;    // The Y component (scaled along the X axis) of the particle's pitch rotation
	public static float rXZ;    // combined X and Z components of the particle's pitch rotation.

	public final ResourceLocation location;
	protected final Vec3D prevPos;
	protected final Vec3D pos;
	protected double motX, motY, motZ;
	protected float r = 1, g = 1, b = 1, a = 1;
	protected float size = 0.2F;
	protected int life, maxLife;
	protected boolean onGround;
	protected float gravity = BASE_GRAVITY;

	protected ParticleBase(double x, double y, double z, double motX, double motY, double motZ, float size, int life, ResourceLocation location) {
		pos = new Vec3D(x, y, z);
		prevPos = new Vec3D(x, y, z);
		this.motX = motX;
		this.motY = motY;
		this.motZ = motZ;
		this.size = size * 0.1F;
		this.life = 0;
		this.maxLife = life;
		this.location = location;
	}

	/*public final boolean advance() {
		copyVecValues(prevPos, pos);
		return checkLife() && handleMovement();
	}

	public boolean handleMovement() {
		motY -= gravity;
		if (!moveParticle(motX, motY, motZ)) {
			return false;
		}
		applyFriction();
		return true;
	}

	public void applyFriction() {
		motX *= 0.9800000190734863D;
		motY *= 0.9800000190734863D;
		motZ *= 0.9800000190734863D;
		if ()
	}*/
}
