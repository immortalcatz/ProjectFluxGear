package mortvana.projectfluxgear.util.block.metadata;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemBlockMetadata extends ItemBlock {
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
