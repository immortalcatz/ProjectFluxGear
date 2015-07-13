package oldcode.morttech;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//TODO: Must ImpliMint

public class ClientTickHandler {
    Minecraft mc = Minecraft.getMinecraft();

    MTControls controlInstance = ClientProxy.controlInstance;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void tickEnd (ClientTickEvent event) {
        MTContent.leafyThing.setGraphicsLevel(Blocks.leaves.field_150121_P);
        if (mc.thePlayer != null && mc.thePlayer.onGround)
            controlInstance.landOnGround();
    }
}