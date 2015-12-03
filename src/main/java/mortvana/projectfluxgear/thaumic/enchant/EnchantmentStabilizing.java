package mortvana.projectfluxgear.thaumic.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IRepairable;

public class EnchantmentStabilizing extends Enchantment {
    public EnchantmentStabilizing(int id, int weight) {
        super(id, weight, EnumEnchantmentType.all);
        setName("stabilizing");
    }

    @Override
    public int getMinEnchantability(int level) {
        return 20 + (level - 1) * 10;
    }

    @Override
    public int getMaxEnchantability(int level) {
        return super.getMinEnchantability(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.isItemStackDamageable() && (stack.getItem() instanceof IRepairable || stack.getItem() instanceof IStabilizing || stack.getItem() instanceof ItemBook);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.canApply(stack);
    }

    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != Enchantment.unbreaking.effectId;
    }

    public static boolean canRepair(ItemStack itemstack, EntityPlayer player) {
        return itemstack.getMetadata() > 0 && !player.capabilities.isCreativeMode && (itemstack.getItem() instanceof IRepairable || itemstack.getItem() instanceof IStabilizing || (itemstack.getItem().getItemEnchantability() != 0 && player.ticksExisted % 40 * itemstack.getItem().getItemEnchantability() == 0));
    }

    public static void doRepair(ItemStack itemstack, EntityPlayer player) {
        int level = EnchantmentHelper.getEnchantmentLevel(42 /*TODO: Config for ID*/, itemstack);
        if (level > 4) {
            level = 4;
        }
        if ((!(itemstack.getItem() instanceof IRepairable) || !(itemstack.getItem() instanceof IStabilizing)) && level > 2) {
            level = 2;
        }

        itemstack.damageItem(getRandomizedInteger(level), player);
    }

    public static void canDoRepair(ItemStack itemstack, EntityPlayer player) {
        if (canRepair(itemstack, player)) {
            doRepair(itemstack, player);
        }
    }

    public static int getRandomizedInteger(int level) {
        int sqLvl = level * level;
        int fact;

        /*TODO: Integer Randomizer*/return level;
    }
}
