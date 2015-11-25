package mortvana.projectfluxgear.thaumic.block;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import mortvana.melteddashboard.block.FluxGearItemBlock;

public class ItemBlockThaumicPlant extends FluxGearItemBlock {

	public static String[] plantNames = new String[] { "exubitura" };
	public static int[] plantRarities = new int[] { 1 };

	public ItemBlockThaumicPlant(Block block) {
		super(block, plantNames, plantRarities, "fluxgear.thaumicPlant");
	}

	@Override
	public int getMetadata(int metadata) {
		return (metadata % 8) + 8;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return unlocalizedName + names[itemstack.getMetadata() % 8] + (itemstack.getMetadata() < 8 ? ".wild" : ".cultivated") + ".name";
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.values()[rarities[itemstack.getMetadata() % 8]];
	}


}
