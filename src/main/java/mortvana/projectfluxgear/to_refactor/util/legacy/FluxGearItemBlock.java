package mortvana.projectfluxgear.to_refactor.util.legacy;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mortvana.projectfluxgear.util.helper.StringHelper;

public class FluxGearItemBlock extends ItemBlock{

    public FluxGearItemBlock(Block block, String sentBlockName, Class sentBlockClass) {

        super(block);
        blockName = sentBlockName;
        blockClass = sentBlockClass;
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public String getItemStackDisplayName(ItemStack item) {

        return StringHelper.localize(getUnlocalizedName(item));
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {

        return null;//"tile." + blockName + "." + BlockOre.NAMES[ItemHelper.getItemDamage(item)] + ".name";
    }

    @Override
    public int getMetadata(int i) {

        return i;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return null;//EnumRarity.values()[BlockOre.RARITY[ItemHelper.getItemDamage(stack)]];
    }

    public static String blockName;
    public static Class blockClass;
}
