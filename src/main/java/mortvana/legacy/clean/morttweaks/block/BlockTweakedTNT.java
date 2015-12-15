package mortvana.legacy.clean.morttweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTweakedTNT extends BlockTNT {

	public BlockTweakedTNT(float hardness, SoundType soundType, String unlocalizedName, String textureName) {
		super();
		setHardness(hardness);
		setStepSound(soundType);
		setUnlocalizedName(unlocalizedName);
		setTextureName(textureName);
	}

	public BlockTweakedTNT() {
		this(0.0F, Block.soundTypeGrass, "tnt", "tnt");
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
		func_150114_a(world, x, y, z, par5, null);
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		ItemStack stack = player != null ? player.getEquipmentInSlot(0) : null;
		if (player == null || !player.capabilities.isCreativeMode && (stack == null || (!(stack.getItem() instanceof ItemShears) && !EnchantmentHelper.getSilkTouchModifier(player)))) {
			func_150114_a(world, x, y, z, world.getBlockMetadata(x, y, z), player);
			world.setBlockToAir(x, y, z);
			return false;
		}
		return world.setBlockToAir(x, y, z);
	}

	@Override
	public void func_150114_a(World world, int x, int y, int z, int par5, EntityLivingBase entity) { //primeTnt(...) {}
		if (!world.isRemote) {
			EntityTNTPrimed tnt = new EntityTNTPrimed(world, (double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), entity);
			world.spawnEntityInWorld(tnt);
			world.playSoundAtEntity(tnt, "game.tnt.primed", 1.0F, 1.0F);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			removedByPlayer(world, null, x, y, z);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		super.registerIcons(iconRegister);
		Blocks.tnt.registerIcons(iconRegister);
	}
}
