package mortvana.tweaks.tweak.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

public class TweakPortal extends BlockPortal {
	public TweakPortal(int id) {
		super(id);
	}

	public void registerIcons(IconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		Block.portal.registerIcons(par1IconRegister);
	}

	@Override
	public boolean tryToCreatePortal(World world, int x, int y, int z) {
		return false;
	}
}