package mortvana.projectfluxgear.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleHelper {

	@SideOnly(Side.CLIENT)
	public static boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer renderer, Block block, int metadata, int side) {
		byte size = 4;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					double posX = (double) x + ((double) i + 0.5D) / (double) size;
					double posY = (double) y + ((double) j + 0.5D) / (double) size;
					double posZ = (double) z + ((double) k + 0.5D) / (double) size;
					renderer.addEffect(new EntityDiggingFX(world, posX, posY, posZ, posX - (double) x - 0.5D, posY - (double) y - 0.5D, posZ - (double) z - 0.5D, block, metadata, side).applyColourMultiplier(x, y, z));
				}
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public static boolean addBlockHitEffects(World world, MovingObjectPosition target, EffectRenderer renderer, int metadata, int side) {
		double x = target.blockX;
		double y = target.blockY;
		double z = target.blockZ;
		int sideHit = target.sideHit;
		Block block = world.getBlock(target.blockX, target.blockY, target.blockZ);

		double offset = 0.1D;
		double posX = x + world.rand.nextDouble() * (block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() - (offset * 2.0D)) + offset + block.getBlockBoundsMinX();
		double posY = y + world.rand.nextDouble() * (block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() - (offset * 2.0D)) + offset + block.getBlockBoundsMinY();
		double posZ = z + world.rand.nextDouble() * (block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() - (offset * 2.0D)) + offset + block.getBlockBoundsMinZ();

		switch (sideHit) {
			case 0:
				posY = y + block.getBlockBoundsMinY() - offset;
			case 1:
				posY = y + block.getBlockBoundsMaxY() + offset;
			case 2:
				posZ = z + block.getBlockBoundsMinZ() - offset;
			case 3:
				posZ = z + block.getBlockBoundsMaxZ() + offset;
			case 4:
				posX = x + block.getBlockBoundsMinX() - offset;
			case 5:
				posX = x + block.getBlockBoundsMaxX() + offset;
		}
		renderer.addEffect(new EntityDiggingFX(world, posX, posY, posZ, 0.0D, 0.0D, 0.0D, block, metadata, side));
		return true;
	}

}
