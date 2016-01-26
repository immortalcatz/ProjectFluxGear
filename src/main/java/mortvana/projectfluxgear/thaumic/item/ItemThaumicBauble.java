package mortvana.projectfluxgear.thaumic.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;

import mortvana.projectfluxgear.library.FluxGearLibrary;

public class ItemThaumicBauble extends FluxGearItemBauble {

	public ItemThaumicBauble() {
		super("fluxgear", FluxGearLibrary.thaumicRevelationsTab);
        setFolder("/baubles");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (itemstack.getMetadata() == 1) {
			world.playSoundAtEntity(player, "fluxgear:abderp", 1, 1);
		}
		return super.onItemRightClick(itemstack, world, player);
	}
}