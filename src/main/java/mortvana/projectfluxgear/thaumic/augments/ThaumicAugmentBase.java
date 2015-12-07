package mortvana.projectfluxgear.thaumic.augments;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import mortvana.melteddashboard.util.enums.EnumAugmentHolder;
import mortvana.melteddashboard.util.helpers.StringHelper;

import thaumcraft.api.aspects.Aspect;

public abstract class ThaumicAugmentBase {
	public Aspect aspect;
	public Random random = new Random();

	public ThaumicAugmentBase(Aspect aspect) {
		this.aspect = aspect;
	}

	//Used when the player attacks something.
	public void onAttack(ItemStack stack, EntityPlayer player, Entity entity) {}

	//Used randomly, goes on armor
	public void onTick(World world, EntityPlayer player, ItemStack stack) {}

	//Used when the player is attacked by something
	public void onAttacked(LivingHurtEvent event) {}

	public String getQuote() {
		return StringHelper.localize("upgrade." + aspect.getName() + ".quote");
	}

	// THESE TWO AREN'T USABLE YET...
	@Deprecated
	public byte getSlot() {
		return 0;
	}

	@Deprecated
	public EnumAugmentHolder getHolder(ItemStack itemstack) {
		return EnumAugmentHolder.PANTS;
	}
}
