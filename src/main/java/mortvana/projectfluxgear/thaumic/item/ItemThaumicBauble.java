package mortvana.projectfluxgear.thaumic.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import baubles.api.BaubleType;
import mortvana.melteddashboard.intermod.baubles.item.FluxGearItemBauble;
import mortvana.melteddashboard.intermod.baubles.item.ItemBauble;
import mortvana.projectfluxgear.thaumic.common.ThaumicContent;

public class ItemThaumicBauble extends FluxGearItemBauble {

	public ItemThaumicBauble() {
		super("fluxgear", ThaumicContent.thaumicRevelationsTab);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (itemstack.getItemDamage() == 1) {
			world.playSoundAtEntity(player, "trevelations:abderp", 1, 1);
		}
		return super.onItemRightClick(itemstack, world, player);
	}
}