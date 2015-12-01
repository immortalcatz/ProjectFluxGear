package mortvana.legacy.dependent.firstdegree.crystalclimate.util;

import mortvana.melteddashboard.inventory.FluxGearCreativeTab;

import mortvana.legacy.errored.crystalclimate.common.CrystalClimate;

public class TabCrystal extends FluxGearCreativeTab {

	public TabCrystal(String label) {
		super(label, label, CrystalClimate.essenceStack);
	}

}