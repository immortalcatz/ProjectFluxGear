package mortvana.melteddashboard.registry.material;

public enum EnumMaterialType {
	MALLEABLE_METAL("MALLEABLE_METAL", EnumMaterialForm.BLOCK, EnumMaterialForm.INGOT, EnumMaterialForm.DUST, EnumMaterialForm.PLATE, EnumMaterialForm.NUTSNBOLTS, EnumMaterialForm.SHAFT, EnumMaterialForm.GEAR, EnumMaterialForm.FOIL, EnumMaterialForm.WASHER, EnumMaterialForm.BEARING),
	WORKABLE_METAL("WORKABLE_METAL"), //Steel, etc.
	INGOT_METAL("INGOT_METAL", EnumMaterialForm.BLOCK, EnumMaterialForm.INGOT, EnumMaterialForm.DUST), //Tellurium, etc.
	GEM("GEM", EnumMaterialForm.BLOCK, EnumMaterialForm.GEM, EnumMaterialForm.DUST), //Dioptase, etc.
	DUST("DUST"), //Rust, etc.
	DUMMY("REMOVED"); //A dummy for future/undecided types;

	// TODO: More types

	public String internalName;
	public EnumMaterialForm[] associatedForms;
	EnumMaterialType(String name, EnumMaterialForm... forms) {
		//Don't register multiple things with the same name
		internalName = name;
		associatedForms = forms;
	}

	public EnumMaterialForm[] getAssociatedTypes(EnumMaterialType type) {
		return type.associatedForms;
	}

	public String getNameFromValue(EnumMaterialType type) {
		return type.internalName;
	}

	// TODO: Make this a thing
	//public EnumMaterialType getValueFromName(String name) {
	//}

	public boolean containsForm(EnumMaterialForm form) {
		for (EnumMaterialForm associatedForm : associatedForms) {
			if (associatedForm.equals(form)) {
				return true;
			}
		}
		return false;
	}
}