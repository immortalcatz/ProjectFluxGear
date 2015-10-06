package mortvana.legacy.clean.morttech.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGemOre extends ItemBlock {

	public ItemBlockGemOre(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		String name;
		
		switch(itemstack.getItemDamage()) {
			case 0:
				name= "Dioptase Ore";
				break;
			case 1:
				name= "Ruby Ore";
				break;
			case 2:
				name= "Sapphire Ore";
				break;
			case 3:
				name= "Green Sapphire Ore";
				break;
			case 4:
				name= "Pink Sapphire Ore";
				break;
			case 5:
				name= "Purple Sapphire Ore";
				break;
			case 6:
				name= "Topaz Ore";
				break;
			case 7:
				name= "Tanzanite Ore";
				break;
			case 8:
				name= "Pyrope Ore";
				break;
			case 9:
				name= "Malachite Ore";
				break;
			case 10:
				name= "Uranite Ore";
				break;
			default: name = "Gem Ore";
		}
		
		return getUnlocalizedName() + "." + name;
	}
	
	public int getMetadata(int par1)
	{
		return par1;
	}
	
}
