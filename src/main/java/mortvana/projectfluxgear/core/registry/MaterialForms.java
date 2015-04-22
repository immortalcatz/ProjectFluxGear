package mortvana.projectfluxgear.core.registry;

public class MaterialForms {
	public static final MaterialForm NULL = new MaterialForm("NULL");

	public static final MaterialForm INGOT = new MaterialForm("INGOT");
	public static final MaterialForm INGOT_PILE = new MaterialForm("INGOTPILE");
	public static final MaterialForm LARGE_INGOT = new MaterialForm("LARGEINGOT");
	public static final MaterialForm CAST_INGOT = new MaterialForm("CASTINGOT");
	public static final MaterialForm CHUNK = new MaterialForm("INGOT");
	public static final MaterialForm NUGGET = new MaterialForm("NUGGET");
	public static final MaterialForm BOLT = new MaterialForm("BOLT");
	public static final MaterialForm NUT = new MaterialForm("NUT");

	public static final MaterialForm LARGE_DUST = new MaterialForm("LARGEDUST");
	public static final MaterialForm DUST = new MaterialForm("DUST");
	public static final MaterialForm HALF_DUST = new MaterialForm("HALFDUST");
	public static final MaterialForm THIRD_DUST = new MaterialForm("THIRDDUST");
	public static final MaterialForm SMALL_DUST = new MaterialForm("SMALLDUST");
	public static final MaterialForm FIFTH_DUST = new MaterialForm("FIFTHDUST");
	public static final MaterialForm SIXTH_DUST = new MaterialForm("SIXTHDUST");
	public static final MaterialForm EIGHTH_DUST = new MaterialForm("EIGHTHDUST");

	public static final MaterialForm ROD = new MaterialForm("ROD");
	public static final MaterialForm SHAFT = new MaterialForm("SHAFT");
	public static final MaterialForm LARGE_SHAFT = new MaterialForm("LARGESHAFT");
	public static final MaterialForm TINY_DUST = new MaterialForm("TINYDUST");
	public static final MaterialForm TENTH_DUST = new MaterialForm("TENTHDUST");
	public static final MaterialForm TWENTIETH_DUST = new MaterialForm("TWENTIETHDUST");
	public static final MaterialForm TWENTY_FIFTH_DUST = new MaterialForm("TWENTYFIFTHDUST");
	public static final MaterialForm HUNDREDTH_DUST = new MaterialForm("HUNDREDTHDUST");


	public static final MaterialForm CASING = new MaterialForm("CASING");
	public static final MaterialForm THIN_PLATE = new MaterialForm("THINPLATE");
	public static final MaterialForm PANEL = new MaterialForm("PANEL");
	public static final MaterialForm PLATE = new MaterialForm("PLATE");
	public static final MaterialForm DOUBLE_PLATE = new MaterialForm("DOUBLEPLATE");
	public static final MaterialForm THICK_PLATE = new MaterialForm("THICKPLATE");
	public static final MaterialForm LARGE_PLATE = new MaterialForm("LARGEPLATE");
	public static final MaterialForm HEAVY_PLATE = new MaterialForm("HEAVYPLATE");

	public static final MaterialForm GEAR = new MaterialForm("GEAR");
	public static final MaterialForm WASHER = new MaterialForm("WASHER");
	public static final MaterialForm FOIL = new MaterialForm("FOIL");
	public static final MaterialForm BALL_BEARINGS = new MaterialForm("BEARINGS");
	public static final MaterialForm SCREW = new MaterialForm("SCREW");

	public static final MaterialForm THIRD_GEM = new MaterialForm("THIRDGEM");
	public static final MaterialForm QUARTER_GEM = new MaterialForm("QUARTERGEM");
	public static final MaterialForm SIXTH_GEM = new MaterialForm("SIXTHGEM");
	public static final MaterialForm EIGHTH_GEM = new MaterialForm("EIGHTHGEM");
	public static final MaterialForm GEM_SHARD = new MaterialForm("GEMSHARD");

	public static final MaterialFormArray INGOTS = new MaterialFormArray(INGOT, INGOT_PILE, LARGE_INGOT, CAST_INGOT, CHUNK, NUGGET, BOLT, NUT);
	public static final MaterialFormArray DUSTS = new MaterialFormArray(LARGE_DUST, DUST, HALF_DUST, THIRD_DUST, SMALL_DUST, FIFTH_DUST, SIXTH_DUST, EIGHTH_DUST);
	public static final MaterialFormArray SHAFTS = new MaterialFormArray(ROD, SHAFT, LARGE_SHAFT, TINY_DUST, TENTH_DUST, TWENTIETH_DUST, TWENTY_FIFTH_DUST, HUNDREDTH_DUST);
	public static final MaterialFormArray PLATES = new MaterialFormArray(CASING, THIN_PLATE, PANEL, PLATE, DOUBLE_PLATE, THICK_PLATE, LARGE_PLATE, HEAVY_PLATE);
	public static final MaterialFormArray GEARS = new MaterialFormArray(GEAR, WASHER, FOIL, BALL_BEARINGS, SCREW, NULL, NULL, NULL);
	public static final MaterialFormArray GEMS = new MaterialFormArray(THIRD_GEM, QUARTER_GEM, SIXTH_GEM, EIGHTH_GEM, GEM_SHARD, NULL, NULL, NULL);

}
