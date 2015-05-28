package mortvana.projectfluxgear.to_refactor.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mortvana.projectfluxgear.util.helper.cofh.ItemHelper;
import mortvana.projectfluxgear.util.helper.cofh.StringHelper;

import mortvana.projectfluxgear.to_refactor.block.BlockTemporalPylon;

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
