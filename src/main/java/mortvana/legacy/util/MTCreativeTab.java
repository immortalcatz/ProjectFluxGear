package mortvana.legacy.util;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import mortvana.legacy.common.ProjectFluxGear;

public class MTCreativeTab extends CreativeTabs
{
	public static final String NAME = "MortTech";
	public static MTCreativeTab instance;
	public static ItemStack stack;
	public static MTCreativeTab tab = new MTCreativeTab();
	
	public MTCreativeTab(String name)
	{
		super(name/*"MortTechTab"*/);
		LanguageRegistry.instance().addStringLocalization("itemGroup.MortTechTab", "MortTech");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + NAME, "en_US", "MortTech");
	}
	
@Override
public ItemStack getIconItemStack() {
	if (stack == null) {
		stack = new ItemStack(ProjectFluxGear.BasicOre);
	}
	return stack;
	//return new ItemStack(MortTech.GemOre);
	          icon = new ItemStack(Items.potato);
	return icon;
	}

@Override
public String getTranslatedTabLabel() {
return "MortTech";
	}

	public static MTCreativeTab instance() {
		if (instance == null) {
			instance = new MTCreativeTab(CreativeTabs.getNextID(), NAME);
		}
		return instance;
	}

	public MTCreativeTab(int id, String name) {
		super(id, name);
		LanguageRegistry.instance().addStringLocalization("itemGroup." + NAME, "en_US", "MortTech");
	}

}