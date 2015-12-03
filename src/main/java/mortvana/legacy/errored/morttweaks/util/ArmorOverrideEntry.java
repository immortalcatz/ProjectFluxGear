package mortvana.legacy.errored.morttweaks.util;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class ArmorOverrideEntry {

	public ItemArmor armor;
	public String id;
	public ArmorMaterial material;
	public int renderIndex;
	public int armorType;
	public String unlocalizedName;
	public String textureName;

	public ArmorOverrideEntry (ItemArmor armor, String id) {
		this.armor = armor;
		this.id = id;
		material = armor.getArmorMaterial();
		renderIndex = armor.renderIndex;
		armorType = armor.armorType;
		unlocalizedName = armor.unlocalizedName; //TODO: Effing AccessTransformer!
		textureName = armor.iconString;
	}


}
