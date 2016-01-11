package mortvana.melteddashboard.util.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import mortvana.melteddashboard.util.helpers.science.MathHelper;

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

	public static void dropInventoryContents(World world, int x, int y, int z, IInventory inventory) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			dropStackWithVelocity(world, x, y, z, inventory.getStackInSlot(i));
		}
	}

	public static boolean dropStackWithVelocity(World world, int x, int y, int z, ItemStack stack) {
		if (stack != null) {
			float xOffset = world.rand.nextFloat() * 0.8F + 0.1F;
			float yOffset = world.rand.nextFloat() * 0.8F + 0.1F;
			float zOffset = world.rand.nextFloat() * 0.8F + 0.1F;

			EntityItem entity = new EntityItem(world, x + xOffset, y + yOffset, z + zOffset, stack.copy());

			entity.motionX = world.rand.nextGaussian() * 0.05D;
			entity.motionY = world.rand.nextGaussian() * 0.05D;
			entity.motionZ = world.rand.nextGaussian() * 0.05D;

			world.spawnEntityInWorld(entity);
		}
		return stack != null;
	}

	public static boolean dropStackWithoutVelocity(World world, int x, int y, int z, ItemStack stack) {
		if (stack != null) {
			EntityItem entity = new EntityItem(world, x + 0.5F, y, z + 0.5F, stack.copy());

			entity.motionX = 0;
			entity.motionY = -0.05D;
			entity.motionZ = 0;

			world.spawnEntityInWorld(entity);
		}
		return stack != null;
	}
}
