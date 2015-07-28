package mortvana.projectfluxgear.thaumic.augments;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.monster.EntityEldritchCrab;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;

public class ThaumicAugmentExubitor extends ThaumicAugmentBase {
	public ThaumicAugmentExubitor(Aspect aspect) {
		super(aspect);
	}

	@Override
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {
		super.onAttack(stack, player, entity);
		if (entity instanceof IEldritchMob || entity instanceof ITaintedMob || entity instanceof EntityCultist || entity instanceof EntityCultistLeader || entity instanceof EntityEldritchCrab || entity instanceof EntityInhabitedZombie) {
			DamageSource damageSource = new EntityDamageSource("warden", player);
			entity.attackEntityFrom(damageSource, 20);
		}
	}

	@Override
	public void onTick(World world, EntityPlayer player, ItemStack stack) {
		super.onTick(world, player, stack);
		if (player.isPotionActive(Config.potionDeathGazeID)) {
			if (random.nextInt(5) == 1) {
				player.removePotionEffect(Config.potionDeathGazeID);
			}
		}
		if (player.isPotionActive(Config.potionTaintPoisonID)) {
			if (random.nextInt(5) == 1) {
				player.removePotionEffect(Config.potionTaintPoisonID);
			}
		}
		if (player.isPotionActive(Potion.wither.getId())) {
			if (random.nextInt(5) == 1) {
				player.removePotionEffect(Potion.wither.getId());
			}
		}
	}

	@Override
	public void onAttacked(LivingHurtEvent event) {
		super.onAttacked(event);
		if (event.source.getEntity() instanceof IEldritchMob || event.source.getEntity() instanceof ITaintedMob || event.source.getEntity() instanceof EntityCultist || event.source.getEntity() instanceof EntityCultistLeader || event.source.getEntity() instanceof EntityEldritchCrab || event.source.getEntity() instanceof EntityInhabitedZombie) {
			event.setCanceled(true);
		}
	}
}
