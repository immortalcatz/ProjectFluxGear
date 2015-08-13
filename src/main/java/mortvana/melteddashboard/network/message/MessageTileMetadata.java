package mortvana.melteddashboard.network.message;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import io.netty.buffer.ByteBuf;
import mortvana.melteddashboard.block.metadata.TileEntityMetadata;
import mortvana.melteddashboard.util.helpers.WorldHelper;

public class MessageTileMetadata extends MessageTileCoords implements IMessageHandler<MessageTileMetadata, IMessage> {

	public int metadata;

	public MessageTileMetadata() { /* Singleton */ }

	public MessageTileMetadata(int x, int y, int z, int metadata) {
		super(x, y, z);
		this.metadata = metadata;
	}

	public MessageTileMetadata(TileEntity tile, int metadata) {
		super(tile);
		this.metadata = metadata;
	}

	public MessageTileMetadata(TileEntityMetadata tile) {
		super(tile);
		metadata = tile.getTileMetadata();
	}

	@Override
	public void toBytes(ByteBuf target) {
		super.toBytes(target);
		target.writeShort(metadata);
	}

	@Override
	public void fromBytes(ByteBuf source) {
		super.fromBytes(source);
		metadata = source.readUnsignedShort();
	}

	@Override
	public IMessage onMessage(MessageTileMetadata message, MessageContext ctx) {
		World world = WorldHelper.getWorld();
		TileEntityMetadata tile = (TileEntityMetadata) world.getTileEntity(x, y, z);
		if (tile == null) {
			tile = new TileEntityMetadata();
			world.setTileEntity(x, y, z, tile);
		}
		tile.setTileMetadata(metadata);
		world.markBlockForUpdate(x, y, z);

		return null;
	}
}
