package mortvana.melteddashboard.util.enums;

public enum EnumNBTType {
	END,
	BYTE,
	SHORT,
	INT,
	LONG,
	FLOAT,
	DOUBLE,
	BYTE_ARRAY,
	STRING,
	LIST,
	COMPOUND,
	INT_ARRAY,
	NULL;

	public static byte getValue(EnumNBTType nbtType) {
		return (byte) nbtType.ordinal();
	}

	public static EnumNBTType getValue(byte ordinal) {
		return values()[ordinal];
	}

	public static EnumNBTType getValue(int ordinal) {
		return getValue((byte) ordinal);
	}
}
