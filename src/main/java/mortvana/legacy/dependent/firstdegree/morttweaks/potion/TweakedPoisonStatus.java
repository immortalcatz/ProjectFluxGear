package mortvana.legacy.dependent.firstdegree.morttweaks.potion;

import net.minecraft.potion.Potion;

import mortvana.legacy.errored.morttweaks.common.MortTweaks;

public class TweakedPoisonStatus extends Potion {
	public TweakedPoisonStatus(int id, boolean badEffect, int color) {
		super(id, badEffect, color);
		setEffectiveness(0.25D);
	}

	@Override
	public boolean isReady(int par1, int par2) {
		if (id == poison.id) {
			int k = MortTweaks.poisonTime >> par2;
			return k <= 0 || par1 % k == 0;
		}
		return false;
	}
}
