package mortvana.melteddashboard.api.item.tool.wrench;

import cofh.api.item.IToolHammer;
import appeng.api.implementations.items.IAEWrench;
import buildcraft.api.tools.IToolWrench;
import crazypants.enderio.api.tool.ITool;
import mekanism.api.IMekWrench;

/**
 *  An interface to implement on any item that can be used as a Flux Gear compatible wrench, CoFH compatable
 *  wrench/hammer, Applied Energistics compatible wrench, Mekanism compatible wrench, Buildcraft compatible wrench,
 *  and EnderIO compatible tool-thingy.
 *
 *  @author Mortvana
 */
public interface IFluxGearMultiwrench extends IAEWrench, IMekWrench, IToolHammer, IToolWrench, ITool, IFluxGearWrench {}
