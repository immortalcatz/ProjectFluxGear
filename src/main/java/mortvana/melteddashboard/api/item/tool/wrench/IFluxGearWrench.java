package mortvana.melteddashboard.api.item.tool.wrench;

import net.minecraft.item.ItemStack;

/**
 *  An interface to implement on any item that can be used as a Flux Gear compatible wrench.
 *
 *  @author Mortvana
 */
public interface IFluxGearWrench {

	/**
	 *  Returns true if the given itemstack (which SHOULD be of the item implementing this interface) is a Flux Gear
	 *  compatible wrench. Provided so that your wrench (or other tool) can differentiate via metadata and NBT data.
	 *
	 *  @param wrench - The ItemStack of the wrench (or other tool).
	 *  @return - Whether the item is usable as a Flux Gear compatable wrench.
	 */
	public boolean isFluxGearWrench(ItemStack wrench);

	/**
	 *  Returns true if the given itemstack (which SHOULD be of the item implementing this interface) hides facades.
	 *  Provided so that your wrench (or other tool) can differentiate via metadata and NBT data.
	 *
	 *  @param wrench - The ItemStack of the wrench (or other tool).
	 *  @return - Does this item hide facades on cables and such.
	 */
	public boolean hideFacades(ItemStack wrench);

	//TODO:
	//public void onLeftClick(); //onSneakLeftClick, onLeftClickPressed, onLeftClickReleased, onSneakLeftClickPressed, onSneakLeftClickReleased, whileLeftClickHeld, whileSneakLeftClickHeld
	//public void onRightClick(); //onSneakRightClick, onRightClickPressed, onRightClickReleased, onSneakRightClickPressed, onSneakRightClickReleased, whileRightClickHeld, whileSneakRightClickHeld
}
