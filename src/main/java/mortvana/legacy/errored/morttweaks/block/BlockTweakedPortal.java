package mortvana.legacy.errored.morttweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

public class BlockTweakedPortal extends BlockPortal {
	public BlockTweakedPortal(int id) {
		super();
	}

	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		Block.portal.registerIcons(par1IconRegister);
	}

	@Override
	public boolean tryToCreatePortal(World world, int x, int y, int z) {
		return false;
	}
}