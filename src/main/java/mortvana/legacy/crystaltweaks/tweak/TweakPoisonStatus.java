package mortvana.legacy.crystaltweaks.tweak;

import net.minecraft.potion.Potion;

import mortvana.legacy.crystaltweaks.common.MortTweaks;

public class TweakPoisonStatus extends Potion {
	public TweakPoisonStatus(int id, boolean badEffect, int color) {
		super(id, badEffect, color);
		this.setEffectiveness(0.25D);
	}

	@Override
	public boolean isReady(int par1, int par2) {
		int k;

		if (id == poison.id) {
			k = MortTweaks.poisonTime >> par2;
			return k <= 0 || par1 % k == 0;
		}

		return false;
	}
}
