package mortvana.projectfluxgear.tinkers.modifiers;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

import tconstruct.library.ActiveToolMod;
import tconstruct.library.tools.ToolCore;

public class ActiveToolModFeedback extends ActiveToolMod {

	@Override
	public void afterBlockBreak(ToolCore tool, ItemStack itemstack, Block block, int x, int y, int z, EntityLivingBase entity) {
		super.afterBlockBreak(tool, itemstack, block, x, y, z, entity);

		if (hasFeedback(itemstack)) {
			//TODO: Create feedback damage, make DamageHelper a thing with damage types
			entity.attackEntityFrom(DamageSource.outOfWorld, 5.0F);
		}
	}

	@Override
	public int attackDamage(int modDamage, int currentDamage, ToolCore tool, NBTTagCompound nbt, NBTTagCompound toolNBT, ItemStack itemstack, EntityLivingBase player, Entity entity) {
		if (hasFeedback(itemstack)) {
			entity.attackEntityFrom(DamageSource.outOfWorld, 5.0F);
		}

		return super.attackDamage(modDamage, currentDamage, tool, nbt, toolNBT, itemstack, player, entity);
	}

	public boolean hasFeedback(ItemStack itemstack) {
		boolean feedback1 = false;
		boolean feedback2 = false;

		NBTTagCompound nbt = itemstack.getTagCompound().getCompoundTag("InfiTool");

		//TODO: Feedback Pairs (ex. Enderium and Terrasteel)

		return feedback1 == true && feedback2 == true;
	}
}
