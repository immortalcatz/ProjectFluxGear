package mortvana.projectfluxgear.thaumic.item.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;

public class ItemTinkersBoots extends ItemMagitechArmor {

    @Override
    public float getPixieChance(ItemStack stack) {
        return 0;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return 0;
    }
}
