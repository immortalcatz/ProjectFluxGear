package mortvana.melteddashboard.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class MeltedDashboardEvents {

	public static int elapsedTicks;

	@SubscribeEvent
	public void onTick(TickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			elapsedTicks += 1;
		}
	}
}
