package mortvana.projectfluxgear.util.block;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.projectfluxgear.util.helper.ItemHelper;

public class AdvItemBlockFluxGear extends ItemBlockFluxGear {

	public String unlocalizedName;
	public String[] blockNames;
	public int[] blockRarities;

	public AdvItemBlockFluxGear(Block block, String unlocName, String[] blockNames, int[] blockRarities) {
		super(block);
		unlocalizedName = unlocName;
		this.blockNames = blockNames;
		this.blockRarities = blockRarities;
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return unlocalizedName + blockNames[ItemHelper.getItemDamage(item)] + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.values()[blockRarities[ItemHelper.getItemDamage(stack)]];
	}
}
