package mortvana.melteddashboard.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChatHelper {

	public static IChatComponent addChatMessage(EntityPlayer player, String message) {
		IChatComponent component = IChatComponent.Serializer.jsonToComponent(message);
		player.addChatMessage(component);
		return component;
	}

    public static ChatComponentText addTextMessage(EntityPlayer player, String message) {
        ChatComponentText text = new ChatComponentText(message);
        player.addChatMessage(text);
        return text;
    }
}
