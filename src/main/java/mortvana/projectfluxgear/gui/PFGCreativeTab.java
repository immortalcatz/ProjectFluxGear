package mortvana.projectfluxgear.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.common.FluxGearContent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PFGCreativeTab extends CreativeTabs {
    public PFGCreativeTab() {
        super("ThermalTinkerer");
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
        return "thermaltinkerer.creativeTab";
    }
}
