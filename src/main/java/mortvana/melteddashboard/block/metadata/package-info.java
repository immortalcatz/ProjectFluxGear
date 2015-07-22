package mortvana.melteddashboard.block.metadata;

   /**
	*   xMeta: A super-special-awesome way of taking up less block IDs!
	*
	*   Used in many mods, "xMeta", better known as "Extended Metadata", is a way of shoving more blocks in one ID.
	*   Due to the NBT syncing, these blocks must be TileEntities, so it is not recommended to have large amounts
	*   in the world, for CPU conservation. However they are only a bit less laggy than if you made the same area out
	*   of Furnaces, so not much, unless you are playing Minecraft on a potato or have 1,000's of 'em.
	*
	*   Extended Metadata is used by a few large mods, such as Binnie's Mods, GeoStrata, Red Power 2 (back in 1.4.7),
	*   Project:Red, Metallurgy Bees (back in 1.6.4), and *shudder* GregTech.
	*
	*   I use xMeta for Storage Blocks and Crops (I was also thinking to use it for Machines, but it might be better to
	*   use Vazkii's subtiles). You can use it for whatever though! (I recommend NOT using it for anything which
	*   generates in the world in large amounts, such as ores, for CPU reasons, as each TileEntity is its own Object,
	*   unlike the millions of Stone blocks loaded at once, which are all the same Object.)
	*/