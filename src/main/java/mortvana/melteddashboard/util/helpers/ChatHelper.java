package mortvana.melteddashboard.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;

public class ChatHelper {

	public static IChatComponent addChatMessage(EntityPlayer player, String message) {
		IChatComponent component = IChatComponent.Serializer.jsonToComponent(message);
		player.addChatMessage(component);
		return component;
	}
}
