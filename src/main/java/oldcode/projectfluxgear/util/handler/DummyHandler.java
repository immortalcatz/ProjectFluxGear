package oldcode.projectfluxgear.util.handler;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

/**
 * MmmMmmMmmMmm
 */
public class DummyHandler {
	public IIcon getSubTileIconForName(String name) {
		return Blocks.potatoes.getIcon(0, 0);
	}

	public void registerBasicSignatureIcons(String name, IIconRegister register) {
	}
}
