package mortvana.melteddashboard.util.data;

import net.minecraft.util.IIcon;

public class SidedIIcon {

	public IIcon[] sidedIcons = new IIcon[6];
	public EnumSidedness sidedness;

	public IIcon iconOnSide(int side) {
		return sidedIcons[sidedness.getIconIndex(side)];
	}

	public enum EnumSidedness {
		STANDARD(0, 0, 0, 0, 0, 0);

		public int[] iconIndexes; //Technically a correct spelling.

		//This will error if you supply a int greater than 5...
		EnumSidedness(int down, int up, int north, int south, int west, int east) {
			iconIndexes = new int[] { down, up, north, south, west, east };
		}

		public int getIconIndex(int side) {
			return iconIndexes[side];
		}
	}
}
