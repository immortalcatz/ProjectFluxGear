package mortvana.melteddashboard.api.item.tool.wrench;

import net.minecraft.item.ItemStack;

import binnie.extratrees.api.IToolHammer;
import com.carpentersblocks.api.ICarpentersHammer;

/**
 *  An interface to implement on any item that can be used as a Flux Gear compatible wrench, CoFH compatable
 *  wrench/hammer, Applied Energistics compatible wrench, Mekanism compatible wrench, Buildcraft compatible wrench,
 *  EnderIO compatible tool-thingy, Railcraft crowbar, Project:Red screwdriver, BluePower Screwdriver, Flaxbeard's Steam
 *  Power pipe wrench, and Carpenter's Blocks carpenter's hammer.
 *
 *  @author Mortvana
 */
public interface IFluxGearAdvOmniwrench extends IFluxGearOmniwrench, IToolHammer, ICarpentersHammer {

	boolean isBinnieHammer(ItemStack stack);

	boolean isCarpentersHammer(ItemStack stack);
}
