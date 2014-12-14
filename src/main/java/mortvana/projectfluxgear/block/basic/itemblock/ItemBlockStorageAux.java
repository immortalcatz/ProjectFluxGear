package mortvana.projectfluxgear.block.basic.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.fluxgearcore.block.ItemBlockFluxGear;
import mortvana.fluxgearcore.util.helper.ItemHelper;
import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.block.basic.BlockStorageAux;

public class ItemBlockStorageAux extends ItemBlockFluxGear {

	public ItemBlockStorageAux(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {

		return "tile.projectfluxgear.storage." + BlockStorageAux.NAMES[ItemHelper.getItemDamage(item)] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.values()[BlockStorageAux.RARITY[ItemHelper.getItemDamage(stack)]];
	}

}
