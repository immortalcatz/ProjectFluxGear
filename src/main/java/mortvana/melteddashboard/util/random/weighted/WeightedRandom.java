package mortvana.melteddashboard.util.random.weighted;

import java.util.Collection;
import java.util.Random;

/**
 *  An extended version of the Minecraft-native WeightedRandom, for use almost anywhere.
 *
 *  @author Mortvana
 */

public class WeightedRandom {

	/**
	 *  Returns the total weight of all the entries in a collection.
	 */
	public static int getTotalWeight(Collection<WeightedEntry> entries) {
		int i = 0;
		for (WeightedEntry entry : entries) {
			i += entry.getWeight();
		}
		return i;
	}

	/**
	 *  Returns the total weight of all entries in an array.
	 */
	public static int getTotalWeight(WeightedEntry[] entries) {
		int i = 0;
		for (WeightedEntry entry : entries) {
			i += entry.getWeight();
		}
		return i;
	}

	/**
	 *  Returns a random entry from the collection of entries, with a total weight value.
	 */
	public static WeightedEntry getRandomEntry(Random random, Collection<WeightedEntry> entries, int weight) {
		return weight > 0 ? getEntry(entries, random.nextInt(weight)) : null;
	}


	/**
	 *  Returns a random entry from the collection of entries, with a total weight value.
	 */
	public static WeightedEntry getRandomEntry(Random random, WeightedEntry[] entries, int weight) {
		return weight > 0 ? getEntry(entries, random.nextInt(weight)) : null;
	}

	/**
	 *  Returns a random choice from the input entries using the total weight.
	 */
	public static WeightedEntry getRandomEntry(Random random, Collection<WeightedEntry> entries) {
		return getRandomEntry(random, entries, getTotalWeight(entries));
	}

	/**
	 *  Returns a random choice from the input entries using the total weight.
	 */
	public static WeightedEntry getRandomEntry(Random random, WeightedEntry[] entries) {
		return getRandomEntry(random, entries, getTotalWeight(entries));
	}

	/**
	 *  Use this for custom random implementations.
	 *  You are responsible for making sure the weight is under the total weight of the entries.
	 */
	public static WeightedEntry getEntry(Collection<WeightedEntry> entries, int weight) {
		for (WeightedEntry entry : entries) {
			weight -= entry.getWeight();
			if (weight < 0) {
				return entry;
			}
		}
		return null;
	}

	/**
	 *  Use this for custom random implementations.
	 *  You are responsible for making sure the weight is under the total weight of the entries.
	 */
	public static WeightedEntry getEntry(WeightedEntry[] entries, int weight) {
		for (WeightedEntry entry : entries) {
			weight -= entry.getWeight();
			if (weight < 0) {
				return entry;
			}
		}
		return null;
	}
}
