package mortvana.legacy.dependent.firstdegree.crystalclimate.block;

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

import mortvana.legacy.errored.crystalclimate.CrystalClimate;

public class BlockAsh extends Block {
	public BlockAsh() {
		super(Material.sand);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setCreativeTab(CrystalClimate.tab);
		setBlockBoundsForSnowDepth(0);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		int l = par1World.getBlockMetadata(par2, par3, par4) & 7;
		float f = 0.125F;
		return AxisAlignedBB.getBoundingBox((double) par2 + minX, (double) par3 + minY, (double) par4 + minZ, (double) par2 + maxX, (double) ((float) par3 + (float) l * f), (double) par4 + maxZ);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return false;
	}

	/**
	 * Sets the block's bounds for rendering it as an item
	 */
	public void setBlockBoundsForItemRender() {
		setBlockBoundsForSnowDepth(0);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4) {
		setBlockBoundsForSnowDepth(world.getBlockMetadata(par2, par3, par4));
	}

	/**
	 * calls setBlockBounds based on the depth of the snow. Int is any values 0x0-0x7, usually this blocks metadata.
	 */
	protected void setBlockBoundsForSnowDepth(int par1) {
		int j = par1 & 7;
		float f = (float) (2 * (1 + j)) / 16.0F;
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
	}

	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
		return world.getBlockMetadata(x, y, z) == 7;
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		if (block == null)
			return false;
		if (block == this && (world.getBlockMetadata(x, y - 1, z) & 7) == 7)
			return true;
		if (!block.isLeaves(world, x, y - 1, z) && !block.isOpaqueCube())
			return false;
		return world.getBlock(x, y - 1, z).getMaterial().blocksMovement();
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor blockID
	 */
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		this.canAshStay(par1World, par2, par3, par4);
	}

	/**
	 * Checks if this snow block can stay at this location.
	 */
	private boolean canAshStay(World world, int par2, int par3, int par4) {
		if (canPlaceBlockAt(world, par2, par3, par4)) {
			world.setBlockToAir(par2, par3, par4);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
	 * block and l is the block's subtype/damage.
	 */
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
		par1World.setBlockToAir(par3, par4, par5);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public Item itemDropped(int par1, Random random, int par3) {
		return Items.snowball;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random) {
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
