package mortvana.projectfluxgear.thaumic.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import baubles.api.BaubleType;
import mortvana.melteddashboard.intermod.baubles.item.ItemBauble;
import mortvana.projectfluxgear.thaumic.common.ThaumicContent;

public class ItemWardenAmulet extends ItemBauble {

	public ItemWardenAmulet() {
		super();
		setUnlocalizedName("itemWardenAmulet");
		setCreativeTab(ThaumicContent.thaumicRevelationsTab);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon("trevelations:wardenamulet");
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.AMULET;
	}
}