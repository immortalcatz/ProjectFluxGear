package mortvana.legacy.clean.morttweaks.util;

import mortvana.legacy.errored.morttweaks.common.MortTweaks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;

public class TweakedFoodStats extends FoodStats {
	EntityPlayer player;
	int maxFoodLevel = MortTweaks.maxFoodLevel;
	int healLevel = MortTweaks.regenerationThreshold;

	public TweakedFoodStats(EntityPlayer player) {
		this.player = player;
		//this.foodLevel = maxFoodLevel;
	}

	@Override
	public void addStats(int food, float saturation) {
		if (MortTweaks.disableHunger)
			player.heal(Math.min(food, MortTweaks.maxHungerHeal));
		else {
			foodLevel = Math.min(food + foodLevel, maxFoodLevel);
			foodSaturationLevel = Math.min(foodSaturationLevel + (float) food * saturation * 2.0F, (float) foodLevel);
		}
	}

	//Override for custom effects
	@Override
	public void onUpdate(EntityPlayer player) {
		if (MortTweaks.disableHunger) {
			foodLevel = 14;
			foodSaturationLevel = 5.0F;
			foodExhaustionLevel = 0F;
			prevFoodLevel = 20;
		} else {
			int i = player.worldObj.difficultySetting.getDifficultyId();
			prevFoodLevel = foodLevel;

			if (foodExhaustionLevel > 4.0F) {
				foodExhaustionLevel -= 4.0F;

				if (foodSaturationLevel > 0.0F) {
					foodSaturationLevel = Math.max(foodSaturationLevel - 1.0F, 0.0F);
				} else if (i > 0) {
					foodLevel = Math.max(foodLevel - 1, 0);
				}
			}

			if ((MortTweaks.alwaysHungerRegenerate || foodLevel >= healLevel) && player.shouldHeal() && player.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration")) {
				++foodTimer;
				int timer = 80;
				if (MortTweaks.alwaysHungerRegenerate)
					timer = 80 + (maxFoodLevel - foodLevel) * 10;

				if (foodTimer >= timer) {
					player.heal(1.0F);
					addExhaustion(MortTweaks.foodExhaustion);
					foodTimer = 0;
				}
			} else if (this.foodLevel <= 0) {
				++foodTimer;

				if (foodTimer >= 80) {
					if (player.getHealth() > 10.0F || i >= 3 || player.getHealth() > 1.0F && i >= 2) {
						player.attackEntityFrom(DamageSource.starve, 1.0F);
					}

					foodTimer = 0;
				}
			} else {
				foodTimer = 0;
			}
		}
	}

	@Override
	public void addExhaustion(float par1) {
		if (!MortTweaks.disableHunger) {
			if (player.isSprinting()) {
				par1 /= 3;
			}
			super.addExhaustion(par1);
		}
	}

	@Override
	public boolean needFood() {
		if (MortTweaks.disableHunger) {
			return (player.getHealth() < player.getMaxHealth());
		} else {
			return foodLevel < maxFoodLevel;
		}
	}

    public void readNBT (NBTTagCompound nbt){
        if (nbt.hasKey("foodLevel")) {
            maxFoodLevel = nbt.getInteger("maxFoodLevel");
        }
        super.readNBT(nbt);
    }
    
    public void writeNBT (NBTTagCompound nbt){
        nbt.setInteger("maxFoodLevel", maxFoodLevel);
        super.writeNBT(nbt);
    }
}
