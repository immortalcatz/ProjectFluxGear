package oldcode.morttech;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockComplexOre extends ItemBlock {

	public ItemBlockComplexOre(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		String name = "";
		
		switch (itemstack.getItemDamage()) {
			case 0:
				name= "Bauxite Ore";
				break;

			case 1:
				name= "Monazite Ore";
				break;

			case 2:
				name= "Chalcocite Ore";
				break;

			case 3:
				name= "Millerite Ore";
				break;

			case 4:
				name= "Bornite Ore";
				break;

			case 5:
				name= "Limonite Ore";
				break;

			case 6:
				name= "Magnetite Ore";
				break;

			case 7:
				name= "Hematite Ore";
				break;

			case 8:
				name= "Pyrolusite Ore";
				break;

			case 9:
				name= "Molybdenite Ore";
				break;

			case 10:
				name= "Cooprite Ore";
				break;

			case 11:
				name= "Ilmenite Ore";
				break;

			case 12:
				name= "Tetrahedrite Ore";
				break;

			case 13:
				name= "Tennatite Ore";
				break;

			case 14:
				name= "Pentlandite Ore";
				break;

			case 15:
				name= "Nierdermayrite Ore";
				break;

			default:
				name = "Complex Ore";
		}
		
		return getUnlocalizedName() + "." + name;
	}
	
	public int getMetadata(int par1) {
		return par1;
	}
	
}
