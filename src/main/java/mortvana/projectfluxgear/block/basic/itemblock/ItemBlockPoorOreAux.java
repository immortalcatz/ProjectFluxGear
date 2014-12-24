package mortvana.projectfluxgear.block.basic.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.fluxgearcore.block.ItemBlockFluxGear;
import mortvana.fluxgearcore.util.helper.ItemHelper;

import mortvana.projectfluxgear.block.basic.BlockPoorOreAux;

public class ItemBlockPoorOreAux extends ItemBlockFluxGear{

	public ItemBlockPoorOreAux(Block block) {

		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {

		return "tile.projectfluxgear.orePoor." + BlockPoorOreAux.NAMES[ItemHelper.getItemDamage(item)] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.values()[BlockPoorOreAux.RARITY[ItemHelper.getItemDamage(stack)]];
	}
}
