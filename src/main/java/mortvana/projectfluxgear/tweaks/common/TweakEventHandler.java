package mortvana.projectfluxgear.tweaks.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import static mortvana.melteddashboard.util.repack.mortvana.science.math.MathHelper.diffRand;
import static mortvana.projectfluxgear.core.config.MortTweaksConfig.*;

public class TweakEventHandler {

	@SubscribeEvent
	public void killExpOrbs(EntityJoinWorldEvent event) {
		if (disableExp && event.entity instanceof EntityXPOrb && !event.world.isRemote) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void pluckChickens(LivingUpdateEvent event) {
		if (pluckChickens && event.entity instanceof EntityChicken && !event.entity.worldObj.isRemote) {
			boolean isChild = ((EntityChicken) event.entity).isChild();
			boolean doFeatherDrop = isChild ? chicksDropFeathers : adultsDropFeathers;
			int dropRarity = isChild ? chickFeatherRarity : adultFeatherRarity;
			if (doFeatherDrop && dropRarity > 0 && ((EntityChicken) event.entity).getRNG().nextInt(dropRarity) == 0) {
				int dropQuantity = isChild ? diffRand(chickMaxFeathers, chickMaxFeathers) : diffRand(adultMinFeathers, adultMaxFeathers);
				event.entity.dropItem(Items.feather, dropQuantity);
			}
		}
	}

	@SubscribeEvent
	public void enderDump(LivingDropsEvent event) {
		if (enderDumps && event.entity instanceof EntityEnderman && !event.entity.worldObj.isRemote) {
			EntityEnderman endermang = (EntityEnderman) event.entity;
			Block block = endermang.getCarriedBlock();
			if (block.getMaterial() != Material.air) {
				ItemStack stack = new ItemStack(block == Blocks.grass ? Blocks.dirt : block, 1, endermang.getCarryingData());
				event.drops.add(new EntityItem(endermang.worldObj, endermang.posX, endermang.posY, endermang.posZ, stack));
				endermang.setCarriedBlock(Blocks.air);
				endermang.setCarryingData(0);
			}
		}

	}


}
