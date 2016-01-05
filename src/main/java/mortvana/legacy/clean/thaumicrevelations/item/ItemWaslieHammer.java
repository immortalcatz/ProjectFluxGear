package mortvana.legacy.clean.thaumicrevelations.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.core.common.ProjectFluxGear;
import mortvana.projectfluxgear.library.FluxGearLibrary;

public class ItemWaslieHammer extends Item {

	public ItemWaslieHammer() {
		super();
		setUnlocalizedName("itemWaslieHammer");
		setCreativeTab(FluxGearLibrary.thaumicRevelationsTab);
		setMaxStackSize(1);
		canRepair = false;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.openGui(ProjectFluxGear.instance, 0, world, 0, 0, 0);
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("projectfluxgear:tool/wasliehammer");
	}
}