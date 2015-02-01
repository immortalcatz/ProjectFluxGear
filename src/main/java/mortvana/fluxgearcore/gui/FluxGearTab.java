package mortvana.fluxgearcore.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FluxGearTab extends CreativeTabs{

	public FluxGearTab(String internalName, String label, ItemStack icon) {
		super(internalName);
		name = label;
		display = icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return display;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		if (display != null) {
			return display.getItem();
		} else {
			return Items.potato;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel() {
		return name;
	}

	private ItemStack display;
	private String name;
}
