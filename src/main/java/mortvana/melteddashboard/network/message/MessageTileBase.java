package mortvana.melteddashboard.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import mortvana.melteddashboard.block.metadata.TileEntityMetadata;
import mortvana.melteddashboard.block.tile.FluxGearTileEntity;
import mortvana.melteddashboard.util.helpers.WorldHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.UUID;

public class MessageTileBase extends FluxGearMessageBase implements IMessageHandler<MessageTileBase, IMessage> {

    public int x, z;
    public short y, metadata;
    public byte orientation, state;
    public String name;
    public UUID owner;

    public MessageTileBase() {}

    public MessageTileBase(FluxGearTileEntity tile) {
        x = tile.xCoord;
        y = (short) tile.yCoord;
        z = tile.zCoord;
        metadata = tile.getMetadata();
        orientation = tile.getOrientaion().ordianal();
        state = tile.getState();
        name = tile.getCustomName();
        owner = tile.getOwnerUUID();
    }

    @Override
    public void toBytes(ByteBuf target) {
        target.writeInt(x);
        target.writeShort(y);
        target.writeInt(z);
        target.writeShort(metadata);
        target.writeByte(orientation);
        target.writeByte(state);
        target.writeInt(name.length());
        target.writeBytes(name.getBytes());
        if (owner != null) {
            target.writeBoolean(true);
            target.writeLong(owner.getMostSignificantBits());
            target.writeLong(owner.getLeastSignificantBits());
        } else {
            target.writeBoolean(false);
        }
    }

    @Override
    public void fromBytes(ByteBuf source) {
        x = source.readInt();
        y = source.readShort();
        z = source.readInt();
        metadata = source.readShort();
        orientation = source.readByte();
        state = source.readByte();
        int length = source.readInt();
        name =  new String(source.readBytes(length).array());
        owner = source.readBoolean() ? new UUID(source.readLong(), source.readLong()) : null;
    }

    @Override
    public IMessage onMessage(MessageTileBase message, MessageContext ctx) {
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
