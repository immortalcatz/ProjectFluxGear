package mortvana.melteddashboard.util.helpers;

import net.minecraft.block.Block;

public class MiscHelper {

	public static boolean isBlockEqual(Block block, Block... blocks) {
		boolean isEqual = false;
		for (Block compare : blocks) {
			if (block == compare) {
				isEqual = true;
			}
		}
		return isEqual;
	}
}
