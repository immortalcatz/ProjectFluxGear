package mortvana.legacy.errored.crystalclimate.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mortvana.legacy.dependent.seconddegree.crystalclimate.client.CrystalBlockRender;
import mortvana.legacy.errored.crystalclimate.common.CrystalClimate;
import mortvana.legacy.errored.crystalclimate.block.tile.CrystalLogic;
import mortvana.legacy.errored.crystalclimate.block.tile.RedstoneAggregator;
import mortvana.melteddashboard.util.helpers.ChatHelper;

public class BlockCrystal extends BlockContainer {
	String[] textureNames = {"redstone", "lightstone"};
	IIcon[] icons;

	public BlockCrystal() {
		super(Material.glass);
		setCreativeTab(CrystalClimate.tab);
		setStepSound(soundTypeGlass);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[0];
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return CrystalBlockRender.model;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	    /*for (int iter = 0; iter < 1; iter++)
        {
            list.add(new ItemStack(id, 1, iter));
        }*/
	}

	public void registerIcons(IIconRegister iconRegister) {
		this.icons = new IIcon[textureNames.length];

		for (int i = 0; i < this.icons.length; ++i) {
			this.icons[i] = iconRegister.registerIcon("crystal:crystal_" + textureNames[i]);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new CrystalLogic();
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		//if (world.getBlockMetadata(x, y, z) == 0)
		{
			TileEntity logic = world.getTileEntity(x, y, z);

			if (logic != null && logic instanceof CrystalLogic) {
				if (((CrystalLogic) logic).getActive()) {
					return 15;
				}
			}
		}
		return super.getLightValue(world, x, y, z);
	}

    /*@Override
    public void breakBlock (World world, int x, int y, int z, int par5, int meta)
    {
        if (meta <= 4)
        {
            TileEntity logic = world.getBlockTileEntity(x, y, z);

            if (logic != null && logic instanceof CrystalLogic)
            {
                
            }
        }
    }*/

	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		player.addExhaustion(0.025F);
		int meta = world.getBlockMetadata(x, y, z);
		if (meta <= 5) {
			CrystalLogic logic = (CrystalLogic) world.getTileEntity(x, y, z);
			int value = logic.getCrystalValue();

			TileEntity aggregator = world.getTileEntity(x, y - 1, z);
			if (aggregator instanceof RedstoneAggregator) {
				value = ((RedstoneAggregator) aggregator).getCrystalValue();
				((RedstoneAggregator) aggregator).harvestCrystal();
			}
			ItemStack stack = new ItemStack(Items.redstone, value / 10, 0);

			for (int i = 0; i < getCrystalHeight(value); i++)
				world.setBlockToAir(x, y + i, z);

			if ((!player.capabilities.isCreativeMode || player.isSneaking()) && value >= 10)
				dropBlock(world, x, y, z, stack);

			//Drop the crystal itself
            /*ItemStack stack = new ItemStack(this.blockID, 1, 0);
            CrystalLogic logic = (CrystalLogic) world.getBlockTileEntity(x, y, z);
            NBTTagCompound tag = new NBTTagCompound();
            int value = logic.getCrystalValue();
            tag.setInteger("Value", value);
            stack.setTagCompound(tag);

            if (logic.growing())
            {
                TileEntity aggregator = world.getBlockTileEntity(x, y - 1, z);
                if (aggregator instanceof RedstoneAggregator)
                {
                    ((RedstoneAggregator) aggregator).harvestCrystal();
                }
            }

            for (int i = 0; i < getCrystalHeight(value); i++)
                world.setBlockToAir(x, y + i, z);

            if (!player.capabilities.isCreativeMode || player.isSneaking())
                dropBlock(world, x, y, z, stack);*/
		} else {
			Block below = world.getBlock(x, y - 1, z);
			if (below == CrystalClimate.crystalBlock) {
				below.removeBlockByPlayer(world, player, x, y - 1, z);
			} else
				world.setBlockToAir(x, y, z);
		}

		return true;//world.setBlockToAir(x, y, z);
	}

	public static int getCrystalHeight(int crystalValue) {
		if (crystalValue >= 440)
			return 4;
		if (crystalValue >= 224)
			return 3;
		if (crystalValue >= 80)
			return 2;

		return 1;
	}

	protected void dropBlock(World world, int x, int y, int z, ItemStack stack) {
		if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
			float f = 0.7F;
			double d0 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d1 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			double d2 = (double) (world.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
			EntityItem entityitem = new EntityItem(world, (double) x + d0, (double) y + d1, (double) z + d2, stack);
			entityitem.delayBeforeCanPickup = 10;
			world.spawnEntityInWorld(entityitem);
		}
	}

	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
        /*if (meta > 4)
            super.harvestBlock(world, player, x, y, z, meta);*/
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
		CrystalLogic logic = (CrystalLogic) world.getTileEntity(x, y, z);
		logic.setActive(true);
		if (stack.hasTagCompound()) {
			int value = stack.getTagCompound().getInteger("Value");
			//WorldTracker.updateTheft(world.provider.dimensionId, x, z, value, CrystalType.Light);
			logic.setCrystalValue(value);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.isSneaking())
			return false;
		if (!player.worldObj.isRemote) {
			int meta = world.getBlockMetadata(x, y, z);
			if (meta > 5) {
				Block block = world.getBlock(x, y - 1, z);
				if (block == this)
					block.onBlockActivated(world, x, y - 1, z, player, side, hitX, hitY, hitZ);
			} else {
				TileEntity te = world.getTileEntity(x, y - 1, z);
				float value = 0;
				if (te instanceof RedstoneAggregator) {
					value = ((RedstoneAggregator) te).getCrystalValue() / 10f;
				} else {
					CrystalLogic logic = (CrystalLogic) world.getTileEntity(x, y, z);
					value = logic.getCrystalValue() / 10f;
				}

				ChatHelper.addChatMessage(player, StatCollector.translateToLocal("tooltip.crystalvalue") + ": " + value);
			}

		}
		return true;
	}
}
