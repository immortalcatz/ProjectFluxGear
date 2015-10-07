package mortvana.legacy.errored.thaumicrevelations.client;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.client.gui.GuiResearchRecipe;
//import vazkii.tinkerer.client.core.handler.kami.ToolModeHUDHandler;
//import vazkii.tinkerer.client.gui.GuiResearchPeripheral;

public class ClientTickHandler implements ITickHandler {
	public static int elapsedTicks;

	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		Minecraft mc = Minecraft.getMinecraft();
		//if ((mc.currentScreen != null) && ((mc.currentScreen instanceof GuiResearchRecipe)) /* &&(!(mc.currentScreen instanceof GuiResearchPeripheral))*/) {
			//ResearchItem research = ReflectionHelper.getPrivateValue(GuiResearchRecipe.class, (GuiResearchRecipe)mc.currentScreen, 9);
			//if ((research.key.equals("PERIPHERALS")) || (research.key.equals("GOLEM_CONNECTOR"))) {
			//	mc.func_71373_a(new GuiResearchPeripheral(research));
			//}
		//}
		//ToolModeHUDHandler.clientTick();

		elapsedTicks += 1;
	}

	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	public String getLabel() {
		return "Thaumic Tinkerer";
	}
}