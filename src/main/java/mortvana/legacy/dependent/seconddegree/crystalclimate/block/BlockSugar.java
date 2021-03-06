package mortvana.legacy.dependent.seconddegree.crystalclimate.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.dependent.firstdegree.crystalclimate.common.CrystalClimate;

public class BlockSugar extends Block {
	public BlockSugar() {
		super(Material.cloth);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setCreativeTab(CrystalClimate.tab);
		setBlockBoundsForSnowDepth(0);
	}


	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("crystal:sugar");
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
		setBlockBoundsForSnowDepth(7);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		setBlockBoundsForSnowDepth(world.getBlockMetadata(x, y, z));
	}

	/**
	 * calls setBlockBounds based on the depth of the snow. Int is any values 0x0-0x7, usually this blocks metadata.
	 */
	protected void setBlockBoundsForSnowDepth(int par1) {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, ((float) (2 * (1 + (par1 & 7))) / 16.0F), 1.0F);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		return block != null && ((block == this && (world.getBlockMetadata(x, y - 1, z) & 7) == 7) || (block.isLeaves(world, x, y - 1, z) && !block.isOpaqueCube()) && world.getBlock(x, y - 1, z).getMaterial().blocksMovement());
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor blockID
	 */
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
		this.canSugarStay(par1World, par2, par3, par4);
	}

	/**
	 * Checks if this snow block can stay at this location.
	 */
	private boolean canSugarStay(World world, int x, int y, int z) {
		if (!canPlaceBlockAt(world, x, y, z)) {
			int meta = world.getBlockMetadata(x, y, z);
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			ItemStack itemstack = createStackedBlock(meta);

			if (itemstack != null) {
				items.add(itemstack);
			}
			for (ItemStack is : items) {
				dropBlockAsItem(world, x, y, z, is);
			}
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
	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
		par1World.setBlockToAir(par3, par4, par5);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Items.sugar;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return side == 1 || super.shouldSideBeRendered(world, x, y, z, side);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return (meta & 7) + 1;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 7));
	}

	@Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
		return false;
	}

	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return 7;
	}
}
