package mortvana.legacy.dependent.firstdegree.fluxgearaddons.network;

import mortvana.legacy.clean.fluxgearaddons.network.ParticleGenPacket;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import mortvana.legacy.errored.fluxgearaddons.block.tileentity.TileParticleGenerator;

public class ParticleGenPacketHandler implements IMessageHandler<ParticleGenPacket, IMessage> {
	public ParticleGenPacketHandler() {
	}

	public IMessage onMessage(ParticleGenPacket message, MessageContext ctx) {
		TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.tileX, message.tileY, message.tileZ);
		TileParticleGenerator gen = tile != null && tile instanceof TileParticleGenerator ? (TileParticleGenerator) tile : null;
		if (gen != null) {
			switch(message.buttonId) {
				case 0:
					gen.red = message.value;
					break;
				case 1:
					gen.green = message.value;
					break;
				case 2:
					gen.blue = message.value;
					break;
				case 3:
					gen.motion_x = (float)message.value / 1000.0F;
					break;
				case 4:
					gen.motion_y = (float)message.value / 1000.0F;
					break;
				case 5:
					gen.motion_z = (float)message.value / 1000.0F;
					break;
				case 6:
					gen.red = message.value;
					break;
				case 7:
					gen.green = message.value;
					break;
				case 8:
					gen.blue = message.value;
					break;
				case 9:
					gen.motion_x = (float)message.value / 1000.0F;
					break;
				case 10:
					gen.motion_y = (float)message.value / 1000.0F;
					break;
				case 11:
					gen.motion_z = (float)message.value / 1000.0F;
					break;
				case 12:
					gen.random_red = message.value;
					break;
				case 13:
					gen.random_green = message.value;
					break;
				case 14:
					gen.random_blue = message.value;
					break;
				case 15:
					gen.random_motion_x = (float)message.value / 1000.0F;
					break;
				case 16:
					gen.random_motion_y = (float)message.value / 1000.0F;
					break;
				case 17:
					gen.random_motion_z = (float)message.value / 1000.0F;
					break;
				case 18:
					gen.random_red = message.value;
					break;
				case 19:
					gen.random_green = message.value;
					break;
				case 20:
					gen.random_blue = message.value;
					break;
				case 21:
					gen.random_motion_x = (float)message.value / 1000.0F;
					break;
				case 22:
					gen.random_motion_y = (float)message.value / 1000.0F;
					break;
				case 23:
					gen.random_motion_z = (float)message.value / 1000.0F;
					break;
				case 24:
					gen.life = message.value;
					break;
				case 25:
					gen.life = message.value;
					break;
				case 26:
					gen.random_life = message.value;
					break;
				case 27:
					gen.random_life = message.value;
					break;
				case 28:
					gen.scale = (float)message.value / 100.0F;
					break;
				case 29:
					gen.scale = (float)message.value / 100.0F;
					break;
				case 30:
					gen.random_scale = (float)message.value / 100.0F;
					break;
				case 31:
					gen.random_scale = (float)message.value / 100.0F;
					break;
				case 32:
					gen.page = message.value;
					break;
				case 33:
					gen.page = message.value;
					break;
				case 34:
					gen.spawn_x = (float)message.value / 100.0F;
					break;
				case 35:
					gen.spawn_x = (float)message.value / 100.0F;
					break;
				case 36:
					gen.random_spawn_x = (float)message.value / 100.0F;
					break;
				case 37:
					gen.random_spawn_x = (float)message.value / 100.0F;
					break;
				case 38:
					gen.spawn_y = (float)message.value / 100.0F;
					break;
				case 39:
					gen.spawn_y = (float)message.value / 100.0F;
					break;
				case 40:
					gen.random_spawn_y = (float)message.value / 100.0F;
					break;
				case 41:
					gen.random_spawn_y = (float)message.value / 100.0F;
					break;
				case 42:
					gen.spawn_z = (float)message.value / 100.0F;
					break;
				case 43:
					gen.spawn_z = (float)message.value / 100.0F;
					break;
				case 44:
					gen.random_spawn_z = (float)message.value / 100.0F;
					break;
				case 45:
					gen.random_spawn_z = (float)message.value / 100.0F;
					break;
				case 46:
					gen.spawn_rate = message.value;
					break;
				case 47:
					gen.spawn_rate = message.value;
					break;
				case 48:
					gen.fade = message.value;
					break;
				case 49:
					gen.fade = message.value;
					break;
				case 50:
					gen.collide = message.value != 0;
					break;
				case 51:
					gen.selected_particle = message.value;
					break;
				case 52:
					gen.gravity = (float)message.value / 1000.0F;
					break;
				case 53:
					gen.gravity = (float)message.value / 1000.0F;
					break;
				case 54:
					gen.page = message.value;
					break;
				case 55:
					gen.page = message.value;
			}

			ctx.getServerHandler().playerEntity.worldObj.markBlockForUpdate(message.tileX, message.tileY, message.tileZ);
		}

		return null;
	}
}