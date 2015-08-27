package mortvana.legacy.crystaltweaks.util;

import net.minecraft.entity.item.EntityXPOrb;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import mortvana.legacy.errored.morttweaks.common.MortTweaks;

public class ExpOrbListener {

	@SubscribeEvent
	public void killExpOrbs(EntityJoinWorldEvent event) {
		if (MortTweaks.disableExp) {
			if (event.entity instanceof EntityXPOrb) {
				event.setCanceled(true);
			}
		}
	}
}