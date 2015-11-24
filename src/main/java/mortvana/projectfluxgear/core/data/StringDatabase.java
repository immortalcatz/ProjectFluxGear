package mortvana.projectfluxgear.core.data;

import static mortvana.melteddashboard.util.helpers.StringHelper.*;

public class StringDatabase {

	/* Tooltips */
	public static final String TOOLTIP = "info.fluxgear.tooltip.";

	public static final String SHIFT_FOR_DETAILS = LIGHT_GRAY + TOOLTIP + "hold" + " " + TEAL + ITALIC + localize(TOOLTIP + "shift") + " " + END + LIGHT_GRAY + localize(TOOLTIP + "details") + END;

	/* NBT Tags */
	// Does the item interact with Blood Magic (generally used for charging with LP)
	public static final String SANGUINE = "PFG_IS_SANGUINE";
}
