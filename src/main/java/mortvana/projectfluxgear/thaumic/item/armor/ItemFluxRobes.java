package mortvana.projectfluxgear.thaumic.item.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import mortvana.legacy.clean.core.util.item.ItemArmorRF;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;

public class ItemFluxRobes extends ItemArmorRF implements IRunicArmor, IVisDiscountGear {
	@Override
	public int getRunicCharge(ItemStack stack) {
		return 0;
	}

	@Override
	public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
		return 0;
	}
}
