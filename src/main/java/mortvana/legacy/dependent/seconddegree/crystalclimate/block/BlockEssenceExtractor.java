package mortvana.legacy.dependent.seconddegree.crystalclimate.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.FakePlayer;

import mortvana.legacy.dependent.firstdegree.crystalclimate.common.CrystalClimate;
import mortvana.legacy.dependent.seconddegree.crystalclimate.block.tile.EssenceExtractorLogic;

public class BlockEssenceExtractor extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;

	@SideOnly(Side.CLIENT)
	private IIcon bottomIcon;

	public BlockEssenceExtractor() {
		super( Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
		setLightOpacity(0);
		setCreativeTab(CrystalClimate.tab);
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return false;
	}


	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		super.randomDisplayTick(world, x, y, z, random);

		for (int l = x - 2; l <= x + 2; ++l) {
			for (int i1 = z - 2; i1 <= z + 2; ++i1) {
				if (l > x - 2 && l < x + 2 && i1 == z - 1) {
					i1 = z + 2;
				}

				if (random.nextInt(16) == 0) {
					for (int j1 = y; j1 <= y + 1; ++j1) {
						if (world.getBlock(l, j1, i1) == Blocks.bookshelf) {
							if (!world.isAirBlock((l - x) / 2 + x, j1, (i1 - z) / 2 + z)) {
								break;
							}
							world.spawnParticle("enchantmenttable", (double) x + 0.5D, (double) y + 2.0D, (double) z + 0.5D, (double) ((float) (l - x) + random.nextFloat()) - 0.5D, (double) ((float) (j1 - y) - random.nextFloat() - 1.0F), (double) ((float) (i1 - z) + random.nextFloat()) - 0.5D);
						}
					}
				}
			}
		}
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() {
		return false;
	}


	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return par1 == 0 ? bottomIcon : (par1 == 1 ? topIcon : blockIcon);
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new EssenceExtractorLogic();
	}

    /* Right-click to form a crystal */

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (!world.isRemote) {
			EssenceExtractorLogic logic = (EssenceExtractorLogic) world.getTileEntity(x, y, z);
			int amount = logic.removeEssence();
			if (amount > 0) {
				ItemStack crystal = new ItemStack(CrystalClimate.essenceCrystal, 1);
				NBTTagCompound tags = new NBTTagCompound();
				tags.setInteger("Essence", amount);
				crystal.setTagCompound(tags);

				EntityItem entityitem = new EntityItem(world, player.posX, player.posY - 1.0D, player.posZ, crystal);
				world.spawnEntityInWorld(entityitem);
				if (!(player instanceof FakePlayer))
					entityitem.onCollideWithPlayer(player);
			}
		}
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!par1World.isRemote) {
			EssenceExtractorLogic logic = (EssenceExtractorLogic) par1World.getTileEntity(x, y, z);
			logic.addEssence(player);
			logic.getEssenceMessage(player);
		}
		return true;
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving, par6ItemStack);

		if (par6ItemStack.hasDisplayName()) {
			((EssenceExtractorLogic) par1World.getTileEntity(par2, par3, par4)).func_94134_a(par6ItemStack.getDisplayName());
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon("crystal:extractor_side");
		topIcon = iconRegister.registerIcon("crystal:extractor_top");
		bottomIcon = iconRegister.registerIcon("crystal:extractor_bottom");
	}
}
