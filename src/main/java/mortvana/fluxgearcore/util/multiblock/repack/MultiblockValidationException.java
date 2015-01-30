package mortvana.fluxgearcore.util.multiblock.repack;

/**
 * An exception thrown when trying to validate a multiblock. Requires a string describing why the multiblock
 * could not assemble.
 */
public class MultiblockValidationException extends Exception {
	public MultiblockValidationException(String reason) {
		super(reason);
	}
}