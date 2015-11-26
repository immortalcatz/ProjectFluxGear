package mortvana.legacy.clean.morttweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockTweakedPortal extends BlockPortal {

	public BlockTweakedPortal(float hardness, float lightValue, SoundType soundType, String unlocalizedName, String textureName) {
		super();
		setHardness(hardness);
		setLightLevel(lightValue);
		setStepSound(soundType);
		setUnlocalizedName(unlocalizedName);
		setTextureName(textureName);
	}

	public BlockTweakedPortal() {
		this(-1.0F, 0.75F, Block.soundTypeGlass, "portal", "portal");
	}

	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		Blocks.portal.registerIcons(par1IconRegister);
	}

	@Override
	public boolean tryToCreatePortal(World world, int x, int y, int z) {
		return false;
	}
}