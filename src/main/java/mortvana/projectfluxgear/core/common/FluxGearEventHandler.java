package mortvana.projectfluxgear.core.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import mortvana.projectfluxgear.core.config.MortTweaksConfig;
import mortvana.projectfluxgear.thaumic.enchant.EnchantmentStabilizing;

public class FluxGearEventHandler {

    @SubscribeEvent
    public void livingTick(LivingUpdateEvent event) {
        int t;
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;

            if (!event.entity.worldObj.isRemote) {
                if (player.ticksExisted % 40 == 0) {
                    t = 0;

                    while (true) {
                        InventoryPlayer inventory = player.inventory;
                        if (t >= InventoryPlayer.getHotbarSize()) {
                            for (t = 0; t < 4; t++) {
                                if (inventory.armorItemInSlot(t) != null) {
                                    EnchantmentStabilizing.canDoRepair(inventory.armorItemInSlot(t), player);
                                }
                            }
                            break;
                        }

                        if (inventory.mainInventory[t] != null) {
                            EnchantmentStabilizing.canDoRepair(inventory.mainInventory[t], player);
                        }

                        ++t;
                    }
                }
            }
        }
    }

	@SubscribeEvent
	public void killExpOrbs(EntityJoinWorldEvent event) {
		if (MortTweaksConfig.disableExp && event.entity instanceof EntityXPOrb) {
			event.setCanceled(true);
		}
	}
}
