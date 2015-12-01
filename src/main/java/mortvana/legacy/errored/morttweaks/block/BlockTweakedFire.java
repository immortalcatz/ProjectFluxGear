package mortvana.legacy.errored.morttweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockTweakedFire extends BlockFire {

	public BlockTweakedFire(float hardness, float lightValue, SoundType soundType, String unlocalizedName, String textureName) {
		super();
		setHardness(hardness);
		setLightLevel(lightValue);
		setStepSound(soundType);
		setUnlocalizedName(unlocalizedName);
		setTextureName(textureName);
		disableStats();
	}

	public BlockTweakedFire() {
		this(0.0F, 1.0F, Block.soundTypeWood, "fire", "fire");
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		super.registerIcons(iconRegister);
		Blocks.fire.registerIcons(iconRegister);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		if (world.provider.dimensionId > 0 || world.getBlock(x, y - 1, z) != Blocks.obsidian || !Blocks.portal.tryToCreatePortal(world, x, y, z)) {
			if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && canNeighborBurn(world, x, y, z)) {
				world.setBlockToAir(x, y, z);
			} else {
				world.scheduleBlockUpdate(x, y, z, this, tickRate(world) + world.rand.nextInt(10));
			}
		}
	}

	//private boolean canNeighborBurn(World world, int x, int y, int z) {}
}
