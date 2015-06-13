package mortvana.projectfluxgear.util.network;

import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import io.netty.buffer.ByteBuf;
import mortvana.projectfluxgear.util.block.metadata.TileEntityMetadata;
import mortvana.projectfluxgear.util.helpers.WorldHelper;

public abstract class FluxGearMessageTile extends FluxGearMessage {

	public int x, y, z;

	public FluxGearMessageTile() { /* Singleton */ }

	public FluxGearMessageTile(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void encodeTo(ByteBuf target) {
		target.writeInt(x);
		target.writeShort(y);
		target.writeInt(z);
	}

	public void decodeFrom(ByteBuf source) {
		x = source.readInt();
		y = source.readShort();
		z = source.readInt();
	}

	@SideOnly(Side.CLIENT)
	public abstract void handle();

	public static class FluxGearMessageMetadata extends FluxGearMessageTile {

		public int metadata;

		public FluxGearMessageMetadata() { /* Singleton */ }

		public FluxGearMessageMetadata(int x, int y, int z, int metadata) {
			super(x, y, z);
			this.metadata = metadata;
		}

		public void encodeTo(ByteBuf target) {
			super.encodeTo(target);
			target.writeShort(metadata);
		}

		public void decodeFrom(ByteBuf source) {
			super.decodeFrom(source);
			metadata = source.readUnsignedShort();
		}

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
}
