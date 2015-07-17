package oldcode.legacy.util.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mortvana.projectfluxgear.util.enums.EnumArmorType;

public class ItemArmorFluxGear extends ItemArmor {
	public static String sheetName;
	public static EnumArmorType armorType;
	public static String icon;

	public ItemArmorFluxGear(ArmorMaterial material, int par2, EnumArmorType type, String name, String sheet, String icon) {
		super(material, par2, type.ordinal());
		setMaxStackSize(1);
		setUnlocalizedName(name);
		sheetName = sheet;
		armorType = type;
		this.icon = icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(icon);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

		return "fluxgear:armor/" + sheetName + (armorType == EnumArmorType.PANTS ? "_2" : "_1") + ".png";

	}
}
