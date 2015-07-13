package mortvana.projectfluxgear.util.module.classifiers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IRightClickModule extends IFluxGearModule {

	public void onRightClick(EntityPlayer player, World world, ItemStack itemstack);

	public void onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ);

	public void onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ);

	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int par4);
}
