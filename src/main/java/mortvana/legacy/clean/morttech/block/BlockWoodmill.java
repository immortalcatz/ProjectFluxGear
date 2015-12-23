package mortvana.legacy.clean.morttech.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.legacy.clean.morttech.block.tile.WoodmillLogic;
import mortvana.legacy.dependent.firstdegree.core.common.FluxGearContent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

import mortvana.legacy.clean.core.client.gui.FluxGearGUIHandler;
import mortvana.legacy.errored.core.ProjectFluxGear;
import mortvana.melteddashboard.api.item.tool.wrench.IFluxGearWrench;

import static mortvana.melteddashboard.util.repack.mortvana.science.math.MathHelper.RANDOM;

public class BlockWoodmill extends BlockContainer {

    /**
     * This flag is used to prevent the furnace inventory to be dropped upon block removal, is used internally when the
     * furnace block changes from idle to active and vice-versa.
     */
    private static boolean keepFurnaceInventory;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconFront;

	public BlockWoodmill(Material material) {
		super(material);
	}

    public BlockWoodmill() {
        this(Material.iron);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public Item getItemDropped(Item item, Random random, int par3) {
        return Item.getItemFromBlock(FluxGearContent.tileMachineTier0);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        setDefaultDirection(world, x, y, z);
    }

    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block l = world.getBlock(x, y, z - 1);
            Block i1 = world.getBlock(x, y, z + 1);
            Block j1 = world.getBlock(x - 1, y, z);
            Block k1 = world.getBlock(x + 1, y, z);
            byte b0 = 3;

            if (l.isFullBlock() && i1.isFullBlock()) {
                b0 = 3;
            }

            if (i1.isFullBlock() && !l.isFullBlock()) {
                b0 = 2;
            }

            if (j1.isFullBlock() && k1.isFullBlock()) {
                b0 = 5;
            }

            if (k1.isFullBlock() && j1.isFullBlock()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? furnaceIconTop : (side == 0 ? furnaceIconTop : (side != metadata ? blockIcon : furnaceIconFront));
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon("morttech:" + getUnlocalizedName());
        furnaceIconFront = iconRegister.registerIcon("morttech:" + getUnlocalizedName() + "_front");
        furnaceIconTop = iconRegister.registerIcon("morttech:" + getUnlocalizedName() + "_top");
    }

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            WoodmillLogic woodmill = (WoodmillLogic) world.getTileEntity(x, y, z);
            if (woodmill != null && !(player.getCurrentEquippedItem().getItem() instanceof IFluxGearWrench)) {
                player.openGui(ProjectFluxGear.instance, FluxGearGUIHandler.woodmill, world, x, y, z);
            }
        }
		/*if (player.isSneaking()) {
            FMLClientHandler.instance().displayGuiScreen(player, new GuiWoodmill(player.inventory, new WoodmillLogic()));
            return true;
        }
        return false;*/
        return true;
    }

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new WoodmillLogic();
    }

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new WoodmillLogic();
	}


	/**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (stack.hasDisplayName()) {
            ((WoodmillLogic) world.getTileEntity(x, y, z)).setInvName(stack.getDisplayName());
        }
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        if (!keepFurnaceInventory) {
            WoodmillLogic tile = (WoodmillLogic) world.getTileEntity(x, y, z);

            if (tile != null) {
                for (int j1 = 0; j1 < tile.getSizeInventory(); ++j1) {
                    ItemStack itemstack = tile.getStackInSlot(j1);

                    if (itemstack != null) {
                        float f = RANDOM.nextFloat() * 0.8F + 0.1F;
                        float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
                        float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0) {
                            int k1 = RANDOM.nextInt(21) + 10;

                            if (k1 > itemstack.stackSize) {
                                k1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getMetadata()));

                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double) ((float) RANDOM.nextGaussian() * f3);
                            entityitem.motionY = (double) ((float) RANDOM.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double) ((float) RANDOM.nextGaussian() * f3);
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                world.updateNeighborsAboutBlockChange(x, y, z, block);
            }
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    @Override
    public boolean hasComparatorInputOverride(){
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int par5) {
        return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(FluxGearContent.tileMachineTier0);
    }
}
