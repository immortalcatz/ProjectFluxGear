package mortvana.legacy.errored.morttweaks.common;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.*;

import static mortvana.melteddashboard.util.helpers.science.MathHelper.RANDOM;

public class CommonProxy {

	public CommonProxy() {}

	//public void postInit() {}

	//Events

    @SubscribeEvent
    public void creepSplosion (LivingDeathEvent event) {
        if (!event.entityLiving.worldObj.isRemote && event.entityLiving instanceof EntityCreeper) {
            EntityLivingBase living = event.entityLiving;
            ItemStack firework = new ItemStack(Items.fireworks);
            NBTTagCompound compound = new NBTTagCompound();
            NBTTagCompound tags = new NBTTagCompound("Fireworks"); //TODO: NBT Constructor Changes
            NBTTagCompound explosion = new NBTTagCompound();
            explosion.setByte("Type", (byte) 3);
            explosion.setByte("Flight", (byte) 3);
            tags.setTag("Explosion", explosion);
            compound.setTag("Fireworks", tags);
            firework.setTagCompound(compound);
            EntityFireworkRocket rocket = new EntityFireworkRocket(living.worldObj, living.posX, living.posY+1, living.posZ, firework);
            living.worldObj.spawnEntityInWorld(rocket);
        }
    }

	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		if (!(event.entityLiving == null) || !event.entity.worldObj.isRemote) {
			EntityLivingBase living = event.entityLiving;
			if (MortTweaks.leather && living instanceof EntityCow) {
				addDrops(event, new ItemStack(Items.leather));
			} else if (MortTweaks.feathers && living instanceof EntityChicken) {
				addDrops(event, new ItemStack(Items.feather, (RANDOM.nextInt(5) + RANDOM.nextInt(1 + event.lootingLevel) + RANDOM.nextInt(1 + event.lootingLevel) + 1)));
			} else if (living instanceof EntityEnderman) {
				Block block = ((EntityEnderman) living).getCarriedBlock();
				if (block != null) {
					addDrops(event, new ItemStack(block, 1, ((EntityEnderman) living).getCarryingData()));
				}
			} else if (MortTweaks.animalBones && living instanceof EntityAnimal) {
				if (living.worldObj.difficultySetting.ordinal() == 0) {
					addDrops(event, new ItemStack(Items.bone, (RANDOM.nextInt(3) + RANDOM.nextInt(1 + event.lootingLevel) + 1)));
				}
			} else if (MortTweaks.fleshToFeathers && event.entityLiving instanceof EntityZombie) {
				Iterator iter = event.drops.iterator();
				while (iter.hasNext()) {
					EntityItem entity = (EntityItem) iter.next();
					if (entity.getEntityItem().getItem() == Items.rotten_flesh) {
						iter.remove();
					}
				}
				if (RANDOM.nextInt(3) == 0) {
					int amount = RANDOM.nextInt(3) + RANDOM.nextInt(event.lootingLevel + 1);
					if (amount > 0) {
						event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(Items.feather, amount)));
					}
				}
			}
		}
	}

	void addDrops(LivingDropsEvent event, ItemStack dropStack) {
		EntityItem entityitem = new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, dropStack);
		entityitem.delayBeforeCanPickup = 10;
		event.drops.add(entityitem);
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (MortTweaks.alwaysDropExp && !(event.source == DamageSource.lava || event.source == DamageSource.drown))
			event.entityLiving.recentlyHit += 50;

		if (MortTweaks.creeperBehavior) {
			EntityLivingBase receiver = event.entityLiving;
			if (receiver instanceof EntityCreeper) {
				Entity attacker = event.source.getEntity();
				if (attacker instanceof EntityLivingBase) {
					Entity target = ((EntityCreeper) receiver).getAttackTarget();
					if (target != null) {
						float d1 = receiver.getDistanceToEntity(((EntityCreeper) receiver).getAttackTarget());
						float d2 = receiver.getDistanceToEntity(attacker);
						if (d2 < d1) {
							((EntityCreeper) event.entityLiving).setAttackTarget((EntityLivingBase) event.source.getEntity());
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingSpawn(LivingSpawnEvent.SpecialSpawn event) {
		if (MortTweaks.mounts && !event.world.isRemote) {
			EntityLivingBase living = event.entityLiving;
			if (living.getClass() == EntitySpider.class && RANDOM.nextInt(100) == 0) {
				EntityCreeper creeper = new EntityCreeper(living.worldObj);
				spawnEntityLiving(living.posX, living.posY + 1, living.posZ, creeper, living.worldObj);
				if (living.riddenByEntity != null) {
					creeper.mountEntity(living.riddenByEntity);
				} else {
					creeper.mountEntity(living);
				}

				EntityXPOrb orb = new EntityXPOrb(living.worldObj, living.posX, living.posY, living.posZ, RANDOM.nextInt(20) + 20);
				orb.mountEntity(creeper);
			} else if (living.getClass() == EntitySlime.class) {
				if (RANDOM.nextInt(10) == 0) {
					attachSlime(living, ((EntitySlime) living).getSlimeSize() - 1);
				}
			}
		}
	}

	private void attachSlime(EntityLivingBase living, int size) {
		if (RANDOM.nextBoolean()) {
			EntitySlime slime = new EntitySlime(living.worldObj);
			slime.setSlimeSize(size);

			spawnEntityLiving(living.posX, living.posY + 1, living.posZ, slime, living.worldObj);
			if (living.riddenByEntity != null) {
				slime.mountEntity(living.riddenByEntity);
			} else {
				slime.mountEntity(living);
			}

			if (size > 1) {
				attachSlime(slime, size - 1);
			}
		}
	}

	public static void spawnEntityLiving(double x, double y, double z, EntityLiving entity, World world) {
		if (!world.isRemote) {
			entity.setPosition(x, y, z);
			entity.onSpawnWithEgg(null);
			world.spawnEntityInWorld(entity);
		}
	}

	private void func_71013_b(int par1, EntityPlayer player) {
		player.field_71079_bU = 0.0F;
		player.field_71089_bV = 0.0F;

		switch (par1) {
			case 0:
				player.field_71089_bV = -1.8F;
				break;
			case 1:
				player.field_71079_bU = 1.8F;
				break;
			case 2:
				player.field_71089_bV = 1.8F;
				break;
			case 3:
				player.field_71079_bU = -1.8F;
		}
	}
}
