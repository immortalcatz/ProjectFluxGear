package oldcode.projectfluxgear.thaumic.util.wardenic;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import oldcode.projectfluxgear.thaumic.item.tool.ItemWardenicBlade;
import mortvana.projectfluxgear.thaumic.util.ItemWardenArmor;

public class WardenicChargeEvents {

	private Random random = new Random();

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new WardenicChargeEvents());
	}

	@SubscribeEvent
	public void onTick(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			for (int i = 0; i < 5; i++) {
				if (player.getEquipmentInSlot(i) != null && (player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor || player.getEquipmentInSlot(i).getItem() instanceof ItemWardenicBlade) && (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage()) && (random.nextInt(50) == 49)) {
					player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() - 1);
				}
			}
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			for (int i = 1; i < 5; i++) {
				if (player.getEquipmentInSlot(i) != null && (player.getEquipmentInSlot(i).getItem() instanceof ItemWardenArmor) && (player.getEquipmentInSlot(i).getItemDamage() != player.getEquipmentInSlot(i).getMaxDamage())) {
					player.getEquipmentInSlot(i).setItemDamage(player.getEquipmentInSlot(i).getItemDamage() + 1);
					WardenicChargeHelper.getUpgrade(player.getEquipmentInSlot(i)).onAttacked(event);
				}
			}
		}
	}
}
