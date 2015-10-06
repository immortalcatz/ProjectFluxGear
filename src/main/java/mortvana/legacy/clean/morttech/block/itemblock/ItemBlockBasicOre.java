package mortvana.legacy.clean.morttech.block.itemblock;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemBlockBasicOre extends ItemBlock {

    public static final String blockType[] = { "chalcopyrite_ore", "cassiterite_ore", "acanthite_ore", "galena_ore", "sphalerite_ore", "bismuthinite_ore", "garnierite_ore", "chromite_ore", "cobaltite_ore", "wolframite_ore" };

    public ItemBlockBasicOre(Block block) {
        super(block);
        setHasSubtypes(true);
        setUnlocalizedName("BasicOre");
    }

    public int getMetadata (int metadata)
    {
        return metadata;
    }

    public String getUnlocalizedName (ItemStack itemstack) {
        int index = MathHelper.clamp_int(itemstack.getItemDamage(), 0, blockType.length - 1);
        return "block.ore.basic.";
    }

	public String getUnlocalizedName2 (ItemStack itemstack) {
		String name = "";

		switch(itemstack.getItemDamage()) {
			case 0:
				name= "Chalcocite Ore";
				break;
			case 1:
				name= "Cassiterite Ore";
				break;
			case 2:
				name= "Acanthite Ore";
				break;
			case 3:
				name= "Galena Ore";
				break;
			case 4:
				name= "Sphalerite Ore";
				break;
			case 5:
				name= "Bismuthinite Ore";
				break;
			case 6:
				name= "Millerite Ore";
				break;
			case 7:
				name= "Chromite Ore";
				break;
			case 8:
				name= "Cobaltite Ore";
				break;
			default: name = "Basic Ore";
		}

		return getUnlocalizedName() + "." + name;
	}
    
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < blockType.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
}
