package oldcode.legacy.util;

//TODO

import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;

import oldcode.legacy.util.handlers.MTKeyHandler;

public class MTControls extends MTKeyHandler {
    public static final String keybindCategory = "key.morttech.category";
    public static KeyBinding randomKey = new KeyBinding("key.lemmings", 24, keybindCategory);
    static KeyBinding jumpKey;
    static KeyBinding invKey;
    static Minecraft mc;

    boolean jumping;
    int midairJumps = 0;
    boolean climbing = false;
    boolean onGround = false;
    boolean onStilts = false;

    int currentTab = 1;

    public MTControls()
    {
        super(new KeyBinding[] { randomKey }, new boolean[] { false, false }, getVanillaKeyBindings(), new boolean[] { false, false });
    }

    private static KeyBinding[] getVanillaKeyBindings ()
    {
        mc = Minecraft.getMinecraft();
        jumpKey = mc.gameSettings.keyBindJump;
        invKey = mc.gameSettings.keyBindInventory;
        return new KeyBinding[] { jumpKey, invKey };
    }

    @Override
    public void keyDown (TickEvent.Type types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
    {
        if (tickEnd && mc.theWorld != null)
        {
            if (kb == armorKey && mc.currentScreen == null) // Extended Armor
            {
                openArmorGui();// mc.thePlayer.username);
            }
            if (kb == invKey && mc.currentScreen != null && mc.currentScreen.getClass() == GuiInventory.class)// &&
            // !mc.playerController.isInCreativeMode())
            {
                TabRegistry.addTabsToInventory((GuiContainer) mc.currentScreen);
            }
            if (kb == refreshCapes && mc.currentScreen == null)
            {
                EventCloakRender.instance.refreshCapes();
            }
            if (kb == jumpKey) // Double jump
            {
                if (mc.thePlayer.capabilities.isCreativeMode)
                    return;

                if (jumping && midairJumps > 0)
                {
                    mc.thePlayer.motionY = 0.42D;
                    mc.thePlayer.fallDistance = 0;

                    if (mc.thePlayer.isPotionActive(Potion.jump))
                    {
                        mc.thePlayer.motionY += (double) ((float) (mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
                    }

                    midairJumps--;
                    resetFallDamage();
                }

                if (!jumping)
                    jumping = mc.thePlayer.isAirBorne;
            }
        }

    }

    @Override
    public void keyUp (TickEvent.Type types, KeyBinding kb, boolean tickEnd)
    {

    }

    public void landOnGround ()
    {
        midairJumps = 0;
        jumping = false;
    }

    public void resetControls ()
    {
        midairJumps = 0;
        jumping = false;
        climbing = false;
        onGround = false;
        onStilts = false;
    }

    void resetFallDamage()
    {
        AbstractPacket packet = new PacketDoubleJump();
        updateServer(packet);
    }


    public static void openArmorGui ()
    {
        AbstractPacket packet = new PacketExtendedInventory(TProxyCommon.armorGuiID);
        updateServer(packet);
    }

    public static void openKnapsackGui ()
    {
        AbstractPacket packet = new PacketExtendedInventory(TProxyCommon.knapsackGuiID);
        updateServer(packet);
    }

    static void updateServer (AbstractPacket abstractPacket)
    {
        TConstruct.packetPipeline.sendToServer(abstractPacket);
    }
}