package mortvana.melteddashboard.block.metadata;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockMetadata extends ItemBlock {

	public ItemBlockMetadata(Block block) {
		super(block);
		setMaxDurability(0);
		setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack) {
		return blockInstance.getRenderColor(stack.getMetadata());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return blockInstance.getIcon(2, meta);
	}

	@Override
	public int getMetadata(int meta) {
		return 0;
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		int placedMeta = ((BlockExtendedMetadata) blockInstance).getPlacedMetadata(player, stack, world, x, y, z, side, hitX, hitY, hitZ);
		if (!world.setBlock(x, y, z, blockInstance, metadata, 3)) {
			return false;
		}
		if (world.getBlock(x, y, z) == blockInstance) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null) {
				if (!(tile instanceof TileEntityMetadata)) {
					tile = new TileEntityMetadata();
					world.setTileEntity(x, y, z, tile);
				}
				((TileEntityMetadata) tile).setTileMetadata(placedMeta);
			}
			blockInstance.onBlockPlacedBy(world, x, y, z, player, stack);
			blockInstance.onPostBlockPlaced(world, x, y, z, metadata);
		}
		return true;
	}

	public static ItemStack withTag(ItemStack block, int metadata) {
		if (!block.hasTagCompound()) {
			block.stackTagCompound = new NBTTagCompound();
		}
		block.stackTagCompound.setInteger("Metadata", metadata);
		return block;
	}
}
