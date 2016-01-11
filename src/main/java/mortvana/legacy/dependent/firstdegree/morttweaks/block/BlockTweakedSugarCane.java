package mortvana.legacy.dependent.firstdegree.morttweaks.block;

import java.util.Random;

import mortvana.legacy.errored.morttweaks.common.MortTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTweakedSugarCane extends BlockReed {
	public BlockTweakedSugarCane(float hardness, SoundType soundType, String unlocalizedName, String textureName) {
		super();
		disableStats();
	}

	public BlockTweakedSugarCane() {
		this(0.0F, Block.soundTypeGrass, "reeds", "reeds");
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.isAirBlock(x, y + 1, z)) {
			int l;

			for (l = 1; world.getBlock(x, y - l, z) == this; l++) {
			}

			if (l < MortTweaks.sugarCaneHeight) {
				int i1 = world.getBlockMetadata(x, y, z);

				if (i1 == 15) {
					world.setBlock(x, y + 1, z, this);
					world.setBlockMetadataWithNotify(x, y, z, 0, 4);
				} else {
					world.setBlockMetadataWithNotify(x, y, z, i1 + 1, 4);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		super.registerIcons(iconRegister);
		Blocks.reeds.registerIcons(iconRegister);
	}
}
