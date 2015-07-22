package mortvana.melteddashboard.registry;

public enum EnumMaterialForm {
	BLOCK("BLOCK", EnumForms.BLOCK),
	INGOT("INGOT", EnumForms.INGOT, EnumForms.CHUNK, EnumForms.NUGGET, EnumForms.CAST_INGOT, EnumForms.INGOT_PILE, EnumForms.LARGE_INGOT),
	DUST("DUST", EnumForms.LARGE_DUST, EnumForms.DUST, EnumForms.HALF_DUST, EnumForms.THIRD_DUST, EnumForms.SMALL_DUST, EnumForms.FIFTH_DUST, EnumForms.SIXTH_DUST, EnumForms.EIGHTH_DUST, EnumForms.TINY_DUST, EnumForms.TENTH_DUST, EnumForms.SIXTEENTH_DUST, EnumForms.TWENTIETH_DUST, EnumForms.TWENTY_FIFTH_DUST, EnumForms.HUNDREDTH_DUST),
	GEM("GEM", EnumForms.INGOT, EnumForms.CHUNK, EnumForms.NUGGET),
	PLATE("PLATE", EnumForms.CASING, EnumForms.THIN_PLATE, EnumForms.PANEL, EnumForms.PLATE, EnumForms.DOUBLE_PLATE, EnumForms.THICK_PLATE, EnumForms.LARGE_PLATE, EnumForms.HEAVY_PLATE),
	NUTSNBOLTS("NUTSNBOLTS", EnumForms.NUT, EnumForms.BOLT, EnumForms.SCREW),
	SHAFT("SHAFT", EnumForms.ROD, EnumForms.SHAFT, EnumForms.LARGE_SHAFT),
	GEAR("GEAR", EnumForms.GEAR, EnumForms.COG),
	FOIL("FOIL", EnumForms.FOIL),
	WASHER("WASHER", EnumForms.WASHER, EnumForms.BUSHING),
	BEARING("BALL_BEARING", EnumForms.BALL_BEARINGS);

	public String name;
	public EnumForms[] subForms;
	EnumMaterialForm(String name, EnumForms... subForms) {
		this.name = name;
		this.subForms = subForms;
	}
}