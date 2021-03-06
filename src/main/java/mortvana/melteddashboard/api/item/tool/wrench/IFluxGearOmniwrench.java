package mortvana.melteddashboard.api.item.tool.wrench;

import net.minecraft.item.ItemStack;

import flaxbeard.steamcraft.api.IPipeWrench;
import mods.railcraft.api.core.items.IToolCrowbar;
import mrtjp.projectred.api.IScrewdriver;

/**
 *  An interface to implement on any item that can be used as a Flux Gear compatible wrench, CoFH compatable
 *  wrench/hammer, Applied Energistics compatible wrench, Mekanism compatible wrench, Buildcraft compatible wrench,
 *  EnderIO compatible tool-thingy, Railcraft crowbar, Project:Red screwdriver, BluePower Screwdriver, and Flaxbeard's
 *  Steam Power pipe wrench.
 *
 *  @author Mortvana
 */
public interface IFluxGearOmniwrench extends IFluxGearMultiwrench, IToolCrowbar, IScrewdriver, IPipeWrench, com.bluepowermod.api.misc.IScrewdriver {

	boolean isRCCrowbar(ItemStack stack);

	boolean isPRScrewdriver(ItemStack stack);

	boolean isPipeWrench(ItemStack stack);

	boolean isBPScrewdriver(ItemStack stack);
}
