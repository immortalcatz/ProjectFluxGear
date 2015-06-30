package oldcode.projectfluxgear;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class BlockMetadata extends BlockContainer implements IBlockMetadata {


	//@Implemented
	public BlockMetadata(Material material) {
		super(material);
	}

	//
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int blockMeta, int fortune) {
		return getBlockDropped(this, world, x, y, z, blockMeta);
	}

	//@Implemented
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		List<ItemStack> drops = new ArrayList();

		Block block = (Block) this;
		TileEntityMetadata tile = TileEntityMetadata.getTile(world, x, y, z);

		if (tile != null && !tile.hasDroppedBlock()) {
			int tileMeta = TileEntityMetadata.getTileMetadata(world, x, y, z);
			drops = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		}
		boolean hasBeenBroken = world.setBlockToAir(x, y, z);
		if (hasBeenBroken && ProjectFluxGear.proxy.isSimulating(world) && drops.size() > 0 && (player == null || !player.capabilities.isCreativeMode)) {
			ItemStack drop;
			for (Iterator i$ = drops.iterator(); i$.hasNext(); this.dropAsStack(world, x, y, z, drop)) {
				drop = (ItemStack) i$.next();
			}
			tile.dropBlock();
		}
		return hasBeenBroken;
	}

	//
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMetadata();
	}

	//
	public boolean hasTileEntity(int meta) {
		return true;
	}

	//
	public boolean onBlockEventReceived(World world, int x, int y, int z, int par5, int par6) {
		super.onBlockEventReceived(world, x, y, z, par5, par6);
		TileEntity tile = world.getTileEntity(x, y, z);
		return tile != null && tile.receiveClientEvent(par5, par6);
	}

	//
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int meta = TileEntityMetadata.getTileMetadata(world, x, y, z);
		return getIcon(side, meta);
	}

	//
	public String getBlockName(ItemStack itemstack) {
		return getLocalizedName();
	}

	//
	public void getBlockTooltip(ItemStack itemstack, List list) {}

	//
	public int getPlacedMeta(ItemStack itemstack, World world, int x, int y, int z, ForgeDirection clickedBlock) {
		return TileEntityMetadata.getItemMetadata(itemstack);
	}

	//
	public int getDroppedMeta(int tileMeta, int blockMeta) {
		return tileMeta;
	}

	//
	public static ArrayList<ItemStack> getBlockDropped(IBlockMetadata block, World world, int x, int y, int z, int blockMeta) {
		ArrayList<ItemStack> array = new ArrayList<ItemStack>();
		TileEntityMetadata tile = TileEntityMetadata.getTile(world, x, y, z);

		if (tile != null && !tile.hasDroppedBlock()) {
			int meta = block.getDroppedMeta(world.getBlockMetadata(x, y, z), tile.getTileMetadata());
			array.add(TileEntityMetadata.getItemStack((Block) block, meta));
		}
		return array;
	}

	//
	public void dropAsStack(World world, int x, int y, int z, ItemStack itemstack) {
		dropBlockAsItem(world, x, y, z, itemstack);
	}

	//
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);
		world.removeTileEntity(x, y, z);
	}

	//
	public static ItemStack getPickBlock(World world, int x, int y, int z) {
		List<ItemStack> list = getBlockDropped((IBlockMetadata) world.getBlock(x, y, z), world, x, y, z, world.getBlockMetadata(x, y, z));
		return list.isEmpty() ? null : list.get(0);
	}

	//
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return getPickBlock(world, x, y, z);
	}














	public static class ItemBlockMetadata extends ItemBlock {
		public ItemBlockMetadata(Block block) {
			super(block);
		}

		public int getMetadata(int uselessMeta) {
			return 0;
		}

		public boolean placeBlockAt(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
			//A block that is a block is equal to... RANDOM FIELD NAME!?!? I blame Mojang...
			Block block = field_150939_a;
			//If the block is not an IEldritchMob... Wait, WHAT!?!?
			if (!(block instanceof IBlockMetadata)) {
				return false;
			}
			int placedMeta = ((IBlockMetadata) block).getPlacedMeta(itemstack, world, x, y, z, ForgeDirection.values()[side]);
			if (placedMeta < 0) {
				return false;
			}
			//And the three is for?
			if (!world.setBlock(x, y, z, block, meta, 3)) {
				return false;
			}
			if (world.getBlock(x, y, z) == block) {
				TileEntityMetadata tile = TileEntityMetadata.getTile(world, x, y, z);
				//If the tile is a senile old man...
				if (tile != null) {
					tile.setTileMetadata(placedMeta, false);
				}
				block.onBlockPlacedBy(world, x, y, z, player, itemstack);
				block.onPostBlockPlaced(world, x, y, z, meta);
			}
			return true;
		}

		@SideOnly(Side.CLIENT)
		public String getItemStackDisplayName(ItemStack itemstack) {
			return ((IBlockMetadata) field_150939_a).getBlockName(itemstack);
		}

		//func_77624_a
		@SideOnly(Side.CLIENT)
		public void randomTooltipListMethod(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
			((IBlockMetadata) field_150939_a).getBlockTooltip(itemstack, list);
		}

		public IIcon getIconFromDamage(int meta) {
			return field_150939_a.getIcon(1, meta);
		}
	}

	public static class ItemBlockMetadataRenderer implements IItemRenderer {

		public ItemBlockMetadataRenderer() {}

		public boolean handleRenderType(ItemStack item, ItemRenderType type) {
			return type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
		}

		public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
			if (type == ItemRenderType.INVENTORY) {
				return helper == ItemRendererHelper.INVENTORY_BLOCK;
			}
			if (type == ItemRenderType.ENTITY) {
				return helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION;
			}
			if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
				return helper == ItemRendererHelper.EQUIPPED_BLOCK;
			}
			return false;
		}

		public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
			if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
				GL11.glTranslated(0.5D, 0.5D, 0.5D);
			}
			((RenderBlocks) data[0]).renderBlockAsItem(Block.getBlockFromItem(item.getItem()), TileEntityMetadata.getItemMetadata(item), 1.0F);
			GL11.glPopMatrix();
		}
	}

	public static class TileEntityMetadata extends TileEntity {

		protected int meta;

		public TileEntityMetadata() {}

		public boolean receiveClientEvent(int par1, int par2) {
			if (par1 == 42) {
				meta = par2;
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
			return true;
		}

		public void readFromNBT(NBTTagCompound nbt) {
			super.readFromNBT(nbt);
			meta = nbt.getInteger("meta");
		}

		public void writeToNBT(NBTTagCompound nbt) {
			super.writeToNBT(nbt);
			nbt.setInteger("meta", meta);
		}

		public boolean canUpdate() {
			return false;
		}

		public int getTileMetadata() {
			return meta;
		}

		public void setTileMetadata(int meta, boolean notify) {
			if (this.meta != meta) {
				this.meta = meta;
				if (notify) {
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}
			}
		}

		public Packet getDescriptionPacket() {
			return ProjectFluxGear.instance.getNetworkWrapper().getPacketFrom(new MessageBase.MessageMetadata(xCoord, yCoord, zCoord, meta).getMessage());
		}

		public static TileEntityMetadata getTile(IBlockAccess world, int x, int y, int z) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (!(tile instanceof TileEntityMetadata)) {
				return null;
			}
			return (TileEntityMetadata) tile;
		}

		public static ItemStack getItemStack(Block block, int meta) {
			ItemStack itemstack = new ItemStack(block, 1, 0);
			setItemMetadata(itemstack, meta);
			return itemstack;
		}

		public static void setItemMetadata(ItemStack itemstack, int meta) {
			itemstack.setItemDamage(meta < 16387 ? meta : 16387);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("meta", meta);
			itemstack.setTagCompound(tag);
		}

		public static int getItemMetadata(ItemStack itemstack) {
			if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("meta")) {
				return itemstack.getTagCompound().getInteger("meta");
			}
			return itemstack.getItemDamage();
		}

		public static int getTileMetadata(IBlockAccess world, int x, int y, int z) {
			TileEntityMetadata tile = getTile(world, x, y, z);
			return tile == null ? 0 : tile.getTileMetadata();
		}

		boolean droppedBlock = false;

		public boolean hasDroppedBlock() {
			return droppedBlock;
		}

		public void dropBlock() {
			droppedBlock = true;
		}
	}

}
