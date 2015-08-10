package mortvana.melteddashboard.util.random.weighted;

import net.minecraft.nbt.NBTBase;

public class WeightedNBTTag extends WeightedEntry {

	public final NBTBase NBT;

	public WeightedNBTTag(NBTBase nbt, int weight) {
		super(weight);
		NBT = nbt;
	}

	public NBTBase getNBTTag() {
		return NBT;
	}
}
