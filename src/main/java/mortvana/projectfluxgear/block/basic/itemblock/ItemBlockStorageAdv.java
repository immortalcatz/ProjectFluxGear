package mortvana.projectfluxgear.block.basic.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.fluxgearcore.block.ItemBlockFluxGear;
import mortvana.fluxgearcore.util.helper.ItemHelper;
import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.block.basic.BlockStorageAdv;

public class ItemBlockStorageAdv extends ItemBlockFluxGear {

	public ItemBlockStorageAdv(Block block) {

		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {

		return "tile.projectfluxgear.storage." + BlockStorageAdv.NAMES[ItemHelper.getItemDamage(item)] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.values()[BlockStorageAdv.RARITY[ItemHelper.getItemDamage(stack)]];
	}

}
