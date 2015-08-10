package mortvana.melteddashboard.network.message.old;

import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import io.netty.buffer.ByteBuf;
import mortvana.melteddashboard.block.metadata.TileEntityMetadata;
import mortvana.melteddashboard.util.helpers.WorldHelper;

public class FluxGearMessageMetadata extends FluxGearMessageTile {

	public int metadata;

	public FluxGearMessageMetadata() { /* Singleton */ }

	public FluxGearMessageMetadata(int x, int y, int z, int metadata) {
		super(x, y, z);
		this.metadata = metadata;
	}

	@Override
	public void encodeTo(ByteBuf target) {
		super.encodeTo(target);
		target.writeShort(metadata);
	}

	@Override
	public void decodeFrom(ByteBuf source) {
		super.decodeFrom(source);
		metadata = source.readUnsignedShort();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handle() {
		World world = WorldHelper.getWorld();
		TileEntityMetadata tile = (TileEntityMetadata) world.getTileEntity(x, y, z);
		if (tile == null) {
			tile = new TileEntityMetadata();
			world.setTileEntity(x, y, z, tile);
		}
		tile.setTileMetadata(metadata);
		world.markBlockForUpdate(x, y, z);
	}
}