package mortvana.projectfluxgear.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.common.FluxGearContent;

public class PFGCreativeTab extends CreativeTabs{

	public PFGCreativeTab() {
		super("FluxGearTweaks");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return FluxGearContent.gemDioptase;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return getIconItemStack().getItem();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel() {
		return "projectfluxgear.creativeTab";
	}
}
