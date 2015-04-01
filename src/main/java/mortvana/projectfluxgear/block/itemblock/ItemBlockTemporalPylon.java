package mortvana.projectfluxgear.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mortvana.projectfluxgear.util.helper.ItemHelper;
import mortvana.projectfluxgear.util.helper.StringHelper;

import mortvana.projectfluxgear.block.BlockTemporalPylon;

public class ItemBlockTemporalPylon extends ItemBlock {

	public ItemBlockTemporalPylon(Block block) {
		super(block);
		setHasSubtypes(true).setMaxDamage(0);
	}

	@Override
	public String getItemStackDisplayName(ItemStack item) {

		return StringHelper.localize(getUnlocalizedName(item));
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {

		return "tile.mortvana.projectfluxgear.temporalPylon." + BlockTemporalPylon.NAMES[ItemHelper.getItemDamage(item)] + ".name";
	}

	@Override
	public int getMetadata(int i) {

		return i;
	}
}
