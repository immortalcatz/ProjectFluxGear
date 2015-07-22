package mortvana.melteddashboard.api.item.tool.wrench;

import net.minecraft.item.ItemStack;

public interface IFluxGearWrench {
	public boolean isPFGWrench(ItemStack wrench);
	public boolean hideFacades(ItemStack wrench);
	public void onLeftClick(); //onSneakLeftClick, onLeftClickPressed, onLeftClickReleased, onSneakLeftClickPressed, onSneakLeftClickReleased, whileLeftClickHeld, whileSneakLeftClickHeld
	public void onRightClick(); //onSneakRightClick, onRightClickPressed, onRightClickReleased, onSneakRightClickPressed, onSneakRightClickReleased, whileRightClickHeld, whileSneakRightClickHeld
}
