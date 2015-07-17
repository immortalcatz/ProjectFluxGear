package mortvana.projectfluxgear.thaumic.augments;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import thaumcraft.api.aspects.Aspect;

public class ThaumicAugmentTutamen extends ThaumicAugmentBase {
	public ThaumicAugmentTutamen(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);
		if (event.source.getEntity() != null) {
			DamageSource damageSource = new EntityDamageSource("warden", event.entity);
			event.source.getEntity().attackEntityFrom(damageSource, event.ammount / 2);
		}
	}
}
