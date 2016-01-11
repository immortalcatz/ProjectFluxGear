package mortvana.legacy.clean.thaumicrevelations.util;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import mortvana.melteddashboard.util.helpers.science.MathHelper;

import mortvana.projectfluxgear.api.item.armor.IWardenicArmor;
import mortvana.projectfluxgear.api.item.IWardenicEquipment;

public class WardenicChargeEventHandler {

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new WardenicChargeEventHandler());
	}

	@SubscribeEvent
	public void onTick(LivingEvent.LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			for (int i = 0; i < 5; i++) {
				if (player.getEquipmentInSlot(i) != null && (player.getEquipmentInSlot(i).getItem() instanceof IWardenicEquipment) && (player.getEquipmentInSlot(i).getMetadata() != player.getEquipmentInSlot(i).getMaxDurability()) && (MathHelper.RANDOM.nextInt(50) == 50)) {
					player.getEquipmentInSlot(i).setMetadata(player.getEquipmentInSlot(i).getMetadata() - 1);
				}
			}
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			for (int i = 1; i < 5; i++) {
				if (player.getEquipmentInSlot(i) != null && (player.getEquipmentInSlot(i).getItem() instanceof IWardenicArmor) && (player.getEquipmentInSlot(i).getMetadata() != player.getEquipmentInSlot(i).getMaxDurability())) {
					player.getEquipmentInSlot(i).setMetadata(player.getEquipmentInSlot(i).getMetadata() + 1);
					WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(i)).onAttacked(event);
				}
			}
		}
	}
}