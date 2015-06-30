package mortvana.projectfluxgear.util.block.metadata;

import java.util.ArrayList;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeEventFactory;

import mortvana.projectfluxgear.util.helpers.ServerHelper;

public abstract class BlockContainerMetadata extends BlockContainer {

	/**
	 *  Literally just a wrapper for a default block, stupidly simple!
	 *  @param material - The material of the block.
	 */
	public BlockContainerMetadata(Material material) {
		super(material);
	}

	/**
	 * The really simple way to initialize a block and add it to a creative tab.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 */
	public BlockContainerMetadata(Material material, CreativeTabs tab) {
		super(material);
		setCreativeTab(tab);
	}

	/**
	 * The simple way to initialize a block and add it to a creative tab, while also setting
	 * hardness and blast resistance.
	 * @param material - The material of the block.
	 * @param tab - The creative tab the block is under.
	 * @param hardness - The hardness of the block (how long it takes to mine).
	 * @param resistance - The blast resistance of the block (how resistant it is to explosions).
	 */
	public BlockContainerMetadata(Material material, CreativeTabs tab, float hardness, float resistance) {
		super(material);
		setCreativeTab(tab);
		setHardness(hardness);
		setResistance(resistance);
	}
	// TODO: Expand this as FluxGearBlock adds more constructors

	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		TileEntityMetadata tile = (TileEntityMetadata) getTileEntity(world, x, y, z);
		if (tile != null && !tile.hasDropped) {
			drops = world.getBlock(x, y, z).getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), EnchantmentHelper.getFortuneModifier(player));
		}
		boolean hasBeenBroken = world.setBlockToAir(x, y, z);
		if (hasBeenBroken && ServerHelper.isServerWorld(world) && drops.size() > 0 && (player == null || !player.capabilities.isCreativeMode)) {
			for (ItemStack drop : drops) {
				float chance = ForgeEventFactory.fireBlockHarvesting(drops, world, this, x, y, z, world.getBlockMetadata(x, y, z), EnchantmentHelper.getFortuneModifier(player), 1.0F, false, player);
				if (world.rand.nextFloat() <= chance) {
					dropBlockAsItem(world, x, y, z, drop);
				}

			}
			tile.hasDropped = true;
		}
		return hasBeenBroken;
	}

	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune) {
		TileEntityMetadata tile = (TileEntityMetadata) world.getTileEntity(x, y, z);
		if (tile != null && !tile.hasDropped) {
			super.dropBlockAsItemWithChance(world, x, y, z, meta, chance, fortune);
		}
	}

	public TileEntity getTileEntity(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		BlockContainer block = (BlockContainer) world.getBlock(x, y, z);
		if (tile == null || (block instanceof BlockContainerMetadata && !tile.getClass().isAssignableFrom(((BlockContainerMetadata) block).getTileEntityClass()))) {
			tile = block.createNewTileEntity(null, world.getBlockMetadata(x, y, z));
			if (world instanceof World) {
				tile = block.createNewTileEntity((World) world, world.getBlockMetadata(x, y, z));
				((World) world).setTileEntity(x, y, z, tile);
			}
		}
		return tile;
	}

	public abstract Class<? extends TileEntity> getTileEntityClass();
}
