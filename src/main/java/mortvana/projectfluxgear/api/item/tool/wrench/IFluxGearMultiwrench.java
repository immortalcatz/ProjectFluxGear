package mortvana.projectfluxgear.api.item.tool.wrench;

import cofh.api.item.IToolHammer;
import appeng.api.implementations.items.IAEWrench;
import buildcraft.api.tools.IToolWrench;
import crazypants.enderio.api.tool.ITool;
import mekanism.api.IMekWrench;

public interface IFluxGearMultiwrench extends IAEWrench, IMekWrench, IToolHammer, IToolWrench, ITool, IFluxGearWrench {}
