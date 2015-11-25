package mortvana.projectfluxgear.thaumic.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

public class ItemTinkersTrenchcoat extends ItemMagitechArmor {

    @Override
    public float getPixieChance(ItemStack stack) {
        return 0;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return 0;
    }
}
