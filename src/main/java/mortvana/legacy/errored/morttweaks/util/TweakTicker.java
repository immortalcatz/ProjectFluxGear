package mortvana.legacy.errored.morttweaks.util;

import java.util.EnumSet;

import net.minecraft.client.renderer.RenderBlocks;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

//TODO: 1.7.10 Version...
public class TweakTicker implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		RenderBlocks.fancyGrass = true;
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return null;
	}

}
