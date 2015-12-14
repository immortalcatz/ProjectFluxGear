package mortvana.legacy.errored.core;

import java.util.List;

import mortvana.legacy.clean.core.util.block.metadata.IBlockMetadata;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.ForgeDirection;

import mortvana.legacy.errored.projectfluxgear.network.MessageBase;
import mortvana.legacy.errored.core.ProjectFluxGear;
import org.lwjgl.opengl.GL11;

public class BlockMetadata extends BlockContainer implements IBlockMetadata {

	public String getBlockName(ItemStack itemstack) {
		return getLocalizedName();
	}

	public void getBlockTooltip(ItemStack itemstack, List list) {}

	public int getPlacedMeta(ItemStack itemstack, World world, int x, int y, int z, ForgeDirection clickedBlock) {
		return TileEntityMetadata.getItemMetadata(itemstack);
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
			Block block = blockInstance;
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
			return ((IBlockMetadata) blockInstance).getBlockName(itemstack);
		}

		//func_77624_a
		@SideOnly(Side.CLIENT)
		public void randomTooltipListMethod(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
			((IBlockMetadata) blockInstance).getBlockTooltip(itemstack, list);
		}

		public IIcon getIconFromDamage(int meta) {
			return blockInstance.getIcon(1, meta);
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

		//
		public void readFromNBT(NBTTagCompound nbt) {
			super.readFromNBT(nbt);
			meta = nbt.getInteger("meta");
		}

		//
		public void writeToNBT(NBTTagCompound nbt) {
			super.writeToNBT(nbt);
			nbt.setInteger("meta", meta);
		}

		//
		public boolean canUpdate() {
			return false;
		}

		//
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
			itemstack.setMetadata(meta < 16387 ? meta : 16387);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("meta", meta);
			itemstack.setTagCompound(tag);
		}

		public static int getItemMetadata(ItemStack itemstack) {
			if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("meta")) {
				return itemstack.getTagCompound().getInteger("meta");
			}
			return itemstack.getMetadata();
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
