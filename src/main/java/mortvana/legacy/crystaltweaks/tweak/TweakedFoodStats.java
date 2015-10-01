package mortvana.legacy.crystaltweaks.tweak;

import mortvana.legacy.errored.morttweaks.common.MortTweaks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;

public class TweakedFoodStats extends FoodStats {
	EntityPlayer player;
	int maxFoodLevel = MortTweaks.maxFoodLevel;
	int healLevel = MortTweaks.regenerationThreshold;

	public TweakedFoodStats(EntityPlayer player) {
		this.player = player;
		this.foodLevel = maxFoodLevel;
	}

	@Override
	public void addStats(int food, float saturation) {
		if (MortTweaks.disableHunger)
			player.heal(Math.min(food, MortTweaks.maxHungerHeal));
		else {
			this.foodLevel = Math.min(food + this.foodLevel, maxFoodLevel);
			this.foodSaturationLevel = Math.min(this.foodSaturationLevel + (float) food * saturation * 2.0F, (float) this.foodLevel);
		}
	}

	//Override for custom effects
	@Override
	public void onUpdate(EntityPlayer ePlayer) {
		if (MortTweaks.disableHunger) {
			foodLevel = 14;
			foodSaturationLevel = 5.0F;
			foodExhaustionLevel = 0F;
			prevFoodLevel = 20;
		} else {
			int i = ePlayer.worldObj.difficultySetting;
			this.prevFoodLevel = this.foodLevel;

			if (this.foodExhaustionLevel > 4.0F) {
				this.foodExhaustionLevel -= 4.0F;

				if (this.foodSaturationLevel > 0.0F) {
					this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 1.0F, 0.0F);
				} else
					if (i > 0) {
						this.foodLevel = Math.max(this.foodLevel - 1, 0);
					}
			}

			if ((MortTweaks.alwaysHungerRegenerate || this.foodLevel >= healLevel) && ePlayer.shouldHeal() && ePlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration")) {
				++this.foodTimer;
				int timer = 80;
				if (MortTweaks.alwaysHungerRegenerate)
					timer = 80 + (maxFoodLevel - foodLevel) * 10;

				if (this.foodTimer >= timer) {
					ePlayer.heal(1.0F);
					this.addExhaustion(MortTweaks.foodExhaustion);
					this.foodTimer = 0;
				}
			} else
				if (this.foodLevel <= 0) {
					++this.foodTimer;

					if (this.foodTimer >= 80) {
						if (ePlayer.getHealth() > 10.0F || i >= 3 || ePlayer.getHealth() > 1.0F && i >= 2) {
							ePlayer.attackEntityFrom(DamageSource.starve, 1.0F);
						}

						this.foodTimer = 0;
					}
				} else {
					this.foodTimer = 0;
				}
		}
	}

	@Override
	public void addExhaustion(float par1) {
		if (!MortTweaks.disableHunger) {
			if (player.isSprinting())
				par1 /= 3;
			super.addExhaustion(par1);
		}
	}

	@Override
	public boolean needFood() {
		if (MortTweaks.disableHunger)
			return (player.getHealth() < player.getMaxHealth());
		else
			return this.foodLevel < maxFoodLevel;
	}

    /*public void readNBT (NBTTagCompound par1NBTTagCompound)
    {
        if (par1NBTTagCompound.hasKey("foodLevel"))
        {
            this.maxFoodLevel = par1NBTTagCompound.getInteger("maxFoodLevel");
        }
        super.readNBT(par1NBTTagCompound);
    }
    
    public void writeNBT (NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("maxFoodLevel", this.maxFoodLevel);
        super.writeNBT(par1NBTTagCompound);
    }*/
}
