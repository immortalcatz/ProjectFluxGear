package mortvana.melteddashboard.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class FluxGearDamageSources {

	public static DamageSource wardenic = (new DamageSource("wardenic")).setDamageBypassesArmor().setMagicDamage();

	public static DamageSource entityWardenic(Entity entity) {
		return new EntityDamageSource("wardenic", entity).setDamageBypassesArmor().setMagicDamage();
	}

}
