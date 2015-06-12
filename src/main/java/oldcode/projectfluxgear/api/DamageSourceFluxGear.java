package oldcode.projectfluxgear.api;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceFluxGear extends EntityDamageSource {

	public static DamageSource wardenic = (new DamageSource("wardenic")).setDamageBypassesArmor().setMagicDamage();

	public DamageSourceFluxGear(String name, Entity entity) {

		super(name, entity);
		setDamageBypassesArmor();
		setMagicDamage();
	}
}
