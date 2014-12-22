package mortvana.projectfluxgear.block.basic.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.fluxgearcore.block.ItemBlockFluxGear;
import mortvana.fluxgearcore.util.helper.ItemHelper;

import mortvana.projectfluxgear.block.basic.BlockAlloyMain;

public class ItemBlockAlloyMain extends ItemBlockFluxGear {

	public ItemBlockAlloyMain(Block block) {

		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {

		return "tile.projectfluxgear.storage." + BlockAlloyMain.NAMES[ItemHelper.getItemDamage(item)] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.values()[BlockAlloyMain.RARITY[ItemHelper.getItemDamage(stack)]];
	}
}
