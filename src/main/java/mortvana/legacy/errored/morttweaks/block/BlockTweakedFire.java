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

	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		Blocks.fire.registerIcons(par1IconRegister);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (world.provider.dimensionId > 0 || world.getBlock(x, y - 1, z) != Blocks.obsidian || !((BlockPortal) Block.blocksList[90]).tryToCreatePortal(world, x, y, z)) {
			if (!world.doesBlockHaveSolidTopSurface(x, y - 1, z) && !this.canNeighborBurn(world, x, y, z)) {
				world.setBlockToAir(x, y, z);
			} else {
				world.scheduleBlockUpdate(x, y, z, this.blockID, this.tickRate(world) + world.rand.nextInt(10));
			}
		}
	}

	private boolean canNeighborBurn(World par1World, int par2, int par3, int par4) {
		return canBlockCatchFire(par1World, par2 + 1, par3, par4, WEST) || canBlockCatchFire(par1World, par2 - 1, par3, par4, EAST) || canBlockCatchFire(par1World, par2, par3 - 1, par4, UP)
				|| canBlockCatchFire(par1World, par2, par3 + 1, par4, DOWN) || canBlockCatchFire(par1World, par2, par3, par4 - 1, SOUTH) || canBlockCatchFire(par1World, par2, par3, par4 + 1, NORTH);
	}
}
