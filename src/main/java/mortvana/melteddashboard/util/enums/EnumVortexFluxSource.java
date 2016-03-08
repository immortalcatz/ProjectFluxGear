package mortvana.melteddashboard.util.enums;

public enum EnumVortexFluxSource {
	INTERNAL(false, 1),
	GAUNTLET(false, 1),
	FLUX    (true,  1000),
	HUON    (true,  0),
	ARTRON  (true,  0),
	VIS     (true,  0),
	ESSENTIA(true,  0),
	BLOOD   (true,  0),
	MANA    (true,  0),
	THERMAL (true,  0),
	EU      (true,  0),
	SHOCK   (false, 0),
	LIGHT   (false, 0);

    boolean consuming;
    int conversion;

	EnumVortexFluxSource(boolean consuming, int conversion) {
        this.consuming = consuming;
        this.conversion = conversion;
	}

    public boolean consumesPower() {
        return consuming;
    }

    public int getPowerRatio() {
        return conversion;
    }
}
