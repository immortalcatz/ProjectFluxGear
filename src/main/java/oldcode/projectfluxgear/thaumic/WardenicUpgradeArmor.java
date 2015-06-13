package oldcode.projectfluxgear.thaumic;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import oldcode.projectfluxgear.api.DamageSourceFluxGear;
import thaumcraft.api.aspects.Aspect;

public class WardenicUpgradeArmor extends WardenicUpgrade {

	public WardenicUpgradeArmor(Aspect aspect) {super(aspect);}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);
		if (event.source.getEntity() != null) {
			DamageSource damageSource = new DamageSourceFluxGear("warden", event.entity);
			event.source.getEntity().attackEntityFrom(damageSource, event.ammount / 2);
		}
	}
}