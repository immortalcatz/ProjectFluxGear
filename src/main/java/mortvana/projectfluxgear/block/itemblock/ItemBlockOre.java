package mortvana.projectfluxgear.block.itemblock;

import cofh.lib.util.helpers.ItemHelper;
import cofh.lib.util.helpers.StringHelper;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mortvana.projectfluxgear.block.BlockOre;

public class ItemBlockOre extends ItemBlock {

    public ItemBlockOre(Block block) {

        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public String getItemStackDisplayName(ItemStack item) {

        return StringHelper.localize(getUnlocalizedName(item));
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {

        return "tile.projectfluxgear.ore." + BlockOre.NAMES[ItemHelper.getItemDamage(item)] + ".name";
    }

    @Override
    public int getMetadata(int i) {

        return i;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return EnumRarity.values()[BlockOre.RARITY[ItemHelper.getItemDamage(stack)]];
    }

}
