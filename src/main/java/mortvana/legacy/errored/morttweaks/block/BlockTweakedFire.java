package mortvana.legacy.errored.morttweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockTweakedFire extends BlockFire {

	public BlockTweakedFire() {
		disableStats();
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		super.registerIcons(iconRegister);
		Blocks.fire.registerIcons(iconRegister);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (world.provider.dimensionId > 0 || world.getBlock(x, y - 1, z) != Blocks.obsidian || !Blocks.portal.tryToCreatePortal(world, x, y, z)) {
			if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && !this.canNeighborBurn(world, x, y, z)) {
				world.setBlockToAir(x, y, z);
			} else {
				world.scheduleBlockUpdate(x, y, z, this, tickRate(world) + world.rand.nextInt(10));
			}
		}
	}

	private boolean canNeighborBurn(World world, int x, int y, int z) {
		return canBlockCatchFire(world, x + 1, y, z, WEST) || canBlockCatchFire(world, x - 1, y, z, EAST) || canBlockCatchFire(world, x, y - 1, z, UP) || canBlockCatchFire(world, x, y + 1, z, DOWN) || canBlockCatchFire(world, x, y, z - 1, SOUTH) || canBlockCatchFire(world, x, y, z + 1, NORTH);
	}
}
