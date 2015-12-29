package mortvana.legacy.dependent.seconddegree.crystalclimate.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.ForgeDirection;

import mortvana.legacy.dependent.firstdegree.crystalclimate.common.CrystalClimate;

public class BlockAsh extends Block {
	public BlockAsh() {
		super(Material.sand);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setCreativeTab(CrystalClimate.tab);
		setBlockBoundsForAshDepth(0);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox((double) x + minX, (double) y + minY, (double) z + minZ, (double) x + maxX, (double) ((float) y + (float) (world.getBlockMetadata(x, y, z) & 7) * 0.125F), (double) z + maxZ);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
    @Override
	public boolean isOpaqueCube() {
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
    @Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	/**
	 * Sets the block's bounds for rendering it as an item
	 */
    @Override
	public void setBlockBoundsForItemRender() {
		setBlockBoundsForAshDepth(0);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBoundsForAshDepth(world.getBlockMetadata(x, y, z));
	}

	/**
	 * calls setBlockBounds based on the depth of the snow. Int is any values 0x0-0x7, usually this blocks metadata.
	 */
	protected void setBlockBoundsForAshDepth(int height) {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, ((float) (2 * (1 + height & 7)) / 16.0F), 1.0F);
	}

	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
		return world.getBlockMetadata(x, y, z) == 7;
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
    @Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y - 1, z);
        return block != null && (block == this && (world.getBlockMetadata(x, y - 1, z) & 7) == 7 || !(!block.isLeaves(world, x, y - 1, z) && !block.isOpaqueCube()) && world.getBlock(x, y - 1, z).getMaterial().blocksMovement());
    }

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor blockID
	 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		canAshStay(world, x, y, z);
	}

	/**
	 * Checks if this snow block can stay at this location.
	 */
	public boolean canAshStay(World world, int x, int y, int z) {
		if (canPlaceBlockAt(world, x, y, z)) {
			world.setBlockToAir(x, y, z);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
	 * block and l is the block's subtype/damage.
	 */
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int par6) {
		super.harvestBlock(world, player, x, y, z, par6);
		world.setBlockToAir(x, y, z);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public Item getItemDropped(int metadata, Random random, int par3) {
		return Items.snowball;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random random) {
		return 1;
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return side == 1 || super.shouldSideBeRendered(world, x, y, z, side);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return (meta & 7) + 1;
	}
}
