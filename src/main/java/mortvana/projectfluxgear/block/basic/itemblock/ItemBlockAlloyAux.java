package mortvana.projectfluxgear.block.basic.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mortvana.fluxgearcore.util.helper.ItemHelper;
import mortvana.fluxgearcore.util.helper.StringHelper;
import mortvana.projectfluxgear.block.basic.BlockAlloyAux;

public class ItemBlockAlloyAux extends ItemBlock {

    public ItemBlockAlloyAux(Block block) {

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

        return "tile.projectfluxgear.fluidicalloy." + BlockAlloyAux.NAMES[ItemHelper.getItemDamage(item)] + ".name";
    }

    @Override
    public int getMetadata(int i) {

        return i;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return EnumRarity.values()[BlockAlloyAux.RARITY[ItemHelper.getItemDamage(stack)]];
    }
}
