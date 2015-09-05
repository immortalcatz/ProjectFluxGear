package mortvana.melteddashboard.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import mortvana.melteddashboard.util.repack.mortvana.science.math.MathHelper;

public class MiscHelper {

	public static boolean isBlockEqual(Block block, Block... blocks) {
		boolean isEqual = false;
		for (Block compare : blocks) {
			if (block == compare) {
				isEqual = true;
			}
		}
		return isEqual;
	}

	public static MovingObjectPosition raytraceFromPlayer(World world, EntityLivingBase entity, boolean par3) {
		Vec3 vec3 = Vec3.createVectorHelper(entity.posX, entity.posY + 1.62D - (double) entity.yOffset, entity.posZ);
		float pitchCos = (float) -MathHelper.cos(-entity.rotationPitch * 0.017453292F);
		Vec3 vector = vec3.addVector(MathHelper.sin(-entity.rotationYaw * 0.017453292F - Math.PI * pitchCos) * 5.0D, MathHelper.sin(-entity.rotationPitch * 0.017453292F) * 5.0D, MathHelper.cos(-entity.rotationYaw * 0.017453292F - Math.PI) * pitchCos * 5.0D);
		return world.rayTraceBlocks(vec3, vector, par3);
	}

}
