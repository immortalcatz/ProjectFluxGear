package mortvana.legacy.errored.morttweaks.block;

import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockTweakedPortal extends BlockPortal {

	public BlockTweakedPortal() {
		super();
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		super.registerBlockIcons(par1IconRegister);
		Blocks.portal.registerBlockIcons(par1IconRegister);
	}

	@Override
	public boolean tryToCreatePortal(World world, int x, int y, int z) {
		return false;
	}
}